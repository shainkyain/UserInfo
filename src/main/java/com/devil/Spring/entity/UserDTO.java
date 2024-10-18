package com.devil.Spring.entity;



public class UserDTO {


    private Long userId;
    private String name;
    private String email;
    private String image; // Base64 encoded image

    // Constructors
    public UserDTO() {
    }

    public UserDTO(Long userId, String name, String email, String image) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.image = image;
    }

    // Getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
