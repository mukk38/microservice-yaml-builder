# 🛠️ Microservice Deployment Configurator YAML Builder

This project provides a REST API built with **Spring Boot** that allows users to automatically generate Kubernetes **Deployment** YAML files by submitting microservice configuration details in JSON format.

---

## 🚀 Features

- 🧾 Accepts microservice configuration (name, image, ports, env vars, etc.)
- 🔧 Generates valid Kubernetes `Deployment` YAML
- ☁️ Optional `Service` support (extensible)
- 📦 JSON input → YAML output
- ⚡ Lightweight and fast with Spring Boot

---

## 📬 Sample Request

### 🔗 Endpoint

POST /api/yaml/generate
Content-Type: application/json


### 📄 Request Body

```json
{
  "name": "my-app",
  "image": "nginx:latest",
  "replicas": 2,
  "containerPorts": [
    { "containerPort": 80 }
  ],
  "env": {
    "ENVIRONMENT": "production"
  },
  "labels": {
    "tier": "frontend"
  },
  "serviceType": "ClusterIP"
}

```

#### 🧾 Sample YAML Output
```

apiVersion: apps/v1
kind: Deployment
metadata:
name: my-app
labels:
tier: frontend
spec:
replicas: 2
selector:
matchLabels:
app: my-app
template:
metadata:
labels:
app: my-app
spec:
containers:
- name: my-app
image: nginx:latest
ports:
- containerPort: 80
protocol: TCP
env:
- name: ENVIRONMENT
value: production
```

#### 🛠️ How to Run
1. Clone the repository
git clone https://github.com/mukk38/microservice-yaml-builder.git
cd microservice-yaml-builder
2. Build the project 
```
./mvnw clean install
```
3. Run the Spring Boot app
```
   ./mvnw spring-boot:run
```
4. Access API
```
http://localhost:8080/api/yaml/generate

```
