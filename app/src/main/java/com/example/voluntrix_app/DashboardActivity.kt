package com.example.voluntrix_app

import EventAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DashboardActivity : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerview : RecyclerView
    private lateinit var userArrayList : ArrayList<Event>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        userRecyclerview = findViewById(R.id.recyclerDashboard)
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.setHasFixedSize(true)



        userArrayList = arrayListOf<Event>()
        getUserData()

    }

    private fun getUid(){
        dbref = FirebaseDatabase.getInstance().reference

        // Example data to add to the database
        val data = mapOf(
            "name" to "John",
            "age" to 30,
            "email" to "john@example.com"
        )
        val currentUser = FirebaseAuth.getInstance().currentUser
        val uid = currentUser?.uid
        // Add the data to a specific location in the database
        // Get the current user's email (assuming you have it)
        val email = currentUser?.email

        // Use the email as the unique identifier in the database
        if (email != null) {
            dbref.child("users").child(email).setValue(data)
        }

    }

    private fun getUserData() {

        dbref = FirebaseDatabase.getInstance().getReference("Users")

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){


                        val user = userSnapshot.getValue(Event::class.java)
                        userArrayList.add(user!!)

                    }

                    userRecyclerview.adapter = EventAdapter(userArrayList)


                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }
}