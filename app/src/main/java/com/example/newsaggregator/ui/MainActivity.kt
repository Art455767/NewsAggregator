package com.example.newsaggregator.ui

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.OptIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsaggregator.ui.theme.NewsAggregatorTheme
import com.example.newsaggregator.viewModel.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: ViewModel by viewModels()

    @OptIn(UnstableApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                            "newsDetail/{link}",
                            arguments = listOf(navArgument("link") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val link = backStackEntry.arguments?.getString("link")?.let { Uri.decode(it) }
                            NewsDetailScreen(linkUrl = link)
                        }
                    }
                }
            }
        }
        viewModel.fetchNews()
    }
}
