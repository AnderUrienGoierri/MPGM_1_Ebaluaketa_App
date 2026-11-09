package com.goierrieskola.mpgl_1_ebaluaketa_app.data

import com.goierrieskola.mpgl_1_ebaluaketa_app.model.CheatSheetItem

object CheatSheetRepository {
    val items: List<CheatSheetItem> = listOf(
        CheatSheetItem(
            id = 1,
            category = "Hardwarea",
            term = "SoC (System on a Chip)",
            definition = "CPU, GPU, DSP eta NPU txip bakar batean batzen dituen zirkuitua. Tamaina txikia eta kontsumo eraginkorra lortzen du.",
            examWarning = "Azterketan: Gogoratu zergatik ez diren ordenagailu arruntetako txip bereiziak erabiltzen (bateria eta espazioagatik)."
        ),
        CheatSheetItem(
            id = 2,
            category = "Hardwarea",
            term = "OLED vs LCD",
            definition = "LCD-k atze-argiztapen orokorra du. OLED-ek pixel bakoitza argitzen du banaka; beltzetan pixela itzali egiten da bateria aurreztuz.",
            examWarning = "Azterketan: Dark Mode-k OLED pantailetan bakarrik aurrezten du benetako bateria!"
        ),
        CheatSheetItem(
            id = 3,
            category = "Arkitektura",
            term = "Android Geruzak (Behetik Gora)",
            definition = "1. Linux Kernel -> 2. HAL -> 3. Native Libs & ART -> 4. Java API Framework -> 5. System Apps.",
            codeOrFormula = "Linux Kernel -> HAL -> ART -> Framework -> Apps",
            examWarning = "Azterketan: Ordena hau maiz galdetzen da azterketetan!"
        ),
        CheatSheetItem(
            id = 4,
            category = "Arkitektura",
            term = "ART vs Dalvik",
            definition = "Dalvik zaharra: JIT (Just-In-Time) konpilazioa unean uneko exekuzioan. ART modernoa: AOT (Ahead-Of-Time) instalazioan eta JIT optimizatua.",
            examWarning = "ART-ek aplikazioaren abioa askoz azkarragoa egiten du."
        ),
        CheatSheetItem(
            id = 5,
            category = "Segurtasuna",
            term = "Sandbox eta Linux UID",
            definition = "Android-ek aplikazio bakoitzari Linux erabiltzaile ID bakarra ematen dio instalatzean. Aplikazioak ezin du beste baten memoriarik ikusi baimenik gabe.",
            examWarning = "Aplikazio bakoitza erabiltzaile independente bat bezala tratatzen du kernelak."
        ),
        CheatSheetItem(
            id = 6,
            category = "Kotlin OOP",
            term = "val vs var",
            definition = "val = aldagaitza (read-only), behin balioa emanda ezin da aldatu. var = aldakorra (mutable).",
            codeOrFormula = "val x = 10 // ezin da berriz esleitu\nvar y = 20; y = 25 // onartzen da",
            examWarning = "Praktika onena beti `val` lehenestea da."
        ),
        CheatSheetItem(
            id = 7,
            category = "Kotlin OOP",
            term = "Null Safety (? eta ?:)",
            definition = "Kotlin-ek null segurtasuna bermatzen du konpilazioan. Mota nuloak `?` daramate. `?:` Elvis operadoreak ordezko balioa ematen du null bada.",
            codeOrFormula = "val luzera = izena?.length ?: 0",
            examWarning = "Ez nahastu safe call (`?.`) eta not-null assertion (`!!`)."
        ),
        CheatSheetItem(
            id = 8,
            category = "Kotlin OOP",
            term = "Klase Abstraktua vs Interfazea",
            definition = "Klase abstraktua: Txantiloi komun bat da, egoera (state) eta constructor eduki ditzake. Ezin da instantziatu. Interfazea: Jokabide-kontratu hutsa.",
            codeOrFormula = "abstract class Figura(val kolorea: String) : Gainazala",
            examWarning = "Zergatik ezin da Figura() instantziatu? Ez duelako neurri errealik azalera kalkulatzeko!"
        ),
        CheatSheetItem(
            id = 9,
            category = "Kotlin OOP",
            term = "Polimorfismoa eta LSP",
            definition = "Liskov Substitution Principle: Azpiklase batek gurasoaren ordez balio du beti. Figura batek Gainazala inplementatzen badu, ez da `is Gainazala` behar.",
            examWarning = "Konpiladoreak bermatzen du tipo-segurtasuna."
        ),
        CheatSheetItem(
            id = 10,
            category = "Compose",
            term = "@Composable Funtzioak",
            definition = "Datuak UI bihurtzen dituzten funtzioak. Arauak: Maiuskulaz hasi, Unit itzuli, eta birkonposizioan behin baino gehiagotan exekutatu daitezke.",
            codeOrFormula = "@Composable fun Greeting(name: String) { Text(\"Kaixo \$name\") }",
            examWarning = "Ez egin deirik API motelera zuzenean Composable funtzio baten gorputzean!"
        ),
        CheatSheetItem(
            id = 11,
            category = "Compose",
            term = "Column, Row eta Box",
            definition = "Column = elementuak bertikalean; Row = elementuak horizontalean; Box = elementuak elkarren gainean (Z-indizea).",
            examWarning = "Box oso erabilgarria da atzeko planoak edo ikono flotatzaileak jartzeko."
        ),
        CheatSheetItem(
            id = 12,
            category = "Compose",
            term = "remember vs rememberSaveable",
            definition = "remember: konposizioen artean gogoratzen du. rememberSaveable: gailua biratzean (konfigurazio-aldaketan) ere egoera gordetzen du.",
            codeOrFormula = "var egoera by rememberSaveable { mutableStateOf(false) }",
            examWarning = "Azterketako galdera tipikoa: biraketan egoera mantentzeko `rememberSaveable`!"
        ),
        CheatSheetItem(
            id = 13,
            category = "Compose",
            term = "LazyColumn",
            definition = "Pantailan kabitzen diren elementuak bakarrik sortu eta erakusten dituen zerrenda optimizatua.",
            codeOrFormula = "LazyColumn { items(zerrenda) { item -> Card(...) } }",
            examWarning = "Zerrenda luzeetan `Column` arruntak memoria gainezka egin dezake."
        ),
        CheatSheetItem(
            id = 14,
            category = "Compose",
            term = "dp vs sp",
            definition = "dp (Density-independent pixel) = distantziak, padding, zabalera eta altuerak. sp (Scale-independent pixel) = testu-tamainak (fontSize).",
            examWarning = "Inoiz ez erabili `dp` edo `px` testuetarako, irisgarritasuna hausten duelako!"
        )
    )
}
