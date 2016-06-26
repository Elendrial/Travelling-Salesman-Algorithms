package me.hii488.window.gfx;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import me.hii488.window.Window;

/*
 * 			Not really used -
 * 			Probably can be deleted
 * 
 */


public class Bitmap {
	
	private int[] pixels;
    private int width, height;
	
    public Bitmap(int width, int height){
        this.width = width;
        this.height = height;
        this.pixels = new int[width * height];
    }
    
    public static Bitmap getBitmap(String path){
        try {
            BufferedImage bImage = ImageIO.read(Window.class.getResourceAsStream(path));
            Bitmap bitmap = new Bitmap(bImage.getWidth(), bImage.getHeight());

            bImage.getRGB(0, 0, bitmap.width, bitmap.height, bitmap.pixels, 0, bitmap.width);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    
    public void renderBitmap(Bitmap bitmap, int x, int y){
    	for(int wx = 0; wx < bitmap.width; wx++){
    		for(int hy = 0; hy < bitmap.height; hy++){
                int pixel = bitmap.pixels[bitmap.getPixelPosition(wx, hy)];
                this.pixels[getPixelPosition(x + wx, y + hy)] = pixel;
            }
        }
    }
    
    // Sets the pixels array
    public void setPixels(int[] pixels){
        this.pixels = pixels;
    }
    
    // Fills Entire Screen
    public void fill(int color){
        Arrays.fill(pixels, color);
    }
    
    public void setSpecificPixel(int index, int pixel){
    	pixels[index] = pixel;
    }
    
    public void setSpecificPixels(int[] index, int[] pixel){
    	for(int i = 0; i < index.length && i < pixel.length; i++){
    		pixels[index[i]] = pixel[i];
    	}
    }
    
    public void drawPolygon(Polygon p, Color c){
    	for(int i = 0; i < p.npoints; i++){
    		if(i < p.npoints - 1){
    			int l = p.xpoints[i] <= p.xpoints[i+1] ? i : i+1;
    			int r = p.xpoints[i] > p.xpoints[i+1] ? i : i+1;
    			drawLine(p.xpoints[l], p.ypoints[l], p.xpoints[r], p.ypoints[r], c.getRGB());
    		}
    		if(i == p.npoints - 1){
    			int l = p.xpoints[i] <= p.xpoints[i+1] ? i : 0;
    			int r = p.xpoints[i] > p.xpoints[i+1] ? i : 0;
    			drawLine(p.xpoints[l], p.ypoints[l], p.xpoints[r], p.ypoints[r], c.getRGB());
    		}
    	}
    }
    
    public void drawPolygon(Polygon p, int c){
    	for(int i = 0; i < p.npoints; i++){
    		if(i < p.npoints - 1){
    			int l = p.xpoints[i] <= p.xpoints[i+1] ? i : i+1;
    			int r = p.xpoints[i] > p.xpoints[i+1] ? i : i+1;
    			drawLine(p.xpoints[l], p.ypoints[l], p.xpoints[r], p.ypoints[r], c);
    		}
    		if(i == p.npoints - 1){
    			int l = p.xpoints[i] <= p.xpoints[i+1] ? i : 0;
    			int r = p.xpoints[i] > p.xpoints[i+1] ? i : 0;
    			drawLine(p.xpoints[l], p.ypoints[l], p.xpoints[r], p.ypoints[r], c);
    		}
    	}
    }
    
    public void drawPolygon(Polygon p){
    	for(int i = 0; i < p.npoints; i++){
    //		System.out.println("drawPolygon()");
    		if(i < p.npoints - 1){
    			drawLine(p.xpoints[i], p.ypoints[i], p.xpoints[i+1], p.ypoints[i+1], Color.black.getRGB());
    		}
    		if(i == p.npoints - 1){
    			drawLine(p.xpoints[i], p.ypoints[i], p.xpoints[0], p.ypoints[0], Color.black.getRGB());
    		}
    	}
    }
    
    // setSpecificPixel(getPixelPosition(x,y), colour);
    public void drawLine(int startx, int starty, int endx, int endy, int colour){
    	 int t, distance;
    	    int xerr=0, yerr=0, delta_x, delta_y;
    	    int incx, incy;
    	 
    	    /* compute the distances in both directions */
    	    delta_x=endx-startx;
    	    delta_y=endy-starty;
    	 
    	    /* Compute the direction of the increment,
    	       an increment of 0 means either a horizontal or vertical
    	       line.
    	    */
    	    if(delta_x>0) incx=1;
    	    else if(delta_x==0) incx=0;
    	    else incx=-1;
    	 
    	    if(delta_y>0) incy=1;
    	    else if(delta_y==0) incy=0;
    	    else incy=-1;
    	 
    	    /* determine which distance is greater */
    	    delta_x=Math.abs(delta_x);
    	    delta_y=Math.abs(delta_y);
    	    if(delta_x>delta_y) distance=delta_x;
    	    else distance=delta_y;
    	 
    	    /* draw the line */
    	    for(t=0; t<=distance+1; t++) {
    	    	setSpecificPixel(getPixelPosition(startx,starty), colour);
    	         
    	        xerr+=delta_x;
    	        yerr+=delta_y;
    	        if(xerr>distance) {
    	            xerr-=distance;
    	            startx+=incx;
    	        }
    	        if(yerr>distance) {
    	            yerr-=distance;
    	            starty+=incy;
    	        }
    	    }
    }
    
    
    // gives the pixel position as a single number
    private int getPixelPosition(int x, int y){
        return y*width + x;
    }
}
