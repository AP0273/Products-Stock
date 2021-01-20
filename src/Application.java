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
                System.out.println(product.getName() + " Quantidade em estoque " + product.getNumber_stock());
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
                if(productr == null) {
                    System.err.println("o Produto de id: " +idr+"  não foi encontrado");
                    menu();
                }
                int totalstock= productr.getNumber_stock();
                if(totalstock <=0) {
                    System.err.println("Produto Indisponivel");
                    menu();
                }
                System.out.println("Digite a quantidade desejada: ");
                int amount= S.nextInt();

                if(productr.getNumber_stock() < amount) {
                    System.err.println("Quantidade em stock insuficiente deseja retirar a quantidade disponível de " + totalstock + " unidades digite SIM ou NÃO" );
                    String answer = S.next();
                    if(answer.toLowerCase().equals("sim")) {
                        buy(totalstock,productr);
                    }else
                        menu();
                }else{
                    buy(amount,productr);
                }
                break;
            default:
                System.err.println("Opção Inexistente, tente novamente");
                menu();
                break;
        }
        menu();
    }
    public static void buy (int t_amount,Products productr) {
        int total=t_amount;
        while (t_amount > 0) {
            if(productr.getStock().element().getAmount()>t_amount) {
                productr.getStock().element().setAmount( productr.getStock().element().getAmount() - t_amount);
                t_amount = 0;
            } else{
                int thisamount = productr.getStock().element().getAmount();
                t_amount -= thisamount;
                System.out.println(productr.getStock().poll().toString() + "Removido do estoque");

            }
        }
        productr.setNumber_stock(productr.getNumber_stock()-total);
        controller.saveProduct(productr);
    }
}
