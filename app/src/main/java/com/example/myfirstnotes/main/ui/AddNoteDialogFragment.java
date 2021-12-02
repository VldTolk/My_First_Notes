package com.example.myfirstnotes.main.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.myfirstnotes.R;
import com.example.myfirstnotes.main.ui.details.ToWriteNoteFragment;

public class AddNoteDialogFragment extends DialogFragment {

    public static final String TAG = "AddNoteDialogFragment";

    public static AddNoteDialogFragment newInstance() {
        return new AddNoteDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        View customView = LayoutInflater.from(requireContext()).inflate(R.layout.add_note_dialog_view, null);

        EditText dialogNoteName = customView.findViewById(R.id.dialog_note_name);

        return new AlertDialog.Builder(requireContext())
                .setTitle(R.string.add_dialog_title)
                .setView(customView)
                .setPositiveButton(R.string.add_positive_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog1, int which) {
                        getParentFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, new ToWriteNoteFragment())
                                .addToBackStack(null)
                                .commit();
                    }
                })
                .create();
    }
}
