# Changelog

All notable changes to KriptoMeter project will be documented in this file.

## [1.0.0] - 2025-11-28

### 🎉 Initial Release

#### ✨ Features Added
- **Cryptocurrency Data Display**
  - Fetch real-time crypto data from CoinLore API
  - Display rank, name, symbol, and price (USD)
  - Auto-refresh on app launch
  
- **Modern UI/UX**
  - Material Design 3 dark theme implementation
  - Custom color scheme with cyan (#14AFAF) accent
  - Rounded corner cards (16dp radius)
  - Circular rank badges with primary color background
  - Smooth elevation and shadows
  - Ripple effect on card interactions
  
- **Interactive Features**
  - Pull-to-refresh functionality
  - Loading state with progress indicator
  - Error handling with user-friendly messages
  - Empty state display
  
- **Technical Implementation**
  - MVVM architecture pattern
  - Kotlin Coroutines for async operations
  - LiveData for reactive UI updates
  - ViewBinding for type-safe view access
  - Retrofit + Gson for API communication
  - RecyclerView with ListAdapter and DiffUtil
  
- **Localization**
  - Full Indonesian language support
  - Localized error messages
  - Indonesian UI labels

#### 🎨 Design System
- **Color Palette**
  - Background: #1A1D2E (Dark blue-gray)
  - Card Surface: #2B3A50 (Lighter blue-gray)
  - Primary: #14AFAF (Cyan)
  - Text Primary: #FFFFFF (White)
  - Text Secondary: #B0BEC5 (Light gray)
  
- **Typography**
  - Crypto Name: 18sp, Bold, White
  - Symbol: 12sp, Regular, Gray
  - Price: 20sp, Bold, Cyan
  - Rank: 16sp, Bold, White

#### 📦 Dependencies
- androidx.core:core-ktx:1.12.0
- androidx.appcompat:appcompat:1.6.1
- com.google.android.material:material:1.11.0
- com.squareup.retrofit2:retrofit:2.9.0
- com.squareup.retrofit2:converter-gson:2.9.0
- org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3
- androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2
- androidx.lifecycle:lifecycle-livedata-ktx:2.6.2
- androidx.recyclerview:recyclerview:1.3.2
- androidx.swiperefreshlayout:swiperefreshlayout:1.1.0

#### 🏗️ Project Structure
```
com.syarhida.kriptometer/
├── model/           (Data classes)
├── network/         (API service & client)
├── adapter/         (RecyclerView adapter)
├── viewmodel/       (Business logic)
└── MainActivity.kt  (UI controller)
```

#### 📱 Requirements
- Min SDK: 24 (Android 7.0 Nougat)
- Target SDK: 34 (Android 14)
- Compile SDK: 34
- Kotlin: 1.9.10
- Gradle: 8.0

#### 🔒 Permissions
- INTERNET (untuk API calls)
- ACCESS_NETWORK_STATE (untuk network checking)

#### 📄 Documentation
- README.md - Project overview
- QUICKSTART.md - Quick start guide
- SETUP.md - Detailed setup instructions
- PROJECT_SUMMARY.md - Technical summary
- INDEX.md - Documentation index
- BUILD_INSTRUCTIONS.md - Build & deployment guide
- CHANGELOG.md - This file

#### 🎯 API Integration
- **Provider**: CoinLore
- **Endpoint**: https://api.coinlore.net/api/tickers/
- **Method**: GET
- **Response Format**: JSON
- **Authentication**: None required (public API)

#### ✅ Testing
- Tested on Android 7.0 (API 24) and above
- Tested on multiple screen sizes
- Network error handling verified
- Pull-to-refresh functionality verified
- Loading states verified

---

## 📋 Roadmap

### [1.1.0] - Planned
- [ ] Search functionality
- [ ] Sorting options (by rank, price, name)
- [ ] Favorite cryptocurrencies
- [ ] Price change indicators (24h)
- [ ] Detail page for each crypto
- [ ] Share functionality
- [ ] Settings page (refresh interval, etc.)

### [1.2.0] - Planned
- [ ] Price charts (7d, 30d, 1y)
- [ ] Price alerts/notifications
- [ ] Multiple currency support (IDR, EUR, etc.)
- [ ] Dark/Light theme toggle
- [ ] Offline caching
- [ ] Widget support

### [2.0.0] - Future
- [ ] Portfolio tracking
- [ ] Price predictions
- [ ] News integration
- [ ] Multi-exchange support
- [ ] Real-time price updates (WebSocket)
- [ ] Crypto converter calculator

---

## 🐛 Known Issues

### Version 1.0.0
- None reported yet

---

## 📝 Notes

### Breaking Changes
- None (initial release)

### Deprecations
- None

### Security
- No sensitive data stored
- HTTPS-only API communication
- No authentication tokens required

---

## 🔗 Links

- **Repository**: [GitHub](https://github.com/your-username/kriptometer) *(update this)*
- **API Documentation**: [CoinLore API](https://www.coinlore.com/cryptocurrency-data-api)
- **Material Design**: [Material 3](https://m3.material.io/)

---

## 👥 Contributors

- **Developer**: Syarhida
- **Package**: com.syarhida.kriptometer
- **Year**: 2025

---

## 📄 License

This project is created for educational purposes.

---

## 📊 Statistics

### Version 1.0.0
- **Lines of Code**: ~800 lines
- **Files Created**: 25+ files
- **Screens**: 1 (Main screen)
- **API Endpoints**: 1
- **Dependencies**: 12
- **Supported Languages**: 1 (Indonesian)

---

**Last Updated**: November 28, 2025

---

*Format ini mengikuti standar [Keep a Changelog](https://keepachangelog.com/en/1.0.0/)*

