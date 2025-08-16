/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import dominio.Contacto;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import servicios.ContactoServicio;

/**
 *
 * @author pame
 */
public class MainFrame extends JFrame {
    private final ContactoServicio servicio = new ContactoServicio();

    private JTextField txtNombre, txtTelefono, txtCorreo;
    private JButton btnAgregar, btnEliminar;
    private JTable tabla;
    private DefaultTableModel modelo;

    public MainFrame() {
        setTitle("Agenda de Contactos");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        // Panel principal con color morado suave
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(204, 153, 255)); // morado suave

        // Labels
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 20, 80, 25);
        lblNombre.setForeground(Color.DARK_GRAY);
        panel.add(lblNombre);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(20, 60, 80, 25);
        lblTelefono.setForeground(Color.DARK_GRAY);
        panel.add(lblTelefono);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(20, 100, 80, 25);
        lblCorreo.setForeground(Color.DARK_GRAY);
        panel.add(lblCorreo);

        // TextFields
        txtNombre = new JTextField();
        txtNombre.setBounds(100, 20, 200, 25);
        panel.add(txtNombre);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(100, 60, 200, 25);
        panel.add(txtTelefono);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(100, 100, 200, 25);
        panel.add(txtCorreo);

        // Botones con colores combinando
        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(320, 20, 100, 25);
        btnAgregar.setBackground(new Color(153, 102, 255));
        btnAgregar.setForeground(Color.WHITE);
        panel.add(btnAgregar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(320, 60, 100, 25);
        btnEliminar.setBackground(new Color(153, 102, 255));
        btnEliminar.setForeground(Color.WHITE);
        panel.add(btnEliminar);

        // Tabla
        modelo = new DefaultTableModel(new Object[]{"ID", "Nombre", "Teléfono", "Correo"}, 0);
        tabla = new JTable(modelo);
        tabla.setBackground(new Color(230, 204, 255)); // morado muy claro
        tabla.setForeground(Color.DARK_GRAY);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 150, 540, 200);
        panel.add(scroll);

        add(panel);

        // Eventos
        btnAgregar.addActionListener(e -> agregarContacto());
        btnEliminar.addActionListener(e -> eliminarContacto());
    }

    private void agregarContacto() {
        try {
            Contacto c = servicio.agregarContacto(
                    txtNombre.getText(),
                    txtTelefono.getText(),
                    txtCorreo.getText()
            );
            modelo.addRow(new Object[]{c.getId(), c.getNombre(), c.getTelefono(), c.getCorreo()});
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(),
                    "Validación", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarContacto() {
        int fila = tabla.getSelectedRow();
        if (fila >= 0) {
            int id = (int) modelo.getValueAt(fila, 0);
            if (servicio.eliminarContacto(id)) {
                modelo.removeRow(fila);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un contacto para eliminar");
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
    }
}