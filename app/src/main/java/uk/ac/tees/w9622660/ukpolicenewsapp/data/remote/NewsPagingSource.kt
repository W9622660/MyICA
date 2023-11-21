package uk.ac.tees.w9622660.ukpolicenewsapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import uk.ac.tees.w9622660.ukpolicenewsapp.domain.model.Article
import uk.ac.tees.w9622660.ukpolicenewsapp.util.constants

class NewsPagingSource(
    private val newsApi : NewsApi,
    private val sources : String,
) : PagingSource<Int , Article>() {

    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        val pageSize = params.loadSize
        return try{
            val newsResponse = newsApi.getNews(page , sources , constants.API_KEY , pageSize)
            totalNewsCount += newsResponse.articles.size
            val articles = newsResponse.articles.distinctBy { it.title }

            LoadResult.Page(
                data = articles,
                nextKey = if(totalNewsCount == newsResponse.totalResults) null else page + 1,
                prevKey = if(page == 1) null else page - 1

            )
        }
        catch (e : Exception){
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?:anchorPage?.nextKey?.minus(1)
        }
    }
}