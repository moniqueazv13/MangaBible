package com.mangabible.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mangabible.data.model.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM users WHERE username = :username")
    fun getUserByUsername(username: String): Flow<User?>
}