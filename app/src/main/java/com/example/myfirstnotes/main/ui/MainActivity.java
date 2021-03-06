package com.example.myfirstnotes.main.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.myfirstnotes.R;
import com.example.myfirstnotes.main.domain.Note;
import com.example.myfirstnotes.main.ui.details.ToWriteNoteFragment;
import com.example.myfirstnotes.main.ui.list.NotesListFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements ToolbarNavDrawer {

    private static final String ARG_NOTE = "ARG_NOTE";

    private Note selectedNote;
    private ToWriteNoteFragment toWriteNoteFragment;
    private DrawerLayout drawerLayout;

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

        NavigationView navigationView = findViewById(R.id.navigation_view);

        drawerLayout = findViewById(R.id.drawer_layout);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.all_notes) {
                    getSupportFragmentManager().popBackStack();
                    getSupportFragmentManager().
                            beginTransaction().
                            replace(R.id.fragment_container, new NotesListFragment()).
                            commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }
                if (item.getItemId() == R.id.action_settings_nav) {
                    getSupportFragmentManager().popBackStack();
                    getSupportFragmentManager().
                            beginTransaction().
                            replace(R.id.fragment_container, new SettingsFragment()).
                            addToBackStack(null).
                            commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    @Override
    public void setToolbar(Toolbar toolbar){
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_oren, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (findViewById(R.id.container_list) != null) {
            Snackbar.make(findViewById(R.id.container_list), R.string.are_you_sure, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.yes, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MainActivity.super.onBackPressed();
                        }
                    })
/*                    .setAction(R.string.no, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    })*/
                    .show();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this, R.string.app_close, Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        if (selectedNote != null){
            outState.putParcelable(ARG_NOTE, selectedNote);
        }
        super.onSaveInstanceState(outState);
    }
}