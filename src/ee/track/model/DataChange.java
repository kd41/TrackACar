package ee.track.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class DataChange {
    private String id;
    private Date date;
    private List<String> variables;
    private List<String> oldValues;
    private List<String> newValues;

    public DataChange(String id) {
        this.id = id;
        variables = new ArrayList<>();
        oldValues = new ArrayList<>();
        newValues = new ArrayList<>();
        date = new Date();
    }

    public void addChange(String variable, String oldValue, String newValue) {
        variables.add(variable);
        oldValues.add(oldValue);
        newValues.add(newValue);
    }

    public String getChangesAsString() {
        if (oldValues.isEmpty()) {
            return StringUtils.EMPTY;
        }
        StringBuilder builder = new StringBuilder();
        builder.append("DataChange [date=").append(date);
        builder.append(", id=").append(id);
        builder.append(", changes=");
        for (int i = 0; i < oldValues.size(); i++) {
            builder.append(variables.get(i)).append("[").append(oldValues.get(i)).append(" -> ").append(newValues.get(i)).append("],");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DataChange [id=");
        builder.append(id);
        builder.append(", date=");
        builder.append(date);
        builder.append(", variables=");
        builder.append(variables);
        builder.append(", oldValues=");
        builder.append(oldValues);
        builder.append(", newValues=");
        builder.append(newValues);
        builder.append("]");
        return builder.toString();
    }

}
