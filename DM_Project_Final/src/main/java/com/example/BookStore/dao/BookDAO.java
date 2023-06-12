package com.example.BookStore.dao;

import java.io.InputStream;
import java.util.ArrayList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public interface BookDAO {
    ArrayList<String[]> fetchJobs(String skill, String location, Integer experience);

    ArrayList<String[]> mapping(String bookname, String author, String edition,String bookid,String className);

    ArrayList<String[]> fetchObjectProperties(String className);
    ArrayList<String[]> fetchBaseObjectProperties(String className);

    String authenticateUser(String username, String password);
    void loadbasemodel();
    ArrayList<String> getClasses(InputStream in);
}

