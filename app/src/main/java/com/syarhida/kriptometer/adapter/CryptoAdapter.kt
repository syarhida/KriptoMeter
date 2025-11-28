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

        private val decimalFormat = DecimalFormat("#,##0.00")

        fun bind(crypto: Crypto) {
            binding.apply {
                textRank.text = crypto.rank
                textName.text = crypto.name
                textSymbol.text = crypto.symbol
                
                // Format price
                try {
                    val price = crypto.price_usd.toDoubleOrNull()
                    if (price != null) {
                        textPrice.text = "$${decimalFormat.format(price)}"
                    } else {
                        textPrice.text = "$${crypto.price_usd}"
                    }
                } catch (e: Exception) {
                    textPrice.text = "$${crypto.price_usd}"
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

