package model;
import boardifier.model.ContainerElement;
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
        textplayer2.setLocation(0,0);
        stageModel.setPlayer2Name(textplayer2);

        BattleBoard boardplayer1 = new BattleBoard(0, 1, stageModel);
        // assign the board to the game stage model
        stageModel.setBoardPlayer1(boardplayer1);

        BattleBoard boardplayer2 = new BattleBoard(0, 1, stageModel);
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

        Cell[]cellPlayer1 = new Cell[100];
        for (int i = 0; i < cellPlayer1.length; i++) {
            cellPlayer1[i]= new Cell(i,1,stageModel);
            }
        Cell[]cellPlayer2 = new Cell[100];
        for (int i = 0; i < cellPlayer2.length; i++) {
            cellPlayer2[i]= new Cell(i,1,stageModel);
        }

    }

    private void setupMode2(){
        TextElement textplayer1 = new TextElement(stageModel.getCurrentPlayerName(), stageModel);
        textplayer1.setLocation(0,0);
        stageModel.setPlayer1Name(textplayer1);

        TextElement textplayer2 = new TextElement(stageModel.getCurrentPlayerName(), stageModel);
        textplayer2.setLocation(0,0);
        stageModel.setPlayer2Name(textplayer2);

        BattleBoard boardplayer1 = new BattleBoard(0, 1, stageModel);
        // assign the board to the game stage model
        stageModel.setBoardPlayer1(boardplayer1);

        BattleBoard boardplayer2 = new BattleBoard(0, 1, stageModel);
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

        Cell[]cellPlayer1 = new Cell[100];
        for (int i = 0; i < cellPlayer1.length; i++) {
            cellPlayer1[i]= new Cell(i,1,stageModel);
            }
        Cell[]cellPlayer2 = new Cell[100];
        for (int i = 0; i < cellPlayer2.length; i++) {
            cellPlayer2[i]= new Cell(i,1,stageModel);
        }
        }


}

    }


    @Override
    public void setup() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("mode de jeux 1 ou 2");
        int mode = scanner.nextInt();
        if(mode == 1){
            setupMode1();
        } else if (mode == 2) {
            setupMode2();
        }
    }






}
