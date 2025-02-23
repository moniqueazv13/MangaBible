package com.mangabible.data.repository

import com.mangabible.data.dao.UserDao
import com.mangabible.data.model.entity.User
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(private val userDao: UserDao): IUserRepository {
    override suspend fun insertUser(user: User) {
        userDao.insert(user)
    }

    override fun getUserByUsername(username: String): Flow<User?> {
        return userDao.getUserByUsername(username)
    }
}