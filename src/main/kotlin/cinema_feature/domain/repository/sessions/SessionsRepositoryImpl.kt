package com.ilyanvk.cinema_feature.domain.repository.sessions

import com.ilyanvk.cinema_feature.data.dao.GenericDao
import com.ilyanvk.cinema_feature.domain.cinema.Cinema
import com.ilyanvk.cinema_feature.domain.model.Session

class SessionsRepositoryImpl(
    private val dao: GenericDao<Session>,
) : SessionsRepository {
    override fun getSessions(): List<Session> {
        return dao.getAll()
    }

    override fun getSession(sessionId: Int): Session {
        return dao.getAll().find { it.id == sessionId } ?: throw IllegalArgumentException("Session not found.")
    }

    override fun addSession(session: Session) {
        dao.add(session)
    }

    override fun deleteSession(sessionId: Int) {
        val session = getSessionById(sessionId)
        dao.delete(session)
    }

    override fun editSession(sessionId: Int, movieId: Int, time: Long) {
        val session = getSessionById(sessionId)
        dao.delete(session)
        dao.add(session.copy(movieId = movieId, time = time))
    }

    override fun getBookedSeats(sessionId: Int): List<Int> {
        val session = getSessionById(sessionId)
        return session.bookedSeats.toList()
    }

    override fun bookSeat(sessionId: Int, seat: Int) {
        val session = getSessionById(sessionId)
        require(seat !in session.bookedSeats) { "Seat is already booked." }
        require(seat in 1..Cinema.CAPACITY) { "Seat number is out of range." }
        val newSession = session.copy(bookedSeats = session.bookedSeats + seat)
        dao.delete(session)
        dao.add(newSession)
    }

    override fun cancelBooking(sessionId: Int, seat: Int) {
        val session = getSessionById(sessionId)
        require(seat in session.bookedSeats) { "Seat is not booked." }
        val newSession = session.copy(bookedSeats = session.bookedSeats - seat)
        dao.delete(session)
        dao.add(newSession)
    }

    private fun getSessionById(sessionId: Int): Session {
        return dao.getAll().find { it.id == sessionId } ?: throw IllegalArgumentException("Session not found.")
    }

}