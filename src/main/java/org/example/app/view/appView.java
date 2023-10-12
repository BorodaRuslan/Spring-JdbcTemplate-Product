package org.example.app.view;

import org.example.app.utils.Constans;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;
@Component
public class appView {

    //Todo: Why do not work menu?
    Scanner scanner = new Scanner(System.in);

    public String getOptions(){
        menu();
        String option = "";
        try {
            option = scanner.nextLine().trim();
        } catch (InputMismatchException i){
            System.out.println(Constans.INCORRECT_VALUE_MSG);
        }
        return option;
    }

    // Menu can expand!
    private void menu(){
        System.out.println("""
                 Choose an option
                 ----------------
                 
                 1.Work with product
                 0.Close the App
                 
                 Menu can expand!
            """);
    }

    public void getOutput(String output){
        if (output.equals(Constans.APP_CLOSE_MSG))
            System.out.println(output);
        scanner.close();
        System.exit(0);
    }
}
