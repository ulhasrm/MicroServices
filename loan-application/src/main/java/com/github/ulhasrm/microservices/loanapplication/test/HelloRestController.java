package com.github.ulhasrm.microservices.loanapplication.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {

	@GetMapping(path = "/hello1")
	public String hello1() {
		return "Hello from Test Rest Controller";
	}

	@GetMapping(path = "/hello2")
	public TestUser hello2() {
		return new TestUser(1, "Ulhas");
	}
}
