package com.example.myfirstnotes.main.domain;

import android.os.Handler;
import android.os.Looper;

import com.example.myfirstnotes.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class InMemoryNotesRepository implements NotesRepository{

    private final Executor executor = Executors.newSingleThreadExecutor();

    private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    @Override
    public void getNotes(Callback<List<Note>> callback) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<Note> result = new ArrayList<Note>();
                for (int i = 0; i < 500; i++) {
                    result.add(new Note(R.string.cities, R.string.text_cities, R.string.date_cities, R.string.category_travels));
                    result.add(new Note(R.string.recipes, R.string.text_recipes, R.string.date_recipes, R.string.category_casual));
                    result.add(new Note(R.string.passwords, R.string.text_passwords, R.string.date_passwords, R.string.category_security));
                }

                mainThreadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(result);
                    }
                });

            }
        });
    }
}
