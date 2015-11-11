package com.java.sy2;

public class tetrisController {
	tetrisModel gameModel;
	public static boolean isPlaying = false;
	public int level = 1;

	public tetrisController() {
		gameModel=new tetrisModel();		
	}

	public void leftTurn() {
		if(isPlaying)
			gameModel.leftTurn();
	}

	public void leftMove () {
		if(isPlaying)
			gameModel.leftMove();
	}

	public void rightMove () {
		if(isPlaying)
			gameModel.rightMove();
	}

	public void fallDown () {
		if(isPlaying){
			gameModel.fallDown();		
		}
	}


	public int getLevel() {
		return level;
	}

	public  boolean isPlaying() {
		return isPlaying;
	}

	public int getScore() {
		return gameModel.getScore();
	}

	public int[][] getScrArr() {
		return gameModel.getScrArr();
	}

	public void initScr(){
		gameModel.initScr();
	}

	public int getBlockState() {
		return gameModel.getBlockState();		
	}

	public void checkFullLine() {
		gameModel.deleteFullLine();		
	}

	public void resetBlock() {
		gameModel.resetBlock();		
	}

	public boolean isGameEnd() {
		if(gameModel.isGameEnd()){
			isPlaying=false;
		}
		return gameModel.isGameEnd();	
	}

	public void Play() {
		isPlaying=true;
	}

	public void levelUp() {
		// TODO Auto-generated method stub
		level++;
	}

	public void levelDown() {
		// TODO Auto-generated method stub
		if(level-1>0){
			level--;
		}
	}
}
