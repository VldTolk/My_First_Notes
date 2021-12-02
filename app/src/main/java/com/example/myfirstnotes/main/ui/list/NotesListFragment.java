package com.example.myfirstnotes.main.ui.list;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfirstnotes.R;
import com.example.myfirstnotes.main.domain.InMemoryNotesRepository;
import com.example.myfirstnotes.main.domain.Note;
import com.example.myfirstnotes.main.ui.AddNoteDialogFragment;
import com.example.myfirstnotes.main.ui.SettingsFragment;
import com.example.myfirstnotes.main.ui.ToolbarNavDrawer;

import java.util.List;

public class NotesListFragment extends Fragment implements NotesListView {

    public static final String KEY_NOTES_LIST_ACTIVITY = "KEY_NOTES_LIST_ACTIVITY";
    public final static String ARG_NOTE = "ARG_NOTE";

    private NotesListPresenter presenter;
    private NotesAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

/*    всё это оставляю просто чтоб не забывать, как это делается

        public static NotesListFragment newInstance(Note note) {

        NotesListFragment fragment = new NotesListFragment();
        Bundle args = new Bundle();

        args.putParcelable(ARG_NOTE, note);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new NotesListPresenter(this, new InMemoryNotesRepository());
        adapter = new NotesAdapter();

        adapter.setNoteClicked(new NotesAdapter.OnNoteClicked() {
            @Override
            public void onNoteClicked(Note note) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(ARG_NOTE, note);

                getParentFragmentManager()
                        .setFragmentResult(KEY_NOTES_LIST_ACTIVITY, bundle);
            }
        });
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

        RecyclerView notesListRoot = view.findViewById(R.id.notes_list_root);

        if (layoutManager == null) {
            layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
            notesListRoot.setLayoutManager(layoutManager);
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
                } else if (item.getItemId() == R.id.action_sort_list) {
                    layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
                    notesListRoot.setLayoutManager(layoutManager);
                    return true;
                } else if (item.getItemId() == R.id.action_sort_grid) {
                    layoutManager = new GridLayoutManager(requireContext(), 2);
                    notesListRoot.setLayoutManager(layoutManager);
                    return true;
                } else {
                    return false;
                }
            }
    });

//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(requireContext(), 2);

//        notesListRoot.setLayoutManager(layoutManager);

        notesListRoot.setAdapter(adapter);

        presenter.requestNotes();

    }



    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void showNotes(List<Note> notes) {

        adapter.setNotes(notes);
        adapter.notifyDataSetChanged();



/*         оставляю, чтоб был перед глазами этот пример, на будущее

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
        }*/
    }
}