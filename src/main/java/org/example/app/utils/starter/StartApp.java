package org.example.app.utils.starter;

import org.example.app.configuration.AppConfig;
import org.example.app.controller.impl.ProductControllerImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StartApp {

    public static void startApp(){

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductControllerImpl productController = context.getBean(ProductControllerImpl.class);
        productController.getOption();

        context.close();

    }
}
