package rec.recupera;

import java.text.SimpleDateFormat;
import java.util.Date;
public class Bean {

    private int id;
    private String nombre;
    private String subname;
    private String curp;
    private String cumple;

    public Bean() {
    }

    public Bean(int id, String nombre, String subname, String curp, String cumple) {
        this.id = id;
        this.nombre = nombre;
        this.subname = subname;
        this.curp = curp;
        this.cumple = cumple;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getCumple() {
        return cumple;
    }

    public void setCumple(String cumple) {
        this.cumple = cumple;
    }
}