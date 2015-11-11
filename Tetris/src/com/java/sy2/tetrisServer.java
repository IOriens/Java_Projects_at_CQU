package com.java.sy2;

import java.awt.BorderLayout;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
			// Create a server socket
			ServerSocket serverSocket = new ServerSocket(8000);
			jtaLog.append(new Date() +
					": Server started at socket 8000\n");

			// Number a session
			int sessionNo = 1;

			// Ready to create a session for every two players
			while (true) {
				jtaLog.append(new Date() +
						": Wait for players to join session " + sessionNo + '\n');

				// Connect to player 1
				Socket player1 = serverSocket.accept();

				jtaLog.append(new Date() + ": Player 1 joined session " +
						sessionNo + '\n');
				jtaLog.append("Player 1's IP address" +
						player1.getInetAddress().getHostAddress() + '\n');

				// Notify that the player is Player 1
				new DataOutputStream(
						player1.getOutputStream()).writeInt(PLAYER1);

				// Connect to player 2
				Socket player2 = serverSocket.accept();

				jtaLog.append(new Date() +
						": Player 2 joined session " + sessionNo + '\n');
				jtaLog.append("Player 2's IP address" +
						player2.getInetAddress().getHostAddress() + '\n');

				// Notify that the player is Player 2
				new DataOutputStream(
						player2.getOutputStream()).writeInt(PLAYER2);

				// Display this session and increment session number
				jtaLog.append(new Date() + ": Start a thread for session " +
						sessionNo++ + '\n');

				// Create a new thread for this session of two players
				HandleASession task = new HandleASession(player1, player2);

				// Start the new thread
				new Thread(task).start();
			}
		}
		catch(IOException ex) {
			System.err.println(ex);
		}
	}

}

class HandleASession implements Runnable, tetrisConstants {
	private Socket player1;
	private Socket player2;

	// Create and initialize cells
	//	private char[][] cell =  new char[3][3];
	int rowNum; // �����������
	int columnNum; // �����������

	private int[][] matrixA=new int[rowNum][columnNum];
	private int[][] matrixB=new int[rowNum][columnNum];

	//	private DataInputStream fromPlayer1;
	//	private DataOutputStream toPlayer1;
	//	private DataInputStream fromPlayer2;
	//	private DataOutputStream toPlayer2;

	// Continue to play
	//	private boolean continueToPlay = true;

	/** Construct a thread */
	public HandleASession(Socket player1, Socket player2) {
		this.player1 = player1;
		this.player2 = player2;

		// Initialize cells
		for (int i = 0; i < rowNum; i++){
			for (int j = 0; j < columnNum; j++){
				matrixA[i][j]=blankSquares;
				matrixB[i][j]=blankSquares;
			}
		}				
	}

	/** Implement the run() method for the thread */
	public void run() {
		try {
			// Create data input and output streams
			ObjectInputStream fromPlayer1 = new ObjectInputStream(
					player1.getInputStream());
			ObjectOutputStream toPlayer1 = new ObjectOutputStream(
					player1.getOutputStream());
			ObjectInputStream fromPlayer2 = new ObjectInputStream(
					player2.getInputStream());
			ObjectOutputStream toPlayer2 = new ObjectOutputStream(
					player2.getOutputStream());

			// Write anything to notify player 1 to start
			// This is just to let player 1 know to start
			toPlayer1.writeInt(1);

			// Continuously serve the players and determine and report
			// the game status to the players
			while (true) {
				// Receive a move from player 1		
				try {
					matrixA=(int[][]) fromPlayer1.readObject();
				} catch (ClassNotFoundException e) {

					System.out.println(e);				
				}

				toPlayer2.writeObject(matrixA);

				try {
					matrixB=(int[][]) fromPlayer2.readObject();
				} catch (ClassNotFoundException e) {
					System.out.println(e);
				}

				toPlayer2.writeObject(matrixB);
			}
		}
		catch(IOException ex) {
			System.err.println(ex);
		}
	}
}