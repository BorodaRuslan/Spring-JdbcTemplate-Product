package org.example.app.controller.impl;

import org.example.app.controller.BaseController;
import org.example.app.entity.Product;
import org.example.app.service.impl.ProductServiceImpl;
import org.example.app.utils.Constans;
import org.example.app.utils.starter.StartApp;
import org.example.app.view.product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("productController")
public class ProductControllerImpl implements BaseController {

    @Autowired
    ProductMenuView productMenu;
    @Autowired
    ProductCreateView createView;
    @Autowired
    ProductReadView readView;
    @Autowired
    ProductReadByIdView readByIdView;
    @Autowired
    ProductUpdateView updateView;
    @Autowired
    ProductDeleteView deleteView;
    @Autowired
    ProductServiceImpl service;


    public void getOption() {
        int option = Integer.parseInt(productMenu.getOption());
        switch (option) {
            case 1 -> create();
            case 2 -> getAll();
            case 3 -> getById();
            case 4 -> update();
            case 5 -> delete();
            case 0 -> productMenu.getOutput(Constans.APP_CLOSE_MSG);
        }
    }



    @Override
    public void create() {
       // updateView.getData() почему именно этот метод?
        Map<String, String> data = createView.getData();

        Product product = new Product(data.get("name"), Double.parseDouble(data.get("quota")),
                Double.parseDouble(data.get("price")));

        createView.getOutput(service.create(product));
        StartApp.startApp();

    }

    @Override
    public void getAll() {
        readView.getOutput(service.getAll());
        StartApp.startApp();
    }

    @Override
    public void getById() {
        readByIdView.getOutput(service.getById(readByIdView.getData()));
        StartApp.startApp();
    }

    @Override
    public void update() {
        Map<String, String> data = updateView.getData();
        Product product = new Product(data.get("name"), Double.parseDouble(data.get("quota")),
                Double.parseDouble(data.get("price")));
        updateView.getOutput(service.update(product));
        StartApp.startApp();
    }

    @Override
    public void delete() {
        deleteView.getOutput(service.delete(deleteView.getData()));
        StartApp.startApp();
    }
}
