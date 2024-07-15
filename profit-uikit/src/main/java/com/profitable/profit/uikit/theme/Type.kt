package com.profitable.profit.uikit.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.profitable.profit.uikit.R

val JostFamily = FontFamily(
    Font(R.font.jost_regular, FontWeight.Normal),
    Font(R.font.jost_semi_bold, FontWeight.SemiBold),
)

val LatoFamily = FontFamily(
    Font(R.font.lato_regular, FontWeight.Normal),
)

// Set of Material typography styles to start with
val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = LatoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = JostFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = JostFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = JostFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        color = Color.White,
    ),
    titleSmall = TextStyle(
        fontFamily = JostFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = JostFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = DarkBlue,
    ),
    bodyMedium = TextStyle(
        fontFamily = JostFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = JostFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = JostFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = JostFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
    ),
)
