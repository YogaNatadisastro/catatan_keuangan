package com.example.pencatatan_keuangan.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Pemasukan(

    val pemasukanId : String? = null,

    var note_content : String? = null,

    var deskripsi : String? = null,

    var input_uang : String? = null,

) : Parcelable




