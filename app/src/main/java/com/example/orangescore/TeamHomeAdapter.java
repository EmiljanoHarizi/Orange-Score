package com.example.orangescore;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/** Custom adapter made to populate recycler view that shows home players */
public class TeamHomeAdapter extends RecyclerView.Adapter<TeamHomeAdapter.MyHomeTeamViewHolder> {
    Context homeTeam_context;
    ArrayList<Player> list_of_homeTeam_players;

    public TeamHomeAdapter(Context homeTeam_context, ArrayList<Player> list_of_homeTeam_players) {
        this.homeTeam_context = homeTeam_context;
        this.list_of_homeTeam_players = list_of_homeTeam_players;
    }

    public void setHomePlayers(ArrayList<Player> homePlayers) {
        this.list_of_homeTeam_players = new ArrayList<>();
        this.list_of_homeTeam_players = list_of_homeTeam_players;
        notifyDataSetChanged();
    }

    class MyHomeTeamViewHolder extends  RecyclerView.ViewHolder{
        private TextView nameOfPlayer, positionOfPlayer;

        public MyHomeTeamViewHolder(@NonNull View itemView) {
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
        return list_of_homeTeam_players;
    }

    public ArrayList<Player> getSelectedHomePlayers() {
        ArrayList<Player> selectedHomePlayers = new ArrayList<>();
        for (int i =0; i< list_of_homeTeam_players.size(); i++){
            if (list_of_homeTeam_players.get(i).isChecked()) {
                selectedHomePlayers.add(list_of_homeTeam_players.get(i));
            }
        }
        return selectedHomePlayers;
    }

    @NonNull
    @Override
    public MyHomeTeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(homeTeam_context).inflate(R.layout.recyclerview_row_players, parent, false);
        return new MyHomeTeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHomeTeamViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bind(list_of_homeTeam_players.get(position));
    }

    @Override
    public int getItemCount() {
        return list_of_homeTeam_players.size();
    }
}
