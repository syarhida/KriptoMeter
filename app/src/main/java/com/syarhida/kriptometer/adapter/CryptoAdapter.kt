package com.syarhida.kriptometer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.syarhida.kriptometer.databinding.ItemCryptoBinding
import com.syarhida.kriptometer.model.Crypto
import java.text.DecimalFormat

class CryptoAdapter : ListAdapter<Crypto, CryptoAdapter.CryptoViewHolder>(CryptoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val binding = ItemCryptoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CryptoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CryptoViewHolder(private val binding: ItemCryptoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val decimalFormatUSD = DecimalFormat("#,##0.00")
        private val decimalFormatIDR = DecimalFormat("#,###")
        
        // Rate konversi USD ke IDR (update sesuai kurs terkini)
        private val USD_TO_IDR_RATE = 15700.0

        fun bind(crypto: Crypto) {
            binding.apply {
                textRank.text = crypto.rank
                textName.text = crypto.name
                textSymbol.text = crypto.symbol
                
                // Format price USD and IDR
                try {
                    val priceUSD = crypto.price_usd.toDoubleOrNull()
                    if (priceUSD != null) {
                        // Display USD price
                        textPrice.text = "$${decimalFormatUSD.format(priceUSD)}"
                        
                        // Calculate and display IDR price
                        val priceIDR = priceUSD * USD_TO_IDR_RATE
                        textPriceIdr.text = "Rp ${decimalFormatIDR.format(priceIDR)}"
                    } else {
                        textPrice.text = "$${crypto.price_usd}"
                        textPriceIdr.text = "Rp -"
                    }
                } catch (e: Exception) {
                    textPrice.text = "$${crypto.price_usd}"
                    textPriceIdr.text = "Rp -"
                }
            }
        }
    }

    class CryptoDiffCallback : DiffUtil.ItemCallback<Crypto>() {
        override fun areItemsTheSame(oldItem: Crypto, newItem: Crypto): Boolean {
            return oldItem.rank == newItem.rank
        }

        override fun areContentsTheSame(oldItem: Crypto, newItem: Crypto): Boolean {
            return oldItem == newItem
        }
    }
}

