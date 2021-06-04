
package memorychallengeappv1;


import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import memorychallengeappv1.connections.ConnectionManager;
import memorychallengeappv1.framesMCA.FrameBloque;
import memorychallengeappv1.framesMCA.FramePrincipal;


public class ejemplo {
  //  public static List <Bloque> bloquesActivos = new ArrayList<Bloque>();
    
    public  static int jojo = 0;
  public   static FramePrincipal fp ;
    
 
   public static void inicializadorFrame()
   {
       
   fp = new FramePrincipal();
   fp.setVisible(true);
   fp.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                System.out.println("Replace sysout with your method call");
                System.exit(0);
            }
        });
   
   
   }
   
    public void bloquesActivosCerrados()
    {
        
        
            Connection conn;
            try {
                conn = ConnectionManager.getConnection();
                PreparedStatement stmtBloque = conn.prepareStatement
               (
                        "SELECT * FROM db_memorychallengeapp.table_bloque WHERE estado = 1"
                );

                ResultSet rs = stmtBloque.executeQuery();
                
                while(rs.next())
                {
                     Bloque b = new Bloque();
                     b.setNombre(rs.getString("nombre"));
                     b.setDuracionTotal(rs.getLong("duracion_total"));
                     b.setDuracionInicial(rs.getLong("duracion_inicial"));
                     b.setID(rs.getInt("ID_BLOQUE"));
                             PreparedStatement stmtEnunciado = conn.prepareStatement
                            (
                                     "SELECT * FROM db_memorychallengeapp.table_enunciado WHERE ID_BLOQUE_E = ?" 
                             );
                             stmtEnunciado.setInt(1, b.getID());
                             ResultSet rse = stmtEnunciado.executeQuery();
                             while(rse.next())
                             {
                                 Enunciado e = new Enunciado();
                                 e.setCodigoEnunciado(rse.getString("COD_ENUNCIADO"));
                                 e.setPregunta(rse.getString("pregunta"));
                                 e.setRespuesta(rse.getString("respuesta"));
                                 b.enunciados.add(e);
                             }
                     Bloque.bloques.add(b);

                         System.out.println(b.getNombre());
                         System.out.println(b.getID());
                         System.out.println(b.getDuracionTotal());
                }
                for(Bloque bloqueActivo: Bloque.bloques) 
                     {
                        
                        GestionEjecucionBloque geb = new GestionEjecucionBloque(bloqueActivo);
                        geb.start();
                     }
                if(Bloque.bloques.size()>0)
                {
                    this.fp.jButtonParar.setVisible(true);
                    this.fp.jLabelNumeroBloques.setText("Bloques existentes: " + Bloque.bloques.size() );
                    this.fp.jLabelNumeroBloques.setVisible(true);
                }
                     

            } catch (SQLException ex) {
                Logger.getLogger(ejemplo.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
    }
    
    public void inicializador()
    {
       
        inicializadorFrame();
        bloquesActivosCerrados();
        
    }

  
   
}
