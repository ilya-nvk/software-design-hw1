package com.ilyanvk.di.modules

import com.ilyanvk.cinema_feature.domain.cinema.Cinema
import com.ilyanvk.cinema_feature.domain.user.UserService
import com.ilyanvk.cinema_feature.presentation.console_ui.ConsoleUI
import com.ilyanvk.cinema_feature.presentation.console_ui.ConsoleUIImpl
import com.ilyanvk.cinema_feature.presentation.input_reader.InputReader
import com.ilyanvk.cinema_feature.presentation.input_reader.InputReaderImpl
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {
    @Provides
    fun provideInputReader(): InputReader = InputReaderImpl()

    @Provides
    fun provideConsoleUI(cinema: Cinema, userService: UserService, inputReader: InputReader): ConsoleUI =
        ConsoleUIImpl(cinema, userService, inputReader)
}

