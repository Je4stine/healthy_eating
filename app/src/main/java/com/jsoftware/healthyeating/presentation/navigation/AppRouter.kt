package com.jsoftware.healthyeating.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface AppRouter: NavKey {


    @Serializable  data object Home: AppRouter, NavKey
    @Serializable data object Add: AppRouter, NavKey
    @Serializable data object Analytics: AppRouter, NavKey
    @Serializable data object Account: AppRouter, NavKey

}