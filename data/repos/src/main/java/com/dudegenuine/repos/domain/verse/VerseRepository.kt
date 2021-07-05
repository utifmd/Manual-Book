package com.dudegenuine.repos.domain.verse

import androidx.lifecycle.LiveData
import com.dudegenuine.domain.Verse
import com.dudegenuine.remote.mapper.VerseDataMapper
import com.dudegenuine.remote.payload.IVerseResponsePayload
import com.dudegenuine.remote.persistence.IVersePersistence
import com.dudegenuine.repos.network.Resource
import com.dudegenuine.repos.network.ResourceManager
import javax.inject.Inject

/**
 * Manual Book created by utifmd on 04/07/21.
 */
class VerseRepository @Inject constructor(
    private val persistence: IVersePersistence,
    private val mapper: VerseDataMapper): IVerseRepository {

    override suspend fun getVerse(path: String,
        params: Map<String, String>): LiveData<Resource<Verse>> {

        return object: ResourceManager<Verse, IVerseResponsePayload>(){
            override fun shouldFetch(result: Verse?): Boolean = true

            override suspend fun fetchDataRemote(): IVerseResponsePayload =
                persistence.getVerse(path, params)

            override fun processRequest(response: IVerseResponsePayload): Verse =
                mapper.convertVerseToItem(response)

            override fun saveData(result: Verse) {
                TODO("Not yet implemented")
            }

            override fun fetchDataLocal(): Verse {
                TODO("Not yet implemented")
            }

        }.build().commit()
    }
}