package com.example.demo.quadtree;

import com.example.demo.quadtree.object.Point;
import com.example.demo.quadtree.object.QuadTree;
import com.example.demo.quadtree.object.Region;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

class QuadTreeRunner {

    private Logger logger = LoggerFactory.getLogger(QuadTreeRunner.class);

    @Test
    void quadTreeRunner() {
        Region area = new Region(0, 0, 400, 400);
        QuadTree quadTree = new QuadTree(area);

        // points must be sorted in quadTree node order
        float[][] points = new float[][] { { 21, 25 }, { 55, 53 }, { 70, 318 }, { 98, 302 },
                { 49, 229 }, { 135, 229 }, { 224, 292 }, { 206, 321 }, { 197, 258 }, { 245, 238 } };

        for (int i = 0; i < points.length; i++) {
            Point point = new Point(points[i][0], points[i][1]);
            quadTree.addPoint(point);
        }
        Region searchArea = new Region(200, 200, 250, 250);
        List<Point> result = quadTree.search(searchArea, null);
        logger.info("RESULT IS : {}", result);
    }
}
