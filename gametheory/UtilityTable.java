package gametheory;

import java.util.ArrayList;

public class UtilityTable {

    public boolean STEP_BY_STEP = true;
    private ArrayList<UtilityRow> rows;
    private ArrayList<UtilityColumn> columns;
    private ArrayList<UtilityPair> NashEquilibrums;
    private boolean allEliminated = false;
    private boolean rowsDominated;
    private boolean colsDominated;

    public UtilityTable() {
        rows = new ArrayList<>();
        columns = new ArrayList<>();
        NashEquilibrums = new ArrayList<>();
    }

    public UtilityTable(ArrayList<UtilityRow> rows) {
        this.rows = rows;
    }

    /**
     * Creates columns from given rows
     */
    private void initCols() {
        int colsNum = this.getRow(0).sizeRow();
        for (int i = 0; i < colsNum; i++) {
            UtilityColumn col = new UtilityColumn();
            for (int j = 0; j < rows.size(); j++) {
                col.addPair(this.getRow(j).getPair(i));
            }
            columns.add(col);
        }
    }

    /**
     * Finds Nash Equilibrums on clear strategies
     */
    public void findNE() {
        initCols();
        for (UtilityRow row : rows) {
            row.findBR();
        }
        for (UtilityColumn column : columns) {
            ArrayList<UtilityPair> columBestResponses = column.findBR();
            for (UtilityPair colBR : columBestResponses) {
                if (colBR.isNE()) {
                    NashEquilibrums.add(colBR);
                }
            }
        }
    }

    /**
     * Elemenitates Rigorously Dominated Strategies
     */
    public void eleminateDominated() {
        initCols();
        while (!allEliminated) {
            eleminateColumns();
            eleminateRows();
            allEliminated = rowsDominated && colsDominated;
        }
    }

    /**
     * Elemenites rigorously dominated columns
     */
    private void eleminateColumns() {
        colsDominated = true;
        for (int i = 0; i < columns.size(); i++) {
            UtilityColumn column1 = columns.get(i);
            for (int j = 0; j < columns.size(); j++) {
                UtilityColumn column2 = columns.get(j);
                if (i != j && column1.dominates(column2)) {
                    deleteColumn(j);
                    colsDominated = false;
                    printElimination(2, i, j);
                    i -= 1;
                }
            }
        }
    }

    /**
     * Elemenites rigorously dominated rows
     */
    private void eleminateRows() {
        rowsDominated = true;
        for (int i = 0; i < rows.size(); i++) {
            UtilityRow row = rows.get(i);
            for (int j = 0; j < rows.size(); j++) {
                UtilityRow row2 = rows.get(j);
                if (i != j && row.dominates(row2)) {
                    deleteRow(j);
                    rowsDominated = false;
                    printElimination(1, i, j);
                    i -= 1;
                }
            }
        }
    }

    /**
     * Deletes a column from the table
     * 
     * @param colIndex - which column to delete
     */
    public void deleteColumn(int colIndex) {
        for (UtilityRow row : rows) {
            row.deletePair(colIndex);
        }
        columns.remove(colIndex);
    }

    public void deleteRow(int rowIndex) {
        for (UtilityColumn column : columns) {
            column.deletePair(rowIndex);
        }
        rows.remove(rowIndex);
    }

    public void addUtilityRow(UtilityRow row) {
        rows.add(row);
    }

    public UtilityRow getRow(int i) {
        return rows.get(i);
    }

    public ArrayList<UtilityPair> getNashEquilibrums() {
        return NashEquilibrums;
    }

    /**
     * Prints which strategy dominates another strategy and utility table on a
     * certain iteration
     */
    private void printElimination(int player, int i, int j) {
        if (STEP_BY_STEP) {
            System.out.println(
                    "\nPlayer " + player + ". Strategy " + i + " rigorously dominates strategy " + j );
            System.out.println(this);
        }
    }

    @Override
    public String toString() {
        String x = "";
        for (int i = 0; i < rows.size(); i++) {
            x += this.getRow(i).toString();
            x += "\n";
        }
        return x;
    }
}
