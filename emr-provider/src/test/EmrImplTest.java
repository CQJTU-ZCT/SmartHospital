import com.cqjtu.EmrProviderApplication;
import com.cqjtu.model.Emr;
import com.cqjtu.service.EmrServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * EmrImplTest.class
 * Created in Intelij IDEA
 * <p>
 * Write Some Describe of this class here
 *
 * @author Mevur
 * @date 01/24/18 22:59
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmrProviderApplication.class)
public class EmrImplTest {

    EmrServiceImpl emrService = new EmrServiceImpl();

    @Test
    public void insertEmr() {
        Emr emr = new Emr();
        emr.setEmrId("1");
        emr.setCreateAge(23);
        emr.setCreateTime(new Date(System.currentTimeMillis()));
        Emr nw = emrService.insert(emr);
        System.out.println(nw);
    }
}
