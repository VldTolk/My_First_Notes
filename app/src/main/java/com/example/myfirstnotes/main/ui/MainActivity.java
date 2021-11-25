package com.example.myfirstnotes.main.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.os.Bundle;

import com.example.myfirstnotes.R;
import com.example.myfirstnotes.main.domain.Note;
import com.example.myfirstnotes.main.ui.details.ToWriteNoteFragment;
import com.example.myfirstnotes.main.ui.list.NotesListFragment;

public class MainActivity extends AppCompatActivity {

    private static final String ARG_NOTE = "ARG_NOTE";
    public static final String ARG_NOTE_WRITTEN = "ARG_NOTE_WRITTEN";
    public static final String ARG_TO_WRITE_NOTE = "ARG_TO_WRITE_NOTE";

    private Note selectedNote;
    private ToWriteNoteFragment toWriteNoteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();


        fragmentManager.setFragmentResultListener(NotesListFragment.KEY_NOTES_LIST_ACTIVITY, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                selectedNote = result.getParcelable(NotesListFragment.ARG_NOTE);

                toWriteNoteFragment = ToWriteNoteFragment.newInstance(selectedNote);

                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, toWriteNoteFragment)
                        .addToBackStack(null)
                        .commit();

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