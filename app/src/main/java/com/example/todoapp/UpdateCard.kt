package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


// updating and deleting data from list and database
class UpdateCard : AppCompatActivity() {

    private lateinit var database : Database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_card)

        database = Room.databaseBuilder(
            applicationContext,
            Database::class.java,
            "TO_DO"
        ).build()

        val createTitle = findViewById<EditText>(R.id.create_title)
        val createPriority = findViewById<EditText>(R.id.create_priority)
        val deleteButton = findViewById<Button>(R.id.delete_button)
        val updateButton = findViewById<Button>(R.id.update_button)



        val pos = intent.getIntExtra("id", -1)
        if (pos != -1) {
            val title = DataObject.getData(pos).title
            val priority = DataObject.getData(pos).priority
            createTitle.setText(title)
            createPriority.setText(priority)

            deleteButton.setOnClickListener {
                DataObject.deleteData(pos)
                GlobalScope.launch{
                    database.dao().deleteTask(
                        Entity(pos+1,
                            createTitle.text.toString(),
                            createPriority.text.toString())
                    )
                }

                myIntent()
            }

            updateButton.setOnClickListener {
                DataObject.updateData(
                    pos,
                    createTitle.text.toString(),
                    createPriority.text.toString()
                )
                GlobalScope.launch{
                    database.dao().updateTask(
                        Entity(pos+1,
                            createTitle.text.toString(),
                            createPriority.text.toString())
                    )
                }
                myIntent()
            }
        }
    }

    private fun myIntent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}