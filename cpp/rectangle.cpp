#include <bits/stdc++.h>
using namespace std;
struct Rect {
  // bottomleft_x, bottomleft_y, topright_x, topright_y
  int x1, y1, x2, y2;
  int width() { return max(0, x2 - x1); }
  int height() { return max(0, y2 - y1); }
  int area() { return width() * height(); }
};
Rect overlap(Rect& r1, Rect& r2) {
  return {max(r1.x1, r2.x1), max(r1.y1, r2.y1), min(r1.x2, r2.x2), min(r1.y2, r2.y2)};
}