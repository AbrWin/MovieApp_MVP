package com.abrsoftware.myapplication.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "movies", indices = [Index(value = ["movieId"], unique = true)])
data class Movie(@PrimaryKey(autoGenerate = true)
                 var movieId: Int? = null,
                 val title: String?,
                 var release_date: String?,
                 var poster_path: String?,
                 val overview: String?,
                 var type: String?) : Parcelable, Serializable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(movieId)
        parcel.writeString(title)
        parcel.writeString(release_date)
        parcel.writeString(poster_path)
        parcel.writeString(overview)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}

