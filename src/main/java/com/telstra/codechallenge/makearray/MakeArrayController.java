package com.telstra.codechallenge.makearray;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.telstra.codechallenge.helloworld.HelloWorld;

@RestController
public class MakeArrayController {

	@RequestMapping(path = "/makeArray", method = RequestMethod.POST)
	  public void makeArray(@RequestBody ArrayDTO arrayDTO) {
	   System.out.println(arrayDTO);
	  }
}
