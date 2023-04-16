package com.example.mychat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.mychat.databinding.ActivityReadBinding
import com.google.firebase.database.*

class ReadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReadBinding
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var listData: MutableList<String>
    private lateinit var listener: Listener

    lateinit var mBase: DatabaseReference
    private var USER_KEY = "User"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listener = Listener()
        init()
    }
    private fun init(){
        listData = ArrayList()
        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, listData)
        binding.listView.adapter = adapter
        mBase = FirebaseDatabase.getInstance().getReference(USER_KEY)
        mBase.addValueEventListener(listener)

    }
    inner class Listener: ValueEventListener{

        override fun onDataChange(snapshot: DataSnapshot) {

            if (listData.size > 0) listData.clear()

            for(ds in snapshot.children){
                val user = ds.getValue(User::class.java)
                assert(user != null)
                listData.add(user!!.name)
            }
            adapter.notifyDataSetChanged()
        }

        override fun onCancelled(error: DatabaseError) {

        }
    }
}

