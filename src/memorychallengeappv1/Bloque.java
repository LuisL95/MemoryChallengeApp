
package memorychallengeappv1;

import java.util.List;
import java.util.ArrayList;


public class Bloque 
{
    //VARIABLES
   private String nombre;
   private long duracionTotal;
   private long duracionInicial;
   private int ID = 0;
   //LISTAS
   public static List <Bloque> bloques = new ArrayList<Bloque>();
   public List <Enunciado> enunciados = new ArrayList<Enunciado>();
   

   //GETTERS DE VARIABLES
    public String getNombre() {
        return nombre;
    }

    public long getDuracionTotal() {
        return duracionTotal;
    }
    
        public long getDuracionInicial() {
        return duracionInicial;
    }

    public int getID() {
        return ID;
    }
     
   //SETTERS DE VARIABLES
        
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDuracionTotal(long duracionTotal) {
        this.duracionTotal = duracionTotal;
    }
    public void setDuracionInicial(long duracionInicial) {
        this.duracionInicial = duracionInicial;
    }
     public void setID(int ID) {
        this.ID = ID;
    }
   
   
   
   
}
