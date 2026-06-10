package com.example.inversionestrack.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "investment_accounts")
data class InvestmentAccount(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Long, // FK → User.id
    val name: String,
    val entity: String,
    val amount: Double,
    val layer: InvestmentLayer,
    val vehicleType: VehicleType,
    val liquidity: LiquidityLevel,
    val termMonths: Int? = null
)

