package org.ww.adt.warp;

public class WarpPoint {
    private float positionX;
    private float positionZ;
    private float positionY;

    private float pitch;
    private float yaw;

    public WarpPoint(float x, float z, float y, float pitch, float yaw)
    {
        this.positionX = x;
        this.positionZ = z;
        this.positionY = y;

        this.pitch = pitch;
        this.yaw   = yaw;
    }

    public float PositionX() { return this.positionX; }
    public float PositionZ() { return this.positionZ; }
    public float PositionY() { return this.positionY; }

    public float Pitch() { return this.pitch; }
    public float Yaw() { return this.yaw; }
}
