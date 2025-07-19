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
            String TARGET_URL = "http://localhost/login.php";
            URL url = new URL(TARGET_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            // Configuration de la requête POST
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            
            // Envoi des paramètres
            String params = "login=admin&password=" + URLEncoder.encode(password, "UTF-8");
            try (OutputStream os = conn.getOutputStream()) {
                os.write(params.getBytes());
                os.flush();
            }
            
            int responseCode = conn.getResponseCode();
            conn.disconnect();
            
            return responseCode == HttpURLConnection.HTTP_OK;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;}
        }
}
