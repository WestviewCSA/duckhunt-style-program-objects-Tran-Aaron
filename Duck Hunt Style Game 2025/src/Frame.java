import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Toolkit;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	//frame size
	private int screenWidth = 1080, screenHeight = 1080;
	private String title = "Big Bagel Hunt";
	
	
	
	/**
	 * Declare and instantiate (create) your objects here
	 */
	private Duck duckObject = new Duck();
	private Background myBackground = new Background();
	private Background2 mySecondBackground = new Background2();
	private Bush myBush = new Bush();
	private Bush2 mySecondBush = new Bush2();
	private Bush3 myThirdBush = new Bush3();
	private RightHanded myRightHand = new RightHanded();
	private LeftHanded myLeftHand = new LeftHanded();
	private Tree myTree = new Tree();
	private Dirt myDirt = new Dirt();
	private HUD bigHUD = new HUD();
	
	Music mouseClickSound = new Music("sfx_wpn_laser9.wav", false);
	//private MyCursor myCursor = new MyCursor();
	
	public void paint(Graphics pen) {
		
		//this line of code is to force redraw the entire frame
		super.paintComponent(pen);
		
		
		//background should be drawn before objects
		// or based on layer
		myBackground.paint(pen);
		mySecondBackground.paint(pen);
		
		myTree.paint(pen);
		duckObject.paint(pen);
		myRightHand.paint(pen);
		myLeftHand.paint(pen);
		//myBush.paint(pen);
		myDirt.paint(pen);
		
		mySecondBush.paint(pen);
		myThirdBush.paint(pen);
		
		bigHUD.paint(pen);
		
		//myCursor.paint(pen);
		
		//call paint for the object
		//for objects, you call methods on them using the dot operator
		//methods use always involve parenthesis
		
		
		
		
	}
	
	
	@Override
	public void mouseClicked(MouseEvent mouse) {
	    // Runs when the mouse is clicked (pressed and released quickly).
	    // Example: You could use this to open a menu or select an object.
	}

	@Override
	public void mouseEntered(MouseEvent mouse) {
	    // Runs when the mouse enters the area of a component (like a button).
	    // Example: You could highlight the button when the mouse hovers over it.
	}

	@Override
	public void mouseExited(MouseEvent mouse) {
	    // Runs when the mouse leaves the area of a component.
	    // Example: You could remove the highlight when the mouse moves away.
	}

	@Override
	public void mousePressed(MouseEvent mouse) {
	    // Runs when a mouse button is pressed down.
	    // Example: You could start dragging an object here.
		System.out.println(mouse.getX()+":"+mouse.getY());
		duckObject.checkCollision(mouse.getX(), mouse.getY());
		
		this.mouseClickSound.play();
	}

	@Override
	public void mouseReleased(MouseEvent mouse) {
	    // Runs when a mouse button is released.
	    // Example: You could stop dragging the object or drop it in place.
	}



	/*
	 * This method runs automatically when a key is pressed down
	 */
	public void keyPressed(KeyEvent key) {
		
		System.out.println("from keyPressed method:"+key.getKeyCode());
	}

	/*
	 * This method runs when a keyboard key is released from a pressed state
	 * aka when you stopped pressing it
	 */
	public void keyReleased(KeyEvent key) {
		
	}

	/**
	 * Runs when a keyboard key is pressed then released
	 */
	public void keyTyped(KeyEvent key) {
		
		
	}
	
	
	/**
	 * The Timer animation calls this method below which calls for a repaint of the JFrame.
	 * Allows for our animation since any changes to states/variables will be reflected
	 * on the screen if those variables are being used for any drawing on the screen.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}
	
	/*
	 * Main method to create a Frame (the GUI that you see)
	 */
	public static void main(String[] arg) {
		new Frame();
	}
	
	
	
	public Frame() {
		JFrame f = new JFrame(title);
		f.setSize(new Dimension(screenWidth, screenHeight));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("cursor.png");
		Cursor a = toolkit.createCustomCursor(image,new Point(this.getX(),this.getY()), "");
		this.setCursor (a);
	}

}
