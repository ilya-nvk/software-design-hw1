package com.ilyanvk.cinema_feature.data.dao

import com.ilyanvk.cinema_feature.data.local_storage.LocalStorageManager

class GenericDaoImpl<T>(
    private val localStorageManager: LocalStorageManager<List<T>>,
) : GenericDao<T> {
    override fun getAll(): List<T> {
        return localStorageManager.readFromFile() ?: emptyList()
    }

    override fun add(item: T) {
        try {
            localStorageManager.writeToFile((localStorageManager.readFromFile() ?: emptyList()) + item)
        } catch (e: Exception) {
            localStorageManager.writeToFile(listOf(item))
        }
    }

    override fun delete(item: T) {
        try {
            localStorageManager.writeToFile((localStorageManager.readFromFile() ?: emptyList()) - item)
        } catch (e: Exception) {
            localStorageManager.writeToFile(emptyList())
        }
    }
}