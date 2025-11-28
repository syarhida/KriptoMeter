# 📁 Crypto Icons Folder

## 📍 Location
`app/src/main/assets/img/`

## 📝 Format File
- **Nama File**: `{SYMBOL}.png`
- **Format**: PNG dengan transparency (recommended)
- **Ukuran**: 40x40 dp atau lebih (akan di-resize otomatis)
- **Background**: Boleh kotak atau circle (akan di-convert ke circle otomatis)

## 📋 Contoh Nama File

```
BTC.png     → Bitcoin
ETH.png     → Ethereum
USDT.png    → Tether
XRP.png     → XRP
BNB.png     → Binance Coin
SOL.png     → Solana
ADA.png     → Cardano
DOGE.png    → Dogecoin
DOT.png     → Polkadot
MATIC.png   → Polygon
```

## 🎨 Format Icon

### ✅ RECOMMENDED:
1. **PNG dengan transparency**
2. **Square atau circle shape** (akan di-convert otomatis)
3. **High quality** (minimal 100x100 px)
4. **Warna asli** dari logo cryptocurrency

### ⚠️ NOTES:
- File name harus **UPPERCASE** sesuai symbol dari API
- Jika icon kotak/tanpa background → akan otomatis jadi **CIRCLE**
- Jika file tidak ditemukan → akan pakai **default launcher icon**

## 📥 Cara Download Icons

### Sumber Icon Gratis:

1. **CoinGecko API** (Free)
   - https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd
   - Field: `image` (URL ke icon)

2. **CryptoCompare** (Free)
   - https://www.cryptocompare.com/media/
   - PNG icons berbagai size

3. **CoinIcons** (Free Open Source)
   - https://github.com/spothq/cryptocurrency-icons
   - 400+ icons, berbagai format

4. **Manual Download**
   - https://cryptologos.cc/
   - High quality PNG/SVG

## 🔧 Setup Script (Optional)

Untuk download icons otomatis, bisa pakai script Python:

```python
import requests
import os

# List symbol yang dibutuhkan
symbols = ['BTC', 'ETH', 'USDT', 'XRP', 'BNB', 'SOL', 'ADA', 'DOGE']

# Download dari cryptocurrency-icons
base_url = "https://raw.githubusercontent.com/spothq/cryptocurrency-icons/master/32/color/"

for symbol in symbols:
    url = f"{base_url}{symbol.lower()}.png"
    response = requests.get(url)
    
    if response.status_code == 200:
        with open(f"img/{symbol}.png", 'wb') as f:
            f.write(response.content)
        print(f"✅ Downloaded {symbol}.png")
    else:
        print(f"❌ Failed {symbol}.png")
```

## 📂 Struktur Folder

```
app/src/main/assets/
└── img/
    ├── README.md (file ini)
    ├── BTC.png
    ├── ETH.png
    ├── USDT.png
    ├── XRP.png
    ├── BNB.png
    ├── SOL.png
    ├── ADA.png
    ├── DOGE.png
    ├── DOT.png
    └── ... (tambahkan sesuai kebutuhan)
```

## 🎯 Testing

1. Pastikan nama file **UPPERCASE**
2. Format file **PNG**
3. Jalankan app
4. Jika icon muncul → ✅ SUCCESS
5. Jika launcher icon → ❌ File tidak ditemukan

## 💡 Tips

- Gunakan icon dengan **white/transparent background** untuk best result
- Size **128x128 px atau 256x256 px** optimal
- Icon akan otomatis jadi **circle** dengan border cyan
- Tidak perlu edit manual jadi circle!

## 🔄 Hot Reload

- Setelah menambah icon baru
- **Clean & Rebuild** project
- Icon akan langsung muncul

---

**Last Updated**: November 28, 2025  
**Developer**: Syarhida  
**Project**: KriptoMeter

