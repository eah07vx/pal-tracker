package io.pivotal.pal.tracker.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {

    private final String port, memLimit, cfIndex, cfInstance;

    public EnvController(@Value("${PORT:NOT SET}") String port,
                              @Value("${MEMORY_LIMIT:NOT SET}") String memLimit,
                              @Value("${CF_INSTANCE_INDEX:NOT SET}") String cfIndex,
                              @Value("${CF_INSTANCE_ADDR:NOT SET}") String cfInstance) {
        this.port = port;
        this.memLimit = memLimit;
        this.cfIndex = cfIndex;
        this.cfInstance = cfInstance;
    }

    @GetMapping("/env")
    public Map<String, String> getEnv() {
        Map<String, String> env = new HashMap<>();

        env.put("PORT", port);
        env.put("MEMORY_LIMIT", memLimit);
        env.put("CF_INSTANCE_INDEX", cfIndex);
        env.put("CF_INSTANCE_ADDR", cfInstance);
        return env;
    }
}
