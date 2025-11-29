package com.syarhida.kriptometer.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Shader
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.syarhida.kriptometer.R
import com.syarhida.kriptometer.databinding.ItemCryptoBinding
import com.syarhida.kriptometer.model.Crypto
import java.io.IOException
import java.text.DecimalFormat

class CryptoAdapter(
    private val onLoadMoreClick: () -> Unit
) : ListAdapter<Any, RecyclerView.ViewHolder>(CryptoDiffCallback()) {

    companion object {
        private const val VIEW_TYPE_CRYPTO = 0
        private const val VIEW_TYPE_LOAD_MORE = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Crypto -> VIEW_TYPE_CRYPTO
            is LoadMoreItem -> VIEW_TYPE_LOAD_MORE
            else -> VIEW_TYPE_CRYPTO
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_CRYPTO -> {
                val binding = ItemCryptoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                CryptoViewHolder(binding)
            }
            VIEW_TYPE_LOAD_MORE -> {
                val binding = com.syarhida.kriptometer.databinding.ItemLoadMoreBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                LoadMoreViewHolder(binding, onLoadMoreClick)
            }
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CryptoViewHolder -> holder.bind(getItem(position) as Crypto)
            is LoadMoreViewHolder -> {
                // No binding needed for load more button
            }
        }
    }

    class CryptoViewHolder(private val binding: ItemCryptoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val decimalFormatUSD = DecimalFormat("#,##0.00")
        private val decimalFormatIDR = DecimalFormat("#,###")
        
        // Rate konversi USD ke IDR (update sesuai kurs terkini)
        private val USD_TO_IDR_RATE = 16650.0

        fun bind(crypto: Crypto) {
            binding.apply {
                textRank.text = crypto.rank
                textName.text = crypto.name
                textSymbol.text = crypto.symbol
                
                // Load crypto icon from assets
                loadCryptoIcon(crypto.symbol)
                
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
                
                // Display 24h change
                display24hChange(crypto.percent_change_24h)
            }
        }
        
        private fun display24hChange(change24h: String?) {
            try {
                val changeValue = change24h?.toDoubleOrNull()
                if (changeValue != null) {
                    val arrow = if (changeValue >= 0) "▲" else "▼"
                    val changeText = "${String.format("%.2f", Math.abs(changeValue))}% $arrow"
                    binding.textChange24h.text = changeText
                    
                    // Set color based on positive/negative
                    val context = binding.root.context
                    val color = if (changeValue >= 0) {
                        ContextCompat.getColor(context, R.color.positive_price) // Green
                    } else {
                        Color.parseColor("#FF5252") // Red
                    }
                    binding.textChange24h.setTextColor(color)
                } else {
                    binding.textChange24h.text = "0.00% -"
                    binding.textChange24h.setTextColor(
                        ContextCompat.getColor(binding.root.context, R.color.text_secondary)
                    )
                }
            } catch (e: Exception) {
                binding.textChange24h.text = "0.00% -"
                binding.textChange24h.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.text_secondary)
                )
            }
        }
        
        private fun loadCryptoIcon(symbol: String) {
            // Try multiple variations to find the icon
            val variations = listOf(
                symbol,                    // Exact match (e.g., USDe)
                symbol.uppercase(),        // Uppercase (e.g., USDE)
                symbol.lowercase(),        // Lowercase (e.g., usde)
                symbol.replaceFirstChar { it.uppercase() }  // Capitalize (e.g., Usde)
            )
            
            var loaded = false
            for (variation in variations) {
                try {
                    val inputStream = binding.root.context.assets.open("img/${variation}.png")
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    inputStream.close()
                    
                    // Convert to circular bitmap
                    val circularBitmap = getCircularBitmap(bitmap)
                    binding.imageCryptoIcon.setImageBitmap(circularBitmap)
                    loaded = true
                    break
                } catch (e: IOException) {
                    // Try next variation
                    continue
                }
            }
            
            // If all variations failed, try NOICON.png
            if (!loaded) {
                try {
                    val inputStream = binding.root.context.assets.open("img/NOICON.png")
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    inputStream.close()
                    
                    val circularBitmap = getCircularBitmap(bitmap)
                    binding.imageCryptoIcon.setImageBitmap(circularBitmap)
                } catch (e: IOException) {
                    // Try noicon.png (lowercase)
                    try {
                        val inputStream = binding.root.context.assets.open("img/noicon.png")
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        inputStream.close()
                        
                        val circularBitmap = getCircularBitmap(bitmap)
                        binding.imageCryptoIcon.setImageBitmap(circularBitmap)
                    } catch (e2: IOException) {
                        // Ultimate fallback to launcher icon
                        binding.imageCryptoIcon.setImageResource(R.mipmap.ic_launcher)
                    }
                }
            }
        }
        
        private fun getCircularBitmap(bitmap: Bitmap): Bitmap {
            // Resize bitmap to consistent size first
            val targetSize = 200 // Consistent size for all icons
            val scaledBitmap = Bitmap.createScaledBitmap(bitmap, targetSize, targetSize, true)
            
            val output = Bitmap.createBitmap(targetSize, targetSize, Bitmap.Config.ARGB_8888)
            
            val canvas = Canvas(output)
            val radius = targetSize / 2f
            
            // Draw image with small padding for border
            val imagePaint = Paint()
            imagePaint.isAntiAlias = true
            imagePaint.shader = BitmapShader(scaledBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
            val imageRadius = radius - 3f // Padding for border
            canvas.drawCircle(radius, radius, imageRadius, imagePaint)
            
            // Draw border
            val borderPaint = Paint()
            borderPaint.isAntiAlias = true
            borderPaint.style = Paint.Style.STROKE
            borderPaint.strokeWidth = 6f
            borderPaint.color = ContextCompat.getColor(binding.root.context, R.color.primary)
            canvas.drawCircle(radius, radius, imageRadius, borderPaint)
            
            return output
        }
    }

    class LoadMoreViewHolder(
        private val binding: com.syarhida.kriptometer.databinding.ItemLoadMoreBinding,
        private val onLoadMoreClick: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.buttonLoadMore.setOnClickListener {
                onLoadMoreClick()
            }
        }
    }

    class CryptoDiffCallback : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return when {
                oldItem is Crypto && newItem is Crypto -> oldItem.rank == newItem.rank
                oldItem is LoadMoreItem && newItem is LoadMoreItem -> true
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return when {
                oldItem is Crypto && newItem is Crypto -> oldItem == newItem
                oldItem is LoadMoreItem && newItem is LoadMoreItem -> true
                else -> false
            }
        }
    }
}

// Marker class for load more button
class LoadMoreItem

