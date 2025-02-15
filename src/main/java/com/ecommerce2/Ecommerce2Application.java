package com.ecommerce2;


import java.security.Key;
import java.util.Base64;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@SpringBootApplication
public class Ecommerce2Application {

	public static void main(String[] args) {
		//  Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        // System.out.println(Base64.getEncoder().encodeToString(key.getEncoded()));
		SpringApplication.run(Ecommerce2Application.class, args);
	}

}
