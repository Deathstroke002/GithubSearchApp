package com.example.recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter ( val reposApi: List<Repos>):
    RecyclerView.Adapter<RecyclerAdapter.ReposViewHolder>() {

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ReposViewHolder {
//        val v  = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
//        return ViewHolder(v)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent, false)
        return ReposViewHolder(view, mListener)

    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ReposViewHolder, position: Int) {
        val repoApi = reposApi[position]
        holder.repoName.text = repoApi.full_name
        holder.repoDescription.text = repoApi.description
    }

    override fun getItemCount(): Int {
        return reposApi.size
    }

    inner class ReposViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        var repoName = itemView.findViewById<TextView>(R.id.repoFullName)
        var repoDescription = itemView.findViewById<TextView>(R.id.repoDescription)

        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }

}