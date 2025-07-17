
public class BruteForceFactory implements CrackerFactory {

    private String targetType;

    public BruteForceFactory(String targetType) {
        this.targetType = targetType;
    }

    public Attack createAttack() {
        return new BruteForceAttack();
    }

    public Target createTarget() {
        if (targetType.equals("local")) {
            return new LocalTarget();
        } else {
            return new OnlineTarget();
        }
    }
}
