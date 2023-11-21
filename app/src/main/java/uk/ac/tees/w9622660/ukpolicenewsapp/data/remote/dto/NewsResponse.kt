package uk.ac.tees.w9622660.ukpolicenewsapp.data.remote.dto

import uk.ac.tees.w9622660.ukpolicenewsapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)