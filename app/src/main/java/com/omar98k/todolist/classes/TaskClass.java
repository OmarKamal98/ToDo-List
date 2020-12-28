package com.omar98k.todolist.classes;

import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskClass {
    public String noteBookId="public";
    public String idOfNote;
    public String titleOfNote;
    public String contextOfNote;
    public long dateOfNote;
    public TaskClass(){}
    public TaskClass(String noteBookId,String idOfNote, String titleOfNote, String contextOfNote, long dateOfNote) {
        this.idOfNote = idOfNote;
        this.noteBookId=noteBookId;
        this.titleOfNote = titleOfNote;
        this.contextOfNote = contextOfNote;
        this.dateOfNote = dateOfNote;
    }
    public TaskClass(String noteBookId,String idOfNote) {
        this.idOfNote = idOfNote;
        this.noteBookId=noteBookId;

    }

    public static String generateNoteID(){
        return FirebaseDatabase.getInstance().getReference().child("user").child("note").push().getKey();

    }

    public static String longToDate(long val){
        Date date=new Date(val);
        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
        String dateText = df2.format(date);
        return dateText;
    }
    public String getidTask( ){
        return  idOfNote ;
    }
    // id getter and setter
}