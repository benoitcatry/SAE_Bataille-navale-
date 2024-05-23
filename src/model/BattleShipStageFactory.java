package model;
import boardifier.model.GameElement;
import boardifier.model.GameStageModel;
import boardifier.model.StageElementsFactory;
import boardifier.model.TextElement;

import java.util.Scanner;

public class BattleShipStageFactory extends StageElementsFactory {

    private BattleShipStageModel stageModel;

    public BattleShipStageFactory(GameStageModel gameStageModel) {
        super(gameStageModel);
        stageModel = (BattleShipStageModel) gameStageModel;
    }


    /* setup pour le mode de jeu 1
            - 1 porte-avion de 5 cases,
            - 1 croiseur de 4 cases,
            - 2 contre-torpilleurs de 3 cases,
            - 1 torpilleur de 2 cases.
     */

    private void setupMode1(){
        TextElement textplayer1 = new TextElement(stageModel.getCurrentPlayerName(), stageModel);
        textplayer1.setLocation(0,0);
        stageModel.setPlayer1Name(textplayer1);

        TextElement textplayer2 = new TextElement(stageModel.getCurrentPlayerName(), stageModel);
        textplayer2.setLocation(100,0);
        stageModel.setPlayer2Name(textplayer2);

        BattleBoard boardplayer1 = new BattleBoard(10, 1, stageModel);
        // assign the board to the game stage model
        stageModel.setBoardPlayer1(boardplayer1);

        BattleBoard boardplayer2 = new BattleBoard(100, 1, stageModel);
        // assign the board to the game stage model
        stageModel.setBoardPlayer2(boardplayer2);

        //création des ship et de leur partie
        //ship pour player 1
        Ship[] shipplayer1 = new Ship[5];
        shipplayer1[0] = new Ship(1,1,5,stageModel);
        shipplayer1[1] = new Ship(1,1,4,stageModel);
        shipplayer1[2] = new Ship(1,1,3,stageModel);
        shipplayer1[3] = new Ship(1,1,3,stageModel);
        shipplayer1[4] = new Ship(1,1,2,stageModel);
        stageModel.setShipsPlayer1(shipplayer1);

        //ship pour player 2
        Ship[] shipplayer2 = new Ship[5];
        shipplayer2[0] = new Ship(1,1,5,stageModel);
        shipplayer2[1] = new Ship(1,1,4,stageModel);
        shipplayer2[2] = new Ship(1,1,3,stageModel);
        shipplayer2[3] = new Ship(1,1,3,stageModel);
        shipplayer2[4] = new Ship(1,1,2,stageModel);
        stageModel.setShipsPlayer2(shipplayer2);

        for (int i = 0; i < shipplayer1.length; i++) {
            shipplayer1[i].setShipParts(stageModel);
        }

        for (int i = 0; i < shipplayer2.length; i++) {
            shipplayer2[i].setShipParts(stageModel);
        }

        Misille[] misillePlayer1 = new Misille[50];
        for (int i = 0; i < misillePlayer1.length; i++) {
            misillePlayer1[i]= new Misille(i,1,stageModel);
            }
        stageModel.setMissileJoueur1(misillePlayer1);
        Misille[] misillePlayer2 = new Misille[50];
        for (int i = 0; i < misillePlayer2.length; i++) {
            misillePlayer2[i]= new Misille(i,1,stageModel);
        }
        stageModel.setMissileJoueur2(misillePlayer2);




    }


    //setup mode de jeux 2 :
    //                      -1 cuirassé de 4 cases,
    //                      -2 croiseurs de 3 cases,
    //                      -3 torpilleurs de 2 cases,
    //                      -4 sous-marins de 1 case

    private void setupMode2(){
        TextElement textplayer1 = new TextElement(stageModel.getCurrentPlayerName(), stageModel);
        textplayer1.setLocation(0,0);
        stageModel.setPlayer1Name(textplayer1);

        TextElement textplayer2 = new TextElement(stageModel.getCurrentPlayerName(), stageModel);
        textplayer2.setLocation(100,0);
        stageModel.setPlayer2Name(textplayer2);

        BattleBoard boardplayer1 = new BattleBoard(5, 1, stageModel);
        // assign the board to the game stage model
        stageModel.setBoardPlayer1(boardplayer1);

        BattleBoard boardplayer2 = new BattleBoard(100, 1, stageModel);
        // assign the board to the game stage model
        stageModel.setBoardPlayer2(boardplayer2);

        //création des ship et de leur partie
        //ship pour player 1
        Ship[] shipplayer1 = new Ship[10];
        shipplayer1[0] = new Ship(1,1,4,stageModel);
        shipplayer1[1] = new Ship(1,1,3,stageModel);
        shipplayer1[2] = new Ship(1,1,3,stageModel);
        shipplayer1[3] = new Ship(1,1,2,stageModel);
        shipplayer1[4] = new Ship(1,1,2,stageModel);
        shipplayer1[5] = new Ship(1,1,2,stageModel);
        shipplayer1[6] = new Ship(1,1,1,stageModel);
        shipplayer1[7] = new Ship(1,1,1,stageModel);
        shipplayer1[8] = new Ship(1,1,1,stageModel);
        shipplayer1[9] = new Ship(1,1,1,stageModel);
        stageModel.setShipsPlayer1(shipplayer1);

        //ship pour player 2
        Ship[] shipplayer2 = new Ship[10];
        shipplayer2[0] = new Ship(1,1,4,stageModel);
        shipplayer2[1] = new Ship(1,1,3,stageModel);
        shipplayer2[2] = new Ship(1,1,3,stageModel);
        shipplayer2[3] = new Ship(1,1,2,stageModel);
        shipplayer2[4] = new Ship(1,1,2,stageModel);
        shipplayer2[5] = new Ship(1,1,2,stageModel);
        shipplayer2[6] = new Ship(1,1,1,stageModel);
        shipplayer2[7] = new Ship(1,1,1,stageModel);
        shipplayer2[8] = new Ship(1,1,1,stageModel);
        shipplayer2[9] = new Ship(1,1,1,stageModel);
        stageModel.setShipsPlayer2(shipplayer2);

        for (int i = 0; i < shipplayer1.length; i++) {
            shipplayer1[i].setShipParts(stageModel);
<<<<<<< Updated upstream
=======
        }stageModel.setShipsPlayer1(shipplayer1);


        for(int i = 0; i < shipplayer2.length; i++) {
            shipplayer2[i].setShipParts(stageModel);
        }stageModel.setShipsPlayer2(shipplayer2);

        shipPart[] partsj1 = new shipPart[stageModel.nbdepart(shipplayer1)];
        shipPart[] partsj2 = new shipPart[stageModel.nbdepart(shipplayer2)];
        for (int i = 0; i < partsj1.length; i++) {
            partsj1[i] = new shipPart(i+1,1,0,stageModel);
            partsj2[i] = new shipPart(i+1,1,1,stageModel);
>>>>>>> Stashed changes
        }

        for (int i = 0; i < shipplayer2.length; i++) {
            shipplayer2[i].setShipParts(stageModel);
        }

        Misille[] misillePlayer1 = new Misille[50];
        for (int i = 0; i < misillePlayer1.length; i++) {
            misillePlayer1[i]= new Misille(i,1,stageModel);
            }
        stageModel.setMissileJoueur1(misillePlayer1);
        Misille[] misillePlayer2 = new Misille[50];
        for (int i = 0; i < misillePlayer2.length; i++) {
            misillePlayer2[i]= new Misille(i,1,stageModel);
        }
        stageModel.setMissileJoueur2(misillePlayer2);


        MisileConteneur conteneurjoueur1 = new MisileConteneur(0,0,stageModel);
        MisileConteneur conteneurjoueur2 = new MisileConteneur(0,0,stageModel);

        for(int i =0; i < misillePlayer1.length; i++){
            conteneurjoueur1.addElement(misillePlayer1[i],1,i);
            conteneurjoueur2.addElement(misillePlayer2[i],1,i);
        }

        TextElement infopartie = new TextElement("d", stageModel);
        stageModel.setInfoPartie(infopartie);




    }


        //permet de selecte le mode de jeux
    private int quelleModeDeJeux(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("mode de jeux 1: - 1 porte-avion de 5 cases,\n" +
                "                - 1 croiseur de 4 cases,\n" +
                "                - 2 contre-torpilleurs de 3 cases,\n" +
                "                - 1 torpilleur de 2 cases. \n" + "\n"+
                "mode de jeux 2: - 1 cuirassé de 4 cases,\n" +
                "                - 2 croiseurs de 3 cases,\n" +
                "                - 3 torpilleurs de 2 cases,\n" +
                "                - 4 sous-marins de 1 case " +
                "\n" + "1 ou 2 : ");
        while (true){
            int mode = scanner.nextInt();
            if(mode == 1 ||mode ==2){
                return mode;
            }else{
                System.out.println("doit etre 1 ou 2 : ");
            }
        }
    }


<<<<<<< Updated upstream
=======
    private void setupmissile(int nb){
        StockMissile stkj1 = new StockMissile(54,1,stageModel);
        StockMissile stkj2 = new StockMissile(144,1,stageModel);
        stageModel.setStockMissileJ1(stkj1);
        stageModel.setStockMissileJ2(stkj2);
        stageModel.setPlayer1ToPlay(nb);
        stageModel.setPlayer2ToPlay(nb);
        Missille[] missillePlayer1 = new Missille[nb];
        for (int i = 0; i < missillePlayer1.length; i++) {
            missillePlayer1[i]= new Missille(i+1,1,stageModel);
        }
        stageModel.setMissileJoueur1(missillePlayer1);
        Missille[] missillePlayer2 = new Missille[nb];
        for (int i = 0; i < missillePlayer2.length; i++) {
            missillePlayer2[i]= new Missille(i+1,1,stageModel);
        }
        stageModel.setMissileJoueur2(missillePlayer2);


        for(int i =0; i < missillePlayer1.length; i++){
            stkj1.addElement(missillePlayer1[i],0,0);
            stkj2.addElement(missillePlayer2[i],0,0);
        }
    }


>>>>>>> Stashed changes




    @Override
    public void setup() {
        int mode = quelleModeDeJeux();
        if(mode == 1){
            setupMode1();
        } else if (mode == 2) {
            setupMode2();
        }
    }


}




