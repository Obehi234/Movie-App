package com.example.streammoviesapplication.data.model.mapper

import com.example.streammoviesapplication.data.model.localData.RelatedMoviesEntity
import com.example.streammoviesapplication.data.model.remoteData.relatedMovies.RelatedMovieList

object RelatedMoviesMapper {
    fun mapRelatedMoviesRemoteToEntity(remote: RelatedMovieList) : RelatedMoviesEntity {
        return RelatedMoviesEntity(
            adult = remote.adult,
            backdrop_path = remote.backdrop_path,
            id = remote.id,
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