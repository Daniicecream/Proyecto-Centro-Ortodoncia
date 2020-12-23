package controlador;

import modelo.*;
import java.sql.*;
import bd.Conexion;
import java.util.ArrayList;

public class AgendaAtenciones {

    ////////////////////////////////////////////METODOS PARA AGREGAR
    /**
     * Este metodo permite registrar una cita al dentista en la base de datos,
     * ingresa los datos del paciente a la vez que ingresa los datos de la
     * atencion
     */
    public boolean agregarAtencion(Paciente paciente, Atencion atencion) {

        boolean chkpac = false;
        boolean chkatn = false;
        Connection conexion = Conexion.getConexion();
        try {

            //CARGA DE DATOS PACIENTE
            String query = "INSERT INTO PACIENTE(rut,nombre,edad,celular,email) VALUES(?,?,?,?,?)";
            PreparedStatement ins = conexion.prepareStatement(query);
            ins.setString(1, paciente.getRut());
            ins.setString(2, paciente.getNombre());
            ins.setInt(3, paciente.getEdad());
            ins.setInt(4, paciente.getCelular());
            ins.setString(5, paciente.getEmail());

            if (ins.executeUpdate() > 0) {
                chkpac = true;
            }
        } catch (Exception e) {
            System.out.println("Error en la ejecucion del metodo agregar paciente: " + e.getMessage());
        }

        try {
            //CARGA DE DATOS ATENCION
            String query2 = "INSERT INTO ATENCION(paciente,fecha,hora,descripcion,costo,dentista) VALUES(?,?,?,?,?,?)";
            PreparedStatement ins2 = conexion.prepareStatement(query2);
            ins2.setString(1, atencion.getPaciente().getNombre());
            ins2.setString(2, atencion.getFecha());
            ins2.setInt(3, atencion.getHora());
            ins2.setString(4, atencion.getDescripcion());
            ins2.setInt(5, atencion.getCosto());
            ins2.setString(6, atencion.getDentista().getNombre());

            if (ins2.executeUpdate() > 0) {
                chkatn = true;
            }
        } catch (Exception e) {
            System.out.println("Error en la ejecucion del metodo agregar cita: " + e.getMessage());
        }

        if (chkpac == true && chkatn == true) {
            return true;
        }
        return false;
    }

    /**
     * Este metodo permite omitir el registro de un paciente que ya esta
     * registrado en la base de datos, de esta manera solo ingresara la nueva
     * atencion
     */
    public boolean agregarSoloAtencion(Atencion atencion) {

        Connection conexion = Conexion.getConexion();
        try {
            //CARGA DE DATOS ATENCION
            String query2 = "INSERT INTO ATENCION(paciente,fecha,hora,descripcion,costo,dentista) VALUES(?,?,?,?,?,?)";
            PreparedStatement ins2 = conexion.prepareStatement(query2);

            ins2.setString(1, atencion.getPaciente().getNombre());
            ins2.setString(2, atencion.getFecha());
            ins2.setInt(3, atencion.getHora());
            ins2.setString(4, atencion.getDescripcion());
            ins2.setInt(5, atencion.getCosto());
            ins2.setString(6, atencion.getDentista().getNombre());

            if (ins2.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error en la ejecucion del metodo agregar solo cita: " + e.getMessage());
        }
        return false;
    }

    /**
     * Este metodo permite registrar los datos de un nuevo dentista en la base
     * de datos
     */
    public boolean agregarDentista(Dentista dentista) {

        Connection conexion = Conexion.getConexion();
        try {
            //CARGA DE DATOS DENTISTA
            String query2 = "INSERT INTO DENTISTA(rut,nombre,edad,especialidad) VALUES(?,?,?,?)";
            PreparedStatement ins = conexion.prepareStatement(query2);
            ins.setString(1, dentista.getRut());
            ins.setString(2, dentista.getNombre());
            ins.setInt(3, dentista.getEdad());
            ins.setString(4, dentista.getEspecialidad());

            if (ins.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error en la ejecucion del metodo agregar dentista: " + e.getMessage());
        }
        return false;
    }

    ///////////////////////////////////////////////METODOS DE BUSQUEDA
    /**
     * Este metodo busca todas las atenciones registradas en la base de datos
     */
    public ArrayList<Atencion> buscarTodasAtenciones() {
        ArrayList<Atencion> lista = new ArrayList<>();
        try {
            Connection conexion = Conexion.getConexion();
            String query = "SELECT * FROM ATENCION ORDER BY FECHA ASC";
            PreparedStatement bus = conexion.prepareStatement(query);
            ResultSet rs = bus.executeQuery();
            while (rs.next()) {
                Atencion ate = new Atencion();
                ate.setFolio(rs.getInt("folio"));
                ate.getPaciente().setNombre(rs.getString("paciente"));
                ate.setFecha(rs.getString("fecha"));
                ate.setHora(rs.getInt("hora"));
                ate.setDescripcion(rs.getString("descripcion"));
                ate.setCosto(rs.getInt("costo"));
                ate.getDentista().setNombre(rs.getString("dentista"));
                lista.add(ate);
            }
        } catch (Exception e) {
            System.out.println("Error al ejecutar metodo buscar-todo: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Este metodo recibe por parametro de entrada la fecha de una atencion a
     * buscar y Retorna una colección, es decir, un ArrayList el cual contiene
     * los registros que cumplieron la query
     */
    public ArrayList<Atencion> buscarPorFecha(String fecha) {
        ArrayList<Atencion> lista = new ArrayList<>();
        try {
            Connection conexion = Conexion.getConexion();
            String query = "SELECT * FROM ATENCION WHERE FECHA = ?";
            PreparedStatement bus = conexion.prepareStatement(query);
            bus.setString(1, fecha);

            ResultSet rs = bus.executeQuery();
            while (rs.next()) {
                Atencion ate = new Atencion();
                ate.setFolio(rs.getInt("folio"));
                ate.getPaciente().setNombre(rs.getString("paciente"));
                ate.setFecha(rs.getString("fecha"));
                ate.setHora(rs.getInt("hora"));
                ate.setDescripcion(rs.getString("descripcion"));
                ate.setCosto(rs.getInt("costo"));
                ate.getDentista().setNombre(rs.getString("dentista"));
                lista.add(ate);
            }
        } catch (Exception e) {
            System.out.println("Error al ejecutar el metodo buscar por fecha: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Este metodo recibe por parametro de entrada el rut de un dentista a
     * buscar y Retorna una colección, es decir, un ArrayList el cual contiene
     * los registros que cumplieron la query
     */
    public ArrayList<Dentista> buscarDentistas(String rut) {
        ArrayList<Dentista> lista = new ArrayList<>();

        if (rut.length() == 0) { //Si el string por parametro esta vacio muestra todos los dentistas
            try {
                Connection conexion = Conexion.getConexion();
                String query = "SELECT * FROM DENTISTA ORDER BY NOMBRE ASC";
                PreparedStatement bus = conexion.prepareStatement(query);

                ResultSet rs = bus.executeQuery();
                while (rs.next()) {
                    Dentista dent = new Dentista();
                    dent.setRut(rs.getString("rut"));
                    dent.setNombre(rs.getString("nombre"));
                    dent.setEdad(rs.getInt("edad"));
                    dent.setEspecialidad(rs.getString("especialidad"));
                    lista.add(dent);
                }
            } catch (Exception e) {
                System.out.println("Error al ejecutar el metodo buscar dentista-todos: " + e.getMessage());
            }
        } else { //Sino muestra el dentista que coincida con el rut ingresado
            try {
                Connection conexion = Conexion.getConexion();
                String query = "SELECT * FROM DENTISTA WHERE RUT = ?";
                PreparedStatement bus = conexion.prepareStatement(query);
                bus.setString(1, rut);

                ResultSet rs = bus.executeQuery();
                while (rs.next()) {
                    Dentista dent = new Dentista();
                    dent.setRut(rs.getString("rut"));
                    dent.setNombre(rs.getString("nombre"));
                    dent.setEdad(rs.getInt("edad"));
                    dent.setEspecialidad(rs.getString("especialidad"));
                    lista.add(dent);
                }

            } catch (Exception e) {
                System.out.println("Error al ejecutar el metodo buscar dentista: " + e.getMessage());
            }
        }
        return lista;
    }

    /**
     * Este metodo recibe por parametro de entrada el rut de un paciente a
     * buscar y Retorna una colección, es decir, un ArrayList el cual contiene
     * los registros que cumplieron la query
     */
    public ArrayList<Paciente> buscarPacientes(String rut) {
        ArrayList<Paciente> lista = new ArrayList<>();

        if (rut.length() == 0) { //Si el string por parametro esta vacio muestra todos los pacientes
            try {
                Connection conexion = Conexion.getConexion();
                String query = "SELECT * FROM PACIENTE ORDER BY NOMBRE ASC";
                PreparedStatement bus = conexion.prepareStatement(query);

                ResultSet rs = bus.executeQuery();
                while (rs.next()) {
                    Paciente pac = new Paciente();
                    pac.setRut(rs.getString("rut"));
                    pac.setNombre(rs.getString("nombre"));
                    pac.setEdad(rs.getInt("edad"));
                    pac.setCelular(rs.getInt("celular"));
                    pac.setEmail(rs.getString("email"));
                    lista.add(pac);
                }
            } catch (Exception e) {
                System.out.println("Error al ejecutar el metodo buscar paciente-todos: " + e.getMessage());
            }
        } else { //Sino muestra el paciente que coincida con el rut ingresado
            try {
                Connection conexion = Conexion.getConexion();
                String query = "SELECT * FROM PACIENTE WHERE RUT = ?";
                PreparedStatement bus = conexion.prepareStatement(query);
                bus.setString(1, rut);

                ResultSet rs = bus.executeQuery();
                while (rs.next()) {
                    Paciente pac = new Paciente();
                    pac.setRut(rs.getString("rut"));
                    pac.setNombre(rs.getString("nombre"));
                    pac.setEdad(rs.getInt("edad"));
                    pac.setCelular(rs.getInt("celular"));
                    pac.setEmail(rs.getString("email"));
                    lista.add(pac);
                }

            } catch (Exception e) {
                System.out.println("Error al ejecutar el metodo buscar paciente: " + e.getMessage());
            }
        }
        return lista;
    }

    //////////////////////////////////////////////////METODOS PARA ELIMINAR
    /**
     * Este metodo nos permite eliminar a un paciente de la base de datos luego
     * de buscarlo por su rut
     */
    public boolean eliminarPaciente(String rut) {
        try {
            Connection conexion = Conexion.getConexion();
            String query = "DELETE FROM PACIENTE WHERE RUT = ?";
            PreparedStatement del = conexion.prepareStatement(query);

            del.setString(1, rut);

            if (del.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error ejecutando metodo eliminar paciente" + e.getMessage());
        }
        return false;
    }

    /**
     * Este metodo nos permite eliminar a un dentista de la base de datos luego
     * de buscarlo por su rut
     */
    public boolean eliminarDentista(String rut) {
        try {
            Connection conexion = Conexion.getConexion();
            String query = "DELETE FROM DENTISTA WHERE RUT = ?";
            PreparedStatement del = conexion.prepareStatement(query);

            del.setString(1, rut);

            if (del.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error ejecutando metodo eliminar dentista" + e.getMessage());
        }
        return false;
    }

    /**
     * Este metodo nos permite eliminar una atencion de la base de datos luego
     * de buscarlo por su folio
     */
    public boolean eliminarAtencion(String folio) {
        try {
            Connection conexion = Conexion.getConexion();
            String query = "DELETE FROM ATENCION WHERE FOLIO = ?";
            PreparedStatement del = conexion.prepareStatement(query);

            del.setString(1, folio);

            if (del.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error ejecutando metodo eliminar atencion" + e.getMessage());
        }
        return false;
    }

    /////////////////////////////////////////////////METODOS PARA VALIDAR 
    /**
     * Este metodo valida si ya se encuentra registrado el rut de un paciente,
     * retorna true si ya se encuentra registrado, caso contrario false
     */
    public boolean existeRut(String rut) {
        try {
            Connection conexion = Conexion.getConexion();
            String query = "SELECT * FROM PACIENTE WHERE RUT=?";
            PreparedStatement bus = conexion.prepareStatement(query);
            bus.setString(1, rut);

            ResultSet rs = bus.executeQuery();
            //next() retorna un boolean, si es true hay elementos en el resultset,
            //false si no hay elementos
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error al validar" + e.getMessage());
        }
        return false;
    }

    /**
     * Este metodo valida si ya se encuentra registrado el rut de un dentista,
     * retorna true si ya se encuentra registrado, caso contrario false
     */
    public boolean existeDentista(String rut) {
        try {
            Connection conexion = Conexion.getConexion();
            String query = "SELECT * FROM DENTISTA WHERE RUT=?";
            PreparedStatement bus = conexion.prepareStatement(query);
            bus.setString(1, rut);

            ResultSet rs = bus.executeQuery();
            //next() retorna un boolean, si es true hay elementos en el resultset,
            //false si no hay elementos
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error al validar" + e.getMessage());
        }
        return false;
    }

    /**
     * Este metodo valida si ya se encuentra registrado el rut de un dentista,
     * retorna true si ya se encuentra registrado, caso contrario false
     */
    public boolean existeFolio(String folio) {
        try {
            Connection conexion = Conexion.getConexion();
            String query = "SELECT * FROM ATENCION WHERE FOLIO = ?";
            PreparedStatement bus = conexion.prepareStatement(query);
            bus.setString(1, folio);

            ResultSet rs = bus.executeQuery();
            //next() retorna un boolean, si es true hay elementos en el resultset,
            //false si no hay elementos
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error al ejecutar el metodo existe folio: " + e.getMessage());
        }
        return false;
    }

    /////////////////////////////////////////////////////////UTILIDADES
    /**
     * Este metodo permite mostrar en el combobox los dentistas que estan
     * registrados en la base de datos
     */
    public ArrayList<String> listarItemComboDentista() {
        ArrayList<String> lista = new ArrayList<>();

        try {
            Connection conexion = Conexion.getConexion();
            String query = "SELECT DISTINCT nombre FROM DENTISTA ORDER BY nombre ASC";
            PreparedStatement bus = conexion.prepareStatement(query);

            ResultSet rs = bus.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("nombre"));
            }
        } catch (Exception e) {
            System.out.println("Error al listar productos en el combobox" + e.getMessage());
        }
        return lista;
    }

    /**
     * Este metodo recibe el nombre del dentista, lo busca en la base de datos y
     * retorna al dentista como objeto
     */
    public Dentista cmbDentistaObjeto(String nombre) {
        Dentista dentista = new Dentista();

        try {
            Connection conexion = Conexion.getConexion();
            String query = "SELECT * FROM DENTISTA WHERE NOMBRE = ?";
            PreparedStatement bus = conexion.prepareStatement(query);
            bus.setString(1, nombre);

            ResultSet rs = bus.executeQuery();
            while (rs.next()) {
                dentista.setRut(rs.getString("rut"));
                dentista.setNombre(rs.getString("nombre"));
                dentista.setEdad(rs.getInt("edad"));
                dentista.setEspecialidad(rs.getString("especialidad"));
            }
        } catch (Exception e) {
            System.out.println("Error al ejecutar el metodo dentista-objeto" + e.getMessage());
        }

        return dentista;
    }

    /**
     * Este metodo permite buscar a un paciente que ya esta registrado en la
     * base de datos segun su rut, y permite retornarlo como un objeto de tipo
     * paciente
     */
    public Paciente txtPacienteObjeto(String rut) {
        Paciente paciente = new Paciente();

        try {
            Connection conexion = Conexion.getConexion();
            String query = "SELECT * FROM PACIENTE WHERE RUT = ?";
            PreparedStatement bus = conexion.prepareStatement(query);
            bus.setString(1, rut);

            ResultSet rs = bus.executeQuery();
            while (rs.next()) {
                paciente.setRut(rs.getString("rut"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setEdad(rs.getInt("edad"));
                paciente.setCelular(rs.getInt("celular"));
                paciente.setEmail(rs.getString("email"));
            }
        } catch (Exception e) {
            System.out.println("Error al ejecutar el metodo paciente-objeto" + e.getMessage());
        }

        return paciente;
    }

}
