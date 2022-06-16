package com.example.orangescore;

import android.os.StrictMode;
import android.view.textclassifier.TextLinks;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpHandler {
    public OkHttpHandler() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    ArrayList<PlayerStats> fetchPlayerStatsGame(String url) throws Exception{
        ArrayList<PlayerStats> psList = new ArrayList<>();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), "");
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();
        try {
            JSONObject json = new JSONObject(data);
            Iterator<String> keys = json.keys();
            while(keys.hasNext()){
                String stats = keys.next();
                System.out.println(stats);
                String pname = json.getJSONObject(stats).getString("pname").toString();
                int points = json.getJSONObject(stats).getInt("points");
                int rebounds = json.getJSONObject(stats).getInt("rebounds");
                int assists = json.getJSONObject(stats).getInt("assists");
                String team = json.getJSONObject(stats).getString("team").toString();
                psList.add(new PlayerStats(pname, points,rebounds,assists,team));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return psList;
    }

    ArrayList<PlayerStats> getPlayerStatsSeason(String url) throws Exception{
        ArrayList<PlayerStats> psList = new ArrayList<>();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), "");
        Request request = new Request.Builder().url(url).method("POST",body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();
        System.out.println("My response: " + data);
        try {

            JSONObject json = new JSONObject(data);
            Iterator<String> keys = json.keys();
            while (keys.hasNext()) {
                String brand = keys.next();
                String playerName = json.getJSONObject(brand).getString("pname");
                int playerPoints = json.getJSONObject(brand).getInt("points");
                int playerRebounds = json.getJSONObject(brand).getInt("rebounds");
                int playerAssists = json.getJSONObject(brand).getInt("assists");
                String playerTeam = "";
                psList.add(new PlayerStats(playerName, playerPoints, playerRebounds, playerAssists,playerTeam));
            }
        } catch (JSONException e){
            e.printStackTrace();
        }

        return psList;
    }

}
