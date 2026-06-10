package com.example.inversionestrack.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inversionestrack.viewmodel.AuthViewModel
import com.example.inversionestrack.viewmodel.PIViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PIScreen(
    piViewModel: PIViewModel,
    authViewModel: AuthViewModel,
    onNavigateBack: () -> Unit
) {
    val currentUser by authViewModel.currentUser.collectAsState()
    val piResult by piViewModel.piResult.collectAsState()

    LaunchedEffect(currentUser) {
        currentUser?.let { piViewModel.loadProfile(it.id) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("PI") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (piResult == null) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                piResult?.let { result ->

                    // Tarjeta principal  Punto de independencia o PI
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Tu Punto de Independencia",
                                fontSize = 14.sp,
                                color = Color.White.copy(alpha = 0.8f)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "$ ${"%,.0f".format(result.pI)}",
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Capital necesario para tu independencia",
                                fontSize = 12.sp,
                                color = Color.White.copy(alpha = 0.7f)
                            )
                        }
                    }

                    // Tarjeta aporte mensual
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Text(
                                text = "Aporte mensual sugerido",
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "$ ${"%,.0f".format(result.monthlyContribution)}",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Para alcanzar tu meta en ${result.yearsToBuild} años",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                            )
                        }
                    }

                    // Tarjeta detalles
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)
                    ) {

                        Column(
                            modifier = Modifier.padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = "Detalles del cálculo",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            PIDetailRow(
                                label = "Ingresos anuales",
                                value = "$ ${"%,.0f".format(result.annualIncome)}"
                            )
                            PIDetailRow(
                                label = "Gastos anuales",
                                value = "$ ${"%,.0f".format(result.annualExpenses)}"
                            )
                            PIDetailRow(
                                label = "Rentabilidad real",
                                value = "${"%.1f".format(result.realReturnPercent)}%"
                            )
                            PIDetailRow(
                                label = "Años construyendo",
                                value = "${result.yearsToBuild} años"
                            )
                            PIDetailRow(
                                label = "Años en retiro",
                                value = "${result.yearsInRetirement} años"
                            )
                            PIDetailRow(
                                label = "Perfil de riesgo",
                                value = result.riskProfile
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun PIDetailRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}