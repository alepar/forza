package ru.alepar.forza.android.ui.activities;

import android.content.res.Resources;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import ru.alepar.forza.android.R;
import ru.alepar.forza.android.ui.core.ViewFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AllTeamsTable {

    private final TableLayout table;
    private final ViewFactory viewFactory;

    private final int colorOfRowBackground;
    private final int colorOfRowBackgroundSelected;

    private final Map<Long, TableRow> rows;
    private final Map<Long, TeamInfo> infos;

    private Long selectedTeamId;

    public AllTeamsTable(ViewFactory viewFactory, Resources resources) {
        this.viewFactory = viewFactory;
        this.table = viewFactory.inflate(TableLayout.class, R.layout.allteams_table);
        this.rows = new HashMap<Long, TableRow>();
        this.infos = new HashMap<Long, TeamInfo>();

        this.colorOfRowBackground = resources.getColor(R.color.table_row_background);
        this.colorOfRowBackgroundSelected = resources.getColor(R.color.table_row_background_selected);
    }

    public void updateTeamInfo(final TeamInfo info) {
        infos.put(info.id, info);
        TableRow row = rows.get(info.id);
        if (row == null) {
            row = createRow();
            rows.put(info.id, row);
            table.addView(row);
            row.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onSelectTeam(info.id);
                    return true;
                }
            });
        }
        fillRowWith(info, row);
    }

    private void onSelectTeam(long teamId) {
        if (selectedTeamId != null) {
            rows.get(selectedTeamId).setBackgroundColor(colorOfRowBackground);
        }
        rows.get(teamId).setBackgroundColor(colorOfRowBackgroundSelected);
        selectedTeamId = teamId;
    }

    private TableRow createRow() {
        return viewFactory.inflate(TableRow.class, R.layout.allteams_row);
    }

    public View getContentView() {
        return table;
    }

    private void fillRowWith(TeamInfo info, TableRow row) {
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
        return time < 0 ? "-" : String.format("%.2f", ((double) time) / 1000);
    }

    private static TextView findCell(int allteams_row_teamname, TableRow row) {
        return (TextView) row.findViewById(allteams_row_teamname);
    }

    public Collection<TeamInfo> getCurrentState() {
        return infos.values();
    }

}
