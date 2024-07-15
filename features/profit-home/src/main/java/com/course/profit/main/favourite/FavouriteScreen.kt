package com.course.profit.main.favourite

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.course.profit.data.model.Home
import com.course.profit.main.favourite.viewmodel.FavouriteViewModel
import com.course.profit.main.favourite.viewmodel.FavouriteViewModelState
import com.profitable.profit.uikit.R
import com.profitable.profit.uikit.components.HomeItem
import com.profitable.profit.uikit.components.ProfitFoldingButton
import com.profitable.profit.uikit.theme.ProfitTheme


@Composable
fun FavouriteScreen(
    viewModel: FavouriteViewModel,
    onNavigateToHomeDetails: (Long) -> Unit,
    onNavigateToPlugScreen: () -> Unit,
) {
    OnResumeObserver {
        viewModel.getUserInfo()
    }

    var isCheckedAuth by rememberSaveable {
        mutableStateOf(false)
    }

    var reservedIsHidden by rememberSaveable {
        mutableStateOf(false)
    }

    var favouritesIsHidden by rememberSaveable {
        mutableStateOf(false)
    }

    val favouriteScreenState by viewModel.favouriteFlow.collectAsState()
    when (val currState = favouriteScreenState) {
        is FavouriteViewModelState.Error -> {
            /*TODO()*/
        }

        FavouriteViewModelState.Loading -> {
            /*TODO()*/
        }

        FavouriteViewModelState.None -> {
            /*TODO()*/
        }

        is FavouriteViewModelState.Success -> {
            Content(
                favourites = currState.favouriteList,
                reserved = currState.reservedList,
                onNavigateToHomeDetails = onNavigateToHomeDetails,
                viewModel = viewModel,
                favouritesIsHidden = favouritesIsHidden,
                favouritesIsHiddenChange = {
                    favouritesIsHidden = it
                },
                reservedIsHidden = reservedIsHidden,
                reservedIsHiddenChange = {
                    reservedIsHidden = it
                },
            )
        }

        FavouriteViewModelState.Unauthorized -> {
            if (!isCheckedAuth) {
                isCheckedAuth = true
                onNavigateToPlugScreen()
            }
        }
    }
}

@Composable
private fun Content(
    favourites: List<Home>,
    reserved: List<Home>,
    onNavigateToHomeDetails: (Long) -> Unit,
    viewModel: FavouriteViewModel,
    reservedIsHidden: Boolean,
    reservedIsHiddenChange: (Boolean) -> Unit,
    favouritesIsHidden: Boolean,
    favouritesIsHiddenChange: (Boolean) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(start = 16.dp, top = 32.dp, end = 16.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            ProfitFoldingButton(
                onClick = { reservedIsHiddenChange(!reservedIsHidden) },
                text = stringResource(id = R.string.reserved),
                isOpen = reservedIsHidden,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        subListReserved(reserved, reservedIsHidden, onNavigateToHomeDetails, favourites, viewModel)
        item {
            ProfitFoldingButton(
                onClick = { favouritesIsHiddenChange(!favouritesIsHidden) },
                text = stringResource(id = R.string.favourite),
                isOpen = favouritesIsHidden,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        if (reserved.isEmpty() && favourites.isEmpty()) {
            item {
                Text(
                    text = stringResource(id = R.string.favourite_hint),
                    style = ProfitTheme.typography.titleLarge,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 64.dp, vertical = 32.dp)
                )
            }
        } else {
            subListFavourites(favourites, favouritesIsHidden, onNavigateToHomeDetails, viewModel)
        }
    }
}

private fun LazyListScope.subListFavourites(
    favorites: List<Home>,
    isHidden: Boolean,
    onNavigateToHomeDetails: (Long) -> Unit,
    viewModel: FavouriteViewModel,
) {
    items(favorites) { favourite ->
        AnimatedVisibility(
            visible = isHidden,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            with(favourite) {
                HomeItem(
                    onClick = { onNavigateToHomeDetails(id) },
                    onClickFavourite = {
                        viewModel.removeFavourite(id)
                    },
                    imageLink = images,
                    title = name,
                    roomSizes = roomSizes,
                    address = address,
                    price = price.toString(),
                    isFavourite = true,
                )
            }
        }
    }
}

private fun LazyListScope.subListReserved(
    reserved: List<Home>,
    isHidden: Boolean,
    onNavigateToHomeDetails: (Long) -> Unit,
    favorites: List<Home>,
    viewModel: FavouriteViewModel,
) {
    val favorites2 = favorites
    items(reserved) { favourite ->
        AnimatedVisibility(
            visible = isHidden,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            with(favourite) {
                HomeItem(
                    onClick = { onNavigateToHomeDetails(id) },
                    onClickFavourite = {
                        if (id in favorites.map { it.id }) {
                            viewModel.removeFavourite(id)
                        } else {
                            viewModel.addFavourite(id)
                        }
                    },
                    imageLink = images.split(",")[0],
                    title = name,
                    roomSizes = roomSizes,
                    address = address,
                    price = price.toString(),
                    isFavourite = id in favorites.map { it.id },
                )
            }
        }
    }
}

@Composable
fun OnResumeObserver(
    onResume: () -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                onResume()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}
