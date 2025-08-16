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
// clase que simula la base de datos en memoria
public class ContactoDAO {
    private final List<Contacto> contactos = new ArrayList<>(); // lista para guardar contactos
    private int secuenciaId = 1; // id automatico

    // metodo para agregar contacto
    public Contacto agregar(String nombre, String telefono, String correo) {
        Contacto nuevo = new Contacto(secuenciaId++, nombre, telefono, correo);
        contactos.add(nuevo); // agregar a la lista
        return nuevo;
    }

    // metodo para obtener todos los contactos
    public List<Contacto> obtenerTodos() {
        return new ArrayList<>(contactos); // devuelve copia de la lista
    }

    // metodo para eliminar un contacto por id
    public boolean eliminar(int id) {
        return contactos.removeIf(c -> c.getId() == id); // eliminar si id coincide
    }

    // metodo para actualizar un contacto existente
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