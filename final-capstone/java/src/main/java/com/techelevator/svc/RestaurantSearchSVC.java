package com.techelevator.svc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.Restaurant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Component

public class RestaurantSearchSVC  {
    @Value("${yelpURL}")
    private String apiURL;

    @Value("${yelpAPIKey}")
    private String key;

    public List<Restaurant> getSearchRestaurant(String searchZip) throws JsonProcessingException {
        String url = apiURL + "/search?location=" + searchZip;
        System.out.println("Hello this is an error" + searchZip);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(this.key);

        HttpEntity<String> httpEntity = new HttpEntity<>("", httpHeaders);
        RestTemplate restTemplate = new RestTemplate();

        ObjectMapper objectMapper = new ObjectMapper();

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

        JsonNode jsonNode;
        List<Restaurant> restaurantList = new ArrayList<Restaurant>();
        System.out.println(response);

        jsonNode = objectMapper.readTree(response.getBody());

        JsonNode root = jsonNode.path("businesses");
        for(int i= 0; i< root.size(); i++) {
            String id = root.path(i).path("id").asText();
            String name = root.path(i).path("name").asText();
            String imageUrl = root.path(i).path("image_url").asText();
            boolean isClosed =  Boolean.parseBoolean(root.path(i).path("is_closed").asText());
            String rating = root.path(i).path("rating").asText();
            String address1 = root.path(i).path("location").path("address1").asText();
            String city = root.path(i).path("location").path("city").asText();
            String state = root.path(i).path("location").path("state").asText();
            String zipcode = root.path(i).path("location").path("zip_code").asText();

            String displayAddress = address1 + ", " + city + ", " + state + " " + zipcode;






            String displayPhone = root.path(i).path("display_phone").asText();
            String categories = root.path(i).path("categories").path(0).path("title").asText();

            Restaurant restaurant = new Restaurant(id, name, imageUrl, rating, displayAddress, address1, city, state, zipcode, displayPhone, categories, isClosed);
            restaurantList.add(restaurant);

        }

        System.out.println(restaurantList);

        return restaurantList;

    }

}
