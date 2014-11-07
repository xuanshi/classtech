package com.classtech.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class DefaultController {

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> home() {
		return ResponseEntity.ok("hello world, classtech!");
	}
}
