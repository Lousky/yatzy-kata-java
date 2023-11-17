package com.github.lousky.kata.yatzy;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
		return sumAllGivenDiceValue(1, roll);
	}
	
	/**
	 * Scores the sum of the dice that reads the value 2.
	 * @param roll {@link  Roll}
	 * @return the score
	 */
	public static int twos(Roll roll) {
		return sumAllGivenDiceValue(2, roll);
	}
	
	/**
	 * Scores the sum of the dice that reads the value 3.
	 * @param roll {@link  Roll}
	 * @return the score
	 */
	public static int threes(Roll roll) {
		return sumAllGivenDiceValue(3, roll);
	}
	
	/**
	 * Scores the sum of the dice that reads the value 4.
	 * @param roll {@link  Roll}
	 * @return the score
	 */
	public static int fours(Roll roll) {
		return sumAllGivenDiceValue(4, roll);
	}
	
	/**
	 * Scores the sum of the dice that reads the value 5.
	 * @param roll {@link  Roll}
	 * @return the score
	 */
	public static int fives(Roll roll) {
		return sumAllGivenDiceValue(5, roll);
	}
	
	/**
	 * Scores the sum of the dice that reads the value 6.
	 * @param roll {@link  Roll}
	 * @return the score
	 */
	public static int sixes(Roll roll) {
		return sumAllGivenDiceValue(6, roll);
	}
	
	/**
	 * Scores the sum of the two highest matching dice.
	 * @param roll {@link  Roll}
	 * @return the score
	 */
	public static int pair(Roll roll) {
		Map<Integer, Long> valueOccurrenceMap = createValueOccurrenceMap(roll);
		
		for (int i = Roll.MAX_DICE_VALUE; i != 0; i--) {
			Long valueOccurrence = valueOccurrenceMap.get(i);
			if (valueOccurrence != null && valueOccurrence == 2) {
				return i * 2;
			}
		}
		return 0;
	}
	
	/**
	 * If there are two pairs of dice with the same number, scores the sum of these dice.
	 * @param roll {@link  Roll}
	 * @return the score
	 */
	public static int twoPair(Roll roll) {
		Map<Integer, Long> valueOccurrenceMap = createValueOccurrenceMap(roll);
		
		int numberOfPair = 0;
		int score = 0;
		for (Map.Entry<Integer, Long> entry : valueOccurrenceMap.entrySet()) {
			if (entry.getValue() >= 2) {
				numberOfPair++;
				score += entry.getKey();
			}
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
		return sumValuesWithGivenOccurrence(4, roll);
	}
	
	/**
	 * If there are three dice with the same number, scores the sum of these dice.
	 * @param roll {@link  Roll}
	 * @return the score
	 */
	public static int threeOfAKind(Roll roll) {
		return sumValuesWithGivenOccurrence(3, roll);
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
	
	/**
	 * If the dice are two of a kind and three of a kind, scores the sum of all the dice.
	 * @param roll {@link  Roll}
	 * @return the score
	 */
	public static int fullHouse(Roll roll) {
		Map<Integer, Long> valueOccurrenceMap = createValueOccurrenceMap(roll);
		
		int twoOfAKindScore = 0;
		int threeOfAKindScore = 0;
		
		for (Map.Entry<Integer, Long> entry : valueOccurrenceMap.entrySet()) {
			if (entry.getValue() == 2) {
				twoOfAKindScore = 2 * entry.getKey();
			} else if (entry.getValue() == 3) {
				threeOfAKindScore = 3 * entry.getKey();
			}
		}
		return twoOfAKindScore == 0 || threeOfAKindScore == 0 ? 0 
				: twoOfAKindScore + threeOfAKindScore;
	}
    
	private static int sumAllGivenDiceValue(int diceValue, Roll roll) {
		return roll.getDiceValueList().stream()
				.filter(value -> value == diceValue)
				.reduce(0, Integer::sum);
	}
	
	private static int sumValuesWithGivenOccurrence(int occurrence, Roll roll) {
		Map<Integer, Long> valueOccurrenceMap = createValueOccurrenceMap(roll);
		
		for (Map.Entry<Integer, Long> entry : valueOccurrenceMap.entrySet()) {
			if (entry.getValue() >= occurrence) {
				return entry.getKey() * occurrence;
			}
		}
		return 0;
	}
	
	private static Map<Integer, Long> createValueOccurrenceMap(Roll roll) {
		return roll.getDiceValueList().stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	}
	
	private static int calculateStraight(int straightIdentifier, Roll roll) {
		if (roll.getDiceValueList().stream().distinct().count() == Roll.NUM_OF_DICES_IN_ROLL 
				&& roll.getDiceValueList().contains(straightIdentifier)) {
			return sumAllDicesValues(roll);
		}
		return 0;
	}
	
	private static Integer sumAllDicesValues(Roll roll) {
		return roll.getDiceValueList().stream().reduce(0, Integer::sum);
	}
}
