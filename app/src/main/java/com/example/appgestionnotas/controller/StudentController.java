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
        values.put("name", nombre);
        values.put("id_code", codigo);
        db.insert("students", null, values);
        db.close();
    }

    public List<Estudiante> obtenerEstudiantes() {
        List<Estudiante> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM students", null);

        if (cursor.moveToFirst()) {
            do {
                lista.add(new Estudiante(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lista;
    }

    public Estudiante obtenerEstudiantePorCodigo(String id_code) {
        Estudiante estudiante = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM students WHERE id_code = ?", new String[]{id_code});

        if (cursor.moveToFirst()) {
            estudiante = new Estudiante(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
            );
        }

        cursor.close();
        db.close();
        return estudiante;
    }

    public void eliminarEstudiante(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("students", "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void editarEstudiante(String nuevoNombre, String nuevoCodigo, int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("name", nuevoNombre);
        valores.put("id_code", nuevoCodigo);
        db.update("students", valores, "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}