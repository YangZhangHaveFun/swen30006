package com.unimelb.swen30006.MonopolyExpress.Rules;

import com.unimelb.swen30006.MonopolyExpress.Board.BoardGame;

import java.util.ArrayList;

public class CompositeRuleStrategy implements IRuleStrategy{
    ArrayList<IRuleStrategy> ruleStrategies = new ArrayList<>();

    @Override
    public int getScore(BoardGame boardGame) {
        int score = 0;
        for (IRuleStrategy strategy: ruleStrategies) {
            score = Math.max(score, strategy.getScore(boardGame));
        }
        return score;
    }

    public void addStrategies(IRuleStrategy strategy){
        ruleStrategies.add(strategy);
    }
}
