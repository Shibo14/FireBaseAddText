package com.example.firebaseaddtext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private val collectionRef = db.collection("UserData")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val editTextName = findViewById<EditText>(R.id.editTextNamet)
        val editTextDescription = findViewById<EditText>(R.id.editTextDescription)
        val btnSave = findViewById<Button>(R.id.save)

        btnSave.setOnClickListener {

            val name = editTextName.text.toString()
            val description = editTextDescription.text.toString()
            val newItem = hashMapOf(
                "name" to name,
                "description" to description

            )
            collectionRef.add(newItem)
                .addOnSuccessListener { documentReference ->
                    Log.d("Firestore", "DocumentSnapshot added with ID: ${documentReference.id}")

                }
                .addOnFailureListener { e ->
                    Log.w("Firestore", "Error adding document", e)
                }

        }

    }
}