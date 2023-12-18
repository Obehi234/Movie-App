package com.example.streammoviesapplication.data.repository

import com.example.streammoviesapplication.data.model.localData.DocumentaryEntity
import com.example.streammoviesapplication.utils.resource.Resource
import kotlinx.coroutines.flow.Flow

interface IDocumentaryRepository {
    suspend fun fetchDocumentary() : Flow<Resource<List<DocumentaryEntity>>>
}