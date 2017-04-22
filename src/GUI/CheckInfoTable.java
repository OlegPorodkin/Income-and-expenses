package GUI;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class CheckInfoTable extends AbstractTableModel {

    private int columnCount = 4;
    private ArrayList<String []> dataArrayList ;

    public CheckInfoTable(){
        dataArrayList = new ArrayList<>();
        for (int i = 0; i < dataArrayList.size(); i++){
            dataArrayList.add(new String[getColumnCount()]);
        }
    }

    @Override
    public int getRowCount() {
        return dataArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public String getColumnName(int columnIndex){
        switch (columnIndex){
            case 0: return "п/н";
            case 1: return "Дата";
            case 2: return "Сумма";
            case 3: return "Описание";
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String []rows = dataArrayList.get(rowIndex);
        return rows[columnIndex];
    }

    public void addDate(String []row){
        String [] rowTable = row;
        dataArrayList.add(rowTable);
    }
}
