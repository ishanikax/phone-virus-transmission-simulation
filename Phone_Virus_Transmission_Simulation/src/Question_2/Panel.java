package Question_2;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Panel extends JPanel implements KeyListener, ComponentListener {

    private static final long serialVersionUID = 1L;
    ArrayList<Phone> phonesList = new ArrayList<>();
    private JFrame frame;
    private Image repair;
    private Image infected;
    private Image healthy;
    private Timer repairTime;
    private boolean repairShopFull;
    private Phone phone;
    private final Object repairLock = new Object();

    //This is the constructor for Panel which sets up initial configuration. 
    public  Panel(JFrame frame) {
        this.frame = frame;
        phone = new Phone();
        phone.setRange(frame.getWidth(), frame.getHeight());
        this.addKeyListener(this);
        this.addComponentListener(this);
        this.setFocusable(true);

        //These lines load the images of the phones indicating their different status. 
        healthy = new ImageIcon("phones/healthy.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
        repair = new ImageIcon("phones/repairing.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
        infected = new ImageIcon("phones/infected.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);

        //This is the timer for moving phones and checking for infections. 
        Timer timer = new Timer(1, t -> {
            synchronized (phonesList) {
                for(Phone phones : phonesList) {
                    phones.move();
                }
            }
            repaint();
        });
        
        timer.start();
        
        //This is the timer for managing phone repair process. 
        repairTime = new Timer(10000, t -> {
            synchronized (repairLock) {
                if(repairShopFull == true) {
                    for (Phone currentPhone : phonesList) {
                        if (currentPhone.moveToRepair) {
                            System.out.println(currentPhone + "---fixed!!");
                            currentPhone.moveToRepair = false;
                            currentPhone.setInfected(false);
                            break;
                        }
                    }
                    repairShopFull = false;
                }

                for (Phone currentPhone : phonesList) {
                    if (currentPhone.isInfected && repairShopFull == false) {
                        currentPhone.moveToRepair = true;
                        repairShopFull = true;
                        repairTime.restart();
                        break;
                    }
                }
            }
            repaint();
        });
        repairTime.start();
}


    //This is the method used to paint the phones in the repair shop. 
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        RepairShop repairShop = new RepairShop();
        repairShop.draw(g, phonesList.size(), countInfectedPhones(), countPhonesInRepair());

        Iterator<Phone> iterator = phonesList.iterator();
        while (iterator.hasNext()) {
            Phone currentPhone = iterator.next();
            
            if (currentPhone.isInfected() && currentPhone.getCountdown() <= 0) {
                iterator.remove(); 
                continue; 
            }

            if (currentPhone.toRepair()) {
                g.drawImage(repair, 70, 100, this);
            } else if (currentPhone.isInfected()) {
                g.drawImage(infected, currentPhone.getX(), currentPhone.getY(), this);

                currentPhone.decreaseCountdown(10); 

                if (currentPhone.getCountdown() <= 0) {
                    iterator.remove();
                    continue;
                }
            } else {
                g.drawImage(healthy, currentPhone.getX(), currentPhone.getY(), this);
            }

            if (currentPhone.isInfected() && currentPhone.toRepair()) {
                g.drawString("Life: " + currentPhone.getCountdown(), 70, 100);
            } else if (currentPhone.isInfected()) {
                g.drawString("Life: " + currentPhone.getCountdown(), currentPhone.getX(), currentPhone.getY());
            }

            for (Phone otherPhone : phonesList) {
                if (currentPhone != otherPhone && !currentPhone.isInfected() && otherPhone.isInfected()) {
                    if (currentPhone.virusDistance(otherPhone)) {
                        currentPhone.setIsInfected(true);
                        currentPhone.startTimer();
                        repaint();
                        break; 
                    }
                }
            }
        }
    }
    
    //This method counts for the number of infected phones. 
    private int countInfectedPhones() {
    int count = 0;
    for (Phone phone : phonesList) {
        if (phone.isInfected()) {
            count++;
        }
    }
    return count;
}

    //This method counts the number of phones in repair. 
    private int countPhonesInRepair() {
        int count = 0;
        for (Phone phone : phonesList) {
            if (phone.toRepair()) {
                count++;
            }
        }
        return count;
}


    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
    if (ke.getKeyCode() == KeyEvent.VK_V) {
        if (!phonesList.isEmpty()) {
            int randomI = (int) (Math.random() * phonesList.size());
            Phone randomPhone = phonesList.get(randomI);
            randomPhone.setIsInfected(true);
            randomPhone.startTimer();
            repaint();
        }
    } else if (ke.getKeyCode() == KeyEvent.VK_UP) {
        int randomX = (int) (Math.random() * frame.getWidth());
        int randomY = (int) (Math.random() * frame.getHeight());

        System.out.println("----Adding new Phone object-----: ");
        System.out.println("Before adding: phonesList size =" + phonesList.size());

        Phone addNewPhone = new Phone();
        addNewPhone.setRange(frame.getWidth(), frame.getHeight());
        addNewPhone.x = randomX;
        addNewPhone.y = randomY;

        synchronized (phonesList) {
            phonesList.add(addNewPhone);
        }

        Thread thread = new Thread(addNewPhone);
        thread.start();

        System.out.println("After adding: phonesList size = " + phonesList.size());
        System.out.println("Here are the new Phone coordinates: x = " + randomX + ", y = " + randomY);

        repaint();
        try {
            Thread.sleep(20);
        } catch (InterruptedException ex) {
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

    @Override
    public void keyReleased(KeyEvent ke) {

    }

    @Override
    public void componentResized(ComponentEvent ce) {
        Phone phone = new Phone(); 
        phone.setRange(frame.getWidth(), frame.getHeight()); 
    }

    @Override
    public void componentMoved(ComponentEvent ce) {

    }

    @Override
    public void componentShown(ComponentEvent ce) {

    }

    @Override
    public void componentHidden(ComponentEvent ce) {

    }
}