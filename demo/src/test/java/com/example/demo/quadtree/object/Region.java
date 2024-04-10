package com.example.demo.quadtree.object;

public record Region(
        float x1,
        float y1,
        float x2,
        float y2
) {
    /*
    포인트 포함 여부 확인
     */
    public boolean containsPoint(Point point) {
        return point.x() >= x1 &&
                point.x() < x2 &&
                point.y() >= y1 &&
                point.y() < y2;
    }

    public boolean doesOverlap(Region region) {
        if (region.x2() < this.x1()) {
            return false;
        }
        if (region.x1() > this.x2()) {
            return false;
        }
        if (region.y1() > this.y2()) {
            return false;
        }
        if (region.y2() < this.y1()) {
            return false;
        }
        return true;
    }

    /**
     * left node get
     * @param index leaf node index
     * @return Region
     */
    public Region getQuadrant(int index) {
        float quadrantWidth = (this.x1 + this.x2) / 2;
        float quadrantHeight = (this.y1 + this.y2) / 2;

        return switch (index) {
            case 0 -> new Region(this.x1, this.y1, x1 + quadrantWidth, y1 + quadrantHeight);
            case 1 -> new Region(this.x1, this.y1 + quadrantHeight, this.x2 + quadrantWidth, this.y2);
            case 2 -> new Region(this.x1 + quadrantWidth, this.y1 + quadrantHeight, this.x2, this.y2);
            case 3 -> new Region(this.x1 + quadrantWidth, this.y1, this.x2, this.y2 + quadrantHeight);
            default -> throw new IllegalStateException("Unexpected value: " + index);
        };
    }
}
