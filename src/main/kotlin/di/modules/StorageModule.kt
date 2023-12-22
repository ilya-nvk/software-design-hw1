package com.ilyanvk.di.modules

import com.google.gson.reflect.TypeToken
import com.ilyanvk.data.local_storage.LocalStorageManager
import com.ilyanvk.data.local_storage.LocalStorageManagerImpl
import com.ilyanvk.domain.model.Movie
import com.ilyanvk.domain.model.Session
import com.ilyanvk.domain.model.Ticket
import com.ilyanvk.domain.model.User
import dagger.Module
import dagger.Provides

@Module
class StorageModule {
    @Provides
    fun provideMoviesStorage(): LocalStorageManager<List<Movie>> =
        LocalStorageManagerImpl("movies.json", object : TypeToken<List<Movie>>() {})

    @Provides
    fun provideSessionsStorage(): LocalStorageManager<List<Session>> =
        LocalStorageManagerImpl("sessions.json", object : TypeToken<List<Session>>() {})

    @Provides
    fun provideTicketsStorage(): LocalStorageManager<List<Ticket>> =
        LocalStorageManagerImpl("tickets.json", object : TypeToken<List<Ticket>>() {})

    @Provides
    fun provideUsersStorage(): LocalStorageManager<List<User>> =
        LocalStorageManagerImpl("users.json", object : TypeToken<List<User>>() {})
}
