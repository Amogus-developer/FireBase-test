package com.example.mychat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.mychat.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mBase: DatabaseReference
    private var USER_KEY = "User"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mBase = FirebaseDatabase.getInstance().getReference(USER_KEY)
        binding.bSave.setOnClickListener { logIn() }
        binding.bRead.setOnClickListener {
            val intent = Intent(this, ReadActivity::class.java)
            startActivity(intent)
        }
    }
    private fun logIn(){
        val id = mBase.key.toString()
        val name = binding.edName.text.toString()
        val user = User(id, name)

        if (!TextUtils.isEmpty(binding.edName.text)){
            mBase.push().setValue(user)
            Toast.makeText(this, "Сохранено", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(this, "Заполните поле", Toast.LENGTH_SHORT).show()
        }
    }
}
