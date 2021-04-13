
package memorychallengeappv1;




public class Enunciado  {
    
    
        //VARIABLES
   private String codigoEnunciado;  
   private String respuesta;
   private  String pregunta;
   
   
   //GETTERS
   public String getCodigoEnunciado() {
        return codigoEnunciado;
    }
   
    public String getRespuesta() {
        return respuesta;
    }

    public String getPregunta() {
        return pregunta;
    }

    //SETTERS

    public void setCodigoEnunciado(String codigoEnunciado) {
        this.codigoEnunciado = codigoEnunciado;
    }
    
    
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    @Override
    public String toString() {
        return "Enunciado{" + "respuesta=" + respuesta + ", pregunta=" + pregunta + '}';
    }
   
   
    
}
