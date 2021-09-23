package com.epam.tc.steps;

import com.epam.tc.Exceptions.NotFoundLabelFromBoardException;
import com.epam.tc.dto.BoardDto;
import com.epam.tc.dto.LabelDto;
import com.epam.tc.dto.ListDto;
import com.epam.tc.service.TrelloService;
import com.epam.tc.tests.TrelloFunctionTests;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor

public class ActionSteps {

    public ActionSteps createBoard(String boardName) {
        TrelloFunctionTests.boardDto = TrelloService.createBoardByName(boardName);
        TrelloFunctionTests.boardDtosListToDelete.add(TrelloFunctionTests.boardDto);
        return this;
    }

    static BoardDto[] getBoards() {
        return TrelloService.getAllBoards();
    }

    static LabelDto [] getLabelsFromBoard(BoardDto board) {
        return TrelloService.getLabelsFromBoard(board.getId());
    }

    static LabelDto getLabel(BoardDto board, String labelName) {
        LabelDto [] labelDtos = getLabelsFromBoard(board);
        for (LabelDto ld : labelDtos)
            if (ld.getName().equals(labelName)) return ld;
         throw new NotFoundLabelFromBoardException("Label with name " + labelName + " was not found");
    }

    public ActionSteps deleteBoard(BoardDto board) {
        TrelloService.deleteBoardById(board.getId());
        return this;
    }

    public ActionSteps createListOnBoard(BoardDto board, String listName) {
        TrelloService.createListOnBoard(board.getId(), listName);
        return this;
    }

    public ActionSteps createLabelOnBoard(BoardDto board, String labelName) {
        TrelloService.createLabelOnBoard(board.getId(), labelName);
        return this;
    }

    public ActionSteps updateBoardName(BoardDto board, String newBoardName) {
        TrelloService.updateBoardName(board.getId(), newBoardName);
        return this;
    }

    public ActionSteps updateLabelColor(BoardDto board, String labelName, String color) {
        TrelloService.updateLabelColor(getLabel(board, labelName).getId(), color);
        return this;
    }
    static ListDto [] getListsFromBoard (BoardDto board) {
        return TrelloService.getListsFromBoard(board.getId());
    }

    public void deletedAllBoards (List<BoardDto> boards) {
        for (BoardDto board : boards) {
            TrelloService.deleteBoardById(board.getId());
        }
    }
}
