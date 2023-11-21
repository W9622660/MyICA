package uk.ac.tees.w9622660.ukpolicenewsapp.domain.usecases.news

import javax.inject.Inject

data class NewsUseCases @Inject constructor(
    val getNews: GetNews,
    val searchNews: SearchNews,
    val getArticle: GetArticle,
    val upsertArticle: UpsertArticle,
    val deleteArticle: DeleteArticle,
    val getArticleWithUrl: GetArticleWithUrl
)
