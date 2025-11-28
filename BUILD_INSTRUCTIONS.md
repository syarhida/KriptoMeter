# 🏗️ Build Instructions - KriptoMeter

## Cara Build & Run Aplikasi

### Metode 1: Android Studio (Recommended) ⭐

#### Step 1: Buka Project
```bash
1. Launch Android Studio
2. File → Open
3. Pilih folder: D:\SC\KriptoMeter
4. Klik OK
```

#### Step 2: Wait for Gradle Sync
```
- Android Studio akan otomatis sync Gradle
- Tunggu hingga proses selesai (~2-5 menit pertama kali)
- Lihat progress di status bar bawah
```

#### Step 3: Setup Device

**Opsi A: Emulator**
```
1. Tools → Device Manager
2. Create Device (jika belum ada)
   - Device: Pixel 5
   - System Image: Android 14 (API 34)
   - Klik Download & Install
3. Klik ▶️ untuk start emulator
```

**Opsi B: Physical Device**
```
1. Aktifkan Developer Options di phone:
   - Settings → About Phone
   - Tap "Build Number" 7 kali
2. Settings → Developer Options
   - Enable "USB Debugging"
3. Sambungkan phone ke PC via USB
4. Accept USB debugging prompt di phone
```

#### Step 4: Build & Run
```
1. Klik tombol Run ▶️ (toolbar atas)
   ATAU
   Shift + F10
2. Pilih device (emulator atau phone)
3. Klik OK
4. Tunggu build selesai (~1-3 menit)
5. App akan otomatis install & launch
```

---

### Metode 2: Command Line (Advanced) 🖥️

#### Prerequisites
```bash
# Windows
set ANDROID_HOME=C:\Users\YourUsername\AppData\Local\Android\Sdk
set PATH=%PATH%;%ANDROID_HOME%\platform-tools

# macOS/Linux
export ANDROID_HOME=/Users/YourUsername/Library/Android/sdk
export PATH=$PATH:$ANDROID_HOME/platform-tools
```

#### Build Commands

**Debug Build**
```bash
cd D:\SC\KriptoMeter

# Clean previous builds
./gradlew clean

# Build debug APK
./gradlew assembleDebug

# Output: app/build/outputs/apk/debug/app-debug.apk
```

**Release Build**
```bash
# Build release APK (unsigned)
./gradlew assembleRelease

# Output: app/build/outputs/apk/release/app-release-unsigned.apk
```

**Install to Device**
```bash
# Install debug APK
./gradlew installDebug

# Run app
adb shell am start -n com.syarhida.kriptometer/.MainActivity
```

---

### Metode 3: Build APK for Distribution 📦

#### Step 1: Generate Signing Key (One-time)
```bash
keytool -genkey -v -keystore kriptometer.keystore -alias kriptometer -keyalg RSA -keysize 2048 -validity 10000

# Simpan password dengan aman!
```

#### Step 2: Configure Signing
Tambahkan ke `app/build.gradle`:
```gradle
android {
    signingConfigs {
        release {
            storeFile file("../kriptometer.keystore")
            storePassword "your_password"
            keyAlias "kriptometer"
            keyPassword "your_password"
        }
    }
    buildTypes {
        release {
            signingConfig signingConfigs.release
            minifyEnabled true
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}
```

#### Step 3: Build Signed APK
```bash
./gradlew assembleRelease

# Output: app/build/outputs/apk/release/app-release.apk
```

---

## 🔍 Verifikasi Build

### Check APK Info
```bash
aapt dump badging app/build/outputs/apk/debug/app-debug.apk

# Verify:
# - package: name='com.syarhida.kriptometer'
# - versionCode='1'
# - versionName='1.0'
# - minSdkVersion:'24'
# - targetSdkVersion:'34'
```

### Test Installation
```bash
# Install via ADB
adb install app/build/outputs/apk/debug/app-debug.apk

# Check if installed
adb shell pm list packages | grep kriptometer

# Launch app
adb shell am start -n com.syarhida.kriptometer/.MainActivity
```

---

## 📊 Build Variants

| Variant | Minify | Debuggable | Use Case |
|---------|--------|------------|----------|
| **debug** | ❌ No | ✅ Yes | Development & Testing |
| **release** | ✅ Yes | ❌ No | Production Distribution |

---

## 🛠️ Build Troubleshooting

### Error: "SDK location not found"
**Solution:**
```bash
# Create local.properties
echo "sdk.dir=C:\\Users\\YourUsername\\AppData\\Local\\Android\\Sdk" > local.properties
```

### Error: "Gradle sync failed"
**Solution:**
```bash
# Clear Gradle cache
rm -rf .gradle
./gradlew clean

# Re-sync in Android Studio:
File → Invalidate Caches / Restart
```

### Error: "Build tools version not found"
**Solution:**
```
1. Open SDK Manager in Android Studio
2. Install Android SDK Build-Tools 34.0.0
3. Sync Gradle again
```

### Error: "AAPT2 error"
**Solution:**
Add to `gradle.properties`:
```properties
android.enableAapt2=true
```

### Error: "Out of memory"
**Solution:**
Edit `gradle.properties`:
```properties
org.gradle.jvmargs=-Xmx4096m -Dfile.encoding=UTF-8
```

---

## 📈 Build Performance Tips

### 1. Enable Parallel Builds
`gradle.properties`:
```properties
org.gradle.parallel=true
org.gradle.workers.max=4
```

### 2. Enable Build Cache
`gradle.properties`:
```properties
org.gradle.caching=true
android.enableBuildCache=true
```

### 3. Enable Configuration Cache
`gradle.properties`:
```properties
org.gradle.configuration-cache=true
```

### 4. Increase Heap Size
`gradle.properties`:
```properties
org.gradle.jvmargs=-Xmx4096m -XX:MaxMetaspaceSize=512m
```

---

## 📱 Deploy to Device

### Via Android Studio
```
1. Run → Edit Configurations
2. Select "app"
3. General → Installation Options:
   - Deploy: Default APK
   - Always install with PM
4. Apply → OK
5. Click Run ▶️
```

### Via ADB
```bash
# Install
adb install -r app-debug.apk

# Uninstall (if needed)
adb uninstall com.syarhida.kriptometer

# Check logs
adb logcat | grep KriptoMeter
```

---

## 🔄 CI/CD Setup (Optional)

### GitHub Actions Example
`.github/workflows/android.yml`:
```yaml
name: Android CI

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v3
    - name: Set up JDK 17
      uses: actions/setup-java@v3
      with:
        java-version: '17'
        distribution: 'temurin'
    - name: Grant execute permission for gradlew
      run: chmod +x gradlew
    - name: Build with Gradle
      run: ./gradlew assembleDebug
    - name: Upload APK
      uses: actions/upload-artifact@v3
      with:
        name: app-debug
        path: app/build/outputs/apk/debug/app-debug.apk
```

---

## 📦 Build Output Locations

```
KriptoMeter/
└── app/
    └── build/
        └── outputs/
            ├── apk/
            │   ├── debug/
            │   │   └── app-debug.apk           ← Debug APK
            │   └── release/
            │       └── app-release.apk         ← Release APK
            └── bundle/
                └── release/
                    └── app-release.aab         ← Android App Bundle
```

---

## ✅ Build Checklist

Sebelum build final:

- [ ] Update `versionCode` & `versionName` di `app/build.gradle`
- [ ] Test di minimal 2 devices/emulators berbeda
- [ ] Test landscape orientation
- [ ] Test dengan internet OFF (error handling)
- [ ] Test pull-to-refresh
- [ ] Check ProGuard rules (jika release build)
- [ ] Scan APK dengan virus scanner
- [ ] Test install dari APK file

---

## 🎯 Build Success Indicators

✅ Build successful jika:
```
1. Gradle build completes without errors
2. APK file generated di outputs folder
3. App installs on device
4. App launches without crash
5. Data loads from API
6. UI matches design specs
7. No lint errors (critical)
```

---

## 📞 Need Help?

**Build Issues:**
- Check `BUILD.log` di project root
- Run: `./gradlew build --stacktrace`
- Check Android Studio's "Build" tab

**Runtime Issues:**
- Check Logcat: `adb logcat`
- Filter by package: `adb logcat | grep com.syarhida.kriptometer`

---

## 🚀 Quick Build Commands Summary

```bash
# Clean
./gradlew clean

# Build debug
./gradlew assembleDebug

# Build release
./gradlew assembleRelease

# Install debug
./gradlew installDebug

# Run tests
./gradlew test

# Lint check
./gradlew lint

# Generate bundle (for Play Store)
./gradlew bundleRelease
```

---

**Build Time Estimates:**
- First build: ~5-10 minutes (download dependencies)
- Subsequent builds: ~1-3 minutes
- Incremental builds: ~30-60 seconds

---

*Good luck with your build! 🎉*

