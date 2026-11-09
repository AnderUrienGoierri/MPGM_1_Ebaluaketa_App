package com.goierrieskola.mpgl_1_ebaluaketa_app.model

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * 1. Ebaluazioko Gai Teoriako Eredua
 */
data class Topic(
    val id: String,
    val number: Int,
    val title: String,
    val subtitle: String,
    val summary: String,
    val sections: List<TopicSection>,
    val codeSnippets: List<CodeSnippetItem> = emptyList(),
    val examKeyNotes: List<String> = emptyList()
)

data class TopicSection(
    val title: String,
    val explanation: String,
    val bulletPoints: List<String> = emptyList()
)

data class CodeSnippetItem(
    val title: String,
    val description: String,
    val code: String
)

/**
 * Azterketarako Quiz Galdera
 */
data class QuizQuestion(
    val id: Int,
    val topicTitle: String,
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
) {
    /**
     * Erantzun aukerak (A, B, C, D) ausaz nahasten ditu eta erantzun zuzenaren
     * indize berria kalkulatzen du, beti leku berean edo B-n egon ez dadin.
     */
    fun toShuffled(): QuizQuestion {
        if (options.isEmpty()) return this
        val originalCorrectText = options.getOrNull(correctIndex) ?: return this
        val shuffledOptions = options.shuffled()
        val newCorrectIndex = shuffledOptions.indexOf(originalCorrectText)
        return copy(
            options = shuffledOptions,
            correctIndex = if (newCorrectIndex != -1) newCorrectIndex else correctIndex
        )
    }
}

/**
 * Erabiltzaileak emandako erantzunaren erregistroa
 */
data class QuizAnswerRecord(
    val question: QuizQuestion,
    val selectedOptionIndex: Int,
    val isCorrect: Boolean
)

/**
 * Gai bakoitzeko arrakasta-estatistika
 */
data class TopicStat(
    val topicTitle: String,
    val correctCount: Int,
    val totalCount: Int
) {
    val percentage: Int
        get() = if (totalCount > 0) (correctCount.toDouble() / totalCount * 100).toInt() else 0
}

/**
 * Zati Praktikoaren Azterketa Modalitateak:
 * 1. UNDERSTAND_CODE: Kode zati bat aztertu eta emaitza asmatu
 * 2. FIX_BUG: Akatsa bilatu eta konponbide zuzena identifikatu
 * 3. WRITE_CODE: Hutsunea duen kode zatia idatzi eta egiaztatu
 */
enum class PracticalChallengeType(val title: String) {
    UNDERSTAND_CODE("1. Kodea Ulertu"),
    FIX_BUG("2. Kodea Zuzendu"),
    WRITE_CODE("3. Kodea Idatzi")
}

data class PracticalChallenge(
    val id: Int,
    val title: String,
    val type: PracticalChallengeType,
    val topicCategory: String, // "Kotlin OOP", "Compose Layouts", "Egoera & Animazioak", "Codelab Praktikak", "LazyColumn"
    val scenarioDescription: String,
    val initialCode: String,
    val questionPrompt: String,
    val options: List<String> = emptyList(),
    val correctOptionIndex: Int = 0,
    val expectedKeywords: List<String> = emptyList(),
    val hint: String = "",
    val solutionCode: String = "",
    val detailedExplanation: String
) {
    fun toShuffled(): PracticalChallenge {
        if (options.isEmpty()) return this
        val originalCorrectText = options.getOrNull(correctOptionIndex) ?: return this
        val shuffledOptions = options.shuffled()
        val newCorrectIndex = shuffledOptions.indexOf(originalCorrectText)
        return copy(
            options = shuffledOptions,
            correctOptionIndex = if (newCorrectIndex != -1) newCorrectIndex else correctOptionIndex
        )
    }
}

/**
 * Txukuntze orria (CheatSheet) kontzeptu txartela
 */
data class CheatSheetItem(
    val id: Int,
    val category: String, // "Hardwarea", "Arkitektura", "Kotlin OOP", "Compose", "Gradle"
    val term: String,
    val definition: String,
    val codeOrFormula: String = "",
    val examWarning: String = ""
)

/**
 * Kotlin OOP ariketa: Figurak eta Azalerak
 */
interface Gainazala {
    fun kalkulatuAzalera(): Double
}

interface Perimetroa {
    fun kalkulatuPerimetroa(): Double
}

abstract class GeometriaFigura(
    val izena: String,
    val kolorea: String
) : Gainazala, Perimetroa

class Laukizuzena(
    kolorea: String,
    val oinarria: Double,
    val altuera: Double
) : GeometriaFigura("Laukizuzena", kolorea) {
    override fun kalkulatuAzalera(): Double = oinarria * altuera
    override fun kalkulatuPerimetroa(): Double = 2 * (oinarria + altuera)
}

class Triangelua(
    kolorea: String,
    val oinarria: Double,
    val altuera: Double
) : GeometriaFigura("Triangelua", kolorea) {
    override fun kalkulatuAzalera(): Double = (oinarria * altuera) / 2.0
    // Triangelu angeluzuzena hipotesirako
    override fun kalkulatuPerimetroa(): Double {
        val hipotenusa = kotlin.math.sqrt(oinarria * oinarria + altuera * altuera)
        return oinarria + altuera + hipotenusa
    }
}

class Zirkulua(
    kolorea: String,
    val erradioa: Double
) : GeometriaFigura("Zirkulua", kolorea) {
    override fun kalkulatuAzalera(): Double = Math.PI * erradioa * erradioa
    override fun kalkulatuPerimetroa(): Double = 2 * Math.PI * erradioa
}

class Elipsea(
    kolorea: String,
    val a: Double, // ardatzerdi handia
    val b: Double  // ardatzerdi txikia
) : GeometriaFigura("Elipsea", kolorea) {
    override fun kalkulatuAzalera(): Double = Math.PI * a * b
    // Ramanujan-en lehenengo formula
    override fun kalkulatuPerimetroa(): Double =
        Math.PI * (3 * (a + b) - kotlin.math.sqrt((3 * a + b) * (a + 3 * b)))
}
