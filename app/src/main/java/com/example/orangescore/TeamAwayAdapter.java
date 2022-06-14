package com.example.orangescore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/** Custom adapter made to populate recycler view that shows away players */
public class TeamAwayAdapter extends RecyclerView.Adapter<TeamAwayAdapter.MyAwayTeamViewHolder> {
    Context awayTeam_context;
    ArrayList<Player> list_of_awayTeam_players;

    public TeamAwayAdapter(Context awayTeam_context, ArrayList<Player> list_of_awayTeam_players) {
        this.awayTeam_context = awayTeam_context;
        this.list_of_awayTeam_players = list_of_awayTeam_players;
    }

    public void setAwayPlayers(ArrayList<Player> awayPlayers) {
        this.list_of_awayTeam_players = new ArrayList<>();
        this.list_of_awayTeam_players = list_of_awayTeam_players;
        notifyDataSetChanged();
    }

    class MyAwayTeamViewHolder extends  RecyclerView.ViewHolder{
        private TextView nameOfPlayer, positionOfPlayer;

        public MyAwayTeamViewHolder(@NonNull View itemView) {
            super(itemView);
            nameOfPlayer = itemView.findViewById(R.id.player_name);
            positionOfPlayer = itemView.findViewById(R.id.player_position);
        }

        void  bind(final Player player) {
            nameOfPlayer.setText(player.getPlayer_name());
            positionOfPlayer.setText(player.getPlayer_position());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    player.setChecked(!player.isChecked());
                }
            });
        }
    }

    public ArrayList<Player> getAll() {
        return list_of_awayTeam_players;
    }

    public ArrayList<Player> getSelectedAwayPlayers() {
        ArrayList<Player> selectedAwayPlayers = new ArrayList<>();
        for (int i =0; i< list_of_awayTeam_players.size(); i++){
            if (list_of_awayTeam_players.get(i).isChecked()) {
                selectedAwayPlayers.add(list_of_awayTeam_players.get(i));
            }
        }
        return selectedAwayPlayers;
    }

    @NonNull
    @Override
    public TeamAwayAdapter.MyAwayTeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(awayTeam_context).inflate(R.layout.recyclerview_row_players, parent, false);
        return new TeamAwayAdapter.MyAwayTeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamAwayAdapter.MyAwayTeamViewHolder holder, int position) {
        holder.bind(list_of_awayTeam_players.get(position));
    }

    @Override
    public int getItemCount() {
        return list_of_awayTeam_players.size();
    }
}
