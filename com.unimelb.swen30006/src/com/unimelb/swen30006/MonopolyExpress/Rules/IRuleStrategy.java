package com.unimelb.swen30006.MonopolyExpress.Rules;

import com.unimelb.swen30006.MonopolyExpress.Board.BoardGame;

public interface IRuleStrategy {
    int getScore(BoardGame boardGame);
}
