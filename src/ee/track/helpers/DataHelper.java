package ee.track.helpers;

import ee.track.model.Data;
import ee.track.model.DataChange;

public class DataHelper {

    public static String[][] getData(String link, int columns) {
        return getTestData(columns);
    }

    public static DataChange getDataChage(Data oldValue, Data newValue) {
        if (oldValue.equals(newValue)) {
            return null;
        }
        DataChange change = new DataChange(oldValue.getId());
        if (!oldValue.getName().equals(newValue.getName())) {
            change.addChange("name", oldValue.getName(), newValue.getName());
        }
        if (!oldValue.getMark().equals(newValue.getMark())) {
            change.addChange("mark", oldValue.getMark(), newValue.getMark());
        }
        if (!oldValue.getDescription().equals(newValue.getDescription())) {
            change.addChange("description", oldValue.getDescription(), newValue.getDescription());
        }
        if (!oldValue.getYear().equals(newValue.getYear())) {
            change.addChange("year", oldValue.getYear(), newValue.getYear());
        }
        if (!oldValue.getFuel().equals(newValue.getFuel())) {
            change.addChange("fuel", oldValue.getFuel(), newValue.getFuel());
        }
        if (!oldValue.getGear().equals(newValue.getGear())) {
            change.addChange("gear", oldValue.getGear(), newValue.getGear());
        }
        if (!oldValue.getPrice().equals(newValue.getPrice())) {
            change.addChange("price", oldValue.getPrice(), newValue.getPrice());
        }
        return change;
    }

    private static String[][] getTestData(int columns) {
        int rows = 20;
        String[][] data = new String[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                data[i][j] = "test:" + i + "" + j;
            }
        }
        return data;
    }

}
