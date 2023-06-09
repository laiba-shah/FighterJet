package Objects;

import java.awt.*;
import java.util.ArrayList;

public class EnemyPlayer extends Vehicle{
    private int EnemyPlayerID;
    public MyPoint point;
    public EnemyBullet enemyBullet;
    private Integer speed;
    public boolean visible;
    public ArrayList<EnemyBullet> enemyBullets = new ArrayList<EnemyBullet>();
    private int EbulletsCreated =0;

    private int bulletSpeed = 4;
    public EnemyPlayer(String path, int x, int y)
    {
        super(path, x, y);
    }


    public void setSpeed(Integer speed) {
        this.speed = speed;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }


    public EnemyPlayer(String path, int x, int y, int speed)
    {
        super(path, x, y);
        this.speed = speed;
    }

    @Override
    public Rectangle getBounds() {

        return new Rectangle(x-w/2, y-w/2, w, h);
    }



    public EnemyPlayer(String path, MyPoint p)
    {
        super(path, p);
    }


    public boolean isVisible(){
        return visible;
    }
    public void move()
    {
        if(this.y<h+getHeight()*2){
            this.visible = true;
            if(this.speed == null)
                this.y += 2;
            else
                this.y += speed;
        }else{
            this.visible = false;
        }
    }
    public void dodgeRight(){
        this.x+=speed;
    }
    public void dodgeLeft(){
        this.x-=speed;
    }



    public void Fire()
    {
        point = new MyPoint(this.x,this.y );
        enemyBullet = FactoryCreator.getProjectileFactory().getEnemyBullet("src/Sprites/PlayerFire/1.png", point, bulletSpeed);
        enemyBullets.add(enemyBullet);
        enemyBullet.setBulletID(EbulletsCreated);
        EbulletsCreated++;

    }
    public Integer getSpeed() {
        return speed;
    }
    public boolean isBomber(int ID) {
        if (ID % 3 == 0 && ID != 0){
            return true;
        }

        else
            return false;
    }


    public int getEnemyPlayerID() {
        return EnemyPlayerID;
    }

    public void setEnemyPlayerID(int enemyPlayerID) {
        EnemyPlayerID = enemyPlayerID;
    }

    @Override
    public boolean isBullet() {
        return false;
    }

}
