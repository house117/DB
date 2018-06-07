/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import Main.DB;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
/**
 *
 * @author House
 */
public class Conexion {
    private Connection conexion;
    public Conexion(Integer puerto){
        try {
            // TODO code application logic here
            /*
            UTILIZAMOS EL JDBC, LO DESCARGAMOS!
            EL CODIGO QUE VEMOS VIENE EN LA PAGINA
            DE POSTGRES
            INVESTIGAR LA CADENA DE CONEXION Y EL
            NOMBRE DE LA CLASE
            crear clase conexion
            constructor privado
            Conexion
            Class.forName("org.postgresql...
            this.conexion
            ----------
            
            */
        Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        String url = "jdbc:postgresql://localhost:5432/ControlEscolar";
        try {
            conexion = DriverManager.getConnection(url, "House", "system");
            
            //PreparedStatement query = conexion.prepareStatement("select * from carrera");
            //ResultSet resultado = query.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Connection getConexion(){
        return this.conexion;
    }
}
