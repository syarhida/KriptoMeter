#!/usr/bin/env python3
"""
Crypto Icons Downloader for KriptoMeter
Downloads cryptocurrency icons from cryptocurrency-icons repository
and saves them with proper naming convention (UPPERCASE)
"""

import requests
import os
import sys

# Top cryptocurrencies by market cap
CRYPTO_SYMBOLS = [
    'BTC',   # Bitcoin
    'ETH',   # Ethereum
    'USDT',  # Tether
    'BNB',   # Binance Coin
    'XRP',   # Ripple
    'SOL',   # Solana
    'USDC',  # USD Coin
    'ADA',   # Cardano
    'DOGE',  # Dogecoin
    'TRX',   # TRON
    'TON',   # Toncoin
    'LINK',  # Chainlink
    'MATIC', # Polygon
    'DOT',   # Polkadot
    'DAI',   # Dai
    'LTC',   # Litecoin
    'SHIB',  # Shiba Inu
    'BCH',   # Bitcoin Cash
    'AVAX',  # Avalanche
    'UNI',   # Uniswap
    'ATOM',  # Cosmos
    'XLM',   # Stellar
    'XMR',   # Monero
    'ETC',   # Ethereum Classic
    'FIL',   # Filecoin
    'APT',   # Aptos
    'ARB',   # Arbitrum
    'OP',    # Optimism
    'INJ',   # Injective
    'STX'    # Stacks
]

# Base URL for cryptocurrency icons (128x128 color version)
BASE_URL = "https://raw.githubusercontent.com/spothq/cryptocurrency-icons/master/128/color/"

# Output directory
OUTPUT_DIR = "app/src/main/assets/img"

def download_icon(symbol):
    """Download icon for a cryptocurrency symbol"""
    url = f"{BASE_URL}{symbol.lower()}.png"
    output_path = os.path.join(OUTPUT_DIR, f"{symbol}.png")
    
    try:
        response = requests.get(url, timeout=10)
        if response.status_code == 200:
            with open(output_path, 'wb') as f:
                f.write(response.content)
            print(f"✅ Downloaded: {symbol}.png")
            return True
        else:
            print(f"❌ Failed: {symbol}.png (HTTP {response.status_code})")
            return False
    except Exception as e:
        print(f"❌ Error downloading {symbol}.png: {str(e)}")
        return False

def main():
    """Main function to download all crypto icons"""
    print("=" * 60)
    print("  🪙 Crypto Icons Downloader for KriptoMeter")
    print("=" * 60)
    print()
    
    # Create output directory if not exists
    os.makedirs(OUTPUT_DIR, exist_ok=True)
    print(f"📁 Output directory: {OUTPUT_DIR}")
    print(f"🎯 Downloading {len(CRYPTO_SYMBOLS)} cryptocurrency icons...")
    print()
    
    # Download icons
    success_count = 0
    failed_count = 0
    
    for i, symbol in enumerate(CRYPTO_SYMBOLS, 1):
        print(f"[{i}/{len(CRYPTO_SYMBOLS)}] ", end="")
        if download_icon(symbol):
            success_count += 1
        else:
            failed_count += 1
    
    # Summary
    print()
    print("=" * 60)
    print("📊 Download Summary:")
    print(f"  ✅ Success: {success_count}")
    print(f"  ❌ Failed:  {failed_count}")
    print(f"  📁 Location: {OUTPUT_DIR}")
    print("=" * 60)
    print()
    
    if success_count > 0:
        print("🎉 Icons downloaded successfully!")
        print("📌 Next steps:")
        print("   1. Open Android Studio")
        print("   2. Clean & Rebuild project")
        print("   3. Run the app")
        print("   4. Icons will appear next to crypto symbols!")
    else:
        print("⚠️  No icons were downloaded. Please check your internet connection.")
        sys.exit(1)

if __name__ == "__main__":
    try:
        main()
    except KeyboardInterrupt:
        print("\n\n⚠️  Download cancelled by user.")
        sys.exit(0)
    except Exception as e:
        print(f"\n\n❌ Unexpected error: {str(e)}")
        sys.exit(1)

