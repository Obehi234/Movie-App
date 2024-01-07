package com.example.streammoviesapplication.data.repository

import android.util.Log
import com.example.streammoviesapplication.data.db.DocumentaryDao
import com.example.streammoviesapplication.data.model.localData.DocumentaryEntity
import com.example.streammoviesapplication.data.model.mapper.DocumentaryMapper
import com.example.streammoviesapplication.network.MovieService
import com.example.streammoviesapplication.utils.resource.Resource
import com.example.streammoviesapplication.utils.resource.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DocumentaryRepositoryImpl
@Inject constructor(
    private val api: MovieService,
    private val documentaryDao: DocumentaryDao
): IDocumentaryRepository {
    override suspend fun fetchDocumentary(): Flow<Resource<List<DocumentaryEntity>>> {

        return flow {
            when(val response = safeApiCall { api.getDocumentary(99) }) {
                is Resource.Success -> {
                    val documentaryTabResult = response.data?.results
                    val documentaryList = documentaryTabResult?.map {result ->
                        DocumentaryMapper.mapRemoteToEntity(result)
                    }
                    Log.d("CHECK_DOCUMENTARY_REPO", "$documentaryTabResult")
                    if(documentaryList != null) {
                        Log.d("CHECK_DOCUMENTARY_REPO", "$documentaryList")
                        documentaryDao.insertDocumentaryList(documentaryList)

                    }
                    emit(Resource.Success(documentaryDao.getDocumentaries()))
                }
                is Resource.Error -> {
                    emit(Resource.Error("${response.message}"))

                }

                is Resource.Loading -> {
                    emit(Resource.Loading())
                }

                else -> {}
            }
        }
    }
}