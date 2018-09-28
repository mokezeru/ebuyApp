package cs544.project.serviceImpl;

import cs544.project.model.User;
import cs544.project.repository.IUserRepository;
import cs544.project.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void add(User user){
        userRepository.save(user);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public User get(Long id){
        Optional<User> opt = userRepository.findById(id);
        return opt.orElse(null);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
    
    //added
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User findByUsername(String name) {
    	return this.userRepository.findByName(name);
    }
}
