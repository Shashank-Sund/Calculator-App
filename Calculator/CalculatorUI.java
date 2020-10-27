
package Calculator;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;

public class CalculatorUI extends Application {
    
        //All buttons
        Button btn0 = new Button("0");
        Button btn1 = new Button("1");
        Button btn2 = new Button("2");
        Button btn3 = new Button("3");
        Button btn4 = new Button("4");
        Button btn5 = new Button("5");
        Button btn6 = new Button("6");
        Button btn7 = new Button("7");
        Button btn8 = new Button("8");
        Button btn9 = new Button("9");
        Button btnAdd = new Button("+");
        Button btnSubtract = new Button("-");
        Button btnMultiply = new Button("*");
        Button btnDivide = new Button("/");
        Button btnEquals = new Button("=");
        Button btnSave = new Button("Save");
        Button btnLoad = new Button("Load");
        Button btnClear = new Button("Clear Expression");
        Button btnClearTick = new Button("Clear Ticker");
        
        //outputboxes
        TextArea typeInBox = new TextArea();
        TextArea outputBox = new TextArea();
    
    @Override
    public void start(Stage primaryStage) {
        
        GridPane overallPane = new GridPane();
        overallPane.setAlignment(Pos.CENTER);
        
        typeInBox.setPrefHeight(5);
        outputBox.setPrefHeight(50);
        typeInBox.setEditable(false);
        outputBox.setEditable(false);
        
        overallPane.add(typeInBox, 0, 0, 4, 1);
        overallPane.add(btn1, 0, 1);
        overallPane.add(btn2, 1, 1);
        overallPane.add(btn3, 2, 1);
        overallPane.add(btnAdd, 3, 1);
        overallPane.add(btn4, 0, 2);
        overallPane.add(btn5, 1, 2);
        overallPane.add(btn6, 2, 2);
        overallPane.add(btnSubtract, 3, 2);
        overallPane.add(btn7, 0, 3);
        overallPane.add(btn8, 1, 3); 
        overallPane.add(btn9, 2, 3);
        overallPane.add(btnDivide, 3, 3);
        overallPane.add(btn0, 0, 4,2,1);
        overallPane.add(btnEquals, 2, 4); 
        overallPane.add(btnMultiply, 3, 4);
        overallPane.add(btnSave, 5, 5);
        overallPane.add(btnLoad, 6, 5);
        overallPane.add(btnClear, 7, 5);
        overallPane.add(btnClearTick, 8, 5);
        overallPane.add(outputBox, 4, 0, 50, 5);
        
        btn0.setMinSize(100, 50);
        btn1.setMinSize(50, 50);
        btn2.setMinSize(50, 50);
        btn3.setMinSize(50, 50);
        btn4.setMinSize(50, 50);
        btn5.setMinSize(50, 50);
        btn6.setMinSize(50, 50);
        btn7.setMinSize(50, 50);
        btn8.setMinSize(50, 50);
        btn9.setMinSize(50, 50);
        btnEquals.setMinSize(50, 50);
        btnAdd.setMinSize(50, 50);
        btnSubtract.setMinSize(50, 50);
        btnMultiply.setMinSize(50, 50);
        btnDivide.setMinSize(50, 50);
        btnSave.setMinSize(50, 25);
        btnLoad.setMinSize(50, 25);
        btnClear.setMinSize(50, 25);
        btnClearTick.setMinSize(50, 25);
        outputBox.setMinSize(200, 4);
        typeInBox.setMaxSize(200, 40);

        Scene primaryScene = new Scene(overallPane, 700, 400);
        
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Calculator");
        primaryStage.show();
        
        //Event Handling
        
        btn0.setOnAction(e -> {
            
            typeInBox.appendText("0");

        });
        
        btn1.setOnAction(e -> {
            
            typeInBox.appendText("1");

        });
        
        btn2.setOnAction(e -> {
            
            typeInBox.appendText("2");

        });
        
        btn3.setOnAction(e -> {
            
            typeInBox.appendText("3");

        });
        
        btn4.setOnAction(e -> {
            
            typeInBox.appendText("4");

        });
        
        btn5.setOnAction(e -> {
            
            typeInBox.appendText("5");

        });
        
        btn6.setOnAction(e -> {
            
            typeInBox.appendText("6");

        });
        
        btn7.setOnAction(e -> {
            
            typeInBox.appendText("7");

        });
        
        btn8.setOnAction(e -> {
            
            typeInBox.appendText("8");

        });
        
        btn9.setOnAction(e -> {
            
            typeInBox.appendText("9");

        });
        
        btnAdd.setOnAction(e -> {
            
            typeInBox.appendText("+");

        });
        
        btnSubtract.setOnAction(e -> {
            
            typeInBox.appendText("-");

        });
        
        btnMultiply.setOnAction(e -> {
            
            typeInBox.appendText("*");

        });
        
        btnDivide.setOnAction(e -> {
            
            typeInBox.appendText("/");

        });
        
        //clears current expression
        btnClear.setOnAction(e -> {
            
            typeInBox.clear();

        });
        
        //clears ticker area
        btnClearTick.setOnAction(e -> {
            
            outputBox.clear();

        });
        
        //Saves ticker data
        btnSave.setOnAction(e -> {
            
            try
            {
               String data = outputBox.getText();
               outputBox.clear();
               DataOutputStream output = new DataOutputStream(new FileOutputStream("ticker.dat"));
               output.writeUTF(data);
               output.close();
            }
            catch(IOException ioex)
            {}

        });
        
        //Loads from ticker data
        btnLoad.setOnAction(e -> {
            
            outputBox.clear();
            try
            {
                DataInputStream input = new DataInputStream(new FileInputStream("ticker.dat"));
                outputBox.appendText(input.readUTF());
            }
            catch(IOException ioex)
            {}

        });
        
        //Equals button to perform calculation
        btnEquals.setOnAction(e -> {
            
            String inputStr = typeInBox.getText();
            typeInBox.clear();
            
            try
            {
                int answer = solve(inputStr);
                outputBox.appendText(inputStr + " = " + answer + "\n");
            }
            catch(Exception ex)
            {
                outputBox.appendText(inputStr + " is not a proper expression! \n");
            }

        });

    }
    
    public static int solve(String problem)
    {
        //stacks for numbers and operators
        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();
        
        //adds blank spaces in between numbers and operators
        problem = splitByBlanks(problem);
        //splits numbers and operators by each space and stores each element into an array
        String [] blanksArray = problem.split(" ");
        
        //processes the expressions elements after splitting it by blanks
        for (String e: blanksArray)
        {
            if(e.length() == 0)
                //restarts the for loop at the next element
                continue; 
            else if(e.charAt(0) =='+' || e.charAt(0) =='-')
            {
                // Processes all addition and subtraction
                //checks to see what the operator at the top of the operator stack is
                while (!ops.isEmpty() && (ops.peek() == '+' || ops.peek() == '-' || ops.peek() == '*' || ops.peek() == '/'))
                { 
                    processOperator(nums, ops); 
                }
                ops.push(e.charAt(0));
            }
            else if(e.charAt(0) =='*' || e.charAt(0) =='/')
            {
                //processes all multiplication and division
                while (!ops.isEmpty() && (ops.peek() == '*' || ops.peek() == '/'))
                { 
                    processOperator(nums, ops); 
                }
                ops.push(e.charAt(0));
            }
            else
            {
                //converts the current element to an integer if it is not an opeator
                //puches it to the top of the number stack
                nums.push(new Integer(e));
            }
        }
        
        //while the operator stack is not empty keep calculating after order of ops has been done
        while(!ops.isEmpty()) 
        {
            processOperator(nums, ops);
        }
        
        //returns the last remaining value in the number stack as the answer
        return nums.pop();
    }
    
    //splits the original math expression by spaces to account for multiple digit numbers
    public static String splitByBlanks(String ille)
    {
        //initialize the string
        String blanks = "";
        
        for (int i=0; i<ille.length(); i++)
        {
            //if it is an operator put a space before and after it and add it to the new string
            if(ille.charAt(i) == '*' || ille.charAt(i) == '/' || ille.charAt(i) == '+' || ille.charAt(i) == '-')
                blanks += " " + ille.charAt(i) +" ";
            else
                blanks += ille.charAt(i);
        }
        return blanks;
    }
    
    //process the operator according to order of operations
    public static void processOperator(Stack <Integer> nums, Stack <Character> ops)
    {
        //pops elements from top of stacks to localize the sub expression
        char operator = ops.pop();
        int num1 = nums.pop();
        int num2 = nums.pop();
        
        //performs calculation based on operator value
        switch (operator)
        {
            case '*':
                nums.push(num2 * num1);
                break;
            case '/':
                nums.push(num2 / num1);
                break;
            case '+':
                nums.push(num2 + num1);
                break;
            case '-':
                nums.push(num2 - num1);
                break;
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
