package io.javasmithy.model.room;

import io.javasmithy.model.position.PointGrid;

public enum RoomType {
    ROOM_0(new PointGrid(1,1,0,0,50)),
    ROOM_1(new PointGrid(12, 16, 0, 0,  50)),
    ROOM_2(new PointGrid(12, 16, 0, 0,  50)),
    ROOM_3(new PointGrid(12, 16, 0, 0,  50));

    private PointGrid grid;

    private RoomType(PointGrid grid){
        this.grid = grid;
    }

    public PointGrid getGrid(){
        return this.grid;
    }
}
