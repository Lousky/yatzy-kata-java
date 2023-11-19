package com.github.lousky.kata.yatzy;

import java.util.List;
import java.util.stream.Stream;

/**
 * Represents a roll of {@link #NUM_OF_DICES_IN_ROLL} dices, 
 * every dice having a value between {@link #MIN_DICE_VALUE} and {@link #MAX_DICE_VALUE}.
 */
public class Roll {
	
	public static final Integer MIN_DICE_VALUE = 1;
	public static final Integer MAX_DICE_VALUE = 6;
	public static final Integer NUM_OF_DICES_IN_ROLL = 5;
	
	private List<Integer> diceValueList;
	
	private Roll(Integer... diceValues) {
		this.diceValueList = List.of(diceValues);
	}
	
	/**
	 * Creates an instance of Roll with the allowed number of dices and, for each dice, a valid value.
	 * @param diceValues the dice values
	 * @return a new instance of {@link Roll}
	 */
	public static Roll createInstance(Integer... diceValues) {
		if (diceValues.length != NUM_OF_DICES_IN_ROLL) {
			throw new IllegalStateException("A roll can contain only " + NUM_OF_DICES_IN_ROLL + " dices.");
		}
		
		Stream.of(diceValues).forEach(value -> {
			if (value < MIN_DICE_VALUE || value > MAX_DICE_VALUE) {
				throw new IllegalStateException("Dice value should be between " 
						+ MIN_DICE_VALUE + " and " + MAX_DICE_VALUE);
			}
		});
		
		return new Roll(diceValues);
	}
	
	public List<Integer> getDiceValueList() {
		return this.diceValueList;
	}
	
}
