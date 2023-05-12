package com.example.roomapp.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R
import com.example.roomapp.model.Note
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter(
    private val onLongClickListener: (Note, View) -> Unit
): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var noteList = emptyList<Note>()

    class MyViewHolder(
        itemView: View,
        private val onLongClickListener: (Note, View) -> Unit
    ): RecyclerView.ViewHolder(itemView) {
        private val tvId = itemView.findViewById<TextView>(R.id.tvId)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvDate = itemView.findViewById<TextView>(R.id.tvDate)
        private var currentItem: Note? = null

        init {
            itemView.setOnLongClickListener {
                currentItem?.let {
                    onLongClickListener(it, itemView)
                }
                return@setOnLongClickListener true
            }
        }

        fun bind(note: Note) {
            currentItem = note
            tvId.text = (adapterPosition + 1).toString()
            tvTitle.text = note.title
            tvDate.text = note.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false),
            onLongClickListener
        )
    }

    override fun getItemCount(): Int {
       return noteList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentNote = noteList[position]
        holder.bind(currentNote)

        holder.itemView.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentNote)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(note: List<Note>){
        this.noteList = note
        notifyDataSetChanged()
    }
}