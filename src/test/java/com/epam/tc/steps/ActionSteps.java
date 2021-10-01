package com.epam.tc.steps;

import com.epam.tc.Exceptions.NotFoundLabelFromBoardException;
import com.epam.tc.data.DataToRemove;
import com.epam.tc.dto.BoardDto;
import com.epam.tc.dto.LabelDto;
import com.epam.tc.dto.ListDto;
import com.epam.tc.service.TrelloService;
import com.epam.tc.tests.TrelloFunctionTests;
import java.util.List;

public class ActionSteps {

    public static ActionSteps createBoard(String boardName) {
        TrelloFunctionTests.boardDto = TrelloService.createBoardByName(boardName);
        DataToRemove.boardDtosListToDelete.add(TrelloFunctionTests.boardDto);
        return new ActionSteps();
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

    public static ActionSteps deleteBoard(BoardDto board) {
        TrelloService.deleteBoardById(board.getId());
        return new ActionSteps();
    }

    public static ActionSteps createListOnBoard(BoardDto board, String listName) {
        TrelloService.createListOnBoard(board.getId(), listName);
        return new ActionSteps();
    }

    public static ActionSteps createLabelOnBoard(BoardDto board, String labelName) {
        TrelloService.createLabelOnBoard(board.getId(), labelName);
        return new ActionSteps();
    }

    public static ActionSteps updateBoardName(BoardDto board, String newBoardName) {
        TrelloService.updateBoardName(board.getId(), newBoardName);
        return new ActionSteps();
    }

    public static ActionSteps updateLabelColor(BoardDto board, String labelName, String color) {
        TrelloService.updateLabelColor(getLabel(board, labelName).getId(), color);
        return new ActionSteps();
    }
    static ListDto [] getListsFromBoard (BoardDto board) {
        return TrelloService.getListsFromBoard(board.getId());
    }

    public static void deletedAllBoards (List<BoardDto> boards) {
        for (BoardDto board : boards) {
            TrelloService.deleteBoardById(board.getId());
        }
    }
}
