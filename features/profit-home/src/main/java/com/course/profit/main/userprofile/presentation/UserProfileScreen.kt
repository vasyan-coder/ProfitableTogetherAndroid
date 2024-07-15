package com.course.profit.main.userprofile.presentation

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.course.profit.main.userprofile.presentation.viewmodel.UserProfileState
import com.course.profit.main.userprofile.presentation.viewmodel.UserProfileViewModel
import com.profitable.profit.uikit.R
import com.profitable.profit.uikit.components.buttons.ProfitBackButton
import com.profitable.profit.uikit.components.buttons.ProfitProfileButton
import com.profitable.profit.uikit.theme.ProfitTheme

@Composable
fun UserProfileScreen(
    userId: Long,
    onBackPressed: () -> Unit,
    viewModel: UserProfileViewModel,
) {
    viewModel.getUser(userId)

    val userState by viewModel.user.collectAsState()
    when (val currentUserState = userState) {
        UserProfileState.Loading -> {
            /*TODO()*/
        }

        is UserProfileState.Success -> {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(all = 16.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    ProfitBackButton(
                        onClick = { onBackPressed() },
                        modifier = Modifier.align(Alignment.TopStart),
                    )
                    AsyncImage(
                        model = "TODO",
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
                    text = currentUserState.user.username,
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
                            .verticalScroll(scrollState),
                    ) {
                        Text(
                            text = currentUserState.user.description,
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White,
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    val uriHandler = LocalUriHandler.current
                    ProfitProfileButton(
                        onClick = {
                            if (Patterns.WEB_URL.matcher(currentUserState.user.chatLink)
                                    .matches()
                            ) {
                                uriHandler.openUri(currentUserState.user.chatLink)
                            }
                        },
                        text = stringResource(id = R.string.start_chat),
                        modifier = Modifier.width(196.dp),
                    )
                }
            }
        }
    }
}
