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

    public boolean is_trips(){
        return (this.dievalue1==this.dievalue2)
                        &&(this.dievalue2==this.dievalue3);
    }             
    
    

    






}



