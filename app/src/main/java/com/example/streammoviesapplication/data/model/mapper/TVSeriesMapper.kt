package com.example.streammoviesapplication.data.model.mapper

import com.example.streammoviesapplication.data.model.localData.TVSeriesEntity
import com.example.streammoviesapplication.data.model.remoteData.TabTVSeriesList

object TVSeriesMapper {

    fun mapRemoteTabToTabEntity(remote: TabTVSeriesList): TVSeriesEntity {
        return TVSeriesEntity(
            id = remote.id,
            adult = remote.adult,
            backdropPath = remote.backdrop_path,
            firstAirDate = remote.first_air_date,
            name = remote.name,
            originalLanguage = remote.original_language,
            originalName = remote.original_name,
            overview = remote.overview,
            popularity = remote.popularity,
            posterPath = remote.poster_path,
            voteAverage = remote.vote_average,
            voteCount = remote.vote_count
        )
    }

}