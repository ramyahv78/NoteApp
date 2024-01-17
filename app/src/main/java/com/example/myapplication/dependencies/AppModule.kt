package com.example.myapplication.dependencies

import android.content.Context
import androidx.room.Room
import com.example.myapplication.model.DB.NoteDB
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext app: Context) =
        Room.databaseBuilder(app, NoteDB::class.java, "note_db").build()


    @Provides
    fun provideDao(db: NoteDB) = db.noteDao()
}