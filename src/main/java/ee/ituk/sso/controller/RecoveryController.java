package ee.ituk.sso.controller;

import ee.ituk.sso.service.RecoveryService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/recovery")
public class RecoveryController {

    private final static String RECOVERY_URL = "www.hub.ituk.ee/recovery/";

    @PutMapping("/{id}")
    public void createRecovery(@PathVariable("id") Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        String uuid = RecoveryService.generateAndSaveUUID(id);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(RECOVERY_URL)
                .queryParam("id", id)
                .queryParam("uuid", uuid);
        System.out.println(builder.toUriString());

        HttpEntity<?> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(builder.toUriString(), HttpMethod.PUT, entity, String.class);
    }


}
