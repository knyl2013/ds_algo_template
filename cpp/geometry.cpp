// S=1/2[(x1y2-x2y1)+(x2y3-x3y2)+(x3y1-x1y3)]
int area(int x1, int y1, int x2, int y2, int x3, int y3) {
    return (x1*y2-x2*y1)+(x2*y3-x3*y2)+(x3*y1-x1*y3);
}

double polar_angle(int x1, int y1, int x2, int y2) {
    float ans = atan2(y1 - y2, x1 - x2);
    float pi = atan(1)*4;
    return ans * 180 / pi;
}


int inf = 1e9+7;
pair<int, int> find_intersect(pair<pair<int, int>, pair<int, int>>& line1, pair<pair<int, int>, pair<int, int>>& line2) {
    int x1 = line1.first.first, x2 = line1.second.first, y1 = line1.first.second, y2 = line1.second.second;
    int x3 = line2.first.first, x4 = line2.second.first, y3 = line2.first.second, y4 = line2.second.second;
    int denominator = (x1-x2)*(y3-y4)-(y1-y2)*(x3-x4);
    if (denominator == 0) return {inf, inf};
    int px= ( (x1*y2-y1*x2)*(x3-x4)-(x1-x2)*(x3*y4-y3*x4) ) / denominator;
    int py= ( (x1*y2-y1*x2)*(y3-y4)-(y1-y2)*(x3*y4-y3*x4) ) / denominator;
    return {px, py};
}
int dist(pair<int, int>& from, pair<int, int>& to) {
    return abs(from.first - to.first) + abs(from.second - to.second);
}
bool is_point_online(pair<int, int>& point, pair<pair<int, int>, pair<int, int>>& line) {
    return dist(line.first, point) + dist(line.second, point) == dist(line.first, line.second);
}
pair<int, int> find_segment_intersect(pair<pair<int, int>, pair<int, int>>& line1, pair<pair<int, int>, pair<int, int>>& line2) {
    pair<int, int> inter = find_intersect(line1, line2);
    if (inter.first == inf) return {inf, inf};
    bool within_two_lines = is_point_online(inter, line1) && is_point_online(inter, line2);
    if (!within_two_lines) return {inf, inf};
    return inter;
}