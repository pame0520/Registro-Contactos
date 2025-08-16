/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author pame
 */
// clase que representa un contacto
public class Contacto {
    // atributos del contacto
    private int id;
    private String nombre;
    private String telefono;
    private String correo;

    // constructor para crear un contacto
    public Contacto(int id, String nombre, String telefono, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

    // getter y setter de id
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    // getter y setter de nombre
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    // getter y setter de telefono
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    // getter y setter de correo
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    // metodo para mostrar el contacto como texto
    @Override
    public String toString() {
        return id + " - " + nombre + " (" + telefono + ", " + correo + ")";
    }
}