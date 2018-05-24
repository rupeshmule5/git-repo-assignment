package com.telstra.codechallenge.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.telstra.codechallenge.dto.ZeroFollowersRepo;
import com.telstra.codechallenge.service.ZeroFollowersRepoService;

@RestController
@RequestMapping("/api")
@EnableHystrix
public class ZeroFollowersRepoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	public ZeroFollowersRepoService zeroFollowersService;

	@RequestMapping(path = "/getzerofollowersrepo", method = RequestMethod.GET)
	public ResponseEntity hotestRepo(
			@RequestParam(value = "limit", defaultValue = "0", required = false) String limit) {
		LOGGER.info("---------- In ZeroFollowersRepoController ---------- ");

		List<ZeroFollowersRepo> zeroFollowers = zeroFollowersService.getZeroFollowres(limit);

		if (zeroFollowers.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			LOGGER.info("---------- ZeroFollowersRepoService request successfully completed ---------- ",
					zeroFollowers.toString());
			return new ResponseEntity<List<ZeroFollowersRepo>>(zeroFollowers, HttpStatus.OK);
		}
	}
}
