package com.example.inversionestrack.data.database

import androidx.room.TypeConverter
import com.example.inversionestrack.data.model.InvestmentLayer
import com.example.inversionestrack.data.model.LiquidityLevel
import com.example.inversionestrack.data.model.RiskProfile
import com.example.inversionestrack.data.model.VehicleType
// import java.util.Date
class Converters {

    @TypeConverter
    fun fromRiskProfile(value: RiskProfile): String {
        return value.name
    }

    @TypeConverter
    fun toRiskProfile(value: String): RiskProfile {
        return RiskProfile.valueOf(value)
    }

    @TypeConverter
    fun fromInvestmentLayer(value: InvestmentLayer): String {
        return value.name
    }

    @TypeConverter
    fun toInvestmentLayer(value: String): InvestmentLayer {
        return InvestmentLayer.valueOf(value)
    }

    @TypeConverter
    fun fromVehicleType(value: VehicleType): String {
        return value.name
    }

    @TypeConverter
    fun toVehicleType(value: String): VehicleType {
        return VehicleType.valueOf(value)
    }

    @TypeConverter
    fun fromLiquidityLevel(value: LiquidityLevel): String {
        return value.name
    }

    @TypeConverter
    fun toLiquidityLevel(value: String): LiquidityLevel {
        return LiquidityLevel.valueOf(value)
    }
}