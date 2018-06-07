/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import gui.MainFrame;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author House
 */
public class DB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        MainFrame ventana;
        try {
            ventana = new MainFrame("Administración alumnitoss");
            ventana.setVisible(true);
        } catch (HeadlessException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        /*try {
            // TODO code application logic here
            
            UTILIZAMOS EL JDBC, LO DESCARGAMOS!
            EL CODIGO QUE VEMOS VIENE EN LA PAGINA
            DE POSTGRES
            INVESTIGAR LA CADENA DE CONEXION Y EL
            NOMBRE DE LA CLASE
            crear clase conexion
            constructor privado
            Connection
            Class.forName("org.postgresql...
            this.conexion
            ----------
            
            
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        String url = "jdbc:postgresql://localhost:5432/ControlEscolar";
        try {
            Connection conexion = DriverManager.getConnection(url, "House", "system");
            
            PreparedStatement query = conexion.prepareStatement("select * from carrera");
            ResultSet resultado = query.executeQuery();
            
            while(resultado.next()){
                System.out.printf("%d : %s\n", resultado.getInt(1), resultado.getString(2));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
}
