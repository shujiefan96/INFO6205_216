/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.maze;

/**
 *
 * @author yangjing
 */
import java.util.ArrayList;

/**
 * This class abstracts a maze through which a robot will have to navigate. The
 * maze is represented as a 2d array of integers, with different environment
 * types represented by integers as follows:
 * 
 * 0 = Empty 
 * 1 = Wall 
 * 2 = Starting position 
 * 3 = Route 
 * 4 = Goal position
 * 
 * The most significant method in this class is `scoreRoute`, which will return
 * a fitness score for a path; it is this score that the genetic algorithm will
 * optimize.
 * 
 *
 */
public class Maze {
    private final int maze[][];
    private int startPosition[] = { -1, -1 };
    private int endPosition[] = { -1, -1 };

    public Maze(int maze[][]) {
        this.maze = maze;
    }

    /**
     * Get start position of maze
     * 
     * @return int[] x,y start position of maze
     */
    public int[] getStartPosition() {
        // Check we already found start position
        if (this.startPosition[0] != -1 && this.startPosition[1] != -1) {
            return this.startPosition;
        }

        // Default return value
        int startPosition[] = { 0, 0 };

        // Loop over rows
        for (int rowIndex = 0; rowIndex < this.maze.length; rowIndex++) {
            // Loop over columns
            for (int colIndex = 0; colIndex < this.maze[rowIndex].length; colIndex++) {
                // 2 is the type for start position
                if (this.maze[rowIndex][colIndex] == 2) {
                    this.startPosition = new int[] { colIndex, rowIndex };
                    return new int[] { colIndex, rowIndex };
                }
            }
        }

        return startPosition;
    }

    /**
     * Get start position of maze
     * 
     * @return int[] x,y start position of maze
     */
    public int[] getEndPosition() {
        // Check we already found start position
        if (this.endPosition[0] != -1 && this.endPosition[1] != -1) {
            return this.endPosition;
        }

        // Default return value
        int endPosition[] = { 0, 0 };

        // Loop over rows
        for (int rowIndex = 0; rowIndex < this.maze.length; rowIndex++) {
            // Loop over columns
            for (int colIndex = 0; colIndex < this.maze[rowIndex].length; colIndex++) {
                // 4 is the type for end position
                if (this.maze[rowIndex][colIndex] == 4) {
                    this.endPosition = new int[] { colIndex, rowIndex };
                    return new int[] { colIndex, rowIndex };
                }
            }
        }

        return endPosition;
    }

    /**
     * Gets value for position of maze
     * 
     * @param x
     *            position
     * @param y
     *            position
     * @return int Position value
     */
    public int getPositionValue(int x, int y) {
            if (x < 0 || y < 0 || x >= this.maze[0].length || y >= this.maze.length) {
                    return 1;
            }
            return this.maze[y][x];
    }

    /**
     * Check if position is wall
     * 
     * @param x
     *            position
     * @param y
     *            position
     * @return boolean
     */
    public boolean isWall(int x, int y) {
            return (this.getPositionValue(x, y) == 1);
    }

    /**
     * Gets maximum index of x position
     * 
     * @return int Max index
     */
    public int getMaxX() {
            return this.maze[0].length - 1;
    }

    /**
     * Gets maximum index of y position
     * 
     * @return int Max index
     */
    public int getMaxY() {
            return this.maze.length - 1;
    }

    /**
     * Scores a maze route
     * 
     * This method inspects a route given as an array, and adds a point for each
     * correct step made. We also have to be careful not to reward re-visiting
     * correct paths, otherwise you could get an infinite score just by wiggling
     * back and forth on the route.
     * 
     * @return int Max index
     */
    public int scoreRoute(ArrayList<int[]> route) {
            int score = 0;
            boolean visited[][] = new boolean[this.getMaxY() + 1][this.getMaxX() + 1];

            // Loop over route and score each move
            for (Object routeStep : route) {
                    int step[] = (int[]) routeStep;
                    if (this.maze[step[1]][step[0]] == 3 && visited[step[1]][step[0]] == false) {
                            // Increase score for correct move
                            score++;
                            // Remove reward
                            visited[step[1]][step[0]] = true;
//                                System.out.println("step[1]: " + step[1] + " ,step[0]: " + step[0]);
                    }
            }

            return score;
    }
        
        
    //return total steps
    public int getSteps(){
        int steps = 0;
        for (int[] routeStep : this.maze) {
            for(int i : routeStep){
                if (i == 3 || i == 2 || i == 4) {
                    steps++;
                }
            }                
        }
        return steps;
    }
        
     
    //return actual route
    public String getActualRoute(){
        String routeString = "";
        
        // Loop over rows
        for (int rowIndex = 0; rowIndex < this.maze.length; rowIndex++) {
            // Loop over columns
            for (int colIndex = 0; colIndex < this.maze[rowIndex].length; colIndex++) {
                if (this.maze[rowIndex][colIndex] == 2 || this.maze[rowIndex][colIndex] == 3 || this.maze[rowIndex][colIndex] == 4) {
                    routeString += "{" + colIndex + "," + rowIndex + "}";
                }
            }
        }       
        return routeString;
    }
}
