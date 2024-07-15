package com.course.profit.main.home.presentation.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.course.profit.main.home.presentation.viewmodel.HomeState
import com.course.profit.main.home.presentation.viewmodel.HomeViewModel
import com.profitable.profit.uikit.R
import com.profitable.profit.uikit.components.HomeItem
import com.profitable.profit.uikit.components.ProfitDialogAlert
import com.profitable.profit.uikit.components.textfields.ProfitSecondaryTextField
import com.profitable.profit.uikit.theme.ProfitTheme

@Composable
fun HomeScreen(
    onNavigateToHomeDetails: (Long) -> Unit,
    onNavigateToSearch: () -> Unit,
    viewModel: HomeViewModel,
    minBudget: Int,
    maxBudget: Int,
    roommatesCount: Int,
    metroDistance: String,
    onNavigateToSignIn: () -> Unit,
    onNavigateToSignUp: () -> Unit,
) {
    var isInitSearchParams by remember {
        mutableStateOf(false)
    }

    val dialogAuthShowing = remember { mutableStateOf(false) }
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

    if (!isInitSearchParams) {
        isInitSearchParams = true
        viewModel.setSearchParams(
            minBudget = if (minBudget == 0) null else minBudget,
            maxBudget = if (maxBudget == 0) null else maxBudget,
            roommatesCount = if (roommatesCount == 0) null else roommatesCount,
            metroDistances = metroDistance.ifBlank { null },
        )
    }

    viewModel.getHomes()
    val homes by viewModel.homes.collectAsState()

    when (val currentHomeState = homes) {
        is HomeState.Error -> {
            /*TODO()*/
        }

        HomeState.Loading -> {
            /*TODO()*/
        }

        is HomeState.Success -> {
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        top = 84.dp,
                        end = 16.dp,
                        bottom = 94.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(currentHomeState.homes.size) { index: Int ->
                        HomeItem(
                            onClick = { onNavigateToHomeDetails(currentHomeState.homes[index].id) },
                            onClickFavourite = {
                                if (viewModel.unauthorizedState) {
                                    dialogAuthShowing.value = true
                                } else {
                                    if (currentHomeState.homes[index].id in currentHomeState.favourites.map { it.id }) {
                                        viewModel.removeFavourite(currentHomeState.homes[index].id)
                                    } else {
                                        viewModel.addFavourite(currentHomeState.homes[index].id)
                                    }
                                }
                            },
                            imageLink = currentHomeState.homes[index].images.split(",")[0],
                            title = currentHomeState.homes[index].name,
                            roomSizes = currentHomeState.homes[index].roomSizes,
                            address = currentHomeState.homes[index].address,
                            price = currentHomeState.homes[index].price.toString(),
                            isFavourite = currentHomeState.homes[index].id in currentHomeState.favourites.map { it.id },
                        )
                    }
                }
                ProfitSecondaryTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(40.dp))
                        .clickable {
                            onNavigateToSearch()
                        },
                    enabled = false,
                    leadingIcon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_search_stroked),
                            contentDescription = null,
                            tint = ProfitTheme.colorScheme.primary,
                            modifier = Modifier.size(32.dp),
                        )
                    },
                )
            }
        }
    }
}
