package com.axondragonscale.npinner.di

import android.content.Context
import com.axondragonscale.npinner.data.NPinnerDatabase
import com.axondragonscale.npinner.data.dao.NotificationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Ronak Harkhani on 30/04/23
 */

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideNPinnerDatabase(@ApplicationContext context: Context): NPinnerDatabase {
        return NPinnerDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideNotificationDao(database: NPinnerDatabase): NotificationDao {
        return database.notificationDao()
    }
}
