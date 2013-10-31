/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ceelogui;

/**
 *
 * @author sean
 */
public class Score {
    private int dievalue1;
    private int dievalue2;
    private int dievalue3;
    private int point;

    
    public Score(){
        this.dievalue1 = 0;
        this.dievalue2 = 0;
        this.dievalue3 = 0;
        this.point = 0;
    }
    
    //non-default constructor
    public Score(int a, int b, int c, int point){
        this.dievalue1 = a;
        this.dievalue2 = b;
        this.dievalue3 = c;
        this.point = point;
        
    }
    
    @Override
    public String toString(){
        return (dievalue1 + " " + dievalue2 + " " + dievalue3);
    }
    
    public void clear_score(){
        this.dievalue1 = 0;
        this.dievalue2 = 0;
        this.dievalue3 = 0;
        this.point = 0;
    }
    
    public void set_dice(int a, int b, int c){
        this.dievalue1 = a;
        this.dievalue2 = b;
        this.dievalue3 = c;
    }
    
    //returns value of die i
    public int get_die_top(int i){
        if(i == 1)
            return dievalue1;
        if(i == 2)
            return dievalue2;
        if(i == 3)
            return dievalue3;
        else
            throw new IllegalArgumentException("That Die does not exist");
    }
    
     //Check if dice roll is 4-5-6
    public boolean instant_win(){
        if(this.dievalue1!=4&&this.dievalue2!=4&&this.dievalue3!=4)
            return false;
        else if(this.dievalue1!=5&&this.dievalue2!=5&&this.dievalue3!=5)
            return false;
        else if(this.dievalue1!=6&&this.dievalue2!=6&&this.dievalue3!=6)
            return false;
        else
            return true;
    }
    
    public boolean instant_loss(){
        if(this.dievalue1!=1&&this.dievalue2!=1&&this.dievalue3!=1)
            return false;
        else if(this.dievalue1!=2&&this.dievalue2!=2&&this.dievalue3!=2)
            return false;
        else if(this.dievalue1!=3&&this.dievalue2!=3&&this.dievalue3!=3)
            return false;
        else
            return true;
    }
    
    

    public boolean is_trips(){
        point = dievalue1;
        return (this.dievalue1==this.dievalue2)
                        &&(this.dievalue2==this.dievalue3);
    }             
    
    public boolean is_pair(){
        int[] freq = new int[6];
        int[] freqfreq = new int [4];
        freq[dievalue1-1]++;
        freq[dievalue2-1]++;
        freq[dievalue3-1]++;
        for (int i: freq) freqfreq[i]++;
        if (freqfreq[2]==1) {
            for (int i=0; i<6; i++) {
                if (freq[i]==1) { point = i+1; break; }
            }
            return true;
        }
        return false;
    }
    
    public boolean is_valid_combo(){
        return this.is_trips()||this.is_pair();   
    }
    
    public int get_point(){
        if(this.dievalue1 == this.dievalue2)
            return dievalue3;
        else if(this.dievalue2 == this.dievalue3)
            return dievalue1;
        else if(this.dievalue1 == this.dievalue3)
            return dievalue2;
        else
            return 0;
    }
    
    public void set_point(){
        this.point = this.get_point();  
    }

    






}



