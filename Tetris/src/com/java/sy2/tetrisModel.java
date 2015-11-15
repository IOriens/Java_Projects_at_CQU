
package com.java.sy2;

import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

public class tetrisModel implements tetrisConstants{

	//data
	//	final int unitSize = 30;
	int rowNum; 
	int columnNum;
	int maxAllowRowNum; 
	int blockInitRow; 
	int blockInitCol; 
	int blockCurrRow;
	int blockCurrCol;
	int[][] scrArr; 
	block b;

	int score;
	
	//sound
	String fileLine="sounds/line.wav";
	Clip soundClipLine;
	

	
	tetrisModel() {
		rowNum = 15;
		columnNum = 10;
		maxAllowRowNum = rowNum - 2;
		b = new block();
		blockInitRow = rowNum-1;
		blockInitCol = columnNum / 2 - 2;
		blockCurrRow=blockInitRow;
		blockCurrCol=blockInitCol;
		scrArr = new int[32][32];
		score=0;
		
		
		//sound
		try {
			File fileLinef=new File(fileLine);
			URL url=fileLinef.toURI().toURL();
			if (url == null) {
				System.err.println("Couldn't find file: " + fileLine);
			} else {
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
				soundClipLine = AudioSystem.getClip();
				soundClipLine.open(audioIn);
			}			
		} catch (UnsupportedAudioFileException e) {
			System.err.println("Audio Format not supported!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	void initScr() {
		for (int i = 0; i < rowNum; i++)
			for (int j = 0; j < columnNum; j++)
				scrArr[i][j] = blankSquares;
		b.reset();		
	}
	
	public void resetBlock() {
		b.reset();
		blockCurrRow=blockInitRow;
		blockCurrCol=blockInitCol;
	}


	public void drawUnit(int row, int col, int type) {
		scrArr[row][col] = type;
	}

	public block getblock() {
		return b; 
	}


	void deleteFullLine() {
		int full_line_num = 0;
		int k = 0;
		for (int i = 0; i < rowNum; i++) {
			boolean isfull = true;
			for (int j = 0; j < columnNum; j++){
				if (scrArr[i][j] == blankSquares) {
					k++;
					isfull = false;
					break;
				}
			}

			if (isfull){
				full_line_num++;
				if (soundClipLine.isRunning()) soundClipLine.stop();
				soundClipLine.setFramePosition(0); // rewind to the beginning
				soundClipLine.start();  
			}
				
			if (k != 0 && k - 1 != i && !isfull)
				for (int j = 0; j < columnNum; j++) {
					if (scrArr[i][j] == 0)
						drawUnit(k - 1, j, blankSquares);
					else
						drawUnit(k - 1, j, bottomSquares);
					scrArr[k - 1][j] = scrArr[i][j];
				}
		}

		for (int i = k - 1; i < rowNum; i++) {
			for (int j = 0; j < columnNum; j++) {
				drawUnit(i, j, blankSquares);
				scrArr[i][j] = blankSquares;
			}
		}

		score += full_line_num*columnNum;			
	}

	public boolean isGameEnd() {
		for (int col = 0; col < columnNum; col++) {
			if (scrArr[maxAllowRowNum][col]==bottomSquares)
				return true;
		}
		return false;
	}

	public int getScore() {
		return score;
	}

	public int[][] getScrArr() {
		return scrArr;
	}	

	public void leftTurn() {
		if(assertValid(b.getBlockType(), (b.getTurnState()+ 1) % 4,blockCurrRow, blockCurrCol)){
			scrArr=b.leftTurn(blockCurrRow, blockCurrCol, scrArr);
		}
	}

	public void leftMove () {

		if(assertValid(b.getBlockType(), b.getTurnState(),blockCurrRow, blockCurrCol-1)){
			scrArr=b.leftMove(blockCurrRow, blockCurrCol, scrArr);
			blockCurrCol--;
		}		
	}

	public void rightMove () {
		if(assertValid(b.getBlockType(), b.getTurnState(),blockCurrRow, blockCurrCol+1)){
			scrArr=b.rightMove(blockCurrRow, blockCurrCol, scrArr);
			blockCurrCol++;
		}
	}

	public void fallDown () {
		if(b.getBlockState()!=stableState){
			if(assertValid(b.getBlockType(), b.getTurnState(),blockCurrRow-1, blockCurrCol)){
				scrArr=b.fallDown1(blockCurrRow, blockCurrCol, scrArr);
				blockCurrRow--;
			}else{
				scrArr=b.fallDown2(blockCurrRow, blockCurrCol, scrArr);
			}
		}
		
	}
	
	public int getBlockState(){
		return b.getState();
	}
	
	public int getScrArrXY(int row, int col) {
		if (row < 0 || row >= rowNum || col < 0 || col >= columnNum)
			return (-1);
		else
			return (scrArr[row][col]);
	}
	
	boolean assertValid(int t, int s, int row, int col){		
		int k = 0x8000;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if ((int) (block.getCurrPattern(t,s) & k) != 0) {

					int temp = getScrArrXY(row - i, col + j);
					if (temp < 0 || temp == 2)
						return false;
				}
				k = k >> 1;
			}
		}
		return true;
	}
}
