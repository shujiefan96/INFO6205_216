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
 * 4 = Ending position
 *
 */
public class Maze {
    private final int maze[][];
    private int[] startPosition = { -1, -1 };
    private int[] endPosition = { -1, -1 };

    public Maze(int maze[][]) {
        this.maze = maze;
    }

    /**
     * Get start position of the maze
     * 
     * @return int[] x,y start position of the maze
     */
    public int[] getStartPosition() {
        // Check if we've already found start position
        if (this.startPosition[0] != -1 && this.startPosition[1] != -1) {
            return this.startPosition;
        }

        // Default return value
        this.startPosition = new int[] { 0, 0 };

        // Loop over rows
        for (int row = 0; row < this.getMaxY() + 1; row++) {
            // Loop over columns
            for (int col = 0; col < this.getMaxX() + 1; col++) {
                // 2 is the type for start position
                if (this.maze[row][col] == 2) {
                    this.startPosition = new int[] { col, row };
                }
            }
        }

        return this.startPosition;
    }

    /**
     * Get end position of the maze
     * 
     * @return int[] x,y end position of the maze
     */
    public int[] getEndPosition() {
        // Check if we've already found start position
        if (this.endPosition[0] != -1 && this.endPosition[1] != -1) {
            return this.endPosition;
        }

        // Default return value
        this.endPosition = new int[] { 0, 0 };

        // Loop over rows
        for (int row = 0; row < this.getMaxY() + 1; row++) {
            // Loop over columns
            for (int col = 0; col < this.getMaxX() + 1; col++) {
                // 2 is the type for start position
                if (this.maze[row][col] == 4) {
                    this.endPosition = new int[] { col, row };
                }
            }
        }

        return this.endPosition;
    }

    /**
     * Gets value of the given position of the maze
     * 
     * @param xPos x position
     * @param yPos y position
     * @return int Position value
     */
    public int getPositionValue(int xPos, int yPos) {
        if (xPos < 0 || yPos < 0 || xPos >= this.getMaxX() + 1 || yPos >= this.getMaxY() + 1) {
            return 1;
        }
        return this.maze[yPos][xPos];
    }

    /**
     * Check if the given position is wall
     * 
     * @param xPos x position
     * @param yPos y position
     * @return boolean if is wall, return true, if not, return false
     */
    public boolean isWall(int xPos, int yPos) {
        return (this.getPositionValue(xPos, yPos) == 1);
    }

    /**
     * Get maximum index of x position
     * 
     * @return int Maximum x index
     */
    public int getMaxX() {
        return this.maze[0].length - 1;
    }

    /**
     * Gets maximum index of y position
     * 
     * @return int Maximum y index
     */
    public int getMaxY() {
        return this.maze.length - 1;
    }

    /**
     * Score a route
     * 
     * @param ArrayList<int[]> route
     * @return int score
     */
    public int getRouteScore(ArrayList<int[]> route) {
        int score = 0;
        boolean visitedFlag[][] = new boolean[this.getMaxY() + 1][this.getMaxX() + 1];
//        System.out.println("rrr: " + printRoute(route));

        // Loop over route and score each move
        for (int[] step : route) {
            if (this.getPositionValue(step[0], step[1]) == 3 && visitedFlag[step[1]][step[0]] == false) {
                // Increase score for correct move
                score++;
                // Remove reward
                visitedFlag[step[1]][step[0]] = true;
//                System.out.println("score++: " + step[0] + " " + step[1]);
            }
            if (this.getPositionValue(step[0], step[1]) == 0) {
                // Increase score for correct move
                score--;
//                System.out.println("score--: " + step[0] + " " + step[1]);
            }
        }
//        System.out.println("score: " + score);

        return score;
    }
        
        
    /**
     * Get the actual total steps to go through the maze
     * 
     * @return int total steps
     */
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
        
     
    /**
     * Get the actual route to go through the maze
     * 
     * @return String String format of the actual route
     */
    public String getActualRoute(){
        String routeString = "";
        
        // Loop over rows
        for (int row = 0; row < this.getMaxY() + 1; row++) {
            // Loop over columns
            for (int col = 0; col < this.getMaxX() + 1; col++) {
                if (this.getPositionValue(col, row) == 2 || this.getPositionValue(col, row) == 3 
                        || this.getPositionValue(col, row) == 4) {
                    routeString += "{" + col + "," + row + "}";
                }
            }
        }       
        return routeString;
    }
    
    
    public String printRoute(ArrayList<int[]> route){
        String routes = "";
        
        for (int[] routeStep : route) {
            routes += "{" + routeStep[0] + "," + routeStep[1] + "}";
        }
        
        return routes;
    }
}
