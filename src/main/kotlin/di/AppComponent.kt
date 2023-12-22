package com.ilyanvk.di

import com.ilyanvk.Main
import com.ilyanvk.di.modules.DaoModule
import com.ilyanvk.di.modules.RepositoryModule
import com.ilyanvk.di.modules.ServiceModule
import com.ilyanvk.di.modules.StorageModule
import dagger.Component

@Component(
    modules = [StorageModule::class, RepositoryModule::class, ServiceModule::class, DaoModule::class]
)
interface AppComponent {
    fun inject(main: Main)
}
