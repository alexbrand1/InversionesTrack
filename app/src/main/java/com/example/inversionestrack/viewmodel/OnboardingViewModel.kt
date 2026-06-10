package com.example.inversionestrack.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.inversionestrack.data.database.AppDatabase
import com.example.inversionestrack.data.model.UserProfile
import com.example.inversionestrack.data.model.RiskProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class OnboardingViewModel(application: Application) : AndroidViewModel(application) {

    private val userProfileDao = AppDatabase.getDatabase(application).userProfileDao()

    private val _saveState = MutableStateFlow<SaveState>(SaveState.Idle)
    val saveState: StateFlow<SaveState> = _saveState

    // Función que la UI llama cuando el usuario completa el formulario de onboarding.
    fun saveUserProfile(
        userId: Long,
        monthlyIncome: Double,
        monthlyExpenses: Double,
        currentAge: Int,
        retirementAge: Int,
        lifeExpectancy: Int,
        estimatedInflation: Double,
        expectedReturn: Double,
        riskProfile: RiskProfile
    ) {
        viewModelScope.launch {
            _saveState.value = SaveState.Loading
            try {
                val profile = UserProfile(
                    userId = userId,
                    monthlyIncome = monthlyIncome,
                    monthlyExpenses = monthlyExpenses,
                    currentAge = currentAge,
                    retirementAge = retirementAge,
                    lifeExpectancy = lifeExpectancy,
                    estimatedInflation = estimatedInflation,
                    expectedReturn = expectedReturn,
                    riskProfile = riskProfile
                )
                userProfileDao.insert(profile)
                _saveState.value = SaveState.Success
            } catch (e: Exception) {
                _saveState.value = SaveState.Error("Error al guardar perfil: ${e.message}")
            }
        }
    }


    //Verifica si un usuario ya tiene perfil financiero.
    fun hasProfile(userId: Long, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            onResult(userProfileDao.hasProfile(userId))
        }
    }

    fun resetState() {
        _saveState.value = SaveState.Idle
    }

    //Clase sellada con 4 estados posibles para modelar los estados de autenticación
    sealed class SaveState {
        object Idle : SaveState()
        object Loading : SaveState()
        object Success : SaveState()
        data class Error(val message: String) : SaveState()
    }
}