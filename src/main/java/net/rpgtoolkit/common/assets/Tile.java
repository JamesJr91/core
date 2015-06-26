/**
 * Copyright (c) 2015, rpgtoolkit.net <help@rpgtoolkit.net>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package net.rpgtoolkit.common.assets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 *
 * @author Geoff Wilson
 * @author Joshua Michael Daly
 */
public class Tile extends WritableRaster
{

    private TileSet tileSet;
    private int index;

    private BufferedImage tileImage;

    /*
     * *************************************************************************
     * Public Constructors
     * *************************************************************************
     */
    public Tile()
    {
        super(ColorModel.getRGBdefault().createCompatibleSampleModel(32, 32),
                new Point(0, 0));
    }
    
    public Tile(TileSet tileSet, int index)
    {
        super(ColorModel.getRGBdefault().createCompatibleSampleModel(32, 32),
                new Point(0, 0));
        this.tileSet = tileSet;
        this.index = index + 1;
    }

    /*
     * *************************************************************************
     * Public Getters and Setters
     * *************************************************************************
     */
    public int getIndex()
    {
        return this.index;
    }
    
    public void setIndex(int index)
    {
        this.index = index;
    }
    
    public String getName()
    {
        return this.tileSet.getName() + this.getIndex();
    }
    
    public void setPixel(int x, int y, Color newPixel)
            throws TilePixelOutOfRangeException
    {
        if ((x > 31) || (y > 31) || (x < 0) || (y < 0))
        {
            throw new TilePixelOutOfRangeException("Invalid Pixel Coordinates");
        }

        int[] colorBands = new int[]
        {
            newPixel.getRed(), newPixel.getGreen(),
            newPixel.getBlue(), newPixel.getAlpha()
        };
        this.setPixel(x, y, colorBands);

    }

    public Color getPixel(int x, int y) throws TilePixelOutOfRangeException
    {
        if ((x > 31) || (y > 31) || (x < 0) || (y < 0))
        {
            throw new TilePixelOutOfRangeException("Invalid Pixel Coordinates");
        }

        int[] pixel = this.getPixel(x, y, new int[this.getNumBands()]);

        return new Color(pixel[0], pixel[1], pixel[2], pixel[3]);
    }

    public BufferedImage getTileAsImage()
    {
        if (tileImage == null)
        {
            tileImage = new BufferedImage(getWidth(), getHeight(),
                    BufferedImage.TYPE_INT_ARGB);
            tileImage.setData(this);
        }

        return tileImage;
    }
}
