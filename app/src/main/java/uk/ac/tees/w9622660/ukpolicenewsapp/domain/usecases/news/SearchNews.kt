package uk.ac.tees.w9622660.ukpolicenewsapp.domain.usecases.news

import androidx.paging.PagingData
import uk.ac.tees.w9622660.ukpolicenewsapp.domain.model.Article
import uk.ac.tees.w9622660.ukpolicenewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchNews @Inject constructor(
    private val newsRepository : NewsRepository
) {
    operator fun invoke(
        searchQuery : String ,
        sources : List<String>
    ) : Flow<PagingData<Article>>{
        return newsRepository.searchNews(searchQuery = searchQuery, sources = sources)
    }
}