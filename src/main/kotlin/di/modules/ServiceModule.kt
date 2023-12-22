package com.ilyanvk.di.modules

import com.ilyanvk.domain.cinema.Cinema
import com.ilyanvk.domain.cinema.CinemaImpl
import com.ilyanvk.domain.repository.movies.MoviesRepository
import com.ilyanvk.domain.repository.sessions.SessionsRepository
import com.ilyanvk.domain.repository.tickets.TicketsRepository
import com.ilyanvk.domain.repository.users.UsersRepository
import com.ilyanvk.domain.user.UserService
import com.ilyanvk.domain.user.UserServiceImpl
import com.ilyanvk.presentation.ConsoleUI
import com.ilyanvk.presentation.ConsoleUIImpl
import dagger.Module
import dagger.Provides

@Module
class ServiceModule {
    @Provides
    fun provideUserService(usersRepository: UsersRepository): UserService = UserServiceImpl(usersRepository)

    @Provides
    fun provideConsoleUI(cinema: Cinema, userService: UserService): ConsoleUI = ConsoleUIImpl(cinema, userService)

    @Provides
    fun provideCinema(
        moviesRepository: MoviesRepository,
        sessionsRepository: SessionsRepository,
        ticketsRepository: TicketsRepository,
    ): Cinema = CinemaImpl(
        moviesRepository, sessionsRepository, ticketsRepository
    )
}