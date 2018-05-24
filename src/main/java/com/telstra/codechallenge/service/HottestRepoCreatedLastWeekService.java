package com.telstra.codechallenge.service;

import java.util.List;

import com.telstra.codechallenge.dto.HottestRepoCreatedLastWeek;

public interface HottestRepoCreatedLastWeekService {

	List<HottestRepoCreatedLastWeek> repoCreatedLastWeek(String limit);

}
