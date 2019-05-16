/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author ASUS
 */
public class ComponentRule {
 
    private int gridLimitX, gridLimitY;
    private Random random = new Random();        
    private List<ComponentMain> lstRule;
    private ComponentBox boxStart;
    private ComponentBox boxTarget;
    private ComponentBear bear;
    private ComponentBox[][] box;
    
    public ComponentRule(int gridLimitX, int gridLimitY) {
        this.gridLimitX = gridLimitX;
        this.gridLimitY = gridLimitY;
        
        box = new ComponentBox[gridLimitX][gridLimitY];
        
        for (int x=0; x < gridLimitX; x++) {
            for (int y=0; y<gridLimitY; y++) {
                box[x][y] = new ComponentBox(x,y);
            }
        }
        lstRule = new ArrayList<>();
        
        makeRule();
    }

    private void makeRule() {
        bear = new ComponentBear();        
        bear.setPosisiSel(box[random.nextInt(1)][random.nextInt(1)]);                
        boxStart = bear.getPosisiSel();
        lstRule.add(bear);
                
        ComponentFood food = new ComponentFood();
        
        do {
            food.setPosisiSel(box[random.nextInt(9)][random.nextInt(10)]);                
        } while (getMainByPosition(food.getPosisiSel())!=null);        
        lstRule.add(food);
        boxTarget = food.getPosisiSel();
                
        for (int i=0; i<13; i++) {
            ComponentWall wall = new ComponentWall();
            
            do {
                wall.setPosisiSel(box[random.nextInt(gridLimitX)][random.nextInt(gridLimitY)]);
            } while (getMainByPosition(wall.getPosisiSel())!=null);
            lstRule.add(wall);
        }
    }
    
    public ComponentMain getMainByPosition(ComponentBox box) {
        for (ComponentMain a : lstRule) {
            if (a.getPosisiSel().equals(box)) {
                return a;
            }
        }
        return null;
    }
    
    public ComponentMain getMain(Class<? extends ComponentMain> c) {
       for (ComponentMain actor : lstRule) {
           if (c.isAssignableFrom(actor.getClass())) {
               return actor;
           }
       }
       return null;
    }
    
    public int getGridLimitX() {
        return gridLimitX;
    }
    
    public int getGridLimitY() {
        return gridLimitY;
    }
    
    public ComponentBox getTargetLocation() {
        return boxTarget;
    }
    
    public ComponentBox getStartLocation() {
        return boxStart;
    }
    
    public ComponentBear getBear() {
        return bear;
    }
    
    public List<ComponentBox> getAroundBox(ComponentBox k) {
        
        List<ComponentBox> lstAroundBox = new ArrayList<>();
        ComponentMain actor;
        
        // Box left-
        if (k.getX()-1 >= 0) {
            actor = getMainByPosition(box[k.getX()-1][k.getY()]);
            if (actor==null || actor instanceof ComponentFood) {
                lstAroundBox.add(box[k.getX()-1][k.getY()]);
            }
        }
        
        // Kotak kanan
        if (k.getX()+1 < gridLimitX) {
            actor = getMainByPosition(box[k.getX()+1][k.getY()]);
            if (actor==null || actor instanceof ComponentFood) {
                lstAroundBox.add(box[k.getX()+1][k.getY()]);
            }
        }
        
        // Kotak atas
        if (k.getY()-1 >= 0) {
            actor = getMainByPosition(box[k.getX()][k.getY()-1]);
            if (actor==null || actor instanceof ComponentFood) {
                lstAroundBox.add(box[k.getX()][k.getY()-1]);
            }
        }
        
        // Kotak bawah
        if (k.getY()+1 < gridLimitY) {
            actor = getMainByPosition(box[k.getX()][k.getY()+1]);
            if (actor==null || actor instanceof ComponentFood) {
                lstAroundBox.add(box[k.getX()][k.getY()+1]);
            }
        }
        
        return lstAroundBox;
    }
    
    public ComponentBox getBox(int x, int y) {
        return box[x][y];
    }
    
}
