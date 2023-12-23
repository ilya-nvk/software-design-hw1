package com.ilyanvk.di.modules

import com.ilyanvk.cinema_feature.domain.cinema.Cinema
import com.ilyanvk.cinema_feature.domain.cinema.CinemaImpl
import com.ilyanvk.cinema_feature.domain.repository.movies.MoviesRepository
import com.ilyanvk.cinema_feature.domain.repository.sessions.SessionsRepository
import com.ilyanvk.cinema_feature.domain.repository.tickets.TicketsRepository
import dagger.Module
import dagger.Provides

@Module
class CinemaModule {
    @Provides
    fun provideCinema(
        moviesRepository: MoviesRepository,
        sessionsRepository: SessionsRepository,
        ticketsRepository: TicketsRepository,
    ): Cinema = CinemaImpl(
        moviesRepository, sessionsRepository, ticketsRepository
    )
}