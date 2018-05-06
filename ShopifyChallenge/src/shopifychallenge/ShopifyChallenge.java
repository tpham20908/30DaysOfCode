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

    static String baseStrUrl = "http://backend-challenge-fall-2018.herokuapp.com/carts.json?id=";
    
    public static URL getURL(String strUrl) 
            throws MalformedURLException, IOException {
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
    
    public static String getStringCart(String baseStrUrl, long id, int pageNo) 
            throws IOException {
        URL url = getURL(baseStrUrl + id + "&page=" + pageNo);
        Scanner sc = new Scanner(url.openStream());
        return getStringInput(sc);
    }
        
    public static JSONObject stringToJson(String str) throws ParseException {
        // Declare an instance of the JSONParser
        JSONParser parse = new JSONParser();
        // convert string str to Json object cart
        JSONObject result = (JSONObject)parse.parse(str);
        return result;
    }
    
    public static String getStringInput(Scanner sc) {
        String strInput = "";
        while (sc.hasNext()) {
            strInput += sc.next();
        }
        sc.close();
        return strInput;
    }
    
    public static JSONArray getJsonProducts(String strUrl, long id) 
            throws IOException, ParseException {
        JSONArray allProducts = new JSONArray();
        int page = 1;
        while (true) {
            String currentStrCart = getStringCart(strUrl, id, page);
            JSONObject currentJsonCart = stringToJson(currentStrCart);
            JSONArray currentProducts = (JSONArray) currentJsonCart.get("products");
            if (currentProducts.isEmpty()) 
                break;
            allProducts.addAll(currentProducts);
            page++;
        }
        
        return allProducts;
    }
    
    public static void processCart_collection_discount(double discount_value, 
            String collection, JSONArray products) {
        double product_price;
        double total_amount = 0.0;
        double total_discount = 0.0;
        double total_after_discount = 0.0;
        String product_collection;
        
        for (int i = 0, s = products.size(); i < s; i++) {
            JSONObject product = (JSONObject) products.get(i);
            product_price = (double) product.get("price");
            
            // assign productCollection to key collection if exists
            product_collection = product.keySet().contains("collection") ?
                    (String) product.get("collection") : "";
            
            total_amount += product_price;
            if (product_collection.equals(collection)) {
                total_discount += 
                        product_price > discount_value ? discount_value : product_price;
            }
        }
        
        total_after_discount = total_amount - total_discount;
        System.out.printf("{\n  \"total_amount\": " + total_amount +  
                    ", \n  \"total_after_discount\": " + total_after_discount + " \n}");
    }
    
    public static void processCart_product_value_discount(double discount_value, 
            double product_value, JSONArray products) {
        double product_price;
        double total_amount = 0.0;
        double total_discount = 0.0;
        double total_after_discount = 0.0;
                
        for (int i = 0, s = products.size(); i < s; i++) {
            JSONObject product = (JSONObject) products.get(i);
            product_price = (double) product.get("price");
            
            total_amount += product_price;
            if (product_price > product_value) {
                total_discount += 
                        product_price > discount_value ? discount_value : product_price;
            }
        }
    
        total_after_discount = total_amount - total_discount;
        
        System.out.printf("{\n  \"total_amount\": " + total_amount +  
                    ", \n  \"total_after_discount\": " + total_after_discount + " \n}");
    }
    
    public static void processCart_cart_value_discount(double discount_value, 
            double cart_value, JSONArray products) {
        double product_price;
        double total_amount = 0.0;
        double total_discount = 0.0;
        double total_after_discount = 0.0;
                
        for (int i = 0, s = products.size(); i < s; i++) {
            JSONObject product = (JSONObject) products.get(i);
            product_price = (double) product.get("price");
            
            total_amount += product_price;
        }
        
        total_discount =
                total_amount >= cart_value ? discount_value : 0;
    
        total_after_discount = total_amount - total_discount;
        
        System.out.printf("{\n  \"total_amount\": " + total_amount +  
                    ", \n  \"total_after_discount\": " + total_after_discount + " \n}");
    }
    
    public static void main(String args[] ) throws FileNotFoundException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        // declare variables to map user's input
        // Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new File("test3.txt"));
        long id;
        String discount_type, collection;
        double discount_value, cart_value, product_value;
        // declare variable to map queried product from cart API
        String productName, productCollection;
        double productPrice;
        
        String strInput = getStringInput(sc);
        try {
            
            JSONObject jsonInput = stringToJson(strInput);
            id = (long) jsonInput.get("id");
            
            JSONArray products = getJsonProducts(baseStrUrl, id);
            
            discount_type = (String) jsonInput.get("discount_type");
            
            discount_value = (double) jsonInput.get("discount_value");
            
            if (jsonInput.keySet().contains("collection")) {
                collection = (String) jsonInput.get("collection");
                processCart_collection_discount(discount_value, collection, products);
            }
            
            if (jsonInput.keySet().contains("cart_value")) {
                cart_value = (double) jsonInput.get("cart_value");
                processCart_cart_value_discount(discount_value, cart_value, products);
            }
            
            if (jsonInput.keySet().contains("product_value")) {
                product_value = (double) jsonInput.get("product_value");
                processCart_product_value_discount(discount_value, product_value, products);
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