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
    private boolean turn; //true if it is this player's turn
    private int wins;
    
    public Player(){
        this.name = "Player";
        this.roll = new Score();
        this.turn = false;
        
    }
    
    public Player(String name){
        this.name = name;
        this.roll = new Score();
    }
    
    public void clear_score(){
        this.roll.clear_score();
    }
    
    public String toString(){
        return (name + " rolled " + roll.toString());
    }
    
    public boolean equals(Object o){
        if(o == null)
            return false;
        if(!(o instanceof Player))
            return false;
        else
            return this.name == ((Player) o).name;
    }
    
    public String get_name() {
        return this.name;
    }
    
    public int get_wins(){
        return this.wins;
    }
    
    public void incr_wins(){
        this.wins++;
    }
    
     public boolean get_turn(){
        return this.turn;
    }
    
    public void set_turn(boolean b){
        this.turn = b;
    }
    
    public Score get_roll(){
        return this.roll;
    }
    
    public void roll_dice(Die a, Die b, Die c){
        roll.set_dice(a.roll(), b.roll(), c.roll());
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
        return roll.is_pair();
    }
}
