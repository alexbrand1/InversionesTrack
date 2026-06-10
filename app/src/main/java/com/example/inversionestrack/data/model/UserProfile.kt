package com.example.inversionestrack.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


// Datos financieros
@Entity(tableName = "user_profile")
data class UserProfile(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Long, // FK → User.id
    val monthlyIncome: Double,
    val monthlyExpenses: Double,
    val currentAge: Int,
    val retirementAge: Int,
    val lifeExpectancy: Int,
    val estimatedInflation: Double,
    val expectedReturn: Double,
    val riskProfile: RiskProfile
)