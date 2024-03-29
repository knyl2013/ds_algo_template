/*Intersection lib from g4g starts*/
	static int orientation(Point p, Point q, Point r)
	{
		// See https://www.geeksforgeeks.org/orientation-3-ordered-points/
		// for details of below formula.
		int val = (q.y - p.y) * (r.x - q.x) -
				(q.x - p.x) * (r.y - q.y);
	  
		if (val == 0) return 0; // colinear
	  
		return (val > 0)? 1: 2; // clock or counterclock wise
	}
	  static boolean onSegment(Point p, Point q, Point r)
	{
		if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
			q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y))
		return true;
	  
		return false;
	}
	// The main function that returns true if line segment l1-'p1q1'
	// and l2-'p2q2' intersect.
	static boolean hasIntersection(Line l1, Line l2)
	{
		Point p1 = l1.a, q1 = l1.b, p2 = l2.a, q2 = l2.b;
		// Find the four orientations needed for general and
		// special cases
		int o1 = orientation(p1, q1, p2);
		int o2 = orientation(p1, q1, q2);
		int o3 = orientation(p2, q2, p1);
		int o4 = orientation(p2, q2, q1);
	  
		// General case
		if (o1 != o2 && o3 != o4)
			return true;
	  
		// Special Cases
		// p1, q1 and p2 are colinear and p2 lies on segment p1q1
		if (o1 == 0 && onSegment(p1, p2, q1)) return true;
	  
		// p1, q1 and q2 are colinear and q2 lies on segment p1q1
		if (o2 == 0 && onSegment(p1, q2, q1)) return true;
	  
		// p2, q2 and p1 are colinear and p1 lies on segment p2q2
		if (o3 == 0 && onSegment(p2, p1, q2)) return true;
	  
		// p2, q2 and q1 are colinear and q1 lies on segment p2q2
		if (o4 == 0 && onSegment(p2, q1, q2)) return true;
	  
		return false; // Doesn't fall in any of the above cases
	}
	/*Intersection lib from g4g ends*/