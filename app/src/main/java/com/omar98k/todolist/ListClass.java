package com.omar98k.todolist;

import java.util.ArrayList;

public class ListClass {
    public String id;
    public String name;
    public ArrayList<TaskClass> mNotes=new ArrayList<>();
    public ListClass(){

    }
    public ListClass(String id,String name){
        this.id = id;
        this.name = name;
    }
}
