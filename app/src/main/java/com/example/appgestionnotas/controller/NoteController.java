package com.example.appgestionnotas.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.appgestionnotas.model.DatabaseHelper;
import com.example.appgestionnotas.model.Nota;
import java.util.ArrayList;
import java.util.List;

public class NoteController {
    private DatabaseHelper dbHelper;

    public NoteController(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void agregarNota(int estudianteId, double valor) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("student_id", estudianteId);
        values.put("value", valor);
        db.insert("notas", null, values);
        db.close();
    }

    public List<Nota> obtenerNotasPorEstudiante(int estudianteId) {
        List<Nota> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM notas WHERE student_id = ?", new String[]{String.valueOf(estudianteId)});

        if (cursor.moveToFirst()) {
            do {
                lista.add(new Nota(cursor.getInt(0), cursor.getInt(1), cursor.getDouble(2)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }

        public double calcularPromedio(List<Nota> notas) {
            if(notas.isEmpty()){
                return 0;
            }
            //itera sobre cada elemento de la lista para obtener su valor y sumarlo para obtener promedio
            return (notas.stream()
                    .mapToDouble(Nota::getValue)
                    .sum())/notas.size();
        }

        public void eliminarNota(
                int id
        ) {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            db.delete("notas", "id = ?",new String[]{String.valueOf(id)});
            db.close();
        }

        public void editarNota(
                int notaId,
                double nuevoValor
        ) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put("nota", nuevoValor);
            db.update("notas", valores, "id = ?", new String[]{String.valueOf(notaId)});
            db.close();
        }
    }
