package com.example.letsgooutapp.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letsgooutapp.R;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private ArrayList<Event> events;
    private View.OnClickListener onClickListener;

    public EventAdapter(ArrayList<Event> events)
    {
        this.events = events;
    }

    public void setOnClickListener(View.OnClickListener listener)
    {
        this.onClickListener = listener;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.event_list_item, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.title.setText(events.get(position).getTitle());
        viewHolder.location.setText(events.get(position).getLocation());
        viewHolder.creator.setText(events.get(position).getCreator());
    }

    public int getItemCount() {
        return events.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView location;
        private final TextView creator;
        private final Button showOnMapButton;

        ViewHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.title);
            location = itemView.findViewById(R.id.location);
            creator = itemView.findViewById(R.id.creator);
            showOnMapButton = itemView.findViewById(R.id.showOnMapButton);

           /*
            itemView.setOnClickListener(view -> {
              onClickListener.onClick(events.get(getBindingAdapterPosition()));
            });
           */
        }
    }

    public interface OnClickListener{
        void onClick(Event event);
    }
}
