# 🚀 KriptoMeter - Quick Reference Card

## 📱 Project Info
```
Name:         KriptoMeter
Package:      com.syarhida.kriptometer
Version:      1.0.0
Min SDK:      24 (Android 7.0)
Target SDK:   34 (Android 14)
Language:     Kotlin
Architecture: MVVM
```

## 🎨 Color Palette
```kotlin
Background:     #1A1D2E  // Dark blue-gray
Card Surface:   #2B3A50  // Lighter blue-gray  
Primary:        #14AFAF  // Cyan (accent)
Text Primary:   #FFFFFF  // White
Text Secondary: #B0BEC5  // Light gray
Positive:       #00C853  // Green
```

## 📂 File Structure (Quick Access)
```
MainActivity.kt                    → Entry point, UI controller
CryptoViewModel.kt                 → Business logic, data fetching
CryptoAdapter.kt                   → RecyclerView adapter
RetrofitClient.kt                  → API client singleton
ApiService.kt                      → API endpoints interface
Crypto.kt                          → Data model
activity_main.xml                  → Main screen layout
item_crypto.xml                    → Card item layout
colors.xml                         → Color definitions
strings.xml                        → Text resources (ID)
themes.xml                         → Material theme
```

## 🌐 API
```
Base URL:  https://api.coinlore.net/
Endpoint:  GET /api/tickers/
Response:  { "data": [ {...}, {...} ] }
Auth:      None (public API)
```

## 🔑 Key Classes

### MainActivity
```kotlin
// Location: com.syarhida.kriptometer.MainActivity
- setupRecyclerView()     // Init RecyclerView
- setupViewModel()        // Observe LiveData
- setupSwipeRefresh()     // Pull-to-refresh
- showEmptyState()        // Empty UI
- hideEmptyState()        // Hide empty UI
```

### CryptoViewModel
```kotlin
// Location: viewmodel/CryptoViewModel
- fetchCryptoData()       // Fetch from API
- cryptoList: LiveData    // Crypto list
- isLoading: LiveData     // Loading state
- errorMessage: LiveData  // Error messages
```

### CryptoAdapter
```kotlin
// Location: adapter/CryptoAdapter
- submitList()            // Update data
- CryptoViewHolder        // View holder
- bind(crypto)            // Bind data to view
```

### RetrofitClient
```kotlin
// Location: network/RetrofitClient
- apiService              // API service instance
- BASE_URL                // API base URL
```

## 📋 Common Tasks

### Change Colors
```xml
<!-- res/values/colors.xml -->
<color name="primary">#14AFAF</color>
```

### Change Text
```xml
<!-- res/values/strings.xml -->
<string name="app_name">KriptoMeter</string>
```

### Change API
```kotlin
// network/RetrofitClient.kt
private const val BASE_URL = "https://your-api.com/"
```

### Add New Field
```kotlin
// 1. Update model/Crypto.kt
data class Crypto(
    val rank: String,
    val name: String,
    val symbol: String,
    val price_usd: String,
    val new_field: String  // Add this
)

// 2. Update item_crypto.xml
<TextView
    android:id="@+id/textNewField"
    ... />

// 3. Update adapter/CryptoAdapter.kt
binding.textNewField.text = crypto.new_field
```

## 🔧 Build Commands
```bash
./gradlew clean              # Clean build
./gradlew assembleDebug      # Build debug APK
./gradlew assembleRelease    # Build release APK
./gradlew installDebug       # Install to device
./gradlew test               # Run tests
./gradlew lint               # Run lint checks
```

## 🐛 Debug Commands
```bash
adb logcat                   # View all logs
adb logcat | grep Kripto     # Filter KriptoMeter logs
adb shell pm list packages   # List installed packages
adb uninstall com.syarhida.kriptometer  # Uninstall app
```

## 🎯 Key Features Toggle

### Enable/Disable Pull-to-Refresh
```kotlin
// MainActivity.kt
binding.swipeRefreshLayout.isEnabled = true  // or false
```

### Change Loading Text
```xml
<!-- strings.xml -->
<string name="loading">Your text...</string>
```

### Change Card Corner Radius
```xml
<!-- item_crypto.xml -->
<MaterialCardView
    app:cardCornerRadius="16dp">  <!-- Change this -->
```

### Change Card Elevation
```xml
<!-- item_crypto.xml -->
<MaterialCardView
    app:cardElevation="4dp">  <!-- Change this -->
```

## 🔄 Data Flow (Quick)
```
User Action → MainActivity → ViewModel → Retrofit → API
                                ↓
                            LiveData
                                ↓
                            Observer → Adapter → RecyclerView
```

## 📱 View IDs (Quick Access)
```kotlin
// activity_main.xml
binding.toolbar              // Toolbar
binding.recyclerView         // RecyclerView
binding.swipeRefreshLayout   // SwipeRefresh
binding.progressBar          // Loading indicator
binding.textLoading          // Loading text
binding.textEmpty            // Empty state text

// item_crypto.xml (in adapter)
binding.textRank             // Rank badge
binding.textName             // Crypto name
binding.textSymbol           // Crypto symbol
binding.textPrice            // USD price
```

## 🎨 UI Dimensions
```xml
Card Padding:        16dp
Card Corner Radius:  16dp
Card Elevation:      4dp
Rank Badge Size:     48dp x 48dp
RecyclerView Padding: 16dp
Card Margin Bottom:  12dp
```

## 🔤 Text Sizes
```xml
Crypto Name:   18sp (Bold)
Symbol:        12sp (Regular)
Price:         20sp (Bold)
Rank:          16sp (Bold)
Loading:       14sp (Regular)
Empty State:   16sp (Regular)
```

## 🔍 Important Files to Edit

### UI Changes
- `activity_main.xml` - Main layout
- `item_crypto.xml` - Card layout
- `colors.xml` - Colors
- `themes.xml` - Theme

### Logic Changes
- `MainActivity.kt` - UI logic
- `CryptoViewModel.kt` - Business logic
- `CryptoAdapter.kt` - List adapter

### Data Changes
- `Crypto.kt` - Data model
- `ApiService.kt` - API endpoints
- `RetrofitClient.kt` - API client

### Resources
- `strings.xml` - Text (Indonesian)
- `AndroidManifest.xml` - App config

## 📦 Dependencies (Quick)
```gradle
// Network
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'

// Coroutines
implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3'

// ViewModel & LiveData
implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2'
implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.6.2'

// UI
implementation 'androidx.recyclerview:recyclerview:1.3.2'
implementation 'androidx.swiperefreshlayout:swiperefreshlayout:1.1.0'
implementation 'com.google.android.material:material:1.11.0'
```

## ⚡ Quick Fixes

### App Crashes on Launch
```kotlin
// Check AndroidManifest.xml
<uses-permission android:name="android.permission.INTERNET" />
```

### Data Not Loading
```kotlin
// Check RetrofitClient.kt
private const val BASE_URL = "https://api.coinlore.net/"
```

### Wrong Colors
```xml
<!-- Check themes.xml -->
<item name="colorPrimary">@color/primary</item>
```

### Layout Issues
```
File → Invalidate Caches / Restart
```

## 🔐 Permissions Required
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

## 🎯 Testing Checklist
```
□ App launches successfully
□ Data loads from API
□ Pull-to-refresh works
□ Loading indicator shows
□ Error handling works
□ Empty state works
□ Cards are clickable (ripple)
□ Dark theme applied
□ Text in Indonesian
```

## 📞 Quick Links
- **README**: Project overview → `README.md`
- **Quick Start**: Fast setup → `QUICKSTART.md`
- **Full Setup**: Detailed guide → `SETUP.md`
- **Summary**: Tech details → `PROJECT_SUMMARY.md`
- **Build**: Build instructions → `BUILD_INSTRUCTIONS.md`
- **Index**: Navigation → `INDEX.md`

## 🆘 Emergency Fixes
```bash
# Build won't work?
./gradlew clean
File → Invalidate Caches / Restart

# SDK not found?
# Create: local.properties
# Add: sdk.dir=C:\\path\\to\\android\\sdk

# Dependencies error?
File → Sync Project with Gradle Files

# Emulator won't start?
Tools → Device Manager → Wipe Data

# App won't install?
adb uninstall com.syarhida.kriptometer
./gradlew installDebug
```

## 💡 Pro Tips
```
1. Use Logcat to debug: View → Tool Windows → Logcat
2. Use Layout Inspector: Tools → Layout Inspector
3. Use Database Inspector: View → Tool Windows → App Inspection
4. Format code: Ctrl+Alt+L (Windows) / Cmd+Option+L (Mac)
5. Optimize imports: Ctrl+Alt+O (Windows) / Cmd+Option+O (Mac)
```

---

**Print this page for quick reference! 📄**

*Version 1.0.0 - Last Updated: November 28, 2025*

