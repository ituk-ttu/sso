package ee.ituk.sso.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class RecoveryService {
    public static Map<String, Integer> recoverySet = new HashMap<>();

    public static String generateUUID(Integer id) {
        String str = Integer.toString(id);
        String uuid =  UUID.nameUUIDFromBytes(str.getBytes()).toString();
        recoverySet.put(uuid,id);
        return reformatUUID(uuid);
    }

    private static String reformatUUID(String UUID) {
        return UUID.replace("-", "%2D");
    }


}
