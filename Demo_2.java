import gametheory.*;

public class Demo_2 {
    public static void main(String[] args) {

        /*
         * Gjeni NE ne matricen e dobive
         * (0.0 , 0.0) (1.0 , 0.0)
         * (0.0 , 1.0) (0.41 , 0.41)
         **/
        UtilityRow r1 = new UtilityRow();
        r1.addPair(new UtilityPair(0, 0));
        r1.addPair(new UtilityPair(1, 0));

        UtilityRow r2 = new UtilityRow();
        r2.addPair(new UtilityPair(0, 1));
        r2.addPair(new UtilityPair(0.41, 0.41));

        UtilityTable table = new UtilityTable();
        table.addUtilityRow(r1);
        table.addUtilityRow(r2);

        System.out.println("Utility Matrix");
        System.out.println(table);
        table.findNE();
        System.out.println("NE = " + table.getNashEquilibrums());
    }
}
