import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> stuff = new ArrayList<>();
    ArrayList<String> results = new ArrayList<>();
    String res = "";

    public String handleRequest(URI url) {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().equals("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    stuff.add(parameters[1]);
                    return "\n";
                }
            }else if (url.getPath().equals("/search")){
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    for (String str : stuff){
                        if (str.contains(parameters[1])) results.add(parameters[1]);
                    } 
                    for (String str: results) res.concat(str+", ");
                    return res;
                }
            
            }
            return "404 Not Found!";
        }
    }


public class SearchEngine

{
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
    
}
