package tr.com.muskar.yaml.builder.model;

import lombok.Data;

@Data
public class Port {
    private int containerPort;
    private String protocol = "TCP";
}