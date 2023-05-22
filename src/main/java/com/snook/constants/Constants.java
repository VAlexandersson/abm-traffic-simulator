package com.snook.constants;

public class Constants {

    public static final int pixelsInCm = 38;
    public static final int scale = 38;

    public static final int ticksPerSecond = 32;

    public static final int meter = pixelsInCm/scale;  // ~ 4 meters in 1cm

    public static final int tileSize =  (2*meter);
    public static final int carLength = (4*meter);
    public static final int carWidth =  (2*meter);

    public static final int boardWidthInTiles = 30;
    public static final int boardHeightInTiles = 30;

    public static final int boardWidthInPixels = boardWidthInTiles * scale;
    public static final int boardHeightInPixels = boardHeightInTiles * tileSize;




}
