# 🚀 Quick Setup Guide: Crypto Icons

## 📋 Ringkasan Perubahan

Aplikasi KriptoMeter sekarang memiliki **icon cryptocurrency** dengan fitur:

✅ Icon circular otomatis  
✅ Load dari folder assets  
✅ Rank badge tanpa background  
✅ Fallback to default icon  

---

## 🎯 Cara Setup Icons (3 Langkah Mudah!)

### Opsi 1: Otomatis dengan Python Script ⭐ (RECOMMENDED)

#### Step 1: Install Requirements
```bash
pip install requests
```

#### Step 2: Run Download Script
```bash
python download_crypto_icons.py
```

Script akan:
- Download 30+ crypto icons
- Save ke `app/src/main/assets/img/`
- Format nama UPPERCASE otomatis
- Show progress & summary

#### Step 3: Build & Run
```bash
# Di Android Studio:
Build → Clean Project
Build → Rebuild Project
Run ▶️
```

**Done!** Icons akan muncul di app! 🎉

---

### Opsi 2: Manual Download

#### Step 1: Download Icons

**Source**: https://github.com/spothq/cryptocurrency-icons

```bash
# Clone repository
git clone https://github.com/spothq/cryptocurrency-icons.git

# Copy icons yang dibutuhkan
cd cryptocurrency-icons/128/color/
```

#### Step 2: Rename & Copy

```bash
# Rename ke UPPERCASE
mv btc.png BTC.png
mv eth.png ETH.png
mv usdt.png USDT.png
# ... dll

# Copy ke assets
cp *.png /path/to/KriptoMeter/app/src/main/assets/img/
```

#### Step 3: Build & Run

```bash
# Clean & Rebuild di Android Studio
Build → Clean Project
Build → Rebuild Project
Run ▶️
```

---

## 📁 Struktur File

```
KriptoMeter/
├── app/src/main/assets/
│   └── img/
│       ├── README.md      ← Dokumentasi
│       ├── BTC.png        ← Bitcoin
│       ├── ETH.png        ← Ethereum
│       ├── USDT.png       ← Tether
│       ├── XRP.png        ← XRP
│       ├── BNB.png        ← Binance Coin
│       └── ...            ← Icons lainnya
│
├── download_crypto_icons.py  ← Auto download script
├── ICON_SETUP_GUIDE.md        ← File ini
└── CRYPTO_ICONS_IMPLEMENTATION.md  ← Dokumentasi teknis
```

---

## 🎨 Tampilan Baru

### Before (No Icons):
```
┌────────────────────────────────┐
│  ╭───╮                         │
│  │ 1 │  BTC    $91,532.34      │
│  ╰───╯  Bitcoin Rp 1.437.057   │
└────────────────────────────────┘
```

### After (With Icons):
```
┌────────────────────────────────┐
│  1  🟠  BTC    $91,532.34      │  ← Icon Bitcoin
│      ₿   Bitcoin Rp 1.437.057  │
├────────────────────────────────┤
│  2  💎  ETH    $3,036.40       │  ← Icon Ethereum
│      Ξ   Ethereum Rp 47.671    │
└────────────────────────────────┘
```

**Features:**
- Icon 40x40 dp (circular)
- Border cyan (#14AFAF)
- Rank text only (no background)
- Symbol & name aligned dengan icon

---

## 📝 Format Icon

### ✅ Correct:
- **Nama**: `BTC.png`, `ETH.png`, `USDT.png`
- **Format**: PNG
- **Size**: 40x40 px atau lebih
- **Background**: Transparent atau solid (akan jadi circle otomatis)

### ❌ Wrong:
- ~~`btc.png`~~ (lowercase)
- ~~`Bitcoin.png`~~ (full name)
- ~~`1.png`~~ (number)
- ~~`btc.jpg`~~ (not PNG)

---

## 🔍 Troubleshooting

### Problem: Icon tidak muncul

**Solusi:**
1. Check nama file UPPERCASE (`BTC.png` not `btc.png`)
2. Check lokasi folder (`app/src/main/assets/img/`)
3. Clean & Rebuild project
4. Restart Android Studio

### Problem: Icon masih kotak

**Solusi:**
- Code sudah handle automatic circular
- Jika masih kotak, check `getCircularBitmap()` di adapter
- Pastikan `circle_image_background.xml` ada

### Problem: Script error

**Solusi:**
```bash
# Install dependencies
pip install requests

# Run dengan Python 3
python3 download_crypto_icons.py
```

---

## 💡 Tips

### 1. Batch Rename (Windows PowerShell)
```powershell
# Uppercase all PNG files
Get-ChildItem *.png | Rename-Item -NewName { $_.Name.ToUpper() }
```

### 2. Batch Rename (Linux/Mac)
```bash
# Uppercase all PNG files
for file in *.png; do
    mv "$file" "${file^^}"
done
```

### 3. Check Icon Size
```bash
# Using ImageMagick
identify *.png
```

### 4. Optimize Icons
```bash
# Resize to 128x128
mogrify -resize 128x128 *.png

# Remove metadata
mogrify -strip *.png
```

---

## 📋 Icon Checklist

### Required Icons (Top 10):
- [ ] BTC.png (Bitcoin)
- [ ] ETH.png (Ethereum)
- [ ] USDT.png (Tether)
- [ ] BNB.png (Binance Coin)
- [ ] XRP.png (Ripple)
- [ ] SOL.png (Solana)
- [ ] USDC.png (USD Coin)
- [ ] ADA.png (Cardano)
- [ ] DOGE.png (Dogecoin)
- [ ] TRX.png (TRON)

### Optional (Top 20):
- [ ] TON.png (Toncoin)
- [ ] LINK.png (Chainlink)
- [ ] MATIC.png (Polygon)
- [ ] DOT.png (Polkadot)
- [ ] DAI.png (Dai)
- [ ] LTC.png (Litecoin)
- [ ] SHIB.png (Shiba Inu)
- [ ] BCH.png (Bitcoin Cash)
- [ ] AVAX.png (Avalanche)
- [ ] UNI.png (Uniswap)

---

## 🚀 Quick Commands

### Download Icons
```bash
python download_crypto_icons.py
```

### Clean Build
```bash
./gradlew clean
./gradlew assembleDebug
```

### Check Assets
```bash
# Windows
dir app\src\main\assets\img

# Linux/Mac
ls -la app/src/main/assets/img/
```

---

## 📚 Documentation Files

1. **ICON_SETUP_GUIDE.md** (This file)
   - Quick setup guide
   - How to download icons
   - Troubleshooting

2. **CRYPTO_ICONS_IMPLEMENTATION.md**
   - Technical details
   - Code explanation
   - Advanced customization

3. **app/src/main/assets/img/README.md**
   - Icon naming convention
   - Download sources
   - Format specifications

4. **download_crypto_icons.py**
   - Auto download script
   - 30+ crypto icons
   - UPPERCASE formatting

---

## ✅ Verification

Setelah setup, verify:

1. **Folder exists**
   ```
   app/src/main/assets/img/ ✅
   ```

2. **Icons present**
   ```
   BTC.png, ETH.png, USDT.png, ... ✅
   ```

3. **Build success**
   ```
   ./gradlew assembleDebug ✅
   ```

4. **App running**
   ```
   Icons tampil circular next to symbols ✅
   ```

---

## 🎉 Result

Sekarang aplikasi KriptoMeter Anda memiliki:

✅ **Professional look** dengan crypto icons  
✅ **Circular shape** automatic  
✅ **Easy to identify** each cryptocurrency  
✅ **Compact layout** dengan icon + text  
✅ **Fallback handling** jika icon not found  

---

**Version**: 1.3.0  
**Feature**: Crypto Icons Implementation  
**Status**: ✅ READY  
**Last Updated**: November 28, 2025  

---

**Need Help?**  
Check `CRYPTO_ICONS_IMPLEMENTATION.md` for detailed documentation!

