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
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;


public class Population {
    private Individual[] population;
    private double populationFitness = -1;

    /**
     * Initialize population
     * 
     * @param individualNum The number of individuals in the population
     */
    public Population(int individualNum) {
        // Initial population
        this.population = new Individual[individualNum];
    }

    /**
     * Initialize population of individuals
     * 
     * @param individualNum The number of individuals in the population
     * @param chromosomeSize The size of each individual's chromosome
     */
    public Population(int individualNum, int chromosomeSize) {
        // Initialize the population as an array of individuals
        this.population = new Individual[individualNum];

        // Create each individual in turn
        for (int individualCount = 0; individualCount < individualNum; individualCount++) {
            // Create an individual, initializing its chromosome to the given size
            Individual individual = new Individual(chromosomeSize);
            this.population[individualCount] = individual;
        }
    }

    /**
     * Get individuals from the population
     * 
     * @return Individual[] An array of individuals in population
     */
    public Individual[] getIndividuals() {
        return this.population;
    }

    /**
     * Sort individuals in the population by its fitness
     * 
     * @return Individual[] An array of Individuals in the population sorted by its fitness
     */    
    public Individual[] getSortedIndividuals(){
        Arrays.sort(this.population, new Comparator<Individual>() {
            @Override
            public int compare(Individual i1, Individual i2) {
                if (i1.getFitness() > i2.getFitness()) {
                    return -1;
                } else if (i1.getFitness() < i2.getFitness()) {
                    return 1;
                }
                return 0;
            }
        });
        return this.population;
    }
    
    /**
     * Get individual in the population by index
     * 
     * @param index individual index in the sorted population
     * @return Individual Individual at given index
     */
    public Individual getIndividualByFitness(int index){
        Individual[] p = this.getSortedIndividuals();
        return p[index];
    }

    /**
     * Set population's total fitness
     * 
     * @param fitness The population's total fitness
     */
    public void setPopulationFitness(double populationFitness) {
        this.populationFitness = populationFitness;
    }

    /**
     * Get population's total fitness
     * 
     * @return populationFitness The population's total fitness
     */
    public double getPopulationFitness() {
        return this.populationFitness;
    }

    /**
     * Get population's size
     * 
     * @return int The population's size
     */
    public int getSize() {
        return this.population.length;
    }

    /**
     * Set individual at given index
     * 
     * @param individual
     * @param index given index in the population
     */
    public void setIndividual(int index, Individual individual) {
        population[index] = individual;
    }

    /**
     * Get individual at given index
     * 
     * @param index given index
     * @return Individual individual in the population at given index
     */
    public Individual getIndividual(int index) {
        return population[index];
    }

}
