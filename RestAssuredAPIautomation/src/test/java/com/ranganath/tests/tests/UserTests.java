package com.ranganath.tests.tests;

import com.ranganath.tests.base.BaseTest;
import com.ranganath.tests.endpoints.UserEndpoints;
import com.ranganath.framework.utils.JsonUtils;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserTests extends BaseTest {

    UserEndpoints userEndpoints = new UserEndpoints();

    @DataProvider(name = "userData")
    public Object[][] userData() {
        return JsonUtils.getUsersFromJson("/testdata.json");
    }

    @Test(groups = {"CRUD", "POST"}, dataProvider = "userData")
    public void createUserTest(JSONObject payload) {
        Response response = userEndpoints.createUser(payload);
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("name"), payload.getString("name"));
    }

    @Test(groups = {"CRUD", "PATCH"})
    public void updateUserPartialTest() {
        JSONObject payload = new JSONObject();
        payload.put("name", "Updated Name");

        Response response = userEndpoints.updateUserPartial("1", payload);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("name"), "Updated Name");
    }

    @Test(groups = {"CRUD", "GET"})
    public void getUserTest() {
        Response response = userEndpoints.getUser("1");
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("id"), "1");
    }

    @Test(groups = {"CRUD", "DELETE"})
    public void deleteUserTest() {
        Response response = userEndpoints.deleteUser("1");
        Assert.assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 204);
    }
}
