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
                    var id : Int,
                    var type : String,
                    var price : Double,
                    val area : Int,
                    @ColumnInfo(name = "room_number")
                    val roomNumber : Int,
                    val bedroomNumber : Int,
                    val bathroomNumber : Int,
                    var description : String,
                    var photo: String,
                    val address : String,
                    val hospital : Boolean = false,
                    val school : Boolean = false,
                    val market : Boolean = false,
                    var status : String,
                    @ColumnInfo(name = "entry_date")
                    val entryDate : String,
                    @ColumnInfo(name = "compromise_date")
                    val compromiseDate : String?,
                    @ColumnInfo(name = "sell_date")
                    val sellDate : String?,
                    @ColumnInfo(name = "user_id")
                    val userId : Int)


@Entity(tableName = "photo",
        foreignKeys = [ForeignKey(entity = Property::class,
                        parentColumns = ["id"],
                        childColumns =["property_id"],
                        onDelete = CASCADE)],
        indices = [Index(value = ["property_id"])]
)
data class Photo (
                    @PrimaryKey(autoGenerate = true)
                    @ColumnInfo(name = "id_photo")
                    var idPhoto : Int,
                    @ColumnInfo(name = "url_photo")
                    var urlPhoto : String,
                    var name : String,
                    @ColumnInfo(name = "property_id")
                    var propertyId : Int)
