package com.mycompany.towerdefense;

import ListaEnlazada.SoundPlayer;
import elementos.Tropa;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Random;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Juego extends JPanel implements ActionListener {

    private JButton boton;
    private JRadioButton radioArquero, radioMago, radioCaballero, radioCamino1, radioCamino2;
    private ButtonGroup grupoTropa, grupoCamino;
    public Boolean moverse = false;
    public int fps = 0;
    public Jugador jugador = new Jugador(1);
    public Jugador CPU = new Jugador(2);
    public int cantidadTropas = 4, partida = 1;
    SoundPlayer sound = new SoundPlayer("src/main/java/pelea.wav");

    public Juego() {
        boton = new JButton("Empezar");
        boton.setBounds(getWidth() / 2 - 50, getHeight() / 2 - 15, 100, 30);
        add(boton);
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sound.play();
                if ((cantidadTropas - jugador.getTropas().tamaño() <= 0)) {
                    moverse = true;
                } else {
                    JOptionPane.showMessageDialog(null, "No se puede empezar a mover hasta que seleccione todas las tropas");
                }
            }
        });

        grupoTropa = new ButtonGroup();
        grupoCamino = new ButtonGroup();

        radioMago = new JRadioButton("mago", false);
        radioMago.setBounds(500, 400, 50, 50);

        radioArquero = new JRadioButton("arquero", false);
        radioArquero.setBounds(500, 400, 50, 50);

        radioCaballero = new JRadioButton("caballero", false);
        radioCaballero.setBounds(500, 400, 50, 50);

        radioCamino1 = new JRadioButton("Camino 1", false);
        radioCamino1.setBounds(500, 400, 50, 50);

        radioCamino2 = new JRadioButton("Camino 2", false);
        radioCamino2.setBounds(500, 400, 50, 50);

        grupoTropa.add(radioMago);
        grupoTropa.add(radioArquero);
        grupoTropa.add(radioCaballero);
        grupoCamino.add(radioCamino1);
        grupoCamino.add(radioCamino2);

        add(radioMago);
        add(radioArquero);
        add(radioCaballero);
        add(radioCamino1);
        add(radioCamino2);

        boton = new JButton("Agregar");
        boton.setBounds(getWidth() / 8 - 50, getHeight() / 2 - 15, 100, 30);
        add(boton);
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tropaSeleccion = "";
                int caminoSeleccion = 0;
                if (radioMago.isSelected()) {
                    tropaSeleccion = "mago";
                } else if (radioArquero.isSelected()) {
                    tropaSeleccion = "arquero";

                } else if (radioCaballero.isSelected()) {
                    tropaSeleccion = "caballero";
                }

                if (radioCamino1.isSelected()) {
                    caminoSeleccion = 1;

                } else if (radioCamino2.isSelected()) {
                    caminoSeleccion = 2;
                }

                if (!moverse) {
                    if (cantidadTropas - jugador.getTropas().tamaño() > 0) {
                        if (!"".equals(tropaSeleccion) && caminoSeleccion != 0) {
                            jugador.añadirTropa(tropaSeleccion, caminoSeleccion);
                            JOptionPane.showMessageDialog(null, "Agregado, te quedan " + Integer.toString(cantidadTropas - jugador.getTropas().tamaño()) + " tropas");

                            String[] options = {"caballero", "mago", "arquero"};

                            Random random = new Random();
                            String randomTropa = options[random.nextInt(options.length)];
                            int randomCamino = random.nextInt(2) + 1;

                            CPU.añadirTropa(randomTropa, randomCamino);
                        } else {
                            JOptionPane.showMessageDialog(null, "Selecciona un Camino y Tropa");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se puede agregar más tropas");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se puede agregar mientras se mueve");
                }

            }
        });

    }

    @Override
    public void paintComponent(Graphics g) {
        fps++;

        super.paintComponent(g);
        jugador.getCastillo().draw(g);
        CPU.getCastillo().draw(g);

        int i;
        for (i = 0; i < jugador.getTropas().tamaño(); i++) {
            Tropa tropa = jugador.getTropas().obtener(i);
            tropa.draw(g);
        }

        for (i = 0; i < CPU.getTropas().tamaño(); i++) {
            Tropa tropa = CPU.getTropas().obtener(i);
            tropa.draw(g);
        }

        if (fps == 3) {
            if (this.moverse) {

                for (i = 0; i < CPU.getTropas().tamaño(); i++) {
                    Tropa tropa = CPU.getTropas().obtener(i);
                    tropa.move();
                    if (tropa.chocaCastillo(jugador.getCastillo())) {
                        jugador.getCastillo().dañado(tropa.getDaño());
                        CPU.getTropas().eliminar(tropa);
                    }

                }
                for (i = 0; i < jugador.getTropas().tamaño(); i++) {
                    Tropa tropa1 = jugador.getTropas().obtener(i);
                    for (int j = 0; j < CPU.getTropas().tamaño(); j++) {
                        Tropa tropa2 = CPU.getTropas().obtener(j);
                        if (tropa1.chocaTropa(tropa2)) {
                            if (tropa1.esDebilidad(tropa2.getTipo())) {
                                jugador.getTropas().eliminar(tropa1);
                            }
                            if (tropa2.esDebilidad(tropa1.getTipo())) {
                                CPU.getTropas().eliminar(tropa2);
                            }
                        }
                    }
                }
                for (i = 0; i < jugador.getTropas().tamaño(); i++) {
                    Tropa tropa = jugador.getTropas().obtener(i);
                    tropa.move();
                    if (tropa.chocaCastillo(CPU.getCastillo())) {
                        CPU.getCastillo().dañado(tropa.getDaño());
                        jugador.getTropas().eliminar(tropa);
                    }

                }
                if (jugador.getTropas().tamaño() == 0 && CPU.getTropas().tamaño() == 0) {
                    sound.stop();
                    if (CPU.getCastillo().getVida() <= 0) {
                        JOptionPane.showMessageDialog(null, "Ganaste el juego, iniciando otro juego");
                        partida = 0;
                    } else if (jugador.getCastillo().getVida() <= 0) {
                        JOptionPane.showMessageDialog(null, "Perdiste  el juego, iniciando otro juego");
                        partida = 0;
                    } else {
                        partida += 1;
                        JOptionPane.showMessageDialog(null, "A tu oponente le quedan " + Double.toString(CPU.getCastillo().getVida()) + " puntos de vida");
                    }
                    cantidadTropas = partida + 4;
                    moverse = false;
                }
            }

            fps = 0;
        }
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
