package com.ilyanvk.cinema_feature.domain.model

data class Session(
    val id: Int,
    val movieId: Int,
    val time: Long,
    val bookedSeats: Set<Int> = emptySet(),
)
