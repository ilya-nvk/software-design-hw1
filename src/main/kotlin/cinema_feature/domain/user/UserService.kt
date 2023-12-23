package com.ilyanvk.cinema_feature.domain.user

import com.ilyanvk.cinema_feature.domain.model.User

/**
 * Interface for a User Service.
 * Contains methods for managing user authentication and account management.
 */
interface UserService {
    /**
     * The currently logged in user.
     * Null if no user is logged in.
     */
    val currentUser: User?

    /**
     * Registers a new user with the given login and password.
     * @param login The login of the new user.
     * @param password The password of the new user.
     */
    fun register(login: String, password: String)

    /**
     * Logs in a user with the given login and password.
     * @param login The login of the user.
     * @param password The password of the user.
     */
    fun logIn(login: String, password: String)

    /**
     * Logs out the currently logged in user.
     */
    fun logOut()

    /**
     * Deletes the account of the currently logged in user.
     */
    fun deleteAccount()

    /**
     * Changes the password of the currently logged in user.
     * @param oldPassword The old password of the user.
     * @param newPassword The new password of the user.
     */
    fun changePassword(oldPassword: String, newPassword: String)
}
