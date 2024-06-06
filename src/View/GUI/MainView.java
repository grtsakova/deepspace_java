/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GUI;

import View.DeepSpaceView;
import controller.Controller;
import deepspace.EnemyToUI;
import deepspace.GameState;
import deepspace.SpaceStationToUI;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author gerga
 */
public class MainView extends javax.swing.JFrame implements DeepSpaceView {

    private static MainView instance = null;
    
    private String appName = "Gero's Deepspace";
    
    private SpaceStationView spaceStationVista;
    private EnemyView enemyVista;
    
    public static MainView getInstance(){
        if (instance == null){
            instance = new MainView();
        }
        return instance;
    }

    private MainView() {
        initComponents();
        
        setTitle(appName);
    
        spaceStationVista = new SpaceStationView();
        panelSpaceStation.add(spaceStationVista);
        
        enemyVista = new EnemyView();
        panelEnemy.add(enemyVista);
        
        
        setLocationRelativeTo (null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                Controller.getInstance().finish(0);
            }
        });
        repaint();
    }
    
    @Override
    public void updateView(){
        SpaceStationToUI spaceStation = Controller.getInstance().getUIversion().getCurrentStation();
        spaceStationVista.setSpaceStation(spaceStation);
        
        EnemyToUI enemy = Controller.getInstance().getUIversion().getCurrentEnemy();
        enemyVista.setEnemy(enemy);
        
        //enemy shows only after combat
        enemyVista.setVisible(Controller.getInstance().getState()==GameState.AFTERCOMBAT);
        
        buttonCombatir.setEnabled((Controller.getInstance().getState()==GameState.INIT) || (Controller.getInstance().getState()==GameState.BEFORECOMBAT));
        buttonSiguiente.setEnabled(Controller.getInstance().getState()==GameState.AFTERCOMBAT);
        
        
//        EnemyToUI weapon = Controller.getInstance().dameUnWeaponPrueba();
//        DamageView vistaWeapon = new DamageView();
//        vistaWeapon.setDamage(weapon.getDamage());
//        panelPruebas.add(vistaWeapon);

//        LootView vistaWeapon = new LootView();
//        vistaWeapon.setLoot(weapon.getLoot());
//        EnemyView vistaWeapon = new EnemyView();
//        vistaWeapon.setEnemy(weapon);
//        panelEnemy.add(vistaWeapon);
//        repaint(); 
//        revalidate();
        
        repaint();
        revalidate();
       
        
    }
    
    String getAppName(){
        return appName;
    }
    
    @Override
    public void showView(){
        this.setVisible(true);
    }
    
    @Override
    public ArrayList<String> readNamePlayers(){
        NamesCapture namesCapt = new NamesCapture(this);
        return namesCapt.readNamePlayers();
    }
    
    @Override
    public boolean confirmExitMessage(){
       return (JOptionPane.showConfirmDialog(this, "¿Estás segur@ que deseas salir?", getAppName(), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    }
    
    @Override
    public void nextTurnNotAllowedMessage(){
       JOptionPane.showMessageDialog(this, "No puedes avanzar de turno, no has cumplido tu castigo", getAppName(), JOptionPane.OK_OPTION);
    }
    
    @Override
    public void lostCombatMessage(){
        JOptionPane.showMessageDialog(this, "Has PERDIDO el combate. Cumple tu castigo.", getAppName(), JOptionPane.OK_OPTION);
    }
    
    @Override
    public void escapeMessage(){
        JOptionPane.showMessageDialog(this, "Has logrado escapar. Eres una Gallina Espacial.", getAppName(), JOptionPane.OK_OPTION);
    }
    
    @Override
    public void wonCombatMessage(){
      JOptionPane.showMessageDialog(this, "Has GANADO el combate. Disfruta de tu botín.", getAppName(), JOptionPane.OK_OPTION);
    }
    
    @Override
    public void wonCombatAndConvertedMessage(){
        JOptionPane.showMessageDialog(this, "Te has convertido!", getAppName(), JOptionPane.OK_OPTION);
    }
    
    @Override
    public void wonGameMessage(){
        JOptionPane.showMessageDialog(this, "HAS GANADO LA PARTIDA", getAppName(), JOptionPane.OK_OPTION);
    }
    
    @Override
    public void conversionMessage(){
        JOptionPane.showMessageDialog(this, "Has GANADO el combate. Además te has CONVERTIDO. Disfruta de tu botín", getAppName(), JOptionPane.OK_OPTION);
    }
    
    @Override
    public void noCombatMessage(){
        JOptionPane.showMessageDialog(this, "No puedes combatir en este momento", getAppName(), JOptionPane.OK_OPTION);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelSpaceStation = new javax.swing.JPanel();
        panelEnemy = new javax.swing.JPanel();
        butttonSalir = new javax.swing.JButton();
        buttonSiguiente = new javax.swing.JButton();
        buttonCombatir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        butttonSalir.setText("Salir");
        butttonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butttonSalirActionPerformed(evt);
            }
        });

        buttonSiguiente.setText("Siguiente turno");
        buttonSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSiguienteActionPerformed(evt);
            }
        });

        buttonCombatir.setText("COMBATIR");
        buttonCombatir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCombatirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelSpaceStation, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(butttonSalir))
                    .addComponent(panelEnemy, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(buttonSiguiente, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(buttonCombatir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelSpaceStation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelEnemy, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                        .addComponent(buttonCombatir, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonSiguiente)
                        .addGap(29, 29, 29)
                        .addComponent(butttonSalir)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSiguienteActionPerformed
        Controller.getInstance().nextTurn();
        updateView();
    }//GEN-LAST:event_buttonSiguienteActionPerformed

    private void buttonCombatirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCombatirActionPerformed
        Controller.getInstance().combat();
        updateView();
    }//GEN-LAST:event_buttonCombatirActionPerformed

    private void butttonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butttonSalirActionPerformed
        Controller.getInstance().finish(0);
    }//GEN-LAST:event_butttonSalirActionPerformed

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCombatir;
    private javax.swing.JButton buttonSiguiente;
    private javax.swing.JButton butttonSalir;
    private javax.swing.JPanel panelEnemy;
    private javax.swing.JPanel panelSpaceStation;
    // End of variables declaration//GEN-END:variables
}
