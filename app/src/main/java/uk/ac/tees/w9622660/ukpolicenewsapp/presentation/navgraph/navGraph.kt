package uk.ac.tees.w9622660.ukpolicenewsapp.presentation.navgraph

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import uk.ac.tees.w9622660.ukpolicenewsapp.domain.model.Article
import uk.ac.tees.w9622660.ukpolicenewsapp.presentation.bookmark.BookmarkScreen
import uk.ac.tees.w9622660.ukpolicenewsapp.presentation.bookmark.BookmarkViewModel
import uk.ac.tees.w9622660.ukpolicenewsapp.presentation.home.HomeScreen
import uk.ac.tees.w9622660.ukpolicenewsapp.presentation.home.HomeViewModel
import uk.ac.tees.w9622660.ukpolicenewsapp.presentation.newsDetails.DetailsEvent
import uk.ac.tees.w9622660.ukpolicenewsapp.presentation.newsDetails.DetailsScreen
import uk.ac.tees.w9622660.ukpolicenewsapp.presentation.newsDetails.DetailsViewModel
import uk.ac.tees.w9622660.ukpolicenewsapp.presentation.onboarding.OnBoardingViewModel
import uk.ac.tees.w9622660.ukpolicenewsapp.presentation.onboarding.components.OnBoardingScreen



@Composable
fun NavGraph(
    startDestination : String
){
    val navController = rememberNavController()
    fun navigate(route : String){
        navController.navigate(route)
    }
    fun navigateToDetails( article : Article){            //overloaded navigate function to detail screen
        navController.currentBackStackEntry?.savedStateHandle?.set("article" , article)
        navController.navigate(Route.DetailsScreen.route)
    }
    fun navigateToBookmark(){
        navController.navigate(Route.BookmarkScreen.route)
    }

    NavHost(navController = navController, startDestination = startDestination){

        navigation(
            startDestination = Route.OnBoardingScreen.route,
            route = Route.AppStartNaviation.route
        ){
            composable(
                route = Route.OnBoardingScreen.route
            ){
                val viewModel : OnBoardingViewModel = hiltViewModel()      //the app entry use cases
                OnBoardingScreen(
                    event = {        //basically we expose only required functions of our viewmodel to the composable rather than passing the whole view model
                        viewModel.onEvent(it)
                    }
                )
            }
        }

        navigation(
            startDestination = Route.NewsNavigatorScreen.route,
            route = Route.NewsNavigation.route
        ){
            composable(
                route = Route.NewsNavigatorScreen.route
            ){
                val homeViewModel : HomeViewModel = hiltViewModel()
                val articles = homeViewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles = articles,
                    navigate = {
                        navigate(it)
                    },
                    onClickDetails = {
                        navigateToDetails(it)
                    },
                    onClickBookmark = {
                        navigateToBookmark()
                    }
                )

            }
            composable(
                route = Route.SearchScreen.route + "/{searchQuery}"
            ){backStackEntry->
                val searchQuery = backStackEntry.arguments?.getString("searchQuery")?: "top-news"
                val homeViewModel : HomeViewModel = hiltViewModel()
                homeViewModel.updateSearchQuery(searchQuery)
                val articles = homeViewModel.searchedNews.collectAsLazyPagingItems()
                HomeScreen(
                    articles = articles,
                    navigate = {navigate(it)},
                    onClickDetails = {
                        navigateToDetails(it)
                    },
                    onClickBookmark = {
                        navigateToBookmark()
                    }
                )
            }
            composable(
                route = Route.DetailsScreen.route,
            ){
                val detailsViewModel : DetailsViewModel = hiltViewModel()
                if(detailsViewModel.sideEffect != null){
                    Toast.makeText(LocalContext.current , detailsViewModel.sideEffect , Toast.LENGTH_SHORT).show()
                    detailsViewModel.onEvent(DetailsEvent.RemoveSideEffect)
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")?.let {
                    DetailsScreen(article = it, event = detailsViewModel::onEvent , navigateUp = {navController.navigateUp()})
                }
            }

            composable(
                route = Route.BookmarkScreen.route,
            ){
                val bookmarkViewModel : BookmarkViewModel = hiltViewModel()
                val state = bookmarkViewModel.state.value
                BookmarkScreen(
                    state = state,
                    navigateToDetails = {navigateToDetails(it)}
                )
            }
        }
    }
}

