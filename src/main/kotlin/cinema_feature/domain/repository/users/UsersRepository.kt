package com.ilyanvk.cinema_feature.domain.repository.users

import com.ilyanvk.cinema_feature.domain.model.User

/**
 * Interface for a Users Repository.
 * Contains methods for managing users.
 */
interface UsersRepository {
    /**
     * Retrieves all users.
     * @return List of users.
     */
    fun getUsers(): List<User>

    /**
     * Adds a user.
     * @param user The user to be added.
     */
    fun addUser(user: User)

    /**
     * Deletes a user.
     * @param user The user to be deleted.
     */
    fun deleteUser(user: User)
}
