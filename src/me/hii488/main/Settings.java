package me.hii488.main;

public class Settings {
	
	public static class WorldSettings{
		public static boolean elastic = false;
		public static boolean gravity = true;
		
		public static int TargetTPS = 30;
		public static float currentSpeed = 1;
	}
	
	
	
	// Currently unused
	public static class CameraSettings{
		
		public boolean movable = true;
		
		public static float minZoom = 0.01f;
		public static float maxZoom = 2f;
		
	}
	
}
