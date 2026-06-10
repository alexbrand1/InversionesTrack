package com.example.inversionestrack.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.example.inversionestrack.ui.screens.LoginScreen
import com.example.inversionestrack.ui.screens.RegisterScreen
import com.example.inversionestrack.ui.screens.HomeScreen
import com.example.inversionestrack.ui.screens.OnboardingScreen
import com.example.inversionestrack.viewmodel.AuthViewModel
import com.example.inversionestrack.viewmodel.OnboardingViewModel
import com.example.inversionestrack.ui.screens.PIScreen
import com.example.inversionestrack.viewmodel.PIViewModel
@Composable
fun NavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    onboardingViewModel: OnboardingViewModel,
    piViewModel: PIViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        // Login
        composable(Screen.Login.route) {
            LoginScreen(
                viewModel = authViewModel,
                onLoginSuccess = { userId ->
                    // Verificar si ya se tiene perfil financiero
                    onboardingViewModel.hasProfile(userId) { hasProfile ->
                        if (hasProfile) {
                            navController.navigate(Screen.Home.route) {
                                popUpTo(Screen.Login.route) { inclusive = true }
                            }
                        } else {
                            navController.navigate(Screen.Onboarding.route) {
                                popUpTo(Screen.Login.route) { inclusive = true }
                            }
                        }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route)
                }
            )
        }

        // Registro
        composable(Screen.Register.route) {
            RegisterScreen(
                viewModel = authViewModel,
                onRegisterSuccess = { userId ->
                    navController.navigate(Screen.Onboarding.route) {
                        popUpTo(Screen.Register.route) { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.popBackStack()
                }

            )
        }

        // Onboarding,  datos financieros del usuario
        composable(Screen.Onboarding.route) {
            OnboardingScreen(
                viewModel = onboardingViewModel,
                authViewModel = authViewModel,
                onOnboardingComplete = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }


        //Home
        composable(Screen.Home.route) {
            HomeScreen(
                authViewModel = authViewModel,
                onNavigateToPI = {
                    navController.navigate(Screen.PI.route)
                },
                onNavigateToLayer = { layer ->
                    navController.navigate(layer)

                },
                onLogout = {
                    authViewModel.logout()
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.PI.route) {
            PIScreen(
                piViewModel = piViewModel,
                authViewModel = authViewModel,
                onNavigateBack = { navController.popBackStack() }
            )

        }
        /*

        //

        composable(Screen.Emergency.route) { ... }
        composable(Screen.Unexpected.route) { ... }
        composable(Screen.Retirement.route) { ... }
        composable(Screen.Bursatil.route) { ... }
        composable(Screen.OtherGoals.route) { ... }
        */
    }
}