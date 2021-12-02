package com.example.myfirstnotes.main.ui.list;

import com.example.myfirstnotes.main.domain.Callback;
import com.example.myfirstnotes.main.domain.Note;
import com.example.myfirstnotes.main.domain.NotesRepository;

import java.util.List;

public class NotesListPresenter {

    private final NotesListView view;

    private final NotesRepository repository;

    public NotesListPresenter(NotesListView view, NotesRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void requestNotes() {
        repository.getNotes(new Callback<List<Note>>() {
            @Override
            public void onSuccess(List<Note> result) {
                view.showNotes(result);
            }

            @Override
            public void onError(Throwable error) {

            }
        });
    }
}
