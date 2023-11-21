package uk.ac.tees.w9622660.ukpolicenewsapp.presentation.newsDetails

import uk.ac.tees.w9622660.ukpolicenewsapp.domain.model.Article

sealed class DetailsEvent {
    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()
    object RemoveSideEffect : DetailsEvent()
}