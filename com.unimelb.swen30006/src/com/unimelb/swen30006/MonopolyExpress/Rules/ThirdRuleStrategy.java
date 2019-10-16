package com.unimelb.swen30006.MonopolyExpress.Rules;

import com.unimelb.swen30006.MonopolyExpress.Board.BoardGame;
import com.unimelb.swen30006.MonopolyExpress.Board.SquareSet;

public class ThirdRuleStrategy implements IRuleStrategy{
    @Override
    public int getScore(BoardGame boardGame) {
        int score = 0;

        for (SquareSet set: boardGame.getCompleteGroup()) {
            score += set.getSumValue();
        }

        for (SquareSet set: boardGame.getInCompleteGroup()) {
            score += set.getSumValue();
        }

        return score;
    }
}
