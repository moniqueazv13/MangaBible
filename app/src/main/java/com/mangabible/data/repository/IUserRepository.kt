package com.mangabible.data.repository

import com.mangabible.data.model.entity.User
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    suspend fun insertUser(user: User)
    fun getUserByUsername(username: String): Flow<User?>
}