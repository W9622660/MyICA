package uk.ac.tees.w9622660.ukpolicenewsapp.domain.usecases.news

import uk.ac.tees.w9622660.ukpolicenewsapp.data.local.NewsDao
import uk.ac.tees.w9622660.ukpolicenewsapp.domain.model.Article
import uk.ac.tees.w9622660.ukpolicenewsapp.domain.repository.NewsRepository
import javax.inject.Inject

class UpsertArticle @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article : Article) {
        newsRepository.upsertArticle(article)
    }
}