package problemdomain;

import java.io.Serializable;

/**
 * Game status.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 10/7/2020
 */
public class Game implements Serializable
{
	private boolean start;
	private boolean yourTurn;
	private boolean youWin;
	private boolean playAgain;

	/**
	 * @param start     The game start
	 * @param yourTurn  This is your turn
	 * @param youWin    You win this game
	 * @param playAgain Player want to play again
	 */
	public Game(boolean start, boolean yourTurn, boolean youWin, boolean playAgain)
	{
		this.start = start;
		this.yourTurn = yourTurn;
		this.youWin = youWin;
		this.playAgain = playAgain;
	}

	/**
	 * @return the start
	 */
	public boolean isStart()
	{
		return start;
	}

	/**
	 * @return the yourTurn
	 */
	public boolean isYourTurn()
	{
		return yourTurn;
	}

	/**
	 * @return the youWin
	 */
	public boolean isYouWin()
	{
		return youWin;
	}

	/**
	 * @return the playAgain
	 */
	public boolean isPlayAgain()
	{
		return playAgain;
	}

}
