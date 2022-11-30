package com.example.inventariocomputo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.inventariocomputo.FirebaseConstant.DATA_INVENTARIO_COLLECTION
import com.example.inventariocomputo.databinding.ActivityFormBinding
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await

class FormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGuardar.setOnClickListener {
            val datos = DataInventario(
                nombre = binding.itNombre.text.toString(),
                caracteristicas = binding.itCarct.text.toString(),
                fecha = Timestamp.now(),
                funciona = binding.itFunciona.text.toString()
            )

            saveDataInventario(dataInventario = datos)
        }


    }

    fun saveDataInventario(dataInventario: DataInventario) {
        try {
            var isSuccessFul = false
            FirebaseFirestore.getInstance().collection(DATA_INVENTARIO_COLLECTION)
                .document(dataInventario.id)
                .set(dataInventario, SetOptions.merge())
                .addOnCompleteListener {
                    isSuccessFul = it.isSuccessful
                }

            if (isSuccessFul){
                Toast.makeText(this, "Guardado Correctamente", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show()
            }
        }catch (e:Exception){
            Toast.makeText(this, e.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}