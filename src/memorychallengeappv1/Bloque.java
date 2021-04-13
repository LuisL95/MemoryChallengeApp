
package memorychallengeappv1;

import java.util.List;
import java.util.ArrayList;


public class Bloque 
{
    //VARIABLES
   private String nombre;
   private long duracionTotal;
   private long duracionInicial;
   
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

   
   
   
   
}
