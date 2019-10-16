package com.unimelb.swen30006.MonopolyExpress.Rules;

import com.unimelb.swen30006.MonopolyExpress.Board.BoardGame;
import com.unimelb.swen30006.MonopolyExpress.Board.SquareSet;

public class SecondRuleStrategy implements IRuleStrategy{
    @Override
    public int getScore(BoardGame boardGame) {
        int maxScore = 0;
        for (SquareSet set: boardGame.getInCompleteGroup()) {
            if (maxScore < set.getSumValue())
                maxScore = set.getSumValue();
        }
        return maxScore;
    }
}
