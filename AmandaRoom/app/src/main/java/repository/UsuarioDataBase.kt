//package com.example.brenoroom.repository
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import androidx.room.migration.Migration
//import androidx.sqlite.db.SupportSQLiteDatabase
//import com.example.brenoroom.model.UsuarioModel
//import com.example.brenoroom.repository.dao.UsuarioDao
//
//@Database(entities = [UsuarioModel::class], version = 1)
//abstract class UsuarioDataBase: RoomDatabase() {
//
//    abstract fun usuarioDAO(): UsuarioDao//Referência de UsuarioDAO, pois iremos selecionar essa classe por meio da instancia do banco
//
//    companion object{
//        private lateinit var INSTANCE: UsuarioDataBase
//
//        fun getDataBase(context: Context): UsuarioDataBase{
//            if(!::INSTANCE.isInitialized) {
//                synchronized(UsuarioDataBase::class) {
//                    INSTANCE =
//                        Room.databaseBuilder(context, UsuarioDataBase::class.java, "usuariodb").addMigrations(
//                            MIGRATION_1_2, MIGRATION_2_3).allowMainThreadQueries().build()
//                }
//            }
//            return INSTANCE
//        }
//
//        private val MIGRATION_1_2: Migration = object : Migration(1, 2){
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("")
//            }
//        }
//
//        private val MIGRATION_2_3: Migration = object : Migration(2, 3){
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("")
//            }
//        }
//
//    }
//
//}

package com.example.AmandaRoom.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.AmandaRoom.model.UsuarioModel
import com.example.AmandaRoom.repository.dao.UsuarioDao

@Database(entities = [UsuarioModel::class], version = 1)
abstract class UsuarioDataBase : RoomDatabase() {
    abstract fun usuarioDAO(): UsuarioDao

    companion object {
        @Volatile
        private var INSTANCE: UsuarioDataBase? = null

        fun getDataBase(context: Context): UsuarioDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UsuarioDataBase::class.java,
                    "usuario_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
