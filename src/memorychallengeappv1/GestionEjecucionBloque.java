
package memorychallengeappv1;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_CANCEL_OPTION;
import memorychallengeappv1.framesMCA.DialogRetoPregunta;
import memorychallengeappv1.framesMCA.FrameRetoPregunta;


public class GestionEjecucionBloque extends Thread{
    
    Bloque b;
    long tiempo;
    long tiempoTotal;
    boolean resultado;
   public int fallo = 0;
       
    public GestionEjecucionBloque(Bloque b)
    {
    this.b = b;
    }
    
    
    
    @Override
    public void run() 
    {
        
        
       
       tiempo = b.getDuracionInicial();
       tiempoTotal = b.getDuracionTotal();
        while ( tiempo< tiempoTotal)
        {
           
            try {
                this.sleep(tiempo);
            } catch (InterruptedException ex) {
                Logger.getLogger(GestionEjecucionBloque.class.getName()).log(Level.SEVERE, null, ex);
            }
             indiceAleatorio(b.enunciados);
//            if(resultado)
//            {
//                tiempo = tiempo+tiempo;
//                
//                JOptionPane.showMessageDialog(null, "respuesta CORRECTA");
//                System.out.println("okidoki");
//                
//            }
//            else
//            {
//                JOptionPane.showMessageDialog(null, "respuesta INCORRECTA :C");
//                System.out.println("NOT oki :c");
//            }
            
        }
//        
//        if(count < tiempo)
//        {
//            JOptionPane.showMessageDialog(null, "REPETICIÓN FINALIZADA");
//        }
//        else
//        {
//            JOptionPane.showMessageDialog(null, "Debes establecer un tiempo de repeticion mayor al de INICIO");
//        }
        
        
        
    }
      String pregunta;
      String respuesta;
    public boolean indiceAleatorio(List <Enunciado> lista)
    {
        boolean resultado;
        List <Enunciado> list = new ArrayList <Enunciado>();
        
       
        for(int x = 0;x<lista.size();x++)
        {
            list.add(lista.get(x));
        }
        int count = list.size();
        System.out.println(count);
        
        
        while(count >=1)
        {
         
          int numAleatorio = (int)(Math.floor(Math.random()*(count)+1)-1);
          pregunta = list.get(numAleatorio).getPregunta();  
          respuesta = list.get(numAleatorio).getRespuesta();
             
             System.out.println(pregunta);
             System.out.println(respuesta);
                JFrame f = new JFrame();
                DialogRetoPregunta drp = new DialogRetoPregunta(f,true);
                drp.jTextAreaPregunta.setText(pregunta);
                drp.setVisible(true);
               
                drp.setModal(true);
                if(drp.jTextAreaRespuesta.getText().equals(pregunta))
                        {    
                            JOptionPane.showMessageDialog(null, "respuesta CORRECTA");
                            System.out.println("okidoki");  
                        }
                else
                        {
                            JOptionPane.showMessageDialog(null, "respuesta INCORRECTA :C");
                            System.out.println("NOT oki :c");
                            fallo ++;
                        }
             list.remove(numAleatorio);
             count--;
             System.out.println("numero preguntas "+count);
        }                                             
        
        if(fallo == 0 )
        {
            tiempo = tiempo+tiempo;
            resultado = true;
           
        }
        else
        {
            
             resultado = false;
        }
        System.out.println("tiempo "+tiempo);
       fallo = 0; 
        return resultado;
       
    } 
    
//     public String responder()
//    {
//        Toolkit.getDefaultToolkit().beep();
//        respuesta = JOptionPane.showInputDialog(null,"","JEJE", YES_NO_CANCEL_OPTION);
//        return respuesta;
//        
//    }
    
  
}

