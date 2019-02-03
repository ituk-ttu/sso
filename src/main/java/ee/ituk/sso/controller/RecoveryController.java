package ee.ituk.sso.controller;

import ee.ituk.sso.service.RecoveryService;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/recovery")
public class RecoveryController {

    @Resource
    RecoveryService recoveryService;

    @GetMapping("/{id}")
    public void createRecovery(@PathVariable("id") Integer id) {
        String uuid = recoveryService.generateUUID(id);
        Map<String, String> params = new HashMap<>();
        params.put(uuid,Integer.toString(id));
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange("www.hub.ituk.ee/recovery/id=%s&uuid=%s".replace(Integer.toString(id),uuid), HttpMethod.GET, String.class,  params );
    }


}
