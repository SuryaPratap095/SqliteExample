package com.example.surya.sqliteexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBaseHandlerClass dataBaseHandler=new DataBaseHandlerClass(this);
        Toast.makeText(this,"Below dbhandler object",Toast.LENGTH_LONG).show();
        Log.d("Data Insert","Inserting");

        File interneDB = getApplicationContext().getDatabasePath(DataBaseHandlerClass.DATABASE_NAME);
        Toast.makeText(this, "InternalDB"+interneDB.toString(), Toast.LENGTH_SHORT).show();
          dataBaseHandler.addContacts(new Contacts(1,"sdfs","23634563456"));
          dataBaseHandler.addContacts(new Contacts(2,"asdfsd","967834563456"));
//        dataBaseHandler.addContacts(new Contacts(3,"asdfas","345334563456"));
//        dataBaseHandler.addContacts(new Contacts(4,"Ganasdfas","09834563456"));

        Log.d("Reading all conctacts", "Reading");

        //dataBaseHandler.deleteContactTry();
        Contacts contacts=new Contacts(0,"dsfgsdf","sdgfdsgfds");
         ///contacts.setId(0);
        //dataBaseHandler.updateContact(contacts);
        dataBaseHandler.deleteContact(contacts);

        ArrayList<Contacts> contact=dataBaseHandler.getAllContacts();

        for(Contacts c:contact){
            String log = "Id: "+c.getId()+" ,Name: " + c.getName() + " ,Phone: " + c.getPhoneNumber();
            // Writing Contacts to log

            Toast.makeText(this,c.getId()+" "+c.getName()+" "+c.getPhoneNumber(),Toast.LENGTH_LONG).show();
            Log.d("Values",Integer.toString(c.getId()));
            Log.d("Name: ", log);
        }


    }
}
