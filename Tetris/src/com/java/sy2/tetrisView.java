package com.java.sy2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;

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

public class tetrisView extends JFrame {

	private static final long serialVersionUID = -2208003941032001015L;
//	private JPanel contentPane;
	private JPanel rightScr;
	private MyPanel infoScr;
	private MyPanel controlScr;

	public static MyTimer timer;
	public Thread timerIns;

	public static JTextField scoreField, levelField;

	private tetrisController gameController;
	private GameCanvas gameCanvas;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 480);
		setLayout(new GridLayout(1, 2));

		gameController=new tetrisController();

		gameCanvas=new GameCanvas(gameController);
		gameCanvas.addKeyListener(gameCanvas);
	  

		add(gameCanvas);



		timer = new MyTimer(gameController,gameCanvas);

		timerIns=new Thread(timer);		

		rightScr = new JPanel();
		rightScr.setLayout(new GridLayout(2, 1, 0, 30));
		rightScr.setSize(120, 500);
		add(rightScr);


		JPanel infoScr = new MyPanel();
		infoScr.setLayout(new GridLayout(4, 1, 0, 5));
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
		rightScr.add(controlScr);

		// 定义按钮play
		JButton play_b = new JButton("开始游戏");
		play_b.setSize(new Dimension(50, 200));
		play_b.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				gameController.initScr();
				gameController.Play();				
				scoreField.setText("0");
				timerIns.start();
				gameCanvas.requestFocus();
			}
		});

		// 定义按钮Level UP
		JButton level_up_b = new JButton("提高级数");
		level_up_b.setSize(new Dimension(50, 200));

		// 定义按钮Level Down
		JButton level_down_b = new JButton("降低级数");
		level_down_b.setSize(new Dimension(50, 200));

		// 定义按钮Level Pause
		JButton pause_b = new JButton("游戏暂停");
		pause_b.setSize(new Dimension(50, 200));

		// 定义按钮Quit
		JButton quit_b = new JButton("退出游戏");
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
		controlScr.add(pause_b);
		controlScr.add(quit_b);
		
		  Thread scoreMonitor=new Thread(new Runnable() {
				public void run() {
					while(true){
						scoreField.setText(""+gameController.getScore());
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
			});
		  scoreMonitor.start();
				
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

	GameCanvas(tetrisController gameController) {
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
	}

	public void drawUnit(int row, int col, int type,Graphics g) {
//		g=getGraphics();
		switch (type) { 
		case blankSquares:
			g.setColor(Color.black);
			break; 
		case fallingSquares:			
			g.setColor(Color.blue);
			break;
		case bottomSquares:
			g.setColor(Color.magenta);
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
		System.out.println("LEFTKEY==================================================");
		if (!gameController.isPlaying())
			return;
		switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN:
			gameController.fallDown();		
			repaint();
			break;
		case KeyEvent.VK_LEFT:
			System.out.println("LEFTKEY==================================================");
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
			if(gameController.isGameEnd()){
				break;
			}
			gameCanvas.requestFocus();
		}
	}
}