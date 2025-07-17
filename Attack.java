
import java.io.*;

public interface Attack {
    void execute(Target target);
}

class BruteForceAttack implements Attack {

    public void execute(Target target) {
        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
        int maxLength = 4;

        for (int i = 0; i < Math.pow(chars.length(), maxLength); i++) {
            String password = generatePassword(i, chars, maxLength);
            if (target.tryPassword(password)) {
                System.out.println("Mot de passe trouvé : " + password);
                break;
            }
        }
    }

    private String generatePassword(int index, String chars, int length) {
        StringBuilder sb = new StringBuilder();
        while (index > 0) {
            sb.append(chars.charAt(index % chars.length()));
            index /= chars.length();
        }
        while (sb.length() < length) {
            sb.append(chars.charAt(0));
        }
        return sb.reverse().toString();
    }
}

class DictionaryAttack implements Attack {

    public void execute(Target target) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("dictionnaire.txt"));
            String password;
            while ((password = reader.readLine()) != null) {
                if (target.tryPassword(password)) {
                    System.out.println("Mot de passe trouvé : " + password);
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du dictionnaire : " + e.getMessage());
        }
    }
}
