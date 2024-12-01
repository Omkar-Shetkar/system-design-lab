package org.example;

public class SplitStrategyFactory {

    public static SplitStrategy splitStrategy(SplitType splitType) {
        switch (splitType) {
            case EQUAL -> {
                return new EqualSplitStrategy();
            }
            case EXACT -> {
                return new ExactSplitStrategy();
            }
            case PERCENT -> {
                return new PercentSplitStrategy();
            }
        }

        return null;
    }
}
