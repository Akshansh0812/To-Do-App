package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class CreateCard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)

        val save_button = findViewById<Button>(R.id.save_button)
        val create_title = findViewById<EditText>(R.id.create_title)
        val create_priority = findViewById<EditText>(R.id.create_priority)

        save_button.setOnClickListener {
            if (create_title.text.toString().trim { it <= ' ' }.isNotEmpty()
                && create_title.text.toString().trim { it <= ' ' }.isNotEmpty()
            ) {
                var title = create_title.text.toString()
                var priority = create_priority.text.toString()
                DataObject.setData(title, priority)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}