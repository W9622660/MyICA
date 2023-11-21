package uk.ac.tees.w9622660.ukpolicenewsapp.presentation.bookmark

import uk.ac.tees.w9622660.ukpolicenewsapp.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)