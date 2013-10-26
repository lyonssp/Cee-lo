/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ceelogui;

/**
 *
 * @author sean
 */
public class Player {
    private String name;
    private Score roll;
    
    
    public Player(){
        this.name = "Player";
        this.roll = new Score();
    }
    
    public Player(String name){
        this.name = name;
        this.roll = new Score();
    }
    
    public Score get_roll(){
        return this.roll;
    }
    
    public void roll_dice(Die a, Die b, Die c){
        roll.set_dice(a.roll(), b.roll(), c.roll());
    }
    
    public void set_point(){
        roll.set_point();
    }
    
    //methods to check Player's roll
    public boolean rolled_trips(){
        return roll.is_trips();
    }
    
    public boolean rolled_instant_win(){
        return roll.instant_win();
    }
    
    public boolean rolled_instant_loss(){
        return roll.instant_loss();
    }
    
    public boolean rolled_pair(){
        return roll.pair();
    }
}
