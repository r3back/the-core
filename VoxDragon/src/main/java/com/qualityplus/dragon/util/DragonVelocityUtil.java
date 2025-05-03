package com.qualityplus.dragon.util;

import lombok.experimental.UtilityClass;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

@UtilityClass
public final class DragonVelocityUtil {
    public Vector getTrajectory(Entity entity1, Entity entity2) {
        return getTrajectory(entity1.getLocation().toVector(), entity2.getLocation().toVector());
    }

    public Vector getTrajectory(Location location1, Location location2) {
        return getTrajectory(location1.toVector(), location2.toVector());
    }

    public Vector getTrajectory(Vector vector1, Vector vector2) {
        return vector2.subtract(vector1).normalize();
    }

    public Vector getTrajectory2d(Location location1, Location location2) {
        return getTrajectory2d(location1.toVector(), location2.toVector());
    }

    public Vector getTrajectory2d(Vector vector1, Vector vector2) {
        return vector2.subtract(vector1).setY(0).normalize();
    }

    public float getPitch(Vector paramVector) {
        double d1 = paramVector.getX();
        double d2 = paramVector.getY();
        double d3 = paramVector.getZ();
        double d4 = Math.sqrt(d1 * d1 + d3 * d3);
        double d5 = Math.toDegrees(Math.atan(d4 / d2));
        if (d2 <= 0.0D) {
            d5 += 90.0D;
        } else {
            d5 -= 90.0D;
        }
        return (float)d5;
    }

    public float getYaw(Vector paramVector) {
        double d1 = paramVector.getX();
        double d2 = paramVector.getZ();
        double d3 = Math.toDegrees(Math.atan(-d1 / d2));
        if (d2 < 0.0D)
            d3 += 180.0D;
        return (float)d3;
    }

    public Vector normalize(Vector paramVector) {
        if (paramVector.length() > 0.0D)
            paramVector.normalize();
        return paramVector;
    }

    public void velocity(Entity entity, Vector vector, boolean boolean1, boolean boolean2, double double1, double double2, double double3, double double4) {
        if (Double.isNaN(vector.getX()) || Double.isNaN(vector.getY()) || Double.isNaN(vector.getZ()) || vector.length() == 0.0D)
            return;
        if (boolean1)
            vector.setY(double2);
        vector.normalize();
        vector.multiply(double1);
        vector.setY(vector.getY() + double3);
        if (vector.getY() > double4)
            vector.setY(double4);
        if (boolean2)
            vector.setY(vector.getY() + 0.2D);
        entity.setFallDistance(0.0F);
        entity.setVelocity(vector);
    }
}
