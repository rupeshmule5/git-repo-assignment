package com.telstra.codechallenge.utility;

import java.util.Comparator;

import com.telstra.codechallenge.dto.HottestRepoCreatedLastWeek;
/*
 * Sorting the records best on rating*/
public class SortRepoData implements Comparator<HottestRepoCreatedLastWeek> {
	@Override
	public int compare(HottestRepoCreatedLastWeek o1, HottestRepoCreatedLastWeek o2) {
		if (Integer.parseInt(o1.getWatchersCount()) < Integer.parseInt(o2.getWatchersCount())) {
			return 1;
		} else {
			return -1;
		}
	}

}
