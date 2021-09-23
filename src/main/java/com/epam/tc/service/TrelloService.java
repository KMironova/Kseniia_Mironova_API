package com.epam.tc.service;

import com.epam.tc.dto.BoardDto;
import com.epam.tc.dto.LabelDto;
import com.epam.tc.dto.ListDto;
import com.epam.tc.utils.ConfProperties;
import com.epam.tc.utils.EndPoints;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

import static org.apache.http.HttpStatus.SC_OK;

@NoArgsConstructor

public class TrelloService {
    private static final String BASE_URL = "https://api.trello.com";
    private static final String BASE_PATH = "1";

    public static BoardDto createBoardByName(String boardName) {
        JSONObject requiredParams = new JSONObject();
        requiredParams.put("name", boardName);
        return RestAssured.given(setConfig())
                          .header("Content-Type","application/json")
                          .body(requiredParams.toJSONString())
                          .when()
                          .post(EndPoints.Boards.getTitle())
                          .andReturn().getBody().as(BoardDto.class);
    }

    public static BoardDto getBoardById(String boardId) {
        return RestAssured.given(setConfig())
                          .when()
                          .pathParams("id", boardId)
                          .get(EndPoints.BoardById.getTitle())
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
        JSONObject requiredParams = new JSONObject();
        requiredParams.put("name", listName);

        RestAssured.given(setConfig())
                   .header("Content-Type","application/json")
                   .body(requiredParams.toJSONString())
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
        JSONObject requiredParams = new JSONObject();
        requiredParams.put("name", labelName);
        RestAssured.given(setConfig())
                   .header("Content-Type","application/json")
                   .body(requiredParams.toJSONString())
                   .when()
                   .pathParams("id", boardId)
                   .post(EndPoints.LabelsFromBoard.getTitle())
                   .then()
                   .statusCode(SC_OK);
    }

    public static void updateBoardName(String boardId, String newBoardName) {
        JSONObject requiredParams = new JSONObject();
        requiredParams.put("name", newBoardName);
        RestAssured.given(setConfig())
                   .header("Content-Type","application/json")
                   .body(requiredParams.toJSONString())
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
        JSONObject requiredParams = new JSONObject();
        requiredParams.put("color", color);
        RestAssured.given(setConfig())
                   .header("Content-Type","application/json")
                   .body(requiredParams.toJSONString())
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
}
