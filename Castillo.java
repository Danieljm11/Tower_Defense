package elementos;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Castillo {
   private int x, y, width, height;
   private double vida =10;
   
   public Castillo(int x, int y) {
      this.x = x;
      this.y = y;
      this.width = 50;
      this.height = 100;
   }

    public double getVida() {
        return vida;
    }
   
   public void dañado(double puntos){
       vida -=puntos;
       System.out.println(vida);
   }
   
   public void draw(Graphics g) {
      Image img = Toolkit.getDefaultToolkit().getImage("src/main/java/castillo.png");
      g.drawImage(img, x, y, width, height, null);
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
