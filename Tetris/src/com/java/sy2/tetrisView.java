package com.java.sy2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;

public class tetrisView extends JFrame {

	private static final long serialVersionUID = -2208003941032001015L;
	//	private JPanel contentPane;
	private JPanel rightScr;
	@SuppressWarnings("unused")
	private MyPanel infoScr;
	@SuppressWarnings("unused")
	private MyPanel controlScr;

	public static MyTimer timer;
	public Thread timerIns;

	public static JTextField scoreField, levelField;

	private tetrisController gameController;
	private GameCanvas gameCanvas;
	private EnemyCanvas enemyCanvas;


	//sounds
	String fileBackground = "sounds/music.wav";	
	Clip soundClipBackground;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tetrisView frame = new tetrisView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public tetrisView() {
		setTitle("联网对战俄罗斯方块");		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int width=614;
		int height=490;
		setSize(width+width/2, height);
		setLayout(new GridLayout(1, 3));

		gameController=new tetrisController();
		

		gameCanvas=new GameCanvas(gameController);
		gameCanvas.addKeyListener(gameCanvas);


		add(gameCanvas);


		//sound
		try {
			File fileBackgroundf=new File(fileBackground);
			URL url=fileBackgroundf.toURI().toURL();
			if (url == null) {
				System.err.println("Couldn't find file: " + fileBackground);
			} else {
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
				soundClipBackground = AudioSystem.getClip();
				soundClipBackground.open(audioIn);
			}			
		} catch (UnsupportedAudioFileException e) {
			System.err.println("Audio Format not supported!");
		} catch (Exception e) {
			e.printStackTrace();
		}



		timer = new MyTimer(gameController,gameCanvas);

		timerIns=new Thread(timer);		

		rightScr = new JPanel();
		rightScr.setLayout(new GridLayout(2, 1, 0, 30));
		rightScr.setSize(120, 500);
		add(rightScr);


		JPanel infoScr = new MyPanel();
		infoScr.setLayout(new GridLayout(4, 1, 0, 5));
//		infoScr.setBackground(new Color(77, 208, 225));
		infoScr.setSize(120, 300);
		
		rightScr.add(infoScr);
		JLabel scorep = new JLabel("分数:", JLabel.LEFT);
		JLabel levelp = new JLabel("等级:", JLabel.LEFT);
		scoreField = new JTextField(8);
		levelField = new JTextField(8);
		scoreField.setHorizontalAlignment(JTextField.CENTER);
		levelField.setHorizontalAlignment(JTextField.CENTER);
		scoreField.setEditable(false);
		levelField.setEditable(false);
		infoScr.add(scorep);
		infoScr.add(scoreField);
		infoScr.add(levelp);
		infoScr.add(levelField);
		scorep.setSize(new Dimension(20, 60));
		scoreField.setSize(new Dimension(20, 60));



		levelp.setSize(new Dimension(20, 60));
		levelField.setSize(new Dimension(20, 60));
		scoreField.setText("0");
		levelField.setText("1");


		JPanel controlScr = new MyPanel();
		controlScr.setLayout(new GridLayout(5, 1, 0, 5));
		controlScr.setBackground(new Color(0, 131, 143));
		rightScr.add(controlScr);
		
		
		
		timerIns.setDaemon(true);
		timerIns.start();
		
		// 定义按钮play
		JButton play_b = new JButton("开始游戏");
		play_b.setBackground(Color.white);
		play_b.setSize(new Dimension(50, 200));
		play_b.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!gameController.isPlaying()){
					gameCanvas.repaint();
					gameController.initScr();
					gameController.Play();				
					scoreField.setText("0");					
					if (soundClipBackground.isRunning()) soundClipBackground.stop(); 
					soundClipBackground.setFramePosition(0); // rewind to the beginning
					soundClipBackground.start();             // Start playing
					gameCanvas.requestFocus();				
				}
			}
		});

		// 定义按钮Level UP
		JButton level_up_b = new JButton("提高级数");
		level_up_b.setBackground(Color.white);
		level_up_b.setSize(new Dimension(50, 200));
		level_up_b.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				gameController.levelUp();
				levelField.setText(gameController.getLevel()+"");
//				gameCanvas.requestFocus();
			}
		});

		// 定义按钮Level Down
		JButton level_down_b = new JButton("降低级数");
		level_down_b.setBackground(Color.white);
		level_down_b.setSize(new Dimension(50, 200));
		level_down_b.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				gameController.levelDown();
				levelField.setText(gameController.getLevel()+"");
//				gameCanvas.requestFocus();
			}
		});

		// 定义按钮NetFight_b
		JButton NetFight_b = new JButton("联网对战");
		NetFight_b.setBackground(Color.white);
		NetFight_b.setSize(new Dimension(50, 200));
		NetFight_b.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				gameController.connectToServer();
				
			}
		});

		// 定义按钮Quit
		JButton quit_b = new JButton("退出游戏");
		quit_b.setBackground(Color.white);
		quit_b.setSize(new Dimension(50, 200));
		quit_b.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});

		controlScr.add(play_b);
		controlScr.add(level_up_b);
		controlScr.add(level_down_b);
		controlScr.add(NetFight_b);
		controlScr.add(quit_b);

		Thread scoreMonitor=new Thread(new Runnable() {
			public void run() {
				while(true){
					scoreField.setText(""+gameController.getScore());
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						System.out.println(e);
					}
				}

			}
		});
		scoreMonitor.start();
		enemyCanvas=new EnemyCanvas();
		add(enemyCanvas);

		Thread ememyMonitor=new Thread(new Runnable() {
			public void run() {
				while(true){
					enemyCanvas.paintEnemy(gameController.getEnemyArr(),gameController.isEnemyIsGameOver(),gameController.getEnemyScore());
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		ememyMonitor.start();		
		gameCanvas.requestFocus();

	}
}

class MyPanel extends JPanel{
	private static final long serialVersionUID = 8988470037429045973L;

	public Insets getInsets() {
		return new Insets(30, 50, 30, 50);
	}
}

class GameCanvas extends JPanel implements KeyListener,tetrisConstants{

	private static final long serialVersionUID = -6789163478235725546L;
	final int unitSize = 30; 
	int rowNum; 
	int columnNum;
	private tetrisController gameController;

	//sound
	String fileHit="sounds/hit.wav";
	Clip soundClipHit;

	GameCanvas(tetrisController gameController) {

		//sound
		try {
			File fileHitf=new File(fileHit);
			URL url=fileHitf.toURI().toURL();
			if (url == null) {
				System.err.println("Couldn't find file: " + fileHit);
			} else {
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
				soundClipHit = AudioSystem.getClip();
				soundClipHit.open(audioIn);
			}			
		} catch (UnsupportedAudioFileException e) {
			System.err.println("Audio Format not supported!");
		} catch (Exception e) {
			e.printStackTrace();
		}


		setBorder(BorderFactory.createLineBorder(Color.black));
		rowNum = 15;
		columnNum = 10;
		this.gameController=gameController;		
		initScr();
	}

	void initScr() {
		gameController.initScr();;		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < rowNum; i++){
			for (int j = 0; j < columnNum; j++){
				drawUnit(i, j, gameController.getScrArr()[i][j],g);				
			}
		}
		g.setColor(new Color(195, 185, 123));
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 	
		g.drawString("ME", getWidth()/2-10, 20);
		if(gameController.isGameEnd()){
			g.setColor(Color.white);	
			g.fill3DRect(0, 0, getWidth(), getHeight(),true);
			g.setColor(new Color(0, 160,220));
			g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 	
			g.drawString("Game Over", getWidth()/2-64, getHeight()/2);				
			String scoreString="Score: "+gameController.getScore();
			g.drawString(scoreString,getWidth()/2-44, getHeight()/2+30);
		}
	}

	public void drawUnit(int row, int col, int type,Graphics g) {
		//		g=getGraphics();
		switch (type) { 
		case blankSquares:
			g.setColor(new Color(123,120, 115));
			break; 
		case fallingSquares:			
			g.setColor(new Color(41,160, 192));
			break;
		case bottomSquares:
			g.setColor(new Color(178,120, 118));
			break; 
		}
		g.fill3DRect(col * unitSize, getSize().height - (row + 1) * unitSize,
				unitSize, unitSize, true);		
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	// 处理键盘输入的方法
	public void keyPressed(KeyEvent e) {
				
		if(!gameController.isPlaying())
			return;
		if (soundClipHit.isRunning()) soundClipHit.stop();
		soundClipHit.setFramePosition(0); // rewind to the beginning
		soundClipHit.start();  
		switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN:
			gameController.fallDown();		
			repaint();
			break;
		case KeyEvent.VK_LEFT:		
			gameController.leftMove();
			repaint();
			break;
		case KeyEvent.VK_RIGHT:
			gameController.rightMove();			
			repaint();
			break;
		case KeyEvent.VK_UP:
			gameController.leftTurn();
			repaint();
			break;
		}
	}
}

class EnemyCanvas extends JPanel implements tetrisConstants{

	private static final long serialVersionUID = -2651733920085838493L;
	final int unitSize = 30; 
	int rowNum; 
	int columnNum;
	int enemyScore;
	int arr[][];
	boolean isGameEnd;
	EnemyCanvas() {
		setBorder(BorderFactory.createLineBorder(Color.black));
		rowNum = 15;
		columnNum = 10;
		enemyScore=0;
		arr=new int[rowNum][columnNum];
		for (int[] is : arr) {
			for (@SuppressWarnings("unused") int i : is) {
				i=blankSquares;
			}
		}
	}

	void paintEnemy(int arr[][],boolean isGameEnd,int score){
		this.arr=arr;
		this.isGameEnd=isGameEnd;
		this.enemyScore=score;
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < rowNum; i++){
			for (int j = 0; j < columnNum; j++){
				drawUnit(i, j, arr[i][j],g);				
			}
		}
		g.setColor(new Color(195, 185, 123));
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 	
		g.drawString("Enemy", getWidth()/2-30, 20);
		if(isGameEnd){
			g.setColor(Color.white);	
			g.fill3DRect(0, 0, getWidth(), getHeight(),true);
			g.setColor(new Color(0, 160,220));
			g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 	
			g.drawString("Game Over", getWidth()/2-64, getHeight()/2);				
			String scoreString="Score: "+enemyScore;
			g.drawString(scoreString,getWidth()/2-44, getHeight()/2+30);		
		}
	}

	public void drawUnit(int row, int col, int type,Graphics g) {
		//		g=getGraphics();
		switch (type) { 
		case blankSquares:
			g.setColor(new Color(123,120, 115));
			break; 
		case fallingSquares:			
			g.setColor(new Color(41,160, 192));
			break;
		case bottomSquares:
			g.setColor(new Color(178,120, 118));
			break; 
		}
		g.fill3DRect(col * unitSize, getSize().height - (row + 1) * unitSize,
				unitSize, unitSize, true);		
	}
}


class MyTimer implements Runnable,tetrisConstants{
	tetrisController gameController;
	GameCanvas gameCanvas;
	public MyTimer(tetrisController gameController,GameCanvas gameCanvas) {
		this.gameController = gameController;
		this.gameCanvas=gameCanvas;
	}
	public void run() {
		while (true) {		
			try {
				Thread.sleep((10 - gameController.getLevel()+ 1) * 100);				
			} catch (InterruptedException e) {
			}
			if(gameController.getPlayerNum()==onePlayer){
				gameController.fallDown();
				gameCanvas.repaint();
				if (gameController.getBlockState()==stableState) {
					gameController.checkFullLine();
					gameCanvas.repaint();
					if (!gameController.isGameEnd()) {					
						gameController.resetBlock();
						gameCanvas.repaint();
					}				
				}
			}
			if(gameController.getPlayerNum()==twoPlayer&&gameController.isStarted()&&gameController.isEnemyIsPlaying()){
				gameController.fallDown();
				gameCanvas.repaint();
				if (gameController.getBlockState()==stableState) {
					gameController.checkFullLine();
					gameCanvas.repaint();
					if (!gameController.isGameEnd()) {					
						gameController.resetBlock();
						gameCanvas.repaint();
					}				
				}
			}
		
		}
	}
}