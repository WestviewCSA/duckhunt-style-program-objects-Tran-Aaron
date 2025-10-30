import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.awt.Color;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Font;


// The Duck class represents a picture of a duck that can be drawn on the screen.
public class Duck {
    // Instance variables (data that belongs to each Duck object)
    private Image img;               // Stores the picture of the duck
    
    private Image normal;
    private Image Ascension;
    
    
    
    private AffineTransform tx;      // Used to move (translate) and resize (scale) the image

    // Variables to control the size (scale) of the duck image
    private double scaleX;           
    private double scaleY;           

    // Variables to control the location (x and y position) of the duck
    private double x;                
    private double y;    
    private double yStart;
    
    //waves difficulty
    private int waveScore;
    
    // new X
    public static double ascendX = 0.0;
    public static double ascendY = 0.0;
    
    //variables for speed
    private int vx;
    private int vy;
    
    //debug var
    public boolean debugging = false;
    public int bxllxts;
    
    //big ascension
    public boolean hit;
    private int womboCombo = 0;
    public String comboString = "0";
    private int score = 0;
    
    // Constructor: runs when you make a new Duck object
    public Duck() {
        normal = getImage("/imgs/bagel.png"); // Load the image file
        Ascension = getImage("/imgs/ascendedBagel.png");
        
        img = normal;
        tx = AffineTransform.getTranslateInstance(0, 0); // Start with image at (0,0)
        
        // Default values
        scaleX = 1.0;
        scaleY = 1.0;
        x = 2000;
        y = -100;

        	
        
        
        init(x, y); // Set up the starting location and size
        vx = 5;
        vy = 0;
    }
    
    //2nd constructor to initialize location and scale!
    public Duck(int x, int y, int scaleX, int scaleY) {
    	this();
    	this.x 		= x;
    	this.y 		= y;
    	this.scaleX = scaleX;
    	this.scaleY = scaleY;
    	init(x,y);
    }
    
    //2nd constructor to initialize location and scale!
    public Duck(int x, int y, int scaleX, int scaleY, int vx, int vy) {
    	this();
    	this.x 		= x;
    	this.y 		= y;
    	this.scaleX = scaleX;
    	this.scaleY = scaleY;
    	this.vx 	= vx; 
    	this.vy 	= vy;
    	init(x,y);
    }
    
    public void setVelocityVariables(int vx, int vy) {
    	this.vx = vx;
    	this.vy = vy;
    }
    
    
    // Changes the picture to a new image file
    public void changePicture(String imageFileName) {
        img = getImage("/imgs/"+imageFileName);
        init(x, y); // keep same location when changing image
    }
    
    //update any variables for the object such as x, y, vx, vy
    public void update() {
    	if (vy != 9) {
    	y = Math.abs(200*Math.cos((waveScore*x-yStart)*3.1415926535859/500) + 500);
    		if (x >= 600) {
    			x +=10;
    		}
    		if (x < 600) {
    			x -=10;
    		}
    	}
    	else {
    		y = y - vy;
    	}
    	if (y<-50) {
    	x = Math.random() * 200 + 500;
        yStart = x;	
    	hit = false;
    	vy = 0;
    	img = normal;
    	comboString = "" + womboCombo;
    	}
    	if (x > 1080 | x < -20) {
    	x = Math.random() * 270 + 270;
    	yStart = x;	
    	comboString = "0";
    	womboCombo = 0;
    	}
    	waveScore = (int)score/300 + 1;
    	//System.out.println(bxllxts);
    	
    }
 
    
    // Draws the duck on the screen
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;   // Graphics2D lets us draw images
        g2.drawImage(img, tx, null);      // Actually draw the duck image
        update();
        init(x,y);
        if (debugging == true) {
        g.setColor(Color.green);
        g.drawRect((int) x, (int) y, 100, 100);
        }
       // if (hit == true) {
        //	System.out.println("aaaaaaaaaaaaaaaaa");
        //}
        g.setColor(Color.red);
        g.setFont(new Font("Verdana", Font.PLAIN, 50));
        g2.drawString("x" + comboString, 120, 550);
        g2.drawString("" + score, 250, 550);
        g2.drawString("Wave: " + waveScore, 570, 125);
        g.setColor(Color.black);
    }
    
    // Setup method: places the duck at (a, b) and scales it
    private void init(double a, double b) {
        tx.setToTranslation(a, b);        // Move the image to position (a, b)
        tx.scale(scaleX, scaleY);         // Resize the image using the scale variables
    }

    // Loads an image from the given file path
    private Image getImage(String path) {
        Image tempImage = null;
        try {
            URL imageURL = Duck.class.getResource(path);
            tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempImage;
    }

    // NEW: Method to set scale
    public void setScale(double sx, double sy) {
        scaleX = sx;
        scaleY = sy;
        init(x, y);  // Keep current location
    }

    // NEW: Method to set location
    public void setLocation(double newX, double newY) {
        x = newX;
        y = newY;
        init(x, y);  // Keep current scale
    }
    
    public boolean checkCollision(int mX, int mY) {
    	Rectangle mouse = new Rectangle(mX, mY, 50, 50);
    	
    	Rectangle thisObject = new Rectangle((int) x,(int) y, 100, 100);
    	
    	if(mouse.intersects(thisObject) && hit == false) {
    		hit = true;
    		vx = 0;
    		vy = 9;
    		img = Ascension;
    	//	bxllxts -= 1;
    		womboCombo += 1;
    		score = womboCombo * 15 + score;
    		ascendX = x;
    		ascendY = y;
    		RightHanded.spawnHands();
    		LeftHanded.spawnHands();
    	return true;	
    	}
    	else { 
    //		bxllxts -= 1;
    		return false;
    	}
    }
    
}
