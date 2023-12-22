package com.ilyanvk.di.modules

import com.ilyanvk.data.dao.GenericDaoImpl
import com.ilyanvk.data.local_storage.LocalStorageManager
import com.ilyanvk.domain.model.Movie
import com.ilyanvk.domain.model.Session
import com.ilyanvk.domain.model.Ticket
import com.ilyanvk.domain.model.User
import com.ilyanvk.domain.repository.movies.MoviesRepository
import com.ilyanvk.domain.repository.movies.MoviesRepositoryImpl
import com.ilyanvk.domain.repository.sessions.SessionsRepository
import com.ilyanvk.domain.repository.sessions.SessionsRepositoryImpl
import com.ilyanvk.domain.repository.tickets.TicketsRepository
import com.ilyanvk.domain.repository.tickets.TicketsRepositoryImpl
import com.ilyanvk.domain.repository.users.UsersRepository
import com.ilyanvk.domain.repository.users.UsersRepositoryImpl
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