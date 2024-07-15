package com.course.profit.navigation.navigation

import androidx.compose.ui.graphics.vector.ImageVector

internal data class BottomNavigationItem(
    val route: Screen,
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
)
