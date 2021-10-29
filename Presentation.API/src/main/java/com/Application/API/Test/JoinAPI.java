package com.Application.API.Test;

import IServices.ISampleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoinAPI {

    @Autowired
    ISampleServices _sampleServices;

    @RequestMapping(value = "/jbpm", method = RequestMethod.GET)
    public ResponseEntity<Object> InnerJoinTwoList(String s) {
          return ResponseEntity.ok(_sampleServices.getArimaInfo());
    }
}
