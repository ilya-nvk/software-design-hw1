package com.ilyanvk.domain.model

data class Session(
    val id: Int,
    val movieId: Int,
    val time: Long,
    val bookedSeats: Set<Int> = emptySet(),
)
