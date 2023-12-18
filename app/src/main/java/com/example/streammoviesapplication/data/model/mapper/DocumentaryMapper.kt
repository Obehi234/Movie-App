package com.example.streammoviesapplication.data.model.mapper

import com.example.streammoviesapplication.data.model.localData.DocumentaryEntity
import com.example.streammoviesapplication.data.model.remoteData.DocumentaryList

object DocumentaryMapper {
    fun mapRemoteToEntity(remote: DocumentaryList) : DocumentaryEntity{
        return DocumentaryEntity(
            adult = remote.adult,
            backdrop_path = remote.backdrop_path,
            genre_ids = remote.genre_ids,
            id = remote.id,
            original_language = remote.original_language,
            original_title = remote.original_title,
            overview = remote.overview,
            popularity = remote.popularity,
            poster_path = remote.poster_path,
            release_date = remote.release_date,
            title = remote.title,
            video = remote.video,
            vote_average = remote.vote_average,
            vote_count = remote.vote_count
        )
    }
}