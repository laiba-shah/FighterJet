/* uncomment this to draw stuff
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
*/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

import Objects.*;
;

public class Board extends JPanel implements ActionListener
{
	/**
	 *author: Ali Jone
     * erp: 23031
	 */
	private static final long serialVersionUID = 1L;

	private final int DELAY = 10;
    
	private Player player;
	private boolean isGameOver = false;
	private EnemyPlayer enemyPlane;
	private int enemyPlayerSpawned = 0;
	private int w = 1024;
	private int h = 768;
    private int counter = 0;
	private int count = 0;
	private ArrayList<EnemyPlayer> enemyPlanes = new ArrayList<EnemyPlayer>();
	private int fireTime;
	private int enemyPlayerSpawn =275;
	private int enemyPlayerSpeed = 2;
	private int playerSpeed = 10;
	boolean condition = true;
	private int rndmInterval = 10;
	private int fontSize = 18;
	private int roundScore;
	private int totalScore;
	private int level;
	private int removedEnemyCondition = 0;
    private int removedPlayerBulletCondition = 0;
    private int removedEnemyPlayerBulletCondition = 0;
    private int removedEnemyPlayer;
    private int removedPlayerBullet;
    private int removedEnemyPlayerBullet;
    private Rectangle r1;
    private Rectangle r2;
	private Timer timer;
	private EnemyPlayer enemy;
	private PlayerBullet playerBullet;
	private EnemyBullet enemyBullet;

    public Board() 
    {    	
        initBoard();
    }
    
    private void initBoard() //Initializes all the game objects
    {	
    	addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);
        player = Player.getInstance();
        player.setSpeed(playerSpeed);

        setPreferredSize(new Dimension((int)w, (int)h));   //Set the size of Window

        player.moveTo((int)w/2, (int)h *3/4);
        timer = new Timer(DELAY, this); //Timer with 10 ms delay 
        timer.start();
    }
    
    
    @Override
    public void paintComponent(Graphics g) //Draws all the components on screen
    {
        if (!isGameOver){
            g.setColor(getBackground());		//get the background color
            g.clearRect(0 , 0, (int)w, (int)h);	//clear the entire window
            Dimension size = getSize();  //get the current window size
            w = (int)size.getWidth();
            h = (int)size.getHeight();
            player.setBoardHeight(h);
            player.setBoardWidth(w);
            Graphics2D g2d = (Graphics2D) g;



            //move the objects
            player.move();
            for (int i =0; i<enemyPlanes.size(); i++){
                enemy = enemyPlanes.get(i);
                enemy.move();
            }
            for (int i = 0; i<player.playerBullets.size(); i++){
                playerBullet = player.playerBullets.get(i);
                playerBullet.move();
            }
            if (enemyPlane != null){
                for (int i = 0; i<enemyPlane.enemyBullets.size(); i++){
                    enemyBullet = enemyPlane.enemyBullets.get(i);
                    enemyBullet.move();
                }
            }



            //check if player and bullet collide
            checkCollisions();

            // remove when reach boundaries
            removeOutOfBound();




            //paint the objects
            if (enemyPlane != null ){
                for (int i = 0; i<enemyPlane.enemyBullets.size(); i++){
                    enemyBullet = enemyPlane.enemyBullets.get(i);
                    if (enemyBullet.getBulletID() == 0 ){
                        enemyBullet.makeBackground(g2d);
                    }
//            else if (enemy.getEnemyPlayerID() == 0 && temp.data.isBullet() && !temp.data.isVisible()){
//                temp.data.makeBackground(g);
//            }
                    else{
                        enemyBullet.paintComponent(g2d);
                    }
                }

            }

            for (int i = 0; i<enemyPlanes.size();i++){
                enemy = enemyPlanes.get(i);
                if (enemy.getEnemyPlayerID() == 0  ){
                    enemy.makeBackground(g2d);
                }
//            else if (enemy.getEnemyPlayerID() == 0 && temp.data.isBullet() && !temp.data.isVisible()){
//                temp.data.makeBackground(g);
//            }
                else{
                    enemy.paintComponent(g2d);
                }
            }

            for (int i = 0; i<player.playerBullets.size(); i++){
                playerBullet = player.playerBullets.get(i);
                if (playerBullet.getBulletID() == 0 ){
                    playerBullet.makeBackground(g2d);
                }
//            else if (enemy.getEnemyPlayerID() == 0 && temp.data.isBullet() && !temp.data.isVisible()){
//                temp.data.makeBackground(g);
//            }
                else{
                    playerBullet.paintComponent(g2d);
                }
            }

            player.paintComponent(g2d);



            //text write
            writeText(g2d);




            Toolkit.getDefaultToolkit().sync();
        }else{
            g.setColor(getBackground());		//get the background color
            g.clearRect(0 , 0, (int)w, (int)h);	//clear the entire window
            Dimension size = getSize();  //get the current window size
            w = (int)size.getWidth();
            h = (int)size.getHeight();

            Graphics2D g2d = (Graphics2D) g;
            Font font = new Font("Serif", Font.BOLD, fontSize+60);

            g2d.setFont(font);
            g2d.drawString("G A M E  O V E R " , 0, h/2);
            Toolkit.getDefaultToolkit().sync();
        }

    }
    
    
    public void actionPerformed(ActionEvent e) {
        if (!isGameOver){
            counter++;
            count++;
            fireTime++;
            animate();
            step();
        }else{
            repaint();
        }




    }
    private void animate(){
        if (counter % 4 ==0){
            player.setImage("src/Sprites/BF-109E/Type-1/Animation/");
            for (int i=0; i<enemyPlanes.size(); i++){
                if (enemyPlanes.get(i) != null)
                    enemyPlanes.get(i).setImage("src/Sprites/FighterSprite/");

            }


        }
        bomberSetIcon();


    }
    private void gameOver(){

        isGameOver = true;
    }
    private void step() 
    {
        if (fireTime %rndmInterval == 0){
            for (int i = 0; i<enemyPlanes.size();i++){
                if (enemyPlanes.get(i).getEnemyPlayerID() !=0){
                    enemyPlane = enemyPlanes.get(i);
                    enemyPlane.Fire();
                    rndmInterval = 10 + (int)(Math.random() * ((100 - 10) + 1));
                    fireTime =0;
                }

            }
        }
        if(count == enemyPlayerSpawn){
            enemyPlayerSpawn();
            count = 0;
        }

        setLevel();
        repaint();

    }    
    private void enemyPlayerSpawn(){

        enemyPlane = FactoryCreator.getPlaneFactory().getEnemyPlayer("src/Sprites/FighterSprite/1.png", 0, 0,enemyPlayerSpeed);
        enemyPlane.moveTo((int)(Math.random()*(w - enemyPlane.getWidth()/2 )+enemyPlane.getWidth()/2)  , enemyPlane.getHeight()/2);
        enemyPlanes.add(enemyPlane);
        enemyPlane.setEnemyPlayerID(enemyPlayerSpawned);
        enemyPlayerSpawned++;

    }
    public void bomberSetIcon(){
        for (int i= 0; i<enemyPlanes.size();i++){
             if (enemyPlanes.get(i).isBomber(enemyPlanes.get(i).getEnemyPlayerID()))
                enemyPlanes.get(i).setImage("src/Sprites/B-17/Type-1/Animation/");
        }
    }
    public void checkCollisions() {


        for (int i = 0; i< enemyPlanes.size(); i++){
                if (enemyPlanes.get(i) != null){
                    enemy = enemyPlanes.get(i);
                    if (enemyPlanes.get(i).getEnemyPlayerID() == 0){
                        enemy.setVisible(false);
                    }
                    if (enemyPlanes.get(i) != null){
                        r1  = enemy.getBounds();
                    }

                    for (int j = 0; j< player.playerBullets.size(); j++){
                        if (player.playerBullets.get(j) != null){
                            playerBullet = player.playerBullets.get(j);
                            //System.out.println(i);
                            if (player.playerBullets.get(j).getBulletID() == 0){
                                playerBullet.setVisible(false);
                            }
                            if (player.playerBullets.get(j) !=null){
                                r2 =  playerBullet.getBounds();
                            }

                            if (enemy.getX() - r1.width/2 -10 <= player.playerBullet.getX()&& enemy.getX() + r1.width/2 +10 >= player.playerBullet.getX() && enemy.getY()-r1.height/2-10 <= playerBullet.getY() && enemy.isVisible() && playerBullet.isVisible() && enemy.getY() <= playerBullet.getY()-r2.height && !enemy.isBomber(enemyPlanes.get(i).getEnemyPlayerID())) {

                                if (enemy.getX()>= playerBullet.getX()){
                                    enemy.dodgeRight();

                                }else
                                    enemy.dodgeLeft();
                            }
                            if (r1.intersects(r2) && enemy.isVisible() && playerBullet.isVisible() ){
                                enemy.setVisible(false);
                                System.out.println("visiblity when collide " +enemy.isVisible());
                                playerBullet.setVisible(false);
                                player.playerBullets.remove(player.playerBullets.get(j));
                                enemyPlanes.remove(enemyPlanes.get(i));
                                roundScore++;
                                totalScore++;

                            }
                        }

                    }
                }

        }
        if (enemyPlane != null){
            for (int i=0; i<enemyPlane.enemyBullets.size();i++){
                r1 = enemyPlane.enemyBullets.get(i).getBounds();
                r2 = player.getBounds();
                if (r1.intersects(r2)){
                    gameOver();
                }
            }
        }

    }

    public void setLevel(){


        if (roundScore %15 == 0 && roundScore !=0){
            levelUp();
            level++;
            roundScore = 0;
        }
    }
    public void levelUp(){

            if (enemyPlayerSpawn>=125){
                enemyPlayerSpawn-=25;
            }
            if (player.getSpeed()>=2){
                player.setSpeed(player.getSpeed()-2);
            }
            if (!(enemyPlane.getSpeed()>=10)){
                enemyPlane.setSpeed(player.getSpeed()+2);
            }

    }

    private void writeText(Graphics2D g2d){
        Font font = new Font("Serif", Font.PLAIN, fontSize);

        Font font1 = new Font("Serif", Font.PLAIN, fontSize-5);
        g2d.setFont(font);
        g2d.drawString("Level: " + level , 10, 20);
        g2d.drawString( "As you level up:", 10,20+5+fontSize );
        g2d.drawString( "1. More enemies spawn", 10,40+5+fontSize );
        g2d.drawString( "2. You get slower", 10,60+5+fontSize );
        g2d.drawString( "3. Enemies get faster", 10,80+5+fontSize );


        g2d.drawString("Total Score: " + totalScore, w-125, 20);
        g2d.setFont(font1);
        g2d.drawString("Level Score: " + roundScore, w-100, 20+5+fontSize);
    }
    private void removeOutOfBound(){
        //remove enemy planes when they get out of bounds
        for (int i = removedEnemyCondition; i< enemyPlanes.size(); i++){

            if (!(enemyPlanes.get(i).isVisible()) && enemyPlanes.get(i).getEnemyPlayerID() !=0){

                enemyPlanes.remove(enemyPlanes.get(i));
                removedEnemyPlayer++;
                if (enemyPlanes.get(i) != null){
                    if (enemyPlanes.get(i).getEnemyPlayerID() == 0 ){
                        removedEnemyCondition =removedEnemyPlayer;
                    }else {
                        removedEnemyCondition =0;
                    }
                }

            }
        }
        //remove player bullets when they get out of bounds
        for (int i = removedPlayerBulletCondition; i< player.playerBullets.size(); i++){
            if (!(player.playerBullets.get(i).isVisible()) && player.playerBullets.get(i) != null){
                if (player.playerBullets.get(i).getBulletID() !=0){

                    player.playerBullets.remove(player.playerBullets.get(i));
                    removedPlayerBullet++;
                    if (player.playerBullets != null && player.playerBullets.get(i) != null){
                        if (player.playerBullets.get(i).getBulletID() == 0){
                            removedPlayerBulletCondition =removedPlayerBullet;
                        }else {
                            removedPlayerBullet =0;
                        }
                    }
                }


            }
        }


    }


    private class TAdapter extends KeyAdapter 
    {

        @Override
        public void keyReleased(KeyEvent e) 
        {
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) 
        {
            player.keyPressed(e);
        }
    }

}