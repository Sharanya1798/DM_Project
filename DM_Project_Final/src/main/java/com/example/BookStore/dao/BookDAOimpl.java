package com.example.BookStore.dao;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class BookDAOimpl implements BookDAO {
    static  ArrayList<String[]> books = new ArrayList<String[]>();
    static int x = 1 ;

    @Override
    public ArrayList<String[]> fetchBaseObjectProperties(String className) {

        System.out.println("----------in impl  baseproperties ");
        System.out.println(className);


        ArrayList<String[]> properties = new ArrayList<String[]>();

        OntModel model11= ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM);
        String inputFileName1="/home/sharanya/Desktop/library_harsha_rajiv.owl";

        InputStream in = FileManager.get().open( inputFileName1 );
        if (in == null) {
            throw new IllegalArgumentException(
                    "File: " + inputFileName1 + " not found");
        }
        model11.read(in, null);

        System.out.println("\n---------properties in BaseModel-----------------\n ");

        ExtendedIterator classes = model11.listClasses();

        String namespace  = "http://www.semanticweb.org/harsha/ontologies/2021/1/library_harsha_rajiv.owl#";
        String SEARCH_CLASS = namespace + className;

        while (classes.hasNext())
        {
            OntClass thisClass = (OntClass) classes.next();
            String temp = thisClass.toString();
            if((SEARCH_CLASS.equals(temp)))
            {
                ExtendedIterator instances = thisClass.listInstances();

                if(instances.hasNext())
                {
                    Individual thisInstance = (Individual) instances.next();
                    StmtIterator it = thisInstance.listProperties();
                    while(it.hasNext())
                    {
                        Statement r = it.next();
                        if (r.getObject().isLiteral())
                        {
                            String property = r.getPredicate().getLocalName();
                            System.out.println(property);
                            String[] xyz = {property};
                            properties.add(xyz);

                        }
                    }
                }


            }
        }

        return properties;
    }


    @Override
    public ArrayList<String[]> fetchObjectProperties(String className) {

        System.out.println("----------in impl ");
        System.out.println(className);

        ArrayList<String[]> properties = new ArrayList<String[]>();
        OntModel model2 = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM);
        String inputFileName2  ="/home/sharanya/Desktop/library_sharanya_shubham.owl";
        InputStream in1 = FileManager.get().open( inputFileName2 );
        if (in1 == null) {
            throw new IllegalArgumentException(
                    "File: " + inputFileName2 + " not found");
        }
        model2.read(in1,null);

        ExtendedIterator classes2 = model2.listClasses();

        while (classes2.hasNext()) {
            OntClass thisClass = (OntClass) classes2.next();
            String name = thisClass.toString().split("#")[1];

            if(className.equals(name))
            {
                ExtendedIterator instances = thisClass.listInstances();
                Individual thisInstance = (Individual) instances.next();
                StmtIterator it = thisInstance.listProperties();
                while(it.hasNext()) {
                    Statement r = it.next();
                    if(r.getObject().isLiteral()) {
                        String property = r.getPredicate().getLocalName();
                        String[] temp1 = {property};
                        properties.add(temp1);
                    }

                }

            }


        }

        System.out.println("Properties ---------------------");

        for (String i[] : properties) {
            System.out.println(Arrays.toString(i));
        }



        return properties;
    }

    @Override
    public void loadbasemodel()
    {

        if(x==1) {
            String inputFileName1 = "/home/sharanya/Desktop/library_harsha_rajiv.owl";
            OntModel model1 = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
            InputStream in = FileManager.get().open(inputFileName1);
            if (in == null) {
                throw new IllegalArgumentException(
                        "File: " + inputFileName1 + " not found");
            }
            model1.read(in, null);
            ExtendedIterator classes3 = model1.listClasses();

            while (classes3.hasNext()) {
                OntClass thisClass = (OntClass) classes3.next();

//		      System.out.println("Found class: " + thisClass.toString());

                ExtendedIterator instances = thisClass.listInstances();

                while (instances.hasNext()) {
                    Individual thisInstance = (Individual) instances.next();
                    StmtIterator it = thisInstance.listProperties();
                    String bookname = "";
                    String Author = "";
                    String edition = "";
                    String bookid = "";
                    while (it.hasNext()) {
                        Statement r = it.next();
                        if (r.getObject().isLiteral()) {

                            String Property = r.getPredicate().getLocalName().toString();

                            if (Property.equals("bookname"))
                                bookname = r.getLiteral().getLexicalForm().toString();
                            if (Property.equals("author"))
                                Author = r.getLiteral().getLexicalForm().toString();
                            if (Property.equals("Edition"))
                                edition = r.getLiteral().getLexicalForm().toString();
                            if (Property.equals("bookId"))
                                bookid = r.getLiteral().getLexicalForm().toString();
                        }

                    }
                    String[] temp = {bookname, Author, edition, bookid};
                    books.add(temp);
                    x++;


                }
            }
        }
        else
        {

        }



    }



    @Override
    public ArrayList<String[]> fetchJobs(String BOOKNAME, String AUTHOR, Integer EDITION) {

        if(BOOKNAME.equals("*") && AUTHOR.equals("*"))
        {
            return books;
        }
        System.out.println("---- books ------------------");

        for (String i[] : books) {
            System.out.println(Arrays.toString(i));

        }

        int list_size = books.size();
        ArrayList<String[]> searchResult = new ArrayList<String[]>();
        String edition = EDITION.toString();
        System.out.println("edition :" + edition );
        for(String[] book : books)
        {
//            System.out.println(book[0]+book[1]+book[2]+book[3]);

            String[] dummy = {book[0],book[1],book[2], book[3]};
            if( dummy[0].equals(BOOKNAME) )
            {
               // if(dummy[1].equals(AUTHOR))
                //{
                    System.out.println("matched");
                    searchResult.add(dummy);

              //  }
            }

        }

        System.out.println("---- searchreasult ------------------");

        for (String i[] : searchResult) {
            System.out.println(Arrays.toString(i));
        }
        return searchResult;
    }

    @Override
    public String authenticateUser(String username, String password) {

        final String adminUsername = "admin@gmail.com";
        final String adminPassword = "1234";
        final String userUsername = "user@gmail.com";
        final String userPassword = "1234";

        if (username.equals(adminUsername) && password.equals(adminPassword))
            return "admin";
        else if (username.equals(userUsername) && password.equals(userPassword))
            return "user";
        else
            return "";
    }

    @Override
    public ArrayList<String> getClasses(InputStream in){

        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        model.read(in, "RDF/XML");
        Iterator<OntClass> it = model.listClasses();
        ArrayList<String> classes=new ArrayList<String>();
        while (it.hasNext()) {
            String c = it.next().toString();
            if(c.contains("#"))
            {
                String[] classi = c.split("#");
                classes.add(classi[1]);
            }
        }
        return classes;
    }


    @Override
    public ArrayList<String[]> mapping(String BOOKNAME, String AUTHOR, String EDITION,String BOOKID,String className) {

        System.out.println("------------into mapping -----------");
        System.out.println(BOOKID+BOOKNAME+EDITION+AUTHOR+className);
        String inputFileName1="/home/sharanya/Desktop/library_harsha_rajiv.owl";
        OntModel model1= ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM);
        InputStream in = FileManager.get().open( inputFileName1 );
        if (in == null) {
            throw new IllegalArgumentException(
                    "File: " + inputFileName1 + " not found");
        }
        model1.read(in, null);

        OntModel model2 = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM);
        String inputFileName2  ="/home/sharanya/Desktop/library_sharanya_shubham.owl";
        InputStream in1 = FileManager.get().open( inputFileName2 );
        if (in1 == null) {
            throw new IllegalArgumentException(
                    "File: " + inputFileName2 + " not found");
        }
        model2.read(in1,null);
        model1.addSubModel(model2);
//      System.out.println("---------------------Individuals in Merged Model-----------------\n ");

        ExtendedIterator classes3 = model1.listClasses();
        while (classes3.hasNext()) {
            OntClass thisClass = (OntClass) classes3.next();
            if(true) {

                ExtendedIterator instances = thisClass.listInstances();
                while (instances.hasNext()) {
                    Individual thisInstance = (Individual) instances.next();
                    StmtIterator it = thisInstance.listProperties();
                    String bookname = "";
                    String Author = "";
                    String edition = "";
                    String bookid = "";
                    while (it.hasNext()) {
                        Statement r = it.next();
                        if (r.getObject().isLiteral()) {
                            String Property = r.getPredicate().getLocalName().toString();
                            if (Property.equals(BOOKNAME))
                                bookname = r.getLiteral().getLexicalForm().toString();
                            if (Property.equals(AUTHOR))
                                Author = r.getLiteral().getLexicalForm().toString();
                            if (Property.equals(EDITION))
                                edition = r.getLiteral().getLexicalForm().toString();
                            if (Property.equals(BOOKID))
                                bookid = r.getLiteral().getLexicalForm().toString();
                        }
                    }
                    if(bookname.equals(""))
                    {

                    }
                    else {
                        String temp[] = {bookname, Author, edition, bookid};
                        books.add(temp);
                    }
                }
            }
        }

        System.out.println("----------still -in mapping ------------");
        for (String i[] : books) {
            System.out.println(Arrays.toString(i));
        }
        System.out.println("==========out mapping  ==========");

        return books;
    }
}
