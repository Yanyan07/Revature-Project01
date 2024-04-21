package com.revature.controllers;

import com.revature.daos.OwnerDAO;
import com.revature.models.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private OwnerDAO ownerDAO;

    @Autowired
    public OwnerController(OwnerDAO ownerDAO) {
        this.ownerDAO = ownerDAO;
    }

    @PostMapping
    public ResponseEntity<Owner> addOwner(@RequestBody Owner owner){

        Owner savedOwner = ownerDAO.save(owner);
//        if(savedOwner == null){ //condition 'savedOwner == null' is always 'false'
//        }
        return ResponseEntity.status(201).body(savedOwner);
    }

    @PutMapping("/{ownerId}")
    public ResponseEntity<Owner> updateOwner(@RequestBody Owner owner, @PathVariable int ownerId){
        Optional<Owner> optionalOwner = ownerDAO.findById(ownerId);
        if(optionalOwner.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Owner ownerDB = optionalOwner.get();
        ownerDB.setUsername(owner.getUsername());
        ownerDB.setPassword(owner.getPassword());
        ownerDB = ownerDAO.save(ownerDB);
        return ResponseEntity.accepted().body(ownerDB);
    }

    @GetMapping
    public ResponseEntity<List<Owner>> getAllOwners(){
        return ResponseEntity.ok(ownerDAO.findAll());
    }

}
