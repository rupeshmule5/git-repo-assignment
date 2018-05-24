package com.telstra.codechallenge.serviceImpl;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.telstra.codechallenge.dto.ZeroFollowersRepo;
import com.telstra.codechallenge.service.ZeroFollowersRepoService;

@Service
public class ZeroFollowersRepoServiceImpl implements ZeroFollowersRepoService {

	@Value("${GitRepo.base.url}")
	private String GitRepoBaseUrl;

	private RestTemplate restTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public ZeroFollowersRepoServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") }, fallbackMethod = "reliable")
	public List<ZeroFollowersRepo> getZeroFollowres(String limit) {
		int num = 0;

		LOGGER.info("---------- in ZeroFollowersRepoServiceImpl ----------");
		ZeroFollowersRepo zeroFollowers = null;
		List<ZeroFollowersRepo> zeroFollowersList = new ArrayList<ZeroFollowersRepo>();

		String data = restTemplate.getForObject(GitRepoBaseUrl + "users?q=followers:0&sort=joined&order=asc",
				String.class);

		JSONObject jsonObject = null;
		JSONArray arrayObject = null;
		try {
			jsonObject = new JSONObject(data);
			arrayObject = jsonObject.getJSONArray("items");
		} catch (JSONException e) {
			LOGGER.error("JSON  Parsing Exception : ", e.getMessage());
		}

		if (arrayObject != null) {
			for (int i = 0; i < arrayObject.length(); i++) {
				try {
					// set the API date to POJO object
					zeroFollowers = new ZeroFollowersRepo();
					zeroFollowers.setId(arrayObject.getJSONObject(i).getString("id"));
					zeroFollowers.setLogin(arrayObject.getJSONObject(i).getString("login"));
					zeroFollowers.setHtmlUrl(arrayObject.getJSONObject(i).getString("html_url"));
				} catch (JSONException e) {
					LOGGER.error("JSON object is empty or null :" + e.getMessage());
				}

				zeroFollowersList.add(zeroFollowers);
			}
		} else {
			LOGGER.error("JSON Array is null");
		}
		/*
		 * 
		 * Check limit value limit should be in number format and positive integre if
		 * not then display error
		 * 
		 */
		try {
			num = Integer.parseUnsignedInt(limit);
			if (num != 0) {
				return zeroFollowersList.subList(0, num);
			} else {
				return zeroFollowersList;
			}

		} catch (NumberFormatException e) {
			LOGGER.error("Limit must be number format : " + e.getMessage());

			List<ZeroFollowersRepo> numberFormatErrorMessages = new ArrayList<ZeroFollowersRepo>();
			ZeroFollowersRepo limitValueError = new ZeroFollowersRepo();

			limitValueError.setErrorMessage("Please enter valid positive interger value for Limit parameter.");

			numberFormatErrorMessages.add(limitValueError);

			return numberFormatErrorMessages;

		}

	}

	/*
	 * this method for netflix fallback method
	 */

	public List<ZeroFollowersRepo> reliable(String limit) {
		LOGGER.info("----------- In netflix relible method  ----------------");
		List<ZeroFollowersRepo> fallbackObjectList = new ArrayList<ZeroFollowersRepo>();

		ZeroFollowersRepo fallbackMessage = new ZeroFollowersRepo();

		fallbackMessage.setErrorMessage("Server is down !! please try after some time");

		fallbackObjectList.add(fallbackMessage);

		return fallbackObjectList;
	}
}
