package us.yoloz.controller;

public abstract class ApiController {
    public static final String API_V1_ENDPOINT = "/api/v1";

    public static final String AUTHENTICATE_URL = "/authenticate";

    // Spring Boot Actuator services
    public static final String CONFIG_ENDPOINT = "/config";
    public static final String METRIC_ENDPOINT = "/metric";
    public static final String ENV_ENDPOINT = "/env";
}
