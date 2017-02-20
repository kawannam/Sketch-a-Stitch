import java.io.FileOutputStream;
import java.util.ArrayList;

public class DesignData {
	
	static int HEADER_SIZE    = 512;

	static byte Y_1_PLUS      = (byte) 0x80;
	static byte Y_1_MINUS     = (byte) 0x40;
	static byte Y_9_PLUS      = (byte) 0x20;
	static byte Y_9_MINUS     = (byte) 0x10;
	static byte X_9_MINUS     = (byte) 0x08;
	static byte X_9_PLUS      = (byte) 0x04;
	static byte X_1_MINUS     = (byte) 0x02;
	static byte X_1_PLUS      = (byte) 0x01;
	
	static byte Y_3_PLUS      = (byte) 0x80;
	static byte Y_3_MINUS     = (byte) 0x40;
	static byte Y_27_PLUS     = (byte) 0x20;
	static byte Y_27_MINUS    = (byte) 0x10;
	static byte X_27_MINUS    = (byte) 0x08;
	static byte X_27_PLUS     = (byte) 0x04;
	static byte X_3_MINUS     = (byte) 0x02;
	static byte X_3_PLUS      = (byte) 0x01;
	
	static byte JUMP          = (byte) 0x80;
	static byte COLOUR_CHANGE = (byte) 0x40;
	static byte Y_81_PLUS     = (byte) 0x20;
	static byte Y_81_MINUS    = (byte) 0x10;
	static byte X_81_MINUS    = (byte) 0x08;
	static byte X_81_PLUS     = (byte) 0x04;
	static byte SET           = (byte) 0x03;
	
	FileOutputStream out;
	ArrayList<Byte> design;
	byte[] header;
	
	//Header info
	String designName;
	int numberOfStitches;
	int numberOfColourChanges;
	int maxX;
	int minX;
	int maxY;
	int minY;
	int lastX;
	int lastY;
	int firstX;
	int firstY;
	boolean seenFirst;
	
	
	public DesignData() {
		seenFirst = false;
		ArrayList<Point> ps = new ArrayList<Point>();
		designName = "Test";
		design = new ArrayList<Byte>();
		firstX = 0;
		firstY = 0;
		numberOfStitches = 0;
		numberOfColourChanges = 0;
		maxX = 0;
		minX = 0;
		maxY = 0;
		minY = 0;
		fillStitch();
		/*for(int i = 1; i < 10; i = i+2) {
			straightStitch(0, (i), 10, (i), i);
			straightStitch(10, (i +1), 0, ( i +1), (i+1));
		}*/
		//straightStitch(0, 0, 10, 0, 1);
		//straightStitch(0, 2, 10, 2, 1);
		//Point pss[] = {new Point( 0 , 0), new Point( 1, 0), new Point( 2, 0), new Point( 3, 0), new Point( 4, 0), new Point( 5, 0),
		//		 new Point( 6, 0), new Point( 7, 0), new Point( 8, 0), new Point( 9, 0), new Point( 10, 0), new Point( 11, 0),
		//		 new Point( 12, 0), new Point( 13, 0), new Point( 14, 0), new Point( 15, 0), new Point( 16, 0), new Point( 17, 0)};
		//for( Point p: pss) {
		//	addStitch(p.x, p.y, false, false);
		//}
		
	}
	
	public void fillStitch() {
			for(int l = 0; l < 10; l++){
				for(int i = 0; i < 12; i++) {
					addStitch(5, 0, false, false);
				}
				addStitch(0, 3, false, false);
				for(int i = 0; i < 12; i++) {
					addStitch(-5, 0, false, false);
				}
				addStitch(0, 3, false, false);
			}
			for(int l = 0; l < 10; l++){
				for(int i = 0; i < 3; i++) {
					addStitch(20, 0, false, false);
				}
				addStitch(0, 3, false, false);
				for(int i = 0; i < 3; i++) {
					addStitch(-20, 0, false, false);
				}
				addStitch(0, 3, false, false);
			}
	}
	
	public void fill() {
		for(int k = 5; k < 10; k++){
			for(int l = 0; l < 10; l++){
				for(int i = 0; i < (80/k); i++) {
					addStitch(k, 0, false, false);
				}
				addStitch(0, 3, false, false);
				for(int i = 0; i < (80/k); i++) {
					addStitch(-k, 0, false, false);
				}
				addStitch(0, 3, false, false);
			}
		}
	}
	
	public void multiSquare() {
		for(int k = 3; k < 13; k++){
			for(int i = 0; i <= 10; i++) {
				addStitch(k, 0, false, false);
			}
			for(int i = 0; i <= 10; i++) {
				addStitch(0, k, false, false);
			}
			for(int i = 10; i >= 0; i--) {
				addStitch(-k, 0, false, false);
			}
			for(int i = 10; i >= 0; i--) {
				addStitch(0, -k, false, false);
			}
		}
	}
	
	public void line() {
		for(int i = 0; i <= 30; i++) {
			addStitch(10, 0, false, false);
		}
		addStitch(0, 30, false, true);
		addStitch(0, 30, false, true);
		for(int i = 00; i >= -30; i--) {
			addStitch(-10, 0, false, false);
		}
	}
	
	public void square() {
		for(int i = -10; i <= 10; i++) {
			addStitch(10, 0, false, false);
		}
		for(int i = -10; i <= 10; i++) {
			addStitch(0, 10, false, false);
		}
		for(int i = 10; i >= -10; i--) {
			addStitch(-10, 0, false, false);
		}
		for(int i = 10; i >= -10; i--) {
			addStitch(0, -10, false, false);
		}
	}
	
	public void circle() {
		for(int i = -10; i <= 10; i++) {
			for(int j = 0; j <= 10; j++){
				
				addStitch(i, j, false, false);
			}
		}
		for(int i = 10; i >= -10; i--) {
			for(int j = -10; j <= 0; j++){
				
				addStitch(i, j, false, false);
			}
		}
	}
	
	public byte[] getDesign() {
		byte[] a = new byte[design.size()];
		for(int i = 0; i < a.length; i++){
			a[i] = design.get(i);
		}		
		return a;
	}
	
	public void printPoints(ArrayList<Point> ps) {
		for(Point p : ps){
			System.out.println("(" + p.x + ", " + p.y + ")");
		}
	}
	
	public void straightStitch(int foX, int foY, int toX, int toY, int length) {
		System.out.println("Straight stitch " + foX + " " + foY + " " + toX + " " + toY + " " + length);
		ArrayList<Point> ps = new ArrayList<Point>();
		Point[] points = {new Point(foX, foY), new Point(toX, toY)};
		ps = HelperFunctions.createLines( points, length );
		for( Point p: ps) {
			System.out.println("(" + p.x + ", " + p.y + ")");
			addStitch(p.x, p.y, false, false);
		}
	}
	
	public int getPosChangeX() {
		return Math.abs(firstX - lastX);
	}
	public int getPosChangeY() {
		return Math.abs(firstY - lastY);
	}
	
	void addStitch(int x, int y, boolean colourChange, boolean jump) {
		if (!seenFirst) {
			seenFirst = true;
			firstX = x;
			firstY = y;
		}
		Stitch s = new Stitch(x, y, colourChange, jump);
		if( colourChange ) {
			numberOfColourChanges++;
		}
		lastX = x;
		lastY = y;
		numberOfStitches++;

		if( lastX > maxX ) {
			maxX = lastX;
		}
		if ( lastX < minX ) {
			minX = lastX;
		}
		if ( lastY > maxY ) {
			maxY = lastY;
		}
		if ( lastY < minY ) {
			minY = lastY;
		}
		byte[] stitch = s.getStitch();
		design.add(stitch[0]);
		design.add(stitch[1]);
		design.add(stitch[2]);
		System.out.println("(" + x + ", " + y + ")");
		/*System.out.println(
				String.format("%8s", Integer.toBinaryString(stitch[0] & 0xFF)).replace(' ', '0') + " " + 
				String.format("%8s", Integer.toBinaryString(stitch[1] & 0xFF)).replace(' ', '0') + " " + 
				String.format("%8s", Integer.toBinaryString(stitch[2] & 0xFF)).replace(' ', '0'));
				*/
	}
}
