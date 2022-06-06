package com.example.orangescore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class TeamHomeRecyclerViewAdapter extends RecyclerView.Adapter<TeamHomeRecyclerViewAdapter.MyHomeTeamViewHolder> {
    Context homeTeam_context;
    ArrayList<Player> list_of_homeTeam_players;

    public TeamHomeRecyclerViewAdapter(Context homeTeam_context, ArrayList<Player> list_of_homeTeam_players) {
        this.homeTeam_context = homeTeam_context;
        this.list_of_homeTeam_players = list_of_homeTeam_players;
    }

    @NonNull
    @Override
    public MyHomeTeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(homeTeam_context).inflate(R.layout.recyclerview_row_players, parent, false);
        return new MyHomeTeamViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamHomeRecyclerViewAdapter.MyHomeTeamViewHolder holder, int position) {
        Player player = list_of_homeTeam_players.get(position);

        holder.nameOfPlayer.setText(player.player_name);
        holder.positionOfPlayer.setText(player.player_position);
    }

    @Override
    public int getItemCount() {
        return list_of_homeTeam_players.size();
    }

    public static class MyHomeTeamViewHolder extends RecyclerView.ViewHolder {
        TextView nameOfPlayer, positionOfPlayer;

        public MyHomeTeamViewHolder(@NonNull View itemView) {
            super(itemView);

            nameOfPlayer = itemView.findViewById(R.id.player_name);
            positionOfPlayer = itemView.findViewById(R.id.player_position);
        }
    }
}
