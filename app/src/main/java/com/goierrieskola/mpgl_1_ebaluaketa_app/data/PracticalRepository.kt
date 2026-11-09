package com.goierrieskola.mpgl_1_ebaluaketa_app.data

import com.goierrieskola.mpgl_1_ebaluaketa_app.model.PracticalChallenge
import com.goierrieskola.mpgl_1_ebaluaketa_app.model.PracticalChallengeType

object PracticalRepository {

    val challenges: List<PracticalChallenge> = listOf(
        // ==========================================
        // 1. MODALITATEA: KODEA ULERTU (Output / Portaera aurreikusi)
        // ==========================================
        PracticalChallenge(
            id = 1,
            title = "Modifier Ordenaren Eragina",
            type = PracticalChallengeType.UNDERSTAND_CODE,
            topicCategory = "Compose Layouts",
            scenarioDescription = "Aztertu Jetpack Compose-n Modifier katea aplikatzean ematen den ordena.",
            initialCode = """
Text(
    text = "Kaixo Goierri!",
    modifier = Modifier
        .padding(16.dp)
        .background(Color.Red)
        .padding(8.dp)
)
            """.trimIndent(),
            questionPrompt = "Zein izango da elementu honen azken marrazketa bisuala pantailan?",
            options = listOf(
                "Kanpoko 16dp-ko hutsune garden bat, atzealde gorri bat eta barruan 8dp-ko marjina testuaren inguruan.",
                "Atzealde gorri osoa hartzen duen 24dp-ko padding bakarra sortuko da, lehenengo padding-ak balioa gainidazten baitu.",
                "Errore sintaktiko bat emango du, Compose-n ezin delako Modifier.padding bi aldiz deitu bloke berean.",
                "Atzealde gorria kanpoaldean kokatuko da eta padding guztiak barruan aplikatuko dira."
            ),
            correctOptionIndex = 0,
            detailedExplanation = "Compose-n Modifier-ak sekuentzialki ezartzen dira katean: lehenengo padding-ak kanpoko marjina gardena sortzen du; ondoren datorren background-ak atzealde gorria marrazten du, eta bigarren padding-ak kolore gorriaren barruko marjina gehitzen dio testuari."
        ),
        PracticalChallenge(
            id = 2,
            title = "Polimorfismoa eta Dinamika (OOP)",
            type = PracticalChallengeType.UNDERSTAND_CODE,
            topicCategory = "Kotlin OOP",
            scenarioDescription = "Figura geometrikoen ariketan oinarritutako herentzia eta interfaze deia.",
            initialCode = """
abstract class Figura(val izena: String) {
    open fun kalkulatuAzalera(): Double = 0.0
}

class Zirkulua(val r: Double) : Figura("Zirkulua") {
    override fun kalkulatuAzalera(): Double = Math.PI * r * r
}

fun inprimatu(figura: Figura) {
    println("${'$'}{figura.izena}: ${'$'}{figura.kalkulatuAzalera()}")
}

// Deia:
val nireFigura: Figura = Zirkulua(r = 10.0)
inprimatu(nireFigura)
            """.trimIndent(),
            questionPrompt = "Zer pantailaratuko du kontsolak 'inprimatu(nireFigura)' exekutatzean?",
            options = listOf(
                "Zirkulua: 314.159... (Zirkulua klaseko bertsio gainidatzia exekutatzen da polimorfismoari esker).",
                "Zirkulua: 0.0 (Aldagaiaren mota 'Figura' denez, oinarrizko klaseko metodoa exekutatzen da).",
                "Errorea emango du exekuzioan, 'Figura' abstraktua delako eta ezin delako 'Zirkulua' esleitu.",
                "Ezer ez, klase abstraktuetako metodoak ezin baitira zuzenean inprimatu."
            ),
            correctOptionIndex = 0,
            detailedExplanation = "Polimorfismoaren arabera (Dynamic Dispatch), nahiz eta erreferentzia 'Figura' motakoa izan, instantzia erreala 'Zirkulua' denez, 'Zirkulua'-ren 'override fun kalkulatuAzalera()' exekutatzen da (314.159...)."
        ),
        PracticalChallenge(
            id = 3,
            title = "LazyColumn vs Column Errendimendua",
            type = PracticalChallengeType.UNDERSTAND_CODE,
            topicCategory = "LazyColumn",
            scenarioDescription = "Codelab 3-ko zerrenda handien errendimenduaren azterketa.",
            initialCode = """
@Composable
fun IkasleZerrenda(izenak: List<String>) {
    LazyColumn {
        items(izenak) { izena ->
            GreetingCard(name = izena)
        }
    }
}
            """.trimIndent(),
            questionPrompt = "Zergatik da 'LazyColumn' egokia 10.000 elementuko zerrenda baterako 'Column' arrunta erabili beharrean?",
            options = listOf(
                "Pantailan ikusgai dauden elementuak bakarrik konposatzen eta marrazten dituelako, memoria eta PUZa optimizatuz.",
                "Column-ek ez duelako onartzen testurik bistaratzea eta LazyColumn-ek bakarrik duelako gaitasun hori.",
                "LazyColumn-ek datu guztiak SQLite datu-base batera igortzen dituelako automatikoki.",
                "LazyColumn-ek elementu guztiak aldi berean kargatzen dituelako atzeko hari baten bidez."
            ),
            correctOptionIndex = 0,
            detailedExplanation = "Column arruntak zerrendako elementu GUZTIAK (10.000ak) konposatuko lituzke batera, aplikazioa blokeatuz. LazyColumn-ek birziklatze mekanismoa du (RecyclerView bezala) eta ikusten direnak bakarrik kalkulatzen ditu."
        ),
        PracticalChallenge(
            id = 4,
            title = "Egoera eta Birkonposizioa (State)",
            type = PracticalChallengeType.UNDERSTAND_CODE,
            topicCategory = "Egoera & Animazioak",
            scenarioDescription = "Egoera-aldaketak Compose-n duen portaera UI birkonposizioan.",
            initialCode = """
@Composable
fun KontagailuDemo() {
    var count by remember { mutableStateOf(0) }

    Button(onClick = { count++ }) {
        Text("Klik kopurua: ${'$'}count")
    }
}
            """.trimIndent(),
            questionPrompt = "Zer gertatzen da 'Button' sakatzean 'count' aldagaiak balio berria hartzen duenean?",
            options = listOf(
                "Compose-k egoera irakurtzen duen funtzioa (Text eta Button) automatikoki birkonposatzen du balio eguneratuarekin.",
                "Pantaila osoa suntsitu eta Activity berri bat abiarazten da null pointerrik ez izateko.",
                "Botoia desgaitu egiten da eta erabiltzaileak eskuz deitu behar dio 'invalidate()' metodoari.",
                "Ez da ezer gertatuko pantailan, aldagaia 'mutableStateOf' barruan badago ezin delako 'count++' egin."
            ),
            correctOptionIndex = 0,
            detailedExplanation = "'mutableStateOf'-ek behagarria den egoera sortzen du. Balioa aldatzen denean ('count++'), Compose-ko snapshot sistemak egoera irakurtzen duten composable-ak markatzen ditu eta birkonposizio eraginkorra egiten du."
        ),
        PracticalChallenge(
            id = 5,
            title = "Data Class Inmutabilitatea eta Berdintasuna",
            type = PracticalChallengeType.UNDERSTAND_CODE,
            topicCategory = "Kotlin OOP",
            scenarioDescription = "Data class-en .copy() metodoa eta balio bidezko berdintasuna (structural equality).",
            initialCode = """
data class Erabiltzailea(val id: Int, val izena: String, val rola: String)

val u1 = Erabiltzailea(1, "Ander", "Ikaslea")
val u2 = u1.copy(rola = "Irakaslea")
val u3 = Erabiltzailea(1, "Ander", "Ikaslea")

println(u1 == u2)
println(u1 == u3)
println(u1.rola)
            """.trimIndent(),
            questionPrompt = "Zein da hiru inprimatzeen irteera ordenatua?",
            options = listOf(
                "false, true, Ikaslea (u1-ek jatorrizko balioa mantentzen du eta u1==u3 berdina da edukia berdina delako).",
                "true, true, Irakaslea (u1.copy-k jatorrizko objektua zuzenean mutatu egiten duelako memoriako posizioan).",
                "false, false, Ikaslea (objektu ezberdinak direnez '==' eragileak beti false itzultzen du memoriako helbideagatik).",
                "true, false, Irakaslea (u1 eta u2 id bera dutelako berdinak dira, baina u3 instantzia berria da)."
            ),
            correctOptionIndex = 0,
            detailedExplanation = "Data class-etan '==' eragileak balioak konparatzen ditu (structural equality): u1 eta u3-k datu berdinak dituztenez 'true' da. Bestalde, .copy()-k instantzia berria sortzen du jatorrizkoa aldatu gabe (inmutabilitatea), beraz u1.rola 'Ikaslea' izaten jarraitzen du."
        ),

        // ==========================================
        // 2. MODALITATEA: KODEA ZUZENDU (Bug Fixing / Akatsak aurkitu)
        // ==========================================
        PracticalChallenge(
            id = 6,
            title = "Modifier.weight(1f) Okerreko Testuinguruan",
            type = PracticalChallengeType.FIX_BUG,
            topicCategory = "Compose Layouts",
            scenarioDescription = "Ikasle batek Box baten barruan 'Modifier.weight(1f)' jarri du eta konpilazio-errorea jaso du.",
            initialCode = """
// AKASTUN KODEA:
@Composable
fun OkerrekoKoadrantea() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Lehen Panela",
            modifier = Modifier.weight(1f) // <-- KONPILAZIO ERROREA!
        )
    }
}
            """.trimIndent(),
            questionPrompt = "Zergatik sortzen du errore honek 'Unresolved reference: weight' eta nola konpondu?",
            options = listOf(
                "Modifier.weight() bakarrik RowScope edo ColumnScope barruan existitzen delako; Row edo Column erabili behar da.",
                "Text composable-ak ezin duelako inolako modifierrik eduki; Surface baten barruan kapsulatu behar da.",
                "Box-ek ez duelako fillMaxSize onartzen; tamaina finkoa ezarri behar zaio (adibidez 200.dp).",
                "weight() parametroari Int bat pasatu behar zaiolako Float ordez (adibidez weight(1) eta ez weight(1f))."
            ),
            correctOptionIndex = 0,
            detailedExplanation = "Compose Quadrant ariketan ikusi genuen bezala, 'Modifier.weight()' soilik RowScope edo ColumnScope-n definitutako hedapen-funtzioa da, lerro edo zutabe bateko espazio erabilgarria banatzeko. Box barruan ez du zentzurik."
        ),
        PracticalChallenge(
            id = 7,
            title = "Egoera Aldagaia Birkonposizioan Galtzea",
            type = PracticalChallengeType.FIX_BUG,
            topicCategory = "Egoera & Animazioak",
            scenarioDescription = "Botoia sakatzean zenbakia handitu nahi da, baina pantailan beti '0' agertzen da.",
            initialCode = """
// AKASTUN KODEA:
@Composable
fun KontagailuAkastuna() {
    var count = 0 // <-- AKATSA!

    Button(onClick = { count++ }) {
        Text("Balioa: ${'$'}count")
    }
}
            """.trimIndent(),
            questionPrompt = "Zein da akats larria eta nola konpondu kodea funtziona dezan?",
            options = listOf(
                "'var count by remember { mutableStateOf(0) }' erabili behar da, birkonposizioan balioa ez galtzeko eta aldaketak detektatzeko.",
                "'var count = 0' funtziotik kanpo aldagai global 'static' moduan deklaratu behar da klasearen goiburuan.",
                "Button-en onClick barruan 'Thread.sleep(100)' gehitu behar da UIari denbora emateko marrazteko.",
                "Text-en barruan 'count.toString()' deitu behar da derrigorrez, bestela Compose-k ez baitu zenbakirik onartzen."
            ),
            correctOptionIndex = 0,
            detailedExplanation = "Composable funtzio bat exekutatzen den bakoitzean, 'var count = 0' aldagai lokala birsortu egingo litzateke eta aldaketak ez luke birkonposiziorik eragingo. 'remember { mutableStateOf(0) }'-k balioa memoria-konposizioan gordetzen du eta birkonposizioa abiarazten du."
        ),
        PracticalChallenge(
            id = 8,
            title = "Pantaila Biratzean (Errotazioan) Datuak Galtzea",
            type = PracticalChallengeType.FIX_BUG,
            topicCategory = "Egoera & Animazioak",
            scenarioDescription = "Codelab 3 Onboarding pantailan, erabiltzaileak mugikorra biratzen duenean (horizontala/bertikala) hasierako pantailara itzultzen da.",
            initialCode = """
// AKASTUN KODEA:
@Composable
fun MyApp() {
    var shouldShowOnboarding by remember { mutableStateOf(true) } // <-- ERROREA ERROTAZIOAN!

    if (shouldShowOnboarding) {
        OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
    } else {
        GreetingsList()
    }
}
            """.trimIndent(),
            questionPrompt = "Nola konpondu behar da deklarazioa pantaila biratzean egoera gal ez dadin?",
            options = listOf(
                "'remember' ordez 'rememberSaveable' erabili behar da: 'rememberSaveable { mutableStateOf(true) }'.",
                "AndroidManifest.xml fitxategian 'screenOrientation=\"portrait\"' finkatu behar da biratzea debekatzeko.",
                "'shouldShowOnboarding' aldagaiari 'val' jarri behar zaio 'var' ordez.",
                "Activity-aren 'onDestroy()' metodoan datu-base lokal bat sortu behar da sinkronoki."
            ),
            correctOptionIndex = 0,
            detailedExplanation = "Pantaila biratzean Android-ek konfigurazio aldaketa (Configuration Change) dela eta Activity osoa suntsitu eta berrabiarazten du. 'remember'-ek memorian gordetzen du baina Activity berrian galtzen da. 'rememberSaveable'-k Bundle bidez egoera salbatzen du biraketaren aurrean."
        ),
        PracticalChallenge(
            id = 9,
            title = "Kotlin Null Pointer eta Elvis Eragilea",
            type = PracticalChallengeType.FIX_BUG,
            topicCategory = "Kotlin OOP",
            scenarioDescription = "Aplikazioak huts egiten du erabiltzaile baten testua null denean luzera neurtzean.",
            initialCode = """
// AKASTUN KODEA:
fun kalkulatuKaraktereak(sarrera: String?): Int {
    return sarrera.length // <-- KONPILAZIO ERROREA: Only safe (?.) or non-null asserted (!!.) calls are allowed
}
            """.trimIndent(),
            questionPrompt = "Zein da Kotlin-en modu segurua eta idiomatikoa null denean 0 bueltatzeko?",
            options = listOf(
                "return sarrera?.length ?: 0 (Safe call eragilea gehi Elvis eragilea balio lehenetsia emateko).",
                "return sarrera!!.length (Asertzio indartsua jarriz null ez dela ziurtatzeko).",
                "return (int) sarrera.length (Java-ko cast arrunt bat behartuz).",
                "return if (sarrera == null) return null else sarrera.length (Int nullable batera bihurtuz)."
            ),
            correctOptionIndex = 0,
            detailedExplanation = "Kotlin-en 'sarrera?.length ?: 0' da biderik gomendatuena: 'sarrera?' null bada seguruki 'null' itzultzen du, eta Elvis eragileak '?:' eskuineko balioa (0) hartzen du orduan."
        ),
        PracticalChallenge(
            id = 10,
            title = "Liskov Substitution Principle (LSP) Haustura",
            type = PracticalChallengeType.FIX_BUG,
            topicCategory = "Kotlin OOP",
            scenarioDescription = "Figura geometrikoetan Laukia Laukizuzenetik heredatzeak sortzen duen akats kontzeptuala.",
            initialCode = """
// AKASTUN DISEINUA:
open class Laukizuzena(open var zabalera: Double, open var altuera: Double)

class Karratua(aldea: Double) : Laukizuzena(aldea, aldea) {
    override var zabalera: Double = aldea
        set(value) { field = value; super.altuera = value } // <-- LSP HAUSTURA!
}
            """.trimIndent(),
            questionPrompt = "Zergatik da diseinu-akatsa eta nola zuzendu herentzia egitura?",
            options = listOf(
                "Laukizuzena ordezkatzean portaera aldatzen duelako (zabalera aldatzean altuera ere aldatuz); biak Figura komun batetik heredatu beharko lirateke.",
                "Karratuak ezin duelako sekula aldagairik gainidatzi (override) Kotlin lengoaian.",
                "Laukizuzenaren klaseak 'final' izan behar duelako beti eta ezin zaiolako azpiklaserik sortu.",
                "Ezin da konpondu, matematikan karratua laukizuzena denez Kotlin-en derrigorrezkoa baita horrela egitea."
            ),
            correctOptionIndex = 0,
            detailedExplanation = "Liskov Substitution Principle (LSP)-ren arabera, azpiklase batek gurasoaren portaera kontratua errespetatu behar du. Laukizuzen batean zabalera eta altuera independenteak dira. Karratuak hori bortxatzen duenez, konponbide zuzena biak Figura interfazetik independente sortzea da."
        ),

        // ==========================================
        // 3. MODALITATEA: KODE ZATIA IDATZI / BETE (Code Challenge)
        // ==========================================
        PracticalChallenge(
            id = 11,
            title = "Bete: Onboarding Egoera Salbagarria",
            type = PracticalChallengeType.WRITE_CODE,
            topicCategory = "Egoera & Animazioak",
            scenarioDescription = "Codelab 3-ko Onboarding pantaila: idatzi egoera pantaila biratzean ere mantentzeko beharrezkoa den funtzioa.",
            initialCode = """
@Composable
fun MyApp() {
    // BETE BEHARREKO KODEA:
    var shouldShowOnboarding by ____________________(true)

    if (shouldShowOnboarding) {
        OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
    }
}
            """.trimIndent(),
            questionPrompt = "Idatzi falta den kodea hutsunean (erabili 'rememberSaveable' eta 'mutableStateOf'):",
            expectedKeywords = listOf("remembersaveable", "mutablestateof"),
            hint = "Egoera konfigurazio aldaketen aurrean salbatzeko 'rememberSaveable { mutableStateOf(...) }' egitura behar duzu.",
            solutionCode = "rememberSaveable { mutableStateOf(true) }",
            detailedExplanation = "'rememberSaveable { mutableStateOf(true) }' erabiliz gero, aplikazioak egoera Bundle batean gordetzen du eta pantaila biratzean ez da hasierako egoerara itzultzen."
        ),
        PracticalChallenge(
            id = 12,
            title = "Bete: Compose Quadrant Pisu Banaketa (weight)",
            type = PracticalChallengeType.WRITE_CODE,
            topicCategory = "Compose Layouts",
            scenarioDescription = "Compose Quadrant ariketan, bi zutabeek pantailaren zabaleraren erdia (%50) berdin har dezaten pisu erlatiboa ezarri.",
            initialCode = """
Row(modifier = Modifier.fillMaxWidth()) {
    Column(
        modifier = Modifier.________________
    ) {
        Text("1. Koadrantea")
    }
    Column(
        modifier = Modifier.________________
    ) {
        Text("2. Koadrantea")
    }
}
            """.trimIndent(),
            questionPrompt = "Idatzi zutabe bakoitzari %50eko proportzioa emateko behar den Modifier metodoa:",
            expectedKeywords = listOf("weight"),
            hint = "Erabili 'weight(1f)' espazio erabilgarria zutabeen artean berdin banatzeko.",
            solutionCode = "weight(1f)",
            detailedExplanation = "RowScope barruan 'Modifier.weight(1f)' ezartzeak adierazten dio Compose-ri zutabe bakoitzak pisu berdina hartuko duela, pantaila tamaina edozein dela ere."
        ),
        PracticalChallenge(
            id = 13,
            title = "Bete: Happy Birthday Testu Tamaina eta Lerrokatzea",
            type = PracticalChallengeType.WRITE_CODE,
            topicCategory = "Codelab Praktikak",
            scenarioDescription = "Codelab 1 Happy Birthday ariketan: jarri testuari 36sp tamaina eta zentratu testu-lerrokatzea.",
            initialCode = """
Text(
    text = "Zorionak Zuri!",
    fontSize = 36.sp,
    // BETE BEHARREKO KODEA: lerrokatu erdian
    textAlign = ________________
)
            """.trimIndent(),
            questionPrompt = "Idatzi falta den testu lerrokatze parametroaren balioa ('TextAlign.Center'):",
            expectedKeywords = listOf("textalign", "center"),
            hint = "androidx.compose.ui.text.style paketean 'TextAlign.Center' dago.",
            solutionCode = "TextAlign.Center",
            detailedExplanation = "'textAlign = TextAlign.Center' erabiliz gero, testu-lerroa bere koadroaren erdigunean lerrokatzen da horizontaleko ardatzean."
        ),
        PracticalChallenge(
            id = 14,
            title = "Bete: Kotlin OOP Herentzia Deklarazioa",
            type = PracticalChallengeType.WRITE_CODE,
            topicCategory = "Kotlin OOP",
            scenarioDescription = "GeometriaFigura guraso-klasetik heredatu eta izena pasatu ('Laukizuzena').",
            initialCode = """
abstract class GeometriaFigura(val izena: String, val kolorea: String)

// BETE BEHARREKO KODEA:
class Laukizuzena(
    kolorea: String,
    val oinarria: Double,
    val altuera: Double
) : __________________________________
            """.trimIndent(),
            questionPrompt = "Idatzi guraso-klaseari deitzeko herentzia zatia ('GeometriaFigura(\"Laukizuzena\", kolorea)'):",
            expectedKeywords = listOf("geometriafigura", "laukizuzena", "kolorea"),
            hint = "Klase abstraktutik heredatzean bere eraikitzaileari (constructor) deitu behar zaio: GeometriaFigura(\"Laukizuzena\", kolorea)",
            solutionCode = "GeometriaFigura(\"Laukizuzena\", kolorea)",
            detailedExplanation = "Kotlin-en herentzia ':' ikurrarekin adierazten da eta guraso-klasearen eraikitzaile primarioa deitu behar da derrigorrez beharrezko parametroak emanez."
        ),
        PracticalChallenge(
            id = 15,
            title = "Bete: LazyColumn-en Zerrenda Elementuak Kargatu",
            type = PracticalChallengeType.WRITE_CODE,
            topicCategory = "LazyColumn",
            scenarioDescription = "Codelab 3-ko ikasle zerrenda dinamikoa osatu LazyListScope-ren metodoa erabiliz.",
            initialCode = """
val ikasleak = listOf("Ander", "Nerea", "Mikel", "Maite")

LazyColumn(modifier = Modifier.fillMaxSize()) {
    // BETE BEHARREKO KODEA: kargatu 'ikasleak' zerrendako bakoitza
    __________(ikasleak) { izena ->
        Text(text = "Kaixo, ${'$'}izena!")
    }
}
            """.trimIndent(),
            questionPrompt = "Idatzi LazyColumn barruan zerrendak iteratzeko erabiltzen den metodoaren izena ('items'):",
            expectedKeywords = listOf("items"),
            hint = "androidx.compose.foundation.lazy.items funtzioa da LazyColumn barruan zerrendak iteratzeko gakoa.",
            solutionCode = "items(ikasleak)",
            detailedExplanation = "'items(ikasleak) { izena -> ... }' da Compose-n zerrenda bildumak modu alferrean (lazy) kargatzeko API estandarra."
        )
    )

    fun getChallengesShuffled(type: PracticalChallengeType? = null): List<PracticalChallenge> {
        val list = if (type == null) challenges else challenges.filter { it.type == type }
        return list.map { it.toShuffled() }
    }
}
