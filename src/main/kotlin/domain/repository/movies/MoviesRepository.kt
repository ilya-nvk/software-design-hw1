package com.ilyanvk.domain.repository.movies

import com.ilyanvk.domain.model.Movie

/**
 * Interface for a Movies Repository.
 * Contains methods for managing movies.
 */
interface MoviesRepository {
    /**
     * Retrieves all movies.
     * @return List of movies.
     */
    fun getMovies(): List<Movie>

    /**
     * Adds a movie.
     * @param movie The movie to be added.
     */
    fun addMovie(movie: Movie)

    /**
     * Deletes a movie.
     * @param movieId ID of the movie to be deleted.
     */
    fun deleteMovie(movieId: Int)
}
