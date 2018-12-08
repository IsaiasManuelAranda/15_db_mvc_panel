/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import db.Mysql;
import views.ViewAgenda;

/**
 *
 * @author Salvador Hernandez Mendoza
 */
public class ModelAgenda {

    private Connection conexion;
    private Statement st;
    private ResultSet rs;
    public PreparedStatement pst;
    ViewAgenda viewAgenda = new ViewAgenda();

    public String nombre;
    public String email;
    public String tel;
    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTel(){
        return tel;
    }
    
    public void setTel(String tel){
        this.tel = tel;
    }

    /**
     * Método que realiza las siguietnes acciones:
     * 1.- Conecta con la base agenda_mvc.
     * 2.- Consulta todo los registros de la tabla contactos.
     * 3.- Obtiene el nombre y el email y los guarda en las variables globales
     * nombre, email y telefono.
     */
    public void conectarDB() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda_mvc", "user_mvc", "pass_mvc.2018");
            st = conexion.createStatement();
            rs = st.executeQuery("SELECT * FROM contactos;");
            rs.next();
            nombre = rs.getString("nombre");
            email = rs.getString("email");
            tel = rs.getString("tel");
            id = rs.getString("id_contacto");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error ModelAgenda 001: " + err.getMessage());
        }

    }
    
    /**
     * Método que realiza las siguiente acciones:
     * 1.- Moverse al primer registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     * 4.- obtener el valior del telefono de rs y guardarlo en la variable tel
     */
    public void moverPrimerRegistro(){
        try {
            rs.first();
            nombre = rs.getString("nombre");
            email = rs.getString("email");
            tel = rs.getString("tel");
            id = rs.getString("id_contacto");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }
    
    /**
     * Método que realiza las siguiente acciones:
     * 1.- Moverse al siguiente registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     * 4.- obtener el valior del telefono de rs y guardarlo en la variable tel
     */
    public void moverSiguienteRegistro(){
        try {
            if (!rs.isLast()){
                rs.next();
            }
            nombre = rs.getString("nombre");
            email = rs.getString("email");
            tel = rs.getString("tel");
            id = rs.getString("id_contacto");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    /**
     * Método que realiza las siguiente acciones:
     * 1.- Moverse al anterior registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     * 4.- obtener el valior del telefono de rs y guardarlo en la variable tel
     */
    public void moverAnteriorRegistro(){
        try {
            if (!rs.isFirst()){
                rs.previous();
            }
            nombre = rs.getString("nombre");
            email = rs.getString("email");
            tel = rs.getString("tel");
            id = rs.getString("id_contacto");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    /**
     * Método que realiza las siguiente acciones:
     * 1.- Moverse al ultimo registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     * 4.- obtener el valior del telefono de rs y guardarlo en la variable tel
     */
    public void moverUltimoRegistro(){
        try {
            rs.last();
            nombre = rs.getString("nombre");
            email = rs.getString("email");
            tel = rs.getString("tel");
            id = rs.getString("id_contacto");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public void addRegister(){
        String insert = ("INSERT INTO contactos (nombre,email,tel) VALUES (?,?,?);");
        Mysql mysql = new Mysql();
        Connection con = mysql.getConnection();
        try {
            pst = (PreparedStatement) con.prepareStatement(insert);            
            pst.setString(1, this.getNombre());
            pst.setString(2, this.getEmail());
            pst.setString(3, this.getTel());
            
            if (this.getNombre().isEmpty() || 
                this.getEmail().isEmpty() || 
                this.getTel().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Los campos no deben quedar vacíos");
            }else{
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se insertó el registro");
            }
        }catch(SQLException ex){ 
            JOptionPane.showMessageDialog(null, "No se pudo actualizar");
        }
    }
    
    public void modifyRegister(){
        String update = ("UPDATE contactos SET nombre=?, email=?, tel =? WHERE id_contacto=?;");
        Mysql mysql = new Mysql();
        Connection con = mysql.getConnection();
        try {
            pst = (PreparedStatement) con.prepareStatement(update);            
            pst.setString(1, this.getNombre());
            pst.setString(2, this.getEmail());
            pst.setString(3, this.getTel());
            pst.setString(4, this.getId());
            
            if (this.getNombre().isEmpty() || 
                this.getEmail().isEmpty() || 
                this.getTel().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Los campos no deben quedar vacíos");
            }else{
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se actualizó el registro");
            }
        }catch(SQLException ex){ 
            JOptionPane.showMessageDialog(null, "No se pudo actualizar");
        }
    }
    
    public void deleteRegister(){
        try {            
            int eliminar = JOptionPane.showConfirmDialog(null, "Quieres eliminar este registro?", "Eliminar Registro", JOptionPane.YES_NO_OPTION);
            if(eliminar == 0){  
                String delete = ("DELETE FROM contactos WHERE id_contacto=?;");
                Mysql mysql = new Mysql();
                Connection con = mysql.getConnection();
                pst = (PreparedStatement) con.prepareStatement(delete);
                pst.setString(1, this.getId());
                if (this.getId() == "0"){
                    JOptionPane.showMessageDialog(null, "No se puede eliminar el registro");
                }else{
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Se eliminó el registro");
                }
            } else {
                
            }           
        } catch(SQLException ex){ 
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro");
        }
    }
}