package com.example.myfirstnotes.main.ui.details;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstnotes.R;
import com.example.myfirstnotes.main.domain.Note;
import com.example.myfirstnotes.main.ui.AttachBottomSheetFragment;
import com.example.myfirstnotes.main.ui.MainActivity;
import com.example.myfirstnotes.main.ui.ToolbarNavDrawer;
import com.example.myfirstnotes.main.ui.list.NotesListFragment;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Objects;

public class ToWriteNoteFragment extends Fragment {

    public static final String ARG_NOTE = "ARG_NOTE";
    public static final String SHARE_CHANNEL = "SHARE_CHANNEL";

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

        NotificationChannelCompat notificationChannelCompat = new NotificationChannelCompat.Builder(SHARE_CHANNEL, NotificationManagerCompat.IMPORTANCE_DEFAULT)
                .setName("Share_channel")
                .setDescription("Description")
                .build();

        NotificationManagerCompat.from(requireContext()).createNotificationChannel(notificationChannelCompat);

        Toolbar toolbar = view.findViewById(R.id.toolbar_to_write_note);

        if (getActivity() instanceof ToolbarNavDrawer) {
            ToolbarNavDrawer drawer = (ToolbarNavDrawer) getActivity();
            drawer.setToolbar(toolbar);
        }

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_share) {
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), SHARE_CHANNEL);

                    builder.setContentTitle("Ты успешно поделился заметкой!")
                            .setContentText("Возьми с полки пирожок")
                            .setSmallIcon(R.drawable.ic_share);

                    NotificationManagerCompat.from(requireContext()).notify(1, builder.build());

                    return true;
                } else if (item.getItemId() == R.id.action_attach) {
                    AttachBottomSheetFragment fragment = AttachBottomSheetFragment.newInstance();
                    fragment.show(getParentFragmentManager(), AttachBottomSheetFragment.TAG);
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