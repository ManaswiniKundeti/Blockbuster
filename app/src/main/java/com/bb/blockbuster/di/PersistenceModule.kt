package com.bb.blockbuster.di

import android.content.Context
import androidx.room.Room
import com.bb.blockbuster.persistence.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object PersistenceModule {

    private var INSTANCE : AppDatabase? = null

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        if(INSTANCE == null) {
            synchronized(AppDatabase::class) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java,"movie_db")
                    .build()
            }
        }
        return INSTANCE!!
    }
}