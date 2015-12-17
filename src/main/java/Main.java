import net.jertocvil.envwrapper.EnvWrapper;

public class Main {

    public static void main(String[] args) {


        TestConfig config = EnvWrapper.getConfig(TestConfig.class);

        System.out.println(config.getLang());

    }
}
