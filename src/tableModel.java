import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class tableModel extends AbstractTableModel {
    private ArrayList<String[]> data;
    private String[] header;

    tableModel(String[] header){
        this.header = header;
        data = new ArrayList<>();
    }

    public int getRowCount(){
        return data.size();
    }
    public int getColumnCount(){
        return header.length;
    }
    public String getValueAt(int row, int col){
        return data.get(row)[col];
    }
    public String getColumnName(int index){
        return header[index];
    }

    public void add(Ship ship, java.util.HashMap<Integer, Thing> masterMap, Job jobToAdd){
        System.out.println("adding");
        String[] row = new String[3];
        row[0] = ship.getName();
        Thing parent = masterMap.get(ship.getParent());
        row[1] = masterMap.get(parent.getParent()).getName() + " -- " + parent.getName();
        row[2] = jobToAdd.getName();
        data.add(row);
        fireTableDataChanged();
    }

    public void remove(String name){
        for(String[] arr : data){
            if(arr[2].equals(name)){
                //System.out.println("Removing Job " + name + "");
                //System.out.println("Job Model Size: " + data.size());
                data.remove(arr);
                fireTableDataChanged();
                break;
            }
        }
    }
}