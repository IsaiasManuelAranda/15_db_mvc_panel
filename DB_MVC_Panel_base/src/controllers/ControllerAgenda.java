/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.ModelAgenda;
import views.ViewAgenda;

/**
 *
 * @author Salvador Hernandez Mendoza & Isaías Aranda
 */
public class ControllerAgenda {

    ModelAgenda modelAgenda;
    ViewAgenda viewAgenda;

    /**
     * Objeto de tipo ActionListener para atrapar los eventos actionPerformed y
     * llamar a los metodos para ver los registros almacenados en la bd.
     */
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewAgenda.jbtn_primero) {
                jbtn_primero_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_anterior) {
                jbtn_anterior_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_siguiente) {
                jbtn_siguiente_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_ultimo) {
                jbtn_ultimo_actionPerformed();
            }else if (e.getSource() == viewAgenda.jb_add) {
                modelAgenda.setNombre(viewAgenda.jtf_nombre.getText());
                modelAgenda.setEmail(viewAgenda.jtf_email.getText());
                modelAgenda.setTel(viewAgenda.jtf_tel.getText());
                modelAgenda.addRegister();
                
                modelAgenda.conectarDB();
            }else if (e.getSource() == viewAgenda.jb_modify) {
                modelAgenda.setNombre(viewAgenda.jtf_nombre.getText());
                modelAgenda.setEmail(viewAgenda.jtf_email.getText());
                modelAgenda.setTel(viewAgenda.jtf_tel.getText());
                modelAgenda.setId(viewAgenda.jl_id.getText());
                modelAgenda.modifyRegister();
                
                modelAgenda.conectarDB();
            }else if (e.getSource() == viewAgenda.jb_delete) {
                modelAgenda.setId(viewAgenda.jl_id.getText());
                modelAgenda.deleteRegister();
                modelAgenda.conectarDB();
            }else if (e.getSource() == viewAgenda.jb_new) {
                viewAgenda.jtf_nombre.setText("");
                viewAgenda.jtf_email.setText("");
                viewAgenda.jtf_tel.setText("");
                viewAgenda.jl_id.setText("0");
            }

        }

    };

    /**
     * Constructor de Controlador para unir el ModelAgenda y ViewAgenda
     *
     * @param modelAgenda objeto de tipo ModelAgenda
     * @param viewAgenda objeto de tipo ViewAgenda
     */
    public ControllerAgenda(ModelAgenda modelAgenda, ViewAgenda viewAgenda) {
        this.modelAgenda = modelAgenda;
        this.viewAgenda = viewAgenda;
        initComponents();
        setActionListener();
        initDB();
    }

    /**
     * Método que llama al método conectarBD del modelo y muestra el nombre y
     * email del primer registro en las cajas de texto de ViewAgenda.
     */
    public void initDB(){
        modelAgenda.conectarDB();
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
        viewAgenda.jtf_tel.setText(modelAgenda.getTel());
        viewAgenda.jl_id.setText(modelAgenda.getId());
    }
    /**
     * Metodo para inicializar la ViewAgenda
     */
    public void initComponents() {
        viewAgenda.setVisible(true);
    }

    /**
     * Método para agregar el actionListener a cada boton de la vista
     */
    public void setActionListener() {
        viewAgenda.jbtn_primero.addActionListener(actionListener);
        viewAgenda.jbtn_anterior.addActionListener(actionListener);
        viewAgenda.jbtn_siguiente.addActionListener(actionListener);
        viewAgenda.jbtn_ultimo.addActionListener(actionListener);
        viewAgenda.jb_add.addActionListener(actionListener);
        viewAgenda.jb_modify.addActionListener(actionListener);
        viewAgenda.jb_delete.addActionListener(actionListener);
        viewAgenda.jb_new.addActionListener(actionListener);
    }

    /**
     * Método para ver el primer registro de la tabla contactos
     */
    private void jbtn_primero_actionPerformed() {
        
        modelAgenda.moverPrimerRegistro();
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
        viewAgenda.jtf_tel.setText(modelAgenda.getTel());
        viewAgenda.jl_id.setText(modelAgenda.getId());
    }

    /**
     * Método para ver el registro anterior de la tabla contactos
     */
    private void jbtn_anterior_actionPerformed() {
        
        modelAgenda.moverAnteriorRegistro();
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
        viewAgenda.jtf_tel.setText(modelAgenda.getTel());
        viewAgenda.jl_id.setText(modelAgenda.getId());
        
    }

    /**
     * Método para ver el último registro de la tabla contactos
     */
    private void jbtn_ultimo_actionPerformed() {
        
        modelAgenda.moverUltimoRegistro();
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
        viewAgenda.jtf_tel.setText(modelAgenda.getTel());
        viewAgenda.jl_id.setText(modelAgenda.getId());
        
    }

    /**
     * Método para ver el siguiente registro de la tabla contactos
     */
    private void jbtn_siguiente_actionPerformed() {
        
        modelAgenda.moverSiguienteRegistro();
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
        viewAgenda.jtf_tel.setText(modelAgenda.getTel());
        viewAgenda.jl_id.setText(modelAgenda.getId());
        
    }
}
