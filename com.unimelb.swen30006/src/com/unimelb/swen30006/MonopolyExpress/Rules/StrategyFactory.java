package com.unimelb.swen30006.MonopolyExpress.Rules;

import com.unimelb.swen30006.MonopolyExpress.Player;

public class StrategyFactory {
    private static StrategyFactory factory = new StrategyFactory();


    public static IRuleStrategy getRuleStrategyInstance(String name, Player currentPlayer){
        if (name.equals("first"))
            return new FirstRuleStrategy();
        if(name.equals("second"))
            return new SecondRuleStrategy();
        if (name.equals("third"))
            return new ThirdRuleStrategy();
        if (name.equals("composite")) {
            CompositeRuleStrategy strategy = new CompositeRuleStrategy();
            if (currentPlayer.getTurn() % 2 == 0) {
                if (currentPlayer.getTurn() % 3 == 0) {
                    strategy.addStrategies(StrategyFactory.getRuleStrategyInstance("first", currentPlayer));
                    strategy.addStrategies(StrategyFactory.getRuleStrategyInstance("second", currentPlayer));
                    strategy.addStrategies(StrategyFactory.getRuleStrategyInstance("third", currentPlayer));
                } else {
                    strategy.addStrategies(StrategyFactory.getRuleStrategyInstance("first", currentPlayer));
                    strategy.addStrategies(StrategyFactory.getRuleStrategyInstance("second", currentPlayer));
                }
            } else {
                if (currentPlayer.getTurn() % 3 == 0) {
                    strategy.addStrategies(StrategyFactory.getRuleStrategyInstance("first", currentPlayer));
                    strategy.addStrategies(StrategyFactory.getRuleStrategyInstance("third", currentPlayer));
                } else {
                    strategy.addStrategies(StrategyFactory.getRuleStrategyInstance("first", currentPlayer));
                }
            }
        }
        return null;
    }
}
