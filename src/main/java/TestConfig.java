import net.jertocvil.envwrapper.annotations.OptionalVar;
import net.jertocvil.envwrapper.annotations.VarName;

public interface TestConfig {
    @OptionalVar
    @VarName("LANG")
    String getLang();
}
