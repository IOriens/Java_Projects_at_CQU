package com.java.sy2;

import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class tetrisServer extends JFrame implements tetrisConstants{

	/**
	 * 
	 */
	private static final long serialVersionUID = -120597616338874927L;

	public static void main(String[] args) {
		tetrisServer frame = new tetrisServer();
	}

	public tetrisServer() {
		JTextArea jtaLog = new JTextArea();

		// Create a scroll pane to hold text area
		JScrollPane scrollPane = new JScrollPane(jtaLog);

		// Add the scroll pane to the frame
		add(scrollPane, BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setTitle("TicTacToeServer");
		setVisible(true);

		try {
			ServerSocket serverSocket = new ServerSocket(8000);
			jtaLog.append(new Date() +
				": Server started at socket 8000\n");
			int sessionNo = 1;
			jtaLog.append(new Date() +
				": Wait for players to join session " + sessionNo + '\n');


			//Player1
			Socket player1 = serverSocket.accept();

			jtaLog.append(new Date() + ": Player 1 joined session " +
				sessionNo + '\n');
			jtaLog.append("Player 1's IP address" +
				player1.getInetAddress().getHostAddress() + '\n');
			new DataOutputStream(
				player1.getOutputStream()).writeInt(PLAYER1);	


			//Player2
			Socket player2 = serverSocket.accept();
			jtaLog.append(new Date() +
				": Player 2 joined session " + sessionNo + '\n');
			jtaLog.append("Player 2's IP address" +
				player2.getInetAddress().getHostAddress() + '\n');
			jtaLog.append(new Date() + ": Start a thread for session " +
				sessionNo++ + '\n');
			new DataOutputStream(
				player2.getOutputStream()).writeInt(PLAYER2);


			HandleASession task = new HandleASession(player1, player2);
			new Thread(task).start();
		}
		catch(IOException ex) {
			System.err.println(ex+"======EX1=====");
		}
	}

}

class HandleASession implements Runnable, tetrisConstants {
	private Socket player1;
	private Socket player2;

	int rowNum=15; 
	int columnNum=10;

	private int[][] matrixA=new int[rowNum][columnNum];
	private int[][] matrixB=new int[rowNum][columnNum];


	public HandleASession(Socket player1, Socket player2) {
		this.player1 = player1;
		this.player2 = player2;

		for (int i = 0; i < rowNum; i++){
			for (int j = 0; j < columnNum; j++){
				matrixA[i][j]=blankSquares;
				matrixB[i][j]=blankSquares;
			}
		}				
	}

	public void run() {
		try {

			DataInputStream fromPlayer1 = new DataInputStream(
				player1.getInputStream());
			DataOutputStream toPlayer1 = new DataOutputStream(
				player1.getOutputStream());
			DataInputStream fromPlayer2 = new DataInputStream(
				player2.getInputStream());
			DataOutputStream toPlayer2 = new DataOutputStream(
				player2.getOutputStream());
			
			toPlayer1.writeInt(1);
			toPlayer2.writeInt(1);

			while(true){
				for(int i=0;i<rowNum;i++){
					for(int j=0;j<columnNum;j++){
						matrixA[i][j]=fromPlayer1.readInt();
					}
				}

				for(int i=0;i<rowNum;i++){
					for(int j=0;j<columnNum;j++){
						matrixB[i][j]=fromPlayer2.readInt();
					}
				}
				Thread.sleep(500);

				
				
				for(int i=0;i<rowNum;i++){
					for(int j=0;j<columnNum;j++){
						toPlayer1.writeInt(matrixB[i][j]);
					}
				}
				for(int i=0;i<rowNum;i++){
					for(int j=0;j<columnNum;j++){
						toPlayer2.writeInt(matrixA[i][j]);
					}
				}
				Thread.sleep(500);
			}				
		}		
		catch(IOException | InterruptedException ex) {
			System.err.println(ex+"=====EX3=====");
		}
	}
}