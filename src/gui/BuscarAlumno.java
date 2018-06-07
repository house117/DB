/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author House
 */
public class BuscarAlumno extends JDialog{
    private JRadioButton  busqId;
    private JRadioButton busqNombre;
    private JRadioButton busqApat;
    private JRadioButton busqAmat;
    private JRadioButton busqCarr;
    private ButtonGroup btnGroup;
    private JPanel radioPanel;
    private JPanel queryPanel;
    private JButton btnAceptar;
    private JTextField txtQuery;
    public BuscarAlumno(MainFrame mainFrame, Connection db) {
        super(mainFrame, "Busqueda", true);
        
        super.setAlwaysOnTop(true);
        
        super.setSize(400, 400);
        super.setDefaultCloseOperation(HIDE_ON_CLOSE);
        super.setLayout(new GridLayout(0, 1));
        super.setLocationRelativeTo(mainFrame);
        
        busqId = new JRadioButton("ID");
        busqId.setActionCommand("id_alumno");
        busqNombre = new JRadioButton("NOMBRE");
        busqNombre.setActionCommand("nombre");
        busqNombre.setSelected(true);
        busqApat = new JRadioButton("APELLIDO PATERNO");
        busqApat.setActionCommand("apellidopaterno");
        busqAmat = new JRadioButton("APELLIDO MATERNO");
        busqAmat.setActionCommand("apellidomaterno");
        busqCarr = new JRadioButton("CARRERA");
        busqCarr.setActionCommand("descripcion");
        
        btnGroup = new ButtonGroup();
        btnGroup.add(busqId);
        btnGroup.add(busqNombre);
        btnGroup.add(busqApat);
        btnGroup.add(busqAmat);
        btnGroup.add(busqCarr);
        
        
        //PANEL RADIO BUTTONSSS
        radioPanel = new JPanel(new GridLayout(0, 1));
        radioPanel.add(busqId);
        radioPanel.add(busqNombre);
        radioPanel.add(busqApat);
        radioPanel.add(busqAmat);
        radioPanel.add(busqCarr);
        
        
        
        txtQuery = new JTextField(15);
        btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                if(btnGroup.getSelection().getActionCommand().equals(busqCarr.getActionCommand())){
                    try {
                        PreparedStatement query = db.prepareStatement("select "
                                + "id_alumno, nombre, apellidopaterno, apellidomaterno, fecha_nac, carrera_id"
                                + " from alumno where id_alumno"
                                + " in (select carrera_id from carrera where descripcion ilike '%"+txtQuery.getText()+"%')");
                        ResultSet resultado = query.executeQuery();
                        ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda(BuscarAlumno.this, resultado);
                        //pnlTabla = new TablePanel(resultado);
                        //BuscarAlumno.this.setVisible(false);
                    } catch (SQLException ex) {
                        Logger.getLogger(BuscarAlumno.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    try {
                        //System.out.println("Select * from alumno where "+
                        //btnGroup.getSelection().getActionCommand()+" ilike '%"+txtQuery.getText()+"%'");
                        PreparedStatement query = db.prepareStatement("Select "
                                + "id_alumno, nombre, apellidopaterno, apellidomaterno, fecha_nac, carrera_id"
                                + " from alumno where "+
                                btnGroup.getSelection().getActionCommand()+" ilike '%"+txtQuery.getText()+"%'");
                        ResultSet resultado = query.executeQuery();
                        ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda(BuscarAlumno.this, resultado);
                        //pnlTabla = new TablePanel(resultado);
                        //BuscarAlumno.this.setVisible(false);
                    } catch (SQLException ex) {
                        Logger.getLogger(BuscarAlumno.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        //PANEL QUERY Y BOTONZZZITO.
        queryPanel = new JPanel(new FlowLayout());
        queryPanel.add(txtQuery);
        queryPanel.add(btnAceptar);
        
        super.add(radioPanel);
        super.add(queryPanel);
        
        
        super.setVisible(true);
    }

    
    
    public JRadioButton getBusqId() {
        return busqId;
    }

    public void setBusqId(JRadioButton busqId) {
        this.busqId = busqId;
    }

    public JRadioButton getBusqNombre() {
        return busqNombre;
    }

    public void setBusqNombre(JRadioButton busqNombre) {
        this.busqNombre = busqNombre;
    }

    public JRadioButton getBusqApat() {
        return busqApat;
    }

    public void setBusqApat(JRadioButton busqApat) {
        this.busqApat = busqApat;
    }

    public JRadioButton getBusqAmat() {
        return busqAmat;
    }

    public void setBusqAmat(JRadioButton busqAmat) {
        this.busqAmat = busqAmat;
    }

    public JRadioButton getBusqCarr() {
        return busqCarr;
    }

    public void setBusqCarr(JRadioButton busqCarr) {
        this.busqCarr = busqCarr;
    }

    /**
     * @return the btnGroup
     */
    public ButtonGroup getBtnGroup() {
        return btnGroup;
    }

    /**
     * @param btnGroup the btnGroup to set
     */
    public void setBtnGroup(ButtonGroup btnGroup) {
        this.btnGroup = btnGroup;
    }
    
    
}
