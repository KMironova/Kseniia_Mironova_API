package com.epam.tc.tests;

import com.epam.tc.data.DataToRemove;
import com.epam.tc.dto.BoardDto;
import com.epam.tc.steps.ActionSteps;
import com.epam.tc.steps.AssertionSteps;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrelloFunctionTests {

    public static BoardDto boardDto;
    public static String boardName, boardNameToDelete, listName, labelName, color;

    @BeforeClass
    public void beforeClass() {
        boardNameToDelete = "board to delete [test]";
        boardName = "new Board";
        listName = "new List";
        labelName = "new Label";
        color = "red";
    }

    @BeforeMethod
    public void beforeMethod() {
        boardDto = BoardDto.builder().name(boardName).build();
    }

    @AfterClass
    public void afterClass() {
        ActionSteps.deletedAllBoards(DataToRemove.boardDtosListToDelete);
    }

    @Test(description = "create board and verify that it was created with correct name")
    public void createBoardOnTrelloAndVerifyCreatedBoardByNameTest() {
        ActionSteps.createBoard(boardName);
        AssertionSteps.verifyThatBoardExist(boardDto.getName());
    }

    @Test(description = "create board, delete created board and verify that it was deleted")
    public void deleteBoardOnTrelloTest() {
        ActionSteps.createBoard(boardNameToDelete);
        ActionSteps.deleteBoard(boardDto);
        AssertionSteps.verifyThatBoardWasDeleted(boardDto);
    }

    @Test(description = "create board, create list on that board, verify that list was created")
    public void createListOnBoardAndVerifyThatListTest() {
        ActionSteps.createBoard(boardName);
        ActionSteps.createListOnBoard(boardDto, listName);
        AssertionSteps.verifyThatListWasCreatedOnBoard(boardDto, listName);
    }

    @Test(description = "create label, update label color and verify that color was changed")
    public void createLabelAndUpdateLabelColor() {
        ActionSteps.createBoard(boardName);
        ActionSteps.createLabelOnBoard(boardDto, labelName);
        ActionSteps.updateLabelColor(boardDto, labelName, color);
        AssertionSteps.verifyThatLabelColorWasUpdated(boardDto, labelName, color);
    }

    @Test(description = "create board, update board name, verify that new name")
    public void updateBoardNameAndVerifyThatNewNameTest() {
        String newBoardName = boardName + "[update]";
        ActionSteps.createBoard(boardName);
        ActionSteps.updateBoardName(boardDto, newBoardName);
        AssertionSteps.verifyThatBoardExist(newBoardName);
    }
}
