package repository;

import model.Products;
import model.StockProduct;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;


public class Load {
    protected ArrayList<Products> products;
    public static final String splitcsvregex = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
    public void run() {

        String csvpath = System.getProperty("user.dir")+"\\Products.csv";
        BufferedReader br = null;
        String line = "";

        try {
            ArrayList<Products> records = new ArrayList();

            br = new BufferedReader(new FileReader(csvpath));
            csvSplitter(br.readLine());

            while ((line = br.readLine()) != null) {

                System.out.println("\n\n"+line);

                String[] row = line.split(splitcsvregex);

                records.add(new Products(Integer.parseInt(row[0].trim()), row[21],randomstockinsert()));

            }

            this.setProducts(records);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void csvSplitter(String firstLine) {

        String[] SplitedFirstLine = firstLine.split(splitcsvregex);

        for (int i = 0; i < SplitedFirstLine.length; i++) {
//            System.out.print("\t\t" + i);
//            System.out.print(" : "+SplitedFirstLine[i]);
        }

    }

    public ArrayList<Products> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Products> products) {
        this.products = products;
    }
    public static int randomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }
    public Queue<StockProduct> randomstockinsert() {
        Queue<StockProduct> stock = new LinkedList<>();
        int iterations = randomIntBetween(1,20);
        for(int i = 0; i<iterations; i++) {
            stock.add(new StockProduct(randomIntBetween(1, 100), randomDate(2020, 2021)));
        }
        return stock;
    }
    public static LocalDate randomDate(int startYear, int endYear) {
        int day = randomIntBetween(1, 28);
        int month = randomIntBetween(1, 12);
        int year = randomIntBetween(startYear, endYear);
        return LocalDate.of(year, month, day);
    }


}
