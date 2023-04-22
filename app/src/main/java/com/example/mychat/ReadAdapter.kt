package com.example.mychat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mychat.databinding.CardViewBinding


class ReadAdapter(private val activity: ReadActivity): RecyclerView.Adapter<ReadAdapter.ReadHolder>() {
    val listRead = ArrayList<User>()
    class ReadHolder(item: View): RecyclerView.ViewHolder(item){
        val binding = CardViewBinding.bind(item)
        fun bind(new: User) = with(binding){
            tvName.text = new.name
            tvDate.text = new.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReadHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return ReadHolder(view)
    }

    override fun onBindViewHolder(holder: ReadHolder, position: Int) {
        holder.bind(listRead[position])
        holder.binding.tvName.setOnClickListener{
            activity.setOnItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return listRead.size
    }
    fun getUser(user: User){
        listRead.add(user)
        notifyDataSetChanged()
    }

}