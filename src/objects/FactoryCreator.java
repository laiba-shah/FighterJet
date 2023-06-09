package Objects;

public abstract class FactoryCreator {
    public static PlaneFactory getPlaneFactory(){
        return new PlaneFactory();
    }
    public static ProjectileFactory getProjectileFactory(){
        return new ProjectileFactory();
    }

}
