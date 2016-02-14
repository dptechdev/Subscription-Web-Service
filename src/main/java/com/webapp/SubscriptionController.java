package com.webapp;






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    @Autowired
    private JpaSubscriptionRepository jpaSubscriptionRepository;

    @RequestMapping(value="/list", method=RequestMethod.GET)
    public ResponseEntity<Iterable<Subscription>> listAllSubscriptions() {
        Iterable<Subscription> allSubscriptions = jpaSubscriptionRepository.findAll();
        return new ResponseEntity<>(allSubscriptions, HttpStatus.OK);
    }


    @RequestMapping(value="/create", method=RequestMethod.POST)
    public ResponseEntity<?> createSubscription(@Valid @RequestBody Subscription subscription) {
        subscription = jpaSubscriptionRepository.save(subscription);
        //Set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newSubscriptionUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(subscription.getSubscriptionId())
                .toUri();
        responseHeaders.setLocation(newSubscriptionUri);
        return new ResponseEntity<>(subscription, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value="/select/{id}", method=RequestMethod.GET)
    public ResponseEntity<?> getSubscription(@PathVariable("id") Integer subscriptionId) {
        Subscription subscription = jpaSubscriptionRepository.findBySubscriptionId(subscriptionId);
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @RequestMapping(value="/remove/{id}")
    public ResponseEntity<?> deleteSubscription(@PathVariable("id") Integer subscriptionId) {
        jpaSubscriptionRepository.delete(subscriptionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
