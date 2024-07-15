package com.course.profit.main.search.presentation.compose

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.course.profit.main.search.presentation.viewmodel.SearchViewModel
import com.course.profit.main.search.presentation.viewmodel.model.SearchMainEvent
import com.profitable.profit.uikit.R
import com.profitable.profit.uikit.components.buttons.ProfitSearchActionButton
import com.profitable.profit.uikit.components.textfields.ProfitSecondaryTextField
import com.profitable.profit.uikit.theme.DarkGrey
import com.profitable.profit.uikit.theme.ProfitTheme


@Composable
fun SearchScreen(
    onNavigateToHome: (Int, Int, Int, String) -> Unit,
    viewModel: SearchViewModel,
) {
    var searchParamsIsInit by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("search_params", Context.MODE_PRIVATE)

    val state by viewModel.isSuccessfulSearch.collectAsState()
    var isSuccess by rememberSaveable {
        mutableStateOf(true)
    }
    if (isSuccess && state) {
        isSuccess = false
        onNavigateToHome(
            viewModel.searchStateUI.minBudget.toInt(),
            viewModel.searchStateUI.maxBudget.toInt(),
            viewModel.searchStateUI.roommatesQuantity.toInt(),
            viewModel.searchStateUI.metroStation
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 32.dp, top = 0.dp, end = 32.dp, bottom = 72.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(216.dp),
        )
        Column {
            Text(
                text = stringResource(id = R.string.your_budget),
                style = ProfitTheme.typography.headlineMedium,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
            ) {
                ProfitSecondaryTextField(
                    value = viewModel.searchStateUI.minBudget.ifBlank {
                        if (!searchParamsIsInit) {
                            viewModel.onEvent(
                                SearchMainEvent.MinBudgetChanged(
                                    minBudget = readSearchMinBudget(sharedPreferences) ?: ""
                                )
                            )
                            readSearchMinBudget(sharedPreferences) ?: ""
                        } else {
                            viewModel.searchStateUI.minBudget
                        }
                    },
                    onValueChange = {
                        if (it.isDigitsOnly()) {
                            viewModel.onEvent(SearchMainEvent.MinBudgetChanged(minBudget = it))
                        }
                    },
                    placeholder = stringResource(id = R.string.min_budget),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next,
                    ),
                    borderColor = if (viewModel.searchStateUI.isErrorMinBudget)
                        ProfitTheme.colorScheme.error
                    else
                        ProfitTheme.colorScheme.secondary,
                )
                ProfitSecondaryTextField(
                    value = viewModel.searchStateUI.maxBudget.ifBlank {
                        if (!searchParamsIsInit) {
                            viewModel.onEvent(
                                SearchMainEvent.MaxBudgetChanged(
                                    maxBudget = readSearchMaxBudget(sharedPreferences) ?: ""
                                )
                            )
                            readSearchMaxBudget(sharedPreferences) ?: ""
                        } else {
                            viewModel.searchStateUI.maxBudget
                        }
                    },
                    onValueChange = {
                        if (it.isDigitsOnly()) {
                            viewModel.onEvent(SearchMainEvent.MaxBudgetChanged(maxBudget = it))
                        }
                    },
                    placeholder = stringResource(id = R.string.max_budget),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next,
                    ),
                    borderColor = if (viewModel.searchStateUI.isErrorMaxBudget)
                        ProfitTheme.colorScheme.error
                    else
                        ProfitTheme.colorScheme.secondary,
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(id = R.string.roommates_count),
                style = ProfitTheme.typography.headlineMedium,
            )
            Spacer(modifier = Modifier.height(16.dp))
            ProfitSecondaryTextField(
                value = viewModel.searchStateUI.roommatesQuantity.ifBlank {
                    if (!searchParamsIsInit) {
                        searchParamsIsInit = true
                        viewModel.onEvent(
                            SearchMainEvent.RoommatesQuantityChanged(
                                roommatesQuantity = readSearchRoommatesCount(sharedPreferences)
                                    ?: ""
                            )
                        )
                        readSearchRoommatesCount(sharedPreferences) ?: ""
                    } else {
                        viewModel.searchStateUI.roommatesQuantity
                    }
                },
                onValueChange = {
                    if (it.isDigitsOnly()) {
                        viewModel.onEvent(SearchMainEvent.RoommatesQuantityChanged(roommatesQuantity = it))
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = stringResource(id = R.string.input_roommates_count),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next,
                ),
                borderColor = if (viewModel.searchStateUI.isErrorRoommatesQuantity)
                    ProfitTheme.colorScheme.error
                else
                    ProfitTheme.colorScheme.secondary,
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(id = R.string.location),
                style = ProfitTheme.typography.headlineMedium,
            )
            Spacer(modifier = Modifier.height(16.dp))
            ProfitSecondaryTextField(
                value = viewModel.searchStateUI.metroStation,
                onValueChange = {
                    viewModel.onEvent(SearchMainEvent.MetroStationChanged(metroStation = it))
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = stringResource(id = R.string.input_metro_station),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                ),
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_search_stroked),
                        contentDescription = null,
                        tint = ProfitTheme.colorScheme.primary,
                        modifier = Modifier.size(32.dp),
                    )
                },
                borderColor = ProfitTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
        Text(
            text = stringResource(id = R.string.search_description),
            color = DarkGrey,
            style = ProfitTheme.typography.titleSmall,
        )
        Spacer(modifier = Modifier.height(16.dp))
        ProfitSearchActionButton(
            onClick = {
                writeSearchMaxBudget(sharedPreferences, viewModel.searchStateUI.maxBudget)
                writeSearchMinBudget(sharedPreferences, viewModel.searchStateUI.minBudget)
                writeSearchRoommatesCount(
                    sharedPreferences,
                    viewModel.searchStateUI.roommatesQuantity
                )

                viewModel.onEvent(SearchMainEvent.Submit)
            },
            text = stringResource(id = R.string.search),
            modifier = Modifier
                .width(200.dp)
                .align(Alignment.CenterHorizontally),
        )
    }
}

private fun readSearchMinBudget(sharedPreferences: SharedPreferences): String? {
    return sharedPreferences.getString("min_budget", "")
}

private fun readSearchMaxBudget(sharedPreferences: SharedPreferences): String? {
    return sharedPreferences.getString("max_budget", "")
}

private fun readSearchRoommatesCount(sharedPreferences: SharedPreferences): String? {
    return sharedPreferences.getString("roommates_count", "")
}

private fun writeSearchMinBudget(sharedPreferences: SharedPreferences, value: String) {
    writePreference(sharedPreferences, "min_budget", value)
}

private fun writeSearchMaxBudget(sharedPreferences: SharedPreferences, value: String) {
    writePreference(sharedPreferences, "max_budget", value)
}

private fun writeSearchRoommatesCount(sharedPreferences: SharedPreferences, value: String) {
    writePreference(sharedPreferences, "roommates_count", value)
}

private fun writePreference(sharedPreferences: SharedPreferences, key: String, value: String) {
    with(sharedPreferences.edit()) {
        putString(key, value)
        apply()
    }
}
