/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ceelogui;
import ceelogui.Die;
import ceelogui.Die.LoadedDie;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author sean
 */
public class CeeLoGUI extends Application {
    
    
    @Override    
public void start(final Stage primaryStage) {

//initialize state of game
final GameElements game = new GameElements();
game.set_start(false);

//Read player names (1 player for now)
Scanner scanner = new Scanner(System.in);
System.out.println("Player 1:  Please Enter Your Name");
final Player Player1 = new Player(scanner.toString());

//Read in user input about the game die
System.out.println("How many sides would you like the die to have?");
final int sides = scanner.nextInt();

final Die GameDie1 = new Die(); //need Die Object to create instance of LoadedDie
final Die GameDie2 = new Die(); //need Die Object to create instance of LoadedDie
final Die GameDie3 = new Die(); //need Die Object to create instance of LoadedDie
final Die[] Dice = {GameDie1, GameDie2, GameDie3};



//congifure grid
primaryStage.setTitle("A Game of Cee-Lo");
final GridPane grid = new GridPane();
grid.setPrefSize(400, 400);
grid.setAlignment(Pos.CENTER);
grid.setHgap(10);
grid.setVgap(10);
grid.setPadding(new Insets(25,25,25,25));
Text scenetitle = new Text("Cee-lo");
scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
grid.add(scenetitle, 0, 0, 2, 1);

//create and add roll button
Button btn = new Button("Roll Dice");

HBox hbBtn = new HBox(10);
hbBtn.setAlignment(Pos.BOTTOM_CENTER);
hbBtn.getChildren().add(btn);

btn.setOnAction(new EventHandler(){
            @Override
            public void handle(Event event) {
                int[] DieValues = new int[3];
                
                for(int i = 0; i < 3; i++){
                Dice[i].value = Dice[i].roll();
                DieValues[i] = Dice[i].value;
                Player1.set_score(DieValues);
                }
                
                if()
                
                //Create Text to label the game die in GUI
                String Str_Die1Value = Integer.toString(GameDie1.value);
                Text Die1Value = new Text(Str_Die1Value);
                
                String Str_Die2Value = Integer.toString(GameDie2.value);
                Text Die2Value = new Text(Str_Die2Value);
                
                String Str_Die3Value = Integer.toString(GameDie3.value);
                Text Die3Value = new Text(Str_Die3Value);
                
                //Create Die Visual
                StackPane stack1 = new StackPane();
                StackPane stack2 = new StackPane();
                StackPane stack3 = new StackPane();
                Rectangle Die_visual = new Rectangle(30,30);
                Die_visual.setFill(Color.WHITE);
                Die_visual.setStroke(Color.BLACK);
                stack1.getChildren().addAll(Die_visual, Die1Value);
                grid.add(stack1, 2, 4, 2, 2);
                
                Rectangle Die_visual2 = new Rectangle(30,30);
                Die_visual2.setFill(Color.WHITE);
                Die_visual2.setStroke(Color.BLACK);
                stack2.getChildren().addAll(Die_visual2, Die2Value);
                grid.add(stack2, 4, 4, 2, 2);
                
                Rectangle Die_visual3 = new Rectangle(30,30);
                Die_visual3.setFill(Color.WHITE);
                Die_visual3.setStroke(Color.BLACK);
                stack3.getChildren().addAll(Die_visual3, Die3Value);
                grid.add(stack3, 6, 4, 2, 2);
            }
            
        });

grid.add(hbBtn, 10, 10);

Scene scene = new Scene(grid, 500, 400);
primaryStage.setScene(scene);
        primaryStage.show();
    }
;
    
    

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
