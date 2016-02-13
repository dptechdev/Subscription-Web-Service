package com.webapp;





import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    @RequestMapping(value="/test", method=RequestMethod.GET)
    public ResponseEntity<Subscription> test() {
       return new ResponseEntity<Subscription>(new Subscription("Derek", 333333), HttpStatus.OK);
   }
}
