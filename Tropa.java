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
        
    }

    public double getDaño() {
        return daño;
    }
    
    public void setIncognito(){
        if (esperar>(2*30)){
            img="incognito";
        }
    }

    public boolean chocaCastillo(Castillo castillo) {
        if (this.getX() + this.getWidth() >= castillo.getX() && this.getX() <= castillo.getX() + castillo.getWidth()) {
            return true;
        }
        return false;
    }

    public boolean chocaTropa(Tropa enemigo) {
        if (this.getX() + this.getWidth() >= enemigo.getX()
                && this.getX() <= enemigo.getX() + enemigo.getWidth()
                && this.getY() + this.getHeight() >= enemigo.getY()
                && this.getY() <= enemigo.getY() + enemigo.getHeight()) {
            return true;
        }
        return false;
    }

    public boolean esDebilidad(String tip) {
        return debilidad.equals(tip);
    }

    public String getTipo() {
        return tipo;
    }

    public String getDebilidad() {
        return debilidad;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void draw(Graphics g) {
        Image imge = Toolkit.getDefaultToolkit().getImage("src/main/java/"+img+".png");
        g.drawImage(imge, x, y, width, height, null);
    }

    public void move() {
        if (esperar == 0) {
            x += speed;
        } else {
            esperar--;
        }
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
