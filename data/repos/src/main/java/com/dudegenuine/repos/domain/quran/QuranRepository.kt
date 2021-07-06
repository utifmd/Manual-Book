package com.dudegenuine.repos.domain.quran

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.*
import com.dudegenuine.domain.Quran
import com.dudegenuine.remote.mapper.QuranDataMapper
import com.dudegenuine.remote.persistence.IQuranPersistence
import com.dudegenuine.repos.network.Resource
import com.dudegenuine.repos.network.ResourceManager
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

/**
 * Manual Book created by utifmd on 27/06/21.
 */
class QuranRepository @Inject constructor(
    val persistence: IQuranPersistence,
    val mapper: QuranDataMapper ) : IQuranRepository {

    override fun getQuran(param: Map<String, Int>): Flow<PagingData<Quran>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { QuranPagingSource(param) }
        ).flow
    }

    inner class QuranPagingSource( private val parentParam: Map<String, Int> ): PagingSource<Int, Quran>(){

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Quran> {
            val startPage = parentParam["start_page"] ?: DEFAULT_PAGE_INDEX
            val finishPage = parentParam["finish_page"] ?: DEFAULT_PAGE_INDEX

            val position = params.key ?: startPage

            return try {
                /*val response = persistence.getQuran(mapOf("page_number" to position.toString())).also {
                    persistence.getRecitations(position.toString())
                }*/

                val quranResp = CoroutineScope(coroutineContext).async {
                    persistence.getQuran( mapOf(
                        "page_number" to position.toString(),
                        "chapter_number" to parentParam["chapter_number"].toString()
                    ))
                }
                val audioResp = CoroutineScope(coroutineContext).async {
                    persistence.getRecitations(position.toString())
                }

                joinAll(audioResp, quranResp)

                val data = mapper.convertRequestQuranList(quranResp.await(), audioResp.await())

                val nextKey = if (data.isEmpty()) {
                    null
                } else {
                    if (position >= finishPage) null else position + 1 // (params.loadSize / NETWORK_PAGE_SIZE)
                }
                LoadResult.Page(
                    data = data,
                    prevKey = null, // if (position <= DEFAULT_PAGE_INDEX) null else position - 1,
                    nextKey = nextKey
                )
            } catch (exception: IOException) {
                return LoadResult.Error(exception)
            } catch (exception: HttpException) {
                return LoadResult.Error(exception)
            }
        }

        override fun getRefreshKey(state: PagingState<Int, Quran>): Int? {
            return state.anchorPosition?.let { anchorPosition ->
                state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                    ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
            }
        }
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 7
        private const val DEFAULT_PAGE_INDEX = 1
    }
}