package com.epam.tc.tests;

import com.epam.tc.ConfProperties;
import com.epam.tc.TestBase;
import com.epam.tc.dto.BoardDto;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrelloFunctionTests extends TestBase {

    public static List<BoardDto> boardDtosListToDelete = new ArrayList<>();
    public static BoardDto boardDto = new BoardDto();

    @BeforeMethod
    public void beforeMethod() {
        boardDto = BoardDto.builder().name(ConfProperties.getProperty("boardName")).build();
    }

    @Test(description = "create board and verify that it was created with correct name")
    public void createBoardOnTrelloAndVerifyCreatedBoardByNameTest() {
        actionSteps.createBoard(ConfProperties.getProperty("boardName"));
        assertionSteps.verifyThatBoardExist(boardDto.getName());
    }

    @Test(description = "create board, delete created board and verify that it was deleted")
    public void deleteBoardOnTrelloTest() {
        actionSteps.createBoard(ConfProperties.getProperty("boardToDelete"))
                   .deleteBoard(boardDto);
        assertionSteps.verifyThatBoardWasDeleted(boardDto);
    }

    @Test(description = "create board, create list on that board, verify that list was created")
    public void createListOnBoardAndVerifyThatListTest() {
        actionSteps.createBoard(ConfProperties.getProperty("boardName"))
                   .createListOnBoard(boardDto, ConfProperties.getProperty("listName"));
        assertionSteps.verifyThatListWasCreatedOnBoard(boardDto, ConfProperties.getProperty("listName"));
    }

    @Test(description = "create label, update label color and verify that color was changed")
    public void createLabelAndUpdateLabelColor() {
        actionSteps.createBoard(ConfProperties.getProperty("boardName"))
                   .createLabelOnBoard(boardDto, ConfProperties.getProperty("labelName"))
                   .updateLabelColor(boardDto,
                       ConfProperties.getProperty("labelName"),
                       ConfProperties.getProperty("color"));
        assertionSteps.verifyThatLabelColorWasUpdated(boardDto, ConfProperties.getProperty("labelName"),
                                                                ConfProperties.getProperty("color"));
    }

    @Test(description = "create board, update board name, verify that new name")
    public void updateBoardNameAndVerifyThatNewNameTest() {
        String newBoardName = ConfProperties.getProperty("boardName") + "[update]";
        actionSteps.createBoard(ConfProperties.getProperty("boardName"))
                   .updateBoardName(boardDto, newBoardName);
        assertionSteps.verifyThatBoardExist(newBoardName);
    }
}
