package com.goierrieskola.mpgl_1_ebaluaketa_app.ui.components

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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.AutoFixHigh
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Visibility
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
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goierrieskola.mpgl_1_ebaluaketa_app.data.PracticalRepository
import com.goierrieskola.mpgl_1_ebaluaketa_app.model.PracticalChallenge
import com.goierrieskola.mpgl_1_ebaluaketa_app.model.PracticalChallengeType
import com.goierrieskola.mpgl_1_ebaluaketa_app.ui.theme.AccentAmber
import com.goierrieskola.mpgl_1_ebaluaketa_app.ui.theme.CorrectGreen
import com.goierrieskola.mpgl_1_ebaluaketa_app.ui.theme.PrimaryBlue
import com.goierrieskola.mpgl_1_ebaluaketa_app.ui.theme.SecondaryTeal
import com.goierrieskola.mpgl_1_ebaluaketa_app.ui.theme.WrongRed

@Composable
fun PracticalExamTrainer(modifier: Modifier = Modifier) {
    var selectedTypeFilter by rememberSaveable { mutableStateOf<String>("ALL") }
    var seed by rememberSaveable { mutableIntStateOf(0) }

    val allChallenges = remember(seed) {
        PracticalRepository.getChallengesShuffled()
    }

    val filteredChallenges = remember(selectedTypeFilter, allChallenges) {
        when (selectedTypeFilter) {
            "UNDERSTAND" -> allChallenges.filter { it.type == PracticalChallengeType.UNDERSTAND_CODE }
            "FIX" -> allChallenges.filter { it.type == PracticalChallengeType.FIX_BUG }
            "WRITE" -> allChallenges.filter { it.type == PracticalChallengeType.WRITE_CODE }
            else -> allChallenges
        }
    }

    // Map to track user answers for challenges: challengeId -> isCorrect
    val solvedMap = remember { mutableStateMapOf<Int, Boolean>() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Goiburukoa eta Azalpena
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = PrimaryBlue.copy(alpha = 0.08f)),
            border = BorderStroke(1.dp, PrimaryBlue.copy(alpha = 0.25f)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Psychology,
                        contentDescription = null,
                        tint = PrimaryBlue,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Azterketa Praktika Entrenatzailea",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryBlue
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Irakasleak azaldutako 3 erronka nagusiak landu: (1) Kodea Ulertu eta emaitza asmatu, (2) Ohiko akatsak bilatu eta zuzendu, eta (3) Codelabetako hutsuneak idatzi.",
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val correctCount = solvedMap.values.count { it }
                    Text(
                        text = "Gaitasun puntuazioa: $correctCount / ${allChallenges.size} gaindituta",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = CorrectGreen
                    )
                    OutlinedButton(
                        onClick = {
                            seed++
                            solvedMap.clear()
                        },
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Nahastu", fontSize = 12.sp)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Iragazki Txipak
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                selected = selectedTypeFilter == "ALL",
                onClick = { selectedTypeFilter = "ALL" },
                label = { Text("Guztiak (${allChallenges.size})") }
            )
            FilterChip(
                selected = selectedTypeFilter == "UNDERSTAND",
                onClick = { selectedTypeFilter = "UNDERSTAND" },
                label = { Text("1. Kodea Ulertu (5)") }
            )
            FilterChip(
                selected = selectedTypeFilter == "FIX",
                onClick = { selectedTypeFilter = "FIX" },
                label = { Text("2. Kodea Zuzendu (5)") }
            )
            FilterChip(
                selected = selectedTypeFilter == "WRITE",
                onClick = { selectedTypeFilter = "WRITE" },
                label = { Text("3. Kodea Idatzi (5)") }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Erronken Zerrenda
        filteredChallenges.forEachIndexed { index, challenge ->
            ChallengeItemCard(
                challenge = challenge,
                isSolved = solvedMap[challenge.id] == true,
                onSolved = { isSuccess ->
                    solvedMap[challenge.id] = isSuccess
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun ChallengeItemCard(
    challenge: PracticalChallenge,
    isSolved: Boolean,
    onSolved: (Boolean) -> Unit
) {
    var selectedOption by rememberSaveable(challenge.id) { mutableIntStateOf(-1) }
    var hasAnsweredChoice by rememberSaveable(challenge.id) { mutableStateOf(false) }

    var userWrittenCode by rememberSaveable(challenge.id) { mutableStateOf("") }
    var writeValidationResult by rememberSaveable(challenge.id) { mutableStateOf<Boolean?>(null) }
    var showHint by rememberSaveable(challenge.id) { mutableStateOf(false) }
    var showSolution by rememberSaveable(challenge.id) { mutableStateOf(false) }

    val typeColor = when (challenge.type) {
        PracticalChallengeType.UNDERSTAND_CODE -> PrimaryBlue
        PracticalChallengeType.FIX_BUG -> WrongRed
        PracticalChallengeType.WRITE_CODE -> SecondaryTeal
    }

    val typeIcon = when (challenge.type) {
        PracticalChallengeType.UNDERSTAND_CODE -> Icons.Default.Code
        PracticalChallengeType.FIX_BUG -> Icons.Default.AutoFixHigh
        PracticalChallengeType.WRITE_CODE -> Icons.Default.EditNote
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(
            width = if (isSolved) 2.dp else 1.dp,
            color = if (isSolved) CorrectGreen else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Goiko Etiketak (Mota + Gaia + Asmatua ikonoa)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(typeColor.copy(alpha = 0.12f))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = typeIcon, contentDescription = null, tint = typeColor, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = challenge.type.title, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = typeColor)
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(text = challenge.topicCategory, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }

                if (isSolved) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = CorrectGreen, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Zuzena!", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = CorrectGreen)
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Titulua eta Eszenatokia
            Text(
                text = challenge.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = challenge.scenarioDescription,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // KODE EDITOREA (Syntax Highlighted)
            CodeEditorView(
                title = "Ariketaren Kode Zatia",
                code = challenge.initialCode
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Galderaren testua
            Text(
                text = challenge.questionPrompt,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 1 eta 2 MODALITATEAK: MULTIPLE CHOICE (UNDERSTAND_CODE / FIX_BUG)
            if (challenge.type != PracticalChallengeType.WRITE_CODE) {
                challenge.options.forEachIndexed { optIndex, optionText ->
                    val isOptionSelected = selectedOption == optIndex
                    val isCorrectOption = optIndex == challenge.correctOptionIndex

                    val optBgColor = when {
                        !hasAnsweredChoice && isOptionSelected -> PrimaryBlue.copy(alpha = 0.12f)
                        hasAnsweredChoice && isCorrectOption -> CorrectGreen.copy(alpha = 0.18f)
                        hasAnsweredChoice && isOptionSelected && !isCorrectOption -> WrongRed.copy(alpha = 0.18f)
                        else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                    }

                    val optBorderColor = when {
                        !hasAnsweredChoice && isOptionSelected -> PrimaryBlue
                        hasAnsweredChoice && isCorrectOption -> CorrectGreen
                        hasAnsweredChoice && isOptionSelected && !isCorrectOption -> WrongRed
                        else -> MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                    }

                    val optionLetter = listOf("A", "B", "C", "D").getOrElse(optIndex) { "?" }

                    Card(
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = optBgColor),
                        border = BorderStroke(1.dp, optBorderColor),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable(enabled = !hasAnsweredChoice) {
                                selectedOption = optIndex
                                hasAnsweredChoice = true
                                val success = optIndex == challenge.correctOptionIndex
                                onSolved(success)
                            }
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(26.dp)
                                    .clip(CircleShape)
                                    .background(optBorderColor.copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = optionLetter,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    color = if (hasAnsweredChoice && isCorrectOption) CorrectGreen else MaterialTheme.colorScheme.onSurface
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = optionText,
                                fontSize = 13.sp,
                                lineHeight = 18.sp,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }

                // Azalpen teknikoa erantzun ondoren
                AnimatedVisibility(visible = hasAnsweredChoice, enter = fadeIn(), exit = fadeOut()) {
                    Column(modifier = Modifier.padding(top = 10.dp)) {
                        Card(
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (selectedOption == challenge.correctOptionIndex) CorrectGreen.copy(alpha = 0.1f) else WrongRed.copy(alpha = 0.1f)
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = if (selectedOption == challenge.correctOptionIndex) "Zorionak! Ondo arrazoitu duzu." else "Aztertu soluzioaren arrazoia:",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = if (selectedOption == challenge.correctOptionIndex) CorrectGreen else WrongRed
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = challenge.detailedExplanation,
                                    fontSize = 12.sp,
                                    lineHeight = 17.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }
            } else {
                // 3. MODALITATEA: WRITE_CODE (Kode zatia idatzi eta egiaztatu)
                OutlinedTextField(
                    value = userWrittenCode,
                    onValueChange = {
                        userWrittenCode = it
                        writeValidationResult = null // berrezarri idaztean
                    },
                    label = { Text("Idatzi hemen falta den kodea...", fontSize = 13.sp) },
                    textStyle = TextStyle(fontFamily = FontFamily.Monospace, fontSize = 13.sp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryBlue,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = false,
                    maxLines = 3
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            val cleanInput = userWrittenCode.trim().lowercase().replace(" ", "")
                            // Begiratu ea beharrezko hitz-gako guztiak sartu dituen
                            val allKeywordsPresent = challenge.expectedKeywords.all { kw ->
                                cleanInput.contains(kw.lowercase().replace(" ", ""))
                            }
                            val isExactOrKeywords = allKeywordsPresent && cleanInput.isNotEmpty()
                            writeValidationResult = isExactOrKeywords
                            onSolved(isExactOrKeywords)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.weight(1.3f)
                    ) {
                        Icon(imageVector = Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Egiaztatu Kodea", fontSize = 12.sp)
                    }

                    OutlinedButton(
                        onClick = { showHint = !showHint },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(imageVector = Icons.Default.Lightbulb, contentDescription = null, modifier = Modifier.size(16.dp), tint = AccentAmber)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(if (showHint) "Pista Itxi" else "Pista", fontSize = 12.sp)
                    }

                    OutlinedButton(
                        onClick = { showSolution = !showSolution },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(imageVector = Icons.Default.Visibility, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(if (showSolution) "Itxi" else "Soluzioa", fontSize = 12.sp)
                    }
                }

                // Pista Koadroa
                AnimatedVisibility(visible = showHint) {
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = AccentAmber.copy(alpha = 0.12f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    ) {
                        Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Lightbulb, contentDescription = null, tint = AccentAmber, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = challenge.hint, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface)
                        }
                    }
                }

                // Soluzio Zuzena Koadroa
                AnimatedVisibility(visible = showSolution) {
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = PrimaryBlue.copy(alpha = 0.1f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(text = "Soluzio Zuzena:", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = PrimaryBlue)
                            Text(
                                text = challenge.solutionCode,
                                fontFamily = FontFamily.Monospace,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }

                // Emaitzaren Feedback-a
                if (writeValidationResult != null) {
                    val isSuccess = writeValidationResult == true
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSuccess) CorrectGreen.copy(alpha = 0.12f) else WrongRed.copy(alpha = 0.1f)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = if (isSuccess) "Bikain! Kodea zuzena da eta kontzeptua betetzen du." else "Kodea ez dago guztiz zuzen. Berrikusi pista edo idatzi beharrezko funtzioa.",
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = if (isSuccess) CorrectGreen else WrongRed
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = challenge.detailedExplanation,
                                fontSize = 12.sp,
                                lineHeight = 17.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }
    }
}
