package tr.com.muskar.yaml.builder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tr.com.muskar.yaml.builder.model.MicroserviceConfig;
import tr.com.muskar.yaml.builder.service.YamlGeneratorService;

@RestController
@RequestMapping("/api/yaml")
public class YamlController {

    @Autowired
    private YamlGeneratorService yamlService;

    @PostMapping("/generate")
    public String generateYaml(@RequestBody MicroserviceConfig config) throws Exception {
        return yamlService.generateYaml(config);
    }
}
