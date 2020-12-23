package modelo;

public class Dentista {

    private String rut;
    private String nombre;
    private int edad;
    private String especialidad;

    public Dentista() {
        this.rut = "sin info";
        this.nombre = "sin info";
        this.edad = 18;
        this.especialidad = "sin info";
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

}
