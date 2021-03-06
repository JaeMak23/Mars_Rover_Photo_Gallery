package com.jaisonmacklin.test.marsroverphotogallery.data.repository
import com.jaisonmacklin.test.marsroverphotogallery.utils.Result

abstract class BaseRepository {

    companion object {
        private const val UNAUTHORIZED = "Unauthorized"
        private const val NOT_FOUND = "Not found"
        private const val SOMETHING_WRONG = "Something went wrong"

        fun <T : Any> handleSuccess(data: T): Result<T> {
            return Result.Success(data)
        }

        fun <T : Any> handleException(code: Int): Result<T> {
            val exception = getErrorMessage(code)
            return Result.Error(Exception(exception))
        }

        private fun getErrorMessage(httpCode: Int): String {
            return when (httpCode) {
                401 -> UNAUTHORIZED
                404 -> NOT_FOUND
                else -> SOMETHING_WRONG
            }
        }
    }
}