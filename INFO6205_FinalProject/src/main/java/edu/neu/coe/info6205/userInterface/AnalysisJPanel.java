/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.userInterface;

import java.awt.CardLayout;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author fanshujie
 */
public class AnalysisJPanel extends javax.swing.JPanel {

    /**
     * Creates new form AnalysisJPanel
     */
    JPanel CardSequenceJPanel;
    private HashMap<String, int[][]> maze;
    
    public AnalysisJPanel(JPanel CardSequenceJPanel, HashMap<String, int[][]> maze) {
        initComponents();
        this.CardSequenceJPanel = CardSequenceJPanel;
        this.maze = maze;
        
        mazejComboBox.removeAllItems();
        for(String s : maze.keySet()){
            mazejComboBox.addItem(s);
        }
    }
    
    private boolean validation(String generation, String times, String popu, String muta, String cross, String eli, String tour){
        Pattern p1 = Pattern.compile("^[0-9]+$");
        Matcher m1 = p1.matcher(generation);
        boolean flag1 = m1.matches();
        if(!flag1){
            JOptionPane.showMessageDialog(null, "Generations can only be integers!");
        }    
        Matcher m2 = p1.matcher(times);
        boolean flag2 = m2.matches();
        if(!flag2){
            JOptionPane.showMessageDialog(null, "Running Times can only be integers!");
        } 
        Matcher m3 = p1.matcher(popu);
        boolean flag3 = m3.matches();
        if(!flag3){
            JOptionPane.showMessageDialog(null, "Population Size can only be integers!");
        } 
        Matcher m4 = p1.matcher(eli);
        boolean flag4 = m4.matches();
        if(!flag4){
            JOptionPane.showMessageDialog(null, "Elitism Size can only be integers!");
        } 
        Matcher m5 = p1.matcher(tour);
        boolean flag5 = m5.matches();
        if(!flag5){
            JOptionPane.showMessageDialog(null, "Tournament Size can only be integers!");
        } 
        Pattern p2 = Pattern.compile("^[0-9]+.?[0-9]*$");
        Matcher m6 = p2.matcher(muta);
        boolean flag6 = m6.matches();
        if(!flag6){
            JOptionPane.showMessageDialog(null, "Mutation Rate can only be double!");
        }  
        Matcher m7 = p2.matcher(cross);
        boolean flag7 = m7.matches();
        if(!flag7){
            JOptionPane.showMessageDialog(null, "Crossover Rate can only be double!");
        }  
        return flag1&&flag2&&flag3&&flag4&&flag5&&flag6&&flag7;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        timeJText = new javax.swing.JTextField();
        mazejComboBox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        maxGeneJText = new javax.swing.JTextField();
        AnaBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        populationJText = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        mutaRateJText = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        crossoverRateJText = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        eliJText = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tourJText = new javax.swing.JTextField();

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel1.setText("Analysis Based on which maze:");

        backBtn.setText("<<Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel2.setText("Running Times:");

        mazejComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel3.setText("Maximum Generations:");

        AnaBtn.setText("Analysis>>");
        AnaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnaBtnActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel4.setText("Population Size:");

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel5.setText("Mutation Rate:");

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel6.setText("Crossover Rate:");

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel7.setText("Elitism Size:");

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel8.setText("Tournament Size:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(maxGeneJText, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timeJText, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mazejComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addGap(159, 159, 159)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(populationJText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mutaRateJText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(crossoverRateJText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(eliJText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tourJText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(156, 156, 156))
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(backBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(AnaBtn)
                .addGap(80, 80, 80))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(populationJText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(mutaRateJText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(crossoverRateJText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(eliJText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tourJText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(maxGeneJText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(timeJText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(mazejComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backBtn)
                    .addComponent(AnaBtn))
                .addGap(60, 60, 60))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
        CardSequenceJPanel.remove(this);
        CardLayout cardLayout = (CardLayout) CardSequenceJPanel.getLayout();
        cardLayout.previous(CardSequenceJPanel);
    }//GEN-LAST:event_backBtnActionPerformed

    private void AnaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnaBtnActionPerformed
        // TODO add your handling code here:
        if(maxGeneJText.getText().isEmpty() || timeJText.getText().isEmpty() || populationJText.getText().isEmpty() 
                || mutaRateJText.getText().isEmpty() || crossoverRateJText.getText().isEmpty() 
                || eliJText.getText().isEmpty() || tourJText.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Input can not be null!");
            return;
        }
        String maxGenes = maxGeneJText.getText();
        String runTimes = timeJText.getText();
        String popu = populationJText.getText();
        String muta = mutaRateJText.getText();
        String cross = crossoverRateJText.getText();
        String eli = eliJText.getText();
        String tour = tourJText.getText();
        if(!validation(maxGenes, runTimes, popu, muta, cross, eli, tour)){
            return;
        }
        
        int maxGeneraions = Integer.parseInt(maxGenes);
        int times = Integer.parseInt(runTimes);
        String mazeName = String.valueOf(mazejComboBox.getSelectedItem());
        int populationSize = Integer.parseInt(popu);
        int elitismSize = Integer.parseInt(eli);
        int tournamentSize = Integer.parseInt(tour);
        double mutationRate = Double.parseDouble(muta);
        double crossoverRate = Double.parseDouble(cross);
        int[][] mazeSelected = maze.get(mazeName);
        
        if(maxGeneraions <= 0 || times <= 0 || populationSize <= 0 || elitismSize <= 0 || tournamentSize <= 0){
            JOptionPane.showMessageDialog(null, "MaxGeneraions or times or population size or elitism size or tournament size should be >0!");
            return;
        }
        
        if(mutationRate < 0 || mutationRate > 1 || crossoverRate < 0 || crossoverRate > 1 ){
            JOptionPane.showMessageDialog(null, "Mutation rate or crossover rate should be in [0,1]!");
            return;
        }
        
        ResultJPanel panel = new ResultJPanel(CardSequenceJPanel, maxGeneraions, times, mazeName, mazeSelected, populationSize,
                                              mutationRate, crossoverRate, elitismSize, tournamentSize);
        CardSequenceJPanel.add("ResultScreen", panel);
        CardLayout cardlayout = (CardLayout) CardSequenceJPanel.getLayout();
        cardlayout.next(CardSequenceJPanel);
    }//GEN-LAST:event_AnaBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AnaBtn;
    private javax.swing.JButton backBtn;
    private javax.swing.JTextField crossoverRateJText;
    private javax.swing.JTextField eliJText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField maxGeneJText;
    private javax.swing.JComboBox<String> mazejComboBox;
    private javax.swing.JTextField mutaRateJText;
    private javax.swing.JTextField populationJText;
    private javax.swing.JTextField timeJText;
    private javax.swing.JTextField tourJText;
    // End of variables declaration//GEN-END:variables
}