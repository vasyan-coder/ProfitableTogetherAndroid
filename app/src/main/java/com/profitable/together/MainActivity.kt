package com.profitable.together

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.course.profit.data.SessionManager
import com.course.profit.navigation.navigation.BottomBar
import com.course.profit.navigation.navigation.Screen
import com.course.profit.navigation.navigation.SetupNavGraph
import com.course.profit.navigation.navigation.fromRoute
import com.profitable.profit.uikit.theme.ProfitableTogetherAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfitableTogetherAndroidTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()

                val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

                val sessionManager = SessionManager(this)

                // Control bottom bar
                when (navBackStackEntry?.fromRoute()) {
                    Screen.Main.HomeTab -> {
                        bottomBarState.value = true
                    }

                    Screen.Main.UserTab -> {
                        bottomBarState.value = true
                    }

                    Screen.Main.FavouriteTab -> {
                        bottomBarState.value = true
                    }

                    Screen.Main.Plug -> {
                        bottomBarState.value = true
                    }

                    else -> {
                        bottomBarState.value = false
                    }
                }

                Scaffold(
                    bottomBar = {
                        if (bottomBarState.value) {
                            BottomBar(navController)
                        }
                    },
                    modifier = Modifier.fillMaxSize(),
                ) { _ ->
                    Surface(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        SetupNavGraph(navController, sessionManager.fetchAuthToken() != null)
                    }
                }
            }
        }
    }
}
