/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorychallengeappv1.framesMCA;

import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import memorychallengeappv1.connections.ConnectionManager;

/**
 *
 * @author ser
 */
public class FrameInfoBloqueInactivo extends javax.swing.JFrame {

    /**
     * Creates new form FrameInfoBloqueInactivo
     */
    int idBloque;
    String nombreBloque;
    Long duracionI;
    Long duracionT;
    
    public FrameInfoBloqueInactivo(int idBloqueL) {
        this.idBloque = idBloqueL;
        
        initComponents();
        setVisible(true);
        
        showBloqueInfo();
        setVisible(true);
        setResizable(false);
        
    }
    
    
    private void getConnection(int idBloque)
    { 
        String queryBloque = "SELECT nombre, duracion_inicial, duracion_total FROM db_memorychallengeapp.table_bloque"
                           + " WHERE ID_BLOQUE = ?";
        String queryEnunciado  = "SELECT pregunta, respuesta FROM db_memorychallengeapp.table_enunciado"
                               + " WHERE ID_BLOQUE_E = ?";
        int count = 0;
        try 
        {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement stmt;
            ResultSet rs;
            //Conexion para traer datos del bloque
            stmt = conn.prepareStatement(queryBloque);
            stmt.setInt(1, idBloque);
            rs = stmt.executeQuery();
            if (rs.next())
            {
                nombreBloque = rs.getString("nombre");
                duracionI = rs.getLong("duracion_inicial");
                   duracionT = rs.getLong("duracion_total");
            
            } 
            //Conexion para traer datos de cada enunciado
            stmt = conn.prepareStatement(queryEnunciado);
            stmt.setInt(1, idBloque);
            rs = stmt.executeQuery();
            int y = 130;
            while(rs.next())
            {
//                     for(int x = 0; x <10; x++)
//                   {
                        JTextArea ta = new JTextArea();
                        ta.setFont(new Font("Verdana", Font.BOLD, 8));
                       // ta.setText("Napoleón I Bonaparte (Ajaccio, Córcega, Francia; 15 de agosto de 1769-Longwood, Isla Santa Elena; 5 de mayo de 1821) fue un militar y estadista francés, general republicano durante la Revolución y el Directorio, y artífice del golpe de Estado del 18 de brumario que lo convirtió en primer cónsul (Premier Cónsul) de la República el 11 de noviembre de 1799. Fue además cónsul vitalicio desde el 2 de agosto de 1802 hasta su proclamación como emperador de los franceses (Empereur des Français) el 18 de mayo de 1804, siendo coronado el 2 de diciembre; fue proclamado también rey de Italia el 18 de marzo de 1805 y coronado el 26 de mayo. Ostentó ambos títulos hasta el 11 de abril de 1814, y desde el 20 de marzo hasta el 22 de junio de 1815."); 
                        ta.setText(rs.getString("pregunta")); 
                        ta.setLineWrap(true);
                        ta.setWrapStyleWord(true);
                       // ta.setBackground(new java.awt.Color(72,208,217));
                        ta.setBackground(new java.awt.Color(177,204,206));
                        ta.setForeground(new java.awt.Color(51,77,79));
                        JScrollBar scb = new JScrollBar();
                        JScrollPane sp = new JScrollPane(ta);
                        sp.setVerticalScrollBar(scb);
                        sp.setBounds(30, y, 250, 150);
                        sp.setVisible(true);
                        jPanel2.add(sp);
                        
                        JLabel delimitador = new JLabel(" ");
                        delimitador.setBounds(35, y+170,540, 10);
                        delimitador.setOpaque(true);
                        delimitador.setBackground(new java.awt.Color(9, 154, 213));
                        delimitador.setVisible(true);
                        jPanel2.add(delimitador);
                        
                        JTextArea rTa = new JTextArea();
                        rTa.setFont(new Font("Verdana", Font.BOLD, 8));
                      //  rTa.setText("Napoleón I Bonaparte (Ajaccio, Córcega, Francia; 15 de agosto de 1769-Longwood, Isla Santa Elena; 5 de mayo de 1821) fue un militar y estadista francés, general republicano durante la Revolución y el Directorio, y artífice del golpe de Estado del 18 de brumario que lo convirtió en primer cónsul (Premier Cónsul) de la República el 11 de noviembre de 1799. Fue además cónsul vitalicio desde el 2 de agosto de 1802 hasta su proclamación como emperador de los franceses (Empereur des Français) el 18 de mayo de 1804, siendo coronado el 2 de diciembre; fue proclamado también rey de Italia el 18 de marzo de 1805 y coronado el 26 de mayo. Ostentó ambos títulos hasta el 11 de abril de 1814, y desde el 20 de marzo hasta el 22 de junio de 1815."); 
                        rTa.setText(rs.getString("respuesta")); 
                        rTa.setLineWrap(true);
                        rTa.setWrapStyleWord(true);
                        rTa.setBackground(new java.awt.Color(177,204,206));
                        rTa.setForeground(new java.awt.Color(51,77,79));
                        JScrollBar rScb = new JScrollBar();
                        JScrollPane rSp = new JScrollPane(rTa);
                        rSp.setVerticalScrollBar(rScb);
                        rSp.setBounds(330, y, 250, 150);
                        rSp.setVisible(true);
                        jPanel2.add(rSp);
                         y = y+200;
                         
//                        count =count+1;
//                        System.out.println("count: "+count);
//                    } 
                     
                     count = count +1;
                     System.out.println("count: "+count);
            }
            int yPanel =549;
          //    count = count* 5;
                if((count)<=3)
                {
                   // jPanelEjemplo.setPreferredSize(new Dimension(594,764));
                    
                    yPanel =549;
                }
                else
                {
                    System.out.println("conteo: "+count);
                    int diferencia = count-3;
                    System.out.println("diferencia: "+ diferencia);
                    for(int i = 0;i<diferencia;i++)
                    {
                        yPanel = yPanel+200;
                       // System.out.println(diferencia+" mucho");
                    }
                }
                jPanel2.setPreferredSize(new Dimension(594,yPanel));
        } 
        catch (SQLException e) 
        {
           ConnectionManager.processException(e);
        }
    }
    
    private void showBloqueInfo()
    {
        getConnection(idBloque);
        jLabelNombreBloque.setText(nombreBloque.toString());
        jLabelShowDI.setText(duracionI.toString());
        jLabelShowDT.setText(duracionT.toString());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelNombreBloque = new javax.swing.JLabel();
        jLabelDI = new javax.swing.JLabel();
        jLabelDT = new javax.swing.JLabel();
        jLabelShowDI = new javax.swing.JLabel();
        jLabelShowDT = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabelTituloEnunciados = new javax.swing.JLabel();
        jLabelTituloPregunta = new javax.swing.JLabel();
        jLabelTituloRespuesta = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(660, 904));

        jLabelNombreBloque.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabelNombreBloque.setForeground(new java.awt.Color(85, 131, 131));
        jLabelNombreBloque.setText("Bloque");

        jLabelDI.setBackground(new java.awt.Color(102, 102, 102));
        jLabelDI.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelDI.setForeground(new java.awt.Color(102, 102, 102));
        jLabelDI.setText("Duración Inicial:");

        jLabelDT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelDT.setForeground(new java.awt.Color(102, 102, 102));
        jLabelDT.setText("Duración Total:");

        jLabelShowDI.setForeground(new java.awt.Color(153, 51, 0));
        jLabelShowDI.setText("resultado DI");

        jLabelShowDT.setForeground(new java.awt.Color(0, 51, 51));
        jLabelShowDT.setText("resultado DT");

        jScrollPane1.setPreferredSize(new java.awt.Dimension(638, 706));

        jLabelTituloEnunciados.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabelTituloEnunciados.setForeground(new java.awt.Color(153, 153, 153));
        jLabelTituloEnunciados.setText("Enunciados");

        jLabelTituloPregunta.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabelTituloPregunta.setText("Pregunta");

        jLabelTituloRespuesta.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabelTituloRespuesta.setText("Respuesta");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(jLabelTituloPregunta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 188, Short.MAX_VALUE)
                .addComponent(jLabelTituloRespuesta)
                .addGap(172, 172, 172))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jLabelTituloEnunciados)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabelTituloEnunciados)
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTituloRespuesta)
                    .addComponent(jLabelTituloPregunta))
                .addContainerGap(451, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(302, Short.MAX_VALUE)
                .addComponent(jLabelNombreBloque)
                .addGap(279, 279, 279))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelDI)
                    .addComponent(jLabelDT))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelShowDT, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelShowDI, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabelNombreBloque)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDI)
                    .addComponent(jLabelShowDI))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDT)
                    .addComponent(jLabelShowDT))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameInfoBloqueInactivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameInfoBloqueInactivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameInfoBloqueInactivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameInfoBloqueInactivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrameInfoBloqueInactivo().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelDI;
    private javax.swing.JLabel jLabelDT;
    private javax.swing.JLabel jLabelNombreBloque;
    private javax.swing.JLabel jLabelShowDI;
    private javax.swing.JLabel jLabelShowDT;
    private javax.swing.JLabel jLabelTituloEnunciados;
    private javax.swing.JLabel jLabelTituloPregunta;
    private javax.swing.JLabel jLabelTituloRespuesta;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
