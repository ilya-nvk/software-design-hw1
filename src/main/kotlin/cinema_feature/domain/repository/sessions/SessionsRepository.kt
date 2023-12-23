package com.ilyanvk.cinema_feature.domain.repository.sessions

import com.ilyanvk.cinema_feature.domain.model.Session

/**
 * Interface for a Sessions Repository.
 * Contains methods for managing sessions and bookings.
 */
interface SessionsRepository {
    /**
     * Retrieves all sessions.
     * @return List of sessions.
     */
    fun getSessions(): List<Session>

    /**
     * Retrieves a session by ID.
     * @param sessionId ID of the session.
     * @return Session with the specified ID.
     */
    fun getSession(sessionId: Int): Session

    /**
     * Adds a session.
     * @param session The session to be added.
     */
    fun addSession(session: Session)

    /**
     * Deletes a session.
     * @param sessionId ID of the session to be deleted.
     */
    fun deleteSession(sessionId: Int)

    /**
     * Edits a session.
     * @param sessionId ID of the session to be edited.
     * @param movieId ID of the movie to be shown.
     * @param time Time of the session.
     */
    fun editSession(sessionId: Int, movieId: Int, time: Long)

    /**
     * Retrieves all booked seats for a session.
     * @param sessionId ID of the session.
     * @return List of booked seat numbers.
     */
    fun getBookedSeats(sessionId: Int): List<Int>

    /**
     * Books a seat for a session.
     * @param sessionId ID of the session.
     * @param seat Seat number to be booked.
     */
    fun bookSeat(sessionId: Int, seat: Int)

    /**
     * Cancels a booking for a session.
     * @param sessionId ID of the session.
     * @param seat Seat number of the booking to be cancelled.
     */
    fun cancelBooking(sessionId: Int, seat: Int)
}
