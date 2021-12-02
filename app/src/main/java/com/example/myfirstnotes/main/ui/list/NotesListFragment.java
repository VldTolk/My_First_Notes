package com.example.myfirstnotes.main.ui.list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstnotes.R;
import com.example.myfirstnotes.main.domain.InMemoryNotesRepository;
import com.example.myfirstnotes.main.domain.Note;
import com.example.myfirstnotes.main.ui.AddNoteDialogFragment;
import com.example.myfirstnotes.main.ui.SettingsFragment;
import com.example.myfirstnotes.main.ui.ToolbarNavDrawer;
import com.example.myfirstnotes.main.ui.details.ToWriteNoteFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class NotesListFragment extends Fragment implements NotesListView {

    public static final String KEY_NOTES_LIST_ACTIVITY = "KEY_NOTES_LIST_ACTIVITY";
    public final static String ARG_NOTE = "ARG_NOTE";

    private LinearLayout notesListRoot;
    private NotesListPresenter presenter;

    public static NotesListFragment newInstance(Note note) {

        NotesListFragment fragment = new NotesListFragment();
        Bundle args = new Bundle();

        args.putParcelable(ARG_NOTE, note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new NotesListPresenter(this, new InMemoryNotesRepository());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbar_list);

        if (getActivity() instanceof ToolbarNavDrawer){
            ToolbarNavDrawer drawer = (ToolbarNavDrawer) getActivity();
            drawer.setToolbar(toolbar);
        }

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_add) {
                    AddNoteDialogFragment.newInstance().show(getParentFragmentManager(), AddNoteDialogFragment.TAG);
                    return true;
                } else if (item.getItemId() == R.id.action_settings) {
                    getParentFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new SettingsFragment())
                            .addToBackStack(null)
                            .commit();
                    return true;
                } else {
                    return false;
                }
            }
        });

        notesListRoot = view.findViewById(R.id.notes_root);

        presenter.requestNotes();

    }



    @Override
    public void showNotes(List<Note> notes) {

        for (Note note : notes) {
            View view = LayoutInflater.from(requireContext()).inflate(R.layout.item_note, notesListRoot, false);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle bundle = new Bundle();
                    bundle.putParcelable(ARG_NOTE, note);

                    getParentFragmentManager()
                            .setFragmentResult(KEY_NOTES_LIST_ACTIVITY, bundle);
                }
            });
            TextView noteName = view.findViewById(R.id.note_name);
            noteName.setText(note.getNameNote());

            TextView noteCategory = view.findViewById(R.id.note_category);
            noteCategory.setText(note.getCategoryNote());

            TextView noteDate = view.findViewById(R.id.note_date);
            noteDate.setText(note.getDateNote());

            notesListRoot.addView(view);
        }
    }
}