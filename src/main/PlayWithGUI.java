/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import View.DeepSpaceView;
import View.GUI.MainView;
import controller.Controller;
import deepspace.GameUniverse;

/**
 *
 * @author gerga
 */
public class PlayWithGUI {

    public static void main(String[] args) {
      GameUniverse model = new GameUniverse();
      DeepSpaceView view = MainView.getInstance();
      Controller controller = Controller.getInstance();
      controller.setModelView(model,view);
      controller.start();
    }
    
}
