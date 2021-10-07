package com.epam.tc.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum EndPoints {
    Boards("/boards"),
    BoardById("/board/{id}"),
    AllBoards("/members/me/boards"),
    ListsFromBoard("/boards/{id}/lists"),
    LabelsFromBoard("/boards/{id}/labels"),
    LabelByIdFromBoard("/labels/{id}");

    private String title;

}
