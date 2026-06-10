package com.example.inversionestrack.ui.navigation


sealed class Screen(val route: String) {

    object Login : Screen(route = "login")
    object Register : Screen(route = "register")
    object Onboarding : Screen(route = "onboarding")
    object Home : Screen(route = "home")
    object PI : Screen(route = "pi")
    object Emergency : Screen(route = "emergency")
    object Unexpected : Screen(route = "unexpected")
    object Retirement : Screen(route = "retirement")
    object Bursatil : Screen(route = "bursatil")
    object OtherGoals : Screen(route = "other_goals")
}