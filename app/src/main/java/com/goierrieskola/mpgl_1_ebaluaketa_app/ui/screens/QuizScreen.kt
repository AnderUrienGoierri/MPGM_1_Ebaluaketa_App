package com.goierrieskola.mpgl_1_ebaluaketa_app.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import com.goierrieskola.mpgl_1_ebaluaketa_app.data.QuizRepository
import com.goierrieskola.mpgl_1_ebaluaketa_app.data.QuizStatsManager
import com.goierrieskola.mpgl_1_ebaluaketa_app.model.QuizAnswerRecord
import com.goierrieskola.mpgl_1_ebaluaketa_app.model.QuizQuestion
import com.goierrieskola.mpgl_1_ebaluaketa_app.ui.theme.AccentAmber
import com.goierrieskola.mpgl_1_ebaluaketa_app.ui.theme.CorrectGreen
import com.goierrieskola.mpgl_1_ebaluaketa_app.ui.theme.PrimaryBlue
import com.goierrieskola.mpgl_1_ebaluaketa_app.ui.theme.SecondaryTeal
import com.goierrieskola.mpgl_1_ebaluaketa_app.ui.theme.WrongRed

@Composable
fun QuizScreen(
    onQuizStateChanged: (Boolean) -> Unit = {},
    modifier: Modifier = Modifier
) {
    // 0: Azterketa Modua (20 galdera ausaz)
    // 1: Entrenamendu Osoa (50 galdera)
    // 2: Fallatutakoak Errepikatu
    var examMode by rememberSaveable { mutableIntStateOf(0) }
    var shuffleSeed by rememberSaveable { mutableIntStateOf(0) }

    var currentQuestionIndex by rememberSaveable { mutableIntStateOf(0) }
    var score by rememberSaveable { mutableIntStateOf(0) }
    var selectedOptionIndex by rememberSaveable { mutableIntStateOf(-1) }
    var hasAnswered by rememberSaveable { mutableStateOf(false) }
    var quizCompleted by rememberSaveable { mutableStateOf(false) }

    // Denbora neurtzeko kronometroa (segundotan)
    var elapsedSeconds by rememberSaveable { mutableIntStateOf(0) }

    // Modua aldatzeko berrespena (Azterketa 20 vs Osoa 50)
    var pendingModeSwitch by rememberSaveable { mutableIntStateOf(-1) }
    var showModeSwitchDialog by rememberSaveable { mutableStateOf(false) }

    // Erantzun guztien erregistroa (berrikusteko eta estatistiketarako)
    val sessionAnswerRecords = remember { mutableStateListOf<QuizAnswerRecord>() }

    // Egoeraren kontrolak
    var isReviewingFailed by rememberSaveable { mutableStateOf(false) }
    var showStatsDialog by rememberSaveable { mutableStateOf(false) }
    var showInternalExitDialog by rememberSaveable { mutableStateOf(false) }

    // Errepikatzeko hautatutako galdera pertsonalizatuak (fallatutakoak)
    var customQuestionsToRetry by remember { mutableStateOf<List<QuizQuestion>?>(null) }

    // Test-eko galdera aktiboak
    val questions = remember(examMode, shuffleSeed, customQuestionsToRetry) {
        if (customQuestionsToRetry != null && customQuestionsToRetry!!.isNotEmpty()) {
            customQuestionsToRetry!!.map { it.toShuffled() }
        } else if (examMode == 0) {
            QuizRepository.getExamQuestions(count = 20)
        } else {
            QuizRepository.getAllQuestionsShuffled()
        }
    }

    val isQuizActive = !quizCompleted && !isReviewingFailed && questions.isNotEmpty()

    // Kronometroaren coroutine-a: test-a aktibo dagoen bitartean segundoro handitzen da
    LaunchedEffect(isQuizActive) {
        while (isQuizActive) {
            delay(1000L)
            elapsedSeconds++
        }
    }

    // Gurasoari (MainActivity) jakinarazi test-a martxan dagoen
    LaunchedEffect(isQuizActive) {
        onQuizStateChanged(isQuizActive)
    }

    // Android-en atzera botoia kudeatu test-a martxan dagoenean
    BackHandler(enabled = isQuizActive) {
        showInternalExitDialog = true
    }

    fun restartQuiz(mode: Int = examMode, customList: List<QuizQuestion>? = null) {
        examMode = mode
        customQuestionsToRetry = customList
        shuffleSeed++
        currentQuestionIndex = 0
        score = 0
        selectedOptionIndex = -1
        hasAnswered = false
        quizCompleted = false
        isReviewingFailed = false
        sessionAnswerRecords.clear()
        elapsedSeconds = 0
    }

    fun formatDuration(totalSeconds: Int): String {
        val m = totalSeconds / 60
        val s = totalSeconds % 60
        return "%02d:%02d".format(m, s)
    }

    // IRTETZEKO BERRESPEN ELKARRIZKETA-KOADROA
    if (showInternalExitDialog) {
        AlertDialog(
            onDismissRequest = { showInternalExitDialog = false },
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
                    text = "Ziur zaude test-etik irten edo beste atal batera joan nahi duzula? Egindako aurrerapena galduko da.",
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showInternalExitDialog = false
                        restartQuiz()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = WrongRed)
                ) {
                    Text("Irten eta Utzi", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { showInternalExitDialog = false }) {
                    Text("Jarraitu Test-arekin")
                }
            }
        )
    }

    // TEST MODUA ALDATZEKO BERRESPEN ELKARRIZKETA-KOADROA
    if (showModeSwitchDialog) {
        val targetModeName = if (pendingModeSwitch == 0) "Azterketa (20 galdera ausaz)" else "Osoa (50 galdera)"
        AlertDialog(
            onDismissRequest = {
                showModeSwitchDialog = false
                pendingModeSwitch = -1
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Shuffle,
                    contentDescription = null,
                    tint = PrimaryBlue,
                    modifier = Modifier.size(32.dp)
                )
            },
            title = {
                Text(
                    text = "Test modua aldatu nahi duzu?",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
            },
            text = {
                Text(
                    text = "Uneko test-a martxan dago (${formatDuration(elapsedSeconds)} daramazu). '$targetModeName' modura aldatzen baduzu, uneko proba bukatutzat emango da eta proba berria hasiko da zerotik.",
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        val target = pendingModeSwitch
                        showModeSwitchDialog = false
                        pendingModeSwitch = -1
                        restartQuiz(mode = target)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
                ) {
                    Text("Aldatu eta Hasi Berria", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = {
                        showModeSwitchDialog = false
                        pendingModeSwitch = -1
                    }
                ) {
                    Text("Jarraitu Unekoarekin")
                }
            }
        )
    }

    // ESTATISTIKA OROKORREN MODALA
    if (showStatsDialog) {
        QuizStatsDialog(
            onDismiss = { showStatsDialog = false },
            onRetryAllFailed = {
                val failed = QuizStatsManager.failedQuestionsList.toList()
                if (failed.isNotEmpty()) {
                    showStatsDialog = false
                    restartQuiz(mode = 2, customList = failed)
                }
            }
        )
    }

    // 1. IKUSPEGIA: FALLATUTAKO GALDERAK BERRIKUSTEKO PANTAILA
    if (isReviewingFailed) {
        val failedList = sessionAnswerRecords.filter { !it.isCorrect }
        QuizReviewView(
            failedRecords = failedList,
            onBackToResults = { isReviewingFailed = false },
            onRetryOnlyFailed = {
                val failedQuestions = failedList.map { it.question }
                restartQuiz(mode = 2, customList = failedQuestions)
            },
            modifier = modifier
        )
    }
    // 2. IKUSPEGIA: TEST-AREN AMAIERAKO EMAITZAK ETA ESTATISTIKAK
    else if (quizCompleted) {
        val failedList = sessionAnswerRecords.filter { !it.isCorrect }

        QuizResultsView(
            score = score,
            total = questions.size,
            examMode = examMode,
            failedCount = failedList.size,
            elapsedSeconds = elapsedSeconds,
            onReviewFailed = { isReviewingFailed = true },
            onRetryFailedOnly = {
                val failedQuestions = failedList.map { it.question }
                restartQuiz(mode = 2, customList = failedQuestions)
            },
            onRestart = { restartQuiz(mode = 0, customList = null) },
            onOpenStats = { showStatsDialog = true },
            modifier = modifier
        )
    }
    // 3. IKUSPEGIA: TEST-A EXEKUTATZEN (GALDERAK ETA AUKERAK)
    else {
        val currentQuestion = questions.getOrElse(currentQuestionIndex) { questions.first() }
        val progress = (currentQuestionIndex + 1).toFloat() / questions.size.toFloat()
        val testUnderway = sessionAnswerRecords.isNotEmpty() || hasAnswered || currentQuestionIndex > 0 || elapsedSeconds > 2

        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Goiko Kontrolak: Modua + Estatistika Botoia
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    FilterChip(
                        selected = examMode == 0 && customQuestionsToRetry == null,
                        onClick = {
                            if (examMode != 0 || customQuestionsToRetry != null) {
                                if (testUnderway) {
                                    pendingModeSwitch = 0
                                    showModeSwitchDialog = true
                                } else {
                                    restartQuiz(mode = 0)
                                }
                            }
                        },
                        label = { Text("Azterketa (20)") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = PrimaryBlue,
                            selectedLabelColor = Color.White
                        )
                    )

                    FilterChip(
                        selected = examMode == 1 && customQuestionsToRetry == null,
                        onClick = {
                            if (examMode != 1 || customQuestionsToRetry != null) {
                                if (testUnderway) {
                                    pendingModeSwitch = 1
                                    showModeSwitchDialog = true
                                } else {
                                    restartQuiz(mode = 1)
                                }
                            }
                        },
                        label = { Text("Osoa (50)") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = PrimaryBlue,
                            selectedLabelColor = Color.White
                        )
                    )
                }

                // Estatistikak ikusteko botoia
                OutlinedButton(
                    onClick = { showStatsDialog = true },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(imageVector = Icons.Default.BarChart, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Estatistikak", fontSize = 12.sp)
                }
            }

            if (customQuestionsToRetry != null) {
                Spacer(modifier = Modifier.height(6.dp))
                Card(
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = AccentAmber.copy(alpha = 0.15f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.Replay, contentDescription = null, tint = AccentAmber, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Fallatutako galderak errepikatzen (${questions.size} galdera)",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Aurrerapen barra, KRONOMETROA eta zenbatzailea
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Galdera: ${currentQuestionIndex + 1} / ${questions.size}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = PrimaryBlue
                )

                // KRONOMETRO BIZIA (Live Timer)
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(PrimaryBlue.copy(alpha = 0.12f))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Timer,
                        contentDescription = "Denbora",
                        tint = PrimaryBlue,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = formatDuration(elapsedSeconds),
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        fontFamily = FontFamily.Monospace,
                        color = PrimaryBlue
                    )
                }

                Text(
                    text = "Asmatuak: $score",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = CorrectGreen
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = PrimaryBlue,
                trackColor = PrimaryBlue.copy(alpha = 0.15f)
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Gaiko etiketa eta Irten / Nahastu botoiak
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(PrimaryBlue.copy(alpha = 0.1f))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = currentQuestion.topicTitle,
                        color = PrimaryBlue,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Test-etik irteteko botoi esplizitua
                    IconButton(
                        onClick = { showInternalExitDialog = true },
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Irten Test-etik",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Galderaren testua
            Text(
                text = currentQuestion.question,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(18.dp))

            // 4 Erantzun Aukerak (A, B, C, D ausazko posizioetan, luzera orekatuak)
            currentQuestion.options.forEachIndexed { index, optionText ->
                OptionCard(
                    text = optionText,
                    index = index,
                    isSelected = selectedOptionIndex == index,
                    isCorrect = index == currentQuestion.correctIndex,
                    hasAnswered = hasAnswered,
                    onClick = {
                        if (!hasAnswered) {
                            selectedOptionIndex = index
                            hasAnswered = true
                            val isCorrect = index == currentQuestion.correctIndex
                            if (isCorrect) {
                                score++
                            }
                            // Erantzuna erregistratu gero berrikusi ahal izateko
                            sessionAnswerRecords.add(
                                QuizAnswerRecord(
                                    question = currentQuestion,
                                    selectedOptionIndex = index,
                                    isCorrect = isCorrect
                                )
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

            // Azalpen txartela erantzun ondoren
            AnimatedVisibility(visible = hasAnswered, enter = fadeIn(), exit = fadeOut()) {
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (selectedOptionIndex == currentQuestion.correctIndex) {
                                CorrectGreen.copy(alpha = 0.12f)
                            } else {
                                WrongRed.copy(alpha = 0.1f)
                            }
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = if (selectedOptionIndex == currentQuestion.correctIndex) {
                                        Icons.Default.CheckCircle
                                    } else {
                                        Icons.AutoMirrored.Filled.HelpOutline
                                    },
                                    contentDescription = null,
                                    tint = if (selectedOptionIndex == currentQuestion.correctIndex) {
                                        CorrectGreen
                                    } else {
                                        WrongRed
                                    },
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = if (selectedOptionIndex == currentQuestion.correctIndex) {
                                        "Zorionak! Erantzun zuzena!"
                                    } else {
                                        "Ez da zuzena. Erantzun zuzena: ${listOf("A", "B", "C", "D").getOrElse(currentQuestion.correctIndex) { "" }}"
                                    },
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = currentQuestion.explanation,
                                fontSize = 13.sp,
                                lineHeight = 18.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (currentQuestionIndex < questions.size - 1) {
                                currentQuestionIndex++
                                selectedOptionIndex = -1
                                hasAnswered = false
                            } else {
                                // Test-a amaitu da: estatistikak gorde
                                QuizStatsManager.recordQuizSession(
                                    score = score,
                                    total = questions.size,
                                    records = sessionAnswerRecords
                                )
                                quizCompleted = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = if (currentQuestionIndex < questions.size - 1) {
                                "Hurrengo Galdera →"
                            } else {
                                "Ikusi Emaitzak eta Estatistikak 🏆"
                            },
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

/**
 * 2. IKUSPEGIA: TEST-AREN AMAIERAKO EMAITZAK, ESTATISTIKAK ETA AUKERAK
 */
@Composable
fun QuizResultsView(
    score: Int,
    total: Int,
    examMode: Int,
    failedCount: Int,
    elapsedSeconds: Int = 0,
    onReviewFailed: () -> Unit,
    onRetryFailedOnly: () -> Unit,
    onRestart: () -> Unit,
    onOpenStats: () -> Unit,
    modifier: Modifier = Modifier
) {
    val percentage = if (total > 0) (score.toDouble() / total.toDouble() * 100).toInt() else 0
    val durationFormatted = "%02d:%02d".format(elapsedSeconds / 60, elapsedSeconds % 60)

    val (badgeText, feedback, color) = when {
        percentage >= 90 -> Triple("BIKAIN! 🌟", "Maila aparta erakutsi duzu! Gai guztiak erabat menperatzen dituzu azterketarako.", CorrectGreen)
        percentage >= 70 -> Triple("OSO ONDO! 👏", "Oso emaitza ona! Berrikusi akats txikiak eta nota gorena lortuko duzu.", PrimaryBlue)
        percentage >= 50 -> Triple("GAINDITUTA 👍", "Oinarriak badituzu, baina gomendagarria da Teoria eta Txukuntze-orria beste behin irakurtzea.", AccentAmber)
        else -> Triple("BERRIKUSI BEHARREKOA ⚠️", "Ez kezkatu! Berrikusi fallatutako galderak beheko botoiarekin eta ikasi zergatia.", WrongRed)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.EmojiEvents,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(68.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = badgeText,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Emaitza: $score / $total ($percentage%) • ⏱️ $durationFormatted",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(14.dp))

        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.08f)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = feedback,
                fontSize = 13.sp,
                lineHeight = 19.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(14.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ESTATISTIKA OROKORREN LABURPENA (Iraupena, Media, Gorena, Test kopurua)
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Zure Estatistika Metatuak",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = PrimaryBlue
                    )
                    TextButton(onClick = onOpenStats) {
                        Text("Xehetasun guztiak →", fontSize = 12.sp)
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    StatMiniBadge(
                        label = "Iraupena",
                        value = durationFormatted,
                        color = PrimaryBlue
                    )
                    StatMiniBadge(
                        label = "Media",
                        value = "%.1f%%".format(QuizStatsManager.getAveragePercentage()),
                        color = PrimaryBlue
                    )
                    StatMiniBadge(
                        label = "Gorena",
                        value = "${QuizStatsManager.highestScorePercentage.intValue}%",
                        color = CorrectGreen
                    )
                    StatMiniBadge(
                        label = "Testak",
                        value = "${QuizStatsManager.totalTestsCompleted.intValue}",
                        color = AccentAmber
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // AKATSAK EGON BADIRA: BOTOI BEREZIAK
        if (failedCount > 0) {
            Button(
                onClick = onReviewFailed,
                colors = ButtonDefaults.buttonColors(containerColor = WrongRed),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(imageVector = Icons.Default.ErrorOutline, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "🔍 Berrikusi Fallatutako Galderak ($failedCount akats)",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = onRetryFailedOnly,
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.5.dp, WrongRed),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(imageVector = Icons.Default.Replay, contentDescription = null, tint = WrongRed, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Errepikatu Fallatutakoak Soilik ($failedCount)",
                    color = WrongRed,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
        }

        // BESTE TEST OSO BAT EGIN
        Button(
            onClick = onRestart,
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Beste Test Bat Egin (Galderak Nahastuz)", fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

/**
 * 1. IKUSPEGIA: FALLATUTAKO GALDEREN BERRIKUSPENA
 */
@Composable
fun QuizReviewView(
    failedRecords: List<QuizAnswerRecord>,
    onBackToResults: () -> Unit,
    onRetryOnlyFailed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Goiburukoa Atzera Botoiarekin
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackToResults) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atzera")
            }
            Spacer(modifier = Modifier.width(6.dp))
            Column {
                Text(
                    text = "Fallatutako Galderen Berrikuspena",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = WrongRed
                )
                Text(
                    text = "${failedRecords.size} galderatan huts egin duzu. Aztertu arrazoiak azterketarako:",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Akatsak dituzten galdera guztien zerrenda zehatza
        failedRecords.forEachIndexed { index, record ->
            val q = record.question
            val optionLetters = listOf("A", "B", "C", "D")

            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.5.dp, WrongRed.copy(alpha = 0.4f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    // Gaiko txapa
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(WrongRed.copy(alpha = 0.1f))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(text = q.topicTitle, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = WrongRed)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "${index + 1}. ${q.question}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // 1. ZUK AUKERATUTAKO ERANTZUN OKERRA
                    val selectedText = q.options.getOrElse(record.selectedOptionIndex) { "Aukeratu gabe" }
                    val selectedLetter = optionLetters.getOrElse(record.selectedOptionIndex) { "?" }
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = WrongRed.copy(alpha = 0.12f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "❌ Zure erantzuna ($selectedLetter): ", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = WrongRed)
                            Text(text = selectedText, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface)
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    // 2. ERANTZUN ZUZENA
                    val correctText = q.options.getOrElse(q.correctIndex) { "" }
                    val correctLetter = optionLetters.getOrElse(q.correctIndex) { "?" }
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = CorrectGreen.copy(alpha = 0.12f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "✅ Erantzun zuzena ($correctLetter): ", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = CorrectGreen)
                            Text(text = correctText, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // 3. ARRAZOIBIDE TEKNIKOA / AZALPENA
                    Text(
                        text = "Arrazoia: ${q.explanation}",
                        fontSize = 12.sp,
                        lineHeight = 17.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onRetryOnlyFailed,
            colors = ButtonDefaults.buttonColors(containerColor = WrongRed),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(imageVector = Icons.Default.Replay, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Errepikatu Galdera Hauek Orain", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = onBackToResults,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Itzuli Emaitzetara")
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

/**
 * ESTATISTIKA OROKORREN ELKARRIZKETA-KOADROA
 */
@Composable
fun QuizStatsDialog(
    onDismiss: () -> Unit,
    onRetryAllFailed: () -> Unit
) {
    val topicStats = QuizStatsManager.getTopicStats()
    val totalTests = QuizStatsManager.totalTestsCompleted.intValue
    val avgScore = QuizStatsManager.getAveragePercentage()
    val highScore = QuizStatsManager.highestScorePercentage.intValue
    val historicallyFailedCount = QuizStatsManager.failedQuestionsList.size

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.BarChart, contentDescription = null, tint = PrimaryBlue)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Zure Ikasketa Estatistikak", fontWeight = FontWeight.Bold, fontSize = 17.sp)
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                // Estatistika Orokorrak
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    StatMiniBadge(label = "Testak", value = "$totalTests", color = PrimaryBlue)
                    StatMiniBadge(label = "Media", value = "%.1f%%".format(avgScore), color = CorrectGreen)
                    StatMiniBadge(label = "Gorena", value = "$highScore%", color = AccentAmber)
                }

                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Gai Bakoitzeko Arrakasta-Tasa:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (totalTests == 0) {
                    Text(
                        text = "Oraindik ez duzu testik amaitu. Egin proba bat datuak biltzeko!",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                } else {
                    topicStats.forEach { stat ->
                        Column(modifier = Modifier.padding(vertical = 4.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = stat.topicTitle,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = "${stat.correctCount}/${stat.totalCount} (${stat.percentage}%)",
                                    fontSize = 12.sp,
                                    color = if (stat.percentage >= 70) CorrectGreen else if (stat.percentage >= 50) AccentAmber else WrongRed,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.height(3.dp))
                            LinearProgressIndicator(
                                progress = { if (stat.totalCount > 0) stat.correctCount.toFloat() / stat.totalCount.toFloat() else 0f },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(6.dp)
                                    .clip(RoundedCornerShape(3.dp)),
                                color = if (stat.percentage >= 70) CorrectGreen else if (stat.percentage >= 50) AccentAmber else WrongRed,
                                trackColor = MaterialTheme.colorScheme.surfaceVariant
                            )
                        }
                    }
                }

                if (historicallyFailedCount > 0) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = WrongRed.copy(alpha = 0.1f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(imageVector = Icons.Default.ErrorOutline, contentDescription = null, tint = WrongRed, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Guztira $historicallyFailedCount galdera ezberdinetan egin duzu huts.",
                                fontSize = 11.sp,
                                color = WrongRed
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            if (historicallyFailedCount > 0) {
                Button(
                    onClick = onRetryAllFailed,
                    colors = ButtonDefaults.buttonColors(containerColor = WrongRed)
                ) {
                    Text("Errepikatu Akatsak ($historicallyFailedCount)", fontSize = 12.sp)
                }
            } else {
                Button(onClick = onDismiss) {
                    Text("Itxi")
                }
            }
        },
        dismissButton = {
            if (historicallyFailedCount > 0) {
                OutlinedButton(onClick = onDismiss) {
                    Text("Itxi")
                }
            }
        }
    )
}

@Composable
fun StatMiniBadge(
    label: String,
    value: String,
    color: Color
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = color)
        Text(text = label, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
fun OptionCard(
    text: String,
    index: Int,
    isSelected: Boolean,
    isCorrect: Boolean,
    hasAnswered: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = when {
        !hasAnswered && isSelected -> PrimaryBlue.copy(alpha = 0.15f)
        hasAnswered && isCorrect -> CorrectGreen.copy(alpha = 0.2f)
        hasAnswered && isSelected && !isCorrect -> WrongRed.copy(alpha = 0.2f)
        else -> MaterialTheme.colorScheme.surface
    }

    val borderColor = when {
        !hasAnswered && isSelected -> PrimaryBlue
        hasAnswered && isCorrect -> CorrectGreen
        hasAnswered && isSelected && !isCorrect -> WrongRed
        else -> MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)
    }

    val optionLetters = listOf("A", "B", "C", "D")

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        border = BorderStroke(1.5.dp, borderColor),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = !hasAnswered, onClick = onClick)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(
                        if (hasAnswered && isCorrect) CorrectGreen.copy(alpha = 0.25f)
                        else if (hasAnswered && isSelected && !isCorrect) WrongRed.copy(alpha = 0.25f)
                        else borderColor.copy(alpha = 0.15f)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = optionLetters.getOrElse(index) { "?" },
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = if (hasAnswered && isCorrect) CorrectGreen
                    else if (hasAnswered && isSelected && !isCorrect) WrongRed
                    else MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = text,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
