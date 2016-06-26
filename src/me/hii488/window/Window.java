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

  //      this.frame.addMouseListener(new MouseListenerStuff());
        
        // Create the display
        this.display = new Display(this);
        this.display.addMouseListener(new MouseListenerStuff());
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
    	if(World.selectedAlgorithm!=null && !World.algorithmFinished)World.tickAlgorithm();
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

    
    // Tick is deliberately throttled, it should only happen every 'x' ms, as it applies game logic
    // FPS should happen as fast as it can, since it renders (only important if the field of vision can change)
    
	public static int currentFPS = 0;
	
	public static long delay = 1;
	
    @Override
    public void run() {
        int fps = 0, tick = 0;
        
        double fpsTimer = System.currentTimeMillis();
        double secondsPerTick = 1D / targetTPS;
        double nsPerTick = secondsPerTick * 1000000000D;
        double then = System.nanoTime();
        double now;
        double unprocessed = 0;
        
        while(isRunning){
  /*          now = System.nanoTime();
            unprocessed += (now - then) / nsPerTick;
            then = now;
            while(unprocessed >= 1){
                tick();
                tick++;
                unprocessed--;
            }
*/
            // This is NOT to sleep, but to limit the game loop
            try {
                Thread.sleep(delay);
            } catch (Exception e) {
                e.printStackTrace();
            }

            render();
            tick();
            fps++;

            // If the current time is 1 second greater than the last time we printed
            if(System.currentTimeMillis() - fpsTimer >= 1000){
                System.out.printf("FPS: %d%n"/*, TPS: %d%n"*/, fps /*,tick*/);
                currentFPS = fps;
                fps = 0; tick = 0;
                fpsTimer += 1000;
            }
        }

        // When the gameloop is finished running, close the program
        this.frame.dispatchEvent(new WindowEvent(this.frame, WindowEvent.WINDOW_CLOSING));
	
    }
}
