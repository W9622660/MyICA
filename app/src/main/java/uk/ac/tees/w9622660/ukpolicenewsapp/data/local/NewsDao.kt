package uk.ac.tees.w9622660.ukpolicenewsapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uk.ac.tees.w9622660.ukpolicenewsapp.domain.model.Article
import uk.ac.tees.w9622660.ukpolicenewsapp.util.constants
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article)

    @Delete
    suspend fun delete(article : Article)

    @Query("SELECT * FROM ${constants.BM_TABLE_NAME}")
    fun getArticles() : Flow<List<Article>>

    @Query("SELECT * FROM ${constants.BM_TABLE_NAME} WHERE url =:url")
    suspend fun getArticleWithUrl(url : String) : Article?
}