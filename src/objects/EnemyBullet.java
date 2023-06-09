package Objects;

import java.awt.*;

public class EnemyBullet extends Vehicle{
    private Integer speed;
    private boolean visible;
    private int BulletID=0;

    public int getBulletID() {
        return BulletID;
    }

    public void setBulletID(int bulletID) {
        BulletID = bulletID;
    }

    public EnemyBullet(String path, int x, int y)
    {
        super(path, x, y);

    }
    public EnemyBullet(String path, int x, int y, int bulletSpeed)
    {
        super(path, x, y);
        this.speed = bulletSpeed;

    }

    public EnemyBullet(String path, MyPoint p)
    {
        super(path, p);
    }
    public EnemyBullet(String path, MyPoint p, int speed)
    {
        super(path, p);
        this.speed = speed;
    }


    public boolean isVisible() {
        return visible;
    }



    public void setVisible(boolean visible) {
        this.visible = visible;

    }

    @Override
    public boolean isBomber(int ID) {
        return false;
    }

    @Override
    public boolean isBullet() {
        return true;
    }



    @Override
    public Rectangle getBounds() {

        return new Rectangle(x-w/2, y-w/2, w, h);
    }
    public void dodgeLeft(){

    }
    public void dodgeRight(){

    }

    public void move(){
        if(this.y>h && this.BulletID != 0){
            this.visible = true;
            if(this.speed == null)
                this.y += 2;
            else
                this.y += speed;
        }else{
            this.visible = false;
        }

    }


    public void Fire()
    {

    }

}
