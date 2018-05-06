package shopifychallenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ShopifyChallenge {

    static String strUrl = "http://backend-challenge-fall-2018.herokuapp.com/carts.json?id=";    //1&page=1
    
    public static URL getURL(String strUrl) throws MalformedURLException, IOException {
        URL url = new URL(strUrl);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        // set request type
        conn.setRequestMethod("GET");
        // open connection
        conn.connect();
        // get the corresponding response code
        int responsecode = conn.getResponseCode();
        // check response code
        if (responsecode != 200)
            throw new RuntimeException("HttpResponseCode: " + responsecode);
        return url;
    }
    
    public static String getStringCart(String strUrl, long id) throws IOException {
        URL url = getURL(strUrl + id);
        Scanner sc = new Scanner(url.openStream());
        String inline = "";
        while (sc.hasNext()) {
            // read each line from the API
            inline += sc.nextLine();
        }
        sc.close();
        return inline;
    }
        
    public static JSONObject stringToJson(String str) throws ParseException {
        // Declare an instance of the JSONParser
            JSONParser parse = new JSONParser();
            // convert string inline to Json object cart
            JSONObject result = (JSONObject)parse.parse(str);
            return result;
    }
    
    public static String getStringInput() throws FileNotFoundException {
        //Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new File("test3.txt"));
        String strInput = "";
        while (sc.hasNext()) {
            strInput += sc.next();
        }
        return strInput;
    }
    
    public static JSONArray getJsonProducts(String strUrl, long id) 
            throws IOException, ParseException {
        // get cart API
        String strCart = getStringCart(strUrl, id);
        JSONObject jsonCart = stringToJson(strCart);
        JSONArray products = (JSONArray) jsonCart.get("products");
        return products;
    }
    
    public static long getTotal(String strUrl, long id) throws IOException, ParseException {
        long total;
        String strCart = getStringCart(strUrl, id);
        JSONObject jsonCart = stringToJson(strCart);
        JSONObject pagination = (JSONObject) jsonCart.get("pagination");
        total = pagination.keySet().contains("total") ? 
                (long) pagination.get("total") : 1;
        return total;
    }
    
    public static void processCart(String discount_type, double discount_value, 
            String collection, long total, JSONArray products) {
        double productPrice;
        double total_amount = 0.0;
        double total_discount = 0.0;
        double total_after_discount = 0.0;
        String productCollection;
        
        for (int i = 0, s = products.size(); i < s; i++) {
            JSONObject product = (JSONObject) products.get(i);
            productPrice = (double) product.get("price");
            
            // assign productCollection to key collection if exists
            productCollection = product.keySet().contains("collection") ?
                    (String) product.get("collection") : "";
            
            total_amount += productPrice;
            if (productCollection.equals(collection)) {
                total_discount += discount_value;
            }
        }
        total_amount *= total;
        total_discount *= total;
        total_after_discount = total_amount - total_discount;
        System.out.printf("{ \"total_amount\": " + total_amount +  
                    ", \"total_after_discount\": " + total_after_discount + "}");
    }
    
    public static void processCart(String discount_type, double discount_value, 
            double product_value, long total, JSONArray products) {
        double productPrice;
        double total_amount = 0.0;
        double total_discount = 0.0;
        double total_after_discount = 0.0;
        
        System.out.println(discount_type);
        System.out.println(discount_value);
        System.out.println(product_value);
        System.out.println(total);
        
        
        for (int i = 0, s = products.size(); i < s; i++) {
            JSONObject product = (JSONObject) products.get(i);
            
            System.out.println(product);
            
            productPrice = (double) product.get("price");
            
            
            if (discount_type.equals("product") && productPrice >= product_value) {
                total_amount += productPrice;
                total_discount += discount_value;
            }
            System.out.println(total_amount);
            System.out.println(total_discount);
        }
        total_amount *= total;
        total_discount *= total;
        total_after_discount = total_amount - total_discount;
        
        System.out.printf("{ \"total_amount\": " + total_amount +  
                    ", \"total_after_discount\": " + total_after_discount + "}");
    }
    
    public static void main(String args[] ) throws FileNotFoundException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        String strInput = getStringInput();
        long id;
        String discount_type, collection;
        double discount_value, cart_value, product_value;
        
        // declare variable to map queried product from cart API
        String productName, productCollection;
        double productPrice;
        
        try {
            
            // get and parse input
            
            JSONObject jsonInput = stringToJson(strInput);
            id = (long) jsonInput.get("id");
            
            // get JSONArray products from Cart API
            JSONArray products = getJsonProducts(strUrl, id);
            // get total products
            long total = getTotal(strUrl, id);
            
            discount_type = (String) jsonInput.get("discount_type");
            
            discount_value = (double) jsonInput.get("discount_value");
            
            if (jsonInput.keySet().contains("collection")) {
                collection = (String) jsonInput.get("collection");
                processCart(discount_type, discount_value, collection, total, products);
            }
            
            if (jsonInput.keySet().contains("cart_value"))
                cart_value = (double) jsonInput.get("cart_value");
            if (jsonInput.keySet().contains("product_value")) {
                product_value = (double) jsonInput.get("product_value");
                processCart(discount_type, discount_value, product_value, total, products);
            }
            
            
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } 
    }
}