package com.example.inversionestrack.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.inversionestrack.data.database.AppDatabase
import com.example.inversionestrack.data.model.UserProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.pow

class PIViewModel(application: Application) : AndroidViewModel(application) {

    private val userProfileDao = AppDatabase.getDatabase(application).userProfileDao()

    private val _userProfile = MutableStateFlow<UserProfile?>(null)
    val userProfile: StateFlow<UserProfile?> = _userProfile

    private val _piResult = MutableStateFlow<PIResult?>(null)
    val piResult: StateFlow<PIResult?> = _piResult

    fun loadProfile(userId: Long) {
        viewModelScope.launch {
            userProfileDao.getByUserId(userId).collect { profile ->
                _userProfile.value = profile
                profile?.let { calculatePI(it) }
            }
        }
    }

    private fun calculatePI(profile: UserProfile) {
        viewModelScope.launch(Dispatchers.Default) {

            // Rentabilidad real = rentabilidad esperada - inflación
            val realReturnPercent = profile.expectedReturn - profile.estimatedInflation
            val realReturnDecimal = realReturnPercent / 100.0

            // Ingresos anuales
            val annualIncome = profile.monthlyIncome * 12

            // Gastos anuales
            val annualExpenses = profile.monthlyExpenses * 12

            // Años en retiro = esperanza de vida - edad de retiro
            val yearsInRetirement = profile.lifeExpectancy - profile.retirementAge

            // Años para construir = edad retiro - edad actual
            val yearsToBuild = profile.retirementAge - profile.currentAge

            // PI = (ingresos anuales + gastos anuales) / rentabilidad real
            val PI = (annualIncome + annualExpenses) / realReturnDecimal

            // Aporte mensual con interés compuesto
            val monthlyRate = realReturnDecimal / 12.0
            val months = yearsToBuild * 12.0

            val monthlyContribution = if (monthlyRate > 0 && months > 0) {
                PI * monthlyRate / ((1 + monthlyRate).pow(months) - 1)

            } else {
                PI / months
            }

            _piResult.value = PIResult(
                pI = PI,
                annualIncome = annualIncome,
                annualExpenses = annualExpenses,
                realReturnPercent = realReturnPercent,
                yearsInRetirement = yearsInRetirement,
                yearsToBuild = yearsToBuild,
                monthlyContribution = monthlyContribution,
                riskProfile = profile.riskProfile.displayName
            )
        }
    }


    data class PIResult(
        val pI: Double,
        val annualIncome: Double,
        val annualExpenses: Double,
        val realReturnPercent: Double,
        val yearsInRetirement: Int,
        val yearsToBuild: Int,
        val monthlyContribution: Double,
        val riskProfile: String

    )


}