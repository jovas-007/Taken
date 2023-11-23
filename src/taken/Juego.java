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
    private ListaLigada listaLigada; 
    private int contadorMovimientos; 

    public Juego() {
        initComponents();
        inicializarTablero();
        vincularBotones();
        listaLigada = new ListaLigada(4);
        int contadorMovimientos=0;
    }

    private void inicializarTablero() {
        tablero = new int[4][4];
        int contador = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (contador < 16) {
                    tablero[i][j] = contador++;
                } else {
                    tablero[i][j] = 0; // Espacio vacío
                }
            }
        }
    }

    public class Nodo {
    int valor; // Valor del nodo, 0 para el espacio vacío
    int fila; // Fila del nodo en el tablero
    int columna; // Columna del nodo en el tablero
    Nodo anterior; // Referencia al nodo anterior en la lista
    Nodo siguiente; // Referencia al nodo siguiente en la lista

    // Constructor para inicializar el nodo
    public Nodo(int valor, int fila, int columna) {
        this.valor = valor;
        this.fila = fila;
        this.columna = columna;
        this.anterior = null;
        this.siguiente = null;
    }

    // Métodos getters y setters para valor, fila, columna, anterior y siguiente
    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public Nodo getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}

    
    
    class ListaLigada {
    Nodo cabeza;
    Nodo nodoVacio; // Nodo que representa el espacio vacío en el tablero
    int dimension; // Dimension del tablero, por ejemplo, 4 para un tablero 4x4

    public ListaLigada(int dimension) {
        this.dimension = dimension;
        this.cabeza = null;
        Nodo ultimo = null; // Referencia al último nodo agregado

        int contador = 1; // Contador para asignar valores a los nodos

        // Crear nodos para cada posición del tablero
        for (int fila = 0; fila < dimension; fila++) {
            for (int columna = 0; columna < dimension; columna++) {
                Nodo nuevoNodo = new Nodo(contador, fila, columna);

                // Si la lista está vacía, el nuevo nodo es la cabeza
                if (this.cabeza == null) {
                    this.cabeza = nuevoNodo;
                } else {
                    // Enlazar el nuevo nodo con el último nodo
                    ultimo.setSiguiente(nuevoNodo);
                    nuevoNodo.setAnterior(ultimo);
                }

                // Actualizar el último nodo agregado
                ultimo = nuevoNodo;

                // Incrementar el contador, el último nodo será el espacio vacío
                if (contador < dimension * dimension) {
                    contador++;
                } else {
                    // El último nodo es el espacio vacío
                    nuevoNodo.setValor(0);
                    this.nodoVacio = nuevoNodo;
                }
            }
        }
    }


    // Método para mover un nodo
    public boolean moverNodo(int valor) {
        Nodo nodoObjetivo = encontrarNodo(valor);
        if (nodoObjetivo == null || nodoVacio == null) {
            return false; // Nodo no encontrado o no hay nodo vacío
        }

        // Verifica si el nodo objetivo es adyacente al nodo vacío
        if (esAdyacente(nodoObjetivo, nodoVacio)) {
            // Intercambia los valores de los nodos
            int temp = nodoVacio.valor;
            nodoVacio.valor = nodoObjetivo.valor;
            nodoObjetivo.valor = temp;

            // Actualiza el nodo vacío
            nodoVacio = nodoObjetivo;
            return true;
        }

        return false;
    }

    // Método para verificar si dos nodos son adyacentes
    private boolean esAdyacente(Nodo nodo1, Nodo nodo2) {
        // Verifica si los nodos son adyacentes en un tablero 2D
        return Math.abs(nodo1.fila - nodo2.fila) + Math.abs(nodo1.columna - nodo2.columna) == 1;
    }

    // Método para encontrar un nodo con un valor específico
    public Nodo encontrarNodo(int valor) {
        Nodo temp = cabeza;
        while (temp != null) {
            if (temp.valor == valor) {
                return temp;
            }
            temp = temp.siguiente;
        }
        return null; // No se encontró el nodo
    }


}
    
    
    
    private void moverFicha(int fila, int columna) {
    // Obtener el valor del nodo en la posición dada
    int valorDelNodo = tablero[fila][columna];

    // Verificar si el nodo seleccionado no es el espacio vacío
    if (valorDelNodo != 0) {
        // Intentar mover el nodo
        if (listaLigada.moverNodo(valorDelNodo)) { // Suponiendo que moverNodo devuelve true si el movimiento es válido
            contadorMovimientos++; // Incrementa el contador solo si el movimiento es válido
            actualizarContadorMovimientos(); // Actualiza el contador en la interfaz
            actualizarTableroYInterfaz(); // Actualiza el tablero y la interfaz después del movimiento
        }
    }
}

// Método para actualizar el contador de movimientos en la interfaz
private void actualizarContadorMovimientos() {
    movs.setText(" Movimientos: " + contadorMovimientos);
}

// Método para actualizar el tablero y la interfaz
private void actualizarTableroYInterfaz() {
    // Recorrer la lista ligada para actualizar la matriz tablero
    Nodo actual = listaLigada.cabeza;
    while (actual != null) {
        tablero[actual.getFila()][actual.getColumna()] = actual.getValor();
        actual = actual.getSiguiente();
    }

    // Actualizar los botones de la interfaz
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
            if (tablero[i][j] != 0) {
                botones[i][j].setText(String.valueOf(tablero[i][j]));
            } else {
                botones[i][j].setText(""); // Espacio vacío
            }
        }
    }
}



private void vincularBotones() {
    botones = new JButton[][] {
        {uno, dos, tres, cuatro},
        {cinco, seis, siete, ocho},
        {nueve, diez, once, doce},
        {trece, catorce, quince, vacio}
    };

    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
            if (tablero[i][j] != 0) {
                botones[i][j].setText(String.valueOf(tablero[i][j]));
            } else {
                botones[i][j].setText(""); // Espacio vacío
            }
        }
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
        solucion = new javax.swing.JButton();
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
        Titulo.setForeground(new java.awt.Color(204, 204, 204));
        Titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titulo.setText("Juego del 15 (Taken)");
        getContentPane().add(Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 13, 425, 48));

        uno.setBackground(new java.awt.Color(0, 153, 255));
        uno.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        uno.setForeground(new java.awt.Color(255, 255, 255));
        uno.setText("1");
        uno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        uno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unoActionPerformed(evt);
            }
        });
        getContentPane().add(uno, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 79, 90, 90));

        dos.setBackground(new java.awt.Color(0, 153, 153));
        dos.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        dos.setForeground(new java.awt.Color(255, 255, 255));
        dos.setText("2");
        dos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dosActionPerformed(evt);
            }
        });
        getContentPane().add(dos, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 79, 90, 90));

        tres.setBackground(new java.awt.Color(0, 153, 255));
        tres.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        tres.setForeground(new java.awt.Color(255, 255, 255));
        tres.setText("3");
        tres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tresActionPerformed(evt);
            }
        });
        getContentPane().add(tres, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 79, 90, 90));

        cuatro.setBackground(new java.awt.Color(0, 153, 153));
        cuatro.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        cuatro.setForeground(new java.awt.Color(255, 255, 255));
        cuatro.setText("4");
        cuatro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuatroActionPerformed(evt);
            }
        });
        getContentPane().add(cuatro, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 79, 90, 90));

        cinco.setBackground(new java.awt.Color(0, 153, 255));
        cinco.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        cinco.setForeground(new java.awt.Color(255, 255, 255));
        cinco.setText("5");
        cinco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cincoActionPerformed(evt);
            }
        });
        getContentPane().add(cinco, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 176, 90, 90));

        seis.setBackground(new java.awt.Color(0, 153, 153));
        seis.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        seis.setForeground(new java.awt.Color(255, 255, 255));
        seis.setText("6");
        seis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seisActionPerformed(evt);
            }
        });
        getContentPane().add(seis, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 176, 90, 90));

        siete.setBackground(new java.awt.Color(0, 153, 255));
        siete.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        siete.setForeground(new java.awt.Color(255, 255, 255));
        siete.setText("7");
        siete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sieteActionPerformed(evt);
            }
        });
        getContentPane().add(siete, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 176, 90, 90));

        ocho.setBackground(new java.awt.Color(0, 153, 153));
        ocho.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        ocho.setForeground(new java.awt.Color(255, 255, 255));
        ocho.setText("8");
        ocho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ochoActionPerformed(evt);
            }
        });
        getContentPane().add(ocho, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 176, 90, 90));

        nueve.setBackground(new java.awt.Color(0, 153, 255));
        nueve.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        nueve.setForeground(new java.awt.Color(255, 255, 255));
        nueve.setText("9");
        nueve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nueveActionPerformed(evt);
            }
        });
        getContentPane().add(nueve, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 273, 90, 90));

        diez.setBackground(new java.awt.Color(0, 153, 153));
        diez.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        diez.setForeground(new java.awt.Color(255, 255, 255));
        diez.setText("10");
        diez.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diezActionPerformed(evt);
            }
        });
        getContentPane().add(diez, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 273, 90, 90));

        once.setBackground(new java.awt.Color(0, 153, 255));
        once.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        once.setForeground(new java.awt.Color(255, 255, 255));
        once.setText("11");
        once.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onceActionPerformed(evt);
            }
        });
        getContentPane().add(once, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 273, 90, 90));

        doce.setBackground(new java.awt.Color(0, 153, 153));
        doce.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        doce.setForeground(new java.awt.Color(255, 255, 255));
        doce.setText("12");
        doce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doceActionPerformed(evt);
            }
        });
        getContentPane().add(doce, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 273, 90, 90));

        trece.setBackground(new java.awt.Color(0, 153, 255));
        trece.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        trece.setForeground(new java.awt.Color(255, 255, 255));
        trece.setText("13");
        trece.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                treceActionPerformed(evt);
            }
        });
        getContentPane().add(trece, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 370, 90, 90));

        catorce.setBackground(new java.awt.Color(0, 153, 153));
        catorce.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        catorce.setForeground(new java.awt.Color(255, 255, 255));
        catorce.setText("14");
        catorce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                catorceActionPerformed(evt);
            }
        });
        getContentPane().add(catorce, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 370, 90, 90));

        quince.setBackground(new java.awt.Color(0, 153, 255));
        quince.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        quince.setForeground(new java.awt.Color(255, 255, 255));
        quince.setText("15");
        quince.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quinceActionPerformed(evt);
            }
        });
        getContentPane().add(quince, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 370, 90, 90));

        solucion.setText("Ver Soluciones");
        getContentPane().add(solucion, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 570, -1, -1));

        vacio.setBackground(new java.awt.Color(0, 153, 153));
        vacio.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        vacio.setForeground(new java.awt.Color(255, 255, 255));
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
        movs.setText(" Movimientos: 0");
        movs.setToolTipText("");
        movs.setSelectedTextColor(new java.awt.Color(0, 153, 153));
        jScrollPane1.setViewportView(movs);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 280, 70));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/DALL·E 2023-11-22 23.56.57 - A simple, wide background with a smooth gradient, using slightly darker shades of blue and green. The background should be completely plain, without a.png"))); // NOI18N
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
    // Restablece la lista ligada
    listaLigada = new ListaLigada(4); // Asumiendo que la dimensión del tablero es 4x4

    // Restablece el tablero y actualiza la interfaz
    inicializarTablero();
    actualizarTableroYInterfaz();

    contadorMovimientos = 0; // Reinicia el contador de movimientos
    actualizarContadorMovimientos(); // Actualiza el contador en la interfaz
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
    private javax.swing.JButton solucion;
    private javax.swing.JButton trece;
    private javax.swing.JButton tres;
    private javax.swing.JButton uno;
    private javax.swing.JButton vacio;
    // End of variables declaration//GEN-END:variables
}
