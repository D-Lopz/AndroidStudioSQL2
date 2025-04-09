package com.example.appgestionnotas.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appgestionnotas.R;
import com.example.appgestionnotas.model.Nota;
import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {

    private List<Nota> notas;
    public interface OnNotaClickListener {
    void onNotaClick(Nota nota);
    }

    private OnNotaClickListener listener;

    public void setOnNotaClickListener(OnNotaClickListener listener) {
        this.listener = listener;
    }


    public NoteListAdapter(DetallesEstudiantes detallesEstudiantes, List<Nota> notas) {
            this.notas = notas;
        }

        @NonNull
        @Override
        public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_nota, parent, false);
            return new NoteViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
            Nota nota = notas.get(position);
            holder.numeroNota.setText("Nota " + (position + 1));
            holder.etiquetaNota.setText("Nota:");
            holder.valorNota.setText(String.valueOf(nota.getValue()));
            holder.itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onNotaClick(notas.get(position));
                }
            });

        }

        @Override
        public int getItemCount() {
            return notas.size();
        }

        public static class NoteViewHolder extends RecyclerView.ViewHolder {
            TextView numeroNota;
            TextView etiquetaNota;
            TextView valorNota;

            public NoteViewHolder(@NonNull View itemView) {
                super(itemView);
                numeroNota = itemView.findViewById(R.id.numeroNota);
                etiquetaNota = itemView.findViewById(R.id.etiquetaNota);
                valorNota = itemView.findViewById(R.id.valorNota);
            }
        }
    }

