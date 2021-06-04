
package memorychallengeappv1;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_CANCEL_OPTION;
import memorychallengeappv1.connections.ConnectionManager;
import memorychallengeappv1.framesMCA.DialogRetoPregunta;
import memorychallengeappv1.framesMCA.FrameFelicitacionFinalBloque;
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

            
        }
        FrameFelicitacionFinalBloque fffb =  new FrameFelicitacionFinalBloque();
        fffb.setjLabelNombreBloque(b.getNombre());
        fffb.setVisible(true);
        Bloque.bloques.remove(b);
        ejemplo.fp.jLabelNumeroBloques.setText("Bloques existentes: " + Bloque.bloques.size() );
       // ejemplo.fp.jLabelNumeroBloques.setVisible(true);
       ejemplo.fp.repaint();
       
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
                if(drp.jTextAreaRespuesta.getText().equals(respuesta))
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
            
                try {
                Connection conn = ConnectionManager.getConnection();
                conn.setAutoCommit(false);
                PreparedStatement stmt = conn.prepareStatement

                ( 
               "UPDATE db_memorychallengeapp.table_bloqueactivocorto SET tiempo_actual = ? WHERE ID_BLOQUE_AC = ?"
                );
                stmt.setLong(1, tiempo);
                stmt.setInt(2, b.getID());
                int updt = stmt.executeUpdate();

                if(tiempo>tiempoTotal)
                {
                       stmt = conn.prepareStatement
                       (
                       "UPDATE db_memorychallengeapp.table_bloque SET estado = 0 WHERE ID_BLOQUE = ?"
                       );
                       stmt.setInt(1, b.getID());
                       updt = stmt.executeUpdate();

                       stmt = conn.prepareStatement
                       (
                       "DELETE FROM db_memorychallengeapp.table_bloqueactivocorto WHERE ID_BLOQUE_AC = ?"
                       );
                       stmt.setInt(1, b.getID());
                       updt = stmt.executeUpdate();
                }


                conn.commit();
                stmt.close();
            
                } catch (SQLException e) 
                {
                 ConnectionManager.processException(e);
                }
           
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

