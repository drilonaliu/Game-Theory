package gametheory;

import java.util.ArrayList;

public class UtilityRow {
    private ArrayList<UtilityPair> row;
    private boolean dominated;
    private ArrayList<UtilityPair> bestResponses;

    public UtilityRow() {
        row = new ArrayList<>();
    }

    public UtilityRow(ArrayList<UtilityPair> row) {
        this.row = row;
    }

    /**
     * @param row2 another row
     * @return true if rigorously dominates row2
     */
    public boolean dominates(UtilityRow row2) {
        boolean dominates = true;
        for (int i = 0; i < row.size(); i++) {
            double x = this.getPair(i).getValue(1);
            double y = row2.getPair(i).getValue(1);
            if (x <= y) {
                dominates = false;
                break;
            }
        }
        if (dominates) {
            row2.setDominated(true);
        }
        return dominates;
    }

    /**
     * @return a list of utilityPairs on which best responses for Player Two occur
     */
        public ArrayList<UtilityPair> findBR() {
            bestResponses = (ArrayList<UtilityPair>) row.clone();
            double br = 0;
            for (UtilityPair utilityPair : row) {
                double y = utilityPair.getValue(2);
                if (br <= y) {
                    br = y;
                } else {
                    bestResponses.remove(utilityPair);
                }
            }
            for (UtilityPair BestResponse : bestResponses) {
                BestResponse.setBr(2);
            }
            return bestResponses;
        }

    public void addPair(UtilityPair pair) {
        row.add(pair);
    }

    public void deletePair(int indexPair) {
        row.remove(indexPair);
    }

    public UtilityPair getPair(int pair) {
        return row.get(pair);
    }

    public ArrayList<UtilityPair> getRow() {
        return row;
    }

    public int sizeRow() {
        return row.size();
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
        for (int i = 0; i < row.size(); i++) {
            x = x + row.get(i).toString() + " ";
        }
        return x;
    }
}
