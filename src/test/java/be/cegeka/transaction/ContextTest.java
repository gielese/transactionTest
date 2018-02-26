package be.cegeka.transaction;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ContextTest {

    @Test
    public void contextTest(){
        Assertions.assertThat(true).describedAs("testing if context can be created").isTrue();
    }
}
