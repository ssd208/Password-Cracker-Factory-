
public class DictionaryFactory implements CrackerFactory {

    private String targetType;

    public DictionaryFactory(String targetType) {
        this.targetType = targetType;
    }

    public Attack createAttack() {
        return new DictionaryAttack();
    }

    public Target createTarget() {
        if (targetType.equals("local")) {
            return new LocalTarget();
        } else {
            return new OnlineTarget();
        }
    }
}
