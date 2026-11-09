package com.goierrieskola.mpgl_1_ebaluaketa_app.data

import com.goierrieskola.mpgl_1_ebaluaketa_app.model.CodeSnippetItem
import com.goierrieskola.mpgl_1_ebaluaketa_app.model.Topic
import com.goierrieskola.mpgl_1_ebaluaketa_app.model.TopicSection

object TheoryRepository {
    val topics: List<Topic> = listOf(
        Topic(
            id = "gaia_1",
            number = 1,
            title = "Gailu Mugikorrak eta Android Sistema",
            subtitle = "Historia, Hardwarea, Garapen Aukerak eta Android Arkitektura",
            summary = "Gailu mugikorren oinarriak, SoC arkitektura, garapen teknologiak (Natiboa, Web, Hibridoa, PWA, Krose) eta Android-en geruza anitzeko arkitektura.",
            sections = listOf(
                TopicSection(
                    title = "1. Gailu Mugikorren Ezaugarriak eta Hardwarea",
                    explanation = "Gailu mugikorrek mugikortasuna, tamaina murriztua, hari gabeko konexio iraunkorra eta autonomia mugatua dute. Hardware mailan osagai nagusiak hauek dira:",
                    bulletPoints = listOf(
                        "SoC (System on a Chip): Txip bakarrean CPU, GPU (grafikoak), DSP (seinale digitalak) eta NPU (adimen artifiziala) integratzen dira espazioa eta bateria aurrezteko.",
                        "RAM memoria: LPDDR motakoa (Low Power DDR), kontsumo txikirako optimizatua.",
                        "Pantaila teknologiak: LCD (atze-argiztapen orokorra) vs. OLED/AMOLED (pixel bakoitza argi-igorle independentea da; beltzetan pixela itzali egiten da bateria aurreztuz).",
                        "Sentsoreak: Azelerometroa, giroskopioa, magnetometroa, hurbiltasun-sentsorea eta GPS (LBS - Location Based Services zerbitzuetarako)."
                    )
                ),
                TopicSection(
                    title = "2. Garapen Teknologiak (Aplikazio Motak)",
                    explanation = "Mugikorretarako aplikazioak garatzeko 5 ikuspegi nagusi daude:",
                    bulletPoints = listOf(
                        "Natiboak (Kotlin/Android, Swift/iOS): Errendimendu gorena eta hardware sarbide osoa. Desabantaila: Garapen eta mantentze kostu bikoitza.",
                        "Web-aplikazioak (Responsive WebApps): HTML5/CSS/JS nabigatzailean exekutatzen dira. Ez dira instalatzen, baina hardware sarbidea oso mugatua da.",
                        "Hibridoak (Cordova, Capacitor): Web teknologiak gailuan instalatzen den WebView baten barruan. Plugins bidez atzitzen da hardwarea, errendimendua apalagoa da.",
                        "PWA (Progressive Web Apps): Service Workers eta Web Manifest erabiltzen dituzte. Lineaz kanpoko modua eta hasierako pantailan ikonoa gehitzea ahalbidetzen dute.",
                        "Krose-plataforma konpilatuak (Flutter, React Native): Kode-base bakarra. Flutter-ek bere renderizatzaile propioa du (Skia/Impeller) eta errendimendu natibo oso hurbila lortzen du."
                    )
                ),
                TopicSection(
                    title = "3. Android Sistemaren Arkitektura eta Segurtasuna",
                    explanation = "Android Linux kernelean oinarritutako geruza anitzeko sistema irekia da:",
                    bulletPoints = listOf(
                        "Linux Kernel: Hardware kontrolatzaileak (drivers), memoria eta prozesuen kudeaketa baxuena.",
                        "HAL (Hardware Abstraction Layer): Gailuaren hardware zehatza modulutan kapsulatzen du (kamera, bluetooth, audioa).",
                        "Native C/C++ Libraries eta ART (Android Runtime): Dalvik zaharraren ordezkoa; AOT (Ahead-Of-Time) eta JIT konpilazioa uztartzen ditu bytekodea makina-kodera bihurtzeko.",
                        "Java API Framework: Garatzaileek erabiltzen dituzten zerbitzuak (Activity Manager, Notification Manager, Content Providers).",
                        "System Apps: Aurrez instalatutako oinarrizko aplikazioak (markatzailea, mezularitza, nabigatzailea).",
                        "Segurtasuna (Sandbox): Aplikazio bakoitzak Linux erabiltzaile ID (UID) bakarra jasotzen du instalatzean, prozesu eta fitxategi isolatuetan exekutatuz."
                    )
                )
            ),
            codeSnippets = listOf(
                CodeSnippetItem(
                    title = "AndroidManifest.xml Baimenak",
                    description = "Gailuaren hardware funtzioak atzitzeko baimenak deklaratu behar dira:",
                    code = "<!-- Internet sarbidea -->\n<uses-permission android:name=\"android.permission.INTERNET\" />\n<!-- Kokapen sarbidea -->\n<uses-permission android:name=\"android.permission.ACCESS_FINE_LOCATION\" />"
                )
            ),
            examKeyNotes = listOf(
                "OLED pantailen abantaila nagusia gai ilunean (Dark Mode) bateria aurreztea da, pixel beltzak erabat itzaltzen direlako.",
                "Android-en aplikazio bakoitza Linux UID propio baten azpian exekutatzen da Sandbox moduan.",
                "Dalvik-ek JIT erabiltzen zuen soilik; ART-ek AOT (instalazioan konpilatua) eta JIT optimizatua konbinatzen ditu."
            )
        ),
        Topic(
            id = "gaia_2",
            number = 2,
            title = "Kotlin Lengoia eta OOP Printzipioak",
            subtitle = "Klase Abstraktuak, Interfazeak, Polimorfismoa eta Null Safety",
            summary = "Kotlin-en sintaxi modernoa, `val` vs `var`, segurtasuna nuloen aurrean, klase abstraktuak vs interfazeak eta 'Figurak eta Azalerak' ariketa praktikoa.",
            sections = listOf(
                TopicSection(
                    title = "1. Kotlin Oinarrizko Ezaugarriak",
                    explanation = "Kotlin lengoaia moderno, garbi eta kontzisoa da, Java-rekin %100 bateragarria:",
                    bulletPoints = listOf(
                        "Aldagaiak: `val` (aldagaitza / immutable - balioa behin bakarrik esleitzen da) vs `var` (aldakorra / mutable). Gomendioa: beti lehenetsi `val`.",
                        "Datu moten inferentzia: Ez da beti mota esplizituki idatzi behar (adibidez: `val mezua = \"Kaixo\"`).",
                        "Null Safety: Kotlin-ek 'Billion Dollar Mistake' saihesten du tipo ez-nuloak lehenetsiz. Nuloa onartzeko `?` gehitu behar da (`String?`). Eragileak: `?.` (safe call), `?:` (Elvis operator), `!!` (not-null assertion)."
                    )
                ),
                TopicSection(
                    title = "2. OOP: Klase Abstraktuak vs Interfazeak",
                    explanation = "Objektuetara Bideratutako Programazioaren oinarrizko galderak (Figurak proiektuan ikusiak):",
                    bulletPoints = listOf(
                        "Klase Abstraktua (`abstract class Figura`): Mundu errealean 'Figura' bat kontzeptu abstraktu bat da, ez entitate zehatz bat. Horregatik ez da zentzuzkoa Figura() zuzenean instantziatzea.",
                        "Interfazea (`interface Gainazala`): Gaitasun edo kontratu bat definitzen du (`fun kalkulatuAzalera()`). Klase batek interfaze anitz inplementa ditzake.",
                        "Polimorfismoa eta LSP (Liskov Substitution Principle): `Figura` abstraktuak `Gainazala` inplementatzen badu, edozein figura-motari `f.erakutsiAzalera()` dei diezaiokegu runtime-ko `is Gainazala` egiaztapenik egin gabe."
                    )
                )
            ),
            codeSnippets = listOf(
                CodeSnippetItem(
                    title = "Null Safety eta Elvis Operator",
                    description = "Nuloak modu seguruan kudeatzeko eredu estandarra:",
                    code = "val izena: String? = null\n// Safe call + Elvis operadorea:\n// Lehenetsitako balioa esleitzeko\nval luzera: Int = izena?.length ?: 0"
                ),
                CodeSnippetItem(
                    title = "Klase Abstraktua eta Interfazea Kotlin-en",
                    description = "Figurak eta azalerak egitura nagusia:",
                    code = "interface Gainazala {\n    fun kalkulatuAzalera(): Double\n}\n\nabstract class Figura(val izena: String) : Gainazala\n\nclass Zirkulua(val erradioa: Double) : Figura(\"Zirkulua\") {\n    override fun kalkulatuAzalera(): Double = Math.PI * erradioa * erradioa\n}"
                )
            ),
            examKeyNotes = listOf(
                "Klase abstraktuek eraikitzailea (constructor) eta barne-egoera (state) izan dezakete; interfazeek ezin dute egoerarik gorde.",
                "Zergatik ez da `is Gainazala` behar? Polimorfismoari esker, Figura motako edozein objektu Gainazala dela bermatzen duelako konpiladoreak.",
                "Elipsearen perimetroa kalkulatzeko Ramanujan-en hurbilketa erabiltzen da integral eliptikoak ebaztea saihesteko."
            )
        ),
        Topic(
            id = "gaia_3",
            number = 3,
            title = "Android Studio eta Proiektuaren Egitura",
            subtitle = "IDE Tresnak, AVD, Gradle Scripts eta AndroidManifest",
            summary = "Android Studio garapen ingurunea, Device Manager, Gradle menpekotasunak (libs.versions.toml) eta Android proiektu baten karpeta-egitura.",
            sections = listOf(
                TopicSection(
                    title = "1. Android Studio-ren Lan-Ikuspegiak",
                    explanation = "Android Studio JetBrains IntelliJ IDEA-n oinarritutako ingurune ofiziala da:",
                    bulletPoints = listOf(
                        "Code ikuspegia: Kotlin kodea idazteko eremua.",
                        "Split / Preview ikuspegia: Kodea eta `@Preview` bidezko diseinua batera denbora errealean erakusten ditu.",
                        "Device Manager: AVD (Android Virtual Device) emuladoreak sortu, konfiguratu eta abiarazteko kudeatzailea.",
                        "SDK Manager: Android bertsioak (API 26, 34, 37...) eta Build Tools tresnak deskargatzeko zentroa."
                    )
                ),
                TopicSection(
                    title = "2. Proiektuaren Karpeta Egitura",
                    explanation = "Android ikuspegiak fitxategiak modu logikoan antolatzen ditu:",
                    bulletPoints = listOf(
                        "manifests/AndroidManifest.xml: Aplikazioaren NAN-a da; pakete-izena, Activity nagusiak, ikonoak, gaiak eta baimenak zehazten ditu.",
                        "kotlin+java/: Gure iturburu-kodea (adibidez: MainActivity.kt).",
                        "res/drawable/: Irudiak eta bektoreak (XML formadunak).",
                        "res/values/: strings.xml (testuak), colors.xml, themes.xml.",
                        "Gradle Scripts: Eraikuntza konfigurazioa (build.gradle.kts, settings.gradle.kts eta libs.versions.toml)."
                    )
                )
            ),
            codeSnippets = listOf(
                CodeSnippetItem(
                    title = "MainActivity.kt Sarrera Puntua",
                    description = "Android Compose aplikazio baten oinarrizko abioa:",
                    code = "class MainActivity : ComponentActivity() {\n    override fun onCreate(savedInstanceState: Bundle?) {\n        super.onCreate(savedInstanceState)\n        enableEdgeToEdge()\n        setContent {\n            MyAppTheme {\n                Scaffold(modifier = Modifier.fillMaxSize()) {\n                    MainScreen()\n                }\n            }\n        }\n    }\n}"
                )
            ),
            examKeyNotes = listOf(
                "Save location eremuan zuriunerik ez egotea gomendatzen da NDK tresnekin eta Gradlerekin arazoak saihesteko.",
                "Kotlin DSL formatuan fitxategiek `.gradle.kts` luzapena dute (Groovy zaharraren ordez).",
                "Mendekotasunak kudeatzeko gomendio modernoa `gradle/libs.versions.toml` (Version Catalog) fitxategia da."
            )
        ),
        Topic(
            id = "gaia_4",
            number = 4,
            title = "Jetpack Compose Oinarriak eta Dinamismoa",
            subtitle = "UI Deklaratiboa, Composable, Layouts, Egoera (State) eta Animazioak",
            summary = "Compose-ren paradigma deklaratiboa, Column/Row/Box egiturak, Modifier-ak, State kudeaketa eta LazyColumn eraginkortasuna.",
            sections = listOf(
                TopicSection(
                    title = "1. Paradigma Aldaketa: Deklaratiboa vs Inperatiboa",
                    explanation = "Compose-k UI-a sortzeko modua errotik aldatu du:",
                    bulletPoints = listOf(
                        "Inperatiboa (XML Views zaharra): NOLA egin agintzen dugu urratsez urrats (`findViewById`, `textView.text = ...`).",
                        "Deklaratiboa (Jetpack Compose): UI-ak ZER erakutsi behar duen deskribatzen da egoeraren funtzio gisa (`UI = f(State)`).",
                        "Birkonposizioa (Recomposition): Egoera bat aldatzean, aldaketak eragindako Composable funtzioak bakarrik marrazten dira berriz."
                    )
                ),
                TopicSection(
                    title = "2. Oinarrizko Osagaiak eta Layout-ak",
                    explanation = "UI-a egituratzeko oinarrizko hiru edukiontziak:",
                    bulletPoints = listOf(
                        "Column: Elementuak bertikalki bata bestearen azpian jartzeko.",
                        "Row: Elementuak horizontalki elkarren ondoan jartzeko.",
                        "Box: Elementuak gainjartzeko (Z-indizea, atzeko planoak, flotatzaileak).",
                        "Modifier: Betegarriak (`padding`), tamainak (`fillMaxSize`, `size`), koloreak eta gertaerak (`clickable`) definitzeko. Garrantzitsua: Modifier-en deien ordenak eragina du!"
                    )
                ),
                TopicSection(
                    title = "3. Egoera Kudeaketa (State) eta Zerrendak",
                    explanation = "Dinamismoa eta errendimendua mugikorretan:",
                    bulletPoints = listOf(
                        "remember { mutableStateOf(...) }: Balioa konposizioen artean gogoratzen du.",
                        "rememberSaveable: Balioa konfigurazio-aldaketetan ere mantentzen du (adibidez mugikorra horizontalean biratzean!).",
                        "LazyColumn: Ehunka edo milaka elementutako zerrendetan erabiltzen da; pantailan une horretan ikusgai dauden elementuak bakarrik konposatzen ditu, memoria eta bateria aurreztuz.",
                        "Animazioak: `animateContentSize` eta `animateDpAsState` malguki (`spring`) efektuekin trantsizio leunak egiteko."
                    )
                )
            ),
            codeSnippets = listOf(
                CodeSnippetItem(
                    title = "State eta rememberSaveable",
                    description = "Botoi bidez tolestu eta zabaltzen den txartela:",
                    code = "var expanded by rememberSaveable { mutableStateOf(false) }\n\nColumn(modifier = Modifier.animateContentSize()) {\n    Text(\"Izenburua\")\n    if (expanded) {\n        Text(\"Xehetasun luzeak hemen...\")\n    }\n    Button(onClick = { expanded = !expanded }) {\n        Text(if (expanded) \"Gutxiago\" else \"Gehiago\")\n    }\n}"
                ),
                CodeSnippetItem(
                    title = "LazyColumn Erabilera Eraginkorra",
                    description = "1000 elementuko zerrenda optimizatua:",
                    code = "LazyColumn(modifier = Modifier.fillMaxSize()) {\n    items(1000) { index ->\n        Text(text = \"Elementua: \$index\", modifier = Modifier.padding(16.dp))\n    }\n}"
                )
            ),
            examKeyNotes = listOf(
                "@Composable funtzioek Maiuskulaz hasi behar dute eta ez dute baliorik itzultzen (Unit).",
                "`remember` bakarrik erabiliz gero, pantaila biratzean balioa hasieratu egiten da; horregatik `rememberSaveable` erabiltzen da.",
                "LazyColumn-ek birziklatze mekanismoa erabiltzen du memoria agortu ez dadin."
            )
        ),
        Topic(
            id = "gaia_5",
            number = 5,
            title = "1. Ebaluazioko Codelab Praktikoak",
            subtitle = "Happy Birthday, ArticuloCompose, Task Manager, Quadrant eta Basics",
            summary = "Gelan landutako ariketa praktiko guztien helburuak, erabilitako osagaiak eta diseinu-teknikak.",
            sections = listOf(
                TopicSection(
                    title = "1. Zorion Txartela (Happy Birthday / App Sinplea)",
                    explanation = "Testu konposagarrien oinarriak:",
                    bulletPoints = listOf(
                        "Mezua eta sinadura Column barruan kokatzen dira.",
                        "Letra-tamaina `sp` unitatean (Scale-independent Pixels) eta betegarriak `dp` unitatean (Density-independent Pixels).",
                        "Lerrokatzea: `horizontalAlignment = Alignment.CenterHorizontally` eta `align(Alignment.End)` sinadurarentzat."
                    )
                ),
                TopicSection(
                    title = "2. 02 Práctica: Aspectos básicos de Compose (3 ariketak)",
                    explanation = "Diseinu-egitura errealak eraikitzeko 3 erronka:",
                    bulletPoints = listOf(
                        "ArticuloCompose: Banner irudia (`Image` + `contentScale = ContentScale.FillWidth`) goian, izenburua 24sp-ra, eta bi paragrafo testu justifikatuarekin (`TextAlign.Justify`).",
                        "Task Manager: Pantaila erdian zentratua (`Arrangement.Center`), zereginak beteta adierazten dituen ikonoa, mezu lodia eta azpititulua.",
                        "Compose Quadrant: Pantaila 4 koadrantetan banatua 2 Row eta 2 Column erabiliz `weight(1f)` pisuekin, bakoitza kolore ezberdin batekin."
                    )
                ),
                TopicSection(
                    title = "3. Jetpack Compose Basics (Codelab Aurreratua)",
                    explanation = "OnboardingScreen, LazyColumn eta animazioak:",
                    bulletPoints = listOf(
                        "Hasierako OnboardingScreen pantaila 'Continue' botoiarekin, egoera aldatzean mezu-zerrendara pasatzen dena.",
                        "Txartel bakoitzak IconButton bat du (`ExpandMore` eta `ExpandLess` ikonoekin), testua zabalduz.",
                        "Malguki animazioa (`spring`) aplikatzen zaio `animateContentSize`-ri mugimendu organikoa emateko."
                    )
                )
            ),
            codeSnippets = listOf(
                CodeSnippetItem(
                    title = "Cuadrante de Compose (2x2 Pisuen Egitura)",
                    description = "Pantaila 4 zati berdinetan banatzeko trikimailua:",
                    code = "Column(modifier = Modifier.fillMaxSize()) {\n    Row(modifier = Modifier.weight(1f)) {\n        Box(modifier = Modifier.weight(1f).background(Color.Yellow))\n        Box(modifier = Modifier.weight(1f).background(Color.Green))\n    }\n    Row(modifier = Modifier.weight(1f)) {\n        Box(modifier = Modifier.weight(1f).background(Color.Cyan))\n        Box(modifier = Modifier.weight(1f).background(Color.Magenta))\n    }\n}"
                )
            ),
            examKeyNotes = listOf(
                "Letra-tamainetarako beti erabili `sp` unitatea (erabiltzailearen irisgarritasun ezarpenetara egokitzeko).",
                "Distantzia eta diseinurako beti erabili `dp` unitatea (pantailaren dentsitatera egokitzeko).",
                "`weight(1f)` erabiliz, pantailaren espazio librea modu proportzionalean banatzen da osagaien artean."
            )
        )
    )
}
