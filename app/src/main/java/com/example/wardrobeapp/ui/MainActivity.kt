package com.example.wardrobeapp.ui

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import com.example.wardrobeapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)
        val btn = findViewById<Button>(R.id.btn)
        var image = findViewById<ImageView>(R.id.imageView)

        btn.setOnClickListener() {
            try {
                CoroutineScope(Dispatchers.IO).launch {
                    val url = URL("https://i.redd.it/bfc0pz8qdji61.jpg")
                    var bitmap = BitmapFactory.decodeStream(url.openStream())

                    withContext(Dispatchers.Main) {
                        Log.d(
                            "MyTag",
                            "onCreate withContext: ThreadName: ${Thread.currentThread().name}"
                        )
                        image.setImageBitmap(bitmap)
                    }
                }
            } catch (e: Exception) {
                Log.d(
                    "MyTag",
                    "onCreate : ${e}"
                )
            }

        }

    }
}