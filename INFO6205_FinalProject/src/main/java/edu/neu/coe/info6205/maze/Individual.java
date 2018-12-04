/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.maze;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author yangjing
 */
public class Individual {
    private int[] chromosome;
    private double fitness = -1;
    private ArrayList<int[]> route;
    Random random = new Random();

    /**
     * Initialize individual with specific chromosome
     * 
     * @param chromosome Initialize individual's chromosome to the given size
     */
    public Individual(int[] chromosome) {
        this.chromosome = chromosome;
    }

    /**
     * Initializes random individual.
     * 
     * @param chromosomeLength The length of the individuals chromosome
     */
    public Individual(int chromosomeLength) {
        this.chromosome = new int[chromosomeLength];
        for (int gene = 0; gene < chromosomeLength; gene++) {
            if (random.nextDouble() > 0.5) {
                this.setGene(gene, 1);
            } else {
                this.setGene(gene, 0);
            }
        }
    }

    /**
     * Gets individual's chromosome
     * 
     * @return int[] The individual's chromosome
     */
    public int[] getChromosome() {
        return this.chromosome;
    }

    /**
     * Gets individual's chromosome length
     * 
     * @return int The individual's chromosome length
     */
    public int getChromosomeLength() {
        return this.chromosome.length;
    }

    /**
     * Set gene at the given index
     * 
     * @param gene
     * @param index
     */
    public void setGene(int index, int gene) {
        this.chromosome[index] = gene;
    }

    /**
     * Get gene at the given index
     * 
     * @param index
     * @return int Gene at the given index
     */
    public int getGene(int index) {
        return this.chromosome[index];
    }

    /**
     * Set individual's fitness
     * 
     * @param fitness The individual's fitness
     */
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    /**
     * Get individual's fitness
     * 
     * @return double The individual's fitness
     */
    public double getFitness() {
        return this.fitness;
    }

    /**
     * Get best route
     * 
     * @return ArrayList<int[]> Best route
     */
    public ArrayList<int[]> getRoute() {
        return route;
    }

    /**
     * Set best route
     * 
     * @param route Best route
     */
    public void setRoute(ArrayList<int[]> route) {
        this.route = route;
    }

    /**
     * Display the chromosome as a string.
     * 
     * @return String String format of the chromosome
     */
    public String toString() {
        String chromosomeString = "";
        for (int gene = 0; gene < this.getChromosomeLength(); gene++) {
            chromosomeString += this.chromosome[gene];
        }
        return chromosomeString;
    }
}
