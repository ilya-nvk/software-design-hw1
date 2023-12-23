package com.ilyanvk

import com.ilyanvk.cinema_feature.presentation.console_ui.ConsoleUI
import com.ilyanvk.di.AppComponent
import com.ilyanvk.di.DaggerAppComponent
import javax.inject.Inject

class Main {

    @Inject
    lateinit var consoleUI: ConsoleUI  // Injected by Dagger


    fun start() {
        val component: AppComponent = DaggerAppComponent.create()  // Create Dagger component
        component.inject(this)  // Inject dependencies into this Main instance
        consoleUI.start()
    }
}

fun main() {
    Main().start()
}
