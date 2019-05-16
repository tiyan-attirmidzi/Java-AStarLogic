/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ComponentAlgoritma {
 
    private List<ComponentBox> lstOpen;
    private List<ComponentBox> lstClose;
    private List<ComponentBox> lstResult;
    private ComponentBox curBox;
    private ComponentRule rule;
    
    public ComponentAlgoritma(ComponentRule rule) {
        this.rule = rule;
        
        lstOpen = new ArrayList<>();
        lstClose = new ArrayList<>();
        lstResult = new ArrayList<>();
        
        curBox = rule.getBear().getPosisiSel();        
        lstOpen.add(curBox);        
        
    }
    
    public void processAlgoritma() {
        
        while (!lstOpen.isEmpty()) {
                        
            if (curBox.equals(rule.getTargetLocation())) break;
            
            List<ComponentBox> lstAroundBox = rule.getAroundBox(curBox);
            for (ComponentBox box : lstAroundBox) {
                
                if (lstClose.contains(box)) continue;
                                
                int i = lstOpen.indexOf(box);
                if (i>=0) {

                    ComponentBox oldBox = lstOpen.get(i);
                    ComponentBox oldParent = oldBox.getParent();
                    int oldCost = calculateValueG(oldBox);                    

                    oldBox.setParent(curBox);
                    if (calculateValueG(oldBox) > oldCost) {
                        oldBox.setParent(oldParent);
                    } 
                } 
                else {
                    box.setParent(curBox);
                    lstOpen.add(box);
                }
            }
                         
            lstOpen.remove(curBox);            
            lstClose.add(curBox);

            if (!lstOpen.isEmpty()) {
                curBox = Collections.min(lstOpen, new Comparator<ComponentBox>() {

                    @Override
                    public int compare(ComponentBox k1, ComponentBox k2) {
                        return calculateValueF(k1) - calculateValueF(k2);
                    }

                });                       
            }
        }

        lstResult = new ArrayList<>();  
        while (curBox.getParent()!=null) {            
            lstResult.add(curBox.getParent());
            curBox = curBox.getParent();           
        }        
        Collections.reverse(lstResult);
    }
    
    private int calculateValueG(ComponentBox box) {
        if (box.getParent()==null) {
            return 0;
        } 
        else {
            return calculateValueG(box.getParent()) + 10;
        }
    }
     
    private int calculateValueH(ComponentBox box) {
        return Math.abs(rule.getTargetLocation().getX()-box.getX()) +
               Math.abs(rule.getTargetLocation().getY()-box.getY());
    }
    
    private int calculateValueF(ComponentBox box) {
        return calculateValueG(box) + calculateValueH(box);
    }
    
    public List<ComponentBox> getListResult() {
        return lstResult;
    }
    
}
