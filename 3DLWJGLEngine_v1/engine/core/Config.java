package core;

public class Config
{
	// TODO: change into text file and use reader and writer at start and stop
	public static final int FILL = 0;
	public static final int LINE = 1;
	public static final int POINT = 2;
	public static int maxFps = 120;
	public static int maxUps = 60;
	public static int majorOpenGL = 4;
	public static int minorOpenGL = 2;
	public static int width = 1280;
	public static int height = 720;
	public static int polygonMode = FILL;
	@Deprecated
	// BUG: does not work!
	public static int mipmapLevel = 0;
	public static String title = "";
	public static boolean vSync = true;
	public static boolean resizable = true;
	public static boolean fullscreen = false;
	public static boolean windowed = true;
	public static boolean perspective = true;
	public static boolean pause = false;
	public static boolean cursor = true;
	public static boolean is3D = false;
	public static boolean blend = true;
	public static boolean cullFace = false;
	public static int launcherWidth = width + 100;
	public static int launcherHeight = height + 100;
	public static String launcherTitle = title;
	public static boolean maximized = false;
}
