package com.example.navio.backend.model;

public class User {
    private long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private String imageURL;
    private long departmentID;
    private int roleID;
    private boolean working_status;
    private boolean status;
    private long parent_id;


    public User() {

    }

    public User(long id, String name, String surname, String username, String email, String imageURL) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.imageURL = imageURL;
    }

    public User(final long id, final String name, final String surname, final String username, final String password, final String email, final String imageURL, final long departmentID, final int roleID, final boolean working_status, final boolean status, final long parent_id) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.imageURL = imageURL;
        this.departmentID = departmentID;
        this.roleID = roleID;
        this.working_status = working_status;
        this.status = status;
        this.parent_id = parent_id;
    }

    public long getId() {
        return id;
    }

    private  void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private  void setName(final String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    private  void setSurname(final String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    private  void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    private  void setPassword(final String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    private  void setEmail(final String email) {
        this.email = email;
    }

    public String getImageURL() {
        return imageURL;
    }

    private  void setImageURL(final String imageURL) {
        this.imageURL = imageURL;
    }

    public long getDepartmentID() {
        return departmentID;
    }

    private  void setDepartmentID(final long departmentID) {
        this.departmentID = departmentID;
    }

    public int getRoleID() {
        return roleID;
    }

    private  void setRoleID(final int roleID) {
        this.roleID = roleID;
    }

    public boolean isWorking_status() {
        return working_status;
    }

    private  void setWorking_status(final boolean working_status) {
        this.working_status = working_status;
    }

    public boolean isStatus() {
        return status;
    }

    private  void setStatus(final boolean status) {
        this.status = status;
    }

    public long getParent_id() {
        return parent_id;
    }

    private void setParent_id(final long parent_id) {
        this.parent_id = parent_id;
    }
}
