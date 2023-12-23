package com.ilyanvk.di.modules

import com.ilyanvk.cinema_feature.data.dao.GenericDaoImpl
import com.ilyanvk.cinema_feature.data.local_storage.LocalStorageManager
import com.ilyanvk.cinema_feature.domain.model.Movie
import com.ilyanvk.cinema_feature.domain.model.Session
import com.ilyanvk.cinema_feature.domain.model.Ticket
import com.ilyanvk.cinema_feature.domain.model.User
import com.ilyanvk.cinema_feature.domain.repository.movies.MoviesRepository
import com.ilyanvk.cinema_feature.domain.repository.movies.MoviesRepositoryImpl
import com.ilyanvk.cinema_feature.domain.repository.sessions.SessionsRepository
import com.ilyanvk.cinema_feature.domain.repository.sessions.SessionsRepositoryImpl
import com.ilyanvk.cinema_feature.domain.repository.tickets.TicketsRepository
import com.ilyanvk.cinema_feature.domain.repository.tickets.TicketsRepositoryImpl
import com.ilyanvk.cinema_feature.domain.repository.users.UsersRepository
import com.ilyanvk.cinema_feature.domain.repository.users.UsersRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideMoviesRepository(moviesStorage: LocalStorageManager<List<Movie>>): MoviesRepository =
        MoviesRepositoryImpl(GenericDaoImpl(moviesStorage))

    @Provides
    fun provideSessionsRepository(sessionsStorage: LocalStorageManager<List<Session>>): SessionsRepository =
        SessionsRepositoryImpl(GenericDaoImpl(sessionsStorage))

    @Provides
    fun provideTicketsRepository(ticketsStorage: LocalStorageManager<List<Ticket>>): TicketsRepository =
        TicketsRepositoryImpl(GenericDaoImpl(ticketsStorage))

    @Provides
    fun provideUsersRepository(usersStorage: LocalStorageManager<List<User>>): UsersRepository =
        UsersRepositoryImpl(GenericDaoImpl(usersStorage))
}