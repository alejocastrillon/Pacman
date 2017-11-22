/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author alejandro
 */
public class Main extends javax.swing.JFrame {

    char mazmorra[][] = {
        {'@', '@', '@', '@', '@', '@', '@', '@', '@'}
    };
    /**
     * Creates new form Main
     *
     */
    /*private BufferedImage resizeImage(BufferedImage originalImage, int width, int height, int type) throws IOException {  
        BufferedImage resizedImage = new BufferedImage(width, height, type);  
        Graphics2D g = resizedImage.createGraphics();  
        g.drawImage(originalImage, 0, 0, width, height, null);  
        g.dispose();  
        return resizedImage;  
    }  */
    public Main() {
        class MovimientoGhost extends TimerTask {

            public void run() {
                movimientoFantasma(fantasmaazul1, (int) Math.floor(Math.random() * 5));
                movimientoFantasma(fantasmaverde1, (int) Math.floor(Math.random() * 5));
            }

            public void movimientoFantasma(JLabel fantasma, int comportamiento) {
                System.out.println(comportamiento);
                switch (comportamiento) {
                    case 1:
                        fantasma.setLocation(fantasma.getX(), fantasma.getY() + 10);
                        break;
                    case 2:
                        fantasma.setLocation(fantasma.getX(), fantasma.getY() - 10);
                        break;
                    case 3:
                        fantasma.setLocation(fantasma.getX() + 10, fantasma.getY());
                        break;
                    case 4:
                        fantasma.setLocation(fantasma.getX() - 10, fantasma.getY());
                        break;
                }
            }
        }
        initComponents();
        readMazmorra();
        ImageIcon imageIcon = new ImageIcon("/home/alejandro/NetBeansProjects/Pacman/src/View/drawable/pacman.png");
        Image i = imageIcon.getImage().getScaledInstance(30, 30, Image.SCALE_FAST);
        imageIcon.setImage(i);
        Pacman.setIcon(imageIcon);
        ImageIcon imageFrutilla1 = new ImageIcon("/home/alejandro/NetBeansProjects/Pacman/src/View/drawable/frutilla.png");
        Image ifrutilla1 = imageFrutilla1.getImage().getScaledInstance(30, 30, Image.SCALE_FAST);
        imageFrutilla1.setImage(ifrutilla1);
        Frutilla1.setIcon(imageFrutilla1);
        ImageIcon imageFantasmaAzul = new ImageIcon("/home/alejandro/NetBeansProjects/Pacman/src/View/drawable/fantasmaazul.png");
        Image ifantasmaazul = imageFantasmaAzul.getImage().getScaledInstance(30, 30, Image.SCALE_FAST);
        imageFantasmaAzul.setImage(ifantasmaazul);
        fantasmaazul1.setIcon(imageFantasmaAzul);
        ImageIcon imagefantasmaverde = new ImageIcon("/home/alejandro/NetBeansProjects/Pacman/src/View/drawable/fantasmaverde.png");
        Image ifantasmaverde = imagefantasmaverde.getImage().getScaledInstance(30, 30, Image.SCALE_FAST);
        imagefantasmaverde.setImage(ifantasmaverde);
        fantasmaverde1.setIcon(imagefantasmaverde);
        Timer timer = new Timer();
        timer.schedule(new MovimientoGhost(), 0, 250);
    }

    public void movimientoFantasma(JLabel fantasma, int comportamiento) {
        switch (comportamiento) {
            case 1:
                fantasma.setLocation(fantasma.getX(), fantasma.getY() + 10);
                break;
            case 2:
                fantasma.setLocation(fantasma.getX(), fantasma.getY() - 10);
                break;
            case 3:
                fantasma.setLocation(fantasma.getX() + 10, fantasma.getY());
                break;
            case 4:
                fantasma.setLocation(fantasma.getX() - 10, fantasma.getY());
                break;
        }
    }

    public void readMazmorra(){
        JLabel label = new JLabel();
        for (int i = 0; i < mazmorra.length; i++) {
            for (int j = 0; j < mazmorra[i].length; j++) {
                if (mazmorra[i][j] == '@') {
                    /*ImageIcon ladrillo = new ImageIcon("/home/alejandro/NetBeansProjects/Pacman/src/View/drawable/ladrillo.png");
                    Image im = ladrillo.getImage().getScaledInstance(30, 30, Image.SCALE_FAST);
                    ladrillo.setImage(im);
                    label.setIcon(ladrillo);
                    label.setLocation(10, j * 10);*/
                    label.setLocation(40, j * 10);
                    label.setText("Hola");
                    label.setForeground(Color.red);
                    jPanel1.add(label);
                }
            }
        }
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
        Pacman = new javax.swing.JLabel();
        Frutilla1 = new javax.swing.JLabel();
        fantasmaazul1 = new javax.swing.JLabel();
        fantasmaverde1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(10, 8, 8));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(15, 11, 11));

        Pacman.setText("          ");

        Frutilla1.setText("            ");

        fantasmaazul1.setText("          ");

        fantasmaverde1.setText("        ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Pacman)
                        .addGap(52, 52, 52)
                        .addComponent(Frutilla1)
                        .addGap(53, 53, 53)
                        .addComponent(fantasmaazul1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(fantasmaverde1)))
                .addContainerGap(187, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Pacman)
                    .addComponent(Frutilla1)
                    .addComponent(fantasmaazul1))
                .addGap(124, 124, 124)
                .addComponent(fantasmaverde1)
                .addContainerGap(130, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            Pacman.setLocation(Pacman.getX(), Pacman.getY() + 10);
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            Pacman.setLocation(Pacman.getX(), Pacman.getY() - 10);
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            Pacman.setLocation(Pacman.getX() - 10, Pacman.getY());
        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            Pacman.setLocation(Pacman.getX() + 10, Pacman.getY());
        }
    }//GEN-LAST:event_formKeyPressed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Frutilla1;
    private javax.swing.JLabel Pacman;
    private javax.swing.JLabel fantasmaazul1;
    private javax.swing.JLabel fantasmaverde1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
