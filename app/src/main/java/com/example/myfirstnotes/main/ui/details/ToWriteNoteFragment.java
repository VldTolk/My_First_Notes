package com.example.myfirstnotes.main.ui.details;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstnotes.R;
import com.example.myfirstnotes.main.domain.Note;
import com.example.myfirstnotes.main.ui.MainActivity;
import com.example.myfirstnotes.main.ui.ToolbarNavDrawer;
import com.example.myfirstnotes.main.ui.list.NotesListFragment;

public class ToWriteNoteFragment extends Fragment {

    public static final String ARG_NOTE = "ARG_NOTE";

    private Note selectedNote;

    public ToWriteNoteFragment() {
        super(R.layout.fragment_to_write_note);
    }

    public static ToWriteNoteFragment newInstance(Note note) {

        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTE, note);

        ToWriteNoteFragment fragment = new ToWriteNoteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbar_to_write_note);

        if (getActivity() instanceof ToolbarNavDrawer){
            ToolbarNavDrawer drawer = (ToolbarNavDrawer) getActivity();
            drawer.setToolbar(toolbar);
        }

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_share) {
                    Toast.makeText(requireContext(), "share", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.action_attach) {
                    Toast.makeText(requireContext(), "attach", Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    return false;
                }
            }
        });

        TextView noteName = view.findViewById(R.id.to_write_note_name);
        TextView dateNote = view.findViewById(R.id.to_write_note_date);
        TextView categoryNote = view.findViewById(R.id.to_write_note_categories);
        TextView textNote = view.findViewById(R.id.to_write_note_text);

        if (getArguments() != null && getArguments().containsKey(ARG_NOTE)) {

            Note note = getArguments().getParcelable(ARG_NOTE);

            noteName.setText(note.getNameNote());
            dateNote.setText(note.getDateNote());
            categoryNote.setText(note.getCategoryNote());
            textNote.setText(note.getTextNote());
        }
    }
}