package com.example.notes;

import static android.app.PendingIntent.getActivity;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
    TextView notesTextView;
    TextView titleTextView;
    CardView cardView;
    RecyclerView recyclerView;

    public RecyclerViewViewHolder(@NonNull View itemView) {
        super(itemView);
        notesTextView = itemView.findViewById(R.id.notesItemView);
        titleTextView = itemView.findViewById(R.id.titleItemView);
        cardView = itemView.findViewById(R.id.cardView);
        recyclerView = itemView.findViewById(R.id.recycler_view);

    }

}
