package com.craft.furniture.data.local.db

import android.content.Context
import androidx.room.Room

/**
 * Get Database unique instance
 * */
object DatabaseBuilder {
    private const val DATABASE_NAME = "FurnitureCraft"
    private var INSTANCE: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        val mContext = context.applicationContext
        if (INSTANCE == null) {
            synchronized(AppDatabase::class) {
                INSTANCE = buildRoomDB(mContext)
            }
        }
        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
}
