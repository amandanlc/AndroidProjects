package com.example.amandaroom

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.amandaroom.model.UsuarioModel
import com.example.amandaroom.repository.UsuarioDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Instancia de Room
        val usuarioDAO = UsuarioDataBase.getDataBase(this).usuarioDAO()

        CoroutineScope(Dispatchers.IO).launch {
            // Insert
            val usuario = UsuarioModel().apply {
                nome = "Amanda Nogueira"
                idade = 17
            }
            val retornoInsert = usuarioDAO.insertUser(usuario)
            Log.d("Insert", "ID inserido: $retornoInsert")

            // Select All
            var retornoSelectMultiplo = usuarioDAO.getAll()
            for (item in retornoSelectMultiplo) {
                Log.d("Retorno Múltiplo", "id_usuario: ${item.id_usuario}, nome: ${item.nome}, idade: ${item.idade}")
            }

            // Update
            if (retornoSelectMultiplo.isNotEmpty()) {
                val usuarioToUpdate = retornoSelectMultiplo[0]
                usuarioToUpdate.nome = "Amanda Silva"
                usuarioDAO.updateUser(usuarioToUpdate)
                Log.d("Update", "Usuário atualizado: ${usuarioToUpdate.nome}")
            }

            // Delete
            retornoSelectMultiplo = usuarioDAO.getAll()
            if (retornoSelectMultiplo.size > 1) {
                val usuarioToDelete = retornoSelectMultiplo[1]
                usuarioDAO.deleteUser(usuarioToDelete)
                Log.d("Delete", "Usuário deletado: ${usuarioToDelete.nome}")
            }

            // Select All again to verify changes
            retornoSelectMultiplo = usuarioDAO.getAll()
            for (item in retornoSelectMultiplo) {
                Log.d("Retorno Múltiplo", "id_usuario: ${item.id_usuario}, nome: ${item.nome}, idade: ${item.idade}")
            }
        }
    }
}
