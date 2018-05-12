package com.telstra.codechallenge.reversstring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReverseStringController {

	@Autowired
	ReverseStringService reverseStringService;
	
	
	@RequestMapping(path = "/ReverseWords", method = RequestMethod.GET)
	public ReverseString reverse(@RequestParam(value = "sentence") String sentence) {
		return new ReverseString(reverseStringService.reverseString(sentence));		
	}
}
