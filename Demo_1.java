import gametheory.*;

public class Demo_1 {

    public static void main(String[] args) {

        /**
         * Eliminoni strategjite rigorozisht te dominuara ne matricen e dobive
         * 
         * (2.0 , 500.0) (2200.0 , 111.0) (2.0 , 2.0)
         * (1.0 , 500.0) (1.0 , 111.0) (1.0 , 1.0)
         * (300.0 , 500.0) (3300.0 , 111.0) (300.0 , 3.0)
         */
        UtilityRow row1 = new UtilityRow();
        row1.addPair(new UtilityPair(1, 500));
        row1.addPair(new UtilityPair(1, 111));
        row1.addPair(new UtilityPair(1, 1));

        UtilityRow row2 = new UtilityRow();
        row2.addPair(new UtilityPair(2, 500));
        row2.addPair(new UtilityPair(2200, 111));
        row2.addPair(new UtilityPair(2, 2));

        UtilityRow row3 = new UtilityRow();
        row3.addPair(new UtilityPair(300, 500));
        row3.addPair(new UtilityPair(3300, 111));
        row3.addPair(new UtilityPair(300, 3));

        UtilityTable table = new UtilityTable();
        table.addUtilityRow(row2);
        table.addUtilityRow(row1);
        table.addUtilityRow(row3);

        System.out.println("Utility matrix");
        System.out.println(table);
        table.eleminateDominated();
    }
}
