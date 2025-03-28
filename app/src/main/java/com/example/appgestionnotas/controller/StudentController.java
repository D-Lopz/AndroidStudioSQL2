package com.example.appgestionnotas.controller;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.appgestionnotas.model.DatabaseHelper;
import com.example.appgestionnotas.model.Estudiante;
import java.util.ArrayList;
import java.util.List;

public class StudentController {
    private DatabaseHelper dbHelper;
    public StudentController(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void agregarEstudiante(String nombre, String codigo) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("codigo", codigo);
        db.insert("estudiantes", null, values);
        db.close();
    }

    public List<Estudiante> obtenerEstudiantes() {
        List<Estudiante> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM studets", null);

        if (cursor.moveToFirst()) {
            do {
                lista.add(new Estudiante(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }
}


