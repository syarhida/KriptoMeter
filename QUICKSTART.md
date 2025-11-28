# Quick Start Guide - KriptoMeter

## 🚀 Cara Cepat Menjalankan Aplikasi

### 1. Persiapan (5 menit)

```bash
# Clone atau buka project
cd KriptoMeter
```

### 2. Buka di Android Studio

- File → Open → Pilih folder `KriptoMeter`
- Tunggu Gradle sync selesai (otomatis)

### 3. Setup SDK Path (jika diperlukan)

Jika muncul error "SDK location not found":

**Windows:**
```
Buat file: local.properties
Isi dengan: sdk.dir=C:\\Users\\YourUsername\\AppData\\Local\\Android\\Sdk
```

**macOS:**
```
Buat file: local.properties
Isi dengan: sdk.dir=/Users/YourUsername/Library/Android/sdk
```

### 4. Run Aplikasi

1. Hubungkan Android device (USB debugging ON) ATAU
2. Buka Android Emulator
3. Klik tombol Run ▶️ (atau Shift+F10)
4. Pilih device
5. Tunggu build selesai (~2-3 menit pertama kali)

### 5. Test Aplikasi

✅ App terbuka dengan toolbar "KriptoMeter"
✅ Loading indicator muncul
✅ List cryptocurrency muncul dengan:
   - Rank badge (circular, cyan)
   - Nama crypto (bold, putih)
   - Symbol (kecil, abu-abu)
   - Harga USD (cyan, kanan)
✅ Swipe down untuk refresh
✅ Dark theme terlihat

## 🎨 Preview Hasil

```
┌────────────────────────────────┐
│     🟦 KriptoMeter             │ ← Toolbar
├────────────────────────────────┤
│  ┌──────────────────────────┐  │
│  │ [1] Bitcoin         $45K  │  │ ← Card 1
│  │     BTC                   │  │
│  └──────────────────────────┘  │
│  ┌──────────────────────────┐  │
│  │ [2] Ethereum        $3.2K │  │ ← Card 2
│  │     ETH                   │  │
│  └──────────────────────────┘  │
│  ┌──────────────────────────┐  │
│  │ [3] Tether          $1.00 │  │ ← Card 3
│  │     USDT                  │  │
│  └──────────────────────────┘  │
└────────────────────────────────┘
```

## 🎯 Fitur yang Bisa Dicoba

1. **Scroll list** - Lihat lebih banyak crypto
2. **Pull to refresh** - Swipe down untuk update data
3. **Tap card** - Lihat ripple effect
4. **Matikan internet** - Lihat error message

## 📱 Minimum Requirements

- Android 7.0 (API 24) atau lebih tinggi
- Koneksi internet aktif
- ~50 MB storage untuk build files

## 🐛 Troubleshooting Cepat

**Problem**: Build gagal
**Solution**: Build → Clean Project, kemudian Build → Rebuild Project

**Problem**: API tidak load
**Solution**: 
- Cek koneksi internet
- Restart app
- Swipe down untuk refresh

**Problem**: Layout tidak sesuai
**Solution**: File → Invalidate Caches / Restart

## 📚 File Penting

- `MainActivity.kt` - Main logic
- `activity_main.xml` - Main layout
- `item_crypto.xml` - Card layout
- `CryptoViewModel.kt` - Business logic
- `colors.xml` - Color scheme
- `strings.xml` - Text resources

## 🔗 API yang Digunakan

```
https://api.coinlore.net/api/tickers/
```

API ini gratis dan tidak memerlukan API key.

## ✨ Tech Stack

- **Kotlin** - Programming language
- **Retrofit** - API calls
- **Coroutines** - Async operations
- **LiveData** - Reactive UI
- **Material 3** - Design system
- **ViewBinding** - Type-safe views

## 📖 Dokumentasi Lengkap

- `README.md` - Overview project
- `SETUP.md` - Setup detail lengkap
- `PROJECT_SUMMARY.md` - Ringkasan komponen

## 🎉 Selamat!

Aplikasi KriptoMeter Anda sekarang siap digunakan!

Untuk kustomisasi lebih lanjut, baca `SETUP.md`.

---

**Happy Coding! 🚀**

