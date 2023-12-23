package com.ilyanvk.cinema_feature.domain.repository.tickets

import com.ilyanvk.cinema_feature.data.dao.GenericDao
import com.ilyanvk.cinema_feature.domain.model.Ticket

class TicketsRepositoryImpl(
    private val dao: GenericDao<Ticket>,
) : TicketsRepository {
    override fun getTickets(): List<Ticket> {
        return dao.getAll()
    }

    override fun getTicket(ticketId: Int): Ticket {
        return dao.getAll().find { it.id == ticketId }
            ?: throw IllegalArgumentException("Session not found.")
    }

    override fun addTicket(ticket: Ticket) {
        dao.add(ticket)
    }

    override fun deleteTicket(ticketId: Int) {
        val ticket = dao.getAll().find { it.id == ticketId } ?: throw IllegalArgumentException("Session not found.")
        dao.delete(ticket)
    }
}