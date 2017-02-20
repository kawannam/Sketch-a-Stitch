import java.io.FileOutputStream;
import java.lang.StringBuilder;

public class BasicStitches {
	
	DesignData dd;
	
	public BasicStitches() {
		dd = new DesignData();
		
	}
	
	public static void main(String[] args) {
		BasicStitches bs = new BasicStitches();
		bs.writeFile("test.dst");
	}
	
	public void writeFile( String fileName ) {
		try {
			FileOutputStream out = new FileOutputStream(fileName);
			//System.out.println(writeHeader());
			out.write(writeHeader().getBytes());
			
			out.write(0x1A);
			char[] padding = new char[389];
			for( int i = 0; i < padding.length - 1; i++ ) {
				padding[i] = ' ';
			}
			padding[padding.length - 1] = '\n';
			padding[0] = '\n';
			out.write(( new String(padding)).getBytes());
			
			//======CONTENT=================================================
			out.write(dd.getDesign());
			//=======FOOTER================================================
			out.write(0x0000F3);
			
			
			out.flush();
			out.close();
		} catch (Exception e) {
			System.out.println("Error opening file");
			e.printStackTrace();
		}
	}
	
	public String writeHeader() {
		// ==================================HEADER==========================================
		StringBuilder sb = new StringBuilder();
		//LA: (16+1) The file name. Name can be max 8. pad with 0x20
		sb.append(createCharArray("LA:", dd.designName, 17));
		//ST: (7+1) Number of stitches. 7 digit number pad with leading 0s. (Includes everything)
		sb.append(createCharArray("ST:",Integer.toString(dd.numberOfStitches), 8));
		//CO: (3+1) Number of color changes. 3 digit number pad with leading 0s.
		sb.append(createCharArray("CO:", Integer.toString(dd.numberOfColourChanges), 4));
		//POSX: (5+1)the most +X position in cm/mm?. 5 digit non-decimal number pad with leading zeros
		sb.append(createCharArray("+X:", Integer.toString(dd.maxX), 6));	
		//NEGX: (5+1) the most -X position in cm/mm?. 5 digit non-decimal number pad with leading zeros
		sb.append(createCharArray("-X:", Integer.toString(dd.minX), 6));
		//POSY: (5+1)the most +Y position in cm/mm?. 5 digit non-decimal number pad with leading zeros
		sb.append(createCharArray("+Y:", Integer.toString(dd.maxY), 6));
		//NEGY: (5+1) the most -Y position in cm/mm?. 5 digit non-decimal number pad with leading zeros
		sb.append(createCharArray("-Y:", Integer.toString(dd.minY), 6));
		//AX: (6+1) the relative coordinates from the starting x to the final x. in 0.1 mm?
		sb.append(createCharArray("AX:", Integer.toString(dd.getPosChangeX()), 7));
		//AY: (6+1) the relative coordinates from the starting y to the final y
		sb.append(createCharArray("AY:", Integer.toString(dd.getPosChangeY()), 7));
		//MX: (6+1) the last position of the stitch. Used for multi file design
		sb.append(createCharArray("MX:", Integer.toString(dd.firstX), 7));
		//MY: (6+1) the last position of the stitch. Used for multi file design
		sb.append(createCharArray("MY:", Integer.toString(dd.firstY), 7));
		//PD: (9+1) also random info for multi design files
		sb.append("PD:******\n");
		return sb.toString();
	}
	
	public char[] createCharArray( String title, String value, int size) {
		if ( value.length()  > ( size - 1 ) ) {
			System.out.println("Error: strings too big: " + title + " " + value);
			return null;
		}
		char[] v = new char[ title.length() + size];
		int start = 0;
		for(int i = 0; i < title.length(); i++ ){
			v[i] = title.charAt(i);
			//System.out.println(i + " " + title.charAt(i));
		}
		for(int i = 0; i < (size - 1 - value.length()); i++) {
			v[title.length() + i] = ' ';
			//System.out.println((title.length() + i) + " space");
			start = i;
		}
		start += title.length() + 1;
		for(int i = 0; i < value.length(); i++) {
			v[start + i] = value.charAt(i);
			//System.out.println((start + i) + " a " + value.charAt(i));
		}
		v[v.length - 1] = '\n';
		return v;
	}
}
