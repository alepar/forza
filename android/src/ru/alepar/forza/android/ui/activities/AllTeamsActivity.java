package ru.alepar.forza.android.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import ru.alepar.forza.android.ui.core.LayoutInflaterFactory;

import java.util.Collection;
import java.util.Random;

public class AllTeamsActivity extends Activity {

    private static final String STATE_KEY = "AllTeamsActivity-state";

    private AllTeamsTable table;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        table = new AllTeamsTable(new LayoutInflaterFactory(getLayoutInflater()), getResources());
        setContentView(table.getContentView());

        runMockData(table);
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);

        final Collection<TeamInfo> currentState = table.getCurrentState();
        outState.putSerializable(STATE_KEY, currentState.toArray(new TeamInfo[currentState.size()]));
    }

    @Override
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        table.getContentView().post(new Runnable() {
            public void run() {
                @SuppressWarnings("unchecked")
                final TeamInfo[] teamInfos = (TeamInfo[]) savedInstanceState.get(STATE_KEY);
                for (TeamInfo teamInfo : teamInfos) {
                    table.updateTeamInfo(teamInfo);
                }
            }
        });
    }

    private static void runMockData(final AllTeamsTable table) {
        final Random rnd = new Random();

        table.updateTeamInfo(new TeamInfo(0L, "Forza RS", -1, -1, -1));
        table.updateTeamInfo(new TeamInfo(1L, "Swift RT", -1, -1, -1));
        table.updateTeamInfo(new TeamInfo(2L, "MySmart", -1, -1, -1));

        table.getContentView().postDelayed(new MockDataRunnable(table, rnd), 1000L);
    }

    private static class MockDataRunnable implements Runnable {

        private final AllTeamsTable table;
        private final Random rnd;

        public MockDataRunnable(AllTeamsTable table, Random rnd) {
            this.table = table;
            this.rnd = rnd;
        }

        public void run() {
            table.getContentView().postDelayed(new Runnable() {
                public void run() {
                    table.updateTeamInfo(new TeamInfo(0L, "Forza RS", 31500 + rnd.nextInt(200), 31700 + rnd.nextInt(100), 0));
                }
            }, rnd.nextInt(250));
            table.getContentView().postDelayed(new Runnable() {
                public void run() {
                    table.updateTeamInfo(new TeamInfo(1L, "Swift RT", 31800 + rnd.nextInt(200), 32000 + rnd.nextInt(100), rnd.nextInt(300)));
                }
            }, rnd.nextInt(450));
            table.getContentView().postDelayed(new Runnable() {
                public void run() {
                    table.updateTeamInfo(new TeamInfo(2L, "MySmart", 31800 + rnd.nextInt(200), 32000 + rnd.nextInt(100), rnd.nextInt(300)));
                }
            }, rnd.nextInt(450));
            table.getContentView().postDelayed(new MockDataRunnable(table, rnd), 1000L);
        }
    }
}
