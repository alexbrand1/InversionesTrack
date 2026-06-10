package com.example.inversionestrack.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inversionestrack.data.model.InvestmentLayer
import com.example.inversionestrack.ui.navigation.Screen
import com.example.inversionestrack.viewmodel.AuthViewModel

data class LayerItem(
    val layer: InvestmentLayer,
    val route: String,
    val description: String,
    val icon: ImageVector,
    val color: Color
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    authViewModel: AuthViewModel,
    onNavigateToPI: () -> Unit,
    onNavigateToLayer: (String) -> Unit,
    onLogout: () -> Unit
) {
    val currentUser by authViewModel.currentUser.collectAsState()

    val layers = listOf(
        LayerItem(InvestmentLayer.EMERGENCY,   Screen.Emergency.route,  "% Progreso", Icons.Default.Security,    Color(0xFF4CAF50)),
        LayerItem(InvestmentLayer.UNEXPECTED,  Screen.Unexpected.route, "% Progreso", Icons.Default.Warning,     Color(0xFFFF9800)),
        LayerItem(InvestmentLayer.RETIREMENT,  Screen.Retirement.route, "% Progreso", Icons.Default.Savings,     Color(0xFF2196F3)),
        LayerItem(InvestmentLayer.BURSATIL,    Screen.Bursatil.route,   "% Progreso",Icons.AutoMirrored.Filled.TrendingUp,  Color(0xFF9C27B0)),
        LayerItem(InvestmentLayer.OTHER_GOALS, Screen.OtherGoals.route, "% Progreso", Icons.Default.Flag,        Color(0xFFE91E63))
    )

    Scaffold(
        topBar = {
            // Header verde con logo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountBalanceWallet,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(36.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "INVERSIONES",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "T R A C K",
                            fontSize = 11.sp,
                            letterSpacing = 4.sp,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }
                }
                IconButton(
                    onClick = onLogout,
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Logout,
                        contentDescription = "Cerrar sesión",
                        tint = Color.White

                    )
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF5F0E8)),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // Tarjeta bienvenida
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Bienvenido ${currentUser?.name ?: ""}",
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Estado de tus operaciones",
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )


                        Spacer(modifier = Modifier.height(12.dp))

                        // Badge Niveles
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .background(
                                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "NIVELES",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }


                        Spacer(modifier = Modifier.height(16.dp))

                        // Botón calcular PI
                        Button(
                            onClick = onNavigateToPI,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Calcular Pi")
                        }
                    }
                }
            }


            // Cards de niveles
            items(layers) { item ->
                Card(
                    onClick = { onNavigateToLayer(item.route) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = item.color.copy(alpha = 0.15f)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Ícono
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .background(
                                    color = item.color.copy(alpha = 0.15f),
                                    shape = RoundedCornerShape(10.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = null,
                                tint = item.color,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = item.layer.displayName,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = item.description,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                            )
                        }

                        Icon(
                            imageVector = Icons.Default.ChevronRight,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                        )
                    }
                }
            }

            // Botones inferiores
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = { },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color(0xFFA8D5BA),  // ← VERDE SUAVE fondo
                            contentColor = Color(0xFF2E7D32)      // Texto verde oscuro
                        ),
                    ) {
                        Text("Ver más")
                    }
                    Button(
                        onClick = { },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Nuevo nivel")
                    }
                }
            }
        }
    }
}