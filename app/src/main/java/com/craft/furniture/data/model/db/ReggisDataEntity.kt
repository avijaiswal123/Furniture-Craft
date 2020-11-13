package com.craft.furniture.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.craft.furniture.constants.DbConstants
import com.craft.furniture.data.model.db.ReggisDataEntity.Companion.REGISTER_TABLE
/**
 * This data class is  used both as Data class for api
 * and Entity class to save Registration data in [ReggisDataEntity.REGISTER_TABLE]
 */
@Entity(tableName = REGISTER_TABLE)
data class ReggisDataEntity(
    @ColumnInfo(name = DbConstants.FIRST_NAME) var first_name: String?,
    @ColumnInfo(name = DbConstants.LAST_NAME)  var last_name:  String? = "",
    @ColumnInfo(name = DbConstants.EMAIL)      var email:      String?,
    @ColumnInfo(name = DbConstants.PHONE)      var phone:      String?,
    @ColumnInfo(name = DbConstants.PASSWORD)   var password:   String?,
    @ColumnInfo(name = DbConstants.USER_ID)    var user_id:    String?
){
    @PrimaryKey
    var id: Int = 0

    companion object{
        const val REGISTER_TABLE = "registerTable"
    }
}