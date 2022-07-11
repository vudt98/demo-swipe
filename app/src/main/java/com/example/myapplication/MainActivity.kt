package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    lateinit var splashAdapter: SplashAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        splashAdapter = SplashAdapter()
        binding.rvText.adapter = splashAdapter

        updateView()
    }

    private fun updateView() {
        val listShow = ArrayList<String>()
        val textShow = "WELCOME BRO!"
        val listChar = textShow.split("")
        CoroutineScope(Dispatchers.IO).launch {
            for ((index,element) in listChar.withIndex()) {
                listShow.add(element)
                CoroutineScope(Dispatchers.Main).launch {
                    splashAdapter.submitList(listShow)
                }
                delay(100)
                if (index == listChar.size - 1) {
                    delay(300)
                    CoroutineScope(Dispatchers.Main).launch {
                        binding.root.setBackgroundColor(resources.getColor(R.color.purple_200))
                    }
                    val intent = Intent(this@MainActivity, SecondActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}