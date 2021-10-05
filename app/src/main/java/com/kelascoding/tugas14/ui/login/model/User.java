package com.kelascoding.tugas14.ui.login.model;

public class User {
    int id;

    public int getId() {
        return id;
    }

    public User(int id, String username, String email, String nama, String password, int success, String message) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.nama = nama;
        this.password = password;
        this.success = success;
        this.message = message;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return nama;
    }

    public void setName(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String username, email, nama, password;
    private int success;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

}
