package Objects;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player extends Vehicle
{
    public MyPoint point;
    public PlayerBullet playerBullet;
    public ArrayList<PlayerBullet> playerBullets = new ArrayList<PlayerBullet>();
    public int speed =2;
    static private Player instance;
    private int bulletsCreated =0;

    private int bulletSpeed = 4;


    private Player(String path, int x, int y)
    {
        super(path, x, y);
    }
    public static Player getInstance() {
        if (instance == null){
            instance = new Player("src/Sprites/BF-109E/Type-1/Animation/1.png", 0, 0);
        }
        return instance;
    }

    @Override
    public boolean isBomber(int ID) {
        return false;
    }

    @Override
    public boolean isBullet() {
        return false;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isVisible(){
        return true;
    }
    public void setVisible(boolean visible){

    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x-w/2, y-w/2, w, h);
    }

    public Player(String path, MyPoint p)
	{
		super(path, p);
	}
	
	public void move()
	{
		this.x += dx;
		this.y += dy;
	}

    public void dodgeLeft(){

    }
    public void dodgeRight(){

    }
	public void keyPressed(KeyEvent e) 
	{
        int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                if (this.x-getWidth()/2<=0){
                    this.x=getWidth()/2;
                }
                dx = -speed;
            }

            if (key == KeyEvent.VK_RIGHT) {
                if (this.x+getWidth()/2>=boardWidth){
                    this.x=boardWidth-getWidth()/2;
                }
                dx = speed;
            }

            if (key == KeyEvent.VK_UP) {
                if (this.y-getHeight()/2<=0){
                    this.y=getHeight()/2;
                }
                dy = -speed;
            }

            if (key == KeyEvent.VK_DOWN) {
                if (this.y+getHeight()/2>=boardHeight){
                    this.y=boardHeight-getHeight()/2;
                }
                dy = speed;
            }

        if (key == KeyEvent.VK_SPACE) {
            Fire();
        }
    }
	public void Fire()
	{
        point = new MyPoint(this.x,this.y );
        playerBullet = FactoryCreator.getProjectileFactory().getPlayerBullet("src/Sprites/PlayerFire/1.png", point, bulletSpeed);
        playerBullets.add(playerBullet);
        playerBullet.setBulletID(bulletsCreated);
        bulletsCreated++;

	}

    public void keyReleased(KeyEvent e) 
    {        
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}
