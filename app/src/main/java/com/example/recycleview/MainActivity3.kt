package com.example.recycleview

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity3 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val fullName : TextView = findViewById(R.id.repoFullName)
        val description : TextView = findViewById(R.id.repoDescription)
        val full_name :String? = intent.getStringExtra("message")
        val desc : String? = intent.getStringExtra("messaget")
        fullName.text =full_name
        description.text = desc

    }
}