/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author ASUS
 */
public class FrameMain extends JFrame {
    
    private PanelMain panelMain;
    private JButton btnStart;
    
    public FrameMain() {
        super("Beruang Mencari Makan");
        
        panelMain = new PanelMain();
        add(panelMain, BorderLayout.CENTER);
        
        JPanel panelButton = new JPanel();
        
        btnStart = new JButton("Start");
        panelMain.setButton(btnStart);        
        panelButton.add(btnStart);
        add(panelButton, BorderLayout.SOUTH);
        
        setSize(panelMain.RESULT_GRIDX*panelMain.GRID_SIZE+20, panelMain.RESULT_GRIDY*panelMain.GRID_SIZE+100);
        
    }
    
    public static void main(String args[]) {
        FrameMain f = new FrameMain();
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);        
    }
    
    
}
