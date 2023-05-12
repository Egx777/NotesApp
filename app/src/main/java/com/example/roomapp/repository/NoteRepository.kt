package com.example.roomapp.repository

import androidx.lifecycle.LiveData
import com.example.roomapp.data.NoteDao
import com.example.roomapp.model.Note

class NoteRepository(private val noteDao: NoteDao) {

    val readAllData: LiveData<List<Note>> = noteDao.readAllData()

    suspend fun addNote(user: Note){
        noteDao.addNote(user)
    }

    suspend fun updateNote(user: Note){
        noteDao.updateNote(user)
    }

    suspend fun deleteNote(user: Note){
        noteDao.deleteNote(user)
    }

    suspend fun deleteAllNotes(){
        noteDao.deleteAllNotes()
    }

}