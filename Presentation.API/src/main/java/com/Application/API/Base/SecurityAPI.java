package com.Application.API.Base;

import Core.Services.Security.IServices.IAuthenticationService;
import IServices.ISampleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityAPI {

    @Autowired
    IAuthenticationService _authenticationService;

    @RequestMapping(value = "/Base/Token", method = RequestMethod.GET)
    public ResponseEntity<Object> getToken(String s) {
        return ResponseEntity.ok(_authenticationService.getAllClaims());
    }
}
