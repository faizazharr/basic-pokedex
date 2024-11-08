package com.basic.pokedex

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    val code: String,
    val photo: Int,
    val name: String,
    val desc: String,
    val height: String,
    val weight: String,
    val category: String,
    val abilities: String,
    val type: String,
) : Parcelable