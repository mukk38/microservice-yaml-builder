package tr.com.muskar.yaml.builder.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MicroserviceConfig {
    private String name;
    private String image;
    private int replicas;
    private List<Port> containerPorts;
    private Map<String, String> env;
    private Map<String, String> labels;
    private String serviceType = "ClusterIP";
}
