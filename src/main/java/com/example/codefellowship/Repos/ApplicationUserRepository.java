package com.example.codefellowship.Repos;

import com.example.codefellowship.Models.ApplicationUser;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser,Integer> {
    public ApplicationUser findByUsername(String username);
}
