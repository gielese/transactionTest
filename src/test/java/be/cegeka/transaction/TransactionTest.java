package be.cegeka.transaction;

import be.cegeka.transaction.services.UserController;
import ch.qos.logback.core.encoder.EchoEncoder;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
public class TransactionTest {

    @Autowired
    private UserController userController;

    @Autowired
    private JdbcTemplate template;

    @Before
    public void setup(){
        template.execute("delete from USER ");
    }

    @Test
    public void userSave_WithoutError(){
        userController.saveUserWithoutError();

        Assertions.assertThat(template.queryForList("select * from user")).hasSize(1);
    }

    @Test
    public void userSave_WithCheckedException() {
        try {
            userController.saveUserWithCheckedException();
        }catch (Exception ex){
        }

        Assertions.assertThat(template.queryForList("select * from user")).hasSize(0);
    }

    @Test
    public void userSave_WithUncheckedException() {
        try {
            userController.saveUserWithUncheckedException();
        }catch (RuntimeException ex){
        }

        Assertions.assertThat(template.queryForList("select * from user")).hasSize(0);
    }

    @Test
    public void userSave_OtherTransactionalService_WithoutError(){
        userController.saveUserOtherTransactionalServiceWithoutError();

        Assertions.assertThat(template.queryForList("select * from user")).hasSize(1);
    }

    @Test
    public void userSave_OtherTransactionalService_WithCheckedException() {
        try {
            userController.saveUserOtherTransactionalServiceWithCheckedException();
        }catch (Exception ex){
        }

        Assertions.assertThat(template.queryForList("select * from user")).hasSize(0);
    }

    @Test
    public void userSave_OtherTransactionalService_WithUncheckedException() {
        try {
            userController.saveUserOtherTransactionalServiceWithUncheckedException();
        }catch (RuntimeException ex){
        }

        Assertions.assertThat(template.queryForList("select * from user")).hasSize(0);
    }

    @Test
    public void userSave_ServiceThrowsUnchecked_ControllerWillWrapItInAChecked() {
        try {
            userController.saveUserWrappingUncheckedInsideOfACheckedException();
        }catch (Exception ex){
        }

        Assertions.assertThat(template.queryForList("select * from user")).hasSize(0);
    }

    @Test
    public void userSave_OtherTransactionalServiceThrowsUnchecked_ControllerWillWrapItInAChecked() {
        try {
            userController.saveUserOtherTransactionalServiceWrappingUncheckedInsideOfACheckedException();
        }catch (Exception ex){
        }

        Assertions.assertThat(template.queryForList("select * from user")).hasSize(0);
    }

    @Test
    public void userSave_ServiceThrowsChecked_ControllerWillWrapItInAnUnchecked() {
        try {
            userController.saveUserWrappingCheckedInsideOfAnUncheckedException();
        }catch (RuntimeException ex){
        }

        Assertions.assertThat(template.queryForList("select * from user")).hasSize(0);
    }

    @Test
    public void userSave_OtherTransactionalServiceThrowsChecked_ControllerWillWrapItInAnUnchecked() {
        try {
            userController.saveUserOtherTransactionalServiceWrappingCheckedInsideOfAnUncheckedException();
        }catch (RuntimeException ex){
        }

        Assertions.assertThat(template.queryForList("select * from user")).hasSize(0);
    }

}
