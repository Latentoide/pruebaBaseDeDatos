package es.ieslavereda.model;

public class Empleado {
    private String dni;
    private String nombre, apellidos, password;

    public Empleado(String dni, String nombre, String apellidos, String password) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.password = password;
    }

    public Empleado(){

    }

    public String getPassword() {
        return password;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "dni=" + dni +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                '}' + "\n";
    }
}
