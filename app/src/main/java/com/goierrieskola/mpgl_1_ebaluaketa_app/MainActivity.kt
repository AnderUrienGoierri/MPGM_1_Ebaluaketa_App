package com.goierrieskola.mpgl_1_ebaluaketa_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.TipsAndUpdates
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goierrieskola.mpgl_1_ebaluaketa_app.ui.screens.CheatSheetScreen
import com.goierrieskola.mpgl_1_ebaluaketa_app.ui.screens.HomeScreen
import com.goierrieskola.mpgl_1_ebaluaketa_app.ui.screens.PlaygroundScreen
import com.goierrieskola.mpgl_1_ebaluaketa_app.ui.screens.QuizScreen
import com.goierrieskola.mpgl_1_ebaluaketa_app.ui.screens.TheoryScreen
import com.goierrieskola.mpgl_1_ebaluaketa_app.ui.theme.MPGMTheme
import com.goierrieskola.mpgl_1_ebaluaketa_app.ui.theme.PrimaryBlue
import com.goierrieskola.mpgl_1_ebaluaketa_app.ui.theme.WrongRed

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MPGMTheme {
                MPGMEntrenatzaileaApp()
            }
        }
    }
}

sealed class BottomNavItem(
    val routeIndex: Int,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem(0, "Hasiera", Icons.Default.Home)
    object Theory : BottomNavItem(1, "Teoria", Icons.AutoMirrored.Filled.MenuBook)
    object Playground : BottomNavItem(2, "Kodea", Icons.Default.Code)
    object Quiz : BottomNavItem(3, "Test-a", Icons.Default.Quiz)
    object CheatSheet : BottomNavItem(4, "Laburpena", Icons.Default.TipsAndUpdates)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MPGMEntrenatzaileaApp() {
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }

    // Test-a martxan dagoen jakiteko egoera eta nabigazioaren babesa
    var isQuizActive by rememberSaveable { mutableStateOf(false) }
    var pendingDestinationTab by rememberSaveable { mutableIntStateOf(-1) }
    var showNavigationExitDialog by rememberSaveable { mutableStateOf(false) }

    val navItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Theory,
        BottomNavItem.Playground,
        BottomNavItem.Quiz,
        BottomNavItem.CheatSheet
    )

    // Test-a martxan dagoela beste atal batera joateko berrespen koadroa
    if (showNavigationExitDialog) {
        AlertDialog(
            onDismissRequest = {
                showNavigationExitDialog = false
                pendingDestinationTab = -1
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.ErrorOutline,
                    contentDescription = null,
                    tint = WrongRed,
                    modifier = Modifier.size(32.dp)
                )
            },
            title = {
                Text(
                    text = "Test-a amaitu gabe dago",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
            },
            text = {
                Text(
                    text = "Une honetan test bat egiten ari zara. Ziur zaude beste atal batera irten eta proba bukatutzat eman nahi duzula? Zure uneko aurrerapena galduko da.",
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        isQuizActive = false
                        showNavigationExitDialog = false
                        if (pendingDestinationTab != -1) {
                            selectedTab = pendingDestinationTab
                            pendingDestinationTab = -1
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = WrongRed)
                ) {
                    Text("Irten eta Utzi", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = {
                        showNavigationExitDialog = false
                        pendingDestinationTab = -1
                    }
                ) {
                    Text("Jarraitu Test-arekin")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = when (selectedTab) {
                            0 -> "MPGM Entrenatzailea"
                            1 -> "1. Ebaluaketa — Teoria"
                            2 -> "Kode Laborategia"
                            3 -> "Azterketa Test Simulatzailea"
                            4 -> "Txukuntze-Orria (CheatSheet)"
                            else -> "MPGM Entrenatzailea"
                        },
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            ) {
                navItems.forEach { item ->
                    NavigationBarItem(
                        selected = selectedTab == item.routeIndex,
                        onClick = {
                            // Test-etik beste atal batera irteten saiatzen bada eta martxan badago:
                            if (selectedTab == 3 && item.routeIndex != 3 && isQuizActive) {
                                pendingDestinationTab = item.routeIndex
                                showNavigationExitDialog = true
                            } else {
                                selectedTab = item.routeIndex
                            }
                        },
                        icon = {
                            Icon(imageVector = item.icon, contentDescription = item.title)
                        },
                        label = {
                            Text(
                                text = item.title,
                                fontSize = 11.sp,
                                fontWeight = if (selectedTab == item.routeIndex) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = PrimaryBlue,
                            indicatorColor = PrimaryBlue.copy(alpha = 0.15f)
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            when (selectedTab) {
                0 -> HomeScreen(
                    onNavigateToTheory = { selectedTab = 1 },
                    onNavigateToPlayground = { selectedTab = 2 },
                    onNavigateToQuiz = { selectedTab = 3 },
                    onNavigateToCheatSheet = { selectedTab = 4 }
                )
                1 -> TheoryScreen()
                2 -> PlaygroundScreen()
                3 -> QuizScreen(
                    onQuizStateChanged = { isQuizActive = it }
                )
                4 -> CheatSheetScreen()
            }
        }
    }
}

@Preview(showBackground = true, name = "App Nagusia Preview")
@Composable
fun AppPreview() {
    MPGMTheme {
        MPGMEntrenatzaileaApp()
    }
}
