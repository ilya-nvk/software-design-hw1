package com.ilyanvk.di.modules

import com.ilyanvk.cinema_feature.domain.repository.users.UsersRepository
import com.ilyanvk.cinema_feature.domain.user.UserService
import com.ilyanvk.cinema_feature.domain.user.UserServiceImpl
import dagger.Module
import dagger.Provides

@Module
class ServiceModule {
    @Provides
    fun provideUserService(usersRepository: UsersRepository): UserService = UserServiceImpl(usersRepository)
}