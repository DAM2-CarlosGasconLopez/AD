import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public class Pilotos implements Serializable{
    private String Escuderia;
    private LocalDate FechaNacimiento;
    private Integer Dorsal;
    private Integer Puntos;
    
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    public String getEscuderia() {
        return Escuderia;
    }
    public void setEscuderia(String escuderia) {
        Escuderia = escuderia;
    }
    public LocalDate getFechaNacimiento() {
        return FechaNacimiento;
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }
    public Integer getDorsal() {
        return Dorsal;
    }
    public void setDorsal(Integer dorsal) {
        Dorsal = dorsal;
    }
    public Integer getPuntos() {
        return Puntos;
    }
    public void setPuntos(Integer puntos) {
        Puntos = puntos;
    }
    
    @Override
    public String toString() {
        return "Pilotos ==>  Nombre: " + Nombre + ", Escuderia: " + Escuderia + ", FechaNacimiento: "
        + FechaNacimiento + ", Dorsal: " + Dorsal + ", Puntos: " + Puntos ;
    }
    
    private String Nombre;
    public Pilotos(String nombre, String escuderia, LocalDate fechaNacimiento, Integer dorsal, Integer puntos) {
        Nombre = nombre;
        Escuderia = escuderia;
        FechaNacimiento = fechaNacimiento;
        Dorsal = dorsal;
        Puntos = puntos;
    }
    


    
}
