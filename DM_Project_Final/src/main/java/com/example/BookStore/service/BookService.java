package com.example.BookStore.service;

import com.example.BookStore.bean.Book;
import com.example.BookStore.dao.BookDAO;
import com.example.BookStore.dao.BookDAOimpl;
import java.io.InputStream;
import java.util.ArrayList;

public class BookService {

    BookDAO bookdao = new BookDAOimpl();

    public ArrayList<String[]> fetchJobs(String skill, String location, Integer experience) {
        System.out.println("In Service------------");
        return bookdao.fetchJobs(skill, location, experience);
    }
    public ArrayList<String[]> fetchObjectProperties(String className) {
        System.out.println("In Service------------");
        return bookdao.fetchObjectProperties(className);
    }

    public ArrayList<String[]> fetchBaseObjectProperties(String className) {
        System.out.println("In Service------------");
        return bookdao.fetchBaseObjectProperties(className);
    }
    public void loadbasemodel() {
//        System.out.println("In Service------------");
        bookdao.loadbasemodel();
    }

    public String authenticateUser(String username, String password) {
        return bookdao.authenticateUser(username, password);
    }
    public ArrayList<String> getClasses(InputStream in){
        return bookdao.getClasses(in);
    }

    public ArrayList<String[]> mapping(String bookname, String author,String edition,String bookid,String className) {
        System.out.println("In Service------------");
        return bookdao.mapping(bookname,author,edition,bookid,className);
    }
}
