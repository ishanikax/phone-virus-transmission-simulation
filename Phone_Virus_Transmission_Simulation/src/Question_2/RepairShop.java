package Question_2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class RepairShop{
    private int totalPhones;
    private int totalInfectedPhones;
    private int totalPhonesInRepair;
    
    public void draw(Graphics g, int totalPhones, int totalInfectedPhones, int totalPhonesInRepair) {
        this.totalPhones = totalPhones;
        this.totalInfectedPhones = totalInfectedPhones;
        this.totalPhonesInRepair = totalPhonesInRepair;

    g.setColor(new Color(200, 200, 200));
    g.fillRect(55, 75, 100, 100);
    

    g.setColor(new Color(100, 100, 100));
    g.drawRect(55, 75, 100, 100);
    

    g.setColor(new Color(50, 50, 50));
    Font font = new Font("Times New Roman", Font.BOLD, 18);
    g.setFont(font);
    g.drawString("REPAIR SHOP", 52, 60);
    
    g.setFont(new Font("Times New Roman", Font.BOLD, 14));
    
    g.setColor(Color.BLUE); 
    g.drawString("Total Mobile Phones: " + totalPhones, 58, 210);
    
    g.setColor(Color.RED); 
    g.drawString("Total Infected Phones: " + totalInfectedPhones, 58, 240);
    
    g.setColor(Color.BLACK); 
    g.drawString("Total Phones Going to Repair: " + totalPhonesInRepair, 58, 270);
    
    
}
 
}
