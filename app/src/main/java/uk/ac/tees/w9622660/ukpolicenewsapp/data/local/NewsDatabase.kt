package uk.ac.tees.w9622660.ukpolicenewsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import uk.ac.tees.w9622660.ukpolicenewsapp.domain.model.Article

@Database(entities = [Article::class] , version = 2 , exportSchema = false)
@TypeConverters(NewsTypeConvertor::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract val newsDao : NewsDao
}