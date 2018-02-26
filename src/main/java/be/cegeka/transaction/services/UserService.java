package be.cegeka.transaction.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUserWithouterror(User user) {
        userRepository.save(user);
    }

    public void saveUserWithCheckedException(User user) throws Exception{
        saveUserWithouterror(user);
        throw new Exception("it failed");
    }

    public void saveUserWithUncheckedException(User user) {
        saveUserWithouterror(user);
        throw new RuntimeException("it failed");
    }
}
