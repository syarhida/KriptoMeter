# Setup Guide - KriptoMeter

## Prasyarat

- Android Studio (versi terbaru, minimal Arctic Fox)
- JDK 8 atau lebih tinggi
- Android SDK dengan API Level 24-34
- Koneksi internet untuk download dependencies

## Langkah-langkah Setup

### 1. Clone/Download Project

```bash
git clone <repository-url>
cd KriptoMeter
```

### 2. Buka di Android Studio

1. Buka Android Studio
2. Pilih **File > Open**
3. Navigasi ke folder project KriptoMeter
4. Klik **OK**

### 3. Sync Gradle

Android Studio akan otomatis melakukan Gradle sync. Jika tidak:
1. Klik **File > Sync Project with Gradle Files**
2. Tunggu hingga proses selesai

### 4. Setup Android SDK

Pastikan Android SDK sudah terinstall:
1. Buka **Tools > SDK Manager**
2. Install:
   - Android SDK Platform 34 (Android 14)
   - Android SDK Build-Tools 34.0.0
   - Google Play services

### 5. Create Launcher Icon (Opsional)

Untuk membuat icon aplikasi:
1. Klik kanan pada folder `res`
2. Pilih **New > Image Asset**
3. Konfigurasi icon:
   - **Foreground Layer**: Pilih gambar atau clipart cryptocurrency
   - **Background Color**: #14AFAF (cyan)
   - **Icon Shape**: Circle atau Squircle
4. Klik **Next** dan **Finish**

### 6. Run Aplikasi

1. Hubungkan device Android atau jalankan emulator
2. Klik tombol **Run** (▶️) atau tekan **Shift+F10**
3. Pilih device target
4. Tunggu build selesai

## Troubleshooting

### Error: SDK Location not found

Buat file `local.properties` di root project:
```properties
sdk.dir=C\:\\Users\\YourUsername\\AppData\\Local\\Android\\Sdk
```
*(Sesuaikan path dengan lokasi Android SDK Anda)*

### Error: Internet Permission

Pastikan `AndroidManifest.xml` sudah mengandung:
```xml
<uses-permission android:name="android.permission.INTERNET" />
```

### Error: Gradle Build Failed

1. Bersihkan project: **Build > Clean Project**
2. Rebuild: **Build > Rebuild Project**
3. Invalidate Caches: **File > Invalidate Caches / Restart**

### API Not Loading

1. Cek koneksi internet device/emulator
2. Pastikan API CoinLore masih aktif: `https://api.coinlore.net/api/tickers/`
3. Lihat Logcat untuk error messages

## File Structure Lengkap

```
KriptoMeter/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/syarhida/kriptometer/
│   │       │   ├── adapter/
│   │       │   │   └── CryptoAdapter.kt
│   │       │   ├── model/
│   │       │   │   ├── Crypto.kt
│   │       │   │   └── CryptoResponse.kt
│   │       │   ├── network/
│   │       │   │   ├── ApiService.kt
│   │       │   │   └── RetrofitClient.kt
│   │       │   ├── viewmodel/
│   │       │   │   └── CryptoViewModel.kt
│   │       │   └── MainActivity.kt
│   │       ├── res/
│   │       │   ├── drawable/
│   │       │   │   └── rank_badge_background.xml
│   │       │   ├── layout/
│   │       │   │   ├── activity_main.xml
│   │       │   │   └── item_crypto.xml
│   │       │   ├── values/
│   │       │   │   ├── colors.xml
│   │       │   │   ├── strings.xml
│   │       │   │   └── themes.xml
│   │       │   └── xml/
│   │       │       ├── backup_rules.xml
│   │       │       └── data_extraction_rules.xml
│   │       └── AndroidManifest.xml
│   ├── build.gradle
│   └── proguard-rules.pro
├── gradle/
├── build.gradle
├── settings.gradle
├── gradle.properties
└── README.md
```

## Dependencies yang Digunakan

- **Retrofit 2.9.0**: HTTP client untuk API calls
- **Gson 2.9.0**: JSON serialization/deserialization
- **Kotlin Coroutines 1.7.3**: Async operations
- **AndroidX Lifecycle 2.6.2**: ViewModel & LiveData
- **RecyclerView 1.3.2**: List display
- **SwipeRefreshLayout 1.1.0**: Pull to refresh
- **Material Components 1.11.0**: Material Design 3

## Testing

### Manual Testing Checklist

- [ ] App bisa launch dengan sukses
- [ ] Data cryptocurrency muncul di list
- [ ] Pull-to-refresh bekerja
- [ ] Loading indicator muncul saat fetch data
- [ ] Error message muncul saat gagal load data
- [ ] Card ripple effect bekerja saat di-tap
- [ ] Dark theme diterapkan dengan benar
- [ ] Scroll list berjalan smooth

## Kustomisasi

### Mengubah Warna Theme

Edit `app/src/main/res/values/colors.xml`:

```xml
<color name="primary">#14AFAF</color> <!-- Ubah ke warna pilihan Anda -->
```

### Menambah Field Data

1. Update `Crypto.kt` dengan field baru
2. Update `item_crypto.xml` untuk tampilkan field
3. Update `CryptoAdapter.kt` untuk bind data

### Mengubah API

Edit `RetrofitClient.kt`:

```kotlin
private const val BASE_URL = "https://your-api-url.com/"
```

## Support

Jika ada pertanyaan atau issues, silakan buat issue di repository ini.

## Lisensi

Project ini untuk tujuan pembelajaran.

