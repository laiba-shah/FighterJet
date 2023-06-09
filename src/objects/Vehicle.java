package Objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public abstract class Vehicle 
{
	protected Image image;
	protected int x,y,w,h,dx,dy;
	protected String path;
	protected ImageIcon imageIcon;
	private int pictureChange;
	private int noOfImg = 3;
	public int boardWidth;
	public int boardHeight;

	public void setBoardWidth(int boardWidth) {
		this.boardWidth = boardWidth;
	}

	public void setBoardHeight(int boardHeight) {
		this.boardHeight = boardHeight;
	}
	public void setImage(String path) {

		pictureChange++;

		if (pictureChange >noOfImg){
			pictureChange =1;
		}
		imageIcon = new ImageIcon(path + pictureChange + ".png");
		image = imageIcon.getImage();

	}
	public void setImage(String path, int ID) {
		if (!this.isBullet() && !this.isVisible() && ID!=0){
			noOfImg = 4;
			System.out.println("alijone " + ID);
		}else{
			noOfImg = 3;
			System.out.println("not alijone " + ID);
		}
		pictureChange++;

		if (pictureChange >noOfImg){
			pictureChange =1;
		}
		imageIcon = new ImageIcon(path + pictureChange + ".png");
		image = imageIcon.getImage();

	}

	public void setImage(String path, boolean Bomber) {

		if (Bomber == true){
			imageIcon = new ImageIcon(path);
			image = imageIcon.getImage();
		}


	}

	
	public Vehicle(String path, int x, int y)
	{
		this.path = path;
		this.x = x;
		this.y = y;

		imageIcon = new ImageIcon(this.path);

		image = imageIcon.getImage();
		w = image.getWidth(null);
        h = image.getHeight(null);
	}


	public Vehicle(String path, MyPoint p)
	{
		this.path = path;
		x = p.x;
		y = p.y;
		ImageIcon imageIcon = new ImageIcon(this.path);
		image = imageIcon.getImage();

		
		w = image.getWidth(null);
        h = image.getHeight(null);
	}

	
	public void paintComponent(Graphics2D g) 
	{


		g.setColor(Color.BLACK);
		g.drawRect(x-w/2, y-w/2, w, h);//Only to show image bounds, can be removed

		g.drawImage(image, x - image.getWidth(null)/2, y - image.getHeight(null)/2, null);

	}
	public void makeBackground(Graphics2D g)
	{

		g.setColor(Color.WHITE);
		g.fillRect(x-w/2, y-w/2, w+2, h+3);


	}
	
	public void moveTo(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public abstract void move();
	public abstract void Fire();
	public abstract Rectangle getBounds();
	public abstract boolean isVisible();
	public abstract void setVisible(boolean visible);
	public abstract void dodgeLeft();
	public abstract void dodgeRight();
	public abstract boolean isBomber(int ID);
	public abstract boolean isBullet();

	
	public int getX() {
        
        return x;
    }

    public int getY() {
        
        return y;
    }
    
    public int getWidth() {
        
        return w;
    }
    
    public int getHeight() {
        
        return h;
    }    

    public Image getImage() {
        
        return image;
    }

	
	
}
