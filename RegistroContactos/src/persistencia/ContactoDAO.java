/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import dominio.Contacto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author pame
 */
public class ContactoDAO {
    private final List<Contacto> contactos = new ArrayList<>();
    private int secuenciaId = 1;

    // Simula INSERT
    public Contacto agregar(String nombre, String telefono, String correo) {
        Contacto nuevo = new Contacto(secuenciaId++, nombre, telefono, correo);
        contactos.add(nuevo);
        return nuevo;
    }

    // Simula SELECT *
    public List<Contacto> obtenerTodos() {
        return new ArrayList<>(contactos);
    }

    // Simula DELETE
    public boolean eliminar(int id) {
        return contactos.removeIf(c -> c.getId() == id);
    }

    // Simula UPDATE
    public boolean actualizar(Contacto actualizado) {
        Optional<Contacto> existente = contactos.stream()
                .filter(c -> c.getId() == actualizado.getId())
                .findFirst();
        if (existente.isPresent()) {
            Contacto c = existente.get();
            c.setNombre(actualizado.getNombre());
            c.setTelefono(actualizado.getTelefono());
            c.setCorreo(actualizado.getCorreo());
            return true;
        }
        return false;
    }
}
