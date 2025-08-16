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
// clase que crea la ventana principal de la app
public class MainFrame extends JFrame {
    private final ContactoServicio servicio = new ContactoServicio(); // logica de negocio

    // componentes de la interfaz
    private JTextField txtNombre, txtTelefono, txtCorreo;
    private JButton btnAgregar, btnEliminar;
    private JTable tabla;
    private DefaultTableModel modelo;

    // constructor de la ventana
    public MainFrame() {
        setTitle("agenda de contactos"); // titulo
        setSize(600, 400); // tamaño ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE); // cerrar app al cerrar ventana
        setLocationRelativeTo(null); // centrar ventana
        initComponents(); // inicializar componentes
    }

    // metodo para crear y configurar los componentes
    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(null); // sin layout
        panel.setBackground(new Color(204, 153, 255)); // fondo morado suave

        // etiquetas
        JLabel lblNombre = new JLabel("nombre:");
        lblNombre.setBounds(20, 20, 80, 25);
        lblNombre.setForeground(Color.DARK_GRAY);
        panel.add(lblNombre);

        JLabel lblTelefono = new JLabel("telefono:");
        lblTelefono.setBounds(20, 60, 80, 25);
        lblTelefono.setForeground(Color.DARK_GRAY);
        panel.add(lblTelefono);

        JLabel lblCorreo = new JLabel("correo:");
        lblCorreo.setBounds(20, 100, 80, 25);
        lblCorreo.setForeground(Color.DARK_GRAY);
        panel.add(lblCorreo);

        // campos de texto
        txtNombre = new JTextField();
        txtNombre.setBounds(100, 20, 200, 25);
        panel.add(txtNombre);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(100, 60, 200, 25);
        panel.add(txtTelefono);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(100, 100, 200, 25);
        panel.add(txtCorreo);

        // botones
        btnAgregar = new JButton("agregar");
        btnAgregar.setBounds(320, 20, 100, 25);
        btnAgregar.setBackground(new Color(153, 102, 255));
        btnAgregar.setForeground(Color.WHITE);
        panel.add(btnAgregar);

        btnEliminar = new JButton("eliminar");
        btnEliminar.setBounds(320, 60, 100, 25);
        btnEliminar.setBackground(new Color(153, 102, 255));
        btnEliminar.setForeground(Color.WHITE);
        panel.add(btnEliminar);

        // tabla para mostrar contactos
        modelo = new DefaultTableModel(new Object[]{"id", "nombre", "telefono", "correo"}, 0);
        tabla = new JTable(modelo);
        tabla.setBackground(new Color(230, 204, 255)); // fondo tabla
        tabla.setForeground(Color.DARK_GRAY);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 150, 540, 200);
        panel.add(scroll);

        add(panel); // agregar panel a ventana

        // eventos de botones
        btnAgregar.addActionListener(e -> agregarContacto());
        btnEliminar.addActionListener(e -> eliminarContacto());
    }

    // metodo para agregar contacto desde los campos de texto
    private void agregarContacto() {
        try {
            Contacto c = servicio.agregarContacto(
                    txtNombre.getText(),
                    txtTelefono.getText(),
                    txtCorreo.getText()
            );
            modelo.addRow(new Object[]{c.getId(), c.getNombre(), c.getTelefono(), c.getCorreo()});
            limpiarCampos(); // limpiar campos despues de agregar
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "error: " + ex.getMessage(),
                    "validacion", JOptionPane.ERROR_MESSAGE);
        }
    }

    // metodo para eliminar contacto seleccionado
    private void eliminarContacto() {
        int fila = tabla.getSelectedRow();
        if (fila >= 0) {
            int id = (int) modelo.getValueAt(fila, 0);
            if (servicio.eliminarContacto(id)) {
                modelo.removeRow(fila); // quitar fila de la tabla
            }
        } else {
            JOptionPane.showMessageDialog(this, "seleccione un contacto para eliminar");
        }
    }

    // metodo para limpiar campos de texto
    private void limpiarCampos() {
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
    }
}