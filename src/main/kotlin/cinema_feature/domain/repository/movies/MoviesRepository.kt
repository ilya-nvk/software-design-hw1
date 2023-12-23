package com.ilyanvk.cinema_feature.domain.repository.movies

import com.ilyanvk.cinema_feature.domain.model.Movie

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
     * Retrieves a movie by ID.
     * @param movieId ID of the movie.
     * @return Movie with the specified ID.
     */
    fun getMovie(movieId: Int): Movie

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

    /**
     * Edits a movie.
     * @param movieId ID of the movie to be edited.
     * @param title New title of the movie.
     * @param durationMin New duration of the movie in minutes.
     */
    fun editMovie(movieId: Int, title: String, durationMin: Int)
}
