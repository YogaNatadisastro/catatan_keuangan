package com.example.pencatatan_keuangan.Model

import com.example.pencatatan_keuangan.Detail_Pemasukan

data class Pemasukan(
    val pemasukanId : String? = null,
    val note_content : String? = null,
    val deskripsi : String? = null,
    val input_uang : String? = null
)


