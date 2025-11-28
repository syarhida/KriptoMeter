package com.syarhida.kriptometer.model

data class Crypto(
    val rank: String,
    val name: String,
    val symbol: String,
    val price_usd: String,
    val percent_change_24h: String? = null
)

