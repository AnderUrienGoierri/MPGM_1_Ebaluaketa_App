# MPGM — 1. Ebaluaketa Entrenatzailea (Android App)

**Multimedia Programazioa eta Gailu Mugikorrak (MPGM)** moduluko 1. Ebaluaketa osoa azterketarako entrenatu, errepasatu eta prestatzeko aplikazio osoa, Jetpack Compose eta Material Design 3 erabiliz garatua.

Aplikazio hau ordenagailuan bertan (Android Studio emuladorean) zein zure telefono mugikor errealean exekutatu dezakezu.

---

## Aplikazioaren Egitura eta Atal Nagusiak

Aplikazioak beheko nabigazio-barra moderno bat du (`NavigationBar`) 5 atal nagusiekin:

### 1.Hasiera (Home Dashboard)

- Ikastaroaren laburpena eta estatistikak (5 Gai, 50 Galdera, 15 Erronka Praktiko, 14 Fitxa).
- Sarbide azkarreko 4 txartel interaktibo.
- Azterketa prestatzeko aholku gidatuak.

### 2. Teoria Osoa (5 Gaiak)

Gelan emandako eduki teoriko osoa fitxa korrigarrien bidez antolatuta:

- **1. Gaia:** Gailu Mugikorren Oinarriak, Hardwarea (SoC, LPDDR RAM, LCD vs OLED), 5 Garapen Teknologiak (Natiboa, WebApp, Hibridoa, PWA, Krose/Flutter) eta Android Arkitektura (Linux Kernel -> HAL -> ART -> Framework -> Apps) + Segurtasuna (Linux UID eta Sandbox).
- **2. Gaia:** Kotlin Lengoia eta OOP Printzipioak (`val` vs `var`, Null Safety `?.` eta `?:`, `abstract class` vs `interface`, Polimorfismoa eta LSP - Liskov Substitution Principle).
- **3. Gaia:** Android Studio eta Proiektuaren Egitura (Lan-ikuspegiak, Device Manager / AVD, SDK Manager, AndroidManifest.xml, Gradle Scripts eta Version Catalog `libs.versions.toml`).
- **4. Gaia:** Jetpack Compose Oinarriak eta Dinamismoa (Deklaratiboa vs Inperatiboa, `@Composable` funtzioak, Layout-ak: `Column`, `Row`, `Box`, `Modifier`, Egoera kudeaketa `remember` vs `rememberSaveable`, eta `LazyColumn` zerrenda eraginkorrak).
- **5. Gaia:** 1. Ebaluazioko Codelab Praktikoak (Happy Birthday, ArticuloCompose, Task Manager, Quadrant eta Basics Onboarding).
- **IDE Sintaxi Nabarmentzailea (`CodeEditorView`):** Kode-adibide guztiek Android Studio / IntelliJ estiloko koloreak dituzte (hitz-gakoak, motak, funtzioak, aldagaiak, kateak, oharrak, iruzkinak), lerro-zenbakiak, leiho-barra interaktiboa eta arbelean kopiatzeko botoia.
- _Atal bakoitzak Kode-Adibideak eta Azterketarako Gako Kritikoak ditu!_

### 3. Kode Laborategia (Interactive Playground — 5 Atal Handi)

- **1. Tab — Azterketa Praktika Entrenatzailea (BERRIA):**
  - **1. Modalitatea — Kodea Ulertu:** Kode zati bat ikusi eta exekuzioan zer egingo duen edo zer pantailaratuko duen asmatu (Modifier ordena, polimorfismo dinamikoa, LazyColumn optimizazioa, mutableStateOf birkonposaketa, data class inmutabilitatea).
  - **2. Modalitatea — Kodea Zuzendu:** Codelabetako ohiko akats errealak detektatu eta zuzendu (Modifier.weight errorea Box barruan, remember gabe egoera galtzea birkonposizioan, rememberSaveable gabe datuak galtzea mugikorra biratzean, null pointer arriskuak eta LSP hausturak).
  - **3. Modalitatea — Kode Zatia Idatzi / Bete:** Codelab eta klaseko ariketetako kode hutsuneak zuzenean idatzi (`OutlinedTextField`), "Egiaztatu Kodea" sakatu eta ikusi ea zuzena den. Laguntzarako pista (`hint`) eta soluzio osoa ikusteko aukera barne!
  - **Gaitasun Markagailua:** Ebatzi diren ariketen zenbatzailea eta berrabiarazteko aukera.
- **2. Tab — Kotlin OOP & Datuak:**
  - **Figurak & Polimorfismoa:** Hautatu figura (`Laukizuzena`, `Triangelua`, `Zirkulua`, `Elipsea`), aldatu neurriak eta ikusi **2D Canvas marrazki dinamikoa** eta kalkuluak denbora errealean (`Gainazala` eta `Perimetroa`). Polimorfismoaren eta LSPren azalpen sakona (zergatik ez den `is` behar).
  - **Data Class & .copy():** Aldaezintasuna (immutability), balio-berdintasuna (`==`) vs memoria-berdintasuna (`===`) eta instantzia berrien sorrera `.copy(nota = ...)` bidez.
- **3. Tab — Compose Layouts & Modifiers:**
  - **Layout Kontainerrak:** `Column`, `Row`, `Box` switcher-a.
  - **Padding Dinamikoa (0dp - 50dp):** Mugitzean barruko elementuak nola egokitzen diren argi ikusteko, gida-marjina urdin ikusgarria eta `Arrangement.SpaceBetween` / `SpaceEvenly` / `Center` aukerak txertatu dira.
  - **Spacing Slider:** Elementuen arteko hutsunea (`Arrangement.spacedBy`).
  - **Pisuak (`weight`):** Elementuei `Modifier.weight(1f)` aplikatzeko etengailua.
  - **Modifier Ordenaren Garrantzia:** `padding -> background` (kanpoko marjina) vs `background -> padding` (barruko betegarria) konparaketa interaktiboa.
  - **Kodea Zuzenean:** Aukeratutako parametroen araberako Kotlin kodea unean bertan sortzen eta nabarmentzen da `CodeEditorView`-n.
- **4. Tab — Codelab Praktikak (Gelan Landutakoak):**
  - **Cuadrante de Compose (Codelab 2):** 2x2 pantaila 4 koadrantetan banatuta kolore ezberdinekin; aldatu pisuak (`weight(1f)` vs `weight(2f)`) proportzioak ikusteko.
  - **Task Manager (Codelab 2):** Zereginak beteta adierazten dituen pantaila interaktiboa checkmark animazioarekin eta tarteekin (`paddingTop = 24.dp`, `paddingBottom = 8.dp`).
  - **Happy Birthday (Codelab 1):** Testu konposagarriak, `fontSize` eta `lineHeight` erregulagarriak. Ikusi zergatik den beharrezkoa `lineHeight` lerroek elkar ez zapaltzeko.
- **5. Tab — Egoera (State) & Animazioak:**
  - **animateContentSize + spring (Codelab 3):** Txartel zabalgarria; aldatu malgukiaren parametroak (`dampingRatio` eta `stiffness`) eta sentitu trantsizio fisikoa klik egitean!
  - **remember vs rememberSaveable (Biraketa Simulatzailea):** Bi kontagailu interaktibo eta pantaila biraketa simulatzeko botoia (`remember` 0ra itzultzen da, `rememberSaveable`-k balioari eusten dio).

### 4. Azterketa Test Simulatzailea (Quiz — Eguneratu eta Hobetua)

- **50 Galdera Errealeko Bankua:** 10 galdera sakon 1. ebaluaketako 5 gai bakoitzeko.
- **Ausazko Nahasketa Dinamikoa:**
  - **Galderak:** "Azterketa Modua" aukeratzean 20 galdera ausaz hautatzen dira 50en artetik. "Osoa" moduan 50ak egiten dira.
  - **Aukerak (A, B, C, D):** Galdera bakoitzeko 4 aukerak **modu ausazkoan nahasten dira exekuzio bakoitzean**. Erantzun zuzena ez da inoiz leku berean egongo, ezta beti 'B' aukeran ere!
  - **Luzera Orekatua:** Aukera guztiek antzeko hitz kopurua, tonu profesionala eta sakontasun teknikoa dute. Ezin da jakin erantzuna zein den luzeera soilagatik!
- **Nabigazio Babesa (Exit Confirmation Dialog):**
  - Test bat egiten ari zarenean, beheko menuko beste atal bat sakatuz gero edo Android-eko atzera botoia sakatuz gero, **ezin da nahi gabe irten**: berrespen elkarrizketa-koadro bat agertzen da ("Jarraitu Test-arekin" edo "Irten eta Utzi").
- **Estatistikak eta Batez Besteko Nota (Media):**
  - **Estatistikak Modal Osoa:** Egindako test kopurua, batez besteko nota orokorra (`media`), nota maximoa (`gorena`) eta **gai bakoitzeko arrakasta-tasa barra grafikoekin** (zein gaitan zabiltzan indartsu eta zeinetan ahulago jakiteko).
- **Fallatutako Galderak Berrikusi eta Errepikatu:**
  - **🔍 Berrikuspen Zehatza:** Test amaieran huts egindako galdera guztiak aztertu ditzakezu: zure erantzun okerra (❌), erantzun zuzena (✅) eta arrazoibide tekniko osoa.
  - **🔄 Errepikatu Fallatutakoak Soilik:** Botoi baten bidez bakarrik huts egindako galderen mini-test bat egin dezakezu, %100 lortu arte entrenatzeko!
- **Berehalako Feedbacka:** Sakatu aukera bat eta zuzenean koloreztatuko da berdez (zuzena) edo gorriz (okerra).
- **Arrazoibide Zehatza:** Galdera bakoitzak azalpen tekniko argi bat du zergatik den erantzun hori zuzena eta besteak okerrak hobeto ikasteko.

### 5. Txukuntze-Orria (CheatSheet)

- Azterketa aurreko azken 5 minutuetako gako kritikoak.
- Kategoria-iragazkiak (Hardwarea, Arkitektura, Kotlin OOP, Compose, Segurtasuna).
- Bilaketa-barra integratua kontzeptu edo formula zehatzak berehala aurkitzeko.

---

## Nola Ireki eta Exekutatu

### A) Android Studio-n (Ordenagailuan):

1. Ireki **Android Studio**.
2. Sakatu **`File` $\rightarrow$ `Open...`**.
3. Nabigatu proiektu honen karpetara:
   `c:\Dev\GoierriEskola\2_maila\0489-Multimedia-programazioa_gailu_mugikorrak\1_ebaluaketa\MPGL_1_ebaluaketa_app`
4. Egin klik **OK**.
5. Gradlek proiektua kargatuko du (`BUILD SUCCESSFUL`).
6. Bi modu dituzu ikusteko:
   - **Split ikuspegia:** Ireki `MainActivity.kt` eta sakatu **Split** goian eskuinean interfaze osoa ikusteko.
   - **Emuladorean abiarazi:** Sakatu goiko **Run (Play botoi berdea)** aplikazioa emuladorean erabat funtzional erabiltzeko.

### B) Zure Mugikor Fisikoan Probatzeko:

1. Zure Android mugikorrean aktibatu **Garatzaile-aukerak (Developer Options)** eta **USB Arazketa (USB Debugging)**.
2. Konektatu mugikorra ordenagailura USB kable bidez.
3. Android Studio-ko goiko gailu-hautatzailean zure mugikorraren izena agertuko da.
4. Sakatu **Run (Play berdea)** eta aplikazioa zuzenean zure mugikorrean instalatuko da!
