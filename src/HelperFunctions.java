import java.util.ArrayList;
import java.lang.Math;

public class HelperFunctions {

	static int MAX_STITCH_LENGTH = 121;
	
	public HelperFunctions() {
		
	}
	
	static ArrayList<Point> createLines( Point[] points, int length ) {

		ArrayList<Point> rPoints = new ArrayList<Point>();
		rPoints.add(new Point(points[0].x, points[0].y));
		for(int i = 1; i < points.length; i++ ) {
			rPoints.addAll(subDivide(points[i-1], points[i], length) );
		}
		//return createRel(rPoints);
		return rPoints;
	}
	
	static ArrayList<Point> createLine( Point oldP, Point newP, int length ) {
		int xDist;
		int yDist;
		int extraStitches;
		ArrayList<Point> p = new ArrayList<Point>();
		
		xDist = Math.abs(oldP.x - newP.x);
		yDist = Math.abs(oldP.y - newP.y);
		
		if ( (xDist > length ) || ( yDist > length ) ){
			extraStitches = Math.max( ( xDist / length ), ( yDist / length ) );
			for (int j = 1; j < extraStitches; j++ ) {
				System.out.print( " P( " + ( oldP.x + ( ( ( oldP.x - newP.x ) / extraStitches ) * j )) + ", " + ( oldP.y + ( ( ( oldP.y - newP.y ) / extraStitches ) * j ) )  + " )");
				p.add( new Point( ( oldP.x + ( ( ( oldP.x - newP.x ) / extraStitches ) * j ) ),
								  ( oldP.y + ( ( ( oldP.y - newP.y ) / extraStitches ) * j ) ) ) );
			}
		}
		System.out.print( " P( " + newP.x + ", " + newP.y  + " )");

		p.add(newP);
		System.out.println("");
		return p;		
	}
	
	static ArrayList<Point> createRel( ArrayList<Point> obj ) {
		ArrayList<Point> rel = new ArrayList<Point>();
		rel.add(new Point(0, 0));
		for (int i = 1; i < obj.size(); i++ ) {
			rel.add(new Point( ( obj.get(i-1).x - obj.get(i).x ), (obj.get(i-1).y - obj.get(i).y ) ) );
		}
		
		return rel;
	}
	
	static ArrayList<Point> subDivide( Point fir, Point sec, int length) {
		ArrayList<Point> ps = new ArrayList<Point>();
		int dx = fir.x - sec.x;
		int dy = fir.y - sec.y;
		double dist = Math.sqrt( ( dx * dx ) + ( dy * dy ) );
	    double t = length/dist;
	    int x;
	    int y;
	    double tp = t;
	    for(int i = 2; tp < 1; i++ ){
	    	x = (int) ( ( (1-tp) * fir.x ) +  ( tp * sec.x ) );
	    	y = (int) ( ( (1-tp) * fir.y ) +  ( tp * sec.y ) );
	    	ps.add(new Point( x , y));
	    	tp = t*i;
	    }
	    ps.add(new Point(sec.x, sec.y));	    
	    return ps;
	}
}
