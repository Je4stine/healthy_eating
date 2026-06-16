package com.jsoftware.healthyeating

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jsoftware.healthyeating.presentation.navigation.AppGraph
import com.jsoftware.healthyeating.ui.theme.HealthyEatingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HealthyEatingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    AppGraph(modifier = Modifier.padding(padding))
                }
            }
        }
    }
}