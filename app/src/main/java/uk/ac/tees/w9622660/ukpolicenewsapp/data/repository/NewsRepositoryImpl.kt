package uk.ac.tees.w9622660.ukpolicenewsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import uk.ac.tees.w9622660.ukpolicenewsapp.data.local.NewsDao
import uk.ac.tees.w9622660.ukpolicenewsapp.data.remote.NewsApi
import uk.ac.tees.w9622660.ukpolicenewsapp.data.remote.NewsPagingSource
import uk.ac.tees.w9622660.ukpolicenewsapp.data.remote.SearchNewsPagingSource
import uk.ac.tees.w9622660.ukpolicenewsapp.domain.model.Article
import uk.ac.tees.w9622660.ukpolicenewsapp.domain.repository.NewsRepository
import uk.ac.tees.w9622660.ukpolicenewsapp.util.constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class NewsRepositoryImpl (
    private val newsApi : NewsApi,
    private val newsDao : NewsDao
) : NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = constants.PAGE_SIZE),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")             //we get sources in the repository constructor
                )
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = constants.PAGE_SIZE),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    searchQuery = searchQuery,
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")             //we get sources in the repository constructor
                )
            }
        ).flow
    }

    override suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

    override fun getArticle(): Flow<List<Article>> {
        return newsDao.getArticles().onEach { it.reversed() }
    }

    override suspend fun getArticleWithUrl(url: String): Article? {
        return newsDao.getArticleWithUrl(url)
    }


}