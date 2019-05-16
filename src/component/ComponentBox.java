/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package component;

import java.util.Objects;

/**
 *
 * @author ASUS
 */
public class ComponentBox {
    
    private int x;
    private int y;
    private ComponentBox parent;         

    public ComponentBox(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void setParent(ComponentBox parent) {
        this.parent = parent;
    }
    

    public ComponentBox getParent() {
        return parent;
    }
    

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof ComponentBox) {
            ComponentBox box = (ComponentBox) o;
            if (box.getX()==this.getX() && box.getY()==this.getY()) {
                return true;
            } 
            else {
                return false;
            }
        } 
        else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.x;
        hash = 59 * hash + this.y;
        hash = 59 * hash + Objects.hashCode(this.parent);
        return hash;
    }
}
