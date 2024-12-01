package org.example;

public class SplitStrategyFactory {

    public static ISplitStrategy splitStrategy(SplitType splitType) {
        switch (splitType) {
            case EQUAL -> {
                return new EqualISplitStrategy();
            }
            case EXACT -> {
                return new ExactISplitStrategy();
            }
            case PERCENT -> {
                return new PercentISplitStrategy();
            }
        }

        return null;
    }
}
