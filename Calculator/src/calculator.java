import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class calculator{
    Color gray = new Color(212,212,210);
    Color drkGray = new Color(80,80,80);
    Color black = new Color(28,28,28);
    Color green = new Color(76,183,23);

    String [] buttons = {
        "AC" , "+/-" , "%" , "/" ,
        "7" , "8" , "9" , "x" ,
        "4" , "5" , "6" , "-" ,
        "1" , "2" , "3" , "+" ,
        "0" , "." , "√" , "=" ,
    };
    String [] rightSideButtons = {"/" , "x" , "-" , "+" , "="};
    String [] topButtons = {"AC" , "+/-" , "%"};

    String num1 = null , num2 = null , op = null , inputNum = "";

    JFrame frame = new JFrame("Calculator");
    JPanel DisplayPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();
    JLabel label = new JLabel();

    calculator(){
        frame.setSize(360 , 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        label.setBackground(black);
        label.setForeground(Color.white);
        label.setFont(new Font("Arial" , Font.PLAIN , 60));
        label.setHorizontalAlignment(JLabel.RIGHT);
        label.setText("0");
        label.setOpaque(true);

        DisplayPanel.setLayout(new BorderLayout());
        frame.add(DisplayPanel.add(label), BorderLayout.NORTH);

        buttonsPanel.setLayout(new GridLayout(5 , 4));
        buttonsPanel.setBackground(black);
        frame.add(buttonsPanel);

        for (int i = 0 ; i < buttons.length ; i++){
            JButton button = new JButton();
            button.setFont(new Font("Arial" , Font.PLAIN , 25));
            button.setText(buttons[i]);
            button.setFocusable(false);
            button.setBorder(new LineBorder(black));
            if (Arrays.asList(topButtons).contains(buttons[i])){
                button.setBackground(gray);
            }
            else if (Arrays.asList(rightSideButtons).contains(buttons[i])){
                button.setBackground(green);
            }
            else{
                button.setBackground(drkGray);
                button.setForeground(Color.white);
            }
            buttonsPanel.add(button);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    JButton button = (JButton)e.getSource();
                    String value = button.getText();
                    if (Arrays.asList(rightSideButtons).contains(value)){
                        if (value == "="){
                            if (op == "√" && inputNum != ""){
                                double result = Math.sqrt(Double.valueOf(inputNum));
                                doubleOrIntChecker(result);
                            }
                            else{
                                if (num1 != null){
                                    num2 = label.getText();
                                }
                                double a = Double.valueOf(num1);
                                double b = Double.valueOf(num2);
                                doubleOrIntChecker(operation(a,b));
                                clearAll();
                            }
                        }
                        else if ("/x-+".contains(value)){
                            if (num1 == null){
                                num1 = label.getText();
                                label.setText("0");
                            }
                            op = value;
                        }
                    }
                    else if (Arrays.asList(topButtons).contains(value)){
                        if (value == "AC"){
                            clearAll();
                            label.setText("0");
                        }
                        else if (value == "+/-"){
                            double num = Double.parseDouble(label.getText());
                            num *= -1;
                            doubleOrIntChecker(num);
                        }
                        else{
                            double num = Double.parseDouble(label.getText());
                            num /= 100;
                            doubleOrIntChecker(num);
                        }
                    }
                    else{
                        if (value == "."){
                            if (!label.getText().contains(value)){
                                label.setText(label.getText() + value);
                            }

                        }
                        else if ("0123456789".contains(value)){
                            if (label.getText() == "0"){
                                label.setText(value);
                                inputNum += value;
                            }
                            else{
                                label.setText(label.getText() + value);
                                inputNum += value;
                            }
                        }
                        else if ("√".contains(value)){
                            clearAll();
                            label.setText("√");
                            op = "√";
                        }
                    }
                }
            });
            frame.setVisible(true);
        }
    }
    double operation(double a , double b){
        double result = 0;
        if (op == "+"){
            result =  a + b;
        }
        else if (op == "-"){
            result = a - b;
        }
        else if (op == "/"){
            result = a / b;
        }
        else{
            result = a * b;
        }
        return result;
    }
    void doubleOrIntChecker(double num){
        if (num % 1 == 0){
            label.setText(Integer.toString((int)num));
        }
        else{
            label.setText(Double.toString(Math.round(num * 100.0) / 100.0));
        }
    }

    void clearAll(){
        num1 = null ; num2 = null ; op = null ; inputNum = "";
    }
}
