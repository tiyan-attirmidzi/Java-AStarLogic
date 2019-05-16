/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package component;

import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public abstract class ComponentMain {

    private ComponentBox positionSel;
    private Image image;
 
    public ComponentMain(String siteImage) {
        try {
            image = ImageIO.read(getClass().getResource(siteImage));
        } 
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error [" + ex.getMessage() + "]", "Message", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public ComponentBox getPosisiSel() {
        return positionSel;
    }
    
    public void setPosisiSel(ComponentBox positionSel) {
        this.positionSel = positionSel;
    }
    
    public Image getImage() {
        return image;
    }
    
}
