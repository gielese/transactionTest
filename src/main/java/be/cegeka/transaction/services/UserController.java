package be.cegeka.transaction.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

@Controller
@Transactional
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceDifferentTransactional userServiceDifferentTransactional;

    public void saveUserWithoutError(){
        userService.saveUserWithouterror(new User("Jantje", "Verbeken", "Won de lotto."));
    }

    public void saveUserWithCheckedException() throws Exception{
        userService.saveUserWithCheckedException(new User("Jantje", "Verbeken", "Won de lotto."));
    }

    public void saveUserWithUncheckedException() {
        userService.saveUserWithUncheckedException(new User("Jantje", "Verbeken", "Won de lotto."));
    }

    public void saveUserWrappingCheckedInsideOfAnUncheckedException(){
        try {
            userService.saveUserWithCheckedException(new User("Jantje", "Verbeken", "Won de lotto."));

        } catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }

    public void saveUserWrappingUncheckedInsideOfACheckedException() throws Exception{
        try {
            userService.saveUserWithUncheckedException(new User("Jantje", "Verbeken", "Won de lotto."));

        } catch (RuntimeException exception){
            throw new Exception(exception);
        }
    }

    public void saveUserOtherTransactionalServiceWithoutError() {
        userServiceDifferentTransactional.saveUserWithouterror(new User("Jantje", "Verbeken", "Won de lotto."));
    }

    public void saveUserOtherTransactionalServiceWithCheckedException() throws Exception{
        userServiceDifferentTransactional.saveUserWithCheckedException(new User("Jantje", "Verbeken", "Won de lotto."));
    }

    public void saveUserOtherTransactionalServiceWithUncheckedException() {
        userServiceDifferentTransactional.saveUserWithUncheckedException(new User("Jantje", "Verbeken", "Won de lotto."));
    }

    public void saveUserOtherTransactionalServiceWrappingUncheckedInsideOfACheckedException() throws Exception{
        try {
            userServiceDifferentTransactional.saveUserWithUncheckedException(new User("Jantje", "Verbeken", "Won de lotto."));

        } catch (RuntimeException exception){
            throw new Exception(exception);
        }
    }

    public void saveUserOtherTransactionalServiceWrappingCheckedInsideOfAnUncheckedException(){
        try {
            userServiceDifferentTransactional.saveUserWithCheckedException(new User("Jantje", "Verbeken", "Won de lotto."));

        } catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }
}