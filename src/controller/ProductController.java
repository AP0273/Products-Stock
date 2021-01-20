package controller;

import model.Products;

import java.util.ArrayList;
import java.util.Locale;

public class ProductController {

    protected ArrayList<Products> products;

    public ProductController(ArrayList<Products> products){
        this.products = products;
    }

    public Products findById(int id){
        for (Products product : products) {
            if (product.getId() == id) {
                return product;
            }
        }

        return null;
    }
    public void saveProduct(Products product){
     products.set(product.getId(),product);
    }

    public ArrayList<Products> findByNameRootsWay(String targetName) {

        ArrayList<Products> found = new ArrayList();

        for (Products p : products) {
            if (targetName.equals(p.getName())) {
                System.out.println("\n\n\n YES \n\n\n");
            }
        }

        return null;
    }

    public ArrayList<Products> findByName(String name){

        ArrayList<Products> found = new ArrayList();

        for (Products p : products){
            if (p.getName().toLowerCase().contains(name.toLowerCase())){
                found.add(p);
            }
        }

        return found;
    }


    public ArrayList<Products> get() {

        for (Products p : products) {

            System.out.println("\t id : "+p.getId()+"\t name : "+p.getName());

        }

        return null;
    }
}


