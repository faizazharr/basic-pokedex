package com.basic.pokedex

import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.basic.pokedex.databinding.ActivityPokemonDetailBinding

class PokemonDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityPokemonDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setDataPokemon()
    }

    private fun setDataPokemon() {
        val pokemon = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("key_pokemon", Pokemon::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Pokemon>("key_pokemon")
        }
        binding.apply {
            if (pokemon != null) {
                ivPokemon.setImageResource(pokemon.photo)
                tvPokemonNumber.text = getString(R.string.format_pokemon_code, pokemon.code)
                tvPokemonName.text = pokemon.name
                tvDescription.text = pokemon.desc
                tvWeight.text = pokemon.weight
                tvHeight.text = pokemon.height
                tvCategory.text = pokemon.category
                tvAbilities.text = pokemon.abilities
                tvType.text = pokemon.type
                clTopPokemon.backgroundTintList = ColorStateList.valueOf(pokemon.color)
            }
        }
    }
}