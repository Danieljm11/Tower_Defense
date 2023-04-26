package elementos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class Tropa {

    private int x, y, width, height, speed, esperar;
    private String tipo, debilidad, img;
    private double daño;

    public Tropa(String tipo, int x, int y, int direccion, int esperar) {
        this.x = x;
        this.y = y;
        this.width = 20;
        this.height = 20;
        this.tipo = tipo;
        this.speed = 1 * direccion;
        this.esperar = esperar;
        img = tipo;
        
        if (tipo.equals("mago")) {
            debilidad = "arquero";
            daño = 1.5;
        }
        if (tipo.equals("caballero")) {
            debilidad = "mago";
            daño = 2;
        }
        if (tipo.equals("arquero")) {
            debilidad = "caballero";
            daño = 1;
        }
        
    } }