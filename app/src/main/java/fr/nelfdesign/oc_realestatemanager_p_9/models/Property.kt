package fr.nelfdesign.oc_realestatemanager_p_9.models

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Nelfdesign at 25/02/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.models
 */
@Entity(tableName = "property")
data class Property(
                    @PrimaryKey(autoGenerate = true)
                    val id : Int,
                    var type : String,
                    var price : Int,
                    val area : Int,
                    @ColumnInfo(name = "room_number")
                    val roomNumber : Int,
                    val bedroomNumber : Int,
                    val bathroomNumber : Int,
                    var description : String,
                    var photo: String,
                    val address : String,
                    val pois : String?,
                    var status : String,
                    @ColumnInfo(name = "entry_date")
                    val entryDate : String,
                    @ColumnInfo(name = "sell_date")
                    val sellDate : String?,
                    @ColumnInfo(name = "user_id")
                    val userId : Int)


@Entity(tableName = "photo",
        foreignKeys = [ForeignKey(entity = Property::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("property_id"),
                        onDelete = CASCADE)],
        indices = [Index(value = ["property_id"])]
)
data class Photo (
                    @PrimaryKey(autoGenerate = true)
                    val id : Int,
                    @ColumnInfo(name = "url_photo")
                    val urlPhoto : String,
                    val name : String,
                    @ColumnInfo(name = "property_id")
                    var propertyId : Int)
