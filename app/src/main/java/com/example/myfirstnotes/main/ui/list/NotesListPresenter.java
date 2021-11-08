package com.example.myfirstnotes.main.ui.list;

import com.example.myfirstnotes.main.domain.NotesRepository;

public class NotesListPresenter {

    private final NotesListView view;

    private final NotesRepository repository;

    public NotesListPresenter(NotesListView view, NotesRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void requestNotes() {
        view.showNotes(repository.getNotes());
    }
}
