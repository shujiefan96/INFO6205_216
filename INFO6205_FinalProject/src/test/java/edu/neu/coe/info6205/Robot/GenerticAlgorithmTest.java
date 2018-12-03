/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.Robot;
import edu.neu.coe.info6205.maze.GeneticAlgorithm;
import edu.neu.coe.info6205.maze.Individual;
import edu.neu.coe.info6205.maze.Maze;
import edu.neu.coe.info6205.maze.Population;
import edu.neu.coe.info6205.maze.Robot;
import static edu.neu.coe.info6205.maze.RobotController.maxGenerations;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author yangjing
 */

public class GenerticAlgorithmTest {
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
    public void testGenerationInitilization(){
        Maze a = new Maze(m);
        GeneticAlgorithm ga = new GeneticAlgorithm(200, 0.05, 0.9, 2, 10);
        Population population = ga.initPopulation(128);
        int generation = 1;
        while (ga.isTerminationConditionMet(generation, maxGenerations) == false){          
            Individual individual= population.getFittest(0);
            int[] chromosome = individual.getChromosome();
            Robot robot = new Robot(chromosome, a, 100);
            robot.simulation();
            int fitness = a.scoreRoute(robot.getRoute());
            Assert.assertTrue(fitness<29);
            generation++;
        }
    }
    
    @Test
    public void testGenerationFitness(){
        Maze a = new Maze(m);
        GeneticAlgorithm ga = new GeneticAlgorithm(200, 0.05, 0.9, 2, 10);
        Population population = ga.initPopulation(128);
        ga.evalPopulation(population, a);
        double fitness= population.getPopulationFitness();
        
        Individual individual=population.getFittest(0);
        population = ga.crossoverPopulation(population);
        // Apply mutation
        population = ga.mutatePopulation(population);
        // Evaluate population
        ga.evalPopulation(population, a);
        double fitnessAfterGA = population.getPopulationFitness();
        System.out.println(fitness + " " + fitnessAfterGA);
        Assert.assertTrue(fitness<fitnessAfterGA);    
    }
    
}
