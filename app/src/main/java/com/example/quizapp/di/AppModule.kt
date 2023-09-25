package com.example.quizapp.di

import android.content.Context
import androidx.room.Room
import com.example.quizapp.model.RoomTopicRepository
import com.example.quizapp.model.TopicDao
import com.example.quizapp.model.TopicDatabase
import com.example.quizapp.model.TopicRepository
import com.example.quizapp.utils.UtilsEn.databaseEN
import com.example.quizapp.utils.UtilsRu.databaseRU
import com.example.quizapp.utils.UtilsRu.langKeyRu
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): TopicDatabase {
        val isLocaleRu = context
            .resources
            .configuration
            .locales
            .get(0)
            .language == langKeyRu
        return Room.databaseBuilder(
            context,
            TopicDatabase::class.java,
            if(isLocaleRu) {
                databaseRU
            } else {
                databaseEN
            }
        ).build()
    }

    @Provides
    fun provideTopicDao(database: TopicDatabase): TopicDao {
        return database.topicDao()
    }

    @Provides
    fun provideDatabaseCoroutineScope(): CoroutineScope {
        return CoroutineScope(Dispatchers.IO)
    }

    @Provides
    fun provideRoomTopicRepository(
        dao: TopicDao,
        scope: CoroutineScope
    ): TopicRepository {
        return RoomTopicRepository(dao, scope)
    }
}