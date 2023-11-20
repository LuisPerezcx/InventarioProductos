package Controlador;

import Media.MiImagen;
import Modelo.Usuarios;
import Util.Util;
import Vista.VistaAdministrador;
import Vista.VistaLogin;
import Vista.VistaPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorLogin extends CRUD implements ActionListener {
    private final VistaLogin vistaLogin;
    private final VistaPrincipal vistaPrincipal;
    public ControladorLogin(VistaLogin vistaLogin, VistaPrincipal vistaPrincipal) {
        this.vistaLogin = vistaLogin;
        this.vistaPrincipal = vistaPrincipal;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object event = e.getSource();
        if (event == vistaLogin.btnRegresar){
            vistaLogin.setVisible(false);
            //vistaLogin.dispose();
            vistaPrincipal.setVisible(true);
            vistaPrincipal.initControl();
        }else if(event == vistaLogin.btnAceptar){
            if (Util.usuariosArrayList.isEmpty() || !vistaLogin.btnRegistrarse.isVisible()){
                primerUsuario();
            }else validarLogin();
        } else if (event == vistaLogin.btnRegistrarse) {
            registrarUsuario();
        }
    }
    private void validarLogin(){
        String usuario = vistaLogin.txtUsuario.getText();
        String contrasena = vistaLogin.txtContrasena.getText();
        for (Usuarios usuarios : Util.usuariosArrayList){
            String usuarioValido = usuarios.getUsuario();
            String contrasenaValida = usuarios.getContrasena();
            if (usuario.equals(usuarioValido) && contrasena.equals(contrasenaValida)){
                vistaLogin.setVisible(false);
                new VistaAdministrador(new MiImagen(330,0),vistaLogin);
                return;
            }
        }
        JOptionPane.showMessageDialog(vistaLogin,"Credenciales invalidas",":(",JOptionPane.ERROR_MESSAGE);
    }
    private void registrarUsuario(){
        vistaLogin.titulo.setText("REGISTRAR USUARIO");
        vistaLogin.btnRegistrarse.setVisible(false);
        String usuario = vistaLogin.txtUsuario.getText();
        String contrasena = vistaLogin.txtContrasena.getText();
        if (!usuario.isEmpty() && !contrasena.isEmpty()){
            registrarUsuario();
            vistaLogin.btnRegistrarse.setVisible(true);
        }
    }
    private void primerUsuario(){
        agregarUsuario();
        vistaLogin.txtUsuario.setText("");
        vistaLogin.txtContrasena.setText("");
    }
    private void agregarUsuario(){
        String usuario = vistaLogin.txtUsuario.getText();
        String contrasena = vistaLogin.txtContrasena.getText();
        if (!usuario.isEmpty() && !contrasena.isEmpty()){
            agregar(Util.usuariosArrayList, new Usuarios(usuario,contrasena));
            vistaLogin.btnRegistrarse.setVisible(true);
            JOptionPane.showMessageDialog(vistaLogin,"Usuario Registrado");
            vistaLogin.titulo.setText("INICIE SESION");
        }else JOptionPane.showMessageDialog(vistaLogin,"No debe haber campos vacios");
    }
}
