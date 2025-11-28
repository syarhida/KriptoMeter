# KriptoMeter

Aplikasi Android untuk menampilkan informasi cryptocurrency menggunakan Material Design 3 dengan dark theme.

## 📱 Fitur

- Menampilkan daftar cryptocurrency dari API CoinLore
- Material Design 3 dengan dark theme yang elegan
- Pull-to-refresh untuk memperbarui data
- Loading state dan error handling yang baik
- UI responsif dengan RecyclerView dan CardView

## 🎨 Desain

- **Background**: #1A1D2E
- **Card Surface**: #2B3A50
- **Primary Color**: #14AFAF (Cyan)
- **Text Primary**: #FFFFFF
- **Text Secondary**: #B0BEC5

## 🔧 Teknologi

- **Language**: Kotlin
- **Architecture**: MVVM dengan ViewModel
- **Networking**: Retrofit + Gson
- **Async**: Kotlin Coroutines
- **UI**: Material Design 3, ViewBinding
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)

## 📦 Struktur Project

```
com.syarhida.kriptometer/
├── model/
│   ├── Crypto.kt
│   └── CryptoResponse.kt
├── network/
│   ├── ApiService.kt
│   └── RetrofitClient.kt
├── adapter/
│   └── CryptoAdapter.kt
├── viewmodel/
│   └── CryptoViewModel.kt
└── MainActivity.kt
```

## 🚀 Cara Build

1. Clone repository ini
2. Buka project di Android Studio
3. Sync Gradle
4. Run aplikasi di emulator atau device fisik

## 📝 API

Aplikasi ini menggunakan API dari [CoinLore](https://www.coinlore.com/cryptocurrency-data-api):
- Endpoint: `https://api.coinlore.net/api/tickers/`

## 📄 Lisensi

Project ini dibuat untuk tujuan pembelajaran.

## 👨‍💻 Developer

- Package Name: com.syarhida.kriptometer
- Bahasa: Indonesia

