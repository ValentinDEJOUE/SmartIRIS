package com.example.valentindejoue.smartiris.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable

@Entity(tableName = "Client")
data class Client(
        @PrimaryKey(autoGenerate = true) var id: Int?,
        @ColumnInfo(name = "nom") var Nom: String,
        @ColumnInfo(name = "adresse") var Adresse: String,
        @ColumnInfo(name = "societe") var Société: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(Nom)
        parcel.writeString(Adresse)
        parcel.writeString(Société)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Client> {
        override fun createFromParcel(parcel: Parcel): Client {
            return Client(parcel)
        }

        override fun newArray(size: Int): Array<Client?> {
            return arrayOfNulls(size)
        }
    }
}