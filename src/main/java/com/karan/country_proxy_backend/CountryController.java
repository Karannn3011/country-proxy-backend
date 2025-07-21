package com.karan.country_proxy_backend;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

@RestController
@RequestMapping("/api")
// See the next section for a detailed explanation of @CrossOrigin
@CrossOrigin(origins = {"http://localhost:5173", "https://your-deployed-frontend-url.com"})
public class CountryController {

    private static final String EXTERNAL_API_URL = "https://www.apicountries.com/countries";

    @GetMapping("/countries")
    public ResponseEntity<String> getCountries() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            // Fetch the data from the external API
            ResponseEntity<String> response = restTemplate.getForEntity(EXTERNAL_API_URL, String.class);
            // Return the exact response (body, status, headers) to the client
            return response;
        } catch (RestClientException e) {
            // Handle exceptions if the external API is down or returns an error
            return ResponseEntity.status(500).body("Error fetching data from external API: " + e.getMessage());
        }
    }
}