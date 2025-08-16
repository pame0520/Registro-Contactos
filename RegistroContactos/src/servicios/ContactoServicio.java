/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import dominio.Contacto;
import java.util.List;
import persistencia.ContactoDAO;

/**
 *
 * @author pame
 */
public class ContactoServicio {
    private final ContactoDAO dao = new ContactoDAO();

    public Contacto agregarContacto(String nombre, String telefono, String correo) throws Exception {
        if (nombre == null || nombre.isBlank()) {
            throw new Exception("El nombre no puede estar vacío");
        }
        if (telefono == null || telefono.isBlank()) {
            throw new Exception("El teléfono no puede estar vacío");
        }
        return dao.agregar(nombre, telefono, correo);
    }

    public List<Contacto> listarContactos() {
        return dao.obtenerTodos();
    }

    public boolean eliminarContacto(int id) {
        return dao.eliminar(id);
    }

    public boolean actualizarContacto(Contacto contacto) {
        return dao.actualizar(contacto);
    }
}
