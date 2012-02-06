package ru.alepar.forza.android;

import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class AllTeamsTable {

    private final TableLayout table;
    private final ViewFactory viewFactory;
    private final Map<Long, TableRow> teamRows;

    public AllTeamsTable(ViewFactory viewFactory) {
        this.viewFactory = viewFactory;
        this.table = viewFactory.inflate(TableLayout.class, R.layout.allteams_table);
        this.teamRows = new HashMap<Long, TableRow>();
    }

    public void updateTeamInfo(TeamInfo info) {
        TableRow row = teamRows.get(info.id);
        if (row == null) {
            row = createRow();
            teamRows.put(info.id, row);
            table.addView(row);
        }
        fillRowWith(info, row);
    }

    private TableRow createRow() {
        return viewFactory.inflate(TableRow.class, R.layout.allteams_row);
    }

    public View getContentView() {
        return table;
    }

    private static void fillRowWith(TeamInfo info, TableRow row) {
        findCell(R.id.allteams_row_id, row)
                .setText(String.valueOf(info.id));
        findCell(R.id.allteams_row_teamname, row)
                .setText(info.name);
        findCell(R.id.allteams_row_avglap, row)
                .setText(formatTime(info.avgLap));
        findCell(R.id.allteams_row_delta, row)
                .setText(formatTime(info.delta));

        final TextView lastLapCell = findCell(R.id.allteams_row_lastlap, row);
        lastLapCell.setText(formatTime(info.lastLap));
        lastLapCell.startAnimation(new BlinkAnimation(lastLapCell));
    }

    private static String formatTime(long time) {
        return time < 0 ? "-" : String.format("%.3f", ((double) time) / 1000);
    }

    private static TextView findCell(int allteams_row_teamname, TableRow row) {
        return (TextView) row.findViewById(allteams_row_teamname);
    }

}
