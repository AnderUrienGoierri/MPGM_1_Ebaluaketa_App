package com.goierrieskola.mpgl_1_ebaluaketa_app.data

import com.goierrieskola.mpgl_1_ebaluaketa_app.model.QuizQuestion

object QuizRepository {

    /**
     * 1. Ebaluazioko Galdera Banku Osoa (50 Galdera, 10 gai bakoitzeko).
     * Galdera guztiek 4 aukera orekatu dituzte luzera eta sakontasun teknikoan,
     * erantzun zuzena luzeragatik edo posizioagatik igarri ezin izateko.
     */
    val questions: List<QuizQuestion> = listOf(
        // ==========================================
        // 1. GAIA: HARDWAREA, TEKNOLOGIAK ETA ANDROID ARKITEKTURA (10 Galdera)
        // ==========================================
        QuizQuestion(
            id = 1,
            topicTitle = "1. Gaia: Hardwarea (SoC)",
            question = "Zer da SoC (System on a Chip) bat gailu mugikorren hardwarean?",
            options = listOf(
                "Txip bakarrean CPU, GPU, DSP, modem eta NPU integratzen dituen zirkuitua.",
                "Android aplikazioak azkarrago konpilatzeko plaka nagusiko kontrolatzaile bat.",
                "Bateriaren karga azkarra eta tentsio elektrikoa erregulatzen dituen fusiblea.",
                "Pantailako ukipen geruzaren seinale analogikoak digital bihurtzen dituen sentsorea."
            ),
            correctIndex = 0,
            explanation = "SoC-ek siliziozko txip bakar batean prozesadore guztiak (CPU, GPU, seinale-prozesadorea eta AI txipa) biltzen ditu, tamaina txikituz eta kontsumo elektrikoa murriztuz."
        ),
        QuizQuestion(
            id = 2,
            topicTitle = "1. Gaia: Hardwarea (Pantailak)",
            question = "Zergatik aurrezten du bateria gehiago OLED pantaila batek gai ilunarekin LCD batek baino?",
            options = listOf(
                "OLED pantailako kristal likidoak erabat izoztu egiten direlako iluntasunean.",
                "OLED pantailetan pixel bakoitza argi-igorle autonomoa delako eta beltzean itzali egiten delako.",
                "LCD pantailek zuri kolorea marrazteko energia gutxiago behar dutelako beltza marrazteko baino.",
                "LCD pantailetan atze-argi panela erabat itzaltzen delako aplikazioa iluna denean."
            ),
            correctIndex = 1,
            explanation = "LCD pantailek beti piztuta dagoen atze-argiztapen panela (backlight) dute; OLED pantailetan aldiz, pixel bakoitza organikoa da eta beltz hutsean ez du inolako korronterik kontsumitzen."
        ),
        QuizQuestion(
            id = 3,
            topicTitle = "1. Gaia: Hardwarea (Memoria)",
            question = "Zer abantaila nagusi eskaintzen du LPDDR memoriak gailu mugikorretan DDR arruntaren aldean?",
            options = listOf(
                "Datuen irakurketa abiadura bikoiztea disko gogor mekanikoekin sinkronizatzeko.",
                "Kostu ekonomikoa asko murriztea errendimendu orokorraren kalterako.",
                "Tentsio elektriko murriztuan lan egitea eta kontsumo energetiko baxua bermatzea.",
                "Beroa barreiatzeko aluminiozko aireztatze aktibo handiak behar izatea."
            ),
            correctIndex = 2,
            explanation = "LPDDR (Low Power DDR) memoria mugikorretarako bereziki sortua da, tentsio elektriko askoz baxuagoan lan egiten duena bateria ahalik eta gutxien xahutzeko."
        ),
        QuizQuestion(
            id = 4,
            topicTitle = "1. Gaia: Garapen Teknologiak",
            question = "Zein da aplikazio natiboen (Kotlin / Swift) ezaugarri bereizgarri nagusia?",
            options = listOf(
                "Sistemaren API eta hardware baliabide guztiak zuzenean eta abiadura maximoan atzitzea.",
                "Kode-oinarri bakarrarekin sistema eragile guztietan inolako aldaketarik gabe exekutatzea.",
                "Aplikazioa instalatu gabe web nabigatzaile baten barrutik soilik erabili ahal izatea.",
                "Webview baten bidez HTML eta CSS fitxategiak kargatuz interfazea marraztea."
            ),
            correctIndex = 0,
            explanation = "Aplikazio natiboek sistemaren API ofizialak eta hardware azeleratua bitartekaririk gabe atzitzen dituzte, errendimendu gorena eta erabiltzaile-esperientzia leunena eskainiz."
        ),
        QuizQuestion(
            id = 5,
            topicTitle = "1. Gaia: Garapen Teknologiak",
            question = "Zer osagai nagusi baliatzen dute Progressive Web Apps (PWA) aplikazioek konexiorik gabe funtzionatzeko?",
            options = listOf(
                "Android Runtime (ART) makina birtualaren atzeko hari dedikatu bat.",
                "Service Worker deritzon JavaScript script independentea eta katxe biltegiratzea.",
                "Apache Cordova plugina gailuaren tokiko biltegiratze karpetara sartzeko.",
                "Google Play Services zerbitzuak hodeiko datuak automatikoki sinkronizatzeko."
            ),
            correctIndex = 1,
            explanation = "PWA-etan Service Worker-ek nabigatzailearen atzean sare-eskaerak kontrolatzen dituzte, edukia katxetik hornituz internetik ez dagoenean ere."
        ),
        QuizQuestion(
            id = 6,
            topicTitle = "1. Gaia: Android Historia",
            question = "Zein gailu hartzen da historiako lehenengo 'smartphone' aitzindari moduan?",
            options = listOf(
                "1999an merkaturatutako BlackBerry 850 mezularitza gailua.",
                "1992an diseinatu eta aurkeztutako IBM Simon telefono adimenduna.",
                "2007an Steve Jobsek aurkeztutako lehen iPhone modeloa.",
                "2008an Android sistema eragilea ekarri zuen HTC Dream gailua."
            ),
            correctIndex = 1,
            explanation = "IBM Simon (1992an diseinatua) telefono mugikorraren eta PDA baten gaitasunak ukipen-pantaila bidez elkartu zituen lehen gailu adimenduntzat aitortzen da."
        ),
        QuizQuestion(
            id = 7,
            topicTitle = "1. Gaia: Android Arkitektura",
            question = "Android-en geruza anitzeko arkitekturan, zer funtzio betetzen du HAL (Hardware Abstraction Layer) geruzak?",
            options = listOf(
                "Interfaze estandarrak eskaintzea hardware espezifikoa goiko Java API-tik isolatzeko.",
                "Linuxeko erabiltzaile-saioak eta fitxategi-sistemaren baimenak kudeatzea.",
                "Erabiltzailearen pantaila-ukipenak eta keinuak kudeatzea Jetpack Compose-n.",
                "Interneteko HTTP eskaerak zifratzea eta segurtasun-ziurtagiriak egiaztatzea."
            ),
            correctIndex = 0,
            explanation = "HAL geruzak gailuaren kamera, Bluetooth edo sentsore espezifikoen kontrolatzaileak estandarizatzen ditu, goiko Java Framework-ak fabrikatzaile bakoitzaren berezitasunak ezagutu beharrik gabe lan egin dezan."
        ),
        QuizQuestion(
            id = 8,
            topicTitle = "1. Gaia: Android Arkitektura",
            question = "Zein da Android Runtime (ART) exekuzio-ingurunearen abantaila nagusia Dalvik zaharrarekin alderatuta?",
            options = listOf(
                "Bytecode-a behin bakarrik konpilatzen duela eta interprete soil moduan funtzionatzen duela.",
                "AOT (Ahead-of-Time) eta JIT hibridoa erabiltzen dituela exekuzio azkarragoa eta kontsumo txikiagoa lortzeko.",
                "Java lengoaia baztertu eta C++ kode gordina soilik onartzen duela segurtasunagatik.",
                "Aplikazio guztiek memoria-espazio bera partekatzea baimentzen duela memoria aurrezteko."
            ),
            correctIndex = 1,
            explanation = "ART-ek AOT (instalazio/atseden garaiko konpilazioa) eta JIT (denbora errealeko profila) konbinatzen ditu, zabor-biltzaile (Garbage Collector) eraginkorrago batekin batera."
        ),
        QuizQuestion(
            id = 9,
            topicTitle = "1. Gaia: Android Segurtasuna",
            question = "Nola bermatzen du Linux Kernel-ak Android-eko aplikazioen arteko isolamendua (Sandbox)?",
            options = listOf(
                "Aplikazio guztiak supererabiltzaile (root) profil bakar baten azpian exekutatuz.",
                "Aplikazio bakoitzari Linux erabiltzaile-identifikatzaile (UID) bakarra esleituz.",
                "Gailua itzaltzen den bakoitzean aplikazioen datu guztiak zerotik ezabatuz.",
                "Aplikazioak exekutatzea galaraziz kanpoko birusen aurkako software ofizialik gabe."
            ),
            correctIndex = 1,
            explanation = "Android-ek Linuxen multi-user segurtasuna baliatzen du: instalatzean aplikazio bakoitzari UID propioa ematen zaio, bere sandbox-etik kanpoko fitxategiak irakurtzea galaraziz."
        ),
        QuizQuestion(
            id = 10,
            topicTitle = "1. Gaia: Baimenen Sistema",
            question = "Zer desberdintasun dago Android-en Baimen Normalen eta Baimen Arriskutsuen (Dangerous) artean?",
            options = listOf(
                "Baimen Normalak ordainpekoak dira eta Arriskutsuak doakoak dira Google Play-n.",
                "Baimen Arriskutsuek erabiltzailearen berrespena eskatzen dute exekuzio-denboran (Runtime).",
                "Baimen Normalak ezin dira AndroidManifest.xml fitxategian sekula deklaratu.",
                "Baimen Arriskutsuak sistemako fabrikatzaileek bakarrik erabil ditzakete fabrikazioan."
            ),
            correctIndex = 1,
            explanation = "Baimen arraskitsuek (kamera, kokapena, kontaktuak) erabiltzailearen pribatutasunari eragiten diote, eta Android 6.0tik aurrera erabiltzaileak unean bertan onartu behar ditu elkarrizketa-koadro batean."
        ),

        // ==========================================
        // 2. GAIA: KOTLIN LENGOIA ETA OOP PRINTZIPIOAK (10 Galdera)
        // ==========================================
        QuizQuestion(
            id = 11,
            topicTitle = "2. Gaia: Kotlin Oinarriak",
            question = "Zer desberdintasun dago 'val' eta 'var' hitz-gakoen artean Kotlin lengoian?",
            options = listOf(
                "'val' aldagai aldagarria (mutable) da eta 'var' irakurtzeko soilik den konstantea.",
                "'val' erreferentzia aldaezina (inmutable) da eta 'var' balioz alda daitekeen aldagaia.",
                "'val' soilik zenbaki osoekin erabil daiteke eta 'var' testu-kateekin bakarrik.",
                "'val' memoriatik berehala askatzen da eta 'var' betiko gordetzen da diskoan."
            ),
            correctIndex = 1,
            explanation = "'val' (value) hasieratu ondoren ezin da berriro esleitu (irakurtzeko soilik da); 'var' (variable) berriz, behin eta berriz alda daiteke mota bera mantenduz."
        ),
        QuizQuestion(
            id = 12,
            topicTitle = "2. Gaia: Null Safety",
            question = "Zer egiten du Elvis eragileak (?:) ondorengo kodean: 'val l = izena?.length ?: 0'?",
            options = listOf(
                "Errore larri bat jaurtitzen du baldin eta 'izena' null bada exekuzioan.",
                "Ezkerreko adierazpena null bada, eskuineko ordezko balioa (0 kasu honetan) itzultzen du.",
                "'izena' aldagaiari null balioa kentzen dio betiko konpiladorean bihurtuz.",
                "Bi zenbakiren arteko zatiketa erlatiboa kalkulatzen du hondarrik gabe."
            ),
            correctIndex = 1,
            explanation = "Elvis eragileak (?:) null balioei aurre egiteko lehenetsitako ordezko balioa ematen du: 'izena?.length' null denean, eskuineko 0 itzultzen du segurtasunez."
        ),
        QuizQuestion(
            id = 13,
            topicTitle = "2. Gaia: Null Safety",
            question = "Zer arrisku dakar Kotlin-en asertzio ez-nuloa (!!) erabiltzeak kodean?",
            options = listOf(
                "Konpiladoreak kodea motelago optimizatuko duela atzeko planoan.",
                "Aldagaia une horretan null bada, NullPointerException jaurtiko duela aplikazioa geldituz.",
                "Aplikazioak ezingo duela Android-eko bertsio zaharretan konpilatu.",
                "Metodo horretan 'val' aldagai guztiak automatikoki 'var' bihurtuko direla."
            ),
            correctIndex = 1,
            explanation = "'!!' eragileak konpiladoreari esaten dio aldagai hori ez dela null; balioa null bada exekuzioan, aplikazioak 'crash' egingo du NullPointerException batekin."
        ),
        QuizQuestion(
            id = 14,
            topicTitle = "2. Gaia: Smart Cast",
            question = "Nola jokatzen du Kotlin-ek 'if (obj is String)' baldintzaren barruan?",
            options = listOf(
                "Objektua eskuz '(String) obj' bidez bihurtu behar da errorea ez emateko.",
                "Kotlin-ek 'obj' automatikoki String motara bihurtzen du (Smart Cast) blokearen barruan.",
                "Objektuaren kopia berri bat sortzen du memorian jatorrizkoa ezabatu gabe.",
                "Errore bat ematen du, Kotlin-en ezin delako baldintzetan mota egiaztapenik egin."
            ),
            correctIndex = 1,
            explanation = "Smart Cast funtzionalitateari esker, konpiladoreak egiaztapena egin ondoren automatikoki ezagutzen du objektua String dela, eta zuzenean bere metodoak (length, uppercase...) erabili daitezke cast espliziturik gabe."
        ),
        QuizQuestion(
            id = 15,
            topicTitle = "2. Gaia: Data Class",
            question = "Zer metodo sortzen ditu konpiladoreak automatikoki 'data class' bat deklaratzean?",
            options = listOf(
                "SQL kontsultak egiteko eta datu-baseko taulak automatikoki sortzeko metodoak.",
                "equals(), hashCode(), toString(), copy() eta componentN() desegituraketa funtzioak.",
                "Android-eko Activity-ak eta pantailako botoiak sortzeko metodo grafikoak.",
                "Fitxategiak internet bidez bidaltzeko HTTP konektoreak eta JSON serializatzaileak."
            ),
            correctIndex = 1,
            explanation = "Kotlin-eko 'data class'-ek automatikoki inplementatzen dituzte balio bidezko konparaketa (equals/hashCode), irudikapen ulergarria (toString) eta inmutabilitatea babesteko .copy() metodoa."
        ),
        QuizQuestion(
            id = 16,
            topicTitle = "2. Gaia: Klase Abstraktuak vs Interfazeak",
            question = "Zein da klase abstraktu baten eta interfaze baten arteko desberdintasun nagusia Kotlin-en?",
            options = listOf(
                "Interfazeek egoera gorde dezakete (backing fields) eta klase abstraktuek ezin dute.",
                "Klase abstraktuek eraikitzailea eta barne-egoera (backing fields) izan ditzakete; interfazeek ez.",
                "Klase batek hainbat klase abstraktutatik hereda dezake baina interfaze bakarretik.",
                "Klase abstraktuetako metodo guztiek derrigorrez hutsik egon behar dute inolako koderik gabe."
            ),
            correctIndex = 1,
            explanation = "Klase abstraktuek eraikitzaileak izan ditzakete eta propietateen egoera gorde dezakete ('val izena: String'). Interfazeek ezin dute egoerarik gorde (ez dute backing field-ik), eta klase batek hainbat interfaze inplementa ditzake."
        ),
        QuizQuestion(
            id = 17,
            topicTitle = "2. Gaia: Herentzia Kotlin-en",
            question = "Zergatik behar da 'open' hitz-gakoa klase batetik beste klase batek hereda dezan?",
            options = listOf(
                "Kotlin-en klase guztiak lehenespenez 'final' direlako eta itxita daudelako herentziarako.",
                "Klasea publikoki internet bidez eskuragarri jartzeko behar delako.",
                "Android Studio-k klasea emuladorean bistaratu ahal izateko derrigorrezkoa delako.",
                "Klasearen barruko aldagai guztiak null bihur daitezen baimentzeko."
            ),
            correctIndex = 0,
            explanation = "Java-n ez bezala, Kotlin-en klaseak eta metodoak lehenespenez 'final' dira. Beste klase batek heredatzea nahi badugu, esplizituki 'open class' gisa deklaratu behar da."
        ),
        QuizQuestion(
            id = 18,
            topicTitle = "2. Gaia: Polimorfismoa",
            question = "Zer gertatzen da 'val f: GeometriaFigura = Zirkulua(5.0)' deklaratu eta 'f.kalkulatuAzalera()' deitzean?",
            options = listOf(
                "GeometriaFigura guraso-klaseko oinarrizko inplementazio orokorra exekutatzen da.",
                "Exekuzio-denborako objektu errealaren (Zirkulua) gainidatzitako kalkulua exekutatzen da.",
                "Konpilazio-errore bat ematen du erreferentziaren mota eta objektua berdin-berdinak ez direlako.",
                "Kalkulua bertan behera geratzen da eta 0.0 balioa itzultzen du segurtasun neurri gisa."
            ),
            correctIndex = 1,
            explanation = "Polimorfismoaren (Dynamic Dispatch) bitartez, nahiz eta aldagaiaren mota abstraktua izan, JVM-k instantzia errealaren metodo gainidatzia (Zirkulua-rena) deitzen du unean bertan."
        ),
        QuizQuestion(
            id = 19,
            topicTitle = "2. Gaia: Liskov Printzipioa (LSP)",
            question = "Zergatik da Liskov Substitution Principle (LSP) haustea Karratua Laukizuzenetik heredatzea?",
            options = listOf(
                "Karratuak 4 alde dituelako eta laukizuzenak bi bakarrik dituelako geometrikoki.",
                "Laukizuzenaren zabalera aldatzean karratuak altuera ere aldatu behar duelako, portaera hautsiz.",
                "Karratuaren azalera ezin delako matematikoki biderketa bidez kalkulatu.",
                "Kotlin-ek debekatu egiten duelako izen geometrikoak dituzten klaseak sortzea."
            ),
            correctIndex = 1,
            explanation = "Liskov printzipioak dio azpiklaseak gurasoaren lekua har dezakeela programaren portaera aldatu gabe. Laukizuzen baten zabalera aldatzean altuerak berdin jarraitzen du; karratuak altuera ere aldatuko balu, kodearen itxaropena hautsiko luke."
        ),
        QuizQuestion(
            id = 20,
            topicTitle = "2. Gaia: Kotlin Funtzioak",
            question = "Zer da goi-mailako funtzio bat (Higher-Order Function) Kotlin lengoian?",
            options = listOf(
                "Prozesadorearen errendimendua %50 baino gehiago handitzen duen funtzio matematikoa.",
                "Beste funtzio bat parametro gisa onartzen duen edo funtzio bat itzultzen duen funtzioa.",
                "Bakarrik Android sistemaren abiaraztean (Boot) exekutatzen den funtzio berezia.",
                "Klase ororen gainetik 'main' funtzio nagusian bakarrik deklaratu daitekeen funtzioa."
            ),
            correctIndex = 1,
            explanation = "Goi-mailako funtzioek beste funtzio batzuk (lambdak) parametro gisa har ditzakete edo emaitza gisa bueltatu (adibidez: onClick = { ... }, filter { ... }, map { ... })."
        ),

        // ==========================================
        // 3. GAIA: ANDROID STUDIO, FITXATEGI EGITURA ETA GRADLE (10 Galdera)
        // ==========================================
        QuizQuestion(
            id = 21,
            topicTitle = "3. Gaia: Android Studio Ikuspegiak",
            question = "Zer desberdintasun nagusi dago 'Project' eta 'Android' proiektu-ikuspegien artean?",
            options = listOf(
                "'Android' ikuspegiak fitxategiak kontzeptualki taldekatzen ditu eta 'Project'-ek disko errealeko egitura erakusten du.",
                "'Project' ikuspegian Java fitxategiak soilik ikusten dira eta 'Android' ikuspegian Kotlin bakarrik.",
                "'Android' ikuspegian fitxategiak editatzea debekatuta dago eta 'Project'-en bakarrik idatz daiteke.",
                "Ez dago inolako desberdintasunik, izena bakarrik aldatzen da erabiltzailearen gustura."
            ),
            correctIndex = 0,
            explanation = "'Project' ikuspegiak disko gogorreko karpeta eta fitxategi erreal guztiak bistaratzen ditu; 'Android' ikuspegiak berriz, garapena errazteko garrantzizkoenak taldekatzen ditu (manifests, java, res, Gradle Scripts)."
        ),
        QuizQuestion(
            id = 22,
            topicTitle = "3. Gaia: Tresnak",
            question = "Zertarako erabiltzen da Android Studio-ko SDK Manager tresna?",
            options = listOf(
                "Emuladore birtualak sortzeko eta mugikorraren pantaila ordenagailuan ikusteko.",
                "Android API bertsioak, eraikuntza erremintak (Build-Tools) eta sistemako irudiak deskargatzeko.",
                "Aplikazioaren kodea GitHub biltegi batera igotzeko eta sinkronizatzeko.",
                "Mugikorreko bateriaren kontsumoa eta memoriaren erabilera aztertzeko."
            ),
            correctIndex = 1,
            explanation = "SDK Manager-ek Android bertsio ezberdinetako plataforma-fitxategiak, SDK Build-Tools osagaiak eta emuladoreetarako sistema-irudiak kudeatzen eta instalatzen ditu."
        ),
        QuizQuestion(
            id = 23,
            topicTitle = "3. Gaia: Tresnak",
            question = "Zer da AVD (Android Virtual Device) bat Android Studio-ren Device Manager-en?",
            options = listOf(
                "Kable bidez konektatuta dagoen mugikor fisiko baten kontrolatzaile berezia.",
                "Ordenagailuan gailu mugikor erreal baten hardwarea eta sistema simulatzen dituen emuladorea.",
                "Google Play dendara aplikazioak automatikoki igotzeko zerbitzari birtuala.",
                "Diseinuko botoiak marrazteko balio duen estilo-txantiloien liburutegia."
            ),
            correctIndex = 1,
            explanation = "AVD (Android Virtual Device) emuladorean exekutatzen den konfigurazio birtuala da, hardware ezaugarri jakin batzuekin (pantaila tamaina, RAM, API maila) gure aplikazioak probatzeko."
        ),
        QuizQuestion(
            id = 24,
            topicTitle = "3. Gaia: AndroidManifest.xml",
            question = "Zer informazio ezinbesteko deklaratzen da 'AndroidManifest.xml' fitxategian?",
            options = listOf(
                "Aplikazioaren kolore-paleta nagusia eta letra-tipoen estiloak.",
                "Aplikazioaren paketea, beharrezko baimenak, Activity-ak eta abiarazteko intent-filter-a.",
                "Kotlin kodearen konpilazio-abiadura eta memorian erabili beharreko MB kopurua.",
                "Emuladorean erabiliko den teklatuaren eta saguaren lasterbideak."
            ),
            correctIndex = 1,
            explanation = "AndroidManifest.xml aplikazioaren erroko agiria da: sistema eragileari jakinarazten dio zer baimen behar dituen (<uses-permission>), zer osagai dituen (<activity>) eta zein den hasierako pantaila (<intent-filter>)."
        ),
        QuizQuestion(
            id = 25,
            topicTitle = "3. Gaia: AndroidManifest.xml",
            question = "Zer esan nahi du Activity batek 'MAIN' ekintza eta 'LAUNCHER' kategoria izateak bere intent-filter-ean?",
            options = listOf(
                "Aplikazioa abiaraztean pantaila hori izango dela lehenengo irekiko dena mugikorraren menutik.",
                "Pantaila hori atzeko planoan bakarrik exekutatuko dela inolako interfaze grafikorik gabe.",
                "Aplikazioak ezingo duela beste pantaila batera nabigatu erabiltzaileak baimena eman arte.",
                "Pantaila hori probetarako bakarrik dela eta azken bertsioan ezabatu egingo dela."
            ),
            correctIndex = 0,
            explanation = "android.intent.action.MAIN-ek adierazten du aplikazioaren sarrera-puntu nagusia dela, eta android.intent.category.LAUNCHER-ek gailuko aplikazioen menuan bere ikonoa jartzen du."
        ),
        QuizQuestion(
            id = 26,
            topicTitle = "3. Gaia: Gradle Sistema",
            question = "Zer zeregin betetzen du Gradle eraikuntza-sistemak (Build System) Android proiektu batean?",
            options = listOf(
                "Erabiltzailearen pantaila-ukipenak denbora errealean marraztea Compose bidez.",
                "Menpekotasunak kudeatzea, kodea eta baliabideak konpilatzea eta instalatzeko APK sortzea.",
                "Google Play dendako iruzkinak aztertzea eta puntuazioa automatikoki hobetzea.",
                "Gailuko bateria kargatzen ari den ala ez detektatzea eta abisuak ematea."
            ),
            correctIndex = 1,
            explanation = "Gradle-k iturburu-kodea (Kotlin), kanpoko liburutegiak (dependencies) eta baliabideak (irudiak, strings) bildu, konpilatu eta gailuan instalatzeko APK edo AAB paketea sortzen du."
        ),
        QuizQuestion(
            id = 27,
            topicTitle = "3. Gaia: Gradle Fitxategiak",
            question = "Zer desberdintasun dago Proiektu mailako eta Modulu mailako 'build.gradle.kts' fitxategien artean?",
            options = listOf(
                "Proiektukoak plugin orokorrak definitzen ditu eta Modulukoak aplikazioaren menpekotasunak eta SDK mugak.",
                "Proiektukoa C++ lengoian idazten da eta Modulukoa soilik Python lengoian.",
                "Modulukoa ezabatu daiteke inolako arazorik gabe eta Proiektukoa derrigorrezkoa da.",
                "Proiektukoak irudiak gordetzen ditu eta Modulukoak soinuak eta bideoak bakarrik."
            ),
            correctIndex = 0,
            explanation = "Proiektu mailako 'build.gradle.kts'-k proiektu osorako pluginak eta bertsio komunak kudeatzen ditu; modulu mailakoak ('app/build.gradle.kts') berriz compileSdk, minSdk, targetSdk eta liburutegi zehatzak definitzen ditu."
        ),
        QuizQuestion(
            id = 28,
            topicTitle = "3. Gaia: SDK Mugak",
            question = "Zer esan nahi du moduluaren ezarpenetan 'minSdk = 24' ezartzeak?",
            options = listOf(
                "Aplikazioak gehienez 24 pantaila ezberdin izan ditzakeela bere barruan.",
                "Android 7.0 (API 24) edo bertsio berriagoa duten gailuetan bakarrik instalatu ahal izango dela.",
                "Aplikazioak 24 segundo beharko dituela emuladorean kargatzeko lehenengo aldian.",
                "Gailuak gutxienez 24 GB libre izan behar dituela barne-memorian instalatu ahal izateko."
            ),
            correctIndex = 1,
            explanation = "'minSdk' (Minimum SDK) da aplikazioa onartzen duen Android bertsio zaharrena. Zenbaki hori baino txikiagoa duen gailu batean Google Play-k ez du aplikazioa instalatzen utziko."
        ),
        QuizQuestion(
            id = 29,
            topicTitle = "3. Gaia: Gradle Ezarpenak",
            question = "Zertarako balio du 'settings.gradle.kts' fitxategiak Android proiektu batean?",
            options = listOf(
                "Botoien koloreak eta interfazearen tamainak definitzeko.",
                "Proiektuan parte hartzen duten moduluak eta liburutegien biltegiak (repositories) deklaratzeko.",
                "Aplikazioaren pasahitzak eta erabiltzaileen datuak gordetzeko.",
                "Android Studio-ko itxura iluna edo argia konfiguratzeko."
            ),
            correctIndex = 1,
            explanation = "'settings.gradle.kts' fitxategiak proiektuaren egitura orokorra definitzen du (include(\":app\")) eta kanpoko liburutegiak nondik deskargatu adierazten du (google(), mavenCentral())."
        ),
        QuizQuestion(
            id = 30,
            topicTitle = "3. Gaia: Version Catalog",
            question = "Zer abantaila eskaintzen du 'gradle/libs.versions.toml' (Version Catalog) fitxategia erabiltzeak?",
            options = listOf(
                "Aplikazioaren APK tamaina erdira murrizten duela inolako konfiguraziorik gabe.",
                "Liburutegien bertsioak leku bakar zentralizatu batean kudeatzea eta moduluetan berrerabiltzea.",
                "Konpilazio-denbora guztiz ezabatzen duela eta berehalako instalazioa ahalbidetzen duela.",
                "Interneteko konexiorik gabe kanpoko edozein liburutegi berri deskargatzea baimentzen duela."
            ),
            correctIndex = 1,
            explanation = "Version Catalog ('libs.versions.toml') fitxategiak liburutegi eta plugin guztien bertsioak leku bakar batean zentralizatzen ditu, bertsio-gatazkak ekidinez."
        ),

        // ==========================================
        // 4. GAIA: JETPACK COMPOSE OINARRIAK ETA LAYOUTS (10 Galdera)
        // ==========================================
        QuizQuestion(
            id = 31,
            topicTitle = "4. Gaia: UI Paradigma",
            question = "Zer desberdintasun nagusi dago UI Deklaratiboaren (Compose) eta Inperatiboaren (XML zaharrak) artean?",
            options = listOf(
                "Inperatiboan UIak zer itxura izan behar duen definitzen da; Deklaratiboan nola aldatu agintzen da urratsez urrats.",
                "Deklaratiboan UIak uneoro egoeraren arabera zer itxura duen adierazten da, widget-en zuhaitza eskuz mutatu gabe.",
                "Deklaratiboa soilik jokoak sortzeko erabiltzen da eta Inperatiboa testuak bistaratzeko.",
                "XML bidezko diseinua askoz azkarragoa da eta ez du inolako baliabiderik xahutzen telefonoan."
            ),
            correctIndex = 1,
            explanation = "UI Deklaratiboan funtzioek uneko datuen araberako interfazea deskribatzen dute (UI = f(state)). Ez da 'findViewById()' edo 'textView.setText()' bezalako agindu inperatiborik erabiltzen."
        ),
        QuizQuestion(
            id = 32,
            topicTitle = "4. Gaia: Composable Funtzioak",
            question = "Zer gertatzen da Compose-n funtzio bati '@Composable' oharra (annotation) gehitzean?",
            options = listOf(
                "Funtzio hori atzeko hari batean exekutatuko dela eta ezingo duela pantailarik ukitu.",
                "Konpiladoreari adierazten diola funtzio honek datuak UI elementu grafiko bihurtzen dituela.",
                "Funtzio horrek ezingo duela inolako parametrorik jaso segurtasun arrazoiengatik.",
                "Funtzio hori Android gailuetan bakarrik exekutatu ahal izango dela eta ezingo dela probatu."
            ),
            correctIndex = 1,
            explanation = "'@Composable' oharrak Compose konpiladorearen pluginari jakinarazten dio funtzio horrek UI nodoak sortzen dituela eta birkonposizio sisteman parte hartzen duela."
        ),
        QuizQuestion(
            id = 33,
            topicTitle = "4. Gaia: Aurrebista (@Preview)",
            question = "Zertarako erabiltzen da '@Preview' oharra Jetpack Compose-n?",
            options = listOf(
                "Aplikazioaren azken bertsioa Google Play-ra zuzenean argitaratzeko.",
                "Diseinuaren itxura Android Studio-ko editorean bertan denbora errealean ikusteko emuladorea abiarazi gabe.",
                "Bateriaren kontsumo maximoa aldez aurretik kalkulatzeko laborategian.",
                "Kodean dauden errore sintaktiko guztiak automatikoki konpontzeko."
            ),
            correctIndex = 1,
            explanation = "'@Preview' oharrak Android Studio-ko 'Split' edo 'Design' ikuspegian composable-aren errendatzea zuzenean bistaratzen du, garapena asko bizkortuz."
        ),
        QuizQuestion(
            id = 34,
            topicTitle = "4. Gaia: Layout Oinarriak",
            question = "Nola kokatzen dituzte euren elementu umeak Column, Row eta Box layout-ek?",
            options = listOf(
                "Column-ek horizontalki, Row-ek bertikalki eta Box-ek zirkuluan kokatzen ditu.",
                "Column-ek bertikalki, Row-ek horizontalki eta Box-ek bata bestearen gainean pilatuta kokatzen ditu.",
                "Hiru layout-ek modu berean kokatzen dituzte elementuak eta izena bakarrik aldatzen da.",
                "Box-ek automatikoki ordenatzen ditu letren tamainaren arabera handienetik txikienera."
            ),
            correctIndex = 1,
            explanation = "Column-ek goitik behera kokatzen ditu (bertikala), Row-ek ezkerretik eskuinera (horizontala), eta Box-ek bata bestearen gainean gainjartzen ditu (z-indizean)."
        ),
        QuizQuestion(
            id = 35,
            topicTitle = "4. Gaia: Modifier-ak",
            question = "Zer gertatzen da Modifier batean metodoen deiaren ordena aldatzen denean (adib. padding eta background)?",
            options = listOf(
                "Ez da ezer aldatzen, Modifier-ek ordena alfabetikoan aplikatzen baitituzte propietate guztiak.",
                "Emaitza bisuala guztiz aldatzen da, Modifier-ak sekuentzialki aplikatzen baitira katean.",
                "Konpiladoreak errore bat ematen du beti background lehenik jartzea derrigortuta dagoelako.",
                "Compose-k ausaz aukeratzen du zein ezarri lehenago errendimendua hobetzeko."
            ),
            correctIndex = 1,
            explanation = "Compose-n Modifier-en ordenak garrantzi handia du: '.padding(16.dp).background(Color.Red)'-ek marjina baten barruan marrazten du gorria; alderantziz jarriz gero, atzealde gorriak marjina barne hartuko luke."
        ),
        QuizQuestion(
            id = 36,
            topicTitle = "4. Gaia: Modifier.weight",
            question = "Non erabil daiteke 'Modifier.weight(1f)' eta zer lortzen da bere bidez?",
            options = listOf(
                "Edozein Composable-tan, elementuaren letra-tipoaren lodiera handitzeko.",
                "Soilik RowScope edo ColumnScope barruan, espazio erabilgarria proportzionalki banatzeko.",
                "Soilik Box barruan, elementua pantailaren erdira eramateko.",
                "AndroidManifest fitxategian, aplikazioaren lehentasuna igotzeko."
            ),
            correctIndex = 1,
            explanation = "'weight()' hedapen-funtzioa RowScope eta ColumnScope-n bakarrik dago eskuragarri, eta bere bidez umeek lerro edo zutabeko espazio librea proportzionalki banatzen dute (adib. 1f eta 1f = %50 bakoitzak)."
        ),
        QuizQuestion(
            id = 37,
            topicTitle = "4. Gaia: Zerrenda Handiak",
            question = "Zergatik da 'LazyColumn' aproposa ehunka elementuko zerrendetarako 'Column' arrunta baino?",
            options = listOf(
                "LazyColumn-ek internetetik bakarrik kargatzen dituelako datuak eta Column-ek disko lokaldik.",
                "Pantailan ikusgai dauden elementuak bakarrik konposatzen eta marrazten dituelako memoria aurreztuz.",
                "Column-ek ezin duelako inolako testurik erakutsi 10 elementu baino gehiago daudenean.",
                "LazyColumn-ek automatikoki datu-base oso bat sortzen duelako atzeko planoan."
            ),
            correctIndex = 1,
            explanation = "LazyColumn-ek Android-eko RecyclerView zaharrak bezala funtzionatzen du: pantailatik irteten diren bistan dauden elementuak bakarrik marrazten ditu, memoria eta prozesadorea ez itotzeko."
        ),
        QuizQuestion(
            id = 38,
            topicTitle = "4. Gaia: Text Parametroak",
            question = "Codelab 1-ean (Happy Birthday), nola bereizten dira neurri-unitateak 'fontSize' eta 'padding' artean?",
            options = listOf(
                "fontSize-k 'dp' erabiltzen du eta padding-ak 'sp' erabiltzen du beti.",
                "fontSize-k 'sp' (letra eskalagarria) erabiltzen du eta padding-ak 'dp' (dentsitatearekiko independentea).",
                "Bi parametroek pixel arruntak (px) erabiltzen dituzte zenbaki huts gisa.",
                "fontSize ehunekotan neurtzen da eta padding zentimetrotan."
            ),
            correctIndex = 1,
            explanation = "Testuen tamainarako beti 'sp' (Scale-independent Pixels) erabiltzen da erabiltzailearen sistemako letra-tamainaren irisgarritasuna errespetatzeko; marjinetarako aldiz 'dp' (Density-independent Pixels)."
        ),
        QuizQuestion(
            id = 39,
            topicTitle = "4. Gaia: Material 3",
            question = "Zer osagaik eskaintzen du pantaila baten oinarrizko egitura (TopBar, BottomBar, FloatingActionButton)?",
            options = listOf(
                "Canvas osagaiak marrazketa librea egiteko.",
                "Scaffold osagaiak Material Design-eko egitura estandarra emateko.",
                "Card osagaiak laukizuzen itxiak sortzeko.",
                "Spacer osagaiak hutsune bertikalak txertatzeko."
            ),
            correctIndex = 1,
            explanation = "Scaffold-ek Material Design-eko pantailek behar duten egitura modularra hornitzen du: goiko barra (topBar), beheko barra (bottomBar) eta edukirako padding-ak kudeatuz."
        ),
        QuizQuestion(
            id = 40,
            topicTitle = "4. Gaia: Lerrokatzeak",
            question = "Zer desberdintasun dago 'Arrangement' eta 'Alignment' artean Row edo Column batean?",
            options = listOf(
                "Arrangement-ek ardatz nagusian kokatzen ditu umeak; Alignment-ek zeharkako ardatzean lerrokatzen ditu.",
                "Arrangement-ek koloreak aldatzen ditu eta Alignment-ek tamaina bakarrik aldatzen du.",
                "Biek gauza bera egiten dute eta ez dago inolako alderik euren artean.",
                "Alignment bakarrik botoiekin erabil daiteke eta Arrangement testuekin bakarrik."
            ),
            correctIndex = 0,
            explanation = "Ardatz nagusian (Row-en horizontala, Column-en bertikala) elementuen banaketa 'Arrangement' da (Center, spaceBetween...); zeharkako ardatzean berriz 'Alignment' da (Alignment.CenterVertically, CenterHorizontally)."
        ),

        // ==========================================
        // 5. GAIA: EGOERA (STATE), BIRKONPOSIZIOA ETA ANIMAZIOAK (10 Galdera)
        // ==========================================
        QuizQuestion(
            id = 41,
            topicTitle = "5. Gaia: Egoera Oinarriak",
            question = "Zer da 'State' (Egoera) Jetpack Compose-ren testuinguruan?",
            options = listOf(
                "Mugikorrak une horretan duen bateria eta wifi-konexioaren maila.",
                "Denboran zehar alda daitekeen eta UIaren itxura zehazten duen edozein datu edo balio.",
                "Android Studio-k kodea konpilatzeko behar duen segurtasun ziurtagiria.",
                "Emuladorearen pantailan irekita dagoen aplikazio kopurua."
            ),
            correctIndex = 1,
            explanation = "Egoera da interfazean eragina duen edozein balio dinamiko (adib. koadro bateko testua, checkbox baten egoera markatua, sareko karga ikonoa)."
        ),
        QuizQuestion(
            id = 42,
            topicTitle = "5. Gaia: Birkonposizioa",
            question = "Zer da birkonposizioa (Recomposition) eta noiz gertatzen da?",
            options = listOf(
                "Aplikazio osoa itxi eta berriro dendatik instalatzea eguneraketa bat dagoenean.",
                "Egoera-aldaketa baten ondorioz datu hori irakurtzen duten composable-ak berriz exekutatzea.",
                "Telefonoa biratzean pantaila itzali eta datu guztiak zerotik garbitzea.",
                "Konpiladoreak akats guztiak automatikoki konpontzeko egiten duen prozesua."
            ),
            correctIndex = 1,
            explanation = "Birkonposizioan Compose-k egoera aldatu zaiola sumatzen du eta egoera hori erabili duten funtzioak berriro exekutatzen ditu, aldatu ez direnak saltatuz (intelligent skipping)."
        ),
        QuizQuestion(
            id = 43,
            topicTitle = "5. Gaia: remember vs rememberSaveable",
            question = "Zergatik da garrantzitsua 'rememberSaveable' erabiltzea 'remember' hutsaren ordez Onboarding pantailan?",
            options = listOf(
                "remember-ek memorian bakarrik gordetzen duelako eta pantaila biratzean Activity-a berrabiaraztean egoera galtzen delako.",
                "remember-ek birkonposizio bakoitzean aplikazioa blokeatu egiten duelako abiadura faltagatik.",
                "rememberSaveable-k datu guztiak zerbitzari batera bidaltzen dituelako internet bidez automatikoki.",
                "Ez dago inolako desberdintasunik, biak guztiz berdinak dira eta berdin funtzionatzen dute."
            ),
            correctIndex = 0,
            explanation = "Mugikorra biratzean (errotazioan) Android-ek Activity osoa berrabiarazten du; 'remember'-ek balioa galtzen du, baina 'rememberSaveable'-k Bundle bidez gordetzen du eta egoera leheneratzen du."
        ),
        QuizQuestion(
            id = 44,
            topicTitle = "5. Gaia: State Hoisting",
            question = "Zer da 'State Hoisting' (Egoera Igotzea) eta zergatik da praktika on bat?",
            options = listOf(
                "Egoera Composable ume batetik bere gurasora igotzea, umea egoeragabe (stateless) eta berrerabilgarri bihurtuz.",
                "Datu guztiak interneteko hodeira igotzea mugikorrean lekurik ez okupatzeko.",
                "Botoien tamaina ahalik eta handiena egitea erabiltzaileak hobeto ikus ditzan.",
                "Aldagai guztiak klase berezi baten barruan ezkutatzea inork ikusi ez ditzan."
            ),
            correctIndex = 0,
            explanation = "State Hoisting ereduan Composable umeak bi parametro jasotzen ditu: bistaratu beharreko balioa eta gertaera bat (lambda: onValueChange). Horrela osagaia erraz probatu eta berrerabil daiteke."
        ),
        QuizQuestion(
            id = 45,
            topicTitle = "5. Gaia: UDF Eredua",
            question = "Zer esan nahi du Datu-Fluxu Norabidebakarrak (Unidirectional Data Flow - UDF)?",
            options = listOf(
                "Datuak soilik zerbitzaritik bezerora doazela eta bezeroak ezin duela mezurik bidali.",
                "Egoera goitik behera (gurasotik umera) jaisten dela eta gertaerak behetik gora (umetik gurasora) igotzen direla.",
                "Pantailako botoiek norabide bakarrean mugitzeko baimena ematen diotela erabiltzaileari.",
                "Aplikazioak orrialde bakarra izan dezakeela eta ezin dela aurreko pantailara itzuli."
            ),
            correctIndex = 1,
            explanation = "UDF arkitekturan egoera gurasoak kontrolatzen du eta beherantz igortzen du; umeek gertaerak (onClick, onTextChange) gorantz jakinarazten dizkiote gurasoari egoera eguneratu dezan."
        ),
        QuizQuestion(
            id = 46,
            topicTitle = "5. Gaia: Animazioak",
            question = "Zer efektu lortzen da 'Modifier.animateContentSize()' erabiliz Codelab 3-ko txartel zabalgarrietan?",
            options = listOf(
                "Txartela kolorez aldatzen dela segundoko 60 aldiz argi-efektu bat sortzeko.",
                "Edukiaren tamaina aldatzean (zabaldu/itxi) trantsizio leun eta animatu bat sortzen duela salto zakarrik gabe.",
                "Txartela pantailatik erabat desagertzen dela eta beste aplikazio bat irekitzen duela.",
                "Testuaren letra-tamaina handitu egiten dela automatikoki irakurketa errazteko."
            ),
            correctIndex = 1,
            explanation = "'animateContentSize()'-k Composable baten barruko edukia aldatzean sortzen den neurri-aldaketa leunki animatzen du, erabiltzaile-esperientzia askoz naturalagoa eginez."
        ),
        QuizQuestion(
            id = 47,
            topicTitle = "5. Gaia: Animazio Fisikak",
            question = "Codelab 3-n erabilitako 'spring()' animazioan, zer kontrolatzen dute 'dampingRatio' eta 'stiffness' balioek?",
            options = listOf(
                "Animazioaren kolorearen distira eta letren arteko tartea.",
                "Malukiaren errebote maila (damping) eta mugimenduaren zurruntasuna edo azkartasuna (stiffness).",
                "Mugikorraren bibrazio-motorraren intentsitatea eta soinu-bolumena.",
                "Bateriaren karga-portzentajearen araberako animazio-abiadura."
            ),
            correctIndex = 1,
            explanation = "Udaberri-animazioetan (spring physics): 'dampingRatio'-k errebote kantitatea definitzen du (DampingRatioMediumBouncy...), eta 'stiffness'-ek zurruntasuna (StiffnessLow, Medium...)."
        ),
        QuizQuestion(
            id = 48,
            topicTitle = "5. Gaia: MutableState Delegetazioa",
            question = "Zer desberdintasun dago 'val s = remember { mutableStateOf(0) }' eta 'var s by remember { mutableStateOf(0) }' artean?",
            options = listOf(
                "'by' erabiliz gero zuzenean 's' balioa irakurri eta idatzi daiteke 's.value' erabili beharrik gabe.",
                "'by' erabiltzeak aldagaia zifratu egiten du eta ezin da birkonposizioan erabili.",
                "Lehenengoak soilik testuak onartzen ditu eta bigarrenak zenbakiak bakarrik.",
                "Ez dago inolako alderik, bi sintaxiek 's.value' idaztera behartzen dute beti."
            ),
            correctIndex = 0,
            explanation = "Kotlin-eko propietate-delegazioari esker ('by'), 'getValue' eta 'setValue' bidez aldagaia balio soil gisa erabil dezakegu (s = 5), '.value' idazteko beharra saihestuz."
        ),
        QuizQuestion(
            id = 49,
            topicTitle = "5. Gaia: Onboarding Fluxua",
            question = "Codelab 3-ko Onboarding adibidean, nola erabakitzen du aplikazioak zer pantaila erakutsi?",
            options = listOf(
                "Telefonoaren sentsoreak detektatzen badu mugikorra berria dela ala zaharra.",
                "'shouldShowOnboarding' egoera-aldagaia egiaztatzen duen baldintza arrunt baten bidez (if/else).",
                "Google Play dendako kontuarekin sinkronizatuta dagoen fitxategi baten bidez.",
                "Ezin da aukeratu, beti bi pantailak batera erakusten baitira bata bestearen azpian."
            ),
            correctIndex = 1,
            explanation = "Compose-n baldintzazko UIa oso sinplea da: 'if (shouldShowOnboarding) OnboardingScreen(...) else GreetingsList(...)'. Egoera aldatzean Compose-k dagokion adarra birkonposatzen du."
        ),
        QuizQuestion(
            id = 50,
            topicTitle = "5. Gaia: Efektu Lagungarriak",
            question = "Zergatik ez da gomendagarria '@Composable' baten gorputzean zuzenean sare-eskaera bat edo datu-base idazketa bat egitea?",
            options = listOf(
                "Birkonposizio bakoitzean eskaera behin eta berriz errepikatuko litzatekeelako kontrolik gabe.",
                "Android-ek berehala desgaitu egingo lukeelako mugikorraren interneteko antena.",
                "Composable funtzioek ezin dutelako inolaz ere interneteko konexiorik erabili.",
                "Ezer ez da gertatuko, konpiladoreak automatikoki eskaera bakarra egingo baitu."
            ),
            correctIndex = 0,
            explanation = "Composable funtzioak askotan eta edozein unetan birkonposa daitezke. Sare-eskaerak edo eragin sekundarioak (Side-Effects) LaunchedEffect edo ViewModel bidez egin behar dira."
        )
    )

    /**
     * Azterketa Modua: 20 galdera ausaz aukeratzen ditu eta bakoitzaren aukerak (A, B, C, D)
     * ausaz nahasten ditu, erantzun zuzenak beti leku ezberdinean egon daitezen.
     */
    fun getExamQuestions(count: Int = 20): List<QuizQuestion> {
        val selected = questions.shuffled().take(count)
        return selected.map { it.toShuffled() }
    }

    /**
     * Entrenamendu Osoa: Galdera guztiak (50ak) itzultzen ditu aukerak nahastuta.
     */
    fun getAllQuestionsShuffled(): List<QuizQuestion> {
        return questions.shuffled().map { it.toShuffled() }
    }
}
