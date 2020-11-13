package com.craft.furniture.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.craft.furniture.constants.DbConstants
import com.craft.furniture.data.model.db.FurnitureListEntity.Companion.HOME_PAGE_TABLE

/**
 * This data class is  used both as Data class for api
 * and Entity class to Home page  value in [FurnitureListEntity.HOME_PAGE_TABLE]
 */
@Suppress("SpellCheckingInspection")
@Entity(tableName = HOME_PAGE_TABLE)
data class FurnitureListEntity(
    @PrimaryKey
    @ColumnInfo(name = DbConstants.CODE)           val product_code:      String,
    @ColumnInfo(name = DbConstants.PRODUCT_ID)     val id:                String,
    @ColumnInfo(name = DbConstants.IMAGE)          val image:             String?,
    @ColumnInfo(name = DbConstants.PRICE)          val price:             String?,
    @ColumnInfo(name = DbConstants.DISCOUNT_PRICE) val desc_price:        String?,
    @ColumnInfo(name = DbConstants.DESCRIPTION)    val description:       String?,
    @ColumnInfo(name = DbConstants.VENDOR_NAME)    val vendor_name:       String?,
    @ColumnInfo(name = DbConstants.COLOR_NAME)     val color_name:        String?,
    @ColumnInfo(name = DbConstants.PRODUCT_NAME)   val product_name:      String?,
    @ColumnInfo(name = DbConstants.WEIGHT)         val weight:            String?,
    @ColumnInfo(name = DbConstants.DIMENSION)      val product_dimention: String?,
    @ColumnInfo(name = DbConstants.SHIPPING_WEIGHT)val shipping_weight:   String?,
    @ColumnInfo(name = DbConstants.MATERIAL)       val material_name:     String?,
)
{
    companion object{
        const val HOME_PAGE_TABLE = "home_page"
    }
}