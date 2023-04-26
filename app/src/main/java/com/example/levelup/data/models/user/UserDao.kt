package com.example.levelup.data.models.user

import androidx.room.*

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("Select * FROM users LIMIT 1")
    suspend fun getUser(): User?
}
