package com.ilyanvk.domain.repository.tickets

import com.ilyanvk.domain.model.Ticket

/**
 * Interface for a Tickets Repository.
 * Contains methods for managing tickets.
 */
interface TicketsRepository {
    /**
     * Retrieves all tickets.
     * @return List of tickets.
     */
    fun getTickets(): List<Ticket>

    /**
     * Adds a ticket.
     * @param ticket The ticket to be added.
     */
    fun addTicket(ticket: Ticket)

    /**
     * Deletes a ticket.
     * @param ticketId ID of the ticket to be deleted.
     */
    fun deleteTicket(ticketId: Int)
}
