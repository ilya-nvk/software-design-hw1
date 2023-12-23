package com.ilyanvk.cinema_feature.domain.cinema

import com.ilyanvk.cinema_feature.domain.model.Movie
import com.ilyanvk.cinema_feature.domain.model.Session
import com.ilyanvk.cinema_feature.domain.model.Ticket

/**
 * Interface for a Cinema.
 * Contains methods for managing movies, sessions, and tickets.
 */
interface Cinema {
    /**
     * List of movies currently available in the cinema.
     */
    val movies: List<Movie>

    /**
     * List of sessions currently available in the cinema.
     */
    val sessions: List<Session>

    /**
     * Sells a ticket for a specific session and seat.
     * @param sessionId ID of the session.
     * @param seat Seat number.
     * @return Ticket for the session and seat.
     */
    fun sellTicket(sessionId: Int, seat: Int): Ticket

    /**
     * Returns a ticket.
     * @param ticketId ID of the ticket to be returned.
     */
    fun returnTicket(ticketId: Int)

    /**
     * Adds a movie to the cinema.
     * @param title Title of the movie.
     * @param durationMin Duration of the movie in minutes.
     */
    fun addMovie(title: String, durationMin: Int)

    /**
     * Adds a session for a movie at a specific time.
     * @param movieId ID of the movie.
     * @param time Time of the session.
     */
    fun addSession(movieId: Int, time: Long)

    /**
     * Deletes a movie from the cinema.
     * @param movieId ID of the movie to be deleted.
     */
    fun deleteMovie(movieId: Int)

    /**
     * Deletes a session from the cinema.
     * @param sessionId ID of the session to be deleted.
     */
    fun deleteSession(sessionId: Int)

    /**
     * Gets the available seats for a session.
     * @param sessionId ID of the session.
     * @return List of available seat numbers.
     */
    fun getAvailableSeats(sessionId: Int): List<Int>

    /**
     * Gets the booked seats for a session.
     * @param sessionId ID of the session.
     * @return List of booked seat numbers.
     */
    fun getBookedSeats(sessionId: Int): List<Int>

    companion object {
        /**
         * Capacity of the cinema.
         * This constant is used in a few other classes, so it is defined here as a public and static property.
         */
        const val CAPACITY = 10
    }
}
