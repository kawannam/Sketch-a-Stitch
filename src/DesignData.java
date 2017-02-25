import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/*This class maintains the information needed for the header as 
 * stitches are added. It converts the 
 */
public class DesignData {
							//      -, 0, +
static int[][] Y_ENCODER = {{0x400000, 0, 0x800000},  // 1
							{0x004000, 0, 0x008000},  // 3
							{0x100000, 0, 0x200000},  // 9
							{0x001000, 0, 0x002000},  // 27
							{0x000010, 0, 0x000004}}; // 81

static int[][] X_ENCODER = {{0x020000, 0, 0x010000},  // 1
							{0x000100, 0, 0x000100},  // 3
							{0x080000, 0, 0x040000},  // 9
							{0x000800, 0, 0x000400},  // 27
							{0x000008, 0, 0x000004}}; // 81

static byte JUMP          = (byte) 0x000080;
static byte COLOUR_CHANGE = (byte) 0x000040;
static byte SET           = (byte) 0x000003;
	static int HEADER_SIZE    = 512;
	
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
		//fillStitch();
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
			lastX = firstX;
			lastY = firstY;
		}
		if( colourChange ) {
			numberOfColourChanges++;
		}
		lastX += x;
		lastY += y;
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
		byte[] stitch = encodeStitch(x, y, colourChange, jump);
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

	public byte[] encodeStitch(int x, int y, boolean colourChange, boolean jump) {
		int answer = SET;		
		for(int i = 0; i < 5; i++) {
			answer = answer | Y_ENCODER[i][numberToTri(y, i)];
			answer = answer | X_ENCODER[i][numberToTri(x, i)];
		}
		if( colourChange ) answer = answer | COLOUR_CHANGE;
		if( jump ) answer = answer | JUMP;
		byte[] bytesToAdd = new byte[3];
		bytesToAdd[0] = (byte)(answer & 0xFF);
		bytesToAdd[1] = (byte)((answer >> 8) & 0xFF);
		bytesToAdd[2] = (byte)((answer >> 16) & 0xFF);
		return bytesToAdd;
		}
		
		private int numberToTri(int n, int i) {
		// (( floor( (n+121)/3^i) % 3 )
		int answer = n + 121;
		answer = (int) Math.floor((answer/(Math.pow((double)3, (double)i))));
		answer = answer % 3;		
		return answer;
	}

}
