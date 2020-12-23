package modelo;

public class Paciente {

    private String rut;
    private String nombre;
    private int edad;
    private int celular;
    private String email;

    public Paciente() {
        this.rut = "sin info";
        this.nombre = "sin info";
        this.edad = 18;
        this.celular = 0;
        this.email = "sin info";
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
