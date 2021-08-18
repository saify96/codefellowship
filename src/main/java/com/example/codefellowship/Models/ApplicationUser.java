package com.example.codefellowship.Models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String bio;
    ////
    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    ////

    ///many2many

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "following_users",
            joinColumns ={@JoinColumn(name = "user_id")} ,// this entity
            inverseJoinColumns = {@JoinColumn(name = "followedUser_id")})// another side , to what iam point
    private Set<ApplicationUser> followedUsers;/// name of the class

    public void addFollowing(ApplicationUser user){
        followedUsers.add(user);
    }

    @ManyToMany(mappedBy = "followedUsers")
    private Set<ApplicationUser> followers; // cuz we are in the same entitiy <same class>

    public Set<ApplicationUser> getFollowedUsers() {
        return followedUsers;
    }

    public Set<ApplicationUser> getFollowers() {
        return followers;
    }
///

    public ApplicationUser(){}

    public ApplicationUser(String username, String password, String firstName, String lastName, String dateOfBirth, String bio) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
