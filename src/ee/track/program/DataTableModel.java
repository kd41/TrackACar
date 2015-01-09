package ee.track.program;

import javax.swing.table.AbstractTableModel;

public class DataTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 3078150295065043754L;

    private static final String[] TABLE_NAMES = { "nr", "id", "name", "mark", "description", "year", "fuel", "gear", "price" };

    public static final int COLUMNS = TABLE_NAMES.length;

    private String[][] data;

    public DataTableModel(String[][] data) {
        if ((data == null) || (data[0].length != TABLE_NAMES.length)) {
            throw new IllegalArgumentException("Wrong data:" + data);
        }
        this.data = data;

    }

    public static String[] getTableNames() {
        return TABLE_NAMES;
    }

    @Override
    public int getColumnCount() {
        return TABLE_NAMES.length;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public Object getValueAt(int arg0, int arg1) {
        return data[arg0][arg1];
    }

}
