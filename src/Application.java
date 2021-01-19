import controller.ProductController;
import model.Products;
import repository.Load;

import java.util.ArrayList;
import java.util.Scanner;

public class Application {

    private static ProductController controller;

    public static void main(String[] args) {

        Load data = new Load();
        data.run();
        controller = new ProductController(data.getProducts());
        menu();
    }
    public static void menu() {
        System.out.println("1 –Buscar produto por código.\n2 –Buscar produto por nome.\n3 –Retirar Produtos por Código");
        Scanner S = new Scanner(System.in);
        int opt = S.nextInt();
        switch (opt) {
            case 1:
                System.out.println("Digite o codigo do produto: ");
                int id = S.nextInt();
                Products product = controller.findById(id);
                if(product == null) {
                    System.err.println("o Produto de id: " +id+"  não foi encontrado");
                menu();
                }
                System.out.println(product.getName());
                break;
            case 2:
                System.out.println("Digite o nome do Produto: ");
                String name = S.next();
                ArrayList<Products> product_list = controller.findByName(name);
                if(product_list.isEmpty()) {
                    System.err.println("Nenhum Produto com o nome "+name+ " foi encontrado");
                    menu();
                }
                product_list.forEach(Products -> System.out.println(Products.getName()));
                break;
            case 3:
                System.out.println("Digite o codigo do produto: ");
                int idr = S.nextInt();
                Products productr = controller.findById(idr);
                break;
            default:
                System.err.println("Opção Inexistente, tente novamente");
                menu();
                break;
        }
        menu();
    }
}
