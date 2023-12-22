package com.ilyanvk.domain.model

data class User(
    val login: String,
    val passwordHash: Int,
)
