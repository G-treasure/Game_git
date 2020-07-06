package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOfLife extends JFrame {
	public final Pool world;
	public GameOfLife(int rows, int columns) {
		world = new Pool(rows, columns);
		world.setBackground(Color.LIGHT_GRAY);
		new Thread(world).start();
		add(world);
	}
//	==================================================================================
//	==================================================================================
	public static void main(String[]args) {

		GameOfLife frame = new GameOfLife(75, 75);
		
		JMenuBar menu = new JMenuBar();
		frame.setJMenuBar(menu);
		
//		���
		JMenu options = new JMenu("ѡ��");
		menu.add(options);
		JMenu changeSpeed = new JMenu("�ٶ�");
		menu.add(changeSpeed);
		
		JMenuItem start=options.add("��ʼ�ݻ�");
		start.addActionListener(frame.new StartActionListener());

		JMenuItem init = options.add("��ʼ̬1");
		init.addActionListener(frame.new initActionListener());

		JMenuItem init_2 = options.add("��ʼ̬2");
		init_2.addActionListener(frame.new initActionListener_2());

		JMenuItem random=options.add("����ֲ�");
		random.addActionListener(frame.new RandomActionListener());
		
		JMenuItem stop=options.add("ֹͣ����");
		stop.addActionListener(frame.new StopActionListener());



		JMenuItem slow=changeSpeed.add("��");
		slow.addActionListener(frame.new SlowActionListener());

		JMenuItem fast=changeSpeed.add("��");
		fast.addActionListener(frame.new FastActionListener());

		JMenuItem hyper=changeSpeed.add("�൱��");
		hyper.addActionListener(frame.new hyperActionListener());
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,600);
		frame.setTitle("������Ϸ");
		frame.setVisible(true);
		frame.setResizable(true);
	}

	class RandomActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			world.setBackground(Color.LIGHT_GRAY);
			world.setRandom();
		}
	}
	class StartActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			world.setBackground(Color.LIGHT_GRAY);
			world.setShape();
		}
	}
	class StopActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			world.setBackground(Color.LIGHT_GRAY);
			world.setStop();
		}
	}

	class SlowActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			world.changeSpeedSlow();
		}
	}
	class FastActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			world.changeSpeedFast();
		}
	}
	class hyperActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			world.changeSpeedHyper();
		}
	}
	

	private class initActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			world.setBackground(Color.LIGHT_GRAY);
			world.setInit_1();
		}
	}
	private class initActionListener_2 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			world.setBackground(Color.LIGHT_GRAY);
			world.setInit_2();
		}
	}

	
}

