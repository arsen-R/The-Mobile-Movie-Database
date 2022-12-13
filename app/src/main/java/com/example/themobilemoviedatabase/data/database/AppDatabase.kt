package com.example.themobilemoviedatabase.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.themobilemoviedatabase.data.database.entity.MovieDetailEntity
import com.example.themobilemoviedatabase.data.database.entity.TvShowDetailEntity

@Database(
    entities = [MovieDetailEntity::class, TvShowDetailEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var database: AppDatabase? = null
        private const val DATABASE_NAME = "movie.db"

        fun getInstance(context: Context): AppDatabase {
            return database ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                database = instance
                instance
            }
        }
    }

    abstract fun movieDao(): MovieDao
}