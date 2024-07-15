package com.course.profit.main.homedetail.presentation.compose

import android.util.Patterns
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.course.profit.main.homedetail.presentation.viewmodel.HomeDetailViewModelState
import com.course.profit.main.homedetail.presentation.viewmodel.HomeDetailsViewModel
import com.profitable.profit.uikit.R
import com.profitable.profit.uikit.components.ProfitDialogAlert
import com.profitable.profit.uikit.components.ProfitDialogAlertWarning
import com.profitable.profit.uikit.components.ProfitHomePhotoItem
import com.profitable.profit.uikit.components.buttons.ProfitFavouriteButton
import com.profitable.profit.uikit.components.buttons.ProfitReserveButton
import com.profitable.profit.uikit.components.chips.ProfitUserChip
import com.profitable.profit.uikit.theme.ProfitTheme
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.absoluteValue

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeDetailsScreen(
    homeId: Long,
    onNavigateToUserProfile: (Long) -> Unit,
    onNavigateToSignIn: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    viewModel: HomeDetailsViewModel,
) {
    viewModel.getHomeById(homeId)

    val dialogAuthShowing = remember { mutableStateOf(false) }
    val dialogWarningShowing = remember { mutableStateOf(false) }
    if (dialogAuthShowing.value) {
        ProfitDialogAlert(
            onCreateAcc = {
                onNavigateToSignUp()
            },
            onLogin = {
                onNavigateToSignIn()
            },
            onDismissRequest = {
                dialogAuthShowing.value = false
            }
        )
    }

    if (dialogWarningShowing.value) {
        ProfitDialogAlertWarning(
            onDismissRequest = {
                dialogWarningShowing.value = false
            }
        )
    }


    val homeDetailsState by viewModel.homeDetailsState.collectAsState()
    when (val homeDetails = homeDetailsState) {
        HomeDetailViewModelState.Loading -> {

        }

        is HomeDetailViewModelState.Success -> {
            val pagerState =
                rememberPagerState(pageCount = { homeDetails.data.images.split(",").size })
            Column {
                HorizontalPager(
                    state = pagerState,
                    contentPadding = PaddingValues(
                        start = 24.dp,
                        top = 40.dp,
                        end = 40.dp,
                        bottom = 16.dp
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                ) { page ->
                    ProfitHomePhotoItem(
                        imageUrl = homeDetails.data.images.split(",")[page],
                        modifier = Modifier
                            .height(248.dp)
                            .graphicsLayer {
                                val pageOffset = (
                                        (pagerState.currentPage - page) + pagerState
                                            .currentPageOffsetFraction
                                        ).absoluteValue
                                scaleX = lerp(
                                    start = 0.9f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )
                                scaleY = lerp(
                                    start = 0.85f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )
                            },
                    )
                }
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .padding(
                            horizontal = 24.dp,
                            vertical = 0.dp,
                        )
                        .height(386.dp)
                        .verticalScroll(scrollState)
                ) {
                    Text(
                        text = homeDetails.data.roomSizes,
                        style = ProfitTheme.typography.headlineMedium,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    repeat(homeDetails.data.metroDistance.split(",").size) { index ->
                        Text(
                            text = homeDetails.data.metroDistance.split(",")[index],
                            style = ProfitTheme.typography.titleMedium,
                            color = Color.Black,
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = homeDetails.data.address,
                        style = ProfitTheme.typography.titleMedium,
                        color = Color.Black,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    val numberFormat = NumberFormat.getInstance(Locale("ru", "RU"))
                    Text(
                        text = "${numberFormat.format(homeDetails.data.price)} ₽/мес.",
                        style = ProfitTheme.typography.headlineMedium,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    val homeParamsTitles = listOf(
                        stringResource(id = R.string.utility_bill),
                        stringResource(id = R.string.bail),
                        stringResource(id = R.string.commissions),
                        stringResource(id = R.string.prepayment_period),
                        stringResource(id = R.string.rental_period),
                    )
                    val homeParamsValues = listOf(
                        homeDetails.data.utilityBill,
                        "${numberFormat.format(homeDetails.data.bail.toInt())} ₽",
                        homeDetails.data.commissions,
                        homeDetails.data.prepaymentPeriod,
                        homeDetails.data.rentalPeriod,
                    )
                    repeat(homeParamsTitles.size) { index ->
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = homeParamsTitles[index],
                                style = ProfitTheme.typography.bodyLarge,
                                color = Color.Black,
                            )
                            Text(
                                text = homeParamsValues[index],
                                style = ProfitTheme.typography.bodyLarge,
                                color = Color.Black,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    val uriHandler = LocalUriHandler.current
                    Text(
                        text = stringResource(id = R.string.link_to_original_announce),
                        style = ProfitTheme.typography.bodyLarge,
                        color = Color.Black,
                        modifier = Modifier.clickable {
                            if (Patterns.WEB_URL.matcher(homeDetails.data.originalAnnounce)
                                    .matches()
                            ) {
                                uriHandler.openUri(homeDetails.data.originalAnnounce)
                            }
                        }
                    )
                    if (homeDetails.isReserves) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(id = R.string.i_want_to_live_here_too),
                            style = ProfitTheme.typography.headlineMedium,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            with(homeDetails.data) {
                                if (users.isNotBlank()) {
                                    repeat(if (users.any { it == ',' }) users.split(",").size - 1 else 0) { index ->
                                        if (homeDetails.usernames.isNotEmpty()) {
                                            ProfitUserChip(
                                                onClick = {
                                                    onNavigateToUserProfile(homeDetails.usernames[index].first)
                                                },
                                                name = homeDetails.usernames[index].second,
                                                profilePhotoLink = ""
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 24.dp,
                            top = 16.dp,
                            end = 24.dp,
                        )
                ) {
                    ProfitReserveButton(
                        onClick = {
                            if (viewModel.unauthorizedState) {
                                dialogAuthShowing.value = true
                            } else {
                                if (homeDetails.isReserves) {
                                    viewModel.cancelReverse(homeId)
                                } else {
                                    if (homeDetails.reserves.size < 3) {
                                        viewModel.addReverse(homeId)
                                    } else {
                                        dialogWarningShowing.value = true
                                    }
                                }
                            }
                        },
                        isReserved = homeDetails.isReserves,
                        modifier = Modifier.weight(1f),
                        profitReserveButtonState = viewModel.reservationState,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    ProfitFavouriteButton(
                        onClick = {
                            if (viewModel.unauthorizedState) {
                                dialogAuthShowing.value = true
                            } else {
                                if (homeId.toString() in homeDetails.favourites) {
                                    viewModel.removeFavourite(homeId)
                                } else {
                                    viewModel.addFavourite(homeId)
                                }
                            }
                        },
                        isFavourite = homeDetails.favourites.contains(homeId.toString()),
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }

        HomeDetailViewModelState.Unauthorized -> {
            /*TODO()*/
        }
    }
}
