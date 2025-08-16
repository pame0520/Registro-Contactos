/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import ui.MainFrame;

/**
 *
 * @author pame
 */
// clase principal que inicia la aplicacion
public class Main {
    public static void main(String[] args) {
        // iniciar ventana en el hilo de swing
        javax.swing.SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}