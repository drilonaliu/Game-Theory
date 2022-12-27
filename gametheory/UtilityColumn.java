package gametheory;

import java.util.ArrayList;

public class UtilityColumn {

    private ArrayList<UtilityPair> column;
    private boolean dominated;
    private ArrayList<UtilityPair> bestResponses;

    public UtilityColumn() {
        column = new ArrayList<>();
    }

    public UtilityColumn(ArrayList<UtilityPair> column) {
        this.column = column;
    }

    /**
     * @param row2 another column
     * @return true if rigorously dominates column2
     */
    public boolean dominates(UtilityColumn column2) {
        boolean dominates = true;
        for (int i = 0; i < column.size(); i++) {
            double x = this.getPair(i).getValue(2);
            double y = column2.getPair(i).getValue(2);
            if (x <= y) {
                dominates = false;
                break;
            }
        }
        if (dominates) {
            column2.setDominated(true);
        }
        return dominates;
    }

    /**
     * @return a list of utilityPairs on which best responses for Player One occur
     */
    public ArrayList<UtilityPair> findBR() {
        bestResponses = (ArrayList<UtilityPair>) column.clone();
        double br = 0;
        for (UtilityPair utilityPair : column) {
            double x = utilityPair.getValue(1);
            if (br <= x) {
                br = x;
            } else {
                bestResponses.remove(utilityPair);
            }
        }
        for (UtilityPair BestResponse : bestResponses) {
            BestResponse.setBr(1);
        }
        return bestResponses;
    }

    public void addPair(UtilityPair pair) {
        column.add(pair);
    }

    public void deletePair(int indexPair) {
        column.remove(indexPair);
    }

    public ArrayList<UtilityPair> getColumn() {
        return column;
    }

    public UtilityPair getPair(int pair) {
        return column.get(pair);
    }

    public void setDominated(boolean dominated) {
        this.dominated = dominated;
    }

    public boolean isDominated() {
        return dominated;
    }

    @Override
    public String toString() {
        String x = "";
        for (int i = 0; i < column.size(); i++) {
            x = x + column.get(i).toString() + " ";
        }
        return x;
    }

}
