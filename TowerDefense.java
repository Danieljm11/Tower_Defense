package com.mycompany.towerdefense;

import javax.swing.JFrame;

public class TowerDefense {

    public static void main(String[] args) {
        JFrame ventana = new JFrame("Castillo y tropa");
        ventana.add(new Juego());
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(600, 350);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}
