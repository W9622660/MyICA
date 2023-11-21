package uk.ac.tees.w9622660.ukpolicenewsapp.domain.usecases.news

import uk.ac.tees.w9622660.ukpolicenewsapp.data.local.NewsDao
import uk.ac.tees.w9622660.ukpolicenewsapp.domain.model.Article
import uk.ac.tees.w9622660.ukpolicenewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArticle @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke() : Flow<List<Article>>{
        return newsRepository.getArticle()
    }
}