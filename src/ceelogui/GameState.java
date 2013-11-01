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
    
    public void reset(){
        Player1.set_turn(true);
        Player2.set_turn(false);
        Player1.clear_score();
        Player2.clear_score();
    }
    
    public void switch_turns(){
        Player1.set_turn(!Player1.get_turn());
        Player2.set_turn(!Player2.get_turn());
    }
 
    public void update(Player p1, Player p2){
       if(p1.get_roll().is_valid_combo()&&p2.get_roll().is_valid_combo()){        
        //handle cases of trips
        if(p1.rolled_trips() && p2.rolled_trips()){
            if(p1.get_roll().get_point()>p2.get_roll().get_point()){
                p1.incr_wins();
            }
        }else if (p1.get_roll().get_point() < p2.get_roll().get_point()){ 
                    p2.incr_wins();                
        }else if(p1.rolled_trips()){
            p1.incr_wins();
        }else if(p2.rolled_trips()){
            p2.incr_wins();
        }else if(p1.rolled_pair()&&p2.rolled_pair()){
            if(p1.get_roll().get_point() > p2.get_roll().get_point()){
                p1.incr_wins();
            }
            else if(p2.get_roll().get_point() > p1.get_roll().get_point()){
                p2.incr_wins();
            }
            else{
                System.out.println("There is a tie!");
        
            }
        }
        p1.clear_score();
        p2.clear_score();
       }
       
    }
}


    
    

