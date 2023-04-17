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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mBase = FirebaseDatabase.getInstance().getReference(Constants.USER_KEY)
        binding.bSave.setOnClickListener { logIn() }
        binding.bRead.setOnClickListener {
            val intent = Intent(this, ReadActivity::class.java)
            startActivity(intent)
        }
    }
    private fun logIn(){
        val id = mBase.key.toString()
        val date = binding.edDate.text.toString()
        val name = binding.edName.text.toString()
        val theme = binding.edTheme.text.toString()
        val description = binding.edDescription.text.toString()
        val user = User(id = id, date = date, name = name, description = description, theme = theme)

        if (binding.edPassword.text.toString() == "адМин123") {

            if (!TextUtils.isEmpty(binding.edDate.text) &&
                !TextUtils.isEmpty(binding.edName.text) &&
                !TextUtils.isEmpty(binding.edTheme.text) &&
                !TextUtils.isEmpty(binding.edDescription.text)) {

                mBase.push().setValue(user)

                Toast.makeText(this, "Сохранено", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
            }

        }
        else{
            Toast.makeText(this, "Неверный пароль", Toast.LENGTH_LONG).show()
        }
    }
}
