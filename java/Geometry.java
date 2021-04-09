static int area(int[] r)
    {
        int width = r[2] - r[0], height = r[1] - r[3];
        if (width <= 0 || height <= 0) return 0;
        return width * height;
    }
    static int[] overlap(int[] r1, int[] r2)
    {
        r1 = translate(r1);
        r2 = translate(r2);
        int topLeftX = Math.max(r1[0], r2[0]);
        int bottomRightX = Math.min(r1[2], r2[2]);
        int topLeftY = Math.min(r1[1], r2[1]);
        int bottomRightY = Math.max(r1[3], r2[3]);
        int[] ans = new int[]{topLeftX, topLeftY, bottomRightX, bottomRightY};
        return ans;
    }
    static int[] translate(int[] r) // translate any opposite two points to {topLeftX, topLeftY, bottomRightX, bottomRightY}
    {
        int top = Math.max(r[1], r[3]);
        int left = Math.min(r[0], r[2]);
        int bottom = Math.min(r[1], r[3]);
        int right = Math.max(r[0], r[2]);
        return new int[]{left, top, right, bottom};
    }