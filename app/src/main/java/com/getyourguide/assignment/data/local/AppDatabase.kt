package com.getyourguide.assignment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.getyourguide.assignment.data.local.dao.RemoteKeyDao
import com.getyourguide.assignment.data.local.dao.ReviewDao
import com.getyourguide.assignment.data.local.entity.RemoteKeyEntity
import com.getyourguide.assignment.data.local.entity.ReviewEntity

@Database(entities = [ReviewEntity::class,RemoteKeyEntity::class], version = 1)
@TypeConverters(Convertors::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "app_db"
    }

    abstract fun reviewDao(): ReviewDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}