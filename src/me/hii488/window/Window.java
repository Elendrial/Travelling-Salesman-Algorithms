package me.hii488.window;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import me.hii488.World;
import me.hii488.main.Settings;
import me.hii488.window.gfx.Display;

public class Window implements Runnable{

	// Actual window
    private JFrame frame;
    private Display display;
    
    public int width, height;
    public String title;

    // How often we want the game to tick per second
    public int targetTPS;

    public boolean isRunning;
    
    public WindowListeners wListeners = new WindowListeners();
    
    
    public Window(String title, int width, int height){
        // Set the variables
        this.title = title;
        this.width = width;
        this.height = height;

        // Set the target TPS
        this.targetTPS = (int) (Settings.WorldSettings.TargetTPS * Settings.WorldSettings.currentSpeed);

        // Setup Window
        this.frame = new JFrame(title);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.getContentPane().setPreferredSize(new Dimension(width, height));
        this.frame.setResizable(false);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
        
        // Create the display
        this.display = new Display(this);
        this.display.addMouseListener(wListeners);
        this.display.addKeyListener(wListeners);
        this.frame.add(this.display);
    }

    public void start(){
        isRunning = true;
        new Thread(this).start();
    }

    public void stop(){
        isRunning = false;
    }

    private void tick(){
    	if(World.selectedAlgorithm != null && !World.algorithmFinished && !World.paused) World.tickAlgorithm();
    }

	private void render(){
		// Buffer Strategy
        BufferStrategy bs = this.display.getBufferStrategy();
        if(bs == null){
        	this.display.createBufferStrategy(2);
            this.display.requestFocus();
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        
        // Clear the graphics
        g.clearRect(0, 0, width, height);
        
        // Draw the display
        this.display.render(g);
        
        g.dispose();
        bs.show();
    }

	public int currentTPS = 0;
	
	public long delay = 1;
	
    @Override
    public void run() {
        int tps = 0;
        
        double printTimer = System.currentTimeMillis();
        
        while(isRunning){

            // This is NOT to sleep, but to limit the game loop
            try {
                Thread.sleep(delay);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // In try/catches so that if one fails the program doesn't stop.
            try{render();}catch(Exception e){e.printStackTrace();}
            try{tick();}catch(Exception e){e.printStackTrace();tps++;}

            // If the current time is 1 second greater than the last time we printed
            if(System.currentTimeMillis() - printTimer >= 1000){
                System.out.printf("TPS: %d%n", tps);
                currentTPS = tps;
                tps = 0;
                printTimer += 1000;
            }
        }

        // When the gameloop is finished running, close the program
        this.frame.dispatchEvent(new WindowEvent(this.frame, WindowEvent.WINDOW_CLOSING));
	
    }
}
