package com.ranganath.tests.endpoints;

import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class UserEndpoints {

    public Response getUser(String userId) {
        return given()
                .pathParam("id", userId)
                .when()
                .get("/users/{id}");
    }

    public Response createUser(JSONObject payload) {
        return given()
                .header("Content-Type", "application/json")
                .body(payload.toString())
                .when()
                .post("/users");
    }

    public Response updateUserPartial(String userId, JSONObject payload) {
        return given()
                .header("Content-Type", "application/json")
                .pathParam("id", userId)
                .body(payload.toString())
                .when()
                .patch("/users/{id}");
    }

    public Response deleteUser(String userId) {
        return given()
                .pathParam("id", userId)
                .when()
                .delete("/users/{id}");
    }
}
