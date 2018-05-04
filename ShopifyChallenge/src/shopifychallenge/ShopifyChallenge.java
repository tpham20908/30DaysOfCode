package shopifychallenge;

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

    //static String urlStr = "http://backend-challenge-fall-2018.herokuapp.com/carts.json?id=1&page=1";
    static String urlStr = "http://backend-challenge-fall-2018.herokuapp.com/carts.json?id=";
    
    public static URL getURL(String urlStr) throws MalformedURLException, 
            IOException {
        URL url = new URL(urlStr);
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
    
    public String getStringCart(String urlStr, Long id) throws IOException {
        URL url = getURL(urlStr + id);
        Scanner sc = new Scanner(url.openStream());
        String inline = "";
        while (sc.hasNext()) {
            // read each line from the API
            inline += sc.nextLine();
        }
        sc.close();
        return inline;
    }
        
    public JSONObject stringToJson(String str) throws ParseException {
        // Declare an instance of the JSONParser
            JSONParser parse = new JSONParser();
            // convert string inline to Json object cart
            JSONObject result = (JSONObject)parse.parse(str);
            return result;
    }
    
    public String getStringInput(String str) {
        Scanner sc = new Scanner(str);
        String inputStr = "";
        while (sc.hasNext()) {
            inputStr += sc.next();
        }
        return inputStr;
    }

    public static void main(String[] args) {
        
        ShopifyChallenge s = new ShopifyChallenge();
        
        String str1 = "{ \"id\": 1, \"discount_type\": \"product\", \"discount_value\": 5.0, \"collection\": \"Lifestyle\" }";
        String str2 = "{ \"id\": 2, \"discount_type\": \"cart\", \"discount_value\": 50.0, \"cart_value\": 250.0 }";
        String str3 = "{ \"id\": 3, \"discount_type\": \"product\", \"discount_value\": 20.0, \"product_value\": 15.0 }";
        
        String inputStr = s.getStringInput(str2);
        Long id;
        String discount_type = "";
        double discount_value = 0.0;
        String collection = "";
        double cart_value = 0.0;
        double product_value = 0.0;
        
        JSONObject input = new JSONObject();
        try {
            input = s.stringToJson(inputStr);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        
        id = (Long) input.get("id");
        discount_type = (String) input.get("discount_type");
        discount_value = (double) input.get("discount_value");
        
        System.out.println(input.get("id"));
        System.out.println(input.get("discount_type"));
        System.out.println(input.get("discount_value"));
        if (input.get("collection") != null) {
            collection = (String) input.get("collection");
            System.out.println(collection);
        }
        if (input.get("cart_value") != null) {
            cart_value = (double) input.get("cart_value");
            System.out.println(cart_value);
        }
        if (input.get("product_value") != null) {
            product_value = (double) input.get("product_value");
            System.out.println(product_value);
        }
        
        /*
        try {
            
            JSONObject input = s.stringToJson(inputStr);
            
            Long id = (Long) input.get("id");
            
            String cartStr = s.getStringCart(urlStr, id);

            JSONObject cart = s.stringToJson(cartStr);

            // get produtcs array
            JSONArray products = (JSONArray) cart.get("products");

            // get data from products array
            for (int i = 0; i < products.size(); i++) {
                JSONObject product = (JSONObject)products.get(i);
                String name = (String)product.get("name");
                double price = (double)product.get("price");
                String collection = (String)product.get("collection");
                System.out.printf("Name: %s\nPrice: %.2f\nCollection: %s\n", 
                        name, price, collection);
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        */
    }
}