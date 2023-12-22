package com.ilyanvk

import com.ilyanvk.di.AppComponent
import com.ilyanvk.di.DaggerAppComponent
import com.ilyanvk.domain.cinema.Cinema
import com.ilyanvk.domain.user.UserService
import com.ilyanvk.presentation.ConsoleUIImpl
import javax.inject.Inject


class Main {
    @Inject
    lateinit var cinema: Cinema

    @Inject
    lateinit var userService: UserService

    fun start() {
        val component: AppComponent = DaggerAppComponent.create()
        component.inject(this)

        val consoleUI = ConsoleUIImpl(cinema, userService)
        consoleUI.start()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Main().start()
        }
    }
}
