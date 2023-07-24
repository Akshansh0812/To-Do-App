package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// Store the task and priority in list and database

class CreateCard : AppCompatActivity() {

    private lateinit var database : Database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)

        database = Room.databaseBuilder(
            applicationContext,
            Database::class.java,
            "TO_DO"
        ).build()

        val saveButton = findViewById<Button>(R.id.save_button)
        val createTitle = findViewById<EditText>(R.id.create_title)
        val createPriority = findViewById<EditText>(R.id.create_priority)

        saveButton.setOnClickListener {
            if (createTitle.text.toString().trim { it <= ' ' }.isNotEmpty()
                && createPriority.text.toString().trim { it <= ' ' }.isNotEmpty()
            ) {
                val title = createTitle.text.toString()
                val priority = createPriority.text.toString()

                DataObject.setData(title, priority) // Storing data in list

                GlobalScope.launch{
                    database.dao().insertTask(Entity(0,title,priority)) // Storing data in database
                }

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

            if (createTitle.text.toString().trim { it <= ' ' }.isEmpty()
                && createPriority.text.toString().trim { it <= ' ' }.isEmpty()
            ){
                Toast.makeText(this,"Enter Task and Priority",Toast.LENGTH_SHORT).show()
            }

            if (createTitle.text.toString().trim { it <= ' ' }.isEmpty()

            ){
                Toast.makeText(this,"Enter Task",Toast.LENGTH_SHORT).show()
            }
            if(createPriority.text.toString().trim { it <= ' ' }.isEmpty()){
                Toast.makeText(this,"Enter Priority",Toast.LENGTH_SHORT).show()
            }
        }
    }
}