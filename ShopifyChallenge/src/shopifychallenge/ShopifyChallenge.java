package shopifychallenge;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ShopifyChallenge {

    static String urlStr = "http://backend-challenge-fall-2018.herokuapp.com/carts.json?id=1&page=1";
    
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
    
    public String getStringData(String urlStr) throws IOException {
        URL url = getURL(urlStr);
        Scanner sc = new Scanner(url.openStream());
        String inline = "";
        while (sc.hasNext()) {
            // read each line from the API
            inline += sc.nextLine();
        }
        sc.close();
        return inline;
    }
    
    public JSONObject getInput() {
        JSONObject input = new JSONObject();
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter id: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter discount_type");
        String discount_type = sc.nextLine();
        System.out.println("Enter discount_value");
        double discount_value = sc.nextDouble();
        sc.nextLine();
        System.out.println("Enter collection");
        String collection = sc.nextLine();
        
        input.put("id", id);
        input.put("discount_type", discount_type);
        input.put("discount_value", discount_value);
        input.put("collection", collection);
        
        return input;
    }

    public static void main(String[] args) {
        
        ShopifyChallenge s = new ShopifyChallenge();
        JSONObject input = s.getInput();
        System.out.println(input.get("id"));
        System.out.println(input.get("discount_type"));
        System.out.println(input.get("discount_value"));
        System.out.println(input.get("collection"));
        
        /*
        ShopifyChallenge s = new ShopifyChallenge();
        try {
            String result = s.getStringData(urlStr);

            // Declare an instance of the JSONParser
            JSONParser parse = new JSONParser();
            // convert string inline to Json object cart
            JSONObject cart = (JSONObject)parse.parse(result);

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