package br.com.quiz.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.quiz.source.model.Perfil
import br.com.quiz.source.model.Usuario

@Database(
    entities = [
        Usuario::class,Perfil::class],
    version = 2,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun perfilDao(): PerfilDao

    companion object {

        private var INSTANCE: AppDataBase? = null

        private val lock = Any()

        fun getInstance(context: Context): AppDataBase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBase::class.java!!, "quiz")
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return INSTANCE!!
            }
        }


    }
}
