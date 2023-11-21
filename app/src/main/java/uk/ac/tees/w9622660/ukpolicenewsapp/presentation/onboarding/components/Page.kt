package uk.ac.tees.w9622660.ukpolicenewsapp.presentation.onboarding.components
import androidx.annotation.DrawableRes
import uk.ac.tees.w9622660.ukpolicenewsapp.R


data class Page(
    val title : String,
    val description : String,
    @DrawableRes val image : Int
)

//making a list of Page
val pages = listOf<Page>(
    Page(
        title ="Police related News across UK" ,
        description = "UKPoliceNewsApp Allows you to scroll through latest news around the UK" ,
        image = R.drawable.image1
    ) ,
    Page(
        title ="Search News related to police and other related too in the UK!" ,
        description = "UKPoliceNewsApp allows you to search for news crimes" ,
        image = R.drawable.image2
    ),
    Page(
        title ="Save you news!!" ,
        description = "UKPoliceNewsApp also has the feature to save your favourite news to revisit them!" ,
        image = R.drawable.image3
    )
)
