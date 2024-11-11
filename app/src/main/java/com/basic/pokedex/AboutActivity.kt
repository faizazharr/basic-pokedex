package com.basic.pokedex

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.basic.pokedex.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAboutBinding.inflate(layoutInflater)
        onclick()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun onclick() {
        binding.apply {
            actionShare.setOnClickListener {
                shareProfile()
            }
            actBack.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    fun shareProfile() {
        val author = resources.getString(R.string.title_author)
        val email = resources.getString(R.string.title_email)

        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(
                Intent.EXTRA_TEXT,
                "Author: $author \nEmail: $email"
            )
        }
        startActivity(Intent.createChooser(shareIntent, "Send to"))

    }
}