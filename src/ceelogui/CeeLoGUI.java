/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ceelogui;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author sean
 */
public class CeeLoGUI extends Application {
    
    
    @Override    
public void start(final Stage primaryStage){
Writer writer = null;
    
    
//initialize state of game
final GameState game = new GameState();
game.set_start(false);

//Read player names (1 player for now)
Scanner scanner = new Scanner(System.in);
final Player Player1 = new Player("Player1");
final Player Player2 = new Player("Player2");
game.Player1 = Player1;
game.Player2 = Player2;
game.Player1.set_turn(true);

final Die GameDie1 = new Die(); //need Die Object to create instance of LoadedDie
final Die GameDie2 = new Die(); //need Die Object to create instance of LoadedDie
final Die GameDie3 = new Die(); //need Die Object to create instance of LoadedDie
final Die[] Dice = {GameDie1, GameDie2, GameDie3};



//set up GUI
final Button btn = new Button("Roll Dice");
final VBox root = init_gui(game, btn);


btn.setOnAction(new EventHandler(){
            @Override
            public void handle(Event event) {
                Player active_player = new Player();
                    //check who's turn it is
                    if(game.Player1.get_turn()){
                        game.Player1.roll_dice(Dice[0], Dice[1], Dice[2]);
                        System.out.println("Player 1's Turn");
                        System.out.println("Player 1 rolled " + game.Player1.get_roll().toString());
                        game.log += ("\nPlayer 1's turn! \n");
                        game.log += ("Player 1 rolled " + game.Player1.get_roll().toString() + "\n");
                        
                        active_player = game.Player1;
                    }
                    if(game.Player2.get_turn()){
                        game.Player2.roll_dice(Dice[0], Dice[1], Dice[2]);
                        System.out.println("\nPlayer 2's Turn");
                        System.out.println("Player 2 rolled " + game.Player2.get_roll().toString());
                        game.log += ("\nPlayer 2's turn! \n");
                        game.log += ("Player 2 rolled " + game.Player2.get_roll().toString() + "\n");
                        
                        active_player = game.Player2;
                    }
                    
                    //Change turns if necessary
                    if(active_player.get_roll().is_valid_combo())
                        game.switch_turns();
                    
                    //updates die visuals in GUI
                    update_dice(root,active_player, Player1, Player2);
                    
                    //check for instant win or loss
                    if (game.Player1.rolled_instant_win()) {
                        System.out.println("\nPlayer 1 Rolled instant win\n");
                        game.Player1.incr_wins();
                        game.reset();
                        update_gui_score(root, game.Player1, game.Player2, game);
                        game.Player1.clear_score();
                        game.Player2.clear_score();
                    } else if (game.Player1.rolled_instant_loss()) {
                        System.out.println("\nPlayer 1 Rolled instant loss\n");
                        game.Player2.incr_wins();
                        game.reset();
                        update_gui_score(root, game.Player1, game.Player2, game);
                    } else if (game.Player2.rolled_instant_win()) {
                        System.out.println("\nPlayer 2 Rolled instant win\n");
                        game.Player2.incr_wins();
                        game.reset();
                        update_gui_score(root, game.Player1, game.Player2, game);
                    } else if (game.Player2.rolled_instant_loss()) {
                        System.out.println("\nPlayer 2 Rolled instant win\n");
                        game.Player1.incr_wins();
                        game.reset();
                        update_gui_score(root, game.Player1, game.Player2, game);
                    } 
                        //Check for Pairs or Trips
                    game.update(game.Player1,game.Player2);
                    update_gui_score(root, game.Player1, game.Player2, game);
                        
                        
                    System.out.println("Player 1's wins: " + game.Player1.get_wins());
                    System.out.println("Player 2's wins: " + game.Player2.get_wins());
                    game.log += ("Player 1's wins: " + game.Player1.get_wins() + "\n");
                    game.log += ("Player 2's wins: " + game.Player2.get_wins() + "\n");
            }
        });


primaryStage.setTitle("A Game of Cee-Lo");
Scene scene = new Scene(root, 500, 400);
primaryStage.setScene(scene);
        primaryStage.show();
    };

public VBox init_gui(GameState game, Button btn){
    final VBox root = new VBox(25);
    root.setAlignment(Pos.CENTER);

    Text scenetitle = new Text("Player 1's Turn");
    scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
HBox title = new HBox(4);
title.setAlignment(Pos.TOP_CENTER);
title.getChildren().add(scenetitle);
//create and add roll button

HBox hbBtn = new HBox(10);
hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
hbBtn.getChildren().add(btn);

HBox player_status = new HBox(4);
player_status.setAlignment(Pos.CENTER);
Text Playerwins = new Text("Player1's wins: " + game.Player1.get_wins() +"\nPlayer2's wins: " + game.Player2.get_wins());
player_status.getChildren().addAll(Playerwins);

//Create Text to label the game die in GUI                    
                String Str_Die1Value = Integer.toString(0);
                Text Die1Value = new Text(Str_Die1Value);
                
                String Str_Die2Value = Integer.toString(0);
                Text Die2Value = new Text(Str_Die2Value);
                
                String Str_Die3Value = Integer.toString(0);
                Text Die3Value = new Text(Str_Die3Value);
                
                //Create Die Visuals
                
                StackPane stack1 = new StackPane();
                StackPane stack2 = new StackPane();
                StackPane stack3 = new StackPane();
                Rectangle Die_visual = new Rectangle(30,30);
                Die_visual.setStroke(Color.BLACK);
                Die_visual.setFill(Color.WHITE);
                stack1.getChildren().addAll(Die_visual, Die1Value);
                
                Rectangle Die_visual2 = new Rectangle(30,30);
                Die_visual2.setFill(Color.WHITE);
                Die_visual2.setStroke(Color.BLACK);
                stack2.getChildren().addAll(Die_visual2, Die2Value);
                
                Rectangle Die_visual3 = new Rectangle(30,30);
                Die_visual3.setFill(Color.WHITE);
                Die_visual3.setStroke(Color.BLACK);
                stack3.getChildren().addAll(Die_visual3, Die3Value);                
                
                HBox dice = new HBox(4);
                dice.setAlignment(Pos.CENTER);
                dice.getChildren().addAll(stack1,stack2,stack3);
                root.getChildren().addAll(title, player_status, dice, btn);
                return root;
}

public void update_gui_score(VBox root, Player p1, Player p2, GameState game){
    //update session number
    HBox title = new HBox(4);
    title.setAlignment(Pos.TOP_CENTER);
    title.getChildren().add(new Text(p1.get_turn()?"Player 1's Turn":"Player 2's Turn"));
    root.getChildren().set(0, title);
    
    //update player status
    HBox player_status = new HBox(4);
    player_status.setAlignment(Pos.CENTER);
    Text Playerwins = new Text("Player1 wins: " + p1.get_wins() +"\nPlayer2 wins: " + p2.get_wins());
    player_status.getChildren().addAll(Playerwins);
    root.getChildren().set(1, player_status);
    
    Writer writer = null;
    if (p1.get_wins()==5) {
        Text winStatus = new Text("Player 1 won!");
        HBox hbox = new HBox(4);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(winStatus);
        root.getChildren().set(3, hbox);
        
        try {
        writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream("Game_Log.txt"), "utf-8"));
        writer.write(game.log);
        } catch (IOException ex){
        // report
        } finally {
        try {writer.close();} catch (Exception ex) {}
        }
    }
    if (p2.get_wins()==5) {
        Text winStatus = new Text("Player 2 won!");
        HBox hbox = new HBox(4);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(winStatus);
        root.getChildren().set(3, hbox);
        
        try {
        writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream("Game_Log.txt"), "utf-8"));
        writer.write(game.log);
        } catch (IOException ex){
        // report
        } finally {
        try {writer.close();} catch (Exception ex) {}
        }
    }
}

public void update_dice(VBox root, Player active_player, Player Player1,  Player Player2){

    
                HBox player_status = new HBox(4);
                player_status.setAlignment(Pos.CENTER);
                Text Player1wins = new Text("game.Player1 wins: " + Player1.get_wins());
                Text Player2wins = new Text("game.Player2 wins: " + Player2.get_wins());
                player_status.getChildren().addAll(Player1wins,Player2wins);
    
//Create Text to label the game die in GUI                    
                String Str_Die1Value = Integer.toString(active_player.get_roll().get_die_top(1));
                Text Die1Value = new Text(Str_Die1Value);
                
                String Str_Die2Value = Integer.toString(active_player.get_roll().get_die_top(2));
                Text Die2Value = new Text(Str_Die2Value);
                
                String Str_Die3Value = Integer.toString(active_player.get_roll().get_die_top(3));
                Text Die3Value = new Text(Str_Die3Value);
                
                //Create Die Visuals
                StackPane die1 = new StackPane();
                die1.setAlignment(Pos.CENTER);
                StackPane die2 = new StackPane();
                die2.setAlignment(Pos.CENTER);
                StackPane die3 = new StackPane();
                die3.setAlignment(Pos.CENTER);
                
                Rectangle Die_visual = new Rectangle(30,30);
                Die_visual.setStroke(Color.BLACK);
                Die_visual.setFill(Color.WHITE);
                die1.getChildren().addAll(Die_visual, Die1Value);
             
                Rectangle Die_visual2 = new Rectangle(30,30);
                Die_visual2.setFill(Color.WHITE);
                Die_visual2.setStroke(Color.BLACK);
                die2.getChildren().addAll(Die_visual2, Die2Value);
                
                Rectangle Die_visual3 = new Rectangle(30,30);
                Die_visual3.setFill(Color.WHITE);
                Die_visual3.setStroke(Color.BLACK);
                die3.getChildren().addAll(Die_visual3, Die3Value);
                
                HBox dice = new HBox(4);
                dice.setAlignment(Pos.CENTER);
                dice.getChildren().addAll(die1,die2,die3);
                
                StackPane[] GraphicDiceArray = {die1,die2,die3};
                HBox dhbox = new HBox(4);
                dhbox.setAlignment(Pos.CENTER);
                dhbox.getChildren().addAll(GraphicDiceArray[0],GraphicDiceArray[1],GraphicDiceArray[2]);
                root.getChildren().set(2,dhbox);
                rollDiceArray(GraphicDiceArray);
}
    
 public void rollDiceArray (StackPane GraphicDiceArray[]){ 
            
        for (int i=0;i<GraphicDiceArray.length;i++) {
            Timeline rot = new Timeline();
            rot.setCycleCount(i+1);
            rot.setRate(1);
            rot.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(
                            GraphicDiceArray[i].rotateProperty(), 0)),
                    new KeyFrame(Duration.seconds(0.5), new KeyValue(GraphicDiceArray[i]
                            .rotateProperty(), 360)));
            rot.playFromStart();
            ScaleTransition st = new ScaleTransition(Duration.millis(500), GraphicDiceArray[i]);
            st.setByX(1.5f);
            st.setByY(1.5f);
            st.setCycleCount(2);
            st.setAutoReverse(true);
 
            st.play();
            
        }
 }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */

    
    public static void main(String[] args) {
        launch(args);
    }
}
