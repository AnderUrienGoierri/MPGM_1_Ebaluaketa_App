package com.goierrieskola.mpgl_1_ebaluaketa_app.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Animation
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.FormatPaint
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.ScreenRotation
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goierrieskola.mpgl_1_ebaluaketa_app.model.Elipsea
import com.goierrieskola.mpgl_1_ebaluaketa_app.model.GeometriaFigura
import com.goierrieskola.mpgl_1_ebaluaketa_app.model.Laukizuzena
import com.goierrieskola.mpgl_1_ebaluaketa_app.model.Triangelua
import com.goierrieskola.mpgl_1_ebaluaketa_app.model.Zirkulua
import com.goierrieskola.mpgl_1_ebaluaketa_app.ui.components.CodeEditorView
import com.goierrieskola.mpgl_1_ebaluaketa_app.ui.components.PracticalExamTrainer
import com.goierrieskola.mpgl_1_ebaluaketa_app.ui.theme.AccentAmber
import com.goierrieskola.mpgl_1_ebaluaketa_app.ui.theme.PrimaryBlue
import com.goierrieskola.mpgl_1_ebaluaketa_app.ui.theme.SecondaryTeal

@Composable
fun PlaygroundScreen(modifier: Modifier = Modifier) {
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }
    val tabs = listOf(
        "1. Azterketa Praktika (Ulertu/Zuzendu/Idatzi)",
        "2. Kotlin OOP & Datuak",
        "3. Compose Layouts & Modifiers",
        "4. Codelab Praktikak",
        "5. Egoera & Animazioak"
    )

    Column(modifier = modifier.fillMaxSize()) {
        SecondaryScrollableTabRow(
            selectedTabIndex = selectedTab,
            edgePadding = 16.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            text = title,
                            fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 13.sp
                        )
                    }
                )
            }
        }

        when (selectedTab) {
            0 -> PracticalExamTrainer()
            1 -> FiguresOopPlayground()
            2 -> ComposeLivePlayground()
            3 -> CodelabsPlayground()
            4 -> StateAndAnimationsPlayground()
        }
    }
}

// =========================================================================
// 1. KOTLIN OOP: Figurak, Polimorfismoa eta Data Classes
// =========================================================================
@Composable
fun FiguresOopPlayground() {
    var selectedFigureType by rememberSaveable { mutableIntStateOf(0) }
    val figureTypes = listOf("Laukizuzena", "Triangelua", "Zirkulua", "Elipsea")

    var param1 by rememberSaveable { mutableStateOf("10.0") }
    var param2 by rememberSaveable { mutableStateOf("5.0") }

    val val1 = param1.toDoubleOrNull() ?: 0.0
    val val2 = param2.toDoubleOrNull() ?: 0.0

    // Polimorfismo bidezko figura instantziazioa
    val figura: GeometriaFigura = remember(selectedFigureType, val1, val2) {
        when (selectedFigureType) {
            0 -> Laukizuzena("Urdina", val1, val2)
            1 -> Triangelua("Berdea", val1, val2)
            2 -> Zirkulua("Gorria", val1)
            3 -> Elipsea("Horia", val1, val2)
            else -> Laukizuzena("Urdina", val1, val2)
        }
    }

    val azalera = figura.kalkulatuAzalera()
    val perimetroa = figura.kalkulatuPerimetroa()

    // Data Class simulagailuaren egoera
    var ikasleNota by rememberSaveable { mutableDoubleStateOf(8.5) }
    var ikasleTaldea by rememberSaveable { mutableStateOf("2MG3") }
    var kopiaNota by rememberSaveable { mutableDoubleStateOf(9.5) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "1. Geometria Figurak & Polimorfismoa",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "'abstract class GeometriaFigura' + 'interface Gainazala' + 'interface Perimetroa'",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Figura hautatzailea
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            figureTypes.forEachIndexed { index, name ->
                FilterChip(
                    selected = selectedFigureType == index,
                    onClick = {
                        selectedFigureType = index
                        if (index == 2) {
                            param1 = "5.0"
                        } else if (index == 3) {
                            param1 = "8.0"
                            param2 = "4.0"
                        }
                    },
                    label = { Text(name, fontSize = 12.sp) }
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Parametroen editorea eta 2D Canvas marrazkia
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "${figureTypes[selectedFigureType]} Neurriak:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = PrimaryBlue
                )
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Testu-kaxak
                    Column(modifier = Modifier.weight(1f)) {
                        when (selectedFigureType) {
                            0, 1 -> { // Laukizuzena edo Triangelua
                                OutlinedTextField(
                                    value = param1,
                                    onValueChange = { param1 = it },
                                    label = { Text("Oinarria (b)") },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                OutlinedTextField(
                                    value = param2,
                                    onValueChange = { param2 = it },
                                    label = { Text("Altuera (h)") },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            2 -> { // Zirkulua
                                OutlinedTextField(
                                    value = param1,
                                    onValueChange = { param1 = it },
                                    label = { Text("Erradioa (r)") },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            3 -> { // Elipsea
                                OutlinedTextField(
                                    value = param1,
                                    onValueChange = { param1 = it },
                                    label = { Text("a (ardatzerdi handia)") },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                OutlinedTextField(
                                    value = param2,
                                    onValueChange = { param2 = it },
                                    label = { Text("b (ardatzerdi txikia)") },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }

                    // 2D Canvas marrazki dinamikoa
                    Box(
                        modifier = Modifier
                            .size(130.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFF0F172A))
                            .border(1.dp, Color(0xFF334155), RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Canvas(modifier = Modifier.size(110.dp)) {
                            val cx = size.width / 2
                            val cy = size.height / 2
                            when (selectedFigureType) {
                                0 -> { // Laukizuzena
                                    val w = (val1.toFloat() * 7f).coerceIn(30f, size.width - 20f)
                                    val h = (val2.toFloat() * 7f).coerceIn(30f, size.height - 20f)
                                    drawRect(
                                        color = Color(0xFF38BDF8).copy(alpha = 0.8f),
                                        topLeft = Offset(cx - w / 2, cy - h / 2),
                                        size = Size(w, h)
                                    )
                                    drawRect(
                                        color = Color.White,
                                        topLeft = Offset(cx - w / 2, cy - h / 2),
                                        size = Size(w, h),
                                        style = Stroke(width = 2.dp.toPx())
                                    )
                                }
                                1 -> { // Triangelua
                                    val base = (val1.toFloat() * 7f).coerceIn(30f, size.width - 20f)
                                    val h = (val2.toFloat() * 7f).coerceIn(30f, size.height - 20f)
                                    val path = Path().apply {
                                        moveTo(cx, cy - h / 2)
                                        lineTo(cx + base / 2, cy + h / 2)
                                        lineTo(cx - base / 2, cy + h / 2)
                                        close()
                                    }
                                    drawPath(path, color = Color(0xFF10B981).copy(alpha = 0.8f))
                                    drawPath(path, color = Color.White, style = Stroke(width = 2.dp.toPx()))
                                }
                                2 -> { // Zirkulua
                                    val r = (val1.toFloat() * 9f).coerceIn(15f, (size.minDimension / 2) - 10f)
                                    drawCircle(color = Color(0xFFF43F5E).copy(alpha = 0.8f), radius = r, center = Offset(cx, cy))
                                    drawCircle(color = Color.White, radius = r, center = Offset(cx, cy), style = Stroke(width = 2.dp.toPx()))
                                }
                                3 -> { // Elipsea
                                    val a = (val1.toFloat() * 6f).coerceIn(30f, size.width - 15f)
                                    val b = (val2.toFloat() * 6f).coerceIn(20f, size.height - 15f)
                                    drawOval(
                                        color = Color(0xFFF59E0B).copy(alpha = 0.8f),
                                        topLeft = Offset(cx - a / 2, cy - b / 2),
                                        size = Size(a, b)
                                    )
                                    drawOval(
                                        color = Color.White,
                                        topLeft = Offset(cx - a / 2, cy - b / 2),
                                        size = Size(a, b),
                                        style = Stroke(width = 2.dp.toPx())
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Emaitza polimorfikoak (Live Result)
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = SecondaryTeal.copy(alpha = 0.12f)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Kalkulu Polimorfikoaren Emaitza:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = SecondaryTeal
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("Azalera (Gainazala):", fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text(
                            text = "%.2f unitate²".format(azalera),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Column {
                        Text("Perimetroa (Perimetroa):", fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text(
                            text = "%.2f unitate".format(perimetroa),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = AccentAmber
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // OOP Azalpen Sakona
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = "Zergatik funtzionatzen du honek casting gabe?",
                    fontWeight = FontWeight.Bold,
                    color = AccentAmber,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "1. Liskov-en Ordezkatze Printzipioa (LSP): `Figura` abstraktua `Gainazala` eta `Perimetroa` bada, edozein azpiklase (`Zirkulua`, `Elipsea`) zuzenean erabil daiteke kontratu horren pean.\n" +
                           "2. Konpiladoreak badaki `figura.kalkulatuAzalera()` existitzen dela mota guztietan, beraz ez da behar ez `is Gainazala` ezta `as Gainazala` casting-ik ere!\n" +
                           "3. Elipsearen perimetroak Ramanujan-en hurbilketa erabiltzen du integral eliptiko konplexuak saihesteko.",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 12.sp,
                    lineHeight = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        CodeEditorView(
            code = "// Polimorfismoaren egitura nagusia\n" +
                   "val figura: GeometriaFigura = ${figureTypes[selectedFigureType]}(\"Urdina\", ...)\n\n" +
                   "// runtime-ko 'is' casting-ik gabe deitzen da:\n" +
                   "val azalera: Double = figura.kalkulatuAzalera()\n" +
                   "val perimetro: Double = figura.kalkulatuPerimetroa()",
            title = "Polimorfismoa.kt"
        )

        Spacer(modifier = Modifier.height(24.dp))

        // ==========================================
        // DATA CLASS ETA COPY() ARIKETA
        // ==========================================
        Text(
            text = "2. Kotlin Data Class & .copy()",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "Aldaezintasuna (Immutability), balio-berdintasuna eta instantzia berriak sortzea.",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(12.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Jatorrizko Ikaslea (val ikasle1):",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = PrimaryBlue
                )
                Text(
                    text = "Ikaslea(id = 101, izena = \"Ane\", nota = $ikasleNota, taldea = \"$ikasleTaldea\")",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Nota berria kopiarako: %.1f".format(kopiaNota),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp
                )
                Slider(
                    value = kopiaNota.toFloat(),
                    onValueChange = { kopiaNota = it.toDouble() },
                    valueRange = 0f..10f,
                    steps = 19
                )

                Spacer(modifier = Modifier.height(8.dp))

                val isEquals = (ikasleNota == kopiaNota)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "ikasle1 == ikasleKopia (Eduki berdina?):",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = if (isEquals) "TRUE (nota bera dute)" else "FALSE (nota ezberdina)",
                            fontWeight = FontWeight.Bold,
                            color = if (isEquals) Color(0xFF10B981) else Color(0xFFEF4444),
                            fontSize = 14.sp
                        )
                    }
                    Column {
                        Text(
                            text = "ikasle1 === ikasleKopia (Memorian berbera?):",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "FALSE (objektu berria sortu da)",
                            fontWeight = FontWeight.Bold,
                            color = AccentAmber,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        CodeEditorView(
            code = "data class Ikaslea(val id: Int, val izena: String, val nota: Double)\n\n" +
                   "val ikasle1 = Ikaslea(101, \"Ane\", $ikasleNota)\n" +
                   "// .copy()-k objektu berri bat sortzen du aldaketarekin:\n" +
                   "val ikasleKopia = ikasle1.copy(nota = %.1f)\n\n".format(kopiaNota) +
                   "// Data class-ek equals(), hashCode(), toString() eta copy()\n" +
                   "// metodoak automatikoki sortzen dituzte doan!",
            title = "DataClassDemo.kt"
        )
    }
}

// =========================================================================
// 2. JETPACK COMPOSE LIVE LAB (Layouts, Padding eta Modifier Ordena)
// =========================================================================
@Composable
fun ComposeLivePlayground() {
    var layoutType by rememberSaveable { mutableIntStateOf(1) } // 0: Column, 1: Row, 2: Box
    var paddingValue by rememberSaveable { mutableFloatStateOf(16f) }
    var spacingValue by rememberSaveable { mutableFloatStateOf(12f) }
    var selectedArrangementIndex by rememberSaveable { mutableIntStateOf(0) }
    var useWeights by rememberSaveable { mutableStateOf(false) }

    // Modifier ordenaren demoa
    var modifierOrderMode by rememberSaveable { mutableIntStateOf(0) } // 0: Background -> Padding, 1: Padding -> Background

    val arrangementOptions = listOf("spacedBy", "SpaceBetween", "SpaceEvenly", "Center", "SpaceAround")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Compose Osagaien Laborategia",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "Aldatu parametroak eta ikusi nola birkonposatzen den interfazea denbora errealean.",
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Layout hautatzailea
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("Column (Bertikala)", "Row (Horizontala)", "Box (Gainjarria)").forEachIndexed { index, name ->
                FilterChip(
                    selected = layoutType == index,
                    onClick = { layoutType = index },
                    label = { Text(name, fontSize = 11.sp) }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Betegarriaren (Padding) Slider-a
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Modifier.padding = ${paddingValue.toInt()}.dp",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "Barruko marjina dinamikoa",
                        fontSize = 11.sp,
                        color = SecondaryTeal,
                        fontWeight = FontWeight.Medium
                    )
                }
                Slider(
                    value = paddingValue,
                    onValueChange = { paddingValue = it },
                    valueRange = 0f..50f
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Tartea (Spacing / Arrangement.spacedBy)
                if (layoutType != 2) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Arrangement.spacedBy = ${spacingValue.toInt()}.dp",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp
                        )
                        Text(
                            text = if (selectedArrangementIndex == 0) "✅ Aktibatuta" else "Mugitzean aktibatzen da",
                            fontSize = 11.sp,
                            color = if (selectedArrangementIndex == 0) Color(0xFF10B981) else AccentAmber,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Slider(
                        value = spacingValue,
                        onValueChange = {
                            spacingValue = it
                            selectedArrangementIndex = 0 // Automatikoki 'spacedBy' aktibatzen du sliderra mugitzean!
                        },
                        valueRange = 0f..35f
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Banaketa (Arrangement) hautatzailea
        if (layoutType != 2) {
            Text(
                text = "Arrangement (Banaketa modua):",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                arrangementOptions.forEachIndexed { idx, name ->
                    FilterChip(
                        selected = selectedArrangementIndex == idx,
                        onClick = { selectedArrangementIndex = idx },
                        label = {
                            Text(
                                text = if (idx == 0) "spacedBy (${spacingValue.toInt()}dp)" else name,
                                fontSize = 11.sp,
                                fontWeight = if (selectedArrangementIndex == idx) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Weight switch
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Aplikatu Modifier.weight(1f) elementuei:",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
                Switch(
                    checked = useWeights,
                    onCheckedChange = { useWeights = it }
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // ZUZENEKO ERRENDATZEA (LIVE PREVIEW)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Zuzeneko Errendatzea (Live Preview):",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Text(
                text = "Padding: ${paddingValue.toInt()}dp | ${arrangementOptions[selectedArrangementIndex]}",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF38BDF8)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Kanpoko edukiontzia
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF0F172A))
                .border(1.5.dp, Color(0xFF334155), RoundedCornerShape(16.dp))
                .padding(8.dp)
        ) {
            // Barruko kutxa: hemen aplikatzen da 'padding(paddingValue.dp)' modu ikusgarrian!
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF1E293B))
                    .border(1.5.dp, Color(0xFF38BDF8).copy(alpha = 0.6f), RoundedCornerShape(10.dp))
                    .padding(paddingValue.dp)
            ) {
                val rowArrangement = when (selectedArrangementIndex) {
                    0 -> Arrangement.spacedBy(spacingValue.dp, Alignment.CenterHorizontally)
                    1 -> Arrangement.SpaceBetween
                    2 -> Arrangement.SpaceEvenly
                    3 -> Arrangement.Center
                    4 -> Arrangement.SpaceAround
                    else -> Arrangement.spacedBy(spacingValue.dp, Alignment.CenterHorizontally)
                }

                val colArrangement = when (selectedArrangementIndex) {
                    0 -> Arrangement.spacedBy(spacingValue.dp, Alignment.CenterVertically)
                    1 -> Arrangement.SpaceBetween
                    2 -> Arrangement.SpaceEvenly
                    3 -> Arrangement.Center
                    4 -> Arrangement.SpaceAround
                    else -> Arrangement.spacedBy(spacingValue.dp, Alignment.CenterVertically)
                }

                when (layoutType) {
                    0 -> { // Column
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = colArrangement,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            SampleBadge("1. Elem", Color(0xFF3B82F6), modifier = if (useWeights) Modifier.weight(1f).fillMaxWidth() else Modifier)
                            SampleBadge("2. Elem", Color(0xFF10B981), modifier = if (useWeights) Modifier.weight(1f).fillMaxWidth() else Modifier)
                            SampleBadge("3. Elem", Color(0xFFF59E0B), modifier = if (useWeights) Modifier.weight(1f).fillMaxWidth() else Modifier)
                        }
                    }
                    1 -> { // Row
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = rowArrangement,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            SampleBadge("1. Elem", Color(0xFF3B82F6), modifier = if (useWeights) Modifier.weight(1f) else Modifier)
                            SampleBadge("2. Elem", Color(0xFF10B981), modifier = if (useWeights) Modifier.weight(1f) else Modifier)
                            SampleBadge("3. Elem", Color(0xFFF59E0B), modifier = if (useWeights) Modifier.weight(1f) else Modifier)
                        }
                    }
                    2 -> { // Box
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(130.dp)
                                    .clip(RoundedCornerShape(14.dp))
                                    .background(Color(0xFF3B82F6).copy(alpha = 0.35f))
                            )
                            Box(
                                modifier = Modifier
                                    .size(90.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color(0xFF10B981).copy(alpha = 0.65f))
                            )
                            SampleBadge("Z-Index Gainjarria", Color(0xFFF59E0B))
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // SORTUTAKO KOTLIN COMPOSE KODEA DINAMIKOKI (IDE SINTAXI NABARMENTZEAREKIN)
        val arrangementCodeStr = when (selectedArrangementIndex) {
            0 -> if (layoutType == 1) "Arrangement.spacedBy(${spacingValue.toInt()}.dp, Alignment.CenterHorizontally)" else "Arrangement.spacedBy(${spacingValue.toInt()}.dp, Alignment.CenterVertically)"
            1 -> "Arrangement.SpaceBetween"
            2 -> "Arrangement.SpaceEvenly"
            3 -> "Arrangement.Center"
            4 -> "Arrangement.SpaceAround"
            else -> "Arrangement.spacedBy(${spacingValue.toInt()}.dp)"
        }

        val generatedCode = when (layoutType) {
            0 -> "Column(\n" +
                 "    modifier = Modifier\n" +
                 "        .fillMaxSize()\n" +
                 "        .padding(${paddingValue.toInt()}.dp),\n" +
                 "    verticalArrangement = $arrangementCodeStr,\n" +
                 "    horizontalAlignment = Alignment.CenterHorizontally\n" +
                 ") {\n" +
                 "    Elementua(modifier = ${if (useWeights) "Modifier.weight(1f).fillMaxWidth()" else "Modifier"})\n" +
                 "    Elementua(modifier = ${if (useWeights) "Modifier.weight(1f).fillMaxWidth()" else "Modifier"})\n" +
                 "    Elementua(modifier = ${if (useWeights) "Modifier.weight(1f).fillMaxWidth()" else "Modifier"})\n" +
                 "}"
            1 -> "Row(\n" +
                 "    modifier = Modifier\n" +
                 "        .fillMaxSize()\n" +
                 "        .padding(${paddingValue.toInt()}.dp),\n" +
                 "    horizontalArrangement = $arrangementCodeStr,\n" +
                 "    verticalAlignment = Alignment.CenterVertically\n" +
                 ") {\n" +
                 "    Elementua(modifier = ${if (useWeights) "Modifier.weight(1f)" else "Modifier"})\n" +
                 "    Elementua(modifier = ${if (useWeights) "Modifier.weight(1f)" else "Modifier"})\n" +
                 "    Elementua(modifier = ${if (useWeights) "Modifier.weight(1f)" else "Modifier"})\n" +
                 "}"
            2 -> "Box(\n" +
                 "    modifier = Modifier\n" +
                 "        .fillMaxSize()\n" +
                 "        .padding(${paddingValue.toInt()}.dp),\n" +
                 "    contentAlignment = Alignment.Center\n" +
                 ") {\n" +
                 "    AtzekoPlanoa(modifier = Modifier.size(130.dp))\n" +
                 "    ErdikoKoadroa(modifier = Modifier.size(90.dp))\n" +
                 "    GainekoTestua()\n" +
                 "}"
            else -> ""
        }

        CodeEditorView(
            code = generatedCode,
            title = "LiveLayoutGenerated.kt"
        )

        Spacer(modifier = Modifier.height(24.dp))

        // ==========================================
        // MODIFIER ORDENAREN ARIKETA INTERAKTIBOA
        // ==========================================
        Text(
            text = "Modifier Ordenaren Garrantzia (Azterketa Gakoa)",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "Compose-n Modifier deien ordenak erabat aldatzen du osagaiaren marrazketa!",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                selected = modifierOrderMode == 0,
                onClick = { modifierOrderMode = 0 },
                label = { Text("1. background -> padding", fontSize = 11.sp) }
            )
            FilterChip(
                selected = modifierOrderMode == 1,
                onClick = { modifierOrderMode = 1 },
                label = { Text("2. padding -> background", fontSize = 11.sp) }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Konparaketa kutxa
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(Color(0xFFCBD5E1), RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    if (modifierOrderMode == 0) {
                        // Background -> Padding (atzeko plano horia 100x100 osoan zabaltzen da, testua 20dp barnerantz)
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .background(Color(0xFFF59E0B), RoundedCornerShape(8.dp))
                                .padding(20.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Barruko Betegarria", color = Color.Black, fontSize = 10.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                        }
                    } else {
                        // Padding -> Background (lehenik 20dp aldentzen da, atzeko plano horia 60x60 txikituta geratzen da)
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .padding(20.dp)
                                .background(Color(0xFFF59E0B), RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Kanpoko Marjina", color = Color.Black, fontSize = 10.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                        }
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = if (modifierOrderMode == 0) "Barruko Padding Eredua" else "Kanpoko Marjina Eredua",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = PrimaryBlue
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = if (modifierOrderMode == 0)
                            "1. `background()`: Koadro osoa koloreztatzen du lehenik.\n2. `padding()`: Edukia barrurantz bultzatzen du (padding tradizionala)."
                        else
                            "1. `padding()`: Espazio librea uzten du kanpotik (margin bezala).\n2. `background()`: Barruko eremu murriztua bakarrik koloreztatzen du!",
                        fontSize = 11.sp,
                        lineHeight = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

// =========================================================================
// 3. CODELAB PRAKTIKAK (Gelan Landutako Ariketak)
// =========================================================================
@Composable
fun CodelabsPlayground() {
    var selectedExercise by rememberSaveable { mutableIntStateOf(0) }
    val exercises = listOf("Cuadrante (2x2)", "Task Manager", "Happy Birthday")

    // Cuadrante pisuak
    var weightQ1 by rememberSaveable { mutableFloatStateOf(1f) }

    // Task Manager egoera
    var isTaskCompleted by rememberSaveable { mutableStateOf(true) }

    // Happy Birthday egoera
    var fontSizeVal by rememberSaveable { mutableFloatStateOf(48f) }
    var useLineHeight by rememberSaveable { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "3. Codelab Praktikak (Gelan Landutakoak)",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "1. Ebaluaketako ariketen interaktibitatea eta diseinu-teknikak.",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Ariketa hautatzailea
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            exercises.forEachIndexed { index, name ->
                FilterChip(
                    selected = selectedExercise == index,
                    onClick = { selectedExercise = index },
                    label = { Text(name, fontSize = 12.sp) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (selectedExercise) {
            0 -> { // CUADRANTE DE COMPOSE
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Compose Quadrant (2x2 Pisuen Banaketa)",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = PrimaryBlue
                        )
                        Text(
                            text = "2 Row eta 2 Column erabiliz `Modifier.weight(1f)` aplikatzen zaie pantaila 4 zatitan banatzeko.",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "1. Koadrantearen Pisua (Weight): %.1f".format(weightQ1),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp
                        )
                        Slider(
                            value = weightQ1,
                            onValueChange = { weightQ1 = it },
                            valueRange = 0.5f..2.5f,
                            steps = 3
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // 2x2 Koadrante Live Preview
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .clip(RoundedCornerShape(12.dp))
                        ) {
                            Column(modifier = Modifier.fillMaxSize()) {
                                Row(modifier = Modifier.weight(1f)) {
                                    Box(
                                        modifier = Modifier
                                            .weight(weightQ1)
                                            .fillMaxHeight()
                                            .background(Color(0xFFEADDFF))
                                            .padding(12.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text("1. Text\n(w=%.1f)".format(weightQ1), fontWeight = FontWeight.Bold, fontSize = 11.sp, textAlign = TextAlign.Center)
                                    }
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxHeight()
                                            .background(Color(0xFFD0BCFF))
                                            .padding(12.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text("2. Image\n(w=1.0)", fontWeight = FontWeight.Bold, fontSize = 11.sp, textAlign = TextAlign.Center)
                                    }
                                }
                                Row(modifier = Modifier.weight(1f)) {
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxHeight()
                                            .background(Color(0xFFB69DF8))
                                            .padding(12.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text("3. Row\n(w=1.0)", fontWeight = FontWeight.Bold, fontSize = 11.sp, textAlign = TextAlign.Center)
                                    }
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxHeight()
                                            .background(Color(0xFFF6EDFF))
                                            .padding(12.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text("4. Column\n(w=1.0)", fontWeight = FontWeight.Bold, fontSize = 11.sp, textAlign = TextAlign.Center)
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                CodeEditorView(
                    code = "Column(modifier = Modifier.fillMaxSize()) {\n" +
                           "    Row(modifier = Modifier.weight(1f)) {\n" +
                           "        Box(modifier = Modifier.weight(%.1ff).background(Color1))\n".format(weightQ1) +
                           "        Box(modifier = Modifier.weight(1f).background(Color2))\n" +
                           "    }\n" +
                           "    Row(modifier = Modifier.weight(1f)) {\n" +
                           "        Box(modifier = Modifier.weight(1f).background(Color3))\n" +
                           "        Box(modifier = Modifier.weight(1f).background(Color4))\n" +
                           "    }\n" +
                           "}",
                    title = "ComposeQuadrant.kt"
                )
            }
            1 -> { // TASK MANAGER
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Task Manager (Zeregin Kudeatzailea)",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = PrimaryBlue
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        // Preview kutxa
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFF8FAFC))
                                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(12.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    imageVector = if (isTaskCompleted) Icons.Default.CheckCircle else Icons.Default.TaskAlt,
                                    contentDescription = null,
                                    tint = if (isTaskCompleted) Color(0xFF10B981) else Color(0xFF94A3B8),
                                    modifier = Modifier.size(54.dp)
                                )
                                Text(
                                    text = if (isTaskCompleted) "All tasks completed" else "Tasks in progress...",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(top = 16.dp, bottom = 4.dp),
                                    color = Color(0xFF0F172A)
                                )
                                Text(
                                    text = if (isTaskCompleted) "Nice work!" else "3 pending tasks remaining",
                                    fontSize = 13.sp,
                                    color = Color(0xFF64748B)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = { isTaskCompleted = !isTaskCompleted },
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
                        ) {
                            Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(if (isTaskCompleted) "Egoera aldatu (Lanetan)" else "Egoera aldatu (Bukatuta)")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                CodeEditorView(
                    code = "Column(\n" +
                           "    modifier = Modifier.fillMaxSize(),\n" +
                           "    verticalArrangement = Arrangement.Center,\n" +
                           "    horizontalAlignment = Alignment.CenterHorizontally\n" +
                           ") {\n" +
                           "    Image(painter = painterResource(id = R.drawable.ic_task_completed), contentDescription = null)\n" +
                           "    Text(\"All tasks completed\", fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 24.dp, bottom = 8.dp))\n" +
                           "    Text(\"Nice work!\", fontSize = 16.sp)\n" +
                           "}",
                    title = "TaskManager.kt"
                )
            }
            2 -> { // HAPPY BIRTHDAY (TEXT, FONTSIZE, LINEHEIGHT)
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Happy Birthday (Testu Konposagarriak)",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = PrimaryBlue
                        )
                        Text(
                            text = "Letra handietan (sp handia), 'lineHeight' zehaztea derrigorrezkoa da lerroak elkar ez zapaltzeko.",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text("Letra tamaina (fontSize): ${fontSizeVal.toInt()}.sp", fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                        Slider(
                            value = fontSizeVal,
                            onValueChange = { fontSizeVal = it },
                            valueRange = 24f..72f
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("lineHeight egokitua aplikatu (${(fontSizeVal * 1.15f).toInt()}.sp):", fontSize = 12.sp)
                            Switch(checked = useLineHeight, onCheckedChange = { useLineHeight = it })
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Preview kutxa
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFFEF3C7))
                                .padding(16.dp)
                        ) {
                            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
                                Text(
                                    text = "Happy\nBirthday!",
                                    fontSize = fontSizeVal.sp,
                                    lineHeight = if (useLineHeight) (fontSizeVal * 1.15f).sp else 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF78350F)
                                )
                                Text(
                                    text = "- From Emma",
                                    fontSize = 16.sp,
                                    color = Color(0xFF92400E),
                                    modifier = Modifier.align(Alignment.End)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                CodeEditorView(
                    code = "Text(\n" +
                           "    text = \"Happy\\nBirthday!\",\n" +
                           "    fontSize = ${fontSizeVal.toInt()}.sp,\n" +
                           "    lineHeight = ${(fontSizeVal * 1.15f).toInt()}.sp, // Lerroak elkar ez zapaltzeko!\n" +
                           "    textAlign = TextAlign.Center\n" +
                           ")",
                    title = "HappyBirthday.kt"
                )
            }
        }
    }
}

// =========================================================================
// 4. EGOERA (STATE) ETA ANIMAZIOAK (Codelab 3)
// =========================================================================
@Composable
fun StateAndAnimationsPlayground() {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    // Spring malguki parametroak
    var dampingChoice by rememberSaveable { mutableIntStateOf(1) } // 0: High, 1: Medium, 2: NoBouncy
    var stiffnessChoice by rememberSaveable { mutableIntStateOf(1) } // 0: Low, 1: Medium, 2: High

    val dampingRatios = listOf(
        "HighBouncy (0.2f)" to Spring.DampingRatioHighBouncy,
        "MediumBouncy (0.5f)" to Spring.DampingRatioMediumBouncy,
        "NoBouncy (1.0f)" to Spring.DampingRatioNoBouncy
    )
    val stiffnesses = listOf(
        "Low (Geldia)" to Spring.StiffnessLow,
        "Medium (Ertaina)" to Spring.StiffnessMedium,
        "High (Azkarragoa)" to Spring.StiffnessHigh
    )

    // Remember vs RememberSaveable simulagailua
    var rememberCount by remember { mutableIntStateOf(0) }
    var saveableCount by rememberSaveable { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "4. Egoera & Animazioak (Codelab 3)",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "animateContentSize malguki-efektuekin eta rememberSaveable-ren portaera.",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(14.dp))

        // MALGUKI ANIMAZIOA
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "1. animateContentSize + spring() Fisika",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = PrimaryBlue
                )
                Text(
                    text = "Aukeratu errebotea (dampingRatio) eta sendotasuna (stiffness), eta sakatu beheko txartelean trantsizioa ikusteko:",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text("DampingRatio (Errebote Maila):", fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    dampingRatios.forEachIndexed { idx, (label, _) ->
                        FilterChip(
                            selected = dampingChoice == idx,
                            onClick = { dampingChoice = idx },
                            label = { Text(label, fontSize = 10.sp) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text("Stiffness (Abiadura / Zurruntasuna):", fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    stiffnesses.forEachIndexed { idx, (label, _) ->
                        FilterChip(
                            selected = stiffnessChoice == idx,
                            onClick = { stiffnessChoice = idx },
                            label = { Text(label, fontSize = 10.sp) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Animazio txartela interaktiboa
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize(
                            animationSpec = spring(
                                dampingRatio = dampingRatios[dampingChoice].second,
                                stiffness = stiffnesses[stiffnessChoice].second
                            )
                        )
                        .clickable { isExpanded = !isExpanded }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Jetpack Compose Codelab 3 Txartela",
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            IconButton(onClick = { isExpanded = !isExpanded }) {
                                Icon(
                                    imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                    contentDescription = null
                                )
                            }
                        }

                        if (isExpanded) {
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Oraintxe bertan ikusten duzun mugimendu elastikoa Jetpack Compose-ren `animateContentSize` eta `spring` fisikaren bidez gauzatu da!\n\n" +
                                       "• DampingRatio: ${dampingRatios[dampingChoice].first}\n" +
                                       "• Stiffness: ${stiffnesses[stiffnessChoice].first}",
                                fontSize = 13.sp,
                                lineHeight = 19.sp,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        CodeEditorView(
            code = "Card(\n" +
                   "    modifier = Modifier\n" +
                   "        .fillMaxWidth()\n" +
                   "        .animateContentSize(\n" +
                   "            animationSpec = spring(\n" +
                   "                dampingRatio = ${dampingRatios[dampingChoice].first.substringBefore(" ")},\n" +
                   "                stiffness = ${stiffnesses[stiffnessChoice].first.substringBefore(" ")}\n" +
                   "            )\n" +
                   "        )\n" +
                   ")",
            title = "SpringAnimation.kt"
        )

        Spacer(modifier = Modifier.height(24.dp))

        // REMEMBER VS REMEMBERSAVEABLE
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "2. remember vs rememberSaveable",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = PrimaryBlue
                )
                Text(
                    text = "Pantaila biratzean (konfigurazio aldaketan), zer gertatzen da aldagai bakoitzarekin?",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFEF4444).copy(alpha = 0.1f)),
                        modifier = Modifier.weight(1f)
                    ) {
                        Column(modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("remember", fontWeight = FontWeight.Bold, color = Color(0xFFEF4444), fontSize = 13.sp)
                            Text("$rememberCount", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                            Button(
                                onClick = { rememberCount++ },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEF4444)),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("+1")
                            }
                        }
                    }

                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF10B981).copy(alpha = 0.1f)),
                        modifier = Modifier.weight(1f)
                    ) {
                        Column(modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("rememberSaveable", fontWeight = FontWeight.Bold, color = Color(0xFF10B981), fontSize = 13.sp)
                            Text("$saveableCount", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                            Button(
                                onClick = { saveableCount++ },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B981)),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("+1")
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedButton(
                    onClick = {
                        // Simulatu gailuaren biraketa: remember berrezarri egiten da 0ra, saveable mantendu
                        rememberCount = 0
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.ScreenRotation, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Simulatu Pantaila Biraketa (Recreate Activity)")
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Ikusten? 'Simulatu' sakatzean `remember` 0ra itzultzen da (galdu egiten da), eta `rememberSaveable`-k bere balioari eusten dio Bundle barruan gordeta dagoelako!",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 16.sp
                )
            }
        }
    }
}

@Composable
fun SampleBadge(text: String, color: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp
        )
    }
}
