package com.dev.bins.note.model;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bin on 11/24/15.
 */
public class Category extends DataSupport{

    public static final int DEFAULT =1;
    public static final int CLIPBOARD =2;
    public static final int PRIVATE =3;


    private long id;
    private String categoty;

    private List<Note> notes = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoty() {
        return categoty;
    }

    public void setCategoty(String categoty) {
        this.categoty = categoty;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
