/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author House
 */
public class TablePanel extends JPanel{
    /*
        Esta clase es de lo más importante pues aquí se muestran los alumnos
        Recibe un arrayList de alumnos y lo despliega con su constructor
    */
    private JScrollPane scrollPane;
    private JTable studentsTable;
    private DefaultTableModel tblmEstudiante;
    //Despliega la lista completa que recibe
    public TablePanel(ResultSet rs) throws SQLException{
        super();
        super.setLayout(new GridLayout(0, 1));
        super.setSize(790, 590);
        studentsTable = new JTable(buildTableModel(rs));
        scrollPane = new JScrollPane(studentsTable);
        super.add(scrollPane);
    }
    //Método para agregar un alumno más a la lista.
   
        
      

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public JTable getStudentsTable() {
        return studentsTable;
    }

    public void setStudentsTable(JTable studentsTable) {
        this.studentsTable = studentsTable;
    }

    public DefaultTableModel getTblmEstudiante() {
        return tblmEstudiante;
    }

    public void setTblmEstudiante(DefaultTableModel tblmEstudiante) {
        this.tblmEstudiante = tblmEstudiante;
    }
    public static DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);

    }
    
}
