package com.example.orangescore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MatchRecyclerViewAdapter extends RecyclerView.Adapter<MatchRecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<Match> list_of_matches;

    public MatchRecyclerViewAdapter(Context context, ArrayList<Match> list_of_matches) {
        this.context = context;
        this.list_of_matches = list_of_matches;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recyclerview_row_matches, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Match match = list_of_matches.get(position);
        holder.team_home_rec.setText(match.homeTeam);
        holder.team_away_rec.setText(match.awayTeam);
        holder.match_date_rec.setText(match.matchDate);

    }

    @Override
    public int getItemCount() {
        return list_of_matches.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder {
        TextView team_home_rec, team_away_rec, match_date_rec;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            team_home_rec = itemView.findViewById(R.id.recycler_row_team_home);
            team_away_rec = itemView.findViewById(R.id.recycler_row_team_away);
            match_date_rec = itemView.findViewById(R.id.recycler_row_date);

        }
    }
}
