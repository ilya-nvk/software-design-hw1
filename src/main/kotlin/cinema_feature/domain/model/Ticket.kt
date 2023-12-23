package com.ilyanvk.cinema_feature.domain.model

data class Ticket(
    val id: Int,
    val sessionId: Int,
    val seat: Int,
)
