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

public class Diseño_10x10 extends javax.swing.JFrame {

    Tablero tablero = new Tablero("10x10"); 
    private JLabel[][] vector = new JLabel[100][4]; // Permitir hasta 4 jugadores por casilla
    private List<Jugador> jugadores;
    private int currentPlayerIndex = 0;
    private String name = "";
    private String players;

    public Diseño_10x10(String players) {
        initComponents();
        setLocationRelativeTo(null);
        this.players = players;
        tablero.crearJugador(Integer.parseInt(players)); 
        jugadores = tablero.getJugadores();

        // Inicializar el tablero
        for (int row = 9; row >= 0; row--) {
            for (int col = 0; col < 10; col++) {
                int number;

                if (row % 2 == 0) { // Filas pares (0, 2, 4, ...) en orden normal
                    number = row * 10 + (9 - col) + 1;
                } else { // Filas impares (1, 3, 5, ...) en orden inverso
                    number = row * 10 + col + 1;
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
                if (number == 10 || number == 11 || number == 30 || number == 83 || number == 98 || number == 17 || number == 24 || number == 37 || number == 44
                        || number == 57 || number == 53 || number == 68 || number == 73 || number == 60 || number == 61 || number == 80 || number == 23 || number == 38
                        || number == 43) {
                    subPanel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA)); // Borde naranja
                }
                if (number == 99 || number == 82 || number == 79 || number == 62 || number == 59 || number == 42 || number == 39 || number == 22 || number == 19
                        || number == 2 || number == 71 || number == 70 || number == 51 || number == 36 || number == 25 || number == 33 || number == 28
                        || number == 13 || number == 8 || number == 96 || number == 85 || number == 76 || number == 67 || number == 54 || number == 47) {
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
        if (nuevaPosition >= 100) {
            nuevaPosition = 99; // Asegurar que no se salga del tablero
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
        
        
        if (nuevaPosition >= 99) {
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
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lb_dado = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_historial = new javax.swing.JTextArea();
        btn_reiniciar = new javax.swing.JButton();
        lb_jugador = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelPrincipal.setBackground(new java.awt.Color(153, 0, 255));
        jPanelPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelJuego.setLayout(new java.awt.GridLayout(10, 10, 2, 2));
        jPanelPrincipal.add(jPanelJuego, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 51, 500, 500));

        btn_titar_dado.setBackground(new java.awt.Color(102, 0, 153));
        btn_titar_dado.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        btn_titar_dado.setForeground(new java.awt.Color(255, 255, 255));
        btn_titar_dado.setText("Tirar Dado");
        btn_titar_dado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_titar_dadoActionPerformed(evt);
            }
        });
        jPanelPrincipal.add(btn_titar_dado, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 140, 120, 40));
        jPanelPrincipal.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 580, 40, 20));

        jPanel1.setBackground(new java.awt.Color(51, 255, 0));

        lb_dado.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        lb_dado.setForeground(new java.awt.Color(0, 0, 0));
        lb_dado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lb_dado, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lb_dado, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelPrincipal.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, 90, -1));

        txt_historial.setColumns(20);
        txt_historial.setRows(5);
        jScrollPane2.setViewportView(txt_historial);

        jPanelPrincipal.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 230, 270, 350));

        btn_reiniciar.setBackground(new java.awt.Color(102, 0, 153));
        btn_reiniciar.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        btn_reiniciar.setForeground(new java.awt.Color(255, 255, 255));
        btn_reiniciar.setText("Volver al menu");
        btn_reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reiniciarActionPerformed(evt);
            }
        });
        jPanelPrincipal.add(btn_reiniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 190, 140, 30));

        lb_jugador.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lb_jugador.setForeground(new java.awt.Color(0, 0, 0));
        lb_jugador.setText("Jugador Rojo:");
        jPanelPrincipal.add(lb_jugador, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 70, 120, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Turno De:");
        jPanelPrincipal.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 30, -1, -1));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setOpaque(true);
        jPanelPrincipal.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 250, 120));

        jLabel3.setBackground(new java.awt.Color(153, 0, 255));
        jLabel3.setOpaque(true);
        jPanelPrincipal.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 290, 600));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setOpaque(true);
        jPanelPrincipal.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 520, 520));

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
            java.util.logging.Logger.getLogger(Diseño_10x10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Diseño_10x10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Diseño_10x10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Diseño_10x10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new Diseño_10x10().setVisible(true);
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelJuego;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb_dado;
    private javax.swing.JLabel lb_jugador;
    private javax.swing.JTextArea txt_historial;
    // End of variables declaration//GEN-END:variables
}
