package ru.alepar.forza.android;

import android.app.Activity;
import android.os.Bundle;

import java.util.Random;

public class AllTeamsActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final AllTeamsTable table = new AllTeamsTable(new LayoutInflaterFactory(getLayoutInflater()));
        setContentView(table.getContentView());

        runMockData(table);
    }

    private static void runMockData(final AllTeamsTable table) {
        table.updateTeamInfo(new TeamInfo(0L, "Forza RS", -1, -1, -1));
        table.updateTeamInfo(new TeamInfo(1L, "Swift RT", -1, -1, -1));
        table.updateTeamInfo(new TeamInfo(2L, "MySmart", -1, -1, -1));

        table.getContentView().postDelayed(new Runnable() {
            public void run() {
                final Random rnd = new Random();
                for (int i = 0; i < 1000; i++) {
                    table.getContentView().postDelayed(new Runnable() {
                        public void run() {
                            table.updateTeamInfo(new TeamInfo(0L, "Forza RS", 31500 + rnd.nextInt(200), 31700 + rnd.nextInt(100), 0));
                        }
                    }, i * 500 + rnd.nextInt(450));
                    table.getContentView().postDelayed(new Runnable() {
                        public void run() {
                            table.updateTeamInfo(new TeamInfo(1L, "Swift RT", 31800 + rnd.nextInt(200), 32000 + rnd.nextInt(100), rnd.nextInt(300)));
                        }
                    }, i * 500 + rnd.nextInt(450));
                    table.getContentView().postDelayed(new Runnable() {
                        public void run() {
                            table.updateTeamInfo(new TeamInfo(2L, "MySmart", 31800 + rnd.nextInt(200), 32000 + rnd.nextInt(100), rnd.nextInt(300)));
                        }
                    }, i * 500 + rnd.nextInt(450));
                }
            }
        }, 1000L);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        throw new RuntimeException("alepar haven't implemented me yet");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        throw new RuntimeException("alepar haven't implemented me yet");
    }
}
