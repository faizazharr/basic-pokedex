package com.basic.pokedex

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.basic.pokedex.databinding.ActivityAboutBinding
import com.basic.pokedex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Pokemon>()
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        list.addAll(getListPokemon())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        binding.apply {
            when (item.itemId) {
                R.id.action_list -> {
                    rvPokemon.layoutManager = LinearLayoutManager(this@MainActivity)
                }

                R.id.action_grid -> {
                    rvPokemon.layoutManager = GridLayoutManager(this@MainActivity, 2)
                }

                R.id.action_about -> {
                    startActivity(Intent(this@MainActivity, AboutActivity::class.java))
                }
            }

        }
        return super.onOptionsItemSelected(item)
    }

    fun getListPokemon(): ArrayList<Pokemon> {
        val code = resources.getStringArray(R.array.data_code)
        val photo = resources.obtainTypedArray(R.array.data_photo)
        val name = resources.getStringArray(R.array.data_name)
        val desc = resources.getStringArray(R.array.data_description)
        val height = resources.getStringArray(R.array.data_height)
        val weight = resources.getStringArray(R.array.data_weight)
        val category = resources.getStringArray(R.array.data_category)
        val abilities = resources.getStringArray(R.array.data_abilities)
        val type = resources.getStringArray(R.array.data_type)
        val color = resources.getIntArray(R.array.data_color)
        val listPokemon = ArrayList<Pokemon>()
        for (i in name.indices) {
            val photoResourceId = photo.getResourceId(i, -1)

            val pokemon = Pokemon(
                code = code[i],
                photo = photoResourceId,
                name = name[i],
                desc = desc[i],
                height = height[i],
                weight = weight[i],
                category = category[i],
                abilities = abilities[i],
                type = type[i],
                color = color[i]
            )
            listPokemon.add(pokemon)
        }
        return listPokemon
    }

    private fun showRecyclerList() {

        val showLayout =
            if (this.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                GridLayoutManager(this, 2)
            } else {
                LinearLayoutManager(this)
            }
        binding.apply {
            rvPokemon.setHasFixedSize(true)
            rvPokemon.layoutManager = showLayout
            val listPokemonAdapter = PokemonAdapter(list)
            rvPokemon.adapter = listPokemonAdapter
        }
    }

}