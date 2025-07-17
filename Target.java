
import java.net.*;
import java.io.*;

public interface Target {
    boolean tryPassword(String password);
}

class LocalTarget implements Target {

    private String correctPassword = "passer123";

    public boolean tryPassword(String password) {
        if (password.equals(correctPassword)) {
            System.out.println("Connexion réussie avec : " + password);
            return true;
        }
        return false;
    }
}

class OnlineTarget implements Target {

    public boolean tryPassword(String password) {
        try {
            String urlString = "http://localhost/login.php?login=admin&password=" + URLEncoder.encode(password, "UTF-8");
            URL url = new URL (urlString);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String response;
            while ((response = in.readLine()) != null) {
                if (response.contains("Connexion réussie")) {
                    System.out.println("Connexion réussie avec : " + password);
                    return true;
                }
            }
            in.close();
        } catch (Exception e) {
            System.out.println("Erreur lors de la connexion en ligne : " + e.getMessage());
        }
        return false;
    }
}
