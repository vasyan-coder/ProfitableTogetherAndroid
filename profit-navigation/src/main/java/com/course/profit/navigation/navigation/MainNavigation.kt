package com.course.profit.navigation.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.course.profit.main.aboutedit.AboutEditScreen
import com.course.profit.main.favourite.FavouriteScreen
import com.course.profit.main.home.presentation.compose.HomeScreen
import com.course.profit.main.homedetail.presentation.compose.HomeDetailsScreen
import com.course.profit.main.myprofile.presentation.MyProfileScreen
import com.course.profit.main.plug.PlugScreen
import com.course.profit.main.search.presentation.compose.SearchScreen
import com.course.profit.main.userprofile.presentation.UserProfileScreen
import com.profitable.profit.auth.aboutyourself.presentation.compose.AboutYourSelfScreen
import com.profitable.profit.auth.greeting.GreetingScreen
import com.profitable.profit.auth.signin.presentation.compose.SignInScreen
import com.profitable.profit.auth.signup.presentation.compose.SignUpScreen
import com.profitable.profit.uikit.R
import com.profitable.profit.uikit.theme.ProfitTheme

/**
 * Setup nav graph
 *
 * @param navController
 */
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    isLoggedIn: Boolean,
) {
    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn && navController.currentBackStackEntry.fromRoute() != Screen.Auth.AboutYourself)
            Screen.Main
        else
            Screen.Auth
    ) {
        authNavGraph(navController)
        mainNavGraph(navController)
    }
}

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
) {
    navigation<Screen.Auth>(
        startDestination = Screen.Auth.Greeting
    ) {
        composable<Screen.Auth.Greeting> {
            GreetingScreen(
                onNavigateToSignIn = {
                    navController.navigate(Screen.Auth.SignIn)
                },
                onNavigateToSignUp = {
                    navController.navigate(Screen.Auth.SignUp)
                },
                onNavigateToHome = {
                    navController.navigate(Screen.Main.HomeTab)
                }
            )
        }
        composable<Screen.Auth.SignIn> {
            SignInScreen(
                onNavigateToSignUp = {
                    navController.navigate(Screen.Auth.SignUp)
                },
                onNavigateToForgotPassword = {
                    TODO("Not yet implemented")
                },
                onNavigateToHome = {
                    navController.navigate(Screen.Main.HomeTab) {
                        popUpTo<Screen.Auth> {
                            inclusive = true
                        }
                    }
                },
                onGoogleSignIn = {
                    TODO("Not yet implemented")
                },
                onBackPressed = {
                    navController.popBackStack()
                },
                viewModel = hiltViewModel()
            )
        }
        composable<Screen.Auth.SignUp> {
            SignUpScreen(
                onNavigateToAboutYourself = {
                    navController.navigate(Screen.Auth.AboutYourself)
                },
                onBackPressed = {
                    navController.popBackStack()
                },
                viewModel = hiltViewModel(),
            )
        }

        composable<Screen.Auth.AboutYourself> {
            AboutYourSelfScreen(
                onNavigateToHome = {
                    navController.navigate(Screen.Main) {
                        popUpTo<Screen.Auth>() {
                            inclusive = true
                        }
                    }
                },
                viewModel = hiltViewModel(),
            )
        }
    }
}

fun NavGraphBuilder.mainNavGraph(
    navController: NavHostController,
) {
    navigation<Screen.Main>(
        startDestination = Screen.Main.HomeTab
    ) {

        composable<Screen.Main.HomeTab> {
            HomeNavHost(navController)
        }
        composable<Screen.Main.UserTab> {
            UserNavHost(navController)
        }
        composable<Screen.Main.FavouriteTab> {
            FavouriteNavHost(navController)
        }

        composable<Screen.Main.Plug> {
            PlugScreen(
                onNavigateToSignIn = {
                    navController.navigate(Screen.Auth.SignIn)
                },
                onNavigateToSignUp = {
                    navController.navigate(Screen.Auth.SignUp)
                }
            )
        }

    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
) {
    val items = listOf(
        BottomNavigationItem(
            route = Screen.Main.UserTab,
            title = stringResource(id = R.string.profile),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.ic_profile_stroked),
            selectedIcon = ImageVector.vectorResource(id = R.drawable.ic_profile_filled)
        ),
        BottomNavigationItem(
            route = Screen.Main.HomeTab,
            title = stringResource(id = R.string.search),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.ic_search_stroked),
            selectedIcon = ImageVector.vectorResource(id = R.drawable.ic_search_filled)
        ),
        BottomNavigationItem(
            route = Screen.Main.FavouriteTab,
            title = stringResource(id = R.string.favourite),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.ic_heart_stroked),
            selectedIcon = ImageVector.vectorResource(id = R.drawable.ic_heart_filled)
        ),
    )
    NavigationBar(
        containerColor = ProfitTheme.colorScheme.secondary,
        contentColor = ProfitTheme.colorScheme.primary,
    ) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()

        items.forEach { item ->
            val isSelected = navBackStackEntry?.fromRoute() == item.route
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = ProfitTheme.colorScheme.primary,
                ),
                selected = isSelected,
                label = {
                    Text(
                        text = item.title,
                        style = ProfitTheme.typography.titleSmall,
                        color = Color.White,
                    )
                },
                icon = {
                    Icon(
                        imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(36.dp),
                    )
                },
                onClick = {
                    navController.navigate(route = item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}

fun NavBackStackEntry?.fromRoute(): Screen {
    this?.destination?.route?.substringBefore("?")?.substringBefore("/")
        ?.substringAfterLast(".")?.let {
            when (it) {
                Screen.Auth::class.simpleName -> return Screen.Auth
                Screen.Auth.Greeting::class.simpleName -> return Screen.Auth.Greeting
                Screen.Auth.SignIn::class.simpleName -> return Screen.Auth.SignIn
                Screen.Auth.SignUp::class.simpleName -> return Screen.Auth.SignUp
                Screen.Auth.AboutYourself::class.simpleName -> return Screen.Auth.AboutYourself
                Screen.Main::class.simpleName -> return Screen.Main
                Screen.Main.HomeTab.Home::class.simpleName -> return Screen.Main.HomeTab.Home(
                    0,
                    0,
                    0,
                    ""
                )

                Screen.Main.HomeTab.HomeDetails::class.simpleName -> return Screen.Main.HomeTab.HomeDetails(
                    0L
                )

                Screen.Main.HomeTab::class.simpleName -> return Screen.Main.HomeTab
                Screen.Main.FavouriteTab::class.simpleName -> return Screen.Main.FavouriteTab
                Screen.Main.UserTab::class.simpleName -> return Screen.Main.UserTab

                Screen.Main.HomeTab.Search::class.simpleName -> return Screen.Main.HomeTab.Search
                Screen.Main.HomeTab.UserProfile::class.simpleName -> return Screen.Main.HomeTab.UserProfile(
                    0L
                )

                Screen.Main.UserTab.MyProfile::class.simpleName -> return Screen.Main.UserTab.MyProfile
                Screen.Main.Plug::class.simpleName -> return Screen.Main.Plug
                Screen.Main.FavouriteTab.Favourite::class.simpleName -> return Screen.Main.FavouriteTab.Favourite
                else -> return Screen.Auth.Greeting
            }
        }
    return Screen.Main.Plug
}

@Composable
fun HomeNavHost(
    navController: NavHostController,
) {
    val homeNavHost = rememberNavController()
    NavHost(navController = homeNavHost, startDestination = Screen.Main.HomeTab.Search) {
        composable<Screen.Main.HomeTab.Home>(
        ) { backStackEntry: NavBackStackEntry ->
            HomeScreen(
                onNavigateToHomeDetails = { id ->
                    homeNavHost.navigate(Screen.Main.HomeTab.HomeDetails(homeId = id))
                },
                onNavigateToSearch = {
                    homeNavHost.navigate(Screen.Main.HomeTab.Search)
                },
                viewModel = hiltViewModel(),
                minBudget = backStackEntry.toRoute<Screen.Main.HomeTab.Home>().minBudget,
                maxBudget = backStackEntry.toRoute<Screen.Main.HomeTab.Home>().maxBudget,
                roommatesCount = backStackEntry.toRoute<Screen.Main.HomeTab.Home>().roommatesCount,
                metroDistance = backStackEntry.toRoute<Screen.Main.HomeTab.Home>().metroDistance,
                onNavigateToSignUp = {
                    navController.navigate(Screen.Auth.SignUp)
                },
                onNavigateToSignIn = {
                    navController.navigate(Screen.Auth.SignIn)
                },
            )
        }
        composable<Screen.Main.HomeTab.HomeDetails> { backStackEntry ->
            HomeDetailsScreen(
                homeId = backStackEntry.toRoute<Screen.Main.HomeTab.HomeDetails>().homeId,
                onNavigateToUserProfile = {
                    homeNavHost.navigate(Screen.Main.HomeTab.UserProfile(userId = it))
                },
                onNavigateToSignUp = {
                    navController.navigate(Screen.Auth.SignUp)
                },
                onNavigateToSignIn = {
                    navController.navigate(Screen.Auth.SignIn)
                },
                viewModel = hiltViewModel(),
            )
        }

        composable<Screen.Main.HomeTab.Search> {
            SearchScreen(
                onNavigateToHome = { minBudget, maxBudget, roommatesCount, metroDistance ->
                    homeNavHost.navigate(
                        Screen.Main.HomeTab.Home(
                            minBudget = minBudget,
                            maxBudget = maxBudget,
                            roommatesCount = roommatesCount,
                            metroDistance = metroDistance
                        )
                    ) {
                        popUpTo<Screen.Main.HomeTab.Search>() {
                            inclusive = true
                        }
                    }
                },
                viewModel = hiltViewModel(),
            )
        }

        composable<Screen.Main.HomeTab.UserProfile> { backStackEntry ->
            UserProfileScreen(
                userId = backStackEntry.toRoute<Screen.Main.HomeTab.UserProfile>().userId,
                onBackPressed = {
                    homeNavHost.popBackStack()
                },
                viewModel = hiltViewModel(),
            )
        }
    }
}

@Composable
fun UserNavHost(
    navController: NavHostController,
) {
    val userNavHost = rememberNavController()
    NavHost(navController = userNavHost, startDestination = Screen.Main.UserTab.MyProfile) {
        composable<Screen.Main.UserTab.MyProfile> {
            MyProfileScreen(
                viewModel = hiltViewModel(),
                onNavigateToGreetingScreen = {
                    navController.navigate(Screen.Auth) {
                        popUpTo<Screen.Main> {
                            inclusive = true
                        }
                    }
                },
                onNavigateToPlugScreen = {
                    userNavHost.navigate(Screen.Main.UserTab.Plug) {
                        popUpTo<Screen.Main.UserTab.MyProfile>() {
                            inclusive = true
                        }
                    }
                },
                onNavigateToAboutEditScreen = {
                    userNavHost.navigate(Screen.Main.UserTab.AboutEdit)
                }
            )
        }

        composable<Screen.Main.UserTab.AboutEdit> {
            AboutEditScreen(
                onNavigateToMyProfile = {
                    userNavHost.navigate(Screen.Main.UserTab.MyProfile) {
                        popUpTo<Screen.Main.UserTab.MyProfile>() {
                            inclusive = true
                        }
                    }
                },
                viewModel = hiltViewModel(),
            )
        }

        composable<Screen.Main.UserTab.Plug> {
            PlugScreen(
                onNavigateToSignIn = {
                    navController.navigate(Screen.Auth.SignIn)
                },
                onNavigateToSignUp = {
                    navController.navigate(Screen.Auth.SignUp)
                }
            )
        }
    }
}

@Composable
fun FavouriteNavHost(
    navController: NavHostController,
) {
    val favouriteNavHost = rememberNavController()
    NavHost(
        navController = favouriteNavHost,
        startDestination = Screen.Main.FavouriteTab.Favourite
    ) {
        composable<Screen.Main.FavouriteTab.Favourite> {
            FavouriteScreen(
                viewModel = hiltViewModel(),
                onNavigateToHomeDetails = { id ->
                    favouriteNavHost.navigate(Screen.Main.FavouriteTab.HomeDetails(homeId = id))
                },
                onNavigateToPlugScreen = {
                    favouriteNavHost.navigate(Screen.Main.FavouriteTab.Plug) {
                        popUpTo<Screen.Main.FavouriteTab.Favourite> {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable<Screen.Main.FavouriteTab.HomeDetails> { backStackEntry ->
            HomeDetailsScreen(
                homeId = backStackEntry.toRoute<Screen.Main.FavouriteTab.HomeDetails>().homeId,
                onNavigateToUserProfile = {
                    favouriteNavHost.navigate(Screen.Main.FavouriteTab.UserProfile(userId = it))
                },
                viewModel = hiltViewModel(),
                onNavigateToSignUp = {
                    navController.navigate(Screen.Auth.SignUp)
                },
                onNavigateToSignIn = {
                    navController.navigate(Screen.Auth.SignIn)
                },
            )
        }
        composable<Screen.Main.FavouriteTab.UserProfile> { backStackEntry ->
            UserProfileScreen(
                userId = backStackEntry.toRoute<Screen.Main.FavouriteTab.UserProfile>().userId,
                onBackPressed = {
                    favouriteNavHost.popBackStack()
                },
                viewModel = hiltViewModel(),
            )
        }
        composable<Screen.Main.FavouriteTab.Plug> {
            PlugScreen(
                onNavigateToSignIn = {
                    navController.navigate(Screen.Auth.SignIn)
                },
                onNavigateToSignUp = {
                    navController.navigate(Screen.Auth.SignUp)
                }
            )
        }
    }
}
