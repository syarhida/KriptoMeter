# KriptoMeter

Aplikasi Android untuk menampilkan informasi cryptocurrency real-time.

## Fitur

- 📊 Menampilkan daftar cryptocurrency dengan data real-time
- 🪙 Icon cryptocurrency dengan circular border
- 💰 Informasi harga dalam USD dan Rupiah
- 📈 Indikator perubahan harga 24 jam dengan color coding
- 🔄 Pull-to-refresh untuk update data
- 📄 Pagination (load 20 item per halaman, maksimal 100)
- 🌙 Dark theme dengan Material Design 3

## Teknologi

- **Language**: Kotlin
- **Architecture**: MVVM
- **Networking**: Retrofit 2.9.0 + Gson
- **Async**: Kotlin Coroutines
- **UI**: Material Design 3, ViewBinding
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)

## Struktur Project

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

## API

API Endpoint: https://api.coinlore.net/api/tickers/

## Lisensi

MIT License
