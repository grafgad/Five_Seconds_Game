package com.example.fivesecondsgame

import android.app.Application
import androidx.room.Room
import com.example.fivesecondsgame.data.room.QuestionDB

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        db = Room.databaseBuilder(
            applicationContext,
            QuestionDB::class.java,
            "question-database.db"
        )
//            .fallbackToDestructiveMigration()
            .build()
    }

    companion object {
        lateinit var instance: App
        lateinit var db : QuestionDB
    }
}