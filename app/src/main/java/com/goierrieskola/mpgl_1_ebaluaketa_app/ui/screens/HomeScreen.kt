package com.goierrieskola.mpgl_1_ebaluaketa_app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.TipsAndUpdates
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goierrieskola.mpgl_1_ebaluaketa_app.ui.theme.AccentAmber
import com.goierrieskola.mpgl_1_ebaluaketa_app.ui.theme.PrimaryBlue
import com.goierrieskola.mpgl_1_ebaluaketa_app.ui.theme.SecondaryTeal

@Composable
fun HomeScreen(
    onNavigateToTheory: () -> Unit,
    onNavigateToPlayground: () -> Unit,
    onNavigateToQuiz: () -> Unit,
    onNavigateToCheatSheet: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Goiburuko Txartela (Banner dinamikoa)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(PrimaryBlue, SecondaryTeal)
                    )
                )
                .padding(24.dp)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = AccentAmber,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Goierri Eskola • 2MG3",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "MPGM — 1. Ebaluaketa Entrenatzailea",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 28.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Gailu mugikorrak, hardwarea, Android arkitektura, Kotlin OOP eta Jetpack Compose dena aplikazio bakarrean.",
                    color = Color.White.copy(alpha = 0.85f),
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Datu bizkorren barra
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            QuickStatBadge(
                title = "50 Galdera",
                subtitle = "Test Ausaza",
                color = PrimaryBlue,
                modifier = Modifier.weight(1f)
            )
            QuickStatBadge(
                title = "15 Erronka",
                subtitle = "Zati Praktikoa",
                color = SecondaryTeal,
                modifier = Modifier.weight(1f)
            )
            QuickStatBadge(
                title = "14 Fitxa",
                subtitle = "Txukuntze-orria",
                color = AccentAmber,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Ikasketa Atal Nagusiak",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 4 Modulu Nagusiak
        NavOptionCard(
            title = "1. Teoria Osoa (5 Gai)",
            description = "Hardwarea (SoC/OLED), Android arkitektura, Kotlin OOP eta Jetpack Compose kontzeptu guztiak koloretako kodearekin.",
            icon = Icons.AutoMirrored.Filled.MenuBook,
            color = PrimaryBlue,
            onClick = onNavigateToTheory
        )

        Spacer(modifier = Modifier.height(12.dp))

        NavOptionCard(
            title = "2. Kode Laborategia & Azterketa Praktika",
            description = "15 erronka praktiko (Kodea Ulertu, Akatsak Zuzendu eta Hutsuneak Idatzi) + Canvas OOP eta Compose Live Labs.",
            icon = Icons.Default.Code,
            color = Color(0xFF6C5CE7),
            onClick = onNavigateToPlayground
        )

        Spacer(modifier = Modifier.height(12.dp))

        NavOptionCard(
            title = "3. Azterketa Test Simulatzailea",
            description = "50 galderako bankua, 20 galdera ausaz, aukerak (A, B, C, D) nahastuta eta luzera orekatuekin azterketarako.",
            icon = Icons.Default.Quiz,
            color = Color(0xFF00B894),
            onClick = onNavigateToQuiz
        )

        Spacer(modifier = Modifier.height(12.dp))

        NavOptionCard(
            title = "4. Txukuntze-Orria (CheatSheet)",
            description = "Azterketa aurreko azken orduko gakoak: definizio kritikoak, formulak eta abisuak.",
            icon = Icons.Default.TipsAndUpdates,
            color = Color(0xFFE17055),
            onClick = onNavigateToCheatSheet
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Aholku Txartela
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = SecondaryTeal,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Azterketarako Gida: Entrenatu Test Simulatzailean (aukerak beti orden ezberdinean daude!) eta ondoren egin Laborategiko 'Azterketa Praktika' atala kodea ulertu, akatsak zuzendu eta idazteko!",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun QuickStatBadge(
    title: String,
    subtitle: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f)),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = color
            )
            Text(
                text = subtitle,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun NavOptionCard(
    title: String,
    description: String,
    icon: ImageVector,
    color: Color,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(color.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 18.sp
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
