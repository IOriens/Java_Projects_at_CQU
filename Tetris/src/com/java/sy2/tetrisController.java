package com.java.sy2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class tetrisController implements tetrisConstants{
	tetrisModel gameModel;
	public static boolean isPlaying = false;
	public int level = 1;

	int[][] enemyArr=new int[rowNum][columnNum];

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



	// ObjectInputStream objFromServer ;
	// ObjectOutputStream objToServer ;
	private DataInputStream fromServer;
	private DataOutputStream toServer;



	@SuppressWarnings("resource")
	public void connectToServer() {
		try {

			String host="localhost";
			Socket socket;
			int port=8000;		
			socket = new Socket(host,port);

			// objFromServer = new ObjectInputStream(
			// 		socket.getInputStream());
			// objToServer = new ObjectOutputStream(
			// 		socket.getOutputStream());

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
					int player = fromServer.readInt();
					System.out.println(player+"============Player==============");
					System.out.println(fromServer.readInt()+"=======readed=====");


					while(true){			
						
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

						
						Thread.sleep(500);
					}


				}catch(Exception e){
					System.out.println(e+"====EX2======");
				}

				/*
				while (true) {				
					try {
//						objToServer.writeObject(gameModel.getScrArr());
//						enemyArr=(int[][]) objFromServer.readObject();
						Thread.sleep(500);
					}
					catch (Exception e) {
						System.out.println(e);
					}
				}
				 */				
			}
		});
		thread.start();
	}

	public int[][] getEnemyArr() {
		// TODO Auto-generated method stub
		return enemyArr;
	}
}

