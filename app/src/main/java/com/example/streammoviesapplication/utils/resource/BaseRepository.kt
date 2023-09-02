package com.example.streammoviesapplication.utils.resource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<T> = apiToBeCalled()

                if (response.isSuccessful && response.body() != null) {
                    Resource.Success(response.body())
                } else {
                    Resource.Error("Something went wrong")

                }

            } catch (e: HttpException) {
                Resource.Error(e.message() ?: "Something went wrong")
            } catch (e: IOException) {
                Resource.Error("Please check your network connection")
            } catch (e: Exception) {
                Resource.Error("Something went wrong")
            }
        }
    }
