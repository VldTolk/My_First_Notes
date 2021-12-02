package com.example.myfirstnotes.main.domain;

import java.util.List;

public interface NotesRepository {

    void getNotes(Callback<List<Note>> callback);
}
