package com.epam.tc.service;

import com.epam.tc.dto.BoardDto;
import com.epam.tc.dto.LabelDto;
import com.epam.tc.dto.ListDto;
import com.epam.tc.utils.ConfProperties;
import com.epam.tc.utils.EndPoints;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;
import org.json.simple.JSONObject;

import static org.apache.http.HttpStatus.SC_OK;

@UtilityClass

public class TrelloService {
    private static final String BASE_URL = ConfProperties.getProperty("url");
    private static final String BASE_PATH = ConfProperties.getProperty("path");

    public static BoardDto createBoardByName(String boardName) {
        return RestAssured.given(setConfigWithJson(getJsonParam(boardName, "name")))
                          .when()
                          .post(EndPoints.Boards.getTitle())
                          .andReturn().getBody().as(BoardDto.class);
    }

    public static BoardDto[] getAllBoards() {
        return RestAssured.given(setConfig())
                          .when()
                          .get(EndPoints.AllBoards.getTitle())
                          .andReturn().getBody().as(BoardDto[].class);
    }

    public static void deleteBoardById(String boardId) {
        RestAssured.given(setConfig())
                   .when()
                   .pathParams("id", boardId)
                   .delete(EndPoints.BoardById.getTitle())
                   .then()
                   .statusCode(SC_OK);
    }

    public static void createListOnBoard(String boardId, String listName) {
        RestAssured.given(setConfigWithJson(getJsonParam(listName, "name")))
                   .when()
                   .pathParams("id", boardId)
                   .post(EndPoints.ListsFromBoard.getTitle())
                   .then()
                   .statusCode(SC_OK);
    }

    public static ListDto [] getListsFromBoard (String boardId) {
        return RestAssured.given(setConfig())
                          .when()
                          .pathParams("id", boardId)
                          .get(EndPoints.ListsFromBoard.getTitle())
                          .andReturn().getBody().as(ListDto[].class);
    }

    public static void createLabelOnBoard(String boardId, String labelName) {
        RestAssured.given(setConfigWithJson(getJsonParam(labelName, "name")))
                   .when()
                   .pathParams("id", boardId)
                   .post(EndPoints.LabelsFromBoard.getTitle())
                   .then()
                   .statusCode(SC_OK);
    }

    public static void updateBoardName(String boardId, String newBoardName) {
        RestAssured.given(setConfigWithJson(getJsonParam(newBoardName,"name")))
                   .when()
                   .pathParams("id", boardId)
                   .put(EndPoints.BoardById.getTitle())
                   .then()
                   .statusCode(SC_OK);
    }

    public static LabelDto [] getLabelsFromBoard (String boardId) {
        return  RestAssured.given(setConfig())
                           .when()
                           .pathParams("id", boardId)
                           .get(EndPoints.LabelsFromBoard.getTitle())
                           .andReturn().getBody().as(LabelDto[].class);
    }

    public static void updateLabelColor (String labelId, String color) {
        RestAssured.given(setConfigWithJson(getJsonParam(color,"color")))
                   .when()
                   .pathParams("id", labelId)
                   .put(EndPoints.LabelByIdFromBoard.getTitle())
                   .then()
                   .statusCode(SC_OK);
    }

    private static RequestSpecification setConfig() {
        return new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .setBasePath(BASE_PATH)
            .addQueryParam("key", ConfProperties.getProperty("key"))
            .addQueryParam("token", ConfProperties.getProperty("token"))
            .build();
    }

    private static RequestSpecification setConfigWithJson(String json) {
        return new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .setBasePath(BASE_PATH)
            .addQueryParam("key", ConfProperties.getProperty("key"))
            .addQueryParam("token", ConfProperties.getProperty("token"))
            .setBody(json)
            .setContentType("application/json")
            .build();
    }

    public static String getJsonParam(String requirements, String reqName) {
        JSONObject requiredParams = new JSONObject();
        requiredParams.put(reqName, requirements);
        return requiredParams.toJSONString();
    }
}
