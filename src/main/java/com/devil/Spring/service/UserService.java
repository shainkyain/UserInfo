package com.devil.Spring.service;

import com.devil.Spring.entity.User;
import com.devil.Spring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user){
        if(user.getImage() != null){
            byte image[] = user.getImage();
            if(image.length == 0){
                throw new RuntimeException("Image is empty, Please provide valid image");
            }
            int maxImageSize = 2*1024*1024 ;
            if(image.length > maxImageSize){
                throw new RuntimeException("Entered Image is too big keep it in 2MB of size");
            }

        }else{
            throw new RuntimeException("Image is not provided");
        }

        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User findByName(String name){
        return userRepository.findByName(name);
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("No Such USER found"));
    }

    public User updateUserById(User user, Long id){
         User ExistingUser = userRepository.findById(id).orElseThrow(()-> new RuntimeException("wrong userId"));

         ExistingUser.setName(user.getName());
         ExistingUser.setEmail(user.getEmail());
            userRepository.save(ExistingUser);
         return ExistingUser;

    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

}
