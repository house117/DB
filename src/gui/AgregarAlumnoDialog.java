/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import exceptions.FechaInvalidaException;
import static java.awt.Component.RIGHT_ALIGNMENT;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import models.*;
import utilities.Fecha;
/**
 *
 * @author House
 */
public class AgregarAlumnoDialog extends JDialog{
    private JPanel pnlPrincipal;
    private JPanel pnlBtns;
    private JPanel pnlLabels;
    private JPanel pnlData;
    private JButton btnAceptar;
    private JButton btnCancelar;
    public AgregarAlumnoDialog (MainFrame parent, Connection db){
        super(parent, "Agregar alumno", true);
        super.setAlwaysOnTop(true);
        //super.setSize(800, 600);
        super.setDefaultCloseOperation(HIDE_ON_CLOSE);
        super.setLayout(new FlowLayout());
        super.setSize(380, 400);
        super.setResizable(false);
        super.setLocationRelativeTo(parent);
        super.setAlwaysOnTop(rootPaneCheckingEnabled);
        pnlLabels = new JPanel();
        pnlData = new JPanel();
        //PANEL PRINCIPAL
        pnlPrincipal = new JPanel();
        pnlPrincipal.setLayout(new FlowLayout(FlowLayout.LEFT));
        //PANELS LABEL Y DE TEXTFIELDS
        pnlLabels.setLayout(new BoxLayout(pnlLabels, BoxLayout.Y_AXIS));
        pnlData.setLayout(new BoxLayout(pnlData, BoxLayout.Y_AXIS));
        //LABELS
        JLabel lblNoControl = new JLabel("No.Control:");
        lblNoControl.setAlignmentX(RIGHT_ALIGNMENT);
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setAlignmentX(RIGHT_ALIGNMENT);
        JLabel lblApat = new JLabel("Apellido Paterno:");
        lblApat.setAlignmentX(RIGHT_ALIGNMENT);
        JLabel lblAmat = new JLabel("Apellido Materno:");
        lblAmat.setAlignmentX(RIGHT_ALIGNMENT);
        JLabel lblCarrera = new JLabel("Carrera:");
        lblCarrera.setAlignmentX(RIGHT_ALIGNMENT);
        JLabel lblFechaNac = new JLabel("Fecha de nacimiento:");
        lblFechaNac.setAlignmentX(RIGHT_ALIGNMENT);
        JLabel lblContrasenia = new JLabel("Contraseña:");
        lblContrasenia.setAlignmentX(RIGHT_ALIGNMENT);
        JLabel lblConfirmContrasenia = new JLabel("Confirmar contraseña:");
        lblConfirmContrasenia.setAlignmentX(RIGHT_ALIGNMENT);
        //TEXT FIELDS PARA LA INFORMACION
        JTextField txtNoControl = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtApat = new JTextField();
        JTextField txtAmat = new JTextField();
        JTextField txtCarrera = new JTextField();
        JTextField txtContrasenia = new JTextField();
        JTextField txtConfirmContrasenia = new JTextField();
        //Date picker
            
        UtilDateModel model = new UtilDateModel();
        //model.setDate(20,04,2014);
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");

        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);

        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        //ADDING PANELS LABEL
        pnlLabels.add(lblNoControl);
        pnlLabels.add(Box.createVerticalStrut(20));
        pnlLabels.add(lblNombre);
        pnlLabels.add(Box.createVerticalStrut(20));
        pnlLabels.add(lblApat);
        pnlLabels.add(Box.createVerticalStrut(20));
        pnlLabels.add(lblAmat);
        pnlLabels.add(Box.createVerticalStrut(15));
        pnlLabels.add(lblCarrera);
        pnlLabels.add(Box.createVerticalStrut(25));
        pnlLabels.add(lblFechaNac);
        pnlLabels.add(Box.createVerticalStrut(20));
        pnlLabels.add(lblContrasenia);
        pnlLabels.add(Box.createVerticalStrut(20));
        pnlLabels.add(lblConfirmContrasenia);
        //ADDING PANELS DATA
        pnlData.add(txtNoControl);
        pnlData.add(Box.createVerticalStrut(15));
        pnlData.add(txtNombre);
        pnlData.add(Box.createVerticalStrut(15));
        pnlData.add(txtApat);
        pnlData.add(Box.createVerticalStrut(15));
        pnlData.add(txtAmat);
        pnlData.add(Box.createVerticalStrut(15));
        pnlData.add(txtCarrera);
        pnlData.add(Box.createVerticalStrut(15));
        pnlData.add(datePicker);
        pnlData.add(Box.createVerticalStrut(15));
        pnlData.add(txtContrasenia);
        pnlData.add(Box.createVerticalStrut(15));
        pnlData.add(txtConfirmContrasenia);

        //Agregando al panel principal
        pnlPrincipal.add(pnlLabels);
        pnlPrincipal.add(pnlData);
        
        //BOTONES ACEPTAR Y CANCELAR
                
        btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Fecha fechaNac = new Fecha(datePicker.getModel().getDay(),
                            datePicker.getModel().getMonth(),
                            datePicker.getModel().getYear());
                    
                    System.out.println("insert into alumno "
                            + "(id_alumno, nombre, apellidopaterno, apellidomaterno, fecha_nac, contrasenia, carrera_id) "
                            + "VALUES( "
                            + txtNoControl.getText() +", '"+txtNombre.getText()+"', '"+txtApat.getText()+"', '"
                            +txtAmat.getText()+"', '"+fechaNac.toSQLString()+"', '"+txtContrasenia.getText()+"', "
                            +txtCarrera.getText()
                            + " )");
                    PreparedStatement query = db.prepareStatement("insert into alumno "
                            + "(id_alumno, nombre, apellidopaterno, apellidomaterno, fecha_nac, contrasenia, carrera_id) "
                            + "VALUES( "
                            + txtNoControl.getText() +", '"+txtNombre.getText()+"', '"+txtApat.getText()+"', '"
                            +txtAmat.getText()+"', '"+fechaNac.toSQLString()+"', '"+txtContrasenia.getText()+"', "
                            +txtCarrera.getText()
                            + " )");
                        int resultado = query.executeUpdate();
                    if(resultado != 0){
                        JOptionPane.showMessageDialog(AgregarAlumnoDialog.this, "Correcto, alumno agregado", "Nice ", JOptionPane.INFORMATION_MESSAGE);
                    }
                    
                    AgregarAlumnoDialog.this.setVisible(false);
                } catch (FechaInvalidaException ex) {
                    Logger.getLogger(AgregarAlumnoDialog.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(AgregarAlumnoDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                   AgregarAlumnoDialog.this.setVisible(false);
            }
        });
        pnlBtns = new JPanel(new FlowLayout());
        pnlBtns.add(btnAceptar);
        pnlBtns.add(btnCancelar);
        super.add(pnlPrincipal);
        super.add(pnlBtns);
        super.setVisible(true);
    }
}
