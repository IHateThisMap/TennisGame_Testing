import static org.junit.Assert.*;

import org.junit.Test;

public class TennisGameTest {
	
// Here is the format of the scores: "player1Score - player2Score"
// "love - love"
// "15 - 15"
// "30 - 30"
// "deuce"
// "15 - love", "love - 15"
// "30 - love", "love - 30"
// "40 - love", "love - 40"
// "30 - 15", "15 - 30"
// "40 - 15", "15 - 40"
// "player1 has advantage"
// "player2 has advantage"
// "player1 wins"
// "player2 wins"
	@Test
	public void testTennisGame_Start() {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Initial score incorrect", "love - love", score);		
	}
	

	@Test
	public void testTennisGame_MidGame() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player2Scored();

		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Initial score incorrect", "15 - 30", score);

		//Act
		game.player1Scored();
		game.player2Scored();
		
		score = game.getScore() ;
		// Assert
		assertEquals("Initial score incorrect", "30 - 40", score);		
	}

	@Test (expected = TennisGameException.class)
	public void testTennisGame_EahcPlayerWin4Points_Score_Deuce_and_tryMakeOneMoreScoreAfterThat() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Tie score", "deuce", score);

		//Act
		// This statement should cause an exception
		try {
			game.player1Scored();
		}catch (TennisGameException e) {
			//the exception should occur
			game.player2Scored();
		}
	}
	
	@Test
	public void testTennisGame_Player1_wins() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();

		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("p1 has advantage", "player1 has advantage", score);
		
		game.player1Scored();
		//Act
		score = game.getScore() ;
		// Assert
		assertEquals("p1 wins", "player1 wins", score);		
	}
	
	@Test
	public void testTennisGame_Player2_wins() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player2Scored();

		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("p2 has advantage", "player2 has advantage", score);
		
		game.player2Scored();
		//Act
		score = game.getScore() ;
		// Assert
		assertEquals("p2 wins", "player2 wins", score);		
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player1WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		// This statement should cause an exception
		game.player1Scored();			
	}		
}
