package io.yelp.controller;

import io.yelp.domain.Business;
import io.yelp.repo.BusinessRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BusinessCtrl {

    @Autowired
    BusinessRepo businessRepo;

    @RequestMapping(value = "/business", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Business>> getBusiness(){
        Iterable<Business> businesses = businessRepo.findAll();
        return new ResponseEntity<>(businesses, HttpStatus.OK);
    }

    @RequestMapping(value = "/createBusiness", method = RequestMethod.POST)
    public ResponseEntity<?> createBusiness(@RequestBody Business business){
        businessRepo.save(business);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/business/{businessId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBusiness(@PathVariable Business businessId){
        businessRepo.delete(businessId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
