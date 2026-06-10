package com.example.inversionestrack.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "savings_projects")
data class SavingsProject(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Long,          // FK → User.id
    val name: String,
    val targetAmount: Double,
    val currentAmount: Double = 0.0,
    val deadlineDate: Long,
    val monthlyContribution: Double = 0.0
)