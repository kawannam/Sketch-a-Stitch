
public class StraightStitchDesign {
	
	public static void multiSquare(DesignData dd) {
		for(int k = 3; k < 13; k++){
			for(int i = 0; i <= 10; i++) {
				dd.addStitch(k, 0, false, false);
			}
			for(int i = 0; i <= 10; i++) {
				dd.addStitch(0, k, false, false);
			}
			for(int i = 10; i >= 0; i--) {
				dd.addStitch(-k, 0, false, false);
			}
			for(int i = 10; i >= 0; i--) {
				dd.addStitch(0, -k, false, false);
			}
		}
	}
	
	public static void line(DesignData dd) {
		for(int i = 0; i <= 30; i++) {
			dd.addStitch(10, 0, false, false);
		}
		dd.addStitch(0, 30, false, true);
		dd.addStitch(0, 30, false, true);
		for(int i = 00; i >= -30; i--) {
			dd.addStitch(-10, 0, false, false);
		}
	}
	
	public static void square(DesignData dd) {
		for(int i = -10; i <= 10; i++) {
			dd.addStitch(10, 0, false, false);
		}
		for(int i = -10; i <= 10; i++) {
			dd.addStitch(0, 10, false, false);
		}
		for(int i = 10; i >= -10; i--) {
			dd.addStitch(-10, 0, false, false);
		}
		for(int i = 10; i >= -10; i--) {
			dd.addStitch(0, -10, false, false);
		}
	}
	
	public static void circle(DesignData dd) {
		for(int i = -10; i <= 10; i++) {
			for(int j = 0; j <= 10; j++){
				
				dd.addStitch(i, j, false, false);
			}
		}
		for(int i = 10; i >= -10; i--) {
			for(int j = -10; j <= 0; j++){
				
				dd.addStitch(i, j, false, false);
			}
		}
	}
}
