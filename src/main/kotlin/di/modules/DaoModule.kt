package com.ilyanvk.di.modules

import com.ilyanvk.data.dao.GenericDao
import com.ilyanvk.data.dao.GenericDaoImpl
import com.ilyanvk.data.local_storage.LocalStorageManager
import com.ilyanvk.domain.model.Movie
import com.ilyanvk.domain.model.Session
import com.ilyanvk.domain.model.Ticket
import com.ilyanvk.domain.model.User
import dagger.Module
import dagger.Provides

@Module
class DaoModule {
    @Provides
    fun provideMoviesDao(localStorageManager: LocalStorageManager<List<Movie>>): GenericDao<Movie> =
        GenericDaoImpl(localStorageManager)

    @Provides
    fun provideSessionsDao(localStorageManager: LocalStorageManager<List<Session>>): GenericDao<Session> =
        GenericDaoImpl(localStorageManager)

    @Provides
    fun provideTicketsDao(localStorageManager: LocalStorageManager<List<Ticket>>): GenericDao<Ticket> =
        GenericDaoImpl(localStorageManager)

    @Provides
    fun provideUsersDao(localStorageManager: LocalStorageManager<List<User>>): GenericDao<User> =
        GenericDaoImpl(localStorageManager)
}
