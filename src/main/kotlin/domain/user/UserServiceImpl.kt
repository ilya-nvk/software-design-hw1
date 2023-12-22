package com.ilyanvk.domain.user

import com.ilyanvk.domain.model.User
import com.ilyanvk.domain.repository.users.UsersRepository

class UserServiceImpl(
    private val usersRepository: UsersRepository,
) : UserService {
    private var _currentUser: User? = null
    override val currentUser: User?
        get() = _currentUser

    override fun register(login: String, password: String) {
        if (usersRepository.getUsers().any { it.login == login }) {
            throw IllegalArgumentException("Login is already taken.")
        }
        val user = User(login, password.hashCode())
        usersRepository.addUser(user)
        _currentUser = user
    }

    override fun logIn(login: String, password: String) {
        val user = usersRepository.getUsers().find { it.login == login }
            ?: throw IllegalArgumentException("No such login found.")

        require(password.hashCode() == user.passwordHash) { "Wrong password." }
        _currentUser = user
    }

    override fun logOut() {
        requireNotNull(_currentUser) { "No user is currently logged in." }
        _currentUser = null
    }

    override fun deleteAccount() {
        requireNotNull(_currentUser) { "No user is currently logged in." }
        _currentUser?.let { usersRepository.deleteUser(it) }
        logOut()
    }

    override fun changePassword(oldPassword: String, newPassword: String) {
        _currentUser?.let {
            require(oldPassword.hashCode() == it.passwordHash) { "Wrong password." }
            usersRepository.deleteUser(it)
            val user = it.copy(passwordHash = newPassword.hashCode())
            usersRepository.addUser(user)
            _currentUser = user
        } ?: throw IllegalArgumentException("No user is currently logged in.")
    }
}
