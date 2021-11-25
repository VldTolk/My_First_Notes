package com.example.myfirstnotes.main.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.StringRes;

public class Note implements Parcelable {

    @StringRes
    private final int nameNote;

    @StringRes
    private final int textNote;

    @StringRes
    private final int categoryNote;

    @StringRes
    private final int dateNote;

    public Note(int nameNote, int textNote, int categoryNote, int dateNote) {
        this.nameNote = nameNote;
        this.textNote = textNote;
        this.categoryNote = categoryNote;
        this.dateNote = dateNote;
    }

    protected Note(Parcel in) {
        nameNote = in.readInt();
        textNote = in.readInt();
        categoryNote = in.readInt();
        dateNote = in.readInt();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public int getNameNote() {
        return nameNote;
    }

    public int getTextNote() {
        return textNote;
    }

    public int getCategoryNote() {
        return categoryNote;
    }

    public int getDateNote() {
        return dateNote;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(nameNote);
        dest.writeInt(textNote);
        dest.writeInt(categoryNote);
        dest.writeInt(dateNote);
    }
}