package com.example.inversionestrack.data.model


// Vehículos Financieros
enum class VehicleType(val displayName: String) {
    FIC("Fondo de Inversión Colectiva"),
    FVP("Fondo Voluntario de Pensión"),
    CDT("Certificado de Depósito a Término"),
    BROKER("Broker / Bolsa de valores"),
    SAVINGS_ACCOUNT("Cuenta de alta rentabilidad"),
    INSURANCE("Seguro privado de acumulación"),
    OTHER("Otro")

}