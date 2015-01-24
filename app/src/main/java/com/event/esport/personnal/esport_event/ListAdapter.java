package com.event.esport.personnal.esport_event;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * @Author François Hallereau
 * @Date 24/01/2015
 */

public class ListAdapter extends ArrayAdapter<Match> {

    public ListAdapter(Context context, int resource, ArrayList<Match> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {

            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.row, null);

        }

        Match match = getItem(position);

        if (match != null) {

            ImageView live = (ImageView) v.findViewById(R.id.live);
            TextView date = (TextView) v.findViewById(R.id.date);
            TextView event = (TextView) v.findViewById(R.id.event);
            TextView team1 = (TextView) v.findViewById(R.id.team1);
            TextView team2 = (TextView) v.findViewById(R.id.team2);
            TextView percent1 = (TextView) v.findViewById(R.id.percent1);
            TextView percent2 = (TextView) v.findViewById(R.id.percent2);

            if(match.isLive()){
                live.setImageResource(R.drawable.live);
                live.setVisibility(View.VISIBLE);
            }
            else{
                live.setVisibility(View.GONE);
            }

            date.setText(match.getDate());
            event.setText(match.getEvent());
            team1.setText(match.getTeam1());
            team2.setText(match.getTeam2());
            percent1.setText(match.getPercent1());
            percent2.setText(match.getPercent2());
        }

        return v;

    }
}