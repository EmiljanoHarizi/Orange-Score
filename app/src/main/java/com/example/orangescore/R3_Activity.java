package com.example.orangescore;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class R3_Activity extends AppCompatActivity {
    private java.sql.Connection connnection;

    class Connection extends R3_Activity {

        String username, pass, ip, port, database;

        public Connection connectionclass() {
            ip = "mysql-tim.alwaysdata.net";
            database = "tim_orangescore";
            username = "tim";
            pass = "Timtimtim1";
            port = "3306";
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Connection connection = null;
            String ConnectionURL = null;

            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                ConnectionURL = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";" + "databasename=" + database + ";user=" + username + ";password=" + pass + ";";
                connection = (Connection) DriverManager.getConnection(ConnectionURL);
            } catch (Exception ex) {
                Log.e("Error", ex.getMessage());
            }
            return connection;
        }
    }

    public void SelectDataMySQL() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        //Connect to DATABASE
        String url = "https://phpmyadmin.alwaysdata.com/phpmyadmin/index.php?route=/database/structure&db=tim_orangescore";
        String user = "tim";
        String password = "Timtimtim1";

        //Transfer data from R2 to DATABASE
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            // Connection con = DriverManager.getConnection(url, user, password);
            Statement st = connnection.createStatement();

            //points
            String sqlpoints = ("UPDATE playerStatsGame INNER JOIN R2 ON playerStatsGame.pname = R2.Name SET playerStatsGame.points = playerStatsGame.points + IF(R2.Category='points',R2.Value,0)");
            String sqlpointsS = ("UPDATE playerStatsSeason INNER JOIN R2 ON playerStatsSeason.pname = R2.Name SET playerStatsSeason.points = playerStatsSeason.points + IF(R2.Category='points',R2.Value,0)");

            //Nopoints
            String sqlNoFTpoints = ("UPDATE playerStatsGame INNER JOIN R2 ON playerStatsGame.pname = R2.Name SET playerStatsGame.NoFTpoints = playerStatsGame.NoFTpoints + IF(R2.Category='NoFTpoints',R2.Value,0)");
            String sqlNoPT2points = ("UPDATE playerStatsGame INNER JOIN R2 ON playerStatsGame.pname = R2.Name SET playerStatsGame.NoPT2points = playerStatsGame.NoPT2points + IF(R2.Category='NoPT2points',R2.Value,0)");
            String sqlNoPT3points = ("UPDATE playerStatsGame INNER JOIN R2 ON playerStatsGame.pname = R2.Name SET playerStatsGame.NoPT3points = playerStatsGame.NoPT3points + IF(R2.Category='NoPT3points',R2.Value,0)");
            String sqlNoFTpointsS = ("UPDATE playerStatsSeason INNER JOIN R2 ON playerStatsSeason.pname = R2.Name SET playerStatsSeason.NoFTpoints = playerStatsSeason.NoFTpoints + IF(R2.Category='NoFTpoints',R2.Value,0)");
            String sqlNoPT2pointsS = ("UPDATE playerStatsSeason INNER JOIN R2 ON playerStatsSeason.pname = R2.Name SET playerStatsSeason.NoPT2points = playerStatsSeason.NoPT2points + IF(R2.Category='NoPT2points',R2.Value,0)");
            String sqlNoPT3pointsS = ("UPDATE playerStatsSeason INNER JOIN R2 ON playerStatsSeason.pname = R2.Name SET playerStatsSeason.NoPT3points = playerStatsSeason.NoPT3points + IF(R2.Category='NoPT3points',R2.Value,0)");

            //rebounds
            String sqlrebounds = ("UPDATE playerStatsGame INNER JOIN R2 ON playerStatsGame.pname = R2.Name SET playerStatsGame.rebounds = playerStatsGame.rebounds + IF(R2.Category='rebounds',R2.Value,0)");
            String sqlreboundsS = ("UPDATE playerStatsSeason INNER JOIN R2 ON playerStatsSeason.pname = R2.Name SET playerStatsSeason.rebounds = playerStatsSeason.rebounds + IF(R2.Category='rebounds',R2.Value,0)");

            //assists
            String sqlassists = ("UPDATE playerStatsGame INNER JOIN R2 ON playerStatsGame.pname = R2.Name SET playerStatsGame.assists = playerStatsGame.assists + IF(R2.Category='assists',R2.Value,0)");
            String sqlassistsS = ("UPDATE playerStatsSeason INNER JOIN R2 ON playerStatsSeason.pname = R2.Name SET playerStatsSeason.assists = playerStatsSeason.assists + IF(R2.Category='assists',R2.Value,0)");

            //foul
            String sqlfoul = ("UPDATE playerStatsGame INNER JOIN R2 ON playerStatsGame.pname = R2.Name SET playerStatsGame.foul = playerStatsGame.foul + IF(R2.Category='foul',R2.Value,0)");
            String sqlfoulS = ("UPDATE playerStatsSeason INNER JOIN R2 ON playerStatsSeason.pname = R2.Name SET playerStatsSeason.foul = playerStatsSeason.foul + IF(R2.Category='foul',R2.Value,0)");

            //turnover
            String sqlturnover = ("UPDATE playerStatsGame INNER JOIN R2 ON playerStatsGame.pname = R2.Name SET playerStatsGame.turnover = playerStatsGame.turnover + IF(R2.Category='turnover',R2.Value,0)");
            String sqlturnoverS = ("UPDATE playerStatsSeason INNER JOIN R2 ON playerStatsSeason.pname = R2.Name SET playerStatsSeason.turnover = playerStatsSeason.turnover + IF(R2.Category='turnover',R2.Value,0)");

            //block
            String sqlblock = ("UPDATE playerStatsGame INNER JOIN R2 ON playerStatsGame.pname = R2.Name SET playerStatsGame.block = playerStatsGame.block + IF(R2.Category='block',R2.Value,0)");
            String sqlblockS = ("UPDATE playerStatsSeason INNER JOIN R2 ON playerStatsSeason.pname = R2.Name SET playerStatsSeason.block = playerStatsSeason.block + IF(R2.Category='block',R2.Value,0)");

            //steal
            String sqlsteal = ("UPDATE playerStatsGame INNER JOIN R2 ON playerStatsGame.pname = R2.Name SET playerStatsGame.steal = playerStatsGame.steal + IF(R2.Category='steal',R2.Value,0)");
            String sqlstealS = ("UPDATE playerStatsSeason INNER JOIN R2 ON playerStatsSeason.pname = R2.Name SET playerStatsSeason.steal = playerStatsSeason.steal + IF(R2.Category='steal',R2.Value,0)");

            //Make table R2 empty
            String sqlFINAL = ("TRUNCATE TABLE R2");

            st.addBatch(sqlpoints);
            st.addBatch(sqlNoFTpoints);
            st.addBatch(sqlNoPT2points);
            st.addBatch(sqlNoPT3points);
            st.addBatch(sqlrebounds);
            st.addBatch(sqlassists);
            st.addBatch(sqlfoul);
            st.addBatch(sqlturnover);
            st.addBatch(sqlblock);
            st.addBatch(sqlsteal);
            st.addBatch(sqlpointsS);
            st.addBatch(sqlNoFTpointsS);
            st.addBatch(sqlNoPT2pointsS);
            st.addBatch(sqlNoPT3pointsS);
            st.addBatch(sqlreboundsS);
            st.addBatch(sqlassistsS);
            st.addBatch(sqlfoulS);
            st.addBatch(sqlturnoverS);
            st.addBatch(sqlblockS);
            st.addBatch(sqlstealS);
            st.addBatch(sqlFINAL);

            st.executeBatch();
            connnection.commit();
        } catch (Exception e){
            System.out.println("ERROR: couldn't read Data from Database");
            e.printStackTrace();
        }
    }
}
