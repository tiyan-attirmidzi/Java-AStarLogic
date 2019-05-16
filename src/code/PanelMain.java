/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import component.ComponentMain;
import component.ComponentBox;
import component.ComponentAlgoritma;
import component.ComponentRule;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.Timer;

import javax.swing.JPanel;

/**
 *
 * @author ASUS
 */
public class PanelMain extends JPanel{
 
    public final int RESULT_GRIDX = 10;
    public final int RESULT_GRIDY = 10;
    public final int GRID_SIZE = 70;

    private ComponentRule rule;
    private ComponentAlgoritma algoritma;
    private Timer timer;
    private JButton button;
    
    public void setButton(JButton button) {
        this.button = button;
        this.button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });
    }    
    
     public void start() {     
        
        if (button!=null) button.setEnabled(false);

        rule = new ComponentRule(RESULT_GRIDX, RESULT_GRIDY);
        
        algoritma = new ComponentAlgoritma(rule);
        algoritma.processAlgoritma();   

        timer = new Timer(500, new ActionListener() {


            private Iterator<ComponentBox> iter = algoritma.getListResult().iterator();
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (iter.hasNext()) {
                    rule.getBear().setPosisiSel(iter.next());
                    repaint();
                } 
                else {
                    timer.stop();
                    if (button!=null) {
                        button.setEnabled(true);
                    }
                }
            }
        });
        timer.start();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
                
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        int posisiX = 0, posisiY = 0;
        g.setColor(Color.BLACK);
        for (int y=0; y<RESULT_GRIDY; y++) {
            for (int x=0; x<RESULT_GRIDX; x++) {

                // Menggambar actor bila ada                
                if (rule!=null) {                    
                    if (algoritma.getListResult().contains(rule.getBox(x, y))) {
                        g.setColor(Color.GREEN);
                        g.fillRect(posisiX, posisiY, GRID_SIZE, GRID_SIZE);
                    }                    
                    if (rule.getStartLocation().getX() == x && rule.getStartLocation().getY() == y) {
                        g.setColor(Color.BLUE);
                        g.fillOval(posisiX, posisiY, GRID_SIZE, GRID_SIZE);
                    } else {
                        ComponentMain currentActor = null;
                        if ((currentActor = rule.getMainByPosition(rule.getBox(x, y)))!=null) {                    
                            g.drawImage(currentActor.getImage(), posisiX, posisiY, GRID_SIZE, GRID_SIZE, this);
                        }
                    }
                    
                }
                
                // Menggambar border kotak
                g.setColor(Color.BLACK);
                g.drawRect(posisiX, posisiY, GRID_SIZE, GRID_SIZE);                
                posisiX+=GRID_SIZE;
                
            }
            posisiY+=GRID_SIZE;
            posisiX=0;
        }
    }
    
}
