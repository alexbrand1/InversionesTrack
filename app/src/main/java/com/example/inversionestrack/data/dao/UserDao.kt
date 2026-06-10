package com.example.inversionestrack.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.inversionestrack.data.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    // Para registro : devuelve id generado, útil para crear el UserProfile inmediatamente
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long

    // Para Login : busca por email para verificar contraseña
    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?

    // Sesión activa busca por id para restaurar sesión al abrir la app
    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUserById(userId: Long): Flow<User?>

    // Verifica si el email ya está registrado  para evitar duplicados
    @Query("SELECT EXISTS(SELECT 1 FROM users WHERE email = :email)")
    suspend fun emailExists(email: String): Boolean

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)
}