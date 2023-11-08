package com.example.streammoviesapplication.data.model.mapper

import com.example.streammoviesapplication.data.model.localData.MovieResultEntity
import com.example.streammoviesapplication.data.model.remoteData.MovieResult

object MovieTabMapper {

    fun mapRemoteTabToTabEntity(remote: MovieResult) : MovieResultEntity {
        return MovieResultEntity(
            id = remote.id,
            adult = remote.adult,
            backdrop_path = remote.backdrop_path,
            genre_ids = remote.genre_ids,
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