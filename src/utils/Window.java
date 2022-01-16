package utils;

import java.awt.Canvas;
import javax.swing.JFrame;

import main.Game;

import java.awt.Dimension;

// Creates game screen
public class Window extends Canvas{	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4185793785502082319L;

	public Window(String title, int width, int height, Game game) {
	
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.add(game);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
}
