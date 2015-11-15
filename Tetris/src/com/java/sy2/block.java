package com.java.sy2;



public class block implements tetrisConstants{
	static int[][] pattern = {
			{ 0x0f00, 0x4444, 0x0f00, 0x4444 },
			{ 0x04e0, 0x0464, 0x00e4, 0x04c4 },
			{ 0x4620, 0x6c00, 0x4620, 0x6c00 },
			{ 0x2640, 0xc600, 0x2640, 0xc600 },
			{ 0x6220, 0x1700, 0x2230, 0x0740 },
			{ 0x6440, 0x0e20, 0x44c0, 0x8e00 },
			{ 0x0660, 0x0660, 0x0660, 0x0660 } };

	static int blockType; 
	static int turnState; 
	int blockState;


	block() {		
		blockType = (int) (Math.random() * 1000) % 7;
		turnState = (int) (Math.random() * 1000) % 4;
		blockState=fallingState;
	}


	public int getState(){
		return blockState;
	}


	public void reset() {
		blockType = (int) (Math.random() * 1000) % 7;
		turnState = (int) (Math.random() * 1000) % 4;
		blockState=fallingSquares;
	}


	public int[][] leftTurn(int row,int col,int arr[][]) {

		dispBlock(row,col,arr,blankSquares);
		turnState = (turnState + 1) % 4;
		dispBlock(row,col,arr,fallingSquares);

		return arr;
	}

	public  int getBlockType() {
		return blockType;
	}

	public  void setBlockType(int blockType) {
		block.blockType = blockType;
	}

	public int getBlockState() {
		return blockState;
	}

	public void setBlockState(int blockState) {
		this.blockState = blockState;
	}

	// ʵ�֡��顱�����Ƶķ���
	public int[][]  leftMove(int row,int col,int arr[][]) {
		dispBlock(row,col,arr,blankSquares);
		col--;
		dispBlock(row,col,arr,fallingSquares);
		return arr;
	}
	public  int getTurnState() {
		return turnState;
	}

	public  void setTurnState(int turnState) {
		block.turnState = turnState;
	}


	public int[][] rightMove(int row,int col,int arr[][]) {		
		dispBlock(row,col,arr,blankSquares);
		col++;
		dispBlock(row,col,arr,fallingSquares);	
		return arr;
	}


	public int[][] fallDown1(int row,int col,int arr[][]) {
		dispBlock(row,col,arr,blankSquares);
		row--;			
		dispBlock(row,col,arr,fallingSquares);			
		return arr;
	}

	public int[][] fallDown2(int row,int col,int arr[][]) {
		dispBlock(row,col,arr,bottomSquares);			
		blockState=stableState;
		return arr;
	}


	public synchronized void dispBlock(int row,int col,int arr[][],int type) {
		int k = 0x8000;	
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (((int) pattern[blockType][turnState] & k) != 0) {
					if(type==blankSquares)
						arr[row - i][col + j]=blankSquares;
					else if(type==fallingSquares)
						arr[row - i][col + j]=fallingSquares;
					else
						arr[row - i][col + j]=bottomSquares;
				}
				k = k >> 1;
			}
		}
	}


	public static int getCurrPattern(int blockType,int turnState) {
		return pattern[blockType][turnState];
	}

}
