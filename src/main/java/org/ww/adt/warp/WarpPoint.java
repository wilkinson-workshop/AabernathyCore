package org.ww.adt.warp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WarpPoint {
    private float positionX;
    private float positionZ;
    private float positionY;

    private float pitch;
    private float yaw;
}
