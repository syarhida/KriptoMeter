# 🎨 Crypto Icons Implementation Guide

## 📅 Date: November 28, 2025

### ✨ Feature: Crypto Icons with Circular Shape

---

## 🎯 Overview

Aplikasi sekarang menampilkan **icon cryptocurrency** dari folder assets dengan fitur:

✅ **Icon dari assets** (`assets/img/{SYMBOL}.png`)  
✅ **Automatic circular shape** (kotak → circle)  
✅ **Rank badge tanpa background** (text only)  
✅ **Fallback to default** jika icon tidak ada  

---

## 📂 Struktur File Baru

### 1. Assets Folder
```
app/src/main/assets/
└── img/
    ├── README.md          ← Dokumentasi
    ├── BTC.png            ← Bitcoin icon
    ├── ETH.png            ← Ethereum icon
    ├── USDT.png           ← Tether icon
    ├── XRP.png            ← XRP icon
    └── ...                ← Icon lainnya
```

### 2. Drawable Resource
```
app/src/main/res/drawable/
└── circle_image_background.xml  ← Background circle untuk icon
```

---

## 🎨 Layout Changes

### Before:
```
┌────────────────────────────────────┐
│  ╭───╮                             │
│  │ 1 │  BTC      $91,532.34        │ ← Rank dengan circle BG
│  ╰───╯  Bitcoin  Rp 1.437.057      │
└────────────────────────────────────┘
```

### After:
```
┌────────────────────────────────────┐
│  1  ⭕ BTC      $91,532.34         │ ← Rank text + Icon circle
│      ₿  Bitcoin  Rp 1.437.057      │    (Bitcoin icon)
├────────────────────────────────────┤
│  2  ⭕ ETH      $3,036.40          │
│      Ξ  Ethereum Rp 47.671         │
└────────────────────────────────────┘
```

---

## 📐 Component Details

### 1. Rank Number (Updated)
```xml
<TextView
    android:id="@+id/textRank"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:minWidth="24dp"
    android:gravity="center"
    android:textColor="@color/text_secondary"  ← Gray color
    android:textSize="14sp"                    ← Smaller
    android:textStyle="bold"
    (NO background anymore!) />
```

**Changes:**
- ❌ Remove `android:background="@drawable/rank_badge_background"`
- ✅ Width: `wrap_content` (not fixed 48dp)
- ✅ Color: Gray (secondary color)
- ✅ Size: 14sp (lebih kecil)

### 2. Crypto Icon (NEW!)
```xml
<ImageView
    android:id="@+id/imageCryptoIcon"
    android:layout_width="40dp"
    android:layout_height="40dp"
    android:layout_marginStart="12dp"
    android:background="@drawable/circle_image_background"
    android:padding="2dp"
    android:scaleType="centerCrop" />
```

**Features:**
- 40x40 dp size
- Circular background dengan border cyan
- Padding 2dp untuk spacing
- CenterCrop untuk maintain aspect ratio

### 3. Text Position
- Symbol & Name sekarang aligned dengan **icon** (bukan rank)
- Margin 12dp dari icon
- Vertical center dengan icon

---

## 💻 Code Implementation

### Adapter Changes (`CryptoAdapter.kt`)

#### 1. Load Icon from Assets
```kotlin
private fun loadCryptoIcon(symbol: String) {
    try {
        // Try to load from assets/img/{SYMBOL}.png
        val inputStream = binding.root.context.assets.open("img/${symbol}.png")
        val bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream.close()
        
        // Convert to circular bitmap
        val circularBitmap = getCircularBitmap(bitmap)
        binding.imageCryptoIcon.setImageBitmap(circularBitmap)
    } catch (e: IOException) {
        // Fallback to default launcher icon
        binding.imageCryptoIcon.setImageResource(R.mipmap.ic_launcher)
    }
}
```

#### 2. Convert to Circular Shape
```kotlin
private fun getCircularBitmap(bitmap: Bitmap): Bitmap {
    val size = Math.min(bitmap.width, bitmap.height)
    val output = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
    
    val canvas = Canvas(output)
    val paint = Paint()
    paint.isAntiAlias = true
    paint.shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
    
    val radius = size / 2f
    canvas.drawCircle(radius, radius, radius, paint)
    
    return output
}
```

**How it works:**
1. Load PNG dari assets
2. Convert ke circular bitmap dengan BitmapShader
3. Return circular result
4. Apply ke ImageView

---

## 📥 Download Crypto Icons

### Option 1: GitHub Repository (RECOMMENDED) ⭐

**Cryptocurrency Icons** by SpotHQ
- URL: https://github.com/spothq/cryptocurrency-icons
- 400+ icons
- Format: PNG, SVG
- License: CC0 (Public Domain)

**Download Steps:**
```bash
# Clone repository
git clone https://github.com/spothq/cryptocurrency-icons.git

# Copy icons (32x32 or 128x128 color)
cp cryptocurrency-icons/32/color/*.png app/src/main/assets/img/

# Rename to UPPERCASE
# btc.png → BTC.png
# eth.png → ETH.png
```

### Option 2: CoinGecko API (Dynamic)

```kotlin
// Get icon URL from CoinGecko API
// https://api.coingecko.com/api/v3/coins/bitcoin
// Response: { "image": { "thumb": "url_here" } }
```

### Option 3: Manual Download

**Sources:**
1. **CryptoLogos**: https://cryptologos.cc/
2. **CoinMarketCap**: https://coinmarketcap.com/
3. **CoinGecko**: https://www.coingecko.com/

**Format:**
- Right click → Save image as...
- Rename to `{SYMBOL}.png` (UPPERCASE!)
- Put in `app/src/main/assets/img/`

---

## 📋 Icon Naming Convention

### ✅ CORRECT Format:
```
BTC.png      ← Bitcoin
ETH.png      ← Ethereum
USDT.png     ← Tether (USDT, not USDC!)
XRP.png      ← XRP (Ripple)
BNB.png      ← Binance Coin
SOL.png      ← Solana
ADA.png      ← Cardano
DOGE.png     ← Dogecoin
MATIC.png    ← Polygon
```

### ❌ WRONG Format:
```
btc.png      ← lowercase (will not load!)
Bitcoin.png  ← full name (wrong!)
1.png        ← number (wrong!)
```

**RULES:**
- Must be **UPPERCASE**
- Must match **symbol** from API (not name!)
- Must be **PNG** format

---

## 🎯 Visual Example

### Layout Anatomy:
```
┌─────────────────────────────────────────┐
│ Card (#2B3A50, rounded 16dp)            │
│ ┌───────────────────────────────────┐   │
│ │ 1  [🟠] BTC      $91,532.34      │   │
│ │    │    │         │               │   │
│ │    │    │         └─ Price (20sp, bold, cyan)
│ │    │    └─ Symbol (18sp, bold, white)
│ │    └─ Icon (40dp circle, border cyan)
│ │                                     │   │
│ │       Bitcoin   Rp 1.437.057      │   │
│ │       │         │                  │   │
│ │       │         └─ IDR (12sp, gray)
│ │       └─ Name (11sp, gray)         │   │
│ └───────────────────────────────────┘   │
└─────────────────────────────────────────┘
```

**Hierarchy:**
1. **Rank** (14sp, gray) - Left
2. **Icon** (40dp circle) - Center-left
3. **Symbol** (18sp, bold, white) - Center
4. **Name** (11sp, gray) - Center below
5. **Price USD** (20sp, bold, cyan) - Right
6. **Price IDR** (12sp, gray) - Right below

---

## 🔄 Fallback Behavior

### If Icon File Exists:
```
assets/img/BTC.png → Display Bitcoin icon ✅
```

### If Icon File NOT Exists:
```
assets/img/XYZ.png (not found) → Display launcher icon 🚀
```

**Fallback to:**
- `R.mipmap.ic_launcher` (default app icon)
- No crash, graceful degradation

---

## 🧪 Testing Checklist

### Visual:
- [ ] Icon tampil circular (not square)
- [ ] Icon size 40dp x 40dp
- [ ] Border cyan visible
- [ ] Rank tanpa background (text only)
- [ ] Rank color gray (secondary)

### Functional:
- [ ] Icon load dari assets/{SYMBOL}.png
- [ ] Fallback to launcher icon jika not found
- [ ] Circular shape automatic (kotak → circle)
- [ ] No crash jika file missing
- [ ] Performance OK (no lag)

### Files:
- [ ] `assets/img/` folder exists
- [ ] Icon files UPPERCASE (BTC.png, not btc.png)
- [ ] PNG format with transparency
- [ ] Min size 40x40 px

---

## 🚀 Quick Start

### Step 1: Download Icons

```bash
# From cryptocurrency-icons repo
wget https://raw.githubusercontent.com/spothq/cryptocurrency-icons/master/128/color/btc.png
mv btc.png BTC.png

wget https://raw.githubusercontent.com/spothq/cryptocurrency-icons/master/128/color/eth.png
mv eth.png ETH.png

# Repeat for other cryptos...
```

### Step 2: Place Icons

```
Move all PNG files to:
app/src/main/assets/img/

Final structure:
app/src/main/assets/img/
├── BTC.png
├── ETH.png
├── USDT.png
└── ...
```

### Step 3: Build & Run

```bash
./gradlew clean assembleDebug
# Or click Run in Android Studio
```

### Step 4: Verify

- Open app
- Icons should appear circular next to symbols
- If not → check file names (must be UPPERCASE!)

---

## 💡 Pro Tips

### 1. Batch Rename (PowerShell)
```powershell
# Convert all to uppercase
Get-ChildItem *.png | Rename-Item -NewName { $_.Name.ToUpper() }
```

### 2. Optimize Icon Size
```bash
# Using ImageMagick
magick input.png -resize 128x128 output.png
```

### 3. Remove Background
```bash
# Using ImageMagick
magick input.png -background none -flatten output.png
```

### 4. Batch Download Script

Create `download_icons.py`:
```python
import requests
import os

symbols = ['BTC', 'ETH', 'USDT', 'XRP', 'BNB', 'SOL', 'ADA']
base_url = "https://raw.githubusercontent.com/spothq/cryptocurrency-icons/master/128/color/"

os.makedirs("img", exist_ok=True)

for symbol in symbols:
    url = f"{base_url}{symbol.lower()}.png"
    response = requests.get(url)
    
    if response.status_code == 200:
        with open(f"img/{symbol}.png", 'wb') as f:
            f.write(response.content)
        print(f"✅ {symbol}.png")
    else:
        print(f"❌ {symbol}.png failed")
```

Run:
```bash
python download_icons.py
```

---

## 📊 Performance

### Memory:
- Each 40x40 icon ≈ 6KB
- 50 icons ≈ 300KB total
- Negligible impact ✅

### Loading:
- Sync load from assets (fast)
- Circular conversion on-the-fly
- No network call needed
- Smooth scrolling ✅

---

## 🎨 Customization

### Change Icon Size:
```xml
<!-- In item_crypto.xml -->
<ImageView
    android:layout_width="48dp"  ← Increase
    android:layout_height="48dp" ← Increase
    ... />
```

### Change Border Color:
```xml
<!-- In circle_image_background.xml -->
<stroke
    android:width="1dp"
    android:color="@color/positive_price" ← Change to green
    />
```

### Change Border Width:
```xml
<stroke
    android:width="2dp" ← Thicker border
    android:color="@color/primary" />
```

---

## 🔧 Troubleshooting

### Problem: Icon tidak muncul
**Solution:**
- Check file name UPPERCASE (BTC.png not btc.png)
- Check file location (assets/img/ not res/)
- Clean & Rebuild project

### Problem: Icon kotak, bukan circle
**Solution:**
- Check `getCircularBitmap()` function
- Check ImageView `background` attribute
- Verify BitmapShader implementation

### Problem: App crash saat load icon
**Solution:**
- Add try-catch in `loadCryptoIcon()`
- Check file format (PNG only)
- Verify file not corrupted

---

## 📝 Summary

### What Changed:
1. ✅ **Rank badge** → Text only (no circle background)
2. ✅ **Crypto icon** → Added (40dp circular)
3. ✅ **Icon source** → From assets/img/{SYMBOL}.png
4. ✅ **Auto circular** → Square icons → Circle
5. ✅ **Fallback** → Default launcher icon if not found

### Files Modified:
- `item_crypto.xml` → Layout update
- `CryptoAdapter.kt` → Icon loading logic
- `circle_image_background.xml` → NEW drawable

### Files Added:
- `assets/img/README.md` → Icon documentation
- `CRYPTO_ICONS_IMPLEMENTATION.md` → This file

---

**Version**: 1.3.0  
**Feature**: Crypto Icons with Circular Shape  
**Status**: ✅ IMPLEMENTED  
**Build**: SUCCESS  

🎉 **Sekarang crypto icons tampil dengan cantik dalam circular shape!**

