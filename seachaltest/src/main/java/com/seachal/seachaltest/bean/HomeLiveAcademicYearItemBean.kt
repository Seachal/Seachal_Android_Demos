package com.seachal.seachaltest.bean

import android.os.Parcel
import android.os.Parcelable

/**
 **
 * *
 * 首页直播 直播学年
 *
 * Project_Name:TrainingCourseInfoActivity.kt
 * @Description: TODO
 * @author zhangxc
 * @date 2024/5/23 10:18
 * @Version：1.0
 *
 *    {
 *       "academicYear": "2023-2024",
 *       "currentMonth": "2024-05",
 *       "currentTime": "1716436324452",
 *       "endTime": "1725119999452",
 *       "startTime": "1696003200452"
 *     }
 */
data class HomeLiveAcademicYearItemBean(
    val academicYear: String?,
    val currentMonth: String?,
    val currentTime: Long?,
    val endTime: Long,
    val startTime: Long
) : Parcelable {

    //  计算属性，判断是否是当前学年，  startTime<=currentTime<=endTime
    val isCurrentAcademic: Boolean
        get() = currentTime?.let {
            startTime <= it && it <= endTime
        } ?: false


    // 计算属性，格式化为 "23/24学年"
    val formattedAcademicYear: String?
        get() {
            val parts = academicYear?.split("-")
            if (parts?.size == 2) {
                val startYear = parts[0].takeLast(2) // 取后两位
                val endYear = parts[1].takeLast(2)   // 同上
                return "${startYear}/${endYear}学年"
            } else {
                return null
            }
        }

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readLong(),
        parcel.readLong()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(academicYear)
        parcel.writeString(currentMonth)
        parcel.writeValue(currentTime)
        parcel.writeLong(endTime)
        parcel.writeLong(startTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HomeLiveAcademicYearItemBean> {
        override fun createFromParcel(parcel: Parcel): HomeLiveAcademicYearItemBean {
            return HomeLiveAcademicYearItemBean(parcel)
        }

        override fun newArray(size: Int): Array<HomeLiveAcademicYearItemBean?> {
            return arrayOfNulls(size)
        }
    }


}