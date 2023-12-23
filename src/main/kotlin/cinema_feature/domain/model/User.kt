package com.ilyanvk.cinema_feature.domain.model

data class User(
    val login: String,
    val passwordHash: Int,
)
