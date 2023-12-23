package com.ilyanvk.cinema_feature.domain.repository.movies

import com.ilyanvk.cinema_feature.data.dao.GenericDao
import com.ilyanvk.cinema_feature.domain.model.Movie

class MoviesRepositoryImpl(
    private val dao: GenericDao<Movie>,
) : MoviesRepository {
    override fun getMovies(): List<Movie> {
        return dao.getAll()
    }

    override fun getMovie(movieId: Int): Movie {
        return dao.getAll().find { it.id == movieId }
            ?: throw IllegalArgumentException("Movie not found in the cinema.")
    }

    override fun addMovie(movie: Movie) {
        dao.add(movie)
    }

    override fun deleteMovie(movieId: Int) {
        val movie =
            dao.getAll().find { it.id == movieId } ?: throw IllegalArgumentException("Movie not found in the cinema.")
        dao.delete(movie)
    }

    override fun editMovie(movieId: Int, title: String, durationMin: Int) {
        val movie = getMovie(movieId)
        dao.delete(movie)
        dao.add(movie.copy(title = title, durationMin = durationMin))
    }
}
