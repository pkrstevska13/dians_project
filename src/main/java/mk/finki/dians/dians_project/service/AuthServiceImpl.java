package mk.finki.dians.dians_project.service;

import mk.finki.dians.dians_project.exception.InvalidCredentialException;
import mk.finki.dians.dians_project.exception.InvalidUserCredentialsException;
import mk.finki.dians.dians_project.model.User;
import mk.finki.dians.dians_project.repository.UserRepository;
import mk.finki.dians.dians_project.service.impl.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
      if( username==null || username.isEmpty() || password==null || password.isEmpty()){
         throw new InvalidCredentialException();
      }
      return userRepository.findByUsernameOrPassword(username,password).orElseThrow(InvalidUserCredentialsException::new);
    }
}
