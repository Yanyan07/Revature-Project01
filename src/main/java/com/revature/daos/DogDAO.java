package com.revature.daos;

import com.revature.models.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogDAO extends JpaRepository<Dog,Integer> {
    List<Dog> findByOwnerOwnerId(int ownerId);
}
