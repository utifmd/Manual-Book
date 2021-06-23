package com.dudegenuine.repos.network

/**
 * Manual Book created by utifmd on 22/06/21.
 */
data class Resource<T>(
    val status: Status,
    val data: T? = null,
    val throwable: Throwable? = null) {

    companion object {
        fun<T> onLoading(): Resource<T> =
            Resource(Status.LOADING)

        fun<T> onSuccess(data: T): Resource<T> =
            Resource(Status.SUCCESS, data)

        fun<T> onError(data:T?, throwable: Throwable): Resource<T> =
            Resource(Status.ERROR, data, throwable)
    }

    enum class Status { LOADING, SUCCESS, ERROR }
}