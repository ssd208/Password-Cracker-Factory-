
public class CrackerApp {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java CrackerApp [brute|dict] [local|online]");
            return;
        }

        String attackType = args[0];
        String targetType = args[1];

        CrackerFactory factory;

        if (attackType.equals("brute")) {
            factory = new BruteForceFactory(targetType);
        } else {
            factory = new DictionaryFactory(targetType);
        }

        Attack attack = factory.createAttack();
        Target target = factory.createTarget();

        attack.execute(target);
    }
}
