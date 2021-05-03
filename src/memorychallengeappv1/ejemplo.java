
package memorychallengeappv1;


import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;
import memorychallengeappv1.framesMCA.FrameBloque;
import memorychallengeappv1.framesMCA.FramePrincipal;


public class ejemplo {
    
    
    public  static int jojo = 0;
  public   static FramePrincipal fp ;
    
 
   public static void metodo()
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
   

   
  
   
}
