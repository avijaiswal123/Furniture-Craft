package com.craft.furniture.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.craft.furniture.data.local.db.dao.FurnitureDao
import com.craft.furniture.data.local.db.dao.RegisterDao
import com.craft.furniture.data.model.db.ReggisDataEntity
import com.craft.furniture.data.model.db.FurnitureListEntity
/**
 * Furniture Craft Database
 * */
@Database(
    entities = [
        FurnitureListEntity ::class,
        ReggisDataEntity ::class
    ],
    version = 1,
    exportSchema = false
)
/**
 * Abstract class to provides [FurnitureDao] and [RegisterDao]
 * */
abstract class AppDatabase :RoomDatabase(){
    abstract fun getFurnitureDao(): FurnitureDao
    abstract fun getRegisterDao(): RegisterDao
}