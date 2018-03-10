package com.thonysupersonic.applicationwithpermission;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            String contenido = "Hola mundo!";
            OutputStream stream = openFileOutput("archivo.txt", Context.MODE_PRIVATE);
            stream.write(contenido.getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
