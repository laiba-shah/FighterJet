package Objects;

public class PlaneFactory {
    public EnemyPlayer getEnemyPlayer(String path, int x, int y, int speed){
        return new EnemyPlayer(path, x, y, speed);
    }
}
