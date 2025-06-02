package com.example.newsaggregator.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.OptIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsaggregator.ui.theme.NewsAggregatorTheme
import com.example.newsaggregator.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: NewsViewModel by viewModels()

    @OptIn(UnstableApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate: Starting the application")
        setContent {
            NewsAggregatorTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "newsList"
                    ) {
                        composable("newsList") {
                            NewsListScreen(viewModel = viewModel, navController = navController)
                        }
                        composable(
                            "newsDetail/{guid}",
                            arguments = listOf(navArgument("guid") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val guid = backStackEntry.arguments?.getString("guid")
                            NewsDetailScreen(guidUrl = guid)
                        }
                    }
                }
            }
        }
        viewModel.fetchNews()
    }
}
