package com.epam.tc.steps;

import com.epam.tc.dto.BoardDto;
import com.epam.tc.dto.LabelDto;
import com.epam.tc.dto.ListDto;
import com.epam.tc.tests.TrelloFunctionTests;
import org.assertj.core.api.SoftAssertions;

public class AssertionSteps {
    private SoftAssertions softAssertions = new SoftAssertions();

    public void verifyThatBoardExist(String expectedBoardName) {
        BoardDto [] boardDtos = ActionSteps.getBoards();
        boolean check = false;
        for (BoardDto ld : boardDtos) {
            if (ld.getName().equals(expectedBoardName)) {
                check = true;
                break;
            }
        }
        softAssertions.assertThat(check).isTrue();
    }

    public void verifyThatBoardWasDeleted(BoardDto deletedBoard) {
        BoardDto [] boards = ActionSteps.getBoards();
        for (BoardDto bd : boards)
            if (bd.getName().equals(deletedBoard.getName())) softAssertions.fail("board was not deleted");
        TrelloFunctionTests.boardDtosListToDelete.remove(deletedBoard);
    }

    public void verifyThatListWasCreatedOnBoard(BoardDto board, String expectedListName) {
        ListDto [] listDtos = ActionSteps.getListsFromBoard(board);
        boolean check = false;
        for (ListDto ld : listDtos) {
            if (ld.getName().equals(expectedListName)) {
                check = true;
                break;
            }
        }
        softAssertions.assertThat(check).isTrue();
    }

    public void verifyThatLabelColorWasUpdated(BoardDto board, String labelName,String expectedLabelColor) {
        LabelDto labelDto = ActionSteps.getLabel(board, labelName);
        softAssertions.assertThat(labelDto.getColor()).isEqualTo(expectedLabelColor);
    }
}
