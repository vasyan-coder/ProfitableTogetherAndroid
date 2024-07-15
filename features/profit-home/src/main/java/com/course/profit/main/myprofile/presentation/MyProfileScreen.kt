package com.course.profit.main.myprofile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.course.profit.data.model.UserInfo
import com.course.profit.main.myprofile.presentation.viewmodel.MyProfileState
import com.course.profit.main.myprofile.presentation.viewmodel.MyProfileViewModel
import com.profitable.profit.uikit.R
import com.profitable.profit.uikit.components.buttons.ProfitProfileButton
import com.profitable.profit.uikit.theme.ProfitTheme

@Composable
fun MyProfileScreen(
    viewModel: MyProfileViewModel,
    onNavigateToGreetingScreen: () -> Unit,
    onNavigateToPlugScreen: () -> Unit,
    onNavigateToAboutEditScreen: () -> Unit,
) {
    var isCheckedAuth by rememberSaveable {
        mutableStateOf(false)
    }
    val userState by viewModel.userFlow.collectAsState()
    when (val currentUser = userState) {
        is MyProfileState.Error -> {
            /*TODO()*/
        }

        MyProfileState.Loading -> {
            /*TODO()*/
        }

        MyProfileState.None -> {

        }

        is MyProfileState.Success -> {
            Content(
                user = currentUser.userInfo!!,
                viewModel = viewModel,
                onNavigateToGreetingScreen = onNavigateToGreetingScreen,
                onNavigateToAboutEditScreen = onNavigateToAboutEditScreen,
            )
        }

        MyProfileState.Unauthorized -> {
            if (!isCheckedAuth) {
                isCheckedAuth = true
                onNavigateToPlugScreen()
            }
        }
    }

}

@Composable
private fun Content(
    user: UserInfo,
    viewModel: MyProfileViewModel,
    onNavigateToGreetingScreen: () -> Unit,
    onNavigateToAboutEditScreen: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(all = 16.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            AsyncImage(
                model = null,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.Center)
                    .size(150.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = CircleShape,
                    ),
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = user.username,
            style = MaterialTheme.typography.headlineMedium,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(
                    color = ProfitTheme.colorScheme.primary,
                    shape = RoundedCornerShape(40.dp),
                )
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(40.dp),
                )
                .padding(all = 24.dp),
        ) {
            val scrollState = rememberScrollState()
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .verticalScroll(scrollState)
                    .clickable {
                        onNavigateToAboutEditScreen()
                    },
            ) {
                Text(
                    text = user.description,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            ProfitProfileButton(
                onClick = {
                    viewModel.removeToken()
                    onNavigateToGreetingScreen()
                },
                text = stringResource(id = R.string.logout),
            )
        }
    }
}
