package com.example.moviesapp.data.converters

import com.example.moviesapp.data.db.entity.MovieEntity
import com.example.moviesapp.data.dto.MovieDto
import com.example.moviesapp.domain.models.Movie

class MovieDbConvertor {
    fun map(movie: MovieDto): MovieEntity {
        return MovieEntity(movie.id, movie.resultType, movie.image, movie.title, movie.description)
    }

    fun map(movie: MovieEntity): Movie {
        return Movie(movie.id, movie.resultType, movie.image, movie.title, movie.description, false)
    }
}