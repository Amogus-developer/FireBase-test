package com.example.mychat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mychat.databinding.ActivityReadBinding
import com.google.firebase.database.*

class ReadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReadBinding

    private val adapter = ReadAdapter(this)

    private lateinit var listData: MutableList<String>
    private lateinit var listTemp: MutableList<User>
    private lateinit var listener: Listener
    lateinit var mBase: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listener = Listener()
        init()
    }
    private fun init(){
        listData = ArrayList()
        listTemp = ArrayList()
        mBase = FirebaseDatabase.getInstance().getReference(Constants.USER_KEY)
        mBase.addValueEventListener(listener)
        binding.apply {
            rcView.layoutManager = LinearLayoutManager(this@ReadActivity)
            rcView.adapter = adapter
        }
    }
    inner class Listener: ValueEventListener{

        override fun onDataChange(snapshot: DataSnapshot) {

            if (listData.size > 0) listData.clear()
            if (listTemp.size > 0) listTemp.clear()

            for(ds in snapshot.children){
                val user = ds.getValue(User::class.java)
                assert(user != null)
                listData.add(user!!.date + " l " + user!!.name)
                adapter.getUser(user)
                listTemp.add(user)
            }
        }

        override fun onCancelled(error: DatabaseError) {

        }
    }
    fun setOnItemClick(position: Int){
            val user = listTemp[position]
            val intent = Intent(this, LookActivity::class.java)
            intent.putExtra("description", user.description)
            intent.putExtra("theme", user.theme)
            startActivity(intent)
    }
}

