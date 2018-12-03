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
    
//    //11000101010001001010101001011010000101011111111101000110011001111010010111100101110111010110011000010101111001110111010001001110
//    @Test
//    public void testmakeNextAction(){
//        Maze a = new Maze(m);
//       int currentX = 8;
//       int currentY = 0;
////       String chromosomeString="11000101010001001010101001011010000101011111111101000110011001111010010111100101110111010110011000010101111001110111010001001110";
//       int[] chromosome = new int[128];
//       int sensorActions[] = new int[64];
//       Robot r = new Robot(chromosome,a,100);
//       r.setHeading(2);
//       r.makeNextAction();
//       System.out.println(r.getHeading());
//       System.out.println(r.getNextAction());
//       Assert.assertTrue(false);
//    }
        
}