package com.example.letsgooutapp.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letsgooutapp.R;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private ArrayList<Event> events;
    private OnClickListener listener;

    public EventAdapter(ArrayList<Event> events)
    {
        this.events = events;
    }

    public void setOnClickListener(OnClickListener listener)
    {
        this.listener = listener;
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
            showOnMapButton = itemView.findViewById(R.id.deleteButton);

            itemView.setOnClickListener(view -> {
              listener.onClick(events.get(getBindingAdapterPosition()));

            });
            showOnMapButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(view.getContext(),events.get(getBindingAdapterPosition()).getLocation(), Toast.LENGTH_LONG);
                }
            });
        }
    }

    public interface OnClickListener{
        void onClick(Event event);
    }
}
