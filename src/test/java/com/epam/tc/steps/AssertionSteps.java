package com.epam.tc.steps;

import com.epam.tc.data.DataToRemove;
import com.epam.tc.dto.BoardDto;
import com.epam.tc.dto.LabelDto;
import com.epam.tc.dto.ListDto;
import org.junit.Assert;

public class AssertionSteps {

    public static void verifyThatBoardExist(String expectedBoardName) {
        BoardDto [] boardDtos = ActionSteps.getBoards();
        for (BoardDto ld : boardDtos) {
            if (ld.getName().equals(expectedBoardName)) {
                Assert.assertEquals(ld.getName(), expectedBoardName);
                break;
            }
        }
    }

    public static void verifyThatBoardWasDeleted(BoardDto deletedBoard) {
        BoardDto [] boards = ActionSteps.getBoards();
        for (BoardDto bd : boards)
            if (bd.getName().equals(deletedBoard.getName())) Assert.fail("board was not deleted");
        DataToRemove.boardDtosListToDelete.remove(deletedBoard);
    }

    public static void verifyThatListWasCreatedOnBoard(BoardDto board, String expectedListName) {
        ListDto [] listDtos = ActionSteps.getListsFromBoard(board);
        for (ListDto ld : listDtos) {
            if (ld.getName().equals(expectedListName)) {
                Assert.assertEquals(ld.getName(), expectedListName);
                break;
            }
        }
    }

    public static void verifyThatLabelColorWasUpdated(BoardDto board, String labelName,String expectedLabelColor) {
        LabelDto labelDto = ActionSteps.getLabel(board, labelName);
        Assert.assertEquals(labelDto.getColor(), expectedLabelColor);
    }
}
