package com.example.streammoviesapplication.utils

import com.example.streammoviesapplication.data.model.localData.TrendingMoviesEntity
import com.example.streammoviesapplication.data.model.remoteData.TrendingMovies

object MovieMapper {

    fun mapRemoteToEntity(remote: TrendingMovies) : TrendingMoviesEntity {
        return TrendingMoviesEntity(
            id = remote.id,
            adult = remote.adult,
            backdrop_path = remote.backdrop_path,
            genre_ids = remote.genre_ids,
            media_type = remote.media_type,
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