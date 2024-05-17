/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Juego_Main;

import Clases.Jugador;
import Clases.Tablero;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Lenovo
 */
public class Diseño_15x15 extends javax.swing.JFrame {

    Tablero tablero = new Tablero("15x15");
    private JLabel[][] vector = new JLabel[225][4]; // Permitir hasta 4 jugadores por casilla
    private List<Jugador> jugadores;
    private int currentPlayerIndex = 0;
    private String name = "";
    private String players;
    public Diseño_15x15(String players) {
        initComponents();
        setLocationRelativeTo(null);
        this.players = players;

        tablero.crearJugador(Integer.parseInt(players));
        jugadores = tablero.getJugadores();

        // Inicializar el tablero
        for (int row = 14; row >= 0; row--) {
            for (int col = 0; col < 15; col++) {
                int number;

                if (row % 2 == 0) { // Filas pares (0, 2, 4, ...) en orden normal
                    number = row * 15 + (14 - col) + 1;
                } else { // Filas impares (1, 3, 5, ...) en orden inverso
                    number = row * 15 + col + 1;
                }

                JPanel subPanel = new JPanel(new BorderLayout());
                subPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borde negro para separar los paneles

                // Asignar un nombre específico al panel (ejemplo: jpanel_pos1, jpanel_pos2, etc.)
                String panelName = "jpanel_pos" + number;
                subPanel.setName(panelName);

                // Crear un JLabel con el número del panel
                JLabel label = new JLabel(String.valueOf(number));
                label.setFont(label.getFont().deriveFont(Font.BOLD, 12)); // Ajustar el tamaño de la fuente del JLabel
                label.setHorizontalAlignment(JLabel.RIGHT); // Alinear a la derecha
                label.setVerticalAlignment(JLabel.TOP); // Alinear en la parte superior

                // Crear un JPanel para los JLabels de color
                JPanel colorPanel = new JPanel(new GridLayout(2, 2, 0, 0));
                colorPanel.setOpaque(false); // Hacer el colorPanel transparente

                for (int i = 0; i < 4; i++) {
                    JLabel colorLabel = new JLabel("  ");
                    colorLabel.setPreferredSize(new Dimension(10, 10)); // Establecer el tamaño del JLabel
                    colorLabel.setOpaque(true);
                    vector[number - 1][i] = colorLabel; // Asignar el JLabel al vector
                    colorPanel.add(colorLabel);
                }

                // Verificar posiciones específicas para aplicar un borde marrón
                if (number == 12 || number == 19 || number == 42 || number == 49 || number == 26 || number == 35 || number == 56 || number == 65 || number == 86
                        || number == 61 || number == 90 || number == 91 || number == 120 || number == 129 || number == 142 || number == 159 || number == 116 || number == 125
                        || number == 146 || number == 155 || number == 176 || number == 185 || number == 191 || number == 200 || number == 105 || number == 106 
                        || number == 136 || number == 135) {
                    subPanel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA)); // Borde naranja
                }
                if (number == 224 || number == 197 || number == 194 || number == 167 || number == 164 || number == 137 || number == 134 || number == 107 || number == 104
                        || number == 77 || number == 74 || number == 47 || number == 44 || number == 17 || number == 14 || number == 162 || number == 139
                        || number == 132 || number == 109 || number == 100 || number == 81 || number == 70 || number == 51 || number == 40 || number == 212 
                        || number == 209 || number == 182 || number == 179 || number == 88 || number == 63 || number == 58 || number == 33 || number == 28
                        || number == 52 || number == 39 || number == 22 || number == 9 || number == 219 || number == 202) {
                    subPanel.setBorder(BorderFactory.createLineBorder(Color.RED)); // Borde naranja
                }

                // Añadir el JLabel del número y el colorPanel al subPanel
                subPanel.add(label, BorderLayout.NORTH);
                subPanel.add(colorPanel, BorderLayout.WEST);

                jPanelJuego.add(subPanel); // Agregar el subpanel al jPanelJuego
            }
        }

        // Inicializar posiciones de los jugadores
        for (Jugador jugador : jugadores) {
            vector[0][jugadores.indexOf(jugador)].setBackground(jugador.getColor());
        }
    }

    public void moverJugador(int espacios) {
        Jugador currentPlayer = jugadores.get(currentPlayerIndex);
        int posicionAnterior = currentPlayer.getPosicion();
        int nuevaPosition = posicionAnterior + espacios;
        if (nuevaPosition >= 225) {
            nuevaPosition = 224; // Asegurar que no se salga del tablero
        }

        int comprobar_posicion = nuevaPosition;

        // Limpiar la posición actual
        vector[currentPlayer.getPosicion()][jugadores.indexOf(currentPlayer)].setOpaque(false);
        vector[currentPlayer.getPosicion()][jugadores.indexOf(currentPlayer)].repaint();

        nuevaPosition = tablero.verificarSerpiente(nuevaPosition);
        nuevaPosition = tablero.verificarEscalera(nuevaPosition);

        // Establecer la nueva posición
        vector[nuevaPosition][jugadores.indexOf(currentPlayer)].setOpaque(true);
        vector[nuevaPosition][jugadores.indexOf(currentPlayer)].setBackground(currentPlayer.getColor());
        vector[nuevaPosition][jugadores.indexOf(currentPlayer)].repaint();

        // Actualizar la posición actual del jugador
        currentPlayer.setPosicion(nuevaPosition);

        // Pasar al siguiente jugador
        currentPlayerIndex = (currentPlayerIndex + 1) % jugadores.size();

        if (players == "2") {
            switch (currentPlayerIndex) {
                case 1:
                    name = "Jugador Rojo:";
                    lb_jugador.setText("Jugador Verde");
                    break;
                case 0:
                    name = "Jugador Verde:";
                    lb_jugador.setText("Jugador Rojo");
                    break;
            }
        } else {
            if (players == "3") {
                switch (currentPlayerIndex) {
                    case 1:
                        name = "Jugador Rojo:";
                        lb_jugador.setText("Jugador Verde");

                        break;
                    case 2:
                        name = "Jugador Verde:";
                        lb_jugador.setText("Jugador Azul");
                        break;
                    case 0:
                        name = "Jugador Azul:";
                        lb_jugador.setText("Jugador Rojo");
                        break;
                }
            } else {
                switch (currentPlayerIndex) {
                    case 1:
                        name = "Jugador Rojo:";
                        lb_jugador.setText("Jugador Verde");
                        break;
                    case 2:
                        name = "Jugador Verde:";
                        lb_jugador.setText("Jugador Azul");
                        break;
                    case 3:
                        name = "Jugador Azul:";
                        lb_jugador.setText("Jugador Morado");
                        break;
                    case 0:
                        name = "Jugador Morado:";
                        lb_jugador.setText("Jugador Rojo");
                        break;
                }
            }
        }

        if (comprobar_posicion != nuevaPosition) {

            String mensaje = name + " avanzó a la posición " + (comprobar_posicion + 1);
            if (nuevaPosition < posicionAnterior) {
                mensaje += " y bajó a la posición " + (nuevaPosition + 1);
            } else if (nuevaPosition > posicionAnterior) {
                mensaje += " y subió a la posición " + (nuevaPosition + 1);
            }
            txt_historial.append(mensaje + "\n");
        } else {
            txt_historial.append(name + " se movió a la posición " + (nuevaPosition + 1) + "\n");
        }

        if (nuevaPosition >= 224) {
            JOptionPane.showMessageDialog(null, "¡" + name + " ha ganado el juego!");
            // Deshabilitar el botón de tirar el dado
            btn_titar_dado.setEnabled(false);
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

        jPanelPrincipal = new javax.swing.JPanel();
        jPanelJuego = new javax.swing.JPanel();
        btn_titar_dado = new javax.swing.JButton();
        btn_reiniciar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_historial = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lb_dado = new javax.swing.JLabel();
        lb_jugador = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelPrincipal.setBackground(new java.awt.Color(153, 0, 255));
        jPanelPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelJuego.setLayout(new java.awt.GridLayout(15, 15, 2, 2));
        jPanelPrincipal.add(jPanelJuego, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 600, 600));

        btn_titar_dado.setBackground(new java.awt.Color(102, 0, 153));
        btn_titar_dado.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        btn_titar_dado.setForeground(new java.awt.Color(255, 255, 255));
        btn_titar_dado.setText("Tirar Dado");
        btn_titar_dado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_titar_dadoActionPerformed(evt);
            }
        });
        jPanelPrincipal.add(btn_titar_dado, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 190, 150, 40));

        btn_reiniciar.setBackground(new java.awt.Color(102, 0, 153));
        btn_reiniciar.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        btn_reiniciar.setForeground(new java.awt.Color(255, 255, 255));
        btn_reiniciar.setText("volver al menu");
        btn_reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reiniciarActionPerformed(evt);
            }
        });
        jPanelPrincipal.add(btn_reiniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 250, -1, 40));

        txt_historial.setColumns(20);
        txt_historial.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        txt_historial.setForeground(new java.awt.Color(0, 0, 0));
        txt_historial.setRows(5);
        jScrollPane1.setViewportView(txt_historial);

        jPanelPrincipal.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 300, 270, 380));

        jPanel3.setBackground(new java.awt.Color(51, 255, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanelPrincipal.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 40, -1, -1));
        jPanelPrincipal.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 680, 40, 20));

        lb_dado.setForeground(new java.awt.Color(0, 0, 0));
        lb_dado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanelPrincipal.add(lb_dado, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 40, 100, 100));

        lb_jugador.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lb_jugador.setForeground(new java.awt.Color(0, 0, 0));
        lb_jugador.setText("Jugador Rojo:");
        jPanelPrincipal.add(lb_jugador, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 90, 120, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Turno De:");
        jPanelPrincipal.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 50, -1, -1));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setOpaque(true);
        jPanelPrincipal.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 270, 160));

        jLabel3.setBackground(new java.awt.Color(153, 0, 255));
        jLabel3.setOpaque(true);
        jPanelPrincipal.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 0, 310, 700));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("jLabel5");
        jLabel5.setOpaque(true);
        jPanelPrincipal.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 16, 620, 630));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_titar_dadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_titar_dadoActionPerformed
        // TODO add your handling code here:
        int espacios = tablero.tirarDado();
        lb_dado.setText(Integer.toString(espacios));
        moverJugador(espacios);
    }//GEN-LAST:event_btn_titar_dadoActionPerformed

    private void btn_reiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reiniciarActionPerformed
        // TODO add your handling code here:
        Main dis = new Main();
        dis.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_reiniciarActionPerformed

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
            java.util.logging.Logger.getLogger(Diseño_15x15.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Diseño_15x15.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Diseño_15x15.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Diseño_15x15.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               // new Diseño_15x15().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_reiniciar;
    private javax.swing.JButton btn_titar_dado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelJuego;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_dado;
    private javax.swing.JLabel lb_jugador;
    private javax.swing.JTextArea txt_historial;
    // End of variables declaration//GEN-END:variables
}
