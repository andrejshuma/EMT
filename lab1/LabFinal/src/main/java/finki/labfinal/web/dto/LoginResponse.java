package finki.labfinal.web.dto;

import java.time.Instant;
import java.util.List;

public record LoginResponse(
        String tokenType,
        String accessToken,
        long expiresIn,
        Instant expiresAt,
        String username,
        List<String> roles
) {
}

