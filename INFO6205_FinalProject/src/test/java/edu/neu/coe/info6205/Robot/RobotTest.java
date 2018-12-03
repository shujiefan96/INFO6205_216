/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.Robot;

import apple.laf.JRSUIConstants;
import edu.neu.coe.info6205.maze.GeneticAlgorithm;
import edu.neu.coe.info6205.maze.Individual;
import edu.neu.coe.info6205.maze.Maze;
import edu.neu.coe.info6205.maze.Population;
import edu.neu.coe.info6205.maze.Robot;
import static edu.neu.coe.info6205.maze.RobotController.maxGenerations;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author yangjing
 */
public class RobotTest {
    private enum Direction {NORTH, EAST, SOUTH, WEST};
    private int maze[][];
    int[][] m = new int[][] { 
                { 0, 0, 0, 0, 1, 0, 1, 3, 2 }, 
                { 1, 0, 1, 1, 1, 0, 1, 3, 1 },
                { 1, 0, 0, 1, 3, 3, 3, 3, 1 }, 
                { 3, 3, 3, 1, 3, 1, 1, 0, 1 }, 
                { 3, 1, 3, 3, 3, 1, 1, 0, 0 },
                { 3, 3, 1, 1, 1, 1, 0, 1, 1 }, 
                { 1, 3, 0, 1, 3, 3, 3, 3, 3 }, 
                { 0, 3, 1, 1, 3, 1, 0, 1, 3 },
                { 1, 3, 3, 3, 3, 1, 1, 1, 4 } 
        };
    
    @Test
    public void testStartPosition(){
        Maze a = new Maze(m);
        GeneticAlgorithm ga = new GeneticAlgorithm(200, 0.05, 0.9, 2, 10);
        Population population = ga.initPopulation(128);        
        Individual individual= population.getFittest(0);
        int[] chromosome = individual.getChromosome();
        Robot robot = new Robot(chromosome, a, 100);
        robot.run();
        System.out.println(robot.getRoute().get(0)[0]);
        System.out.println(robot.getPosition()[0]);       
        Assert.assertTrue(robot.getRoute().get(0)[0]==8);
        Assert.assertTrue(robot.getRoute().get(0)[1]==0);
    }
    
    
    @Test
    public void testmakeNextAction(){
        Maze a = new Maze(m);
       int[] chromosome = new int[128];
       Robot r = new Robot(chromosome,a,100);
       
       int currentX=8;
       int currentY=0;
       r.setPosition(currentX, currentY);
       r.setHeading(0);
       r.setNextAction(1);
       Assert.assertTrue(r.makeNextAction1()[0]==8);
       Assert.assertTrue(r.makeNextAction1()[1]==0);
       
       
       r.setPosition(currentX, currentY);
       r.setHeading(1);
       r.setNextAction(1);

       Assert.assertTrue(r.makeNextAction1()[0]==8);
       Assert.assertTrue(r.makeNextAction1()[1]==0);
       
       r.setPosition(currentX, currentY);
       r.setHeading(2);
       r.setNextAction(1);
       Assert.assertTrue(r.makeNextAction1()[0]==8);
       Assert.assertTrue(r.makeNextAction1()[1]==0);
       
       r.setPosition(8, 0);
       r.setHeading(3);
       r.setNextAction(1);
       Assert.assertTrue(r.makeNextAction1()[0]==7);
       Assert.assertTrue(r.makeNextAction1()[1]==0);
    }
    
    @Test
    public void testClockwise(){
       Maze a = new Maze(m);
       int[] chromosome = new int[128];
       Robot r = new Robot(chromosome,a,100);
       
       int currentX=8;
       int currentY=0;
       r.setPosition(currentX, currentY);
       r.setHeading(0);
       r.setNextAction(2);
       r.makeNextAction();
       Assert.assertTrue(r.getHeadingString().equals("SOUTH")); 
       

       r.setPosition(currentX, currentY);
       r.setHeading(1);
       r.setNextAction(2);
       r.makeNextAction();
       Assert.assertTrue(r.getHeadingString().equals("EAST"));
       
       r.setPosition(currentX, currentY);
       r.setHeading(2);
       r.setNextAction(2);
       r.makeNextAction();
       Assert.assertTrue(r.getHeadingString().equals("WEST"));
       
       r.setPosition(currentX, currentY);
       r.setHeading(3);
       r.setNextAction(2);
       r.makeNextAction();
       Assert.assertTrue(r.getHeadingString().equals("NORTH"));
    }
    
    
    @Test
    public void testAntiClockwise(){
       Maze a = new Maze(m);
       int[] chromosome = new int[128];
       Robot r = new Robot(chromosome,a,100);
       
       int currentX=8;
       int currentY=0;
       r.setPosition(currentX, currentY);
       r.setHeading(0);
       r.setNextAction(3);
       r.makeNextAction();
       System.out.println(r.getHeading());
       Assert.assertTrue(r.getHeadingString().equals("NORTH"));
       
       
       r.setPosition(currentX, currentY);
       r.setHeading(1);
       r.setNextAction(3);
       r.makeNextAction();
       System.out.println(r.getHeading());
       Assert.assertTrue(r.getHeadingString().equals("WEST"));
       
       
       r.setPosition(currentX, currentY);
       r.setHeading(2);
       r.setNextAction(3);
       r.makeNextAction();
       System.out.println(r.getHeading());
       Assert.assertTrue(r.getHeadingString().equals("EAST"));
       
       
       r.setPosition(currentX, currentY);
       r.setHeading(3);
       r.setNextAction(3);
       r.makeNextAction();
       System.out.println(r.getHeading());
       Assert.assertTrue(r.getHeadingString().equals("SOUTH"));
    }
}
