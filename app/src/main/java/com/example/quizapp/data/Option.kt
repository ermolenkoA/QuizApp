package com.example.quizapp.data

import android.os.Parcel
import android.os.Parcelable

enum class Option(val value: Int): Parcelable {

    A(0), B(1), C(2), D(3);

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Option> {

        private fun getOption(index: Int): Option? = when (index) {
            0 -> A
            1 -> B
            2 -> C
            3 -> D
            else -> null
        }
        override fun createFromParcel(parcel: Parcel): Option? {
            return getOption(parcel.readInt())
        }

        override fun newArray(size: Int): Array<Option?> {
            return arrayOfNulls(size)
        }
    }
}