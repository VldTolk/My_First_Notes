package com.example.myfirstnotes.main.domain;

import com.example.myfirstnotes.R;

import java.util.ArrayList;
import java.util.List;

public class InMemoryNotesRepository implements NotesRepository{
    @Override
    public List<Note> getNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(new Note(R.string.passwords, R.string.text_passwords, R.string.category_security, R.string.date_passwords));
        notes.add(new Note(R.string.recipes, R.string.text_recipes, R.string.category_casual, R.string.date_recipes));
        notes.add(new Note(R.string.cities, R.string.text_cities, R.string.category_travels, R.string.date_cities));
        notes.add(new Note(R.string.cities, R.string.text_cities, R.string.category_travels, R.string.date_cities));
        notes.add(new Note(R.string.cities, R.string.text_cities, R.string.category_travels, R.string.date_cities));
        notes.add(new Note(R.string.cities, R.string.text_cities, R.string.category_travels, R.string.date_cities));
        notes.add(new Note(R.string.cities, R.string.text_cities, R.string.category_travels, R.string.date_cities));
        notes.add(new Note(R.string.cities, R.string.text_cities, R.string.category_travels, R.string.date_cities));
        notes.add(new Note(R.string.cities, R.string.text_cities, R.string.category_travels, R.string.date_cities));
        notes.add(new Note(R.string.cities, R.string.text_cities, R.string.category_travels, R.string.date_cities));
        notes.add(new Note(R.string.cities, R.string.text_cities, R.string.category_travels, R.string.date_cities));
        notes.add(new Note(R.string.cities, R.string.text_cities, R.string.category_travels, R.string.date_cities));
        notes.add(new Note(R.string.recipes, R.string.text_recipes, R.string.category_casual, R.string.date_recipes));
        notes.add(new Note(R.string.cities, R.string.text_cities, R.string.category_travels, R.string.date_cities));
        return notes;
    }
}
