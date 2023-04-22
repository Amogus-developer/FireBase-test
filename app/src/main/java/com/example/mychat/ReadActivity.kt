package com.example.mychat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.mychat.databinding.ActivityReadBinding
import com.google.firebase.database.*

class ReadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReadBinding
    private lateinit var adapter: ArrayAdapter<String>
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
        setOnItemClick()
    }
    private fun init(){
        listData = ArrayList()
        listTemp = ArrayList()
        adapter = ArrayAdapter(this,R.layout.card_view, listData)
        binding.listView.adapter = adapter
        mBase = FirebaseDatabase.getInstance().getReference(Constants.USER_KEY)
        mBase.addValueEventListener(listener)

    }
    inner class Listener: ValueEventListener{

        override fun onDataChange(snapshot: DataSnapshot) {

            if (listData.size > 0) listData.clear()
            if (listTemp.size > 0) listTemp.clear()

            for(ds in snapshot.children){
                val user = ds.getValue(User::class.java)
                assert(user != null)
                listData.add(user!!.date + " l " + user!!.name)
                listTemp.add(user)
            }
            adapter.notifyDataSetChanged()
        }

        override fun onCancelled(error: DatabaseError) {

        }
    }
    private fun setOnItemClick(){
        binding.listView.setOnItemClickListener { adapterView, view, position, l ->
            val user = listTemp[position]
            val intent = Intent(this, LookActivity::class.java)
            intent.putExtra("description", user.description)
            intent.putExtra("theme", user.theme)
            startActivity(intent)
        }
    }
}

