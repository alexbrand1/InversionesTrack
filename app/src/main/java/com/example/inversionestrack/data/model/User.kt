package com.example.inversionestrack.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// User para autenticación
@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val email: String,
    val passwordHash: String,
    val createdAt: Long = System.currentTimeMillis()

)


