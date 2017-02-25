
public class FillStitchDesign {

	public static void fillStitch(DesignData dd) {
		for(int l = 0; l < 10; l++){
			for(int i = 0; i < 12; i++) {
				dd.addStitch(5, 0, false, false);
			}
			dd.addStitch(0, 3, false, false);
			for(int i = 0; i < 12; i++) {
				dd.addStitch(-5, 0, false, false);
			}
			dd.addStitch(0, 3, false, false);
		}
		for(int l = 0; l < 10; l++){
			for(int i = 0; i < 3; i++) {
				dd.addStitch(20, 0, false, false);
			}
			dd.addStitch(0, 3, false, false);
			for(int i = 0; i < 3; i++) {
				dd.addStitch(-20, 0, false, false);
			}
			dd.addStitch(0, 3, false, false);
		}
	}

	public static void fill(DesignData dd) {
		for(int k = 5; k < 10; k++){
			for(int l = 0; l < 10; l++){
				for(int i = 0; i < (80/k); i++) {
					dd.addStitch(k, 0, false, false);
				}
				dd.addStitch(0, 3, false, false);
				for(int i = 0; i < (80/k); i++) {
					dd.addStitch(-k, 0, false, false);
				}
				dd.addStitch(0, 3, false, false);
			}
		}
	}
	
	public static void everyStitch(DesignData dd) {
		for(int k=1; k < 122; k++) {
			dd.addStitch(k, 0, false, false);
			dd.addStitch((-1 * k), 0, false, false);
			dd.addStitch(0, 1, false, false);
		}
	}
}
