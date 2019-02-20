package CleanStrike.carrom;

import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;

public enum Action {
	STRIKE(ActionHelper::strike) ,
	MULTISTRIKE(ActionHelper::multiStrike),
	REDSTRIKE(ActionHelper::redStrike),
	STRIKERSTRIKE(ActionHelper::strikerStrike),
	DEFUNCTCOIN(ActionHelper::defunction),
	NONE(ActionHelper::strike);
	
	private final BinaryOperator<Object> operator;
	
	Action(BinaryOperator<Object> operator){
		this.operator =operator;
	}
	
}
