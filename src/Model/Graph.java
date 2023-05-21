package Model;

import Utility.IPath;
import Utility.IVertex;
import Utility.NonOrientedPath;
import Utility.StaticVertex;

import java.awt.*;
import java.util.ArrayList;

public class Graph implements IModel {
    public Graph() {
        vertexes = new ArrayList<>();
        paths = new ArrayList<>();
    }
    @Override
    public ArrayList<IVertex> getVertexes() {
        return new ArrayList<>(vertexes);
    }

    @Override
    public IVertex createVertex(String vert_name, Point vert_pos) {
        StaticVertex vert = new StaticVertex(vert_name, vert_pos);
        vertexes.add(vert);
        return vert;
    }

    @Override
    public IPath createPath(IVertex beg, IVertex end, double distance) {
        for (NonOrientedPath path : paths) {
            if ((path.getBegin() == beg && path.getEnd() == end) ||
                (path.getEnd() == beg && path.getBegin() == end)) {
                path.updateDistance(distance);

                return null;
            }
        }

        NonOrientedPath path = new NonOrientedPath(beg, end, distance);
        paths.add(path);
        return path;
    }

    @Override
    public IPath createPath(IVertex beg, IVertex end) {
        NonOrientedPath path = new NonOrientedPath(beg, end);
        paths.add(path);
        return path;
    }

    public double[][] getAdjacencyMatrix() {
        double[][] adjacencyMatrix = new double[vertexes.size()][vertexes.size()];

        for (NonOrientedPath path : paths) {
            adjacencyMatrix[vertexes.indexOf(path.getBegin())][vertexes.indexOf(path.getEnd())] = path.getDistance();
        }

        return adjacencyMatrix;
    }

    private final ArrayList<StaticVertex>    vertexes;
    private final ArrayList<NonOrientedPath> paths;
}
