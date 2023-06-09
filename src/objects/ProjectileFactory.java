package Objects;

public class ProjectileFactory {
    public EnemyBullet getEnemyBullet(String path, MyPoint p, int speed){
        return new EnemyBullet(path,p,speed);
    }
    public PlayerBullet getPlayerBullet(String path, MyPoint p, int speed){
        return new PlayerBullet(path,p,speed);
    }
}
