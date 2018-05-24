package com.telstra.codechallenge.service;

import java.util.List;

import com.telstra.codechallenge.dto.ZeroFollowersRepo;

public interface ZeroFollowersRepoService {

	List<ZeroFollowersRepo> getZeroFollowres(String limit);

}
