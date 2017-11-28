/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Models.LectorMazmorra;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.List;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author alejandro
 */
public class Main extends javax.swing.JFrame {

    /*char mazmorra[][] = {
        {'@', '@', '@', '@', '@', '@', '@', '@', '@', '@', '@', '@', '@'},
        {'@', '.', '.', 'a', '.', '.', 'P', '.', '.', '.', '.', '.', '@'},
        {'@', '.', '@', '@', '@', '@', '.', '@', '@', '@', '@', '.', '@'},
        {'@', 'F', '@', '@', '@', '@', '.', '@', '@', '@', '@', '.', '@'},
        {'@', '.', '@', '@', '@', '@', '.', '@', '@', '@', '@', 'F', '@'},
        {'@', 'v', '.', '.', '.', '.', 'F', '.', '.', '.', '.', '.', '@'},
        {'@', '.', '@', '@', '@', '@', '.', '@', '@', '@', '@', 'a', '@'},
        {'@', 'F', '@', '@', '@', '@', '.', '@', '@', '@', '@', '.', '@'},
        {'@', '.', '@', '@', '@', '@', '.', '@', '@', '@', '@', 'F', '@'},
        {'@', 'F', '.', 'F', '.', '.', 'F', '.', '.', 'F', '.', '.', '@'},
        {'@', '@', '@', '@', '@', '@', '@', '@', '@', '@', '@', '@', '@'}
    };*/
    int level = 1;
    int quantitylevels = 2;
    LectorMazmorra lm = new LectorMazmorra();
    char mazmorra[][] = null;
    char mazmorra1[][] = lm.readFile(1);
    char mazmorra2[][] = lm.readFile(2);
    char mazmorra3[][] = lm.readFile(3);

    Timer timer = new Timer();
    Timer t = new Timer();
    int quantitypoints = 0;
    int points = 0;
    JLabel puntuacion = new JLabel();
    boolean respawn = false;

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
    public Main(int levelactual) {
        level = levelactual;
        class MovimientoGhost extends TimerTask {

            public void run() {
                for (int i = 0; i < fantasmas.size(); i++) {
                    movimientoFantasma(fantasmas.get(i), (int) Math.floor(Math.random() * 5));
                }
                /*movimientoFantasma(fantasmaazul1, );
                movimientoFantasma(fantasmaverde1, (int) Math.floor(Math.random() * 5));*/
            }

            public void movimientoFantasma(JLabel fantasma, int comportamiento) {
                //System.out.println(comportamiento);
                mazmorra[fantasma.getY() / 30][fantasma.getX() / 30] = '.';
                switch (comportamiento) {
                    case 1:
                        if (validateMove(fantasma, comportamiento)) {
                            fantasma.setLocation(fantasma.getX(), fantasma.getY() + 30);
                        }
                        break;
                    case 2:
                        if (validateMove(fantasma, comportamiento)) {
                            fantasma.setLocation(fantasma.getX(), fantasma.getY() - 30);
                        }
                        break;
                    case 3:
                        if (validateMove(fantasma, comportamiento)) {
                            fantasma.setLocation(fantasma.getX() + 30, fantasma.getY());
                        }
                        break;
                    case 4:
                        if (validateMove(fantasma, comportamiento)) {
                            fantasma.setLocation(fantasma.getX() - 30, fantasma.getY());
                        }
                        break;
                }
                mazmorra[fantasma.getY() / 30][fantasma.getX() / 30] = 'a';
            }

            public boolean validateMove(JLabel fantasma, int comportamiento) {
                switch (comportamiento) {
                    case 1:
                        if (mazmorra[(fantasma.getY() + 30) / 30][(fantasma.getX() / 30)] == '@') {
                            return false;
                        }
                        break;
                    case 2:
                        if (mazmorra[(fantasma.getY() - 30) / 30][(fantasma.getX() / 30)] == '@') {
                            return false;
                        }
                        break;
                    case 3:
                        if (mazmorra[fantasma.getY() / 30][(fantasma.getX() + 30) / 30] == '@') {
                            return false;
                        }
                        break;
                    case 4:
                        if (mazmorra[fantasma.getY() / 30][(fantasma.getX() - 30) / 30] == '@') {
                            return false;
                        }
                        break;
                }
                return true;
            }
        }
        class RespawnGhost extends TimerTask {

            @Override
            public void run() {
                if (respawn) {
                    for (int i = 0; i < fantasmas.size(); i++) {
                        ImageIcon respicon = new ImageIcon("/home/alejandro/NetBeansProjects/Pacman/src/View/drawable/respawn.gif");
                        Image respimage = respicon.getImage().getScaledInstance(30, 30, Image.SCALE_FAST);
                        respicon.setImage(respimage);
                        fantasmas.get(i).setIcon(respicon);
                    }
                } else {
                    for (int i = 0; i < fantasmas.size(); i++) {
                        if (fantasmas.get(i).getName().equals("GhostBlue")) {
                            ImageIcon ifantasmaazul = new ImageIcon("/home/alejandro/NetBeansProjects/Pacman/src/View/drawable/fantasmaazul.png");
                            Image iazul = ifantasmaazul.getImage().getScaledInstance(30, 30, Image.SCALE_FAST);
                            ifantasmaazul.setImage(iazul);
                            fantasmas.get(i).setIcon(ifantasmaazul);
                        } else {
                            ImageIcon ifantasmaverde = new ImageIcon("/home/alejandro/NetBeansProjects/Pacman/src/View/drawable/fantasmaverde.png");
                            Image iverde = ifantasmaverde.getImage().getScaledInstance(30, 30, Image.SCALE_FAST);
                            ifantasmaverde.setImage(iverde);
                            fantasmas.get(i).setIcon(ifantasmaverde);
                        }
                    }
                }
                respawn = !respawn;
            }

        }
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        switch (levelactual) {
            case 1:
                mazmorra = mazmorra1;
                break;
            case 2:
                mazmorra = mazmorra2;
                break;
            case 3:
                mazmorra = mazmorra3;
        }
        initComponents();
        readMazmorra();
        ImageIcon imageFrutilla1 = new ImageIcon("");
        Image ifrutilla1 = imageFrutilla1.getImage().getScaledInstance(30, 30, Image.SCALE_FAST);
        imageFrutilla1.setImage(ifrutilla1);
        Frutilla1.setIcon(imageFrutilla1);
        timer.schedule(new MovimientoGhost(), 0, 1000);
        if (respawn) {
            t.schedule(new RespawnGhost(), 0, 5000);
        } else {
            t.schedule(new RespawnGhost(), 0, 20000);
        }
    }

    public void addPoint() {
        if (mazmorra[Pacman.getY() / 30][Pacman.getX() / 30] == '.') {
            points++;
            mazmorra[Pacman.getY() / 30][Pacman.getX() / 30] = ' ';
        } else if (mazmorra[Pacman.getY() / 30][Pacman.getX() / 30] == 'F') {
            points = points + 5;
            mazmorra[Pacman.getY() / 30][Pacman.getX() / 30] = ' ';
        }
        JLabel label = new JLabel();
        label.setName("point");
        ImageIcon ladrillo = new ImageIcon("/home/alejandro/NetBeansProjects/Pacman/src/View/drawable/black.png");
        Image im = ladrillo.getImage().getScaledInstance(30, 30, Image.SCALE_FAST);
        ladrillo.setImage(im);
        label.setIcon(ladrillo);
        jPanel1.add(label);
        label.setBounds(Pacman.getX(), Pacman.getY(), 30, 30);
        System.out.println("Cantidad de puntos: " + puntos.size());
        System.out.println("Cantidad de frutillas: " + frutillas.size());
        puntuacion.setForeground(Color.white);
        puntuacion.setText("Puntaje: " + String.valueOf(points) + "\n Puntaje Total: " + quantitypoints);

    }

    public boolean validateLifePacman() {
        System.out.println("h");
        if ((mazmorra[Pacman.getY() / 30][Pacman.getX() / 30] == 'a') && (respawn)) {
            System.out.println("false");
            return false;
        } else if ((mazmorra[Pacman.getY() / 30][Pacman.getX() / 30] == 'a') && (!respawn)) {
            for (int i = 0; i < fantasmas.size(); i++) {
                if ((fantasmas.get(i).getY() == Pacman.getY()) && (fantasmas.get(i).getX() == Pacman.getX())) {
                    fantasmas.get(i).setVisible(false);
                    fantasmas.remove(i);
                    mazmorra[Pacman.getY() / 30][Pacman.getX() / 30] = ' ';
                    points++;
                    puntuacion.setForeground(Color.white);
                    puntuacion.setText("Puntaje: " + String.valueOf(points) + "\n Puntaje Total: " + quantitypoints);
                    return true;
                }
            }
        }
        System.out.println("h");
        return true;
    }

    public boolean validateMovePacman(KeyEvent event) {
        System.out.println("X: " + Pacman.getX() + " Y: " + Pacman.getY());
        if (event.getKeyCode() == KeyEvent.VK_UP) {
            if (mazmorra[(Pacman.getY() - 30) / 30][Pacman.getX() / 30] == '@') {
                return false;
            }
        } else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
            if (mazmorra[(Pacman.getY() + 30) / 30][Pacman.getX() / 30] == '@') {
                return false;
            }
        } else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (mazmorra[(Pacman.getY() / 30)][(Pacman.getX() + 30) / 30] == '@') {
                return false;
            }
        } else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            if (mazmorra[(Pacman.getY() / 30)][(Pacman.getX() - 30) / 30] == '@') {
                return false;
            }
        }
        return true;
    }

    public ArrayList<JLabel> readMazmorra() {
        puntuacion.setForeground(Color.white);
        jPanel1.add(puntuacion);
        puntuacion.setBounds(mazmorra.length * 50, 10, 200, 50);
        for (int i = 0; i < mazmorra.length; i++) {
            for (int j = 0; j < mazmorra[i].length; j++) {
                if (mazmorra[i][j] == '@') {
                    JLabel label = new JLabel();
                    label.setName("brick");
                    ImageIcon ladrillo = new ImageIcon("/home/alejandro/NetBeansProjects/Pacman/src/View/drawable/ladrillo.png");
                    Image im = ladrillo.getImage().getScaledInstance(30, 30, Image.SCALE_FAST);
                    ladrillo.setImage(im);
                    label.setIcon(ladrillo);
                    jPanel1.add(label);
                    label.setBounds(30 * j, 30 * i, 30, 30);
                } else if (mazmorra[i][j] == '.') {
                    JLabel label = new JLabel();
                    label.setName("point");
                    ImageIcon ladrillo = new ImageIcon("/home/alejandro/NetBeansProjects/Pacman/src/View/drawable/puntoblanco.png");
                    Image im = ladrillo.getImage().getScaledInstance(30, 30, Image.SCALE_FAST);
                    ladrillo.setImage(im);
                    label.setIcon(ladrillo);
                    jPanel1.add(label);
                    label.setBounds(30 * j, 30 * i, 30, 30);
                    puntos.add(label);
                    quantitypoints++;
                } else if (mazmorra[i][j] == 'P') {
                    Pacman = new JLabel();
                    ImageIcon imageIcon = new ImageIcon("/home/alejandro/NetBeansProjects/Pacman/src/View/drawable/pacman.png");
                    Image iP = imageIcon.getImage().getScaledInstance(30, 30, Image.SCALE_FAST);
                    imageIcon.setImage(iP);
                    Pacman.setIcon(imageIcon);
                    jPanel1.add(Pacman);
                    Pacman.setBounds(30 * j, 30 * i, 30, 30);
                } else if (mazmorra[i][j] == 'F') {
                    JLabel label = new JLabel();
                    label.setName("frutilla");
                    ImageIcon frutilla = new ImageIcon("/home/alejandro/NetBeansProjects/Pacman/src/View/drawable/frutilla.png");
                    Image im = frutilla.getImage().getScaledInstance(30, 30, Image.SCALE_FAST);
                    frutilla.setImage(im);
                    label.setIcon(frutilla);
                    jPanel1.add(label);
                    frutillas.add(label);
                    label.setBounds(30 * j, 30 * i, 30, 30);
                    quantitypoints = quantitypoints + 5;
                } else if (mazmorra[i][j] == 'a') {
                    JLabel label = new JLabel();
                    label.setName("GhostBlue");
                    ImageIcon ifantasmaazul = new ImageIcon("/home/alejandro/NetBeansProjects/Pacman/src/View/drawable/fantasmaazul.png");
                    Image iazul = ifantasmaazul.getImage().getScaledInstance(30, 30, Image.SCALE_FAST);
                    ifantasmaazul.setImage(iazul);
                    label.setIcon(ifantasmaazul);
                    jPanel1.add(label);
                    label.setBounds(30 * j, 30 * i, 30, 30);
                    fantasmas.add(label);
                } else if (mazmorra[i][j] == 'v') {
                    JLabel label = new JLabel();
                    label.setName("GhostGreen");
                    ImageIcon ifantasmaverde = new ImageIcon("/home/alejandro/NetBeansProjects/Pacman/src/View/drawable/fantasmaverde.png");
                    Image iverde = ifantasmaverde.getImage().getScaledInstance(30, 30, Image.SCALE_FAST);
                    ifantasmaverde.setImage(iverde);
                    label.setIcon(ifantasmaverde);
                    jPanel1.add(label);
                    label.setBounds(30 * j, 30 * i, 30, 30);
                    fantasmas.add(label);
                }
            }
        }
        puntuacion.setText("Puntaje: " + String.valueOf(points) + "\n Puntaje Total: " + quantitypoints);
        return fantasmas;
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Pacman)
                .addGap(52, 52, 52)
                .addComponent(Frutilla1)
                .addContainerGap(270, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Pacman)
                    .addComponent(Frutilla1))
                .addContainerGap(271, Short.MAX_VALUE))
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
            if (validateMovePacman(evt)) {
                Pacman.setLocation(Pacman.getX(), Pacman.getY() + 30);
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            if (validateMovePacman(evt)) {
                Pacman.setLocation(Pacman.getX(), Pacman.getY() - 30);
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            if (validateMovePacman(evt)) {
                Pacman.setLocation(Pacman.getX() - 30, Pacman.getY());
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (validateMovePacman(evt)) {
                Pacman.setLocation(Pacman.getX() + 30, Pacman.getY());
            }
        }
        for (int i = 0; i < puntos.size(); i++) {
            if ((puntos.get(i).getX() == Pacman.getX()) && (puntos.get(i).getY() == Pacman.getY())) {
                puntos.get(i).setVisible(false);
                puntos.remove(i);
            }
        }
        for (int i = 0; i < frutillas.size(); i++) {
            if ((frutillas.get(i).getX() == Pacman.getX()) && (frutillas.get(i).getY() == Pacman.getY())) {
                frutillas.get(i).setVisible(false);
                frutillas.remove(i);
            }
        }
        addPoint();
        if (!validateLifePacman()) {
            timer.cancel();
            JOptionPane.showMessageDialog(this, "Game Over", "Juego", JOptionPane.ERROR_MESSAGE);
            int reiniciar = JOptionPane.showConfirmDialog(this, "Desea reiniciar el juego?", "Juego", JOptionPane.INFORMATION_MESSAGE);
            if (reiniciar == 0) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        new Main(1).setVisible(true);
                    }
                });
            } else {
                this.dispose();
                timer.cancel();
                t.cancel();
            }
        }
        if ((puntos.isEmpty()) && (frutillas.isEmpty())) {
            JOptionPane.showMessageDialog(this, "Felicitaciones", "Juego", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            level++;
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new Main(level++).setVisible(true);
                }
            });
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
                new Main(1).setVisible(true);
            }
        });
    }

    private ArrayList<JLabel> fantasmas = new ArrayList<>();
    private ArrayList<JLabel> puntos = new ArrayList<>();
    private ArrayList<JLabel> frutillas = new ArrayList<>();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Frutilla1;
    private javax.swing.JLabel Pacman;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
