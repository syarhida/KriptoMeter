# 📱 KriptoMeter - Index Dokumentasi

## 🎯 Mulai dari Sini

### Untuk Pengguna Baru
1. 📖 Baca [`README.md`](README.md) - Overview aplikasi
2. 🚀 Ikuti [`QUICKSTART.md`](QUICKSTART.md) - Jalankan dalam 5 menit
3. 📚 Lihat [`SETUP.md`](SETUP.md) - Setup lengkap (jika ada masalah)

### Untuk Developer
1. 📋 Baca [`PROJECT_SUMMARY.md`](PROJECT_SUMMARY.md) - Detail teknis lengkap
2. 🔍 Review struktur code di bawah

---

## 📂 Struktur Project

### 📁 Root Files
```
KriptoMeter/
├── 📄 README.md                    ← Overview project
├── 📄 QUICKSTART.md                ← Panduan cepat
├── 📄 SETUP.md                     ← Setup detail
├── 📄 PROJECT_SUMMARY.md           ← Ringkasan teknis
├── 📄 INDEX.md                     ← File ini
├── 📄 .gitignore                   ← Git ignore rules
├── 📄 build.gradle                 ← Root gradle config
├── 📄 settings.gradle              ← Project settings
├── 📄 gradle.properties            ← Gradle properties
└── 📄 local.properties.example     ← Template SDK path
```

### 📁 App Module
```
app/
├── 📄 build.gradle                 ← App dependencies & config
├── 📄 proguard-rules.pro           ← ProGuard rules
│
├── src/main/
│   ├── 📄 AndroidManifest.xml      ← App manifest & permissions
│   │
│   ├── java/com/syarhida/kriptometer/
│   │   ├── 📄 MainActivity.kt              ← Main activity
│   │   │
│   │   ├── adapter/
│   │   │   └── 📄 CryptoAdapter.kt         ← RecyclerView adapter
│   │   │
│   │   ├── model/
│   │   │   ├── 📄 Crypto.kt                ← Data model
│   │   │   └── 📄 CryptoResponse.kt        ← API response model
│   │   │
│   │   ├── network/
│   │   │   ├── 📄 ApiService.kt            ← Retrofit interface
│   │   │   └── 📄 RetrofitClient.kt        ← Retrofit singleton
│   │   │
│   │   └── viewmodel/
│   │       └── 📄 CryptoViewModel.kt       ← Business logic
│   │
│   └── res/
│       ├── drawable/
│       │   └── 📄 rank_badge_background.xml    ← Rank badge design
│       │
│       ├── layout/
│       │   ├── 📄 activity_main.xml            ← Main screen layout
│       │   └── 📄 item_crypto.xml              ← Card item layout
│       │
│       ├── values/
│       │   ├── 📄 colors.xml                   ← Color palette
│       │   ├── 📄 strings.xml                  ← Text resources (ID)
│       │   └── 📄 themes.xml                   ← Material theme
│       │
│       ├── xml/
│       │   ├── 📄 backup_rules.xml             ← Backup config
│       │   └── 📄 data_extraction_rules.xml    ← Data extraction
│       │
│       └── mipmap-*/                           ← App icons (auto-generated)
```

---

## 🎨 UI Components Map

### Main Screen (`activity_main.xml`)
```
┌─────────────────────────────────────┐
│ 🟦 Toolbar: "KriptoMeter"          │ ← MaterialToolbar
├─────────────────────────────────────┤
│ ↻ SwipeRefreshLayout                │
│   ┌───────────────────────────────┐ │
│   │ RecyclerView                  │ │
│   │  ├─ item_crypto.xml (Card 1) │ │
│   │  ├─ item_crypto.xml (Card 2) │ │
│   │  ├─ item_crypto.xml (Card 3) │ │
│   │  └─ ...                       │ │
│   └───────────────────────────────┘ │
│                                     │
│ 🔄 ProgressBar (loading state)     │
│ 📝 TextView (loading/empty text)   │
└─────────────────────────────────────┘
```

### Card Item (`item_crypto.xml`)
```
┌─────────────────────────────────────┐
│ MaterialCardView (rounded, elevated)│
│ ┌───────────────────────────────┐   │
│ │ [1] ◄ Rank Badge (circular)   │   │
│ │                               │   │
│ │     Bitcoin          $45,234  │   │
│ │     BTC ◄ Symbol     ▲ Price  │   │
│ └───────────────────────────────┘   │
└─────────────────────────────────────┘
```

---

## 🔗 Data Flow Chart

```
User Opens App
      │
      ▼
  MainActivity
      │
      ├──→ CryptoViewModel.fetchCryptoData()
      │         │
      │         ▼
      │    RetrofitClient
      │         │
      │         ▼
      │    ApiService.getCryptoData()
      │         │
      │         ▼
      │    🌐 API: api.coinlore.net
      │         │
      │         ▼
      │    Response<CryptoResponse>
      │         │
      │         ▼
      │    LiveData<List<Crypto>>
      │         │
      ▼         ▼
  Observer in MainActivity
      │
      ▼
  CryptoAdapter.submitList()
      │
      ▼
  RecyclerView Display
```

---

## 🛠️ Tech Stack Reference

| Layer | Technology | File Location |
|-------|-----------|---------------|
| **UI** | Material Design 3 | `res/values/themes.xml` |
| **Layout** | XML Layouts | `res/layout/` |
| **View** | ViewBinding | `MainActivity.kt` |
| **Logic** | ViewModel + LiveData | `viewmodel/CryptoViewModel.kt` |
| **Data** | Data Classes | `model/Crypto.kt` |
| **Network** | Retrofit + Gson | `network/RetrofitClient.kt` |
| **Async** | Kotlin Coroutines | `CryptoViewModel.kt` |
| **UI List** | RecyclerView | `adapter/CryptoAdapter.kt` |

---

## 📝 Key Files to Review

### 1. Entry Point
- **`MainActivity.kt`** - App starts here
  - Setup RecyclerView
  - Observe ViewModel
  - Handle UI states

### 2. Business Logic
- **`CryptoViewModel.kt`** - Core logic
  - Fetch data from API
  - Manage loading state
  - Handle errors

### 3. Network Layer
- **`RetrofitClient.kt`** - HTTP client
- **`ApiService.kt`** - API endpoints

### 4. UI Adapter
- **`CryptoAdapter.kt`** - RecyclerView adapter
  - Bind crypto data to views
  - Format prices
  - Display rank badges

### 5. Layouts
- **`activity_main.xml`** - Main screen
- **`item_crypto.xml`** - List item card

### 6. Resources
- **`colors.xml`** - Dark theme colors
- **`strings.xml`** - Indonesian text
- **`themes.xml`** - Material theme config

---

## 🎨 Color Palette Quick Reference

```kotlin
Background:     #1A1D2E  // Dark blue-gray
Card Surface:   #2B3A50  // Lighter blue-gray
Primary:        #14AFAF  // Cyan (accent)
Text Primary:   #FFFFFF  // White
Text Secondary: #B0BEC5  // Light gray
Positive:       #00C853  // Green (for future use)
```

---

## 🚀 Quick Commands

```bash
# Open in Android Studio
studio .

# Build project (command line)
./gradlew build

# Install debug APK
./gradlew installDebug

# Run tests
./gradlew test

# Clean build
./gradlew clean
```

---

## 📱 API Endpoint

```
Base URL: https://api.coinlore.net/
Endpoint: GET /api/tickers/

Response:
{
  "data": [
    {
      "rank": "1",
      "name": "Bitcoin",
      "symbol": "BTC",
      "price_usd": "45234.56"
    },
    ...
  ]
}
```

---

## ✅ Pre-Flight Checklist

Sebelum menjalankan app, pastikan:

- [ ] Android Studio terinstall
- [ ] Android SDK terinstall (API 24-34)
- [ ] Gradle sync berhasil
- [ ] `local.properties` sudah ada (SDK path)
- [ ] Device/emulator siap (Android 7.0+)
- [ ] Internet connection aktif

---

## 🎯 Fitur Implemented

- [x] Fetch crypto data dari API
- [x] Tampilkan dalam RecyclerView
- [x] Material Design 3 dark theme
- [x] Custom color scheme (#14AFAF)
- [x] Circular rank badges
- [x] Price formatting
- [x] Pull-to-refresh
- [x] Loading states
- [x] Error handling
- [x] Empty state
- [x] Bahasa Indonesia
- [x] ViewBinding
- [x] MVVM architecture
- [x] Coroutines
- [x] LiveData

---

## 📚 Learn More

### Architecture Pattern: MVVM
```
View (MainActivity) ←→ ViewModel (CryptoViewModel) ←→ Model (Crypto)
                              ↓
                        Repository/Network
```

### Async Pattern: Coroutines
```kotlin
viewModelScope.launch {
    val response = apiService.getCryptoData()
    // Update LiveData
}
```

### Reactive Pattern: LiveData
```kotlin
viewModel.cryptoList.observe(this) { cryptoList ->
    adapter.submitList(cryptoList)
}
```

---

## 🐛 Common Issues & Solutions

| Issue | Solution | File to Check |
|-------|----------|---------------|
| Build error | Clean & Rebuild | `build.gradle` |
| SDK not found | Create `local.properties` | Root folder |
| API not loading | Check internet & API URL | `RetrofitClient.kt` |
| Layout broken | Invalidate caches | Android Studio |
| Wrong colors | Check theme | `themes.xml` |
| Text not in ID | Check strings | `strings.xml` |

---

## 📞 Quick Navigation

- 🏠 **Home**: [`README.md`](README.md)
- 🚀 **Quick Start**: [`QUICKSTART.md`](QUICKSTART.md)
- 🔧 **Setup**: [`SETUP.md`](SETUP.md)
- 📊 **Summary**: [`PROJECT_SUMMARY.md`](PROJECT_SUMMARY.md)
- 📑 **This File**: `INDEX.md`

---

## 🎉 Status

**✅ PROJECT COMPLETE**

Semua komponen telah dibuat sesuai spesifikasi. Project siap untuk:
- Build & Run
- Testing
- Customization
- Deployment

**Happy Coding! 🚀💻**

---

*Last Updated: November 28, 2025*
*Package: com.syarhida.kriptometer*
*Version: 1.0*

