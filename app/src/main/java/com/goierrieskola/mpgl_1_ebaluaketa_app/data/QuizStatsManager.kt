package com.goierrieskola.mpgl_1_ebaluaketa_app.data

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import com.goierrieskola.mpgl_1_ebaluaketa_app.model.QuizAnswerRecord
import com.goierrieskola.mpgl_1_ebaluaketa_app.model.QuizQuestion
import com.goierrieskola.mpgl_1_ebaluaketa_app.model.TopicStat

/**
 * Test-en Estatistikak eta Historiala kudeatzen dituen kudeatzailea.
 * Batez besteko nota (media), puntuazio gorena, egindako test kopurua
 * eta gai bakoitzeko arrakasta-tasa gordetzen eta kalkulatzen ditu.
 */
object QuizStatsManager {

    var totalTestsCompleted = mutableIntStateOf(0)
        private set

    var totalQuestionsAnswered = mutableIntStateOf(0)
        private set

    var totalCorrectAnswers = mutableIntStateOf(0)
        private set

    var highestScorePercentage = mutableIntStateOf(0)
        private set

    // Azken proben ehunekoen zerrenda
    val recentScorePercentages = mutableStateListOf<Int>()

    // Gai bakoitzeko estatistikak: Gaiaren izena -> Pair(zuzenak, guztira)
    val topicStatsMap = mutableStateMapOf<String, Pair<Int, Int>>()

    // Orain arte huts egindako galdera bakarren bilduma
    val failedQuestionsList = mutableStateListOf<QuizQuestion>()

    fun recordQuizSession(score: Int, total: Int, records: List<QuizAnswerRecord>) {
        if (total <= 0) return

        totalTestsCompleted.intValue++
        totalQuestionsAnswered.intValue += total
        totalCorrectAnswers.intValue += score

        val percentage = (score.toDouble() / total.toDouble() * 100).toInt()
        recentScorePercentages.add(percentage)

        if (percentage > highestScorePercentage.intValue) {
            highestScorePercentage.intValue = percentage
        }

        // Gai bakoitzeko datuak eguneratu
        records.forEach { record ->
            val cleanTopic = normalizeTopic(record.question.topicTitle)
            val current = topicStatsMap[cleanTopic] ?: Pair(0, 0)
            val newCorrect = if (record.isCorrect) current.first + 1 else current.first
            val newTotal = current.second + 1
            topicStatsMap[cleanTopic] = Pair(newCorrect, newTotal)

            if (!record.isCorrect) {
                if (failedQuestionsList.none { it.id == record.question.id }) {
                    failedQuestionsList.add(record.question)
                }
            } else {
                // Asmatu badu, kendu behin-behineko huts-egindakoen zerrendatik
                failedQuestionsList.removeAll { it.id == record.question.id }
            }
        }
    }

    fun getAveragePercentage(): Double {
        if (recentScorePercentages.isEmpty()) return 0.0
        return recentScorePercentages.average()
    }

    fun getTopicStats(): List<TopicStat> {
        val standardTopics = listOf(
            "1. Gaia: Hardwarea & Arkitektura",
            "2. Gaia: Kotlin OOP",
            "3. Gaia: Android Studio & Gradle",
            "4. Gaia: Jetpack Compose Layouts",
            "5. Gaia: Egoera & Animazioak"
        )

        return standardTopics.map { topicName ->
            val pair = topicStatsMap[topicName] ?: Pair(0, 0)
            TopicStat(
                topicTitle = topicName,
                correctCount = pair.first,
                totalCount = pair.second
            )
        }
    }

    private fun normalizeTopic(raw: String): String {
        return when {
            raw.contains("1. Gaia") -> "1. Gaia: Hardwarea & Arkitektura"
            raw.contains("2. Gaia") -> "2. Gaia: Kotlin OOP"
            raw.contains("3. Gaia") -> "3. Gaia: Android Studio & Gradle"
            raw.contains("4. Gaia") -> "4. Gaia: Jetpack Compose Layouts"
            raw.contains("5. Gaia") -> "5. Gaia: Egoera & Animazioak"
            else -> raw
        }
    }

    fun resetStats() {
        totalTestsCompleted.intValue = 0
        totalQuestionsAnswered.intValue = 0
        totalCorrectAnswers.intValue = 0
        highestScorePercentage.intValue = 0
        recentScorePercentages.clear()
        topicStatsMap.clear()
        failedQuestionsList.clear()
    }
}
