/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package taken;

import javax.swing.JButton;

/**
 *
 * @author jovas
 */
public class Juego extends javax.swing.JFrame {
    private int[][] tablero; // Representación del tablero como matriz bidimensional
    /*
     * 1 2 3 4
     * 5 6 7 8
     * 9 10 11 12
     * 13 14 15 []
     * 
     */
    private JButton[][] botones; // Botones de la interfaz
    private int contadorMovimientos = 0;

    public Juego() {
        initComponents();
        /// Inicializa el tablero y vincula los botones
        inicializarTablero();
        vincularBotones();

    }

    private void inicializarTablero() {
        // Tablero de 4x4 para el juego del 15
        tablero = new int[4][4];
        // Inicializa el tablero con la configuración inicial
        int contador = 1;

        /// Este ciclo llena el tablero con los números del 1 al 15
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (contador <= 15) {
                    tablero[i][j] = contador++;
                } else {
                    /// El último espacio se inicializa en 0
                    tablero[i][j] = 0;
                }
            }
        }
        // Intercambia dos fichas para la configuración inicial
        /// Esto es para que el juego sea resoluble
        /// Si se comenta, el juego puede no ser resoluble
        int temp = tablero[3][2];
        /// Intercambia la posición de los números 14 y 15
        tablero[3][2] = tablero[3][1];
        /// Esta línea es para que el juego sea resoluble
        tablero[3][1] = temp;
    }

    private void vincularBotones() {
        /// Crea una matriz de botones para la interfaz
        botones = new JButton[][] {
                { uno, dos, tres, cuatro },
                { cinco, seis, siete, ocho },
                { nueve, diez, once, doce },
                { trece, catorce, quince, vacio }
        };
        /// Vincula los botones con el método moverFicha() para que se muevan
        actualizarInterfaz();
    }

    /// Mueve una ficha a la posición del espacio vacío (si es posible)
    private void moverFicha(int fila, int columna) {
        // Encuentra la posición del espacio vacío
        /// Si no hay espacio vacío, no hace nada

        /// Inicializa las variables en -1 para saber si se encontró el espacio vacío
        int filaVacia = -1, columnaVacia = -1;

        /// Busca la posición del espacio vacío en el tablero
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                /// Si la posición es 0, es el espacio vacío
                if (tablero[i][j] == 0) {
                    filaVacia = i;
                    columnaVacia = j;
                    break;
                }
            }
        }
        // Verifica si la ficha se puede mover (adyacente al espacio vacío)
        /// Math.abs() devuelve el valor absoluto de un número
        /// Por ejemplo, Math.abs(-5) devuelve 5
        /// esto es para que no importe si la ficha está arriba o abajo del espacio
        // vacío
        if ((Math.abs(filaVacia - fila) == 1 && columnaVacia == columna) ||
                (Math.abs(columnaVacia - columna) == 1 && filaVacia == fila)) {
            // Intercambia la ficha con el espacio vacío
            /// Por ejemplo, si la ficha está arriba del espacio vacío
            /// la ficha se mueve hacia arriba y el espacio vacío hacia abajo
            tablero[filaVacia][columnaVacia] = tablero[fila][columna];
            tablero[fila][columna] = 0;

            // Actualiza la interfaz
            actualizarInterfaz();
        }
        contadorMovimientos++;
        /// Actualiza el contador de movimientos
        actualizarContadorMovimientos();
    }

    /// Actualiza los botones de la interfaz con los valores del tablero
    private void actualizarInterfaz() {
        /// Actualiza los botones de la interfaz con los valores del tablero
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                /// Si el valor es 0, el botón se muestra vacío
                if (tablero[i][j] == 0) {
                    /// El botón vacío no muestra ningún texto (está vacío)
                    botones[i][j].setText("");
                } else {
                    /// Si no, muestra el número correspondiente
                    /// String.valueOf() convierte un número a String
                    botones[i][j].setText(String.valueOf(tablero[i][j]));
                }
            }
        }
    }

    /// Actualiza el contador de movimientos en el JtextPane
    private void actualizarContadorMovimientos() {
        movs.setText("Movimientos: " + contadorMovimientos);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Titulo = new javax.swing.JLabel();
        uno = new javax.swing.JButton();
        dos = new javax.swing.JButton();
        tres = new javax.swing.JButton();
        cuatro = new javax.swing.JButton();
        cinco = new javax.swing.JButton();
        seis = new javax.swing.JButton();
        siete = new javax.swing.JButton();
        ocho = new javax.swing.JButton();
        nueve = new javax.swing.JButton();
        diez = new javax.swing.JButton();
        once = new javax.swing.JButton();
        doce = new javax.swing.JButton();
        trece = new javax.swing.JButton();
        catorce = new javax.swing.JButton();
        quince = new javax.swing.JButton();
        vacio = new javax.swing.JButton();
        separador = new javax.swing.JSeparator();
        reiniciar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        movs = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 255, 204));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setForeground(java.awt.Color.red);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Titulo.setBackground(new java.awt.Color(0, 204, 204));
        Titulo.setFont(new java.awt.Font("Gill Sans Ultra Bold Condensed", 2, 24)); // NOI18N
        Titulo.setForeground(new java.awt.Color(0, 51, 51));
        Titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titulo.setText("Juego del 15 (Taken)");
        getContentPane().add(Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 13, 425, 48));

        uno.setBackground(new java.awt.Color(153, 204, 255));
        uno.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        uno.setText("1");
        uno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        uno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unoActionPerformed(evt);
            }
        });
        getContentPane().add(uno, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 79, 90, 90));

        dos.setBackground(new java.awt.Color(0, 204, 153));
        dos.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        dos.setText("2");
        dos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dosActionPerformed(evt);
            }
        });
        getContentPane().add(dos, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 79, 90, 90));

        tres.setBackground(new java.awt.Color(153, 204, 255));
        tres.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        tres.setText("3");
        tres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tresActionPerformed(evt);
            }
        });
        getContentPane().add(tres, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 79, 90, 90));

        cuatro.setBackground(new java.awt.Color(0, 204, 153));
        cuatro.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        cuatro.setText("4");
        cuatro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuatroActionPerformed(evt);
            }
        });
        getContentPane().add(cuatro, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 79, 90, 90));

        cinco.setBackground(new java.awt.Color(153, 204, 255));
        cinco.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        cinco.setText("5");
        cinco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cincoActionPerformed(evt);
            }
        });
        getContentPane().add(cinco, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 176, 90, 90));

        seis.setBackground(new java.awt.Color(0, 204, 153));
        seis.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        seis.setText("6");
        seis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seisActionPerformed(evt);
            }
        });
        getContentPane().add(seis, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 176, 90, 90));

        siete.setBackground(new java.awt.Color(153, 204, 255));
        siete.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        siete.setText("7");
        siete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sieteActionPerformed(evt);
            }
        });
        getContentPane().add(siete, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 176, 90, 90));

        ocho.setBackground(new java.awt.Color(0, 204, 153));
        ocho.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        ocho.setText("8");
        ocho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ochoActionPerformed(evt);
            }
        });
        getContentPane().add(ocho, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 176, 90, 90));

        nueve.setBackground(new java.awt.Color(153, 204, 255));
        nueve.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        nueve.setText("9");
        nueve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nueveActionPerformed(evt);
            }
        });
        getContentPane().add(nueve, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 273, 90, 90));

        diez.setBackground(new java.awt.Color(0, 204, 153));
        diez.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        diez.setText("10");
        diez.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diezActionPerformed(evt);
            }
        });
        getContentPane().add(diez, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 273, 90, 90));

        once.setBackground(new java.awt.Color(153, 204, 255));
        once.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        once.setText("11");
        once.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onceActionPerformed(evt);
            }
        });
        getContentPane().add(once, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 273, 90, 90));

        doce.setBackground(new java.awt.Color(0, 204, 153));
        doce.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        doce.setText("12");
        doce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doceActionPerformed(evt);
            }
        });
        getContentPane().add(doce, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 273, 90, 90));

        trece.setBackground(new java.awt.Color(153, 204, 255));
        trece.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        trece.setText("13");
        trece.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                treceActionPerformed(evt);
            }
        });
        getContentPane().add(trece, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 370, 90, 90));

        catorce.setBackground(new java.awt.Color(0, 204, 153));
        catorce.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        catorce.setText("14");
        catorce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                catorceActionPerformed(evt);
            }
        });
        getContentPane().add(catorce, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 370, 90, 90));

        quince.setBackground(new java.awt.Color(153, 204, 255));
        quince.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        quince.setText("15");
        quince.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quinceActionPerformed(evt);
            }
        });
        getContentPane().add(quince, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 370, 90, 90));

        vacio.setBackground(new java.awt.Color(0, 204, 153));
        vacio.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        vacio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vacioActionPerformed(evt);
            }
        });
        getContentPane().add(vacio, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 370, 90, 90));
        getContentPane().add(separador, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 478, 456, 10));

        reiniciar.setBackground(new java.awt.Color(204, 204, 255));
        reiniciar.setFont(new java.awt.Font("FiraCode Nerd Font Mono Light", 1, 18)); // NOI18N
        reiniciar.setText("REINICIAR");
        reiniciar.setBorder(new javax.swing.border.MatteBorder(null));
        reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reiniciarActionPerformed(evt);
            }
        });
        getContentPane().add(reiniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 490, 120, 40));

        jScrollPane1.setBackground(new java.awt.Color(0, 153, 153));

        movs.setEditable(false);
        movs.setBackground(new java.awt.Color(0, 153, 153));
        movs.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        movs.setFont(new java.awt.Font("Segoe Print", 3, 22)); // NOI18N
        movs.setToolTipText("");
        movs.setSelectedTextColor(new java.awt.Color(0, 153, 153));
        jScrollPane1.setViewportView(movs);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 280, 70));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/img2.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -90, 470, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void unoActionPerformed(java.awt.event.ActionEvent evt) {
        moverFicha(0, 0);
    }

    private void dosActionPerformed(java.awt.event.ActionEvent evt) {
        moverFicha(0, 1);
    }

    private void tresActionPerformed(java.awt.event.ActionEvent evt) {
        moverFicha(0, 2);
    }

    private void cuatroActionPerformed(java.awt.event.ActionEvent evt) {
        moverFicha(0, 3);
    }

    private void cincoActionPerformed(java.awt.event.ActionEvent evt) {
        moverFicha(1, 0);
    }

    private void seisActionPerformed(java.awt.event.ActionEvent evt) {
        moverFicha(1, 1);
    }

    private void sieteActionPerformed(java.awt.event.ActionEvent evt) {
        moverFicha(1, 2);
    }

    private void ochoActionPerformed(java.awt.event.ActionEvent evt) {
        moverFicha(1, 3);
    }

    private void nueveActionPerformed(java.awt.event.ActionEvent evt) {
        moverFicha(2, 0);
    }

    private void diezActionPerformed(java.awt.event.ActionEvent evt) {
        moverFicha(2, 1);
    }

    private void onceActionPerformed(java.awt.event.ActionEvent evt) {
        moverFicha(2, 2);
    }

    private void doceActionPerformed(java.awt.event.ActionEvent evt) {
        moverFicha(2, 3);
    }

    private void treceActionPerformed(java.awt.event.ActionEvent evt) {
        moverFicha(3, 0);
    }

    private void catorceActionPerformed(java.awt.event.ActionEvent evt) {
        moverFicha(3, 1);
    }

    private void quinceActionPerformed(java.awt.event.ActionEvent evt) {
        moverFicha(3, 2);
    }

    private void vacioActionPerformed(java.awt.event.ActionEvent evt) {
        moverFicha(3, 3);
    }

    private void reiniciarActionPerformed(java.awt.event.ActionEvent evt) {
        // Aquí va la lógica para reiniciar el juego
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Juego().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Titulo;
    private javax.swing.JButton catorce;
    private javax.swing.JButton cinco;
    private javax.swing.JButton cuatro;
    private javax.swing.JButton diez;
    private javax.swing.JButton doce;
    private javax.swing.JButton dos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane movs;
    private javax.swing.JButton nueve;
    private javax.swing.JButton ocho;
    private javax.swing.JButton once;
    private javax.swing.JButton quince;
    private javax.swing.JButton reiniciar;
    private javax.swing.JButton seis;
    private javax.swing.JSeparator separador;
    private javax.swing.JButton siete;
    private javax.swing.JButton trece;
    private javax.swing.JButton tres;
    private javax.swing.JButton uno;
    private javax.swing.JButton vacio;
    // End of variables declaration//GEN-END:variables
}
