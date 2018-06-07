/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import db.Conexion;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author House
 */
public class MainFrame extends JFrame{
    private TablePanel pnlTabla;
    private Conexion conexion;
    private Connection dataBase;
    
    public MainFrame(String string) throws HeadlessException, SQLException {
        super(string);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setSize(800, 600);
        super.setLayout(new BorderLayout());
        super.setLocationRelativeTo(null);
        super.setJMenuBar(createMenu());
        this.conexion = new Conexion(100);
        dataBase = conexion.getConexion();
        
        PreparedStatement query = dataBase.prepareStatement("select * from alumno");
        ResultSet resultado = query.executeQuery();
        pnlTabla = new TablePanel(resultado);
        super.add(pnlTabla, BorderLayout.CENTER);
                
    }
    private JMenuBar createMenu(){
        JMenuBar menu = new JMenuBar();
        
        //MENÚ ALUMNO
        JMenu mmAlumno = new JMenu("Alumno");
        
        //SUBMENÚS DE ALUMNO
        JMenuItem nnBuscar = new JMenuItem("Buscar...");
        nnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                BuscarAlumno busqueda = new BuscarAlumno(MainFrame.this, dataBase);
            }
        });//ADDS A MENU ALUMNO
        JMenuItem nnAgregarAlumno = new JMenuItem("Agregar alumno...");
        nnAgregarAlumno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                AgregarAlumnoDialog agregar = new AgregarAlumnoDialog(MainFrame.this, dataBase);
            }
        });
        JMenuItem nnEditarAlumno = new JMenuItem("Editar alumno...");
        nnEditarAlumno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JTextField cantMaterias = new JTextField(5);
                                JPanel pnlMaterias = new JPanel();
   
                                pnlMaterias.add(new JLabel("ALUMNO A MODIFICAR:"));
                                pnlMaterias.add(cantMaterias);
                                
                                int resultMaterias = JOptionPane.showConfirmDialog(null, pnlMaterias, "Ingresa el ID del alumno",
                                    JOptionPane.OK_CANCEL_OPTION); //Con esto tenemos un boton de ok/cancel, muy util
                                if(resultMaterias == JOptionPane.OK_OPTION){
                    try {
                        ModificarAlumnoDialog modificar = new ModificarAlumnoDialog(MainFrame.this,
                                dataBase, cantMaterias.getText());
                    } catch (SQLException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                                }
            }
        });
        //ADDS A MMALUMNO
        
        mmAlumno.add(nnBuscar);
        mmAlumno.add(nnAgregarAlumno);
        mmAlumno.add(nnEditarAlumno);
        
        
        
        
        //AGREGANDO MENUS AL JMENU
        menu.add(mmAlumno);
        return menu;
    }
    
}
