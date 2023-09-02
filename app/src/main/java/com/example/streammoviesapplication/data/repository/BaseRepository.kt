package com.example.streammoviesapplication.data.repository

import com.example.streammoviesapplication.data.resource.Resource
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository() {
    suspend fun <T> safeApiCall(apiToBeCalled: suspend() -> Response<T>) : Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<T> = apiToBeCalled()

                if(response.isSuccessful){
                    Resource.Success(data = response.body()!!)
                } else {
                    val errorResponse: ExampleErrorResponse? = getErrorResponse(response.errorBody())
                    Resource.Error(errorMessage = errorResponse?.status_message ?: "Something went wrong")

                }

            } catch (e: HttpException) {
                Resource.Error(errorMessage = e.message() ?: "Something went wrong")
            } catch(e: IOException) {
                Resource.Error("Please check your network connection")
            } catch (e: Exception) {
                Resource.Error(errorMessage = "Something went wrong")
            }
        }
    }

     private fun getErrorResponse(errorBody: ResponseBody?): ExampleErrorResponse? {
         if(errorBody == null) {
             return null
         }
         val responseString = errorBody.string()
        return Gson().fromJson(responseString, ExampleErrorResponse::class.java)
    }
}