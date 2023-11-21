package uk.ac.tees.w9622660.ukpolicenewsapp.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import uk.ac.tees.w9622660.ukpolicenewsapp.R
import uk.ac.tees.w9622660.ukpolicenewsapp.domain.model.Article
import uk.ac.tees.w9622660.ukpolicenewsapp.presentation.common.ArticleList
import uk.ac.tees.w9622660.ukpolicenewsapp.presentation.dimens.MediumPadding1
import uk.ac.tees.w9622660.ukpolicenewsapp.presentation.dimens.SmallPadding1
import uk.ac.tees.w9622660.ukpolicenewsapp.presentation.dimens.VerySmallPadding1

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigateToDetails: (Article) -> Unit,

) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(top = MediumPadding1, start = SmallPadding1, end = SmallPadding1)
    ) {

        Text(
            text = "Your Bookmarks",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(
                id = R.color.text_title
            )
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        ArticleList(
            articles = state.articles,
            onClick = navigateToDetails
        )
    }
}