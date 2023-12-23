package com.ilyanvk.cinema_feature.domain.repository.movies

import com.ilyanvk.cinema_feature.data.dao.GenericDao
import com.ilyanvk.cinema_feature.domain.model.Movie

class MoviesRepositoryImpl(
    private val dao: GenericDao<Movie>,
) : MoviesRepository {
    override fun getMovies(): List<Movie> {
        return dao.getAll()
    }

    override fun addMovie(movie: Movie) {
        dao.add(movie)
    }

    override fun deleteMovie(movieId: Int) {
        val movie =
            dao.getAll().find { it.id == movieId } ?: throw IllegalArgumentException("Movie not found in the cinema.")
        dao.delete(movie)
    }
}
