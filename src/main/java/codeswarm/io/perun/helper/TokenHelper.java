package codeswarm.io.perun.helper;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenHelper {

    public String generateToken(Long userId) {
        return UUID.randomUUID() + "-" + userId;
    }
}
