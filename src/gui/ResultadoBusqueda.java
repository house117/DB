/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JDialog;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;

/**
 *
 * @author House
 */
public class ResultadoBusqueda extends JDialog{
    private TablePanel tabla;
    public ResultadoBusqueda(JDialog parent, ResultSet rs) throws SQLException{
        super(parent, "Busqueda", true);
        
        super.setAlwaysOnTop(true);
        super.setSize(800, 600);
        super.setDefaultCloseOperation(HIDE_ON_CLOSE);
        super.setLayout(new GridLayout(0, 1));
        super.setLocationRelativeTo(parent);
        super.setTitle("Resultado");
        super.setAlwaysOnTop(rootPaneCheckingEnabled);
        
        tabla = new TablePanel(rs);
        
        super.add(tabla);
        super.setVisible(true);
        
        
    }
}
