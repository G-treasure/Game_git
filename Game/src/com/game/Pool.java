package com.game;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;


public class Pool extends JPanel implements Runnable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//  ����״̬����
	public static final int DEAD = 0;
	public static final int ALIVE  = 1 ;
    // �����ĳ������
	public static int rows;
	public static int columns;
	public static int speed = 8;
	public static int[][] shape ;
	public static int[][] zero ;
	public static int[][] pauseshape ;

	public static int[][] currentGeneration;
	public static int[][] nextGeneration;
	// ���ı�־
	private  boolean isChanging = false;
	// ���췽����ʵ���ǳ�ʼ����������
	public Pool(int rows, int columns) {
		this.rows = rows;
		this.columns=columns;

		shape = new int[rows][columns] ;
		zero = new int[rows][columns] ;                //��̬����ռ�
		pauseshape = new int[rows][columns] ;

		int[][] generation1 = new int[rows][columns];
		for(int i=0;i<rows;i++) {
			for(int j=0;j<columns;j++) {              //�ѳ�ʼ����һ��Ϊ0
				generation1[i][j] = DEAD;
			}
		}
		int[][] generation2 = new int[rows][columns];
		for(int i=0;i<rows;i++) {
			for(int j=0;j<columns;j++) {               //�ѳ�ʼ���ڶ���Ϊ1
				generation2[i][j]= ALIVE;
			}
		}
		currentGeneration = generation1;              //
		nextGeneration = generation2;
	}

	//����ͣ����pauseshape����generation
	public static void transfrom(int[][] generation, int[][] pauseshape) {
		for(int i = 0;i < rows;i++) {
			if (columns >= 0) {
				System.arraycopy(generation[i], 0, pauseshape[i], 0, columns);
			}
		}
	}
	
	public void run() {
		long start = System.currentTimeMillis();
    //�涨����ݻ�ʱ��Ϊ10����
		while ((System.currentTimeMillis() - start) <= 10 * 60 * 1000) {
			synchronized (this) {
				while (isChanging) {
					try {
						this.wait();//�̵߳ȴ�
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				sleep(speed);//�̹߳���
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < columns; j++) {
						evolve(i, j);//������һ��ϸ��������״̬
					}
				}
				int[][] temp = currentGeneration;
				currentGeneration = nextGeneration;
				nextGeneration = temp;
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < columns; j++) {
						nextGeneration[i][j] = DEAD;
					}
				}
				transfrom(currentGeneration, pauseshape);
				repaint();//���»������
			}
		}
	}


	public static void changeSpeedSlow() {
		speed=80;
	}
	public static void changeSpeedFast() {
		speed=30;
	}
	public static void changeSpeedHyper() {
		speed=1;
	}
//	������
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i=0;i<rows;i++) {
			for(int j=0;j<columns;j++) {
				if(currentGeneration[i][j]== ALIVE) {
					g.fillRect(j*10, i*10, 10, 10);//����Ĵ�С
				} else {
					g.drawRect(j*10, i*10, 10, 10);//����Ĵ�С
				}
			}
		}
	}
	
	public  void setShape() {
		setShape(shape);
	}

	
	//	����ֲ�
	
	public void setRandom() {
		Random r = new Random();
		setShapetemp(shape);   //����α����ģ����ȷֲ� intֵ����0��������ָ��ֵ�������������Ӹ�����������������л��ơ�
		for(int i=0;i < rows; i++) {
			for(int j=0; j< columns; j++) {
				if(r.nextInt() %2 == 0) {
					shape[i][j] = 0;
				}else {
					shape[i][j] = 1;
				}
			}
		}
	}
	
	
	// ��ʼ״̬2
	public  void setInit_2(){
		shape[1][25] = pauseshape[1][25] = ALIVE;
		shape[2][25] = pauseshape[2][25] = ALIVE;
		shape[2][23] = pauseshape[2][23] = ALIVE;
		shape[3][13] = pauseshape[3][13] = ALIVE;
		shape[3][14] = pauseshape[3][14] = ALIVE;
		shape[3][21] = pauseshape[3][21] = ALIVE;
		shape[3][22] = pauseshape[3][22] = ALIVE;
		shape[3][35] = pauseshape[3][35] = ALIVE;
		shape[3][36] = pauseshape[3][36] = ALIVE;
		shape[4][12] = pauseshape[4][12] = ALIVE;
		shape[4][16] = pauseshape[4][16] = ALIVE;
		shape[4][21] = pauseshape[4][21] = ALIVE;
		shape[4][22] = pauseshape[4][22] = ALIVE;
		shape[4][35] = pauseshape[4][35] = ALIVE;
		shape[4][36] = pauseshape[4][36] = ALIVE;
		shape[5][1 ] = pauseshape[5][1 ] = ALIVE;
		shape[5][2 ] = pauseshape[5][2 ] = ALIVE;
		shape[5][11] = pauseshape[5][11] = ALIVE;
		shape[5][17] = pauseshape[5][17] = ALIVE;
		shape[5][21] = pauseshape[5][21] = ALIVE;
		shape[5][22] = pauseshape[5][22] = ALIVE;
		shape[6][1 ] = pauseshape[6][1 ] = ALIVE;
		shape[6][2 ] = pauseshape[6][2 ] = ALIVE;
		shape[6][11] = pauseshape[6][11] = ALIVE;
		shape[6][15] = pauseshape[6][15] = ALIVE;
		shape[6][17] = pauseshape[6][17] = ALIVE;
		shape[6][18] = pauseshape[6][18] = ALIVE;
		shape[6][23] = pauseshape[6][23] = ALIVE;
		shape[6][25] = pauseshape[6][25] = ALIVE;
		shape[7][11] = pauseshape[7][11] = ALIVE;
		shape[7][17] = pauseshape[7][17] = ALIVE;
		shape[7][25] = pauseshape[7][25] = ALIVE;
		shape[8][12] = pauseshape[8][12] = ALIVE;
		shape[8][16] = pauseshape[8][16] = ALIVE;
		shape[9][13] = pauseshape[9][13] = ALIVE;
		shape[9][14] = pauseshape[9][14] = ALIVE;
		setShapetemp(shape);
	}
	
    //��ʼ״̬1
	public void setInit_1(){
		for(int i = 0;i < rows; i++) {
			for(int j = 0;j < columns;j++) {
				if((i == 4 && j == 4)||(i == 5 && j == 5)||
					(i == 5 && j == 6)||(i == 6 && j == 4)||
					(i == 6 && j == 5)){
				    shape[i][j] = ALIVE;
				}
				else {
					shape[i][j] = DEAD;
				}
				pauseshape[i][j] = shape[i][j] ;
			}
		}
		setShapetemp(shape);
	}
//	��ʼ������Ϊzero[] = {0,0,0,...,0}��
// ****************************************************

	public static void setZero() {
	for(int i = 0;i < rows;i++) {
		for(int j = 0; j < columns; j++) {
			zero[i][j] = DEAD;
		}
	}


	}
// ****************************************************
	/*ֹͣ���ָ���ʼ״̬*/
	public void setStop() {
		setZero();
		shape = zero;
		setShape(shape);
		pauseshape = shape;
	}
	
	/******����*******/
	public  void setShapetemp(int [][]shape) {
		isChanging = true;
		synchronized (this) {
			for (int i = 0 ; i < rows ; i ++ ) {
				for (int j = 0 ; j < columns ; j ++ ) {
					currentGeneration[i][j] = (shape[i][j] == ALIVE) ? ALIVE : DEAD;
				}
			}
			repaint();
		}
	}
	
	public  void setShape(int [][]shape) {
		isChanging = true;
		synchronized(this) {
			for (int i = 0 ; i < rows ; i ++ ) {
				for (int j = 0 ; j < columns ; j ++ ) {
					currentGeneration[i][j] = (shape[i][j] == ALIVE) ? ALIVE : DEAD;
				}
			}
			isChanging=false;
			this.notifyAll();
		}
	}
	
// ************************************************************************

	public static void evolve(int x,int y) {
		int count = 0 ;
		if(isVaildCell(x-1,y-1)&&currentGeneration[x-1][y-1]==1) {
			count++;
		}
		if(isVaildCell(x,y-1)&&currentGeneration[x][y-1]==1) {
			count++;
		}
		if(isVaildCell(x+1,y-1)&&currentGeneration[x+1][y-1]==1) {
			count++;
		}
		if(isVaildCell(x-1,y)&&currentGeneration[x-1][y]==1) {
			count++;
		}
		if(isVaildCell(x+1,y)&&currentGeneration[x+1][y]==1) {
			count++;
		}
		if(isVaildCell(x-1,y+1)&&currentGeneration[x-1][y+1]==1) {
			count++;
		}
		if(isVaildCell(x,y+1)&&currentGeneration[x][y+1]==1) {
			count++;
		}
		if(isVaildCell(x+1,y+1)&&currentGeneration[x+1][y+1]==1) {
			count++;
		}
		//����Χ��3������ʱ����һ��Ϊ��������Χ��2����ʱ�����䣬���඼Ϊ��
	    if(count == 3) {
			nextGeneration[x][y] = ALIVE;
		}
	    else if(count == 2) {
			nextGeneration[x][y] = currentGeneration[x][y];
		}
		else {
			nextGeneration[x][y] = DEAD;
		}
		

	}
// *********************************************************************** 
/**
 * �жϸ�ϸ���ǲ��ǲ��ٵ�ͼ��
 * @param x
 * @param y
 * @return flag
 */
	public static boolean isVaildCell(int x,int y) {
		return (x >= 0) && (x < rows) && (y >= 0) && (y < columns);
	}

	public void sleep(int x) {
		try {
			Thread.sleep(8*x);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
	}

	public static int getSpeed() {
		
		return speed;
	}
}



