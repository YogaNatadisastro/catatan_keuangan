package com.example.pencatatan_keuangan.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pencatatan_keuangan.Model.Dompetku
import com.example.pencatatan_keuangan.R

class DompetkuAdapter(private val dompekuList : ArrayList<Dompetku>) : RecyclerView.Adapter<DompetkuAdapter.DompetkuAdapter>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DompetkuAdapter {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.dompet_recycler,
            parent, false)
        return DompetkuAdapter(itemView)
    }

    override fun getItemCount(): Int {
        return dompekuList.size
    }

    override fun onBindViewHolder(holder: DompetkuAdapter, position: Int) {
        val currentItem = dompekuList[position]
        holder.deskripsiDompet.text = currentItem.deskripsi
        holder.jumlahUangDompet.text = currentItem.jumlah_uang
    }
    class DompetkuAdapter(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val deskripsiDompet : TextView = itemView.findViewById(R.id.deskripsi_dompet)
        val jumlahUangDompet : TextView = itemView.findViewById(R.id.jumlah_uang)
    }

}