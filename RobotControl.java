import java.awt.Frame;
import java.awt.Label;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.media.MediaLocator;
import javax.media.Player;
import javax.swing.JFrame;
import javax.swing.JTextField;

import net.sf.fmj.ejmf.toolkit.util.*;

public class RobotControl {
	public static void computeMotorArrays(short[] L, short[] R, short[] S, short[] E, short[] C, 
			int[] keyArray) {
		L[0] = 0;
		R[0] = 0;
		S[0] = 0;
		E[0] = 0;
		C[0] = 0;
		if (keyArray[0] == 1) {
			L[0] += 1;
			R[0] += 1;
		}
		if (keyArray[1] == 1) {
			L[0] -= 1;
			R[0] -= 1;
		}
		if (keyArray[2] == 1) {
			L[0] -= 1;
			R[0] += 1;
		}
		if (keyArray[3] == 1) {
			L[0] += 1;
			R[0] -= 1;
		}
		if (keyArray[4] == 1) {
			S[0] += 1;
		}
		if (keyArray[5] == 1) {
			S[0] -= 1;
		}
		if (keyArray[6] == 1) {
			E[0] += 1;
		}
		if (keyArray[7] == 1) {
			E[0] -= 1;
		}
		if (keyArray[8] == 1) {
			C[0] += 1;
		}
		if (keyArray[9] == 1) {
			C[0] -= 1;
		}
		if (L[0] > 1)
			L[0] = 1;
		if (L[0] < -1)
			L[0] = -1;
		if (R[0] > 1)
			R[0] = 1;
		if (R[0] < -1)
			R[0] = -1;
	}
	
	public static String OutputMotorStates(short[] L, short[] R, short[] S, short[] E, short[] C) {
		String out = "";
		out += (char)((int)L[0]);
		out += (char)((int)L[1]);
		out += (char)((int)R[0]);
		out += (char)((int)R[1]);
		/*out += (char)((int)S[0]);
		out += (char)((int)S[1]);
		out += (char)((int)E[0]);
		out += (char)((int)E[1]);
		out += (char)((int)C[0]);
		out += (char)((int)C[1]);*/
		return out;
	}
	
	public static void main(String[] args) throws Exception {
		/*
		Socket motorSocket = null;
		try {
			motorSocket = new Socket("10.47.150.128", 12345);
		}
		catch (UnknownHostException e) {
			System.out.println("Unknown host.");
			System.exit(1);
		}
		catch (IOException e) {
			System.out.println("No I/O");
			System.exit(1);
		}
		
		final PrintWriter outToServer = new PrintWriter(motorSocket.getOutputStream(), true);
		*/
		
		
		Frame aWindow = new Frame();
		String path = "Insert URL here";
		PlayerPanel p = new PlayerPanel(new MediaLocator(path));
		Player player = p.getPlayer();
		Label l = new Label("Insert title here");

		//  Configure display
		p.addControlComponent();
		p.addVisualComponent();
		aWindow.add(p);
		aWindow.add(l);


		//  Display components
		aWindow.pack();
		aWindow.setVisible(true);


		//  Start media play
		player.start();
		
		// dummy frame for testing control without video
		// JFrame aWindow = new JFrame("Robot Control");
		// aWindow.setBounds(50, 50, 640, 480);
		final int keyArray[] = new int [10]; // up, down, left, right, 2 shoulder, 2 elbow, 2 claw
		final short[] L = new short[2]; // left wheel motor
		L[0] = 0;
		L[1] = 255;
		final short[] R = new short[2]; // right wheel motor
		R[0] = 0;
		R[1] = 255;
		final short[] S = new short[2]; // shoulder motor
		S[0] = 0;
		S[1] = 255;
		final short[] E = new short[2]; // elbow motor
		E[0] = 0;
		E[1] = 255;
		final short[] C = new short[2]; // claw motor
		C[0] = 0;
		C[1] = 255;
		aWindow.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				// nothing
			}
			public void keyPressed(KeyEvent e) {
				char charPressed = e.getKeyChar();
				if (charPressed == 'w') {
					keyArray[0] = 1;
				}
				else if (charPressed == 's') {
					keyArray[1] = 1;
				}
				else if (charPressed == 'a') {
					keyArray[2] = 1;
				}
				else if (charPressed == 'd') {
					keyArray[3] = 1;
				}
				else if (charPressed == 'u') {
					keyArray[4] = 1;
				}
				else if (charPressed == 'j') {
					keyArray[5] = 1;
				}
				else if (charPressed == 'i') {
					keyArray[6] = 1;
				}
				else if (charPressed == 'k') {
					keyArray[7] = 1;
				}
				else if (charPressed == 'o') {
					keyArray[8] = 1;
				}
				else if (charPressed == 'l') {
					keyArray[9] = 1;
				}
				computeMotorArrays(L, R, S, E, C, keyArray);
				/*outToServer.print(motors.OutputMotorStates());
				outToServer.flush();*/
				System.out.println(OutputMotorStates(L, R, S, E, C));
			}
			public void keyReleased(KeyEvent e) {
				char charReleased = e.getKeyChar();
				if (charReleased == 'w') {
					keyArray[0] = 0;
				}
				else if (charReleased == 's') {
					keyArray[1] = 0;
				}
				else if (charReleased == 'a') {
					keyArray[2] = 0;
				}
				else if (charReleased == 'd') {
					keyArray[3] = 0;
				}
				else if (charReleased == 'u') {
					keyArray[4] = 0;
				}
				else if (charReleased == 'j') {
					keyArray[5] = 0;
				}
				else if (charReleased == 'i') {
					keyArray[6] = 0;
				}
				else if (charReleased == 'k') {
					keyArray[7] = 0;
				}
				else if (charReleased == 'o') {
					keyArray[8] = 0;
				}
				else if (charReleased == 'l') {
					keyArray[9] = 0;
				}
				computeMotorArrays(L, R, S, E, C, keyArray);
				/*outToServer.print(motors.OutputMotorStates());
				outToServer.flush();*/
				System.out.println(OutputMotorStates(L, R, S, E, C));
			}
		});
		aWindow.setVisible(true);
	}
}
