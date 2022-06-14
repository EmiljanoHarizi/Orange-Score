package com.example.orangescore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/** Custom adapter made to populate recycler view that shows matches in current championship */
public class MatchRecyclerViewAdapter extends RecyclerView.Adapter<MatchRecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<Match> list_of_matches;
    private ItemClickListener mItemListener;

    public MatchRecyclerViewAdapter(Context context, ArrayList<Match> list_of_matches, ItemClickListener mItemListener) {
        this.context = context;
        this.list_of_matches = list_of_matches;
        this.mItemListener = mItemListener;
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

        holder.itemView.setOnClickListener(view -> {
            mItemListener.onItemClick(list_of_matches.get(position));
        });

    }

    @Override
    public int getItemCount() {
        return list_of_matches.size();
    }

    public interface ItemClickListener{
        void onItemClick(Match details);
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder {
        TextView team_home_rec, team_away_rec, match_date_rec;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            team_home_rec = itemView.findViewById(R.id.homeTeam_Recycler);
            team_away_rec = itemView.findViewById(R.id.awayTeam_Recycler);
            match_date_rec = itemView.findViewById(R.id.matchDate_Recycler);

        }
    }
}
