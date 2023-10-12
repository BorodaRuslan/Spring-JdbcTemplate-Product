package org.example.app.view.product;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class ProductCreateView {
    public Map<String, String> getData(){
        Map<String, String> map = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        String title = "Input product name: ";
        System.out.print(title);
        map.put("name", scanner.nextLine().trim());
        title = "Input product quota: ";
        System.out.print(title);
        map.put("quota", scanner.nextLine().trim());
        title = "Input product price: ";
        System.out.print(title);
        map.put("price", scanner.nextLine().trim());
        return map;
    }
    public void getOutput(String output) {
        System.out.println(output);
    }
}
