package com.example.streammoviesapplication.data.model.mapper

import com.example.streammoviesapplication.data.model.localData.MovieDetailsEntity
import com.example.streammoviesapplication.data.model.remoteData.movieDetails.MovieDetails

object MovieDetailsMapper {
    fun mapRemoteToEntity(remote: MovieDetails): MovieDetailsEntity {
        return MovieDetailsEntity(
            adult = remote.adult,
            backdrop_path = remote.backdrop_path,
            budget = remote.budget,
            genres = remote.genres,
            homepage = remote.homepage,
            id = remote.id,
            imdb_id = remote.imdb_id,
            original_language = remote.original_language,
            original_title = remote.original_title,
            overview = remote.overview,
            popularity = remote.popularity,
            poster_path = remote.poster_path,
            release_date = remote.release_date,
            revenue = remote.revenue,
            runtime = remote.runtime,
            spoken_languages = remote.spoken_languages,
            status = remote.status,
            tagline = remote.tagline,
            title = remote.title,
            video = remote.video,
            vote_average = remote.vote_average,
            vote_count = remote.vote_count
        )

    }
}