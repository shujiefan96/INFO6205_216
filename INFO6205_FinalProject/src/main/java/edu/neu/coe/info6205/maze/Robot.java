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
 * A robot abstraction. Give it a maze and an instruction set, and it will
 * attempt to navigate to the finish.
 *
 */
public class Robot {

    private enum moveDirection {NORTH, EAST, SOUTH, WEST}; 
    private int xPos;
    private int yPos;
    private moveDirection headingDirection = moveDirection.EAST;
    int maxMoves;
    int moveCount = 0;
    private int sensorVal = -1;
    private final int sensorActions[];
    private Maze maze;
    private ArrayList<int[]> route = new ArrayList<int[]>();
 
    
    /**
     * Initalize robot
     * 
     * @param sensorActions The string to map the sensor value to actions
     * @param maze The maze the robot will use
     * @param maxMoves The maximum number of moves the robot can make
     */  
    public Robot(int[] sensorActionsBS, Maze maze, int maxMoves){
        this.sensorActions = this.calcSensorActions(sensorActionsBS);
        this.maze = maze;        
        this.maxMoves = maxMoves;
        
        int startPos[] = this.maze.getStartPosition();
        int endPos[] = this.maze.getEndPosition();
        this.xPos = startPos[0];
        this.yPos = startPos[1];
        this.route.add(startPos);        
    }
    
    /**
     * Robot moves simulation
     */
    public void simulation(){
        while(true){            
            //count robot moves
            this.moveCount++;
            
            //break if the robot stops moving
            if (this.getNextAction() == 0) {
                return;
            }

            //break if we reach the maze exit
            if (this.maze.getPositionValue(this.xPos, this.yPos) == 4) {
                return;
            }
                      
            //break if we reach a maximum number of moves
            if (this.moveCount > this.maxMoves) {
                return;
            }
            
            //run action
            this.runNextAction();
        }       
    }
    
    /**
     * Map robot's sensor data to actions from binary string
     * 
     * @param sensorActionsBS Binary GA chromosome
     * @return int[] An array to map sensor value to an action
     */
    private int[] calcSensorActions(int[] sensorActionsBS){
        int numActions = (int) sensorActionsBS.length / 2;
        int sensorActions[] = new int[numActions];
        
        // Loop through actions
        for (int action = 0; action < numActions; action++){
            // Get sensor action
            int sensorAction = 0;
            if (sensorActionsBS[action*2] == 1){                
                sensorAction += 2;
            }
            if (sensorActionsBS[(action*2)+1] == 1){
                sensorAction += 1;
            }
            // Add to sensor-action array
            sensorActions[action] = sensorAction;
        }
      
        return sensorActions;
    }
    
    /**
     * Get Sensor Actions
     * 
     * @return int[] An array to store sensor actions
     */
    public int[] getSensorActions(){
        return this.sensorActions;
    }
       
    /**
     * Set Robot Heading Direction
     */
    public void setHeadingDir(int a){  
        if(a == 0){
            this.headingDirection = moveDirection.EAST;
        }else if(a == 1){
            this.headingDirection = moveDirection.NORTH;
        }else if(a == 2){
            this.headingDirection = moveDirection.SOUTH;
        }else if(a == 3){
            this.headingDirection = moveDirection.WEST;
        }          
    }
    
    /**
     * Runs the next action
     *  
     * @return int[] An array to store next move
     */
    public int[] runNextAction(){
        //if move forward
        if (this.getNextAction() == 1) {
            int currentX = this.xPos;
            int currentY = this.yPos;
            
            //move depending on current direction
            if (this.getHeadingDir() == moveDirection.NORTH) {
                this.yPos += -1;
                if (this.yPos < 0) {
                    this.yPos = 0;
                }
            }
            else if (this.getHeadingDir() == moveDirection.EAST) {
                this.xPos += 1;
                if (this.xPos > this.maze.getMaxX()) {
                    this.xPos = this.maze.getMaxX();
                }
            }
            else if (this.getHeadingDir() == moveDirection.SOUTH) {
                this.yPos += 1;
                if (this.yPos > this.maze.getMaxY()) {
                    this.yPos = this.maze.getMaxY();
                }
            }
            else if (this.getHeadingDir() == moveDirection.WEST) {
                this.xPos += -1;
                if (this.xPos < 0) {
                    this.xPos = 0;
                }
            }
            
            //if there is a wall
            if (this.maze.isWall(this.xPos, this.yPos) == true) {
                this.xPos = currentX;
                this.yPos = currentY;
            } 
            else {
                if(currentX != this.xPos || currentY != this.yPos) {
                    this.route.add(this.getPosition());
                }
            }
        }
        
        
        //if move clockwise
        else if(this.getNextAction() == 2) {
            if (this.getHeadingDir() == moveDirection.NORTH) {
                this.setHeadingDir(0);
            }
            else if (this.getHeadingDir() == moveDirection.EAST) {
                this.setHeadingDir(2);
            }
            else if (this.getHeadingDir() == moveDirection.SOUTH) {
                this.setHeadingDir(3);
            }
            else if (this.getHeadingDir() == moveDirection.WEST) {
                this.setHeadingDir(1);
            }
        }
        
        
        //if move anti-clockwise
        else if(this.getNextAction() == 3) {
            if (this.getHeadingDir() == moveDirection.NORTH) {
                this.setHeadingDir(3);
            }
            else if (this.getHeadingDir() == moveDirection.EAST) {
                this.setHeadingDir(1);
            }
            else if (this.getHeadingDir() == moveDirection.SOUTH) {
                this.setHeadingDir(0);
            }
            else if (this.getHeadingDir() == moveDirection.WEST) {
                this.setHeadingDir(2);
            }
        }
        
        //reset sensor value
        this.sensorVal = -1;
        
        //return next position
        return new int[]{this.xPos, this.yPos}; 
    }
    
    /**
     * Get next action depending on sensor value
     * 
     * @return int Next action
     */
    public int getNextAction() {
        return this.sensorActions[this.getSensorValue()];
    }
    
    /**
     * Set next action
     * 
     * @param i action
     */
    public void setNextAction(int i){
         this.sensorActions[this.getSensorValue()] = i;
    }
    
    
    
    
    /**
     * Calculate sensor value
     * 
     * @return int Next sensor value
     */
    public int getSensorValue(){
        // If sensor value has already been calculated
        if (this.sensorVal > -1) {
            return this.sensorVal;
        }
                
        boolean frontSensor = false, frontLeftSensor = false, frontRightSensor = false, 
                leftSensor = false, rightSensor = false, backSensor = false;

        // Find which sensors have been activated
        if (this.getHeadingDir() == moveDirection.NORTH) {
            frontSensor = this.maze.isWall(this.xPos, this.yPos-1);
            frontLeftSensor = this.maze.isWall(this.xPos-1, this.yPos-1);
            frontRightSensor = this.maze.isWall(this.xPos+1, this.yPos-1);
            leftSensor = this.maze.isWall(this.xPos-1, this.yPos);
            rightSensor = this.maze.isWall(this.xPos+1, this.yPos);
            backSensor = this.maze.isWall(this.xPos, this.yPos+1);
        }
        else if (this.getHeadingDir() == moveDirection.EAST) {
            frontSensor = this.maze.isWall(this.xPos+1, this.yPos);
            frontLeftSensor = this.maze.isWall(this.xPos+1, this.yPos-1);
            frontRightSensor = this.maze.isWall(this.xPos+1, this.yPos+1);
            leftSensor = this.maze.isWall(this.xPos, this.yPos-1);
            rightSensor = this.maze.isWall(this.xPos, this.yPos+1);
            backSensor = this.maze.isWall(this.xPos-1, this.yPos);
        }
        else if (this.getHeadingDir() == moveDirection.SOUTH) {
            frontSensor = this.maze.isWall(this.xPos, this.yPos+1);
            frontLeftSensor = this.maze.isWall(this.xPos+1, this.yPos+1);
            frontRightSensor = this.maze.isWall(this.xPos-1, this.yPos+1);
            leftSensor = this.maze.isWall(this.xPos+1, this.yPos);
            rightSensor = this.maze.isWall(this.xPos-1, this.yPos);
            backSensor = this.maze.isWall(this.xPos, this.yPos-1);
        }
        else {
            frontSensor = this.maze.isWall(this.xPos-1, this.yPos);
            frontLeftSensor = this.maze.isWall(this.xPos-1, this.yPos+1);
            frontRightSensor = this.maze.isWall(this.xPos-1, this.yPos-1);
            leftSensor = this.maze.isWall(this.xPos, this.yPos+1);
            rightSensor = this.maze.isWall(this.xPos, this.yPos-1);
            backSensor = this.maze.isWall(this.xPos+1, this.yPos);
        }
                
        // Calculate sensor value
        int sensorValue = 0;
        
        if (frontSensor == true) {
            sensorValue += 1;
        }
        if (frontLeftSensor == true) {
            sensorValue += 2;
        }
        if (frontRightSensor == true) {
            sensorValue += 4;
        }
        if (leftSensor == true) {
            sensorValue += 8;
        }
        if (rightSensor == true) {
            sensorValue += 16;
        }
        if (backSensor == true) {
            sensorValue += 32;
        }

        this.sensorVal = sensorValue;
//        System.out.println("sensorValue " + sensorVal);

        return sensorVal;
    }
    
    /**
     * Get robot's position
     * 
     * @return int[] Array with robot's position
     */
    public int[] getPosition(){
        return new int[]{this.xPos, this.yPos};
    }
    
    /**
     * Set robot's position
     * 
     * @param x, y
     */
    public void setPosition(int x,int y){
        this.xPos = x;
        this.yPos = y;
    }
    
    /**
     * Get robot's heading direction
     * 
     * @return Direction Robot's heading
     */
    public moveDirection getHeadingDir(){
        return this.headingDirection;
    }
    
    /**
     * Get robot's heading direction string
     * 
     * @return String of direction Robot's heading
     */
    public String getHeadingDirString(){
        return String.valueOf(this.headingDirection);
    }
    
   
    
    
    
    /**
     * Returns robot's complete route around the maze
     * 
     * @return ArrayList<int[]> Robot's route
     */
    public ArrayList<int[]> getRoute(){       
        return this.route;
    }
    
    /**
     * Returns route in printable format
     * 
     * @return String Robot's route
     */
    public String printRoute(){
        String route = "";
        
        for (int[] routeStep : this.route) {
            route += "{" + routeStep[0] + "," + routeStep[1] + "}";
        }
        
        return route;
    }
    
}
