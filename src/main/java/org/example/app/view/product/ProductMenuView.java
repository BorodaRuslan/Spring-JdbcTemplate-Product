package org.example.app.view.product;

import org.example.app.utils.Constans;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class ProductMenuView {
    Scanner scanner = new Scanner(System.in);

    public String getOption() {
        menu();
        String option = "";
        try {
            option = scanner.nextLine().trim();
        } catch (InputMismatchException ime) {
            System.out.println(Constans.INCORRECT_VALUE_MSG);
        }
        return option;
    }

    private void menu() {
        System.out.print("""
                
                ______ MENU ___________
                1 - Create product.
                2 - View all products.
                3 - View product by id.
                4 - Update product.
                5 - Delete product.
                0 - Close the App.
                """);
    }

    public void getOutput(String output) {
        if (output.equals(Constans.APP_CLOSE_MSG))
            System.out.println(output);
        scanner.close();
        System.exit(0);
    }
}
