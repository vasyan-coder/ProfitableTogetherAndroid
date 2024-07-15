package com.course.profit.navigation.navigation

import kotlinx.serialization.Serializable


/**
 * Screen
 *
 * @constructor Create empty Screen
 */
@Serializable
sealed class Screen {

    @Serializable
    data object Auth : Screen() {
        @Serializable
        data object Greeting : Screen()

        @Serializable
        data object SignIn : Screen()

        @Serializable
        data object SignUp : Screen()

        @Serializable
        data object AboutYourself : Screen()

        @Serializable
        data object Plug : Screen()
    }

    @Serializable
    data object Main : Screen() {

        @Serializable
        data object HomeTab : Screen() {

            @Serializable
            data class Home(
                val minBudget: Int = 0,
                val maxBudget: Int = 0,
                val roommatesCount: Int = 0,
                val metroDistance: String = "",
            ) : Screen()

            @Serializable
            data class HomeDetails(val homeId: Long) : Screen()

            @Serializable
            data object Search : Screen()

            @Serializable
            data class UserProfile(val userId: Long) : Screen()
        }

        @Serializable
        data object UserTab : Screen() {

            @Serializable
            data object MyProfile : Screen()

            @Serializable
            data object AboutEdit : Screen()

            @Serializable
            data object Plug : Screen()
        }

        @Serializable
        data object FavouriteTab : Screen() {

            @Serializable
            data object Favourite : Screen()

            @Serializable
            data class HomeDetails(val homeId: Long) : Screen()

            @Serializable
            data class UserProfile(val userId: Long) : Screen()

            @Serializable
            data object Plug : Screen()
        }

        @Serializable
        data object Plug : Screen()
    }
}
