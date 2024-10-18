package com.devil.Spring.controller;

import com.devil.Spring.entity.User;
import com.devil.Spring.entity.UserDTO;
import com.devil.Spring.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public User addUser(@RequestParam("name") String name,
                        @RequestParam("email") String email,
                        @RequestPart("image")MultipartFile imageFile) throws IOException {
        User user = new User();
        user.setName(name);
        user.setEmail(email);

        if(!imageFile.isEmpty()){
            byte[] imageBytes = imageFile.getBytes();
            user.setImage(imageBytes);
        }
        return userService.addUser(user);
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getUserImage(@PathVariable Long id) {
        // Find the user by id
        User user = userService.findById(id);

        // Check if user has an image
        byte[] image = user.getImage();
        if (image == null || image.length == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // No image found
        }

        // Set headers for the image response
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "image/jpeg"); // or "image/png" depending on the format
        headers.set("Content-Disposition", "inline; filename=\"user_image.jpg\"");

        // Return the image in the response
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }
    @GetMapping("/{id}/data")
    public ResponseEntity<UserDTO> getUserData(@PathVariable Long id) {
        // Find the user by id
        User user = userService.findById(id);

        // Convert image byte[] to Base64 string
        String base64Image = "";
        if (user.getImage() != null && user.getImage().length > 0) {
            base64Image = Base64.getEncoder().encodeToString(user.getImage());
        }

        // Create a UserDTO to hold the data
        UserDTO userDTO = new UserDTO(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                base64Image
        );

        // Return the DTO with user data and image
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }


    @GetMapping("/{id}")
    public User findById(@PathVariable Long id){
       return userService.findById(id);
    }

    @GetMapping("/search/{name}")
    public  User findByUserName(@PathVariable String name){
       return userService.findByName(name);
    }

    @PutMapping("/update/{id}")
    public User userUpdateById(@RequestBody User user , @PathVariable Long id){
        return  userService.updateUserById(user , id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        return " User Deleted ";
    }



}
