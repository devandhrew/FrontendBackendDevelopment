package com.validity.monolithstarter.rest;

import com.validity.monolithstarter.service.HelloService;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import javax.inject.Inject;

@RestController
@RequestMapping("/api")
public class HelloController {

    @Inject
    private HelloService helloService;

    @GetMapping("/csvdupl")
    public ResponseEntity<String> getCSVDuplicates(){
        return new ResponseEntity<String>(helloService.getCSVDuplicates().toString(), HttpStatus.OK);
    }

}
