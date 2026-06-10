package com.example.inversionestrack.data.model

enum class AlertType (val displayName: String){
    REBALANCE_EXCESS("Rebalance Exceso"),
    REBALANCE_DEFICIT("Rebalance Deficit"),
    GOAL_ACHIEVED("Objetivo Alcanzado"),
    GOAL_AT_RISK("Objetivo en Riesgo"),
    OTHER("Otro")

}
