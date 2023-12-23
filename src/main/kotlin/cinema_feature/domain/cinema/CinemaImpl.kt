package com.ilyanvk.cinema_feature.domain.cinema

import com.ilyanvk.cinema_feature.domain.cinema.Cinema.Companion.CAPACITY
import com.ilyanvk.cinema_feature.domain.model.Movie
import com.ilyanvk.cinema_feature.domain.model.Session
import com.ilyanvk.cinema_feature.domain.model.Ticket
import com.ilyanvk.cinema_feature.domain.repository.movies.MoviesRepository
import com.ilyanvk.cinema_feature.domain.repository.sessions.SessionsRepository
import com.ilyanvk.cinema_feature.domain.repository.tickets.TicketsRepository

class CinemaImpl(
    private val moviesRepository: MoviesRepository,
    private val sessionsRepository: SessionsRepository,
    private val ticketsRepository: TicketsRepository,
) : Cinema {

    override val movies: List<Movie>
        get() = moviesRepository.getMovies()
    override val sessions: List<Session>
        get() = sessionsRepository.getSessions()

    override fun sellTicket(sessionId: Int, seat: Int): Ticket {
        require(getAvailableSeats(sessionId).contains(seat)) { "Seat $seat is not available for sale." }

        bookSeat(sessionId, seat)

        val ticket = Ticket((ticketsRepository.getTickets().maxOfOrNull { it.id } ?: -1) + 1, sessionId, seat)
        ticketsRepository.addTicket(ticket)

        return ticket
    }

    override fun returnTicket(ticketId: Int) {
        val ticket = ticketsRepository.getTickets().find { it.id == ticketId }
            ?: throw IllegalArgumentException("Ticket not found in the sold tickets.")
        val session = sessionsRepository.getSession(ticket.sessionId)
        require(session.time > System.currentTimeMillis()) { "Session has already started. Ticket cannot be returned." }
        ticketsRepository.deleteTicket(ticketId)
        cancelBooking(ticket.sessionId, ticket.seat)
    }

    override fun addMovie(title: String, durationMin: Int) {
        val id = (moviesRepository.getMovies().maxOfOrNull { it.id } ?: -1) + 1
        moviesRepository.addMovie(Movie(id, title, durationMin))
    }

    override fun addSession(movieId: Int, time: Long) {
        require(moviesRepository.getMovies().any { it.id == movieId }) { "Movie not found in the cinema." }
        require(time > System.currentTimeMillis()) { "Session time must be in the future." }
        val id = (sessionsRepository.getSessions().maxOfOrNull { it.id } ?: -1) + 1
        sessionsRepository.addSession(Session(id, movieId, time))
    }

    override fun editMovie(movieId: Int, title: String, durationMin: Int) {
        moviesRepository.editMovie(movieId, title, durationMin)
    }

    override fun editSession(sessionId: Int, movieId: Int, time: Long) {
        require(time > System.currentTimeMillis()) { "Session time must be in the future." }
        sessionsRepository.editSession(sessionId, movieId, time)
    }

    override fun deleteMovie(movieId: Int) {
        moviesRepository.deleteMovie(movieId)
        sessionsRepository.getSessions().filter { it.movieId == movieId }.forEach { deleteSession(it.id) }
    }

    override fun deleteSession(sessionId: Int) {
        sessionsRepository.deleteSession(sessionId)
    }

    override fun getAvailableSeats(sessionId: Int): List<Int> {
        return (1..CAPACITY).toList() - sessionsRepository.getBookedSeats(sessionId).toSet()
    }

    override fun getBookedSeats(sessionId: Int): List<Int> {
        return sessionsRepository.getBookedSeats(sessionId)
    }

    private fun bookSeat(sessionId: Int, seat: Int) {
        sessionsRepository.bookSeat(sessionId, seat)
    }

    private fun cancelBooking(sessionId: Int, seat: Int) {
        sessionsRepository.cancelBooking(sessionId, seat)
    }
}
