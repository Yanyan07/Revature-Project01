package com.revature.controllers;

import com.revature.daos.DogDAO;
import com.revature.daos.OwnerDAO;
import com.revature.models.Dog;
import com.revature.models.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dogs")
public class DogController {
    private DogDAO dogDAO;
    private OwnerDAO ownerDAO;

    @Autowired
    public DogController(DogDAO dogDAO, OwnerDAO ownerDAO) {
        this.dogDAO = dogDAO;
        this.ownerDAO = ownerDAO;
    }

    @PostMapping("/{ownerId}")
    public ResponseEntity<Dog> addDog(@RequestBody Dog dog, @PathVariable int ownerId){
        Optional<Owner> optionalOwner = ownerDAO.findById(ownerId);
        if(optionalOwner.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        dog.setOwner(optionalOwner.get());
        Dog d = dogDAO.save(dog);
        return ResponseEntity.status(201).body(d);
    }

    @GetMapping
    public ResponseEntity<List<Dog>> getAllDogs(){
        return ResponseEntity.ok(dogDAO.findAll());
    }

    @GetMapping("/{dogId}")
    public ResponseEntity<Dog> getDogById(@PathVariable int dogId) {
        Optional<Dog> optionalDog = dogDAO.findById(dogId);
        if(optionalDog.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Dog dog = optionalDog.get();
        return ResponseEntity.ok(dog);
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Dog>> getDogsByUserId(@PathVariable int ownerId){
        List<Dog> dogs = dogDAO.findByOwnerOwnerId(ownerId);
        if(dogs == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dogs);
    }

    @DeleteMapping("/{dogId}")
    public ResponseEntity<String> deleteDogById(@PathVariable int dogId){
        Optional<Dog> optionalDog = dogDAO.findById(dogId);
        if(optionalDog.isEmpty()){
            return ResponseEntity.status(404).body("Dog with id:" + dogId + " not found.");
        }
        Dog dog = optionalDog.get();
        dogDAO.deleteById(dogId);
        return ResponseEntity.accepted().body("Dog with id:" + dogId + " has been deleted.");
    }

}
