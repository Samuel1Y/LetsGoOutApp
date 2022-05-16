package com.example.letsgooutapp.Model;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

import com.example.letsgooutapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class InterestAdapter extends BaseAdapter {

    Context context;
    ArrayList<Interest> interests;
    ArrayList<Boolean> interestsClicked = new ArrayList<>();

    LayoutInflater inflater;

    public InterestAdapter(Context context, ArrayList<Interest> interest, ArrayList<Integer> myInterests) {
        this.context = context;
        this.interests = interest;
        for (int i = 0; i < interest.size(); i++){
            this.interestsClicked.add(Boolean.FALSE);
        }
        for(int myInt =0; myInt<myInterests.size();myInt++){
            this.interestsClicked.set(myInterests.get(myInt),Boolean.TRUE);
        }
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return interests.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public ArrayList<Interest> getChosenInterests(){
        ArrayList<Interest> chosenInterests = new ArrayList<>();
        for(int i = 0; i<interestsClicked.size();i++){
            if(interestsClicked.get(i))
            {
                chosenInterests.add(interests.get(i));
            }
        }
        return chosenInterests;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null){

            convertView = inflater.inflate(R.layout.grid_item,parent,false);

        }

        Button buttonInterest = convertView.findViewById(R.id.interest_name);

        buttonInterest.setText(interests.get(position).getInterest());

        if (interestsClicked.get(position)) {
            buttonInterest.setBackgroundColor(Color.CYAN);
        } else {
            buttonInterest.setBackgroundColor(Color.DKGRAY);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context,"You Clicked "+interests.get(position).getInterest(),Toast.LENGTH_LONG).show();
                if(countInterests()<5 || (countInterests()==5 && (interestsClicked.get(position)))) {
                    interestsClicked.set(position, !interestsClicked.get(position));
                    if (interestsClicked.get(position)) {
                        buttonInterest.setBackgroundColor(Color.CYAN);
                    } else {
                        buttonInterest.setBackgroundColor(Color.DKGRAY);
                    }
                }
                else{
                    Toast.makeText(context,"Can't choose more than 5 interests",Toast.LENGTH_LONG).show();
                }
            }
        });

        return convertView;
    }

    private int countInterests() {
        {
            int num = 0;
            for(Boolean interestBool: interestsClicked){
                if(interestBool){num++;}
            }
            return num;
        }
    }

    public void setArrayClickedToTrue(int id)
    {
        this.interestsClicked.set(id,Boolean.TRUE);
    }

}
