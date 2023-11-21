package uk.ac.tees.w9622660.ukpolicenewsapp.di

import android.app.Application
import androidx.room.Room
import uk.ac.tees.w9622660.ukpolicenewsapp.data.local.NewsDao
import uk.ac.tees.w9622660.ukpolicenewsapp.data.local.NewsDatabase
import uk.ac.tees.w9622660.ukpolicenewsapp.data.local.NewsTypeConvertor
import uk.ac.tees.w9622660.ukpolicenewsapp.data.manager.LocalUserManagerImpl
import uk.ac.tees.w9622660.ukpolicenewsapp.data.remote.NewsApi
import uk.ac.tees.w9622660.ukpolicenewsapp.data.repository.NewsRepositoryImpl
import uk.ac.tees.w9622660.ukpolicenewsapp.domain.manager.LocalUserManager
import uk.ac.tees.w9622660.ukpolicenewsapp.domain.repository.NewsRepository
import uk.ac.tees.w9622660.ukpolicenewsapp.domain.usecases.appEntry.AppEntryUseCases
import uk.ac.tees.w9622660.ukpolicenewsapp.domain.usecases.appEntry.ReadAppEntry
import uk.ac.tees.w9622660.ukpolicenewsapp.domain.usecases.appEntry.SaveAppEntry
import uk.ac.tees.w9622660.ukpolicenewsapp.domain.usecases.news.GetNews
import uk.ac.tees.w9622660.ukpolicenewsapp.domain.usecases.news.NewsUseCases
import uk.ac.tees.w9622660.ukpolicenewsapp.domain.usecases.news.SearchNews
import uk.ac.tees.w9622660.ukpolicenewsapp.util.constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ) : LocalUserManager = LocalUserManagerImpl(application)


    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager : LocalUserManager
    ) = AppEntryUseCases(
        saveAppEntry = SaveAppEntry(localUserManager),
        readAppEntry = ReadAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi() : NewsApi {
        return Retrofit.Builder()
            .baseUrl(constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi , newsDao: NewsDao) : NewsRepository = NewsRepositoryImpl(newsApi , newsDao)

//removed this because NewsUseCases has @Inject constructor
//    @Provides
//    @Singleton
//    fun provideNewsUseCases(
//        newsRepository : NewsRepository
//    ) = NewsUseCases(
//        getNews = GetNews(newsRepository),
//        searchNews = SearchNews(newsRepository)
//    )

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ) : NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = constants.NEWS_DB
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ) : NewsDao = newsDatabase.newsDao
}