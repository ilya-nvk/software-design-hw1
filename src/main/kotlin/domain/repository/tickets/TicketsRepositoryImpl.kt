package com.ilyanvk.domain.repository.tickets

import com.ilyanvk.data.dao.GenericDao
import com.ilyanvk.domain.model.Ticket

class TicketsRepositoryImpl(
    private val dao: GenericDao<Ticket>,
) : TicketsRepository {
    override fun getTickets(): List<Ticket> {
        return dao.getAll()
    }

    override fun addTicket(ticket: Ticket) {
        dao.add(ticket)
    }

    override fun deleteTicket(ticketId: Int) {
        val ticket = dao.getAll().find { it.id == ticketId } ?: throw IllegalArgumentException("Session not found.")
        dao.delete(ticket)
    }
}