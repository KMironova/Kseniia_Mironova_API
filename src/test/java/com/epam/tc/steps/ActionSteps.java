package com.epam.tc.steps;

import com.epam.tc.Exceptions.NotFoundLabelFromBoardException;
import com.epam.tc.data.DataToRemove;
import com.epam.tc.dto.BoardDto;
import com.epam.tc.dto.LabelDto;
import com.epam.tc.dto.ListDto;
import com.epam.tc.service.TrelloService;
import java.util.List;

public class ActionSteps {

    public static BoardDto createBoard(String boardName) {
        BoardDto boardDto = TrelloService.createBoardByName(boardName);
        DataToRemove.boardDtosListToDelete.add(boardDto);
        return boardDto;
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

    public static void deleteBoard(BoardDto board) {
        TrelloService.deleteBoardById(board.getId());
    }

    public static void createListOnBoard(BoardDto board, String listName) {
        TrelloService.createListOnBoard(board.getId(), listName);
    }

    public static void createLabelOnBoard(BoardDto board, String labelName) {
        TrelloService.createLabelOnBoard(board.getId(), labelName);
    }

    public static void updateBoardName(BoardDto board, String newBoardName) {
        TrelloService.updateBoardName(board.getId(), newBoardName);
    }

    public static void updateLabelColor(BoardDto board, String labelName, String color) {
        TrelloService.updateLabelColor(getLabel(board, labelName).getId(), color);
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
