package com.lugares.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lugares.data.LugarDao
import com.lugares.model.Lugar


@Database(entities = [Lugar::class], version = 1, exportSchema = false)
abstract class LugarDataBase : RoomDatabase() {
    abstract fun lugarDao() : LugarDao

    companion object{

        @Volatile
        private var INSTANCE: LugarDataBase? = null

        fun getDataBase(context: android.content.Context) : LugarDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LugarDataBase::class.java,
                    "lugar_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}