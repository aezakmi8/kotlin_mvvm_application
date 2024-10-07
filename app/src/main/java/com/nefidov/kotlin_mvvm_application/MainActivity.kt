package com.nefidov.kotlin_mvvm_application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.nefidov.kotlin_mvvm_application.common.di.initKoin
import com.nefidov.kotlin_mvvm_application.router.NavGraph
import com.nefidov.kotlin_mvvm_application.ui.theme.Kotlin_mvvm_applicationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            Kotlin_mvvm_applicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavGraph(
                        navController,
                        innerPadding = innerPadding
                    )
                }
            }
        }
    }
}
