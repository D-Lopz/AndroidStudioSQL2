/*
package com.example.appgestionnotas.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appgestionnotas.R;
import com.example.appgestionnotas.controller.NoteController;
import com.example.appgestionnotas.databinding.ActivityItemStudentBinding;
import com.example.appgestionnotas.model.Nota;
import com.google.android.material.tabs.TabLayout;

import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.appgestionnotas.controller.StudentController;
import com.example.appgestionnotas.databinding.ActivityMainBinding;
import com.example.appgestionnotas.model.Estudiante;

import java.util.ArrayList;
import java.util.List;


public class item_student extends AppCompatActivity {
    private StudentAdapter adapter;
    private List<Estudiante> listaEstudiantes;
    ActivityItemStudentBinding binding;
    NoteController notaController = new NoteController(this);
    StudentController estudianteController = new StudentController(this);

    List<Nota> notas = new ArrayList<>();
    NoteListaAdapter notasAdapter = new NoteListaAdapter(this, notas);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_estudiantes);
        TabLayout tabLayout = findViewById(R.id.tableNotes);
        TextView textNotas = findViewById(R.id.textNotas);
        TextView textPromedio = findViewById(R.id.textPromedio);
        binding = ActivityItemStudentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        int studentId = getIntent().getIntExtra("student_id", -1);
        NoteController notaController = new NoteController(this);

        List<Nota> notas = notaController.obtenerNotasPorEstudiante(studentId);
        Log.d("NotasDebug", "Notas obtenidas para el estudiante " + studentId + ": " + notas);

        textNotas.setText("Notas: " + notas.toString());
        double promedio = notas.stream().mapToDouble(Nota::getValue).average().orElse(0.0);
        textPromedio.setText("Promedio: " + String.format("%.2f", promedio));

        // Listener para cambiar entre pestañas
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    // Mostrar notas
                    textNotas.setVisibility(View.VISIBLE);
                    textPromedio.setVisibility(View.GONE);
                } else if (tab.getPosition() == 1) {
                    // Mostrar promedio

                    //Accion ver promedio
                    binding.verPromedioBtn.setOnClickListener(v -> {
                        String codigoEstudiante = binding.codigoEstudiante.getText().toString().trim();

                        if (codigoEstudiante.isEmpty()) {
                            //Toast.makeText(this, "Por favor ingresa un código", Toast.LENGTH_SHORT).show();
                            binding.codigoEstudiante.setError("Este campo no puede estar vacio!");
                            return;
                        }

                    }
            }*/
/*
            StudentAdapter adapter = new StudentAdapter(listaEstudiantes);
            recyclerView.setAdapter(adapter);*//*


            binding.studentList.setLayoutManager(new LinearLayoutManager(this));
            adapter = new StudentAdapter(listaEstudiantes);
            binding.studentList.setAdapter(adapter);

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }
}*/
