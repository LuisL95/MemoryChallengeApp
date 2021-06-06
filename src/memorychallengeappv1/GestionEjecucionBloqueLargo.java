
package memorychallengeappv1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import memorychallengeappv1.connections.ConnectionManager;
import memorychallengeappv1.framesMCA.DialogRetoPregunta;


public class GestionEjecucionBloqueLargo
{
    int id_bloque = 0;
    Timestamp fechaRepSig = new Timestamp(System.currentTimeMillis());
    int diasTotales = 0;
    int diasSig = 0;
    List <Enunciado> enunciados = new ArrayList<Enunciado>();
    
    
    
    public GestionEjecucionBloqueLargo(int id_bloqueL)
    {
     this.id_bloque = id_bloqueL;
     getConnection();
     
    // gestionDuracion();
    listaEnunciados();
    timer();
    }
    
    private void getConnection()
    {
        Connection conn;
        try 
        {
            conn= ConnectionManager.getConnection();      
            PreparedStatement stmtBloque = conn.prepareStatement
               (
                        "SELECT * FROM db_memorychallengeapp.table_bloqueactivolargo WHERE ID_BLOQUE_AL = ?"
                );
            stmtBloque.setInt(1, id_bloque);
            ResultSet rs = stmtBloque.executeQuery();
            
            
            while(rs.next())
            {
                fechaRepSig = rs.getTimestamp("fecha_sig_rep");
                diasTotales = rs.getInt("dias_totales");
                diasSig = rs.getInt("dias_sig_rep");
            
            }
            
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(ejemplo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void gestionDuracion()
    {
        while((diasSig/2)<=diasTotales)
        {
            listaEnunciados();
            timer();
            getConnection();
        
        }
    }
    
    private void timer()
    {
        //listaEnunciados();
        Timer timer = new Timer();
        TimerTask task = new TimerTask()
        {
            @Override
            public void run()
            {
                Calendar calendar = GregorianCalendar.getInstance(); 

                Integer calS =  calendar.get(Calendar.SECOND);
                Integer calM = calendar.get(Calendar.MINUTE);
                Integer calH = calendar.get(Calendar.HOUR_OF_DAY);
                Integer calD = calendar.get(Calendar.DAY_OF_MONTH);
                
                
                fechaRepSig.getTime();
                Calendar calendarSQL = Calendar.getInstance();
                calendarSQL.setTimeInMillis(fechaRepSig.getTime());

                Integer d = calendarSQL.get(Calendar.DAY_OF_MONTH);
                Integer h = calendarSQL.get(Calendar.HOUR_OF_DAY);
                Integer m = calendarSQL.get(Calendar.MINUTE);
                Integer s = calendarSQL.get(Calendar.SECOND);

                if(calD >= d & calH >= h & calM >= m & calS >= s)
                {
//                    System.out.println("DIA DE TABLA: "+   d);
//                    System.out.println(calendarSQL);
//                    System.out.println("HORA actual: "+   calH);
                    
                   indiceAleatorio(enunciados);
                   System.out.println("veamos");
                   this.cancel();
               
                }
                else
                {
                    int segundos = 0;
                    System.out.println();
                    segundos = segundos +1;
                     
                }
                
            }
        
        };
        timer.schedule(task,1000, 1000);
    
    }
    

    
    private List <Enunciado> listaEnunciados ()
    {
         Connection conn;
            try 
            {
                conn = ConnectionManager.getConnection();
                PreparedStatement stmtEnunciado = conn.prepareStatement
                (
                    "SELECT * FROM db_memorychallengeapp.table_enunciado WHERE ID_BLOQUE_E = ?" 
                );
                stmtEnunciado.setInt(1,id_bloque);
                ResultSet rse = stmtEnunciado.executeQuery();
                while(rse.next())
                {
                    Enunciado e = new Enunciado();
                    e.setCodigoEnunciado(rse.getString("COD_ENUNCIADO"));
                    e.setPregunta(rse.getString("pregunta"));
                    e.setRespuesta(rse.getString("respuesta"));
                    enunciados.add(e);
                }
                System.out.println("LISTA SQL : "+enunciados.size());

            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(ejemplo.class.getName()).log(Level.SEVERE, null, ex);
            }
            return enunciados;
    }
    
    
    
    int fallo = 0;
    String pregunta;
    String respuesta;
    private boolean indiceAleatorio(List <Enunciado> lista)
    {
        boolean resultado;
        List <Enunciado> list = new ArrayList <Enunciado>();
        
       
        for(int x = 0;x<lista.size();x++)
        {
            list.add(lista.get(x));
            System.out.println("enunciado");
            System.out.println("tamaño lista: "+lista.size());
        }
        int count = list.size();
        System.out.println("cantidad enunciados"+count);
        
        
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
            
            resultado = true;
            
                try {
                Connection conn = ConnectionManager.getConnection();
              //  conn.setAutoCommit(false);
                PreparedStatement stmt = conn.prepareStatement

                ( 
               "UPDATE db_memorychallengeapp.table_bloqueactivolargo "
              + "SET "
              + "fecha_sig_rep = DATE_ADD(CURRENT_TIMESTAMP(), INTERVAL ? DAY), dias_sig_rep = ? WHERE ID_BLOQUE_AL = ?"
                );
                String stringDias_sig = ((Integer)diasSig).toString();
                diasSig = diasSig+diasSig;
                
                stmt.setString(1,stringDias_sig);
                stmt.setInt(2,diasSig);
                stmt.setInt(3, id_bloque);
                int updt = stmt.executeUpdate();

                if(true)
                {
                    //timer();
//                       stmt = conn.prepareStatement
//                       (
//                       "UPDATE db_memorychallengeapp.table_bloque SET estado = 0 WHERE ID_BLOQUE = ?"
//                       );
//                       stmt.setInt(1, b.getID());
//                       updt = stmt.executeUpdate();
//
//                       stmt = conn.prepareStatement
//                       (
//                       "DELETE FROM db_memorychallengeapp.table_bloqueactivocorto WHERE ID_BLOQUE_AC = ?"
//                       );
//                       stmt.setInt(1, b.getID());
//                       updt = stmt.executeUpdate();
                }


              //  conn.commit();
                stmt.close();
            
                } catch (SQLException e) 
                {
                 ConnectionManager.processException(e);
                }
           
                
        }
        else
        {
            
             resultado = false;
             
                try 
              {
                        Connection conn = ConnectionManager.getConnection();
                      //  conn.setAutoCommit(false);

                        String queryUptFirst =
                              "UPDATE db_memorychallengeapp.table_bloqueactivolargo "
                            + "SET "
                            + "fecha_sig_rep = DATE_ADD(CURRENT_TIMESTAMP(), INTERVAL 2 MINUTE) WHERE ID_BLOQUE_AL = ?";
                        String queryUptNext =
                              "UPDATE db_memorychallengeapp.table_bloqueactivolargo "
                            + "SET "
                            + "fecha_sig_rep = DATE_ADD(CURRENT_TIMESTAMP(), INTERVAL ? DAY),  WHERE ID_BLOQUE_AL = ?";

                        if(diasSig<2)
                        {
                            PreparedStatement stmt = conn.prepareStatement (queryUptFirst);
                            stmt.setInt(1, id_bloque);
                            int updt = stmt.executeUpdate();
                            stmt.close();
                        }   
                        else
                        {
                            PreparedStatement stmt = conn.prepareStatement (queryUptNext);
                            diasSig = diasSig/2;
                            String stringDias_sig = ((Integer)diasSig).toString();
                            stmt.setString(1,stringDias_sig);
                            stmt.setInt(2, id_bloque);
                            int updt = stmt.executeUpdate();
                            stmt.close();

                        }
                        

                } 
                catch (SQLException e) 
                {
                        ConnectionManager.processException(e);
                }
             
        }
        fallo = 0;
        getConnection();
        if((diasSig/2)<=diasTotales)
        {
            System.out.println("proceso largo realizado");
            timer();
        }
        else
        {
            System.out.println("proceso TOTAL finalizado");
        }
        
       
       return resultado;
    } 
 
    
}
