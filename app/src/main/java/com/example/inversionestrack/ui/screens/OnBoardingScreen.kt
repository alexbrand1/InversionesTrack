package com.example.inversionestrack.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inversionestrack.data.model.RiskProfile
import com.example.inversionestrack.viewmodel.AuthViewModel
import com.example.inversionestrack.viewmodel.OnboardingViewModel

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel,
    authViewModel: AuthViewModel,
    onOnboardingComplete: () -> Unit
) {
    val currentUser by authViewModel.currentUser.collectAsState()
    val saveState by viewModel.saveState.collectAsState()

    var monthlyIncome by remember { mutableStateOf("") }
    var monthlyExpenses by remember { mutableStateOf("") }
    var currentAge by remember { mutableStateOf("") }
    var retirementAge by remember { mutableStateOf("") }
    var lifeExpectancy by remember { mutableStateOf("") }
    var estimatedInflation by remember { mutableStateOf("") }
    var expectedReturn by remember { mutableStateOf("") }
    var selectedRiskProfile by remember { mutableStateOf(RiskProfile.MODERATE) }

    LaunchedEffect(saveState) {
        if (saveState is OnboardingViewModel.SaveState.Success) {
            onOnboardingComplete()
            viewModel.resetState()
        }
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Logo
            Icon(
                imageVector = Icons.Default.AccountBalanceWallet,
                contentDescription = null,
                modifier = Modifier.size(56.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Nombre app
            Text(
                text = "InversionesTrack",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Construye tu independencia financiera",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Título pantalla
            Text(
                text = "Tu perfil financiero",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Estos datos nos permiten calcular tu punto de independencia",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))


            // Campos financieros
            OutlinedTextField(
                value = monthlyIncome,
                onValueChange = { monthlyIncome = it },
                label = { Text("Ingreso mensual (\$)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = monthlyExpenses,
                onValueChange = { monthlyExpenses = it },
                label = { Text("Gastos mensuales (\$)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = currentAge,
                onValueChange = { currentAge = it },
                label = { Text("Edad actual") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = retirementAge,
                onValueChange = { retirementAge = it },
                label = { Text("Edad de retiro deseada") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = lifeExpectancy,
                onValueChange = { lifeExpectancy = it },
                label = { Text("Esperanza de vida") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = estimatedInflation,
                onValueChange = { estimatedInflation = it },
                label = { Text("Inflación estimada (%)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = expectedReturn,
                onValueChange = { expectedReturn = it },
                label = { Text("Rentabilidad esperada (%)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Perfil de riesgo
            Text(
                text = "Perfil de riesgo",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Conservador
                Button(
                    onClick = { selectedRiskProfile = RiskProfile.CONSERVATIVE },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedRiskProfile == RiskProfile.CONSERVATIVE)
                            Color(0xFF2196F3) else Color(0xFF2196F3).copy(alpha = 0.3f),
                        contentColor = Color.White
                    )
                ) {
                    Text(RiskProfile.CONSERVATIVE.displayName, fontSize = 10.sp)
                }

                // Moderado
                Button(
                    onClick = { selectedRiskProfile = RiskProfile.MODERATE },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedRiskProfile == RiskProfile.MODERATE)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                        contentColor = Color.White
                    )
                ) {
                    Text(RiskProfile.MODERATE.displayName, fontSize = 12.sp)
                }


                // Agresivo
                Button(
                    onClick = { selectedRiskProfile = RiskProfile.AGGRESSIVE },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedRiskProfile == RiskProfile.AGGRESSIVE)
                            Color(0xFFF44336) else Color(0xFFF44336).copy(alpha = 0.3f),
                        contentColor = Color.White
                    )
                ) {
                    Text(RiskProfile.AGGRESSIVE.displayName, fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (saveState is OnboardingViewModel.SaveState.Error) {
                Text(
                    text = (saveState as OnboardingViewModel.SaveState.Error).message,
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 13.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    currentUser?.let { user ->
                        viewModel.saveUserProfile(
                            userId = user.id,
                            monthlyIncome = monthlyIncome.toDoubleOrNull() ?: 0.0,
                            monthlyExpenses = monthlyExpenses.toDoubleOrNull() ?: 0.0,
                            currentAge = currentAge.toIntOrNull() ?: 0,
                            retirementAge = retirementAge.toIntOrNull() ?: 0,
                            lifeExpectancy = lifeExpectancy.toIntOrNull() ?: 0,
                            estimatedInflation = estimatedInflation.toDoubleOrNull() ?: 0.0,
                            expectedReturn = expectedReturn.toDoubleOrNull() ?: 0.0,
                            riskProfile = selectedRiskProfile
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = saveState !is OnboardingViewModel.SaveState.Loading
            ) {
                if (saveState is OnboardingViewModel.SaveState.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Text("Guardar y continuar")

                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}