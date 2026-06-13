package com.jsoftware.healthyeating.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.jsoftware.healthyeating.ui.theme.Pink40
import com.jsoftware.healthyeating.ui.theme.PurpleGrey40
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.Home


@Composable
fun AppGraph(){
    val backStack = rememberNavBackStack(AppRouter.Home)

    val selectTab: (AppRouter) -> Unit = { tab ->
        while (backStack.isNotEmpty()) backStack.removeLastOrNull()
        backStack.add(tab)
    }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when(key){
                is AppRouter.Home -> NavEntry(key){
                TabScaffold(currentTab = AppRouter.Home, onTabSelected = selectTab) { padding->
                    Home()
                }
                }
                else -> NavEntry(key) {}
            }
        }
    )
}


@Composable
private fun TabScaffold(
    currentTab: AppRouter,
    onTabSelected: (AppRouter) -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val reservedBottom = 96.dp
    Box(modifier = Modifier.fillMaxSize()) {
        content(PaddingValues(bottom = reservedBottom))
        BottomTabBar(
            currentTab = currentTab,
            onTabSelected = onTabSelected,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        )
    }
}


@Composable
private fun BottomTabBar(
    currentTab: AppRouter,
    onTabSelected: (AppRouter) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .shadow(elevation = 12.dp, shape = RoundedCornerShape(32.dp), clip = false)
            .clip(RoundedCornerShape(32.dp))
            .background(Color.White)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TabItem(
            label = "Home",
            iconSelected = Icons.Rounded.Home,
            iconUnselected = Icons.Outlined.Home,
            selected = currentTab is AppRouter.Home,
            modifier = Modifier.weight(1f),
            onClick = { onTabSelected(AppRouter.Home) }
        )
    }
}

@Composable
private fun TabItem(
    label: String,
    iconSelected: ImageVector,
    iconUnselected: ImageVector,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 4.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(if (selected) Pink40 else Color.Transparent)
            .pointerInput(Unit) { detectTapGestures(onTap = { onClick() }) },
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = if (selected) iconSelected else iconUnselected,
                contentDescription = label,
                tint = if (selected) Color.White else PurpleGrey40,
                modifier = Modifier.size(20.dp)
            )
            if (selected) {
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = label,
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}