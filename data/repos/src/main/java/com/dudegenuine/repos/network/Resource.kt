package com.dudegenuine.repos.network

/**
 * Manual Book created by utifmd on 22/06/21.
 */
data class Resource<T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null // val throwable: Throwable? = null
) {

    companion object {
        fun<T> onLoading(): Resource<T> =
            Resource(Status.LOADING)

        fun<T> onSuccess(data: T): Resource<T> =
            Resource(Status.SUCCESS, data)

        fun<T> onError(data:T?, message: String?): Resource<T> =
            Resource(Status.ERROR, data, message)

        const val TIMEOUT_CODE = 102001

        fun onErrorMessage(code: Int): String {
            return when(code){
                TIMEOUT_CODE -> "Timeout"
                401 -> "Unauthorised"
                404 -> "Not Found"
                else -> "No Internet Connection" // "Something Went Wrong"
            }
        }
    }

    enum class Status { LOADING, SUCCESS, ERROR }
}