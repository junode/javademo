package extension;

import com.junode.SpringBootDemoApp;
import com.junode.autowired_extentsion.PositionDo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.junode.autowired_extentsion.PositionAppService;
/**
 * @author junode
 * @version 1.0.0
 * @Description
 * @createTime 2024年04月12日 00:08:00
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDemoApp.class)
public class SpringExtensionTest {

    @Autowired
    private PositionAppService appService;

    @Test
    public void positionBuildTest() {
        PositionDo positionDo = appService.build();
    }
}
