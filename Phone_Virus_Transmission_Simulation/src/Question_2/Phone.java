package Question_2;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Phone extends Thread implements Comparable<Phone> {
    int x = 0;
    int y = 0;
    int vx = 0;
    int vy = 0;
    int delay = 10; //This line delays in miliseconds for movement. 
    int width; //This line is the width of the phones movement area.
    int height;//This line is the height of the phones movement area.

    //These are the infection status variables.
    boolean isInfected;
    private Timer infectedTimer;
    boolean moveToRepair;

    //Constructor to initialise a phone.
    public Phone() {
        x = 0;
        y = 0;
        vx = (int) (Math.random() * 3) - 1; 
        vy = (int) (Math.random() * 3) - 1;
        isInfected = false;
        infectedTimer = new Timer();
        moveToRepair = false;
        
    }
    
    //This methods sets the movement range of the phone.
    public void setRange(int width, int height) {
        this.width = width;
        this.height = height;
    }

    // This is the run method fore the ohones thread
    public void run() {
        move();
    }

    //This method is to move the phone within its range. 
    public void move() {
        if (x > width || x < 0)
            vx *= -1; //This reverses the x velocity if hitting boundaries.
        if (y > height || y < 0)
            vy *= -1; //This reverses the y velocity if hitting boundaries. 

        try {
            Thread.sleep(delay); //This pauses thread for delay miliseconds. 
        } catch (InterruptedException ex) {
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
        }

        x += vx; //This updates the x position.
        y += vy;//This updates the y position
    }

    public void setIsInfected(boolean infected) {
        isInfected = infected;
    }

    public boolean isInfected() {
        return isInfected;
    }

    private long countdown; 

    //This method starts the infection timer. 
    public void startTimer() {
        infectedTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                setIsInfected(false);
            }
        }, 10000);  

        countdown = 10000;
        
        
    }

    public long getCountdown() {
        return countdown;
    }

    //This method decreases the countdown value by a specified time. 
    public void decreaseCountdown(long time) {
        countdown -= time;
        if (countdown <= 0) {
            countdown = 0;
            setIsInfected(false);
        }
    }

    public boolean toRepair() {
        return moveToRepair;
    }

    //This method calculates the distance between one phone and another. 
     public boolean virusDistance(Phone otherPhone) {
        double distance = Math.sqrt(Math.pow((this.x - otherPhone.x), 2) + Math.pow((this.y - otherPhone.y), 2));
        return distance < 20; 
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    void setInfected(boolean b) {
        boolean infected = false;
        isInfected = infected;

    }
    
    //This is a method for comparing phones based on their total position. 
    public int compareTo(Phone o) {
        return Integer.compare(this.x + this.y, o.x + o.y);
    }
}

