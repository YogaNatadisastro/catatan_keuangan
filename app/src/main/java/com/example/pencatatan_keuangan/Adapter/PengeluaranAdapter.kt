package com.example.pencatatan_keuangan.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pencatatan_keuangan.Model.Pengeluaran
import com.example.pencatatan_keuangan.R

class PengeluaranAdapter(private val pengeluaranList : ArrayList<Pengeluaran>) : RecyclerView.Adapter<PengeluaranAdapter.PengeluaranAdapter>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PengeluaranAdapter {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyle_item_uang_keluar,
            parent, false)
        return PengeluaranAdapter(itemView)
    }

    override fun getItemCount(): Int {
        return pengeluaranList.size
    }

    override fun onBindViewHolder(
        holder: PengeluaranAdapter, position: Int) {
        val currentitem = pengeluaranList[position]

        holder.note_content.text = currentitem.note_content
        holder.uang_keluar.text = currentitem.input_uang

    }

    class PengeluaranAdapter(itemView: View) : RecyclerView.ViewHolder(itemView){

        val note_content : TextView = itemView.findViewById(R.id.deskripsi)
        val uang_keluar : TextView = itemView.findViewById(R.id.input_uang)

    }

}