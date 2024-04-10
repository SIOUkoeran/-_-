package com.example.demo.quadtree.object;

import java.util.ArrayList;
import java.util.List;

public class QuadTree {
    private static final int MAX_POINTS = 3;
    private final Region area;
    private final List<Point> points = new ArrayList<>();
    private final List<QuadTree> quadTrees = new ArrayList<>();

    public QuadTree(Region area) {
        this.area = area;
    }

    public boolean addPoint(Point point){
        try {
            if (this.area.containsPoint(point)) {
                if (this.points.size() < MAX_POINTS) {
                    points.add(point);
                    return true;
                } else {
                    if (this.quadTrees.size() == 0)
                        createQuadrant();
                    return addQuadrantPoint(point);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public List<Point> search(Region searchRegion, List<Point> matches) {
        if (matches == null) {
            matches = new ArrayList<Point>();
        }
        if (!this.area.doesOverlap(searchRegion)) {
            return matches;
        } else {
            for (Point point : points) {
                if (searchRegion.containsPoint(point)) {
                    matches.add(point);
                }
            }
            if (!this.quadTrees.isEmpty()) {
                for (int i = 0; i < 4; i++) {
                    quadTrees.get(i)
                            .search(searchRegion, matches);
                }
            }
        }
        return matches;
    }

    /**
     *
     * @param point
     * @return
     */
    private boolean addQuadrantPoint(Point point) {
        boolean flag = false;
        for (int i = 0; i < 4; i++) {
            flag = this.quadTrees.get(i)
                    .addPoint(point);
            if (flag)
                return true;
        }
        return false;
    }

    private void createQuadrant() {
        Region region = null;
        for (int i = 0; i < 4; i++) {
            region = this.area.getQuadrant(i);
            this.quadTrees.add(new QuadTree(region));
        }
    }
}
