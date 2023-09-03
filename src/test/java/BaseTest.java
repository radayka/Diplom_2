
import org.junit.BeforeClass;
import static stellaburgers.api.help.TestConfig.initConfig;

public class BaseTest {
    @BeforeClass
    public static void setUp() {
        initConfig();
    }
}
