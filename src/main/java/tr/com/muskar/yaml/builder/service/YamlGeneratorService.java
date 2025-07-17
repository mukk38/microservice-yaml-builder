package tr.com.muskar.yaml.builder.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.stereotype.Service;
import tr.com.muskar.yaml.builder.model.MicroserviceConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class YamlGeneratorService {

    public String generateYaml(MicroserviceConfig config) throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        Map<String, Object> deployment = new HashMap<>();
        deployment.put("apiVersion", "apps/v1");
        deployment.put("kind", "Deployment");

        Map<String, Object> metadata = Map.of(
                "name", config.getName(),
                "labels", config.getLabels() != null ? config.getLabels() : Map.of("app", config.getName())
        );
        deployment.put("metadata", metadata);

        Map<String, Object> container = new HashMap<>();
        container.put("name", config.getName());
        container.put("image", config.getImage());
        container.put("ports", config.getContainerPorts());
        if (config.getEnv() != null && !config.getEnv().isEmpty()) {
            container.put("env", config.getEnv().entrySet().stream().map(e -> Map.of("name", e.getKey(), "value", e.getValue())).toList());
        }

        Map<String, Object> spec = Map.of(
                "replicas", config.getReplicas(),
                "selector", Map.of("matchLabels", Map.of("app", config.getName())),
                "template", Map.of(
                        "metadata", Map.of("labels", Map.of("app", config.getName())),
                        "spec", Map.of("containers", List.of(container))
                )
        );

        deployment.put("spec", spec);

        return mapper.writeValueAsString(deployment);
    }
}