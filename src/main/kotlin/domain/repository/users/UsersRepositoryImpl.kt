package com.ilyanvk.domain.repository.users

import com.ilyanvk.data.dao.GenericDao
import com.ilyanvk.domain.model.User

class UsersRepositoryImpl(
    private val dao: GenericDao<User>,
) : UsersRepository {
    override fun getUsers(): List<User> {
        return dao.getAll()
    }

    override fun addUser(user: User) {
        dao.add(user)
    }

    override fun deleteUser(user: User) {
        dao.delete(user)
    }
}
