/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ceelogui;

/**
 *
 * @author sean
 */
public class GameState{
    //Single Instance Variable
    private boolean start;
    public Player Player1;
    public Player Player2;
    public String log;
    
    public GameState(){
        this.start = false;
    }
    
    public void set_start(boolean p){
        start = p;
    }
    
    public boolean get_start(){
        return this.start;
    }
    
    public void reset_turns(){
        Player1.set_turn(true);
        Player2.set_turn(false);
    }
 
    public void update(Player p1, Player p2){
        //handle cases of trips
        if(p1.rolled_trips() && p2.rolled_trips()){
            if(p1.get_roll().get_point()>p2.get_roll().get_point())
                p1.set_wins(p1.get_wins()+1);
            else if (p1.get_roll().get_point() < p2.get_roll().get_point()) 
                p2.set_wins(p2.get_wins()+1);                
        }
        else if(p1.rolled_trips())
                p1.set_wins(p1.get_wins()+1);
        else if(p2.rolled_trips())
                p2.set_wins(p2.get_wins()+1);
                
        //handle cases of pairs
        else {
            if ((p1.get_roll().get_point()) > (p2.get_roll().get_point()))
                p1.set_wins(p1.get_wins()+1);
            else if ((p1.get_roll().get_point()) < (p2.get_roll().get_point()))
                p2.set_wins(p2.get_wins()+1);
        }
        p1.pclear_score();
        p2.pclear_score();
    }
    
    
    
}
