package gametheory;

public class UtilityPair {

    private double value1;
    private double value2;
    private boolean br1 = false;
    private boolean br2 = false;

   public UtilityPair(double value1, double value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public double getValue(int x) {
        if (x == 1) {
            return value1;
        } else {
            return value2;
        }
    }

    public void setValue1(int value1) {
        this.value1 = value1;
    }

    public void setValue2(int value2) {
        this.value2 = value2;
    }

    public void setBr(int x) {
        if (x == 1) {
            br1 = true;
        } else {
            br2 = true;
        }
    }

    public boolean isNE() {
        return br1 && br2;
    }

    @Override
    public String toString() {
        return "(" + value1 + " , " + value2 + ")";
    }
}
