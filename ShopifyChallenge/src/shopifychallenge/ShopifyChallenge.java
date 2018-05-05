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
    
    public static String getStringInput() {
        Scanner sc = new Scanner(System.in);
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
    
    public static void main(String args[] ) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        String strInput = getStringInput();
        long id;
        String discount_type, collection;
        double discount_value, cart_value, product_value;
        try {
            
            // get and parse input
            
            JSONObject jsonInput = stringToJson(strInput);
            id = (long) jsonInput.get("id");
            
            // get JSONArray products from Cart API
            JSONArray products = getJsonProducts(strUrl, id);
            
            discount_type = (String) jsonInput.get("discount_type");
            
            discount_value = (double) jsonInput.get("discount_value");
            
            if (jsonInput.keySet().contains("collection"))
                collection = (String) jsonInput.get("collection");
            if (jsonInput.keySet().contains("cart_value"))
                cart_value = (double) jsonInput.get("cart_value");
            if (jsonInput.keySet().contains("product_value"))
                product_value = (double) jsonInput.get("product_value");
            
            
            
            
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } 
    }
}