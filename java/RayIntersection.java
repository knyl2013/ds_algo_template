static boolean hit(Line ray, Line boundary)
{
	double x1 = boundary.a.x;
	double x2 = boundary.b.x;
	double x3 = ray.a.x;
	double x4 = ray.b.x;
	
	double y1 = boundary.a.y;
	double y2 = boundary.b.y;
	double y3 = ray.a.y;
	double y4 = ray.b.y;
	
	double den = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
	if (den == 0) {
		return false;
	}

	double t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / den;
	double u = -((x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3)) / den;
	return (t >= 0 && t <= 1 && u >= 0);
}