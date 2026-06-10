package com.example.inversionestrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.inversionestrack.ui.navigation.NavGraph
import com.example.inversionestrack.ui.navigation.Screen
import com.example.inversionestrack.ui.theme.InversionesTrackTheme
import com.example.inversionestrack.viewmodel.AuthViewModel
import com.example.inversionestrack.viewmodel.OnboardingViewModel
import com.example.inversionestrack.viewmodel.PIViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InversionesTrackTheme {
                Surface(

                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val authViewModel: AuthViewModel = viewModel()
                    val onboardingViewModel: OnboardingViewModel = viewModel()
                    val piViewModel: PIViewModel = viewModel()
                    val currentUser by authViewModel.currentUser.collectAsState()

                    // Si el usuario cierra sesión, volver al login
                    LaunchedEffect(currentUser) {
                        if (currentUser == null) {
                            navController.navigate(Screen.Login.route) {
                                popUpTo(0) { inclusive = true }
                            }
                        }

                    }



                    NavGraph(
                        navController = navController,
                        authViewModel = authViewModel,
                        onboardingViewModel = onboardingViewModel,
                        piViewModel = piViewModel,
                    )
                }
            }
        }
    }
}