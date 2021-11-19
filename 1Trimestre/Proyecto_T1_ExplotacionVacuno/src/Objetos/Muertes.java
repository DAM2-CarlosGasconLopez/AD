package Objetos;

public class Muertes {
    
    private String id_Crotal;
    private String id_Raza;
    private String fechaNacimiento;
    
    public Muertes(String id_Crotal, String id_Raza, String fechaNacimiento) {
        this.id_Crotal = id_Crotal;
        this.id_Raza = id_Raza;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getId_Crotal() {
        return id_Crotal;
    }

    public void setId_Crotal(String id_Crotal) {
        this.id_Crotal = id_Crotal;
    }

    public String getId_Raza() {
        return id_Raza;
    }

    public void setId_Raza(String id_Raza) {
        this.id_Raza = id_Raza;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

   
    

    

}
