package com.java.sy2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class tetrisController implements tetrisConstants{
	tetrisModel gameModel;
	private int playerNum;
	public static boolean isPlaying = false;
	public int level = 1;

	int[][] enemyArr=new int[rowNum][columnNum];

	boolean isStarted;
	boolean enemyIsPlaying;
	boolean enemyIsGameOver;
	int enemyScore;

	public int getEnemyScore() {
		return enemyScore;
	}

	public void setEnemyScore(int enemyScore) {
		this.enemyScore = enemyScore;
	}

	public boolean isEnemyIsPlaying() {
		return enemyIsPlaying;
	}

	public tetrisController() {
		gameModel=new tetrisModel();
		playerNum=onePlayer;
		enemyIsPlaying=false;
		enemyIsGameOver=false;
		isStarted=false;
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

	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	public void Play() {
		isPlaying=true;
		isStarted=true;
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



	// ObjectInputStream objFromServer ;
	// ObjectOutputStream objToServer ;
	private DataInputStream fromServer;
	private DataOutputStream toServer;



	public boolean isEnemyIsGameOver() {
		return enemyIsGameOver;
	}

	public void setEnemyIsGameOver(boolean enemyIsGameOver) {
		this.enemyIsGameOver = enemyIsGameOver;
	}

	@SuppressWarnings("resource")
	public void connectToServer() {
		playerNum=twoPlayer;
		try {

			String host="localhost";
			Socket socket;
			int port=8000;		
			socket = new Socket(host,port);


			// Create an input stream to receive data from the server
			fromServer = new DataInputStream(socket.getInputStream());

			// Create an output stream to send data to the server
			toServer = new DataOutputStream(socket.getOutputStream());
			
		}
		catch (Exception ex) {
			System.err.println(ex);
		}

		Thread thread = new Thread(new Runnable() {
			public void run() {		
				try{	
					while(true){
						toServer.writeBoolean(isStarted);
						enemyIsPlaying=fromServer.readBoolean();
							
							for(int i=0;i<rowNum;i++){
								for(int j=0;j<columnNum;j++){
									toServer.writeInt(gameModel.getScrArr()[i][j]);
								}
							}

							for(int i=0;i<rowNum;i++){
								for(int j=0;j<columnNum;j++){
									enemyArr[i][j]=fromServer.readInt();
								}
							}							

						toServer.writeBoolean(isGameEnd());
						enemyIsGameOver=fromServer.readBoolean();
						toServer.writeInt(getScore());
						enemyScore=fromServer.readInt();
						if(enemyIsGameOver==true){
//							break;
						}
						Thread.sleep(500);
					}
				}catch(Exception e){
					System.out.println(e+"====EX2======");
				}

			}
		});
		thread.start();
	}

	public int[][] getEnemyArr() {
		// TODO Auto-generated method stub
		return enemyArr;
	}

	public int getPlayerNum() {
		return playerNum;
	}
}

