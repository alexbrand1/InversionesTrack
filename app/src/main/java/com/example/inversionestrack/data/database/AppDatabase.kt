package com.example.inversionestrack.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.inversionestrack.data.dao.*
import com.example.inversionestrack.data.model.*
@Database(
    entities = [
        User::class,
        UserProfile::class,
        InvestmentAccount::class,
        SavingsProject::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun userProfileDao(): UserProfileDao
    abstract fun investmentAccountDao(): InvestmentAccountDao
    abstract fun savingsProjectDao(): SavingsProjectDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "inversiones_track_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}