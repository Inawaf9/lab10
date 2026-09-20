package com.nawaf.lab10.Service;

import com.nawaf.lab10.Model.User;
import com.nawaf.lab10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public int newUser(User user){
        userRepository.save(user);
        return 0;
    }

    public int updateUser(Integer id, User user){
        User foundUser = userRepository.findUserById(id);
        if(foundUser == null) return 1;

        User emailExist = userRepository.findUserByEmail(user.getEmail());

        if(emailExist != null) return 2;

        foundUser.setName(user.getName());
        foundUser.setEmail(user.getEmail());
        foundUser.setPassword(user.getPassword());
        foundUser.setAge(user.getAge());
        foundUser.setRole(user.getRole());

        userRepository.save(foundUser);
        return 0;
    }

    public int deleteUser(Integer id){
        User foundUser = userRepository.findUserById(id);
        if(foundUser == null) return 1;

        userRepository.delete(foundUser);
        return 0;
    }
}
