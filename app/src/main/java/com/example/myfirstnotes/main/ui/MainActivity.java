package com.example.myfirstnotes.main.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.example.myfirstnotes.R;
import com.example.myfirstnotes.main.domain.Note;
import com.example.myfirstnotes.main.ui.details.ToWriteNoteFragment;
import com.example.myfirstnotes.main.ui.list.NotesListFragment;

public class MainActivity extends AppCompatActivity {

    private static final String ARG_NOTE = "ARG_NOTE";

    Note selectedNote;
    ToWriteNoteFragment toWriteNoteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        boolean isLandscape = getResources().getBoolean(R.bool.is_landscape);

        if (!(fragmentManager.findFragmentById(R.id.fragment_container) instanceof NotesListFragment)){
            fragmentManager.popBackStack();
        }

        if (savedInstanceState != null && savedInstanceState.containsKey(ARG_NOTE)) {
            if (isLandscape) {
                selectedNote = savedInstanceState.getParcelable(ARG_NOTE);
                toWriteNoteFragment = ToWriteNoteFragment.newInstance(selectedNote);
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_to_write_note_container, toWriteNoteFragment)
                        .commit();
            }
        }

        getSupportFragmentManager().setFragmentResultListener(NotesListFragment.KEY_NOTES_LIST_ACTIVITY, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                selectedNote = result.getParcelable(NotesListFragment.ARG_NOTE);

                toWriteNoteFragment = ToWriteNoteFragment.newInstance(selectedNote);

                if(isLandscape){
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_to_write_note_container, toWriteNoteFragment)
                            .commit();
                } else {
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, toWriteNoteFragment)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        if (selectedNote != null){
            outState.putParcelable(ARG_NOTE, selectedNote);
        }
        super.onSaveInstanceState(outState);
    }
}