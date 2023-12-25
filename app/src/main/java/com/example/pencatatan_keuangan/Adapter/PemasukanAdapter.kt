package com.example.pencatatan_keuangan.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pencatatan_keuangan.Model.Pemasukan
import com.example.pencatatan_keuangan.R

class PemasukanAdapter(private val pemasukanList : ArrayList<Pemasukan>) : RecyclerView.Adapter<PemasukanAdapter.PemasukanAdapter>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PemasukanAdapter {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyle_item_uang_masuk,
            parent, false)
        return PemasukanAdapter(itemView)
    }

    override fun getItemCount(): Int {
        return pemasukanList.size
    }

    override fun onBindViewHolder(holder: PemasukanAdapter, position: Int) {
        val currentitem = pemasukanList[position]

        holder.note_content.text = currentitem.note_content
        holder.uang_masuk.text = currentitem.input_uang.toString()

    }

    fun updateData(newDataList: List<Pemasukan>) {

    }

    class PemasukanAdapter(itemView : View) : RecyclerView.ViewHolder(itemView){

        val note_content : TextView = itemView.findViewById(R.id.deskripsi)
        val uang_masuk : TextView = itemView.findViewById(R.id.input_uang)


    }

}