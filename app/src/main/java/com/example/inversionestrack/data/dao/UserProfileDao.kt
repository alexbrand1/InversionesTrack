package com.example.inversionestrack.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.inversionestrack.data.model.UserProfile
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProfileDao {

    // Crea perfil financiero al completar onboarding
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userProfile: UserProfile): Long

    // Actualiza cuando el usuario cambia sus datos financieros
    @Update
    suspend fun update(userProfile: UserProfile)

    // Obtiene el perfil del usuario activo para mostrar en la pantalla principal
    @Query("SELECT * FROM user_profile WHERE userId = :userId LIMIT 1")
    fun getByUserId(userId: Long): Flow<UserProfile?>

    // Verifica si ya completó el onboarding
    @Query("SELECT EXISTS(SELECT 1 FROM user_profile WHERE userId = :userId)")
    suspend fun hasProfile(userId: Long): Boolean

    @Delete
    suspend fun delete(userProfile: UserProfile)
}