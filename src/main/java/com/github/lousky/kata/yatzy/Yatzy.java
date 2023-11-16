package com.github.lousky.kata.yatzy;

public class Yatzy {
	
	/**
	 * Scores the sum of all dice.
	 * @param roll {@link  Roll}
	 * @return the score
	 */
	public static int chance(Roll roll) {
		return sumAllDicesValues(roll);
	}

	/**
	 * If all dice have the same number, scores 50, 0 otherwise.
	 * @param roll {@link  Roll}
	 * @return the score
	 */
	public static int yatzy(Roll roll) {
		if (roll.getDiceValueList().stream().distinct().count() == 1L) {
			return 50;
		} else {
			return 0;
		}
	}
	
	/**
	 * Scores the sum of the dice that reads the value 1.
	 * @param roll {@link  Roll}
	 * @return the score
	 */
	public static int ones(Roll roll) {
	    return calculateScoreForGivenDiceValue(1, roll);
	}
	
	/**
	 * Scores the sum of the dice that reads the value 2.
	 * @param roll {@link  Roll}
	 * @return the score
	 */
	public static int twos(Roll roll) {
	    return calculateScoreForGivenDiceValue(2, roll);
	}
	
	/**
	 * Scores the sum of the dice that reads the value 3.
	 * @param roll {@link  Roll}
	 * @return the score
	 */
	public static int threes(Roll roll) {
		return calculateScoreForGivenDiceValue(3, roll);
	}
	
	/**
	 * Scores the sum of the dice that reads the value 4.
	 * @param roll {@link  Roll}
	 * @return the score
	 */
	public static int fours(Roll roll) {
		return calculateScoreForGivenDiceValue(4, roll);
	}
	
	/**
	 * Scores the sum of the dice that reads the value 5.
	 * @param roll {@link  Roll}
	 * @return the score
	 */
	public static int fives(Roll roll) {
		return calculateScoreForGivenDiceValue(5, roll);
	}
	
	/**
	 * Scores the sum of the dice that reads the value 6.
	 * @param roll {@link  Roll}
	 * @return the score
	 */
	public static int sixes(Roll roll) {
		return calculateScoreForGivenDiceValue(6, roll);
	}
	
	/**
	 * Scores the sum of the two highest matching dice.
	 * @param roll {@link  Roll}
	 * @return the score
	 */
	public static int pair(Roll roll) {
	    int[] diceValueCounts = new int[6]; // 6 because there are 6 possible values on a dice
	    int countsArrayLength = diceValueCounts.length;
	    roll.getDiceValueList().forEach(diceValue -> diceValueCounts[diceValue - 1]++);

	    for (int i = 0; i != countsArrayLength; i++)
	        if (diceValueCounts[countsArrayLength - i - 1] >= 2)
	            return (countsArrayLength - i) * 2;
	    return 0;
	}
	
	/**
	 * If there are two pairs of dice with the same number, scores the sum of these dice.
	 * @param roll {@link  Roll}
	 * @return the score
	 */
	public static int twoPair(Roll roll) {
		int[] diceValueCounts = new int[6];
		int countsArrayLength = diceValueCounts.length;
	    roll.getDiceValueList().forEach(diceValue -> diceValueCounts[diceValue - 1]++);
	    
	    int numberOfPair = 0;
	    int score = 0;
	    for (int i = 0; i < countsArrayLength; i++)
	        if (diceValueCounts[countsArrayLength - i - 1] >= 2) {
	        	numberOfPair++;
	            score += (countsArrayLength - i);
	        }        
	    
	    if (numberOfPair == 2)
	        return score * 2;
	    else
	        return 0;
	}
	
	/**
	 * If there are four dice with the same number, scores the sum of these dice
	 * @param roll {@link  Roll}
	 * @return the score
	 */
	public static int fourOfAKind(Roll roll) {
	    return calculateSumOfValuesAppearingWithGivenOccurrence(4, roll);
	}
	
	/**
	 * If there are three dice with the same number, scores the sum of these dice.
	 * @param roll {@link  Roll}
	 * @return the score
	 */
	public static int threeOfAKind(Roll roll) {
	    return calculateSumOfValuesAppearingWithGivenOccurrence(3, roll);
	}
	
	/**
	 * If the dice read 1,2,3,4,5, scores 15 (the sum of all the dice).
	 * @param roll {@link  Roll}
	 * @return the score
	 */
	public static int smallStraight(Roll roll) {
		return calculateStraight(1, roll);
	}
	
	/**
	 * If the dice read 2,3,4,5,6, scores 20 (the sum of all the dice).
	 * @param roll {@link  Roll}
	 * @return the score
	 */
	public static int largeStraight(Roll roll) {
	    return calculateStraight(6, roll);
	}

    public static int fullHouse(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies;
        boolean _2 = false;
        int i;
        int _2_at = 0;
        boolean _3 = false;
        int _3_at = 0;




        tallies = new int[6];
        tallies[d1-1] += 1;
        tallies[d2-1] += 1;
        tallies[d3-1] += 1;
        tallies[d4-1] += 1;
        tallies[d5-1] += 1;

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 2) {
                _2 = true;
                _2_at = i+1;
            }

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 3) {
                _3 = true;
                _3_at = i+1;
            }

        if (_2 && _3)
            return _2_at * 2 + _3_at * 3;
        else
            return 0;
    }
    
	private static int calculateScoreForGivenDiceValue(int diceValue, Roll roll) {
		int diceValueCount = 0;
		for (int value : roll.getDiceValueList()) {
			if (value == diceValue) {
				diceValueCount++;
			}
		}
		return diceValueCount * diceValue;
	}
	
	private static int calculateSumOfValuesAppearingWithGivenOccurrence(int occurrence, Roll roll) {
		int[] diceValueCounts = new int[6];
		int countsArrayLength = diceValueCounts.length;
	    roll.getDiceValueList().forEach(diceValue -> diceValueCounts[diceValue - 1]++);
	    
	    
	    for (int i = 0; i < countsArrayLength; i++)
	        if (diceValueCounts[i] >= occurrence)
	            return (i + 1) * occurrence;
	    return 0;
	}
	
	private static int calculateStraight(int straightIdentifier, Roll roll) {
		if (roll.getDiceValueList().stream().distinct().count() == 5L 
				&& roll.getDiceValueList().contains(straightIdentifier)) {
			return sumAllDicesValues(roll);
		}
		return 0;
	}
	
	private static Integer sumAllDicesValues(Roll roll) {
		return roll.getDiceValueList().stream().reduce(0, Integer::sum);
	}
}
