package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private List<Note> notes = new ArrayList<>();

    public ArrayList<Note> getNotes() {
        return new ArrayList<>(notes);
    }

    //добавлять в адаптер базу данных или работу с сетью не нужно

    //добавляем переменную интерфейсного типа для слушатель по элементу
    private OnNoteClickListener onNoteClickListener;

    //из активити устанавливаем слушатель клика в данный адаптер (добавляем сеттер)
    public void setOnNoteClickListener(OnNoteClickListener onNoteClickListener) {
        this.onNoteClickListener = onNoteClickListener;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        //этот метод сообщит адаптеру что данные изменились
        notifyDataSetChanged();
    }


    //onCreateViewHolder определяет каким образом создавать view из макета xml
    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Создаем view из макета xml
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.note_item,
                parent,
                false);
        return new NotesViewHolder(view);
    }

    //Отвечает за установку определенных значений в каждый созданный view элемент
    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.textViewNote.setText(note.getText());
        int colorResId;
        switch (note.getPriority()) {
            case 0:
                colorResId = android.R.color.holo_green_light;
                break;
            case 1:
                colorResId = android.R.color.holo_orange_light;
                break;
            default:
                colorResId = android.R.color.holo_red_light;
        }

        //Получение цвета из id
        int color = ContextCompat.getColor(holder.itemView.getContext(), colorResId);

        holder.textViewNote.setBackgroundColor(color);

        //устанавливаем слушатель клика и в методе onClick реагируем на клики
        holder.textViewNote.setOnClickListener(view -> {
            if (onNoteClickListener != null) {
                onNoteClickListener.onNoteClick(view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    // class NotesViewHolder хранит ссылки на все view элементы с которыми нужно будет работать
    static class NotesViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewNote;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNote = itemView.findViewById(R.id.textViewNote);
        }
    }

    //определяем интерфейс слушатель клика по элементу списка
    interface OnNoteClickListener {
        void onNoteClick(View view);
    }
}
