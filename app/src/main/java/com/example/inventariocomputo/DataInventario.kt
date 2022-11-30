package com.example.inventariocomputo

import android.os.Parcelable
import com.google.firebase.Timestamp
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class DataInventario (
    val id:String = UUID.randomUUID().toString(),
    val nombre:String= "",
    val caracteristicas:String = "",
    val fecha:Timestamp? = null,
    val funciona:String= "",
):Parcelable