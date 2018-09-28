package cs544.project.service;

import cs544.project.model.User;

import java.util.List;

public interface IUserService {
    public abstract List<User> findAll();
    public abstract void add(User user);
    public abstract User get(Long id);
   // public abstract void update(Long id);
    public abstract void delete(Long id);
    
    //added
    public User findByUsername(String name);
}
