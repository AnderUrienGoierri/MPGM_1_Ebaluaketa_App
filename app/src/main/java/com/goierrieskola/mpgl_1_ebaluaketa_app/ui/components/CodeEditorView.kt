package com.goierrieskola.mpgl_1_ebaluaketa_app.ui.components

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// =========================================================================
// IDE SINTAXI KOLOREAK (Android Studio / IntelliJ / Darcula Modernoa)
// =========================================================================
object CodeColors {
    val Background = Color(0xFF131722)          // Editorearen oinarri iluna
    val HeaderBackground = Color(0xFF0F1117)    // Leihoaren goiburuko barra iluna
    val Border = Color(0xFF262C3D)              // Marko mehea
    val LineNumber = Color(0xFF4C566A)          // Lerro-zenbakien kolore apala
    val DefaultText = Color(0xFFD8DEE9)         // Testu arrunta / eragileak
    
    val Keyword = Color(0xFFFF7B72)             // Hitz gakoak (val, var, fun, class, override, by...)
    val Type = Color(0xFF4EC9B0)                // Klaseak, Interfazeak, Composable-ak (String, Double...)
    val Function = Color(0xFFFFC66D)            // Funtzio eta metodoen deiak (kalkulatuAzalera...)
    val Variable = Color(0xFF9CDCFE)            // Aldagai eta propietateen izenak (izena, luzera...)
    val Annotation = Color(0xFFE5C07B)          // Oharrak (@Composable, @Preview...)
    val StringLiteral = Color(0xFF98C379)       // Kate literalak ("...")
    val Comment = Color(0xFF7F848E)             // Iruzkinak (//..., <!--...-->)
    val Number = Color(0xFF79B8FF)              // Zenbakiak (0, 10.0, 1000...)
    val Operator = Color(0xFFF0883E)            // Operadore bereziak (?:, ?., !!, ->)
    val XmlTag = Color(0xFFFF7B72)              // XML etiketak (<uses-permission, />, >)
    val XmlAttr = Color(0xFF79B8FF)             // XML atributuak (android:name)
}

// =========================================================================
// SINTAXI TOKENIZATZAILEA ETA EREDU ERREGULARRA
// =========================================================================
private val TOKEN_REGEX = Regex(
    // 1: Iruzkinak (// lerroa, XML <!--...-->, blokekoak /*...*/)
    "(?<comment>//[^\\n]*|<!--[\\s\\S]*?-->|/\\*[\\s\\S]*?\\*/)" +
    // 2: Kateak ("...")
    "|(?<string>\"(?:\\\\.|[^\"\\\\\\n])*\")" +
    // 3: Oharrak (@Composable, @Preview...)
    "|(?<annotation>@[a-zA-Z_][a-zA-Z0-9_]*)" +
    // 4: XML etiketak (</?... edo /?> edo >)
    "|(?<xmltag></?[a-zA-Z0-9_\\-]+|/?>)" +
    // 5: XML atributuak (android:name=)
    "|(?<xmlattr>[a-zA-Z_:][a-zA-Z0-9_\\-:]*(?=\\s*=))" +
    // 6: Kotlin hitz gakoak
    "|(?<keyword>\\b(?:abstract|actual|annotation|as|break|by|catch|class|companion|const|constructor|continue|crossinline|data|delegate|do|dynamic|else|enum|expect|external|false|field|file|final|finally|for|fun|get|if|import|in|infix|init|inline|inner|interface|internal|is|it|lateinit|noinline|null|object|open|operator|out|override|package|param|private|property|protected|public|receiver|reified|return|sealed|set|setparam|super|suspend|tailrec|this|throw|true|try|typealias|typeof|val|var|vararg|when|where|while)\\b)" +
    // 7: Mota eta klaseak (Maiuskulaz hasitako identifikatzaileak: String, Int, Gainazala, Modifier, Column...)
    "|(?<type>\\b[A-Z][a-zA-Z0-9_]*\\b)" +
    // 8: Funtzio-deiak (hitza jarraian '(' duena: kalkulatuAzalera(...)
    "|(?<function>\\b[a-zA-Z_][a-zA-Z0-9_]*(?=\\s*\\())" +
    // 9: Zenbakiak (0, 10.0, 1000...)
    "|(?<number>\\b\\d+(?:\\.\\d+)?(?:[fFdDlL])?\\b)" +
    // 10: Kotlin operadore bereziak (?:, ?., !!, ->, ::)
    "|(?<operator>\\?:|\\?\\.|!!|->|::)" +
    // 11: Aldagai eta propietate arrunta (minuskuletan: izena, luzera...)
    "|(?<variable>\\b[a-z_][a-zA-Z0-9_]*\\b)"
)

/**
 * Kode testu arrunta aztertzen du eta IDE kolorez margotutako AnnotatedString bat bueltatzen du.
 */
fun highlightCode(code: String): AnnotatedString {
    return buildAnnotatedString {
        append(code)

        // Oinarrizko testu estiloa
        addStyle(
            SpanStyle(
                color = CodeColors.DefaultText,
                fontFamily = FontFamily.Monospace,
                fontSize = 12.sp
            ),
            0,
            code.length
        )

        val matches = TOKEN_REGEX.findAll(code)
        for (match in matches) {
            val range = match.range
            val start = range.first
            val end = range.last + 1

            when {
                match.groups["comment"] != null -> {
                    addStyle(
                        SpanStyle(
                            color = CodeColors.Comment,
                            fontStyle = FontStyle.Italic
                        ),
                        start,
                        end
                    )
                }
                match.groups["string"] != null -> {
                    addStyle(
                        SpanStyle(color = CodeColors.StringLiteral),
                        start,
                        end
                    )
                }
                match.groups["annotation"] != null -> {
                    addStyle(
                        SpanStyle(
                            color = CodeColors.Annotation,
                            fontWeight = FontWeight.Bold
                        ),
                        start,
                        end
                    )
                }
                match.groups["xmltag"] != null -> {
                    addStyle(
                        SpanStyle(
                            color = CodeColors.XmlTag,
                            fontWeight = FontWeight.Bold
                        ),
                        start,
                        end
                    )
                }
                match.groups["xmlattr"] != null -> {
                    addStyle(
                        SpanStyle(color = CodeColors.XmlAttr),
                        start,
                        end
                    )
                }
                match.groups["keyword"] != null -> {
                    addStyle(
                        SpanStyle(
                            color = CodeColors.Keyword,
                            fontWeight = FontWeight.Bold
                        ),
                        start,
                        end
                    )
                }
                match.groups["type"] != null -> {
                    addStyle(
                        SpanStyle(
                            color = CodeColors.Type,
                            fontWeight = FontWeight.SemiBold
                        ),
                        start,
                        end
                    )
                }
                match.groups["function"] != null -> {
                    addStyle(
                        SpanStyle(color = CodeColors.Function),
                        start,
                        end
                    )
                }
                match.groups["number"] != null -> {
                    addStyle(
                        SpanStyle(color = CodeColors.Number),
                        start,
                        end
                    )
                }
                match.groups["operator"] != null -> {
                    addStyle(
                        SpanStyle(
                            color = CodeColors.Operator,
                            fontWeight = FontWeight.Bold
                        ),
                        start,
                        end
                    )
                }
                match.groups["variable"] != null -> {
                    addStyle(
                        SpanStyle(color = CodeColors.Variable),
                        start,
                        end
                    )
                }
            }
        }
    }
}

/**
 * Android Studio estiloan diseinatutako kode-leiho interaktiboa.
 * Goiburuko barra (3 botoi koloredunekin), sintaxi-nabarmentzea, lerro-zenbakiak eta kopiatzeko botoiarekin.
 */
@Composable
fun CodeEditorView(
    code: String,
    modifier: Modifier = Modifier,
    title: String? = null,
    showLineNumbers: Boolean = true,
    showHeader: Boolean = true
) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    var isCopied by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    val isXml = remember(code) {
        code.contains("<uses-permission") || code.contains("<!--") || code.contains("</") || code.startsWith("<")
    }
    val languageLabel = if (isXml) "XML" else "Kotlin"
    val highlighted = remember(code) { highlightCode(code) }
    val lines = remember(code) { code.lines() }

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CodeColors.Background),
        border = BorderStroke(1.dp, CodeColors.Border),
        modifier = modifier.fillMaxWidth()
    ) {
        Column {
            // IDE Window Header Bar
            if (showHeader) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(CodeColors.HeaderBackground)
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // macOS / IDE window dots (gorria, horia, berdea)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(modifier = Modifier.size(9.dp).clip(CircleShape).background(Color(0xFFFF5F56)))
                        Box(modifier = Modifier.size(9.dp).clip(CircleShape).background(Color(0xFFFFBD2E)))
                        Box(modifier = Modifier.size(9.dp).clip(CircleShape).background(Color(0xFF27C93F)))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = title ?: (if (isXml) "AndroidManifest.xml" else "Snippet.kt"),
                            color = Color(0xFF94A3B8),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = FontFamily.Monospace
                        )
                    }

                    // Lengoaia-txapa eta Kopiatzeko Botoia
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(Color(0xFF1E2538))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = languageLabel,
                                color = if (isXml) Color(0xFFFF7B72) else Color(0xFF4EC9B0),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Monospace
                            )
                        }

                        IconButton(
                            onClick = {
                                clipboardManager.setText(AnnotatedString(code))
                                isCopied = true
                                Toast.makeText(context, "Kodea arbelean kopiatuta!", Toast.LENGTH_SHORT).show()
                                coroutineScope.launch {
                                    delay(2000)
                                    isCopied = false
                                }
                            },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                imageVector = if (isCopied) Icons.Default.Check else Icons.Default.ContentCopy,
                                contentDescription = "Kopiatu kodea",
                                tint = if (isCopied) Color(0xFF10B981) else Color(0xFF94A3B8),
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }
                }
            }

            // Kodearen eremua (Lerro-zenbakiak eta korritze horizontala)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                // Lerro-zenbakiak
                if (showLineNumbers && lines.size > 1) {
                    Column(
                        modifier = Modifier.padding(start = 12.dp, end = 12.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        lines.indices.forEach { index ->
                            Text(
                                text = "${index + 1}",
                                color = CodeColors.LineNumber,
                                fontFamily = FontFamily.Monospace,
                                fontSize = 12.sp,
                                lineHeight = 19.sp
                            )
                        }
                    }
                } else {
                    Spacer(modifier = Modifier.width(12.dp))
                }

                // Kode testua
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .horizontalScroll(rememberScrollState())
                        .padding(end = 12.dp)
                ) {
                    Text(
                        text = highlighted,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 12.sp,
                        lineHeight = 19.sp,
                        softWrap = false
                    )
                }
            }
        }
    }
}
