/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.mazeTest;

import edu.neu.coe.info6205.maze.Maze;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yangjing
 */
public class MazeTest {
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
    public void maze_startPoint() { 
        
        Maze a = new Maze(m);
        for(int i=0;i<m.length;i++){
            for(int j=0;j<m.length;j++){
                Assert.assertTrue(a.getStartPosition()[0]==8);
                Assert.assertTrue(a.getStartPosition()[1]==0);   
            }
        }

    }
    
    @Test
    public void maze_isWall(){
        Maze a = new Maze(m);
//        a.showMaze();
         for(int i=0;i<m.length;i++){
            for(int j=0;j<m.length;j++){
                Assert.assertTrue(a.isWall(6, 0));
                Assert.assertTrue(a.isWall(0, 1));
                Assert.assertTrue(a.isWall(7, 7));
            }
         }
        
    }
    
    @Test
    public void maze_getMaxXAndgetMaxY(){
        Maze a = new Maze(m);
        Assert.assertTrue(a.getMaxX()==8);
        Assert.assertTrue(a.getMaxY()==8);
    }
//    {8,0}{7,0}{7,1}{7,2}{6,2}{5,2}{4,2}{4,3}{4,4}{3,4}{2,4}{2,3}{1,3}{0,3}{0,4}{0,5}{1,5}{1,6}{1,7}{1,8}{2,8}{3,8}{4,8}{4,7}{4,6}{5,6}  25

    @Test
    public void maze_scoreRoute(){
        Maze a = new Maze(m);
        ArrayList<int[]> route= new ArrayList<>();
        Object routeStep = new Object();
        int step1[]=new int[2];
        step1[0]=8;
        step1[1]=0;
        route.add(step1);
        int step2[]=new int[2];
        step2[0]=7;
        step2[1]=0;
        route.add(step2);
        int step3[]=new int[2];
        step3[0]=7;
        step3[1]=1;
        route.add(step3);
        int step4[]=new int[2];
        step4[0]=7;
        step4[1]=2;
        route.add(step4);
        int step5[]=new int[2];
        step5[0]=6;
        step5[1]=2;
        route.add(step5);
        int step6[]=new int[2];
        step6[0]=5;
        step6[1]=2;
        route.add(step6); 
        int step7[]=new int[2];
        step7[0]=4;
        step7[1]=2;
        route.add(step7);  
        int step8[]=new int[2];
        step8[0]=4;
        step8[1]=3;
        route.add(step8); 
        System.out.println(a.scoreRoute(route));
        Assert.assertTrue(a.scoreRoute(route)==7);
    }
}
