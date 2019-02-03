package ee.ituk.sso.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class RecoveryService {

    private static Map<String, Integer> recoveryIds;

    private RecoveryService() {
        recoveryIds = new HashMap<>();
    }

    public static String generateAndSaveUUID(Integer id) {
        String str = Integer.toString(id);
        String uuid =  UUID.nameUUIDFromBytes(str.getBytes()).toString();
        recoveryIds.put(uuid,id);
        return reformatUUID(uuid);
    }

    private static String reformatUUID(String uuid) {
        return uuid.replace("-", "%2D");
    }


}
