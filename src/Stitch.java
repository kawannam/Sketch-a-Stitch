public class Stitch {

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
	
	byte one;
	byte two;
	byte three;
	
	public Stitch(int x, int y, boolean colourChange, boolean jump) {
		one = (byte)0x00;
		two = (byte)0x00;
		three = (byte)0x00;
		int temp;
		
		temp = numBreakDown(y, 0);
		//System.out.println(temp);
		if(temp == -1) {
			one = (byte) (one | Y_1_MINUS);
			//System.out.println("-1");
		} else if (temp == 1) {
			one = (byte) (one | Y_1_PLUS);
			//System.out.println("1");
		} else {
			//0, do nothing
		}
		
		temp = numBreakDown(y,1);
		if(temp == -1) {
			two = (byte) (two | Y_3_MINUS);
		} else if (temp == 1) {
			two = (byte) (two | Y_3_PLUS);
		} else {
			//0, do nothing
		}
		
		temp = numBreakDown(y,2);
		if(temp == -1) {
			one = (byte) (one | Y_9_MINUS);
		} else if(temp == 1) {
			one = (byte) (one | Y_9_PLUS);
		} else {
			//0, do nothing
		}
		
		temp = numBreakDown(y,3);
		if(temp == -1) {
			two = (byte) (two | Y_27_MINUS);
		} else if (temp == 1) {
			two = (byte) (two | Y_27_PLUS);
		} else {
			//0, do nothing
		}

		temp = numBreakDown(y,4);
		if(temp == -1) {
			three = (byte) (three | Y_81_MINUS);
		} else if (temp == 1) {
			three = (byte) (three | Y_81_PLUS);
		} else {
			//0, do nothing
		}
		
		temp = numBreakDown(x, 0);
		if(temp == -1) {
			one = (byte) (one | X_1_MINUS);
		} else if (temp == 1) {
			one = (byte) (one | X_1_PLUS);
		} else {
			//0, do nothing
		}
		
		temp = numBreakDown(x,1);
		if(temp == -1) {
			two = (byte) (two | X_3_MINUS);
		} else if (temp == 1) {
			two = (byte) (two | X_3_PLUS);
		} else {
			//0, do nothing
		}
		
		temp = numBreakDown(x,2);
		if(temp == -1) {
			one = (byte) (one | X_9_MINUS);
		} else if(temp == 1) {
			one = (byte) (one | X_9_PLUS);
		} else {
			//0, do nothing
		}
		
		temp = numBreakDown(x,3);
		if(temp == -1) {
			two = (byte) (two | X_27_MINUS);
		} else if (temp == 1) {
			two = (byte) (two | X_27_PLUS);
		} else {
			//0, do nothing
		}

		temp = numBreakDown(x,4);
		if(temp == -1) {
			three = (byte) (three | X_81_MINUS);
		} else if (temp == 1) {
			three = (byte) (three | X_81_PLUS);
		} else {
			//0, do nothing
		}
		
		three = (byte) (three | SET);
		
		if( colourChange ) {
			three = (byte) (three | COLOUR_CHANGE);
		}
		
		if( jump ) {
			three = (byte) (three | JUMP);
		}
	}
	
	public int numBreakDown(int n, int i) {
		// (( floor( (n+121)/3^i) % 3 ) -1
		int answer = n + 121;
		//System.out.println("1 " + answer);
		answer = (int) Math.floor((answer/(Math.pow((double)3, (double)i))));
		//System.out.println("2 " + answer);
		answer = answer % 3;
		//System.out.println("3 " + answer);
		answer = answer - 1;
		//System.out.println("4 " + answer);
		
		return answer;
	}
	
	public byte[] getStitch() {
		return new byte[]{one, two, three};
	}
}
