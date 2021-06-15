package sample;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class GameManager implements Serializable {

    private Stage primaryStage;
    HBox root;
    VBox vBox;
    Text player1;
    Text player1Time;
    Text player2;
    Text player2Time;
    Scene scene;


    VBox buttonsPlayer2;
    Button p2Option4;


    ArrayList<Circle> pionki;
    ArrayList<BoardSquare> lineZero;
    ArrayList<BoardSquare> lineFirst;
    ArrayList<BoardSquare> lineSecond;
    ArrayList<BoardSquare> lineThird;
    ArrayList<BoardSquare> lineFourth;
    ArrayList<BoardSquare> lineFifth;
    ArrayList<BoardSquare> lineSixth;
    ArrayList<BoardSquare> lineSeventh;
    ArrayList<BoardSquare> movements;
    ArrayList<BoardSquare> possibleOptions;
    ArrayList<MyCircle> previousCircle;
    ArrayList<BoardSquare> allBoarSquares;


    GameManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void restart() {

        pionki = new ArrayList<>();
        lineZero = new ArrayList<>();
        lineFirst = new ArrayList<>();
        lineSecond = new ArrayList<>();
        lineThird = new ArrayList<>();
        lineFourth = new ArrayList<>();
        lineFifth = new ArrayList<>();
        lineSixth = new ArrayList<>();
        lineSeventh = new ArrayList<>();
        movements = new ArrayList<>();
        possibleOptions = new ArrayList<>();
        previousCircle = new ArrayList<>();
        allBoarSquares = new ArrayList<>();


        root = new HBox();
        vBox = new VBox();
        vBox.setMinWidth(200);

        player1 = new Text("Owce");
        player1Time = new Text("Czas: ");

        player2 = new Text("Wilk");
        player2Time = new Text("Czas: ");
        buttonsPlayer2 = new VBox();
        p2Option4 = new Button("Reset");
        buttonsPlayer2.getChildren().addAll(p2Option4);

        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("Menu");

        MenuItem openItem = new MenuItem("Open File");
        MenuItem saveItem = new MenuItem("Save File");
        menuFile.getItems().addAll(openItem, saveItem);

        menuBar.getMenus().addAll(menuFile);

        openItem.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                readFile(file.getAbsolutePath());
            }
        });

        saveItem.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showSaveDialog(primaryStage);
            if (file != null) {
                saveFile(file.getAbsolutePath());
            }
        });

        vBox.getChildren().addAll(menuBar, player1, player1Time, player2, player2Time, buttonsPlayer2);
        root.getChildren().addAll(getBoard(), vBox);

        scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        p2Option4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                restart();
            }
        });
    }


    public GridPane getBoard() {
        GridPane board = new GridPane();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board.add(getBoardSquare(row, col), col, row);
            }
        }
        for (int row = 0; row < 8; row++) {
            RowConstraints constrains = new RowConstraints();
            constrains.setPercentHeight(20);
            board.getRowConstraints().add(constrains);
        }
        for (int col = 0; col < 8; col++) {
            ColumnConstraints constrains = new ColumnConstraints();
            constrains.setPercentWidth(20);
            board.getColumnConstraints().add(constrains);
        }
        return board;

    }

    public StackPane getBoardSquare(int row, int col) {
        StackPane stackPane = new StackPane();
        BoardSquare square = new BoardSquare(row, col);
        allBoarSquares.add(square);



        MyCircle circle = new MyCircle(row, col);
        pionki.add(circle);
        NumberBinding radiusProperty = Bindings.when(square.widthProperty().
                greaterThan(square.heightProperty())).
                then(square.heightProperty().subtract(12).divide(2)).
                otherwise(square.widthProperty().subtract(12).divide(2));
        circle.radiusProperty().bind(radiusProperty);
        circle.setFill(Color.GREEN);

        if (row > 0) {
            circle.setVisible(false);
        }

//        if (row == 1 & col == 2) {
//            circle.setVisible(true);
//        }

        if (row == 7 & col == 2 ) {
            circle.setVisible(true);
            circle.setFill(Color.BLUE);
        }

        switch (row) {
            case 0:
                if ((col + row) % 2 != 0) {
                    stackPane.getChildren().addAll(square, circle);
                    lineZero.add(square);
                } else {
                    stackPane.getChildren().addAll(square);
                    lineZero.add(square);
                }
                break;
            case 1:
                if ((col + row) % 2 != 0) {
                    stackPane.getChildren().addAll(square, circle);
                    lineFirst.add(square);
                } else {
                    stackPane.getChildren().addAll(square);
                    lineFirst.add(square);
                }
                break;

            case 2:
                if ((col + row) % 2 != 0) {
                    stackPane.getChildren().addAll(square, circle);
                    lineSecond.add(square);
                } else {
                    stackPane.getChildren().addAll(square);
                    lineSecond.add(square);
                }
                break;

            case 3:
                if ((col + row) % 2 != 0) {
                    stackPane.getChildren().addAll(square, circle);
                    lineThird.add(square);
                } else {
                    stackPane.getChildren().addAll(square);
                    lineThird.add(square);
                }
                break;

            case 4:
                if ((col + row) % 2 != 0) {
                    stackPane.getChildren().addAll(square, circle);
                    lineFourth.add(square);
                } else {
                    stackPane.getChildren().addAll(square);
                    lineFourth.add(square);
                }
                break;

            case 5:
                if ((col + row) % 2 != 0) {
                    stackPane.getChildren().addAll(square, circle);
                    lineFifth.add(square);
                } else {
                    stackPane.getChildren().addAll(square);
                    lineFifth.add(square);
                }
                break;

            case 6:
                if ((col + row) % 2 != 0) {
                    stackPane.getChildren().addAll(square, circle);
                    lineSixth.add(square);
                } else {
                    stackPane.getChildren().addAll(square);
                    lineSixth.add(square);
                }
                break;

            case 7:
                if ((col + row) % 2 != 0) {
                    stackPane.getChildren().addAll(square, circle);
                    lineSeventh.add(square);
                } else {
                    stackPane.getChildren().addAll(square);
                    lineSeventh.add(square);
                }
                break;

        }

        stackPane.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                if (square.getColor() == Color.BLACK) {
                    if (square.getParent().getChildrenUnmodifiable().get(1).isVisible()) {
                        movements.add(square);
                        previousCircle.add(circle);
                    }
                }
                int squareLine = -1;
                int squareCol = -1;
                int optionFirst = -1;
                int optionSecond = -1;
                int optionThird = -1;
                int optionFourth = -1;
                if (circle.isVisible()) {
                    if (square.getColor() == Color.BLACK) {
                        if (circle.getFill() == Color.GREEN) {
                            blackAll();

                            switch (row) {
                                case 0:
                                    if (col < 7) {
                                        optionFirst = square.getCol() - 1;
                                        optionSecond = square.getCol() + 1;
                                        square.setColor(Color.PINK);
                                        StackPane spOptionFirst = (StackPane) lineFirst.get(optionFirst).getParent();
                                        StackPane spOptionSecond = (StackPane) lineFirst.get(optionSecond).getParent();
                                        if (!spOptionFirst.getChildren().get(1).isVisible()) {
                                            lineFirst.get(optionFirst).highlight();
                                            possibleOptions.add(lineFirst.get(optionFirst));
                                        }
                                        if (!spOptionSecond.getChildren().get(1).isVisible()) {
                                            lineFirst.get(optionSecond).highlight();
                                            possibleOptions.add(lineFirst.get(optionSecond));
                                        }
                                    } else {
                                        optionFirst = square.getCol() - 1;
                                        square.setColor(Color.PINK);
                                        StackPane spOptionFirst = (StackPane) lineFirst.get(optionFirst).getParent();
                                        if (!spOptionFirst.getChildren().get(1).isVisible()) {
                                            lineFirst.get(optionFirst).highlight();
                                            possibleOptions.add(lineFirst.get(optionFirst));
                                        }
                                    }
                                    break;

                                case 1:
                                    if (col > 0) {
                                        optionFirst = square.getCol() - 1;
                                        optionSecond = square.getCol() + 1;
                                        square.setColor(Color.PINK);
                                        StackPane spOptionFirst = (StackPane) lineSecond.get(optionFirst).getParent();
                                        StackPane spOptionSecond = (StackPane) lineSecond.get(optionSecond).getParent();
                                        if (!spOptionFirst.getChildren().get(1).isVisible()) {
                                            lineSecond.get(optionFirst).highlight();
                                        }
                                        if (!spOptionSecond.getChildren().get(1).isVisible()) {
                                            lineSecond.get(optionSecond).highlight();
                                        }
                                    } else {
                                        optionFirst = square.getCol() + 1;
                                        square.setColor(Color.PINK);
                                        StackPane spOptionFirst = (StackPane) lineSecond.get(optionFirst).getParent();
                                        if (!spOptionFirst.getChildren().get(1).isVisible()) {
                                            lineSecond.get(optionFirst).highlight();
                                        }
                                    }
                                    break;

                                case 2:
                                    if (col < 7) {
                                        optionFirst = square.getCol() - 1;
                                        optionSecond = square.getCol() + 1;
                                        square.setColor(Color.PINK);
                                        StackPane spOptionFirst = (StackPane) lineThird.get(optionFirst).getParent();
                                        StackPane spOptionSecond = (StackPane) lineThird.get(optionSecond).getParent();
                                        if (!spOptionFirst.getChildren().get(1).isVisible()) {
                                            lineThird.get(optionFirst).highlight();
                                        }
                                        if (!spOptionSecond.getChildren().get(1).isVisible()) {
                                            lineThird.get(optionSecond).highlight();
                                        }
                                    } else {
                                        optionFirst = square.getCol() - 1;
                                        square.setColor(Color.PINK);
                                        StackPane spOptionFirst = (StackPane) lineThird.get(optionFirst).getParent();
                                        if (!spOptionFirst.getChildren().get(1).isVisible()) {
                                            lineThird.get(optionFirst).highlight();
                                        }
                                    }
                                    break;

                                case 3:
                                    if (col > 0) {
                                        optionFirst = square.getCol() - 1;
                                        optionSecond = square.getCol() + 1;
                                        square.setColor(Color.PINK);
                                        StackPane spOptionFirst = (StackPane) lineFourth.get(optionFirst).getParent();
                                        StackPane spOptionSecond = (StackPane) lineFourth.get(optionSecond).getParent();
                                        if (!spOptionFirst.getChildren().get(1).isVisible()) {
                                            lineFourth.get(optionFirst).highlight();
                                        }
                                        if (!spOptionSecond.getChildren().get(1).isVisible()) {
                                            lineFourth.get(optionSecond).highlight();
                                        }
                                    } else {
                                        optionFirst = square.getCol() + 1;
                                        square.setColor(Color.PINK);
                                        StackPane spOptionFirst = (StackPane) lineFourth.get(optionFirst).getParent();
                                        if (!spOptionFirst.getChildren().get(1).isVisible()) {
                                            lineFourth.get(optionFirst).highlight();
                                        }
                                    }
                                    break;

                                case 4:
                                    if (col < 7) {
                                        optionFirst = square.getCol() - 1;
                                        optionSecond = square.getCol() + 1;
                                        square.setColor(Color.PINK);
                                        StackPane spOptionFirst = (StackPane) lineFifth.get(optionFirst).getParent();
                                        StackPane spOptionSecond = (StackPane) lineFifth.get(optionSecond).getParent();
                                        if (!spOptionFirst.getChildren().get(1).isVisible()) {
                                            lineFifth.get(optionFirst).highlight();
                                        }
                                        if (!spOptionSecond.getChildren().get(1).isVisible()) {
                                            lineFifth.get(optionSecond).highlight();
                                        }
                                    } else {
                                        optionFirst = square.getCol() - 1;
                                        square.setColor(Color.PINK);
                                        StackPane spOptionFirst = (StackPane) lineFifth.get(optionFirst).getParent();
                                        if (!spOptionFirst.getChildren().get(1).isVisible()) {
                                            lineFifth.get(optionFirst).highlight();
                                        }
                                    }
                                    break;

                                case 5:
                                    if (col > 0) {
                                        optionFirst = square.getCol() - 1;
                                        optionSecond = square.getCol() + 1;
                                        square.setColor(Color.PINK);
                                        StackPane spOptionFirst = (StackPane) lineSixth.get(optionFirst).getParent();
                                        StackPane spOptionSecond = (StackPane) lineSixth.get(optionSecond).getParent();
                                        if (!spOptionFirst.getChildren().get(1).isVisible()) {
                                            lineSixth.get(optionFirst).highlight();
                                        }
                                        if (!spOptionSecond.getChildren().get(1).isVisible()) {
                                            lineSixth.get(optionSecond).highlight();
                                        }
                                    } else {
                                        optionFirst = square.getCol() + 1;
                                        square.setColor(Color.PINK);
                                        StackPane spOptionFirst = (StackPane) lineSixth.get(optionFirst).getParent();
                                        if (!spOptionFirst.getChildren().get(1).isVisible()) {
                                            lineSixth.get(optionFirst).highlight();
                                        }
                                    }
                                    break;

                                case 6:
                                    if (col < 7) {
                                        optionFirst = square.getCol() - 1;
                                        optionSecond = square.getCol() + 1;
                                        square.setColor(Color.PINK);
                                        StackPane spOptionFirst = (StackPane) lineSeventh.get(optionFirst).getParent();
                                        StackPane spOptionSecond = (StackPane) lineSeventh.get(optionSecond).getParent();
                                        if (!spOptionFirst.getChildren().get(1).isVisible()) {
                                            lineSeventh.get(optionFirst).highlight();
                                        }
                                        if (!spOptionSecond.getChildren().get(1).isVisible()) {
                                            lineSeventh.get(optionSecond).highlight();
                                        }
                                    } else {
                                        optionFirst = square.getCol() - 1;
                                        square.setColor(Color.PINK);
                                        StackPane spOptionFirst = (StackPane) lineSeventh.get(optionFirst).getParent();
                                        if (!spOptionFirst.getChildren().get(1).isVisible()) {
                                            lineSeventh.get(optionFirst).highlight();
                                        }
                                    }
                                    break;

                                case 7:
                                    break;
                            }

                        } else if (circle.getFill() == Color.BLUE) {
                            blackAll();

                            switch (row) {
                                case 0:
                                    break;

                                case 1:
                                    if (col > 0) {
                                        optionFirst = square.getCol() - 1;
                                        optionSecond = square.getCol() + 1;
                                        square.setColor(Color.PINK);
                                        StackPane spOptionFirst = (StackPane) lineZero.get(optionFirst).getParent();
                                        StackPane spOptionSecond = (StackPane) lineZero.get(optionSecond).getParent();
                                        StackPane spOptionThird = (StackPane) lineSecond.get(optionSecond).getParent();
                                        StackPane spOptionFourth = (StackPane) lineSecond.get(optionSecond).getParent();
                                        if (!spOptionFirst.getChildren().get(1).isVisible()) {
                                            lineZero.get(optionFirst).highlight();
                                        }
                                        if (!spOptionSecond.getChildren().get(1).isVisible()) {
                                            lineZero.get(optionSecond).highlight();
                                        }
                                        if (!spOptionThird.getChildren().get(1).isVisible()) {
                                            lineSecond.get(optionFirst).highlight();
                                        }
                                        if (!spOptionFourth.getChildren().get(1).isVisible()) {
                                            lineSecond.get(optionSecond).highlight();
                                        }
                                    } else {
                                        optionFirst = square.getCol() + 1;
                                        square.setColor(Color.PINK);
                                        StackPane spOptionFirst = (StackPane) lineZero.get(optionFirst).getParent();
                                        StackPane spOptionSecond = (StackPane) lineSecond.get(optionFirst).getParent();
                                        if (!spOptionFirst.getChildren().get(1).isVisible()) {
                                            lineZero.get(optionFirst).highlight();
                                        }
                                        if (!spOptionSecond.getChildren().get(1).isVisible()) {
                                            lineSecond.get(optionFirst).highlight();
                                        }
                                    }
                                    ifHoundsWon();
                                    break;

                                case 2:
                                    if (col < 7) {
                                        optionFirst = square.getCol() - 1;
                                        optionSecond = square.getCol() + 1;
                                        square.setColor(Color.PINK);
                                        StackPane spOptionFirst = (StackPane) lineFirst.get(optionFirst).getParent();
                                        StackPane spOptionSecond = (StackPane) lineFirst.get(optionSecond).getParent();
                                        StackPane spOptionThird = (StackPane) lineThird.get(optionSecond).getParent();
                                        StackPane spOptionFourth = (StackPane) lineThird.get(optionSecond).getParent();
                                        if (!spOptionFirst.getChildren().get(1).isVisible()) {
                                            lineFirst.get(optionFirst).highlight();
                                        }
                                        if (!spOptionSecond.getChildren().get(1).isVisible()) {
                                            lineFirst.get(optionSecond).highlight();
                                        }
                                        if (!spOptionThird.getChildren().get(1).isVisible()) {
                                            lineThird.get(optionFirst).highlight();
                                        }
                                        if (!spOptionFourth.getChildren().get(1).isVisible()) {
                                            lineThird.get(optionSecond).highlight();
                                        }
                                    } else {
                                        optionFirst = square.getCol() - 1;
                                        square.setColor(Color.PINK);
                                        StackPane spOptionFirst = (StackPane) lineFirst.get(optionFirst).getParent();
                                        StackPane spOptionSecond = (StackPane) lineThird.get(optionFirst).getParent();
                                        if (!spOptionFirst.getChildren().get(1).isVisible()) {
                                            lineFirst.get(optionFirst).highlight();
                                        }
                                        if (!spOptionSecond.getChildren().get(1).isVisible()) {
                                            lineThird.get(optionFirst).highlight();
                                        }
                                    }
                                    ifHoundsWon();
                                    break;

                                case 3:
                                    if (col > 0) {
                                        optionFirst = square.getCol() - 1;
                                        optionSecond = square.getCol() + 1;
                                        square.setColor(Color.PINK);
                                        StackPane spOptionFirst = (StackPane) lineSecond.get(optionFirst).getParent();
                                        StackPane spOptionSecond = (StackPane) lineSecond.get(optionSecond).getParent();
                                        StackPane spOptionThird = (StackPane) lineFourth.get(optionSecond).getParent();
                                        StackPane spOptionFourth = (StackPane) lineFourth.get(optionSecond).getParent();
                                        if (!spOptionFirst.getChildren().get(1).isVisible()) {
                                            lineSecond.get(optionFirst).highlight();
                                        }
                                        if (!spOptionSecond.getChildren().get(1).isVisible()) {
                                            lineSecond.get(optionSecond).highlight();
                                        }
                                        if (!spOptionThird.getChildren().get(1).isVisible()) {
                                            lineFourth.get(optionFirst).highlight();
                                        }
                                        if (!spOptionFourth.getChildren().get(1).isVisible()) {
                                            lineFourth.get(optionSecond).highlight();
                                        }
                                    } else {
                                        optionFirst = square.getCol() + 1;
                                        square.setColor(Color.PINK);
                                        StackPane spOptionFirst = (StackPane) lineSecond.get(optionFirst).getParent();
                                        StackPane spOptionSecond = (StackPane) lineFourth.get(optionFirst).getParent();
                                        if (!spOptionFirst.getChildren().get(1).isVisible()) {
                                            lineSecond.get(optionFirst).highlight();
                                        }
                                        if (!spOptionSecond.getChildren().get(1).isVisible()) {
                                            lineFourth.get(optionFirst).highlight();
                                        }
                                    }
                                    ifHoundsWon();
                                    break;

                                case 4:
                                    if (col < 7) {
                                        optionFirst = square.getCol() - 1;
                                        optionSecond = square.getCol() + 1;
                                        square.setColor(Color.PINK);
                                        StackPane spOptionFirst = (StackPane) lineThird.get(optionFirst).getParent();
                                        StackPane spOptionSecond = (StackPane) lineThird.get(optionSecond).getParent();
                                        StackPane spOptionThird = (StackPane) lineFifth.get(optionSecond).getParent();
                                        StackPane spOptionFourth = (StackPane) lineFifth.get(optionSecond).getParent();
                                        if (!spOptionFirst.getChildren().get(1).isVisible()) {
                                            lineThird.get(optionFirst).highlight();
                                        }
                                        if (!spOptionSecond.getChildren().get(1).isVisible()) {
                                            lineThird.get(optionSecond).highlight();
                                        }
                                        if (!spOptionThird.getChildren().get(1).isVisible()) {
                                            lineFifth.get(optionFirst).highlight();
                                        }
                                        if (!spOptionFourth.getChildren().get(1).isVisible()) {
                                            lineFifth.get(optionSecond).highlight();
                                        }
                                    } else {
                                        optionFirst = square.getCol() - 1;
                                        square.setColor(Color.PINK);
                                        StackPane spOptionFirst = (StackPane) lineThird.get(optionFirst).getParent();
                                        StackPane spOptionSecond = (StackPane) lineFifth.get(optionFirst).getParent();
                                        if (!spOptionFirst.getChildren().get(1).isVisible()) {
                                            lineThird.get(optionFirst).highlight();
                                        }
                                        if (!spOptionSecond.getChildren().get(1).isVisible()) {
                                            lineFifth.get(optionFirst).highlight();
                                        }
                                    }
                                    ifHoundsWon();
                                    break;

                                case 5:
                                    if (col > 0) {
                                        optionFirst = square.getCol() - 1;
                                        optionSecond = square.getCol() + 1;
                                        square.setColor(Color.PINK);
                                        StackPane spOptionFirst = (StackPane) lineFourth.get(optionFirst).getParent();
                                        StackPane spOptionSecond = (StackPane) lineFourth.get(optionSecond).getParent();
                                        StackPane spOptionThird = (StackPane) lineSixth.get(optionSecond).getParent();
                                        StackPane spOptionFourth = (StackPane) lineSixth.get(optionSecond).getParent();
                                        if (!spOptionFirst.getChildren().get(1).isVisible()) {
                                            lineFourth.get(optionFirst).highlight();
                                        }
                                        if (!spOptionSecond.getChildren().get(1).isVisible()) {
                                            lineFourth.get(optionSecond).highlight();
                                        }
                                        if (!spOptionThird.getChildren().get(1).isVisible()) {
                                            lineSixth.get(optionFirst).highlight();
                                        }
                                        if (!spOptionFourth.getChildren().get(1).isVisible()) {
                                            lineSixth.get(optionSecond).highlight();
                                        }
                                    } else {
                                        optionFirst = square.getCol() + 1;
                                        square.setColor(Color.PINK);
                                        StackPane spOptionFirst = (StackPane) lineFourth.get(optionFirst).getParent();
                                        StackPane spOptionSecond = (StackPane) lineSixth.get(optionFirst).getParent();
                                        if (!spOptionFirst.getChildren().get(1).isVisible()) {
                                            lineFourth.get(optionFirst).highlight();
                                        }
                                        if (!spOptionSecond.getChildren().get(1).isVisible()) {
                                            lineSixth.get(optionFirst).highlight();
                                        }
                                    }
                                    ifHoundsWon();
                                    break;

                                case 6:
                                    if (col < 7) {
                                        optionFirst = square.getCol() - 1;
                                        optionSecond = square.getCol() + 1;
                                        square.setColor(Color.PINK);
                                        StackPane spOptionFirst = (StackPane) lineFifth.get(optionFirst).getParent();
                                        StackPane spOptionSecond = (StackPane) lineFifth.get(optionSecond).getParent();
                                        StackPane spOptionThird = (StackPane) lineSeventh.get(optionSecond).getParent();
                                        StackPane spOptionFourth = (StackPane) lineSeventh.get(optionSecond).getParent();
                                        if (!spOptionFirst.getChildren().get(1).isVisible()) {
                                            lineFifth.get(optionFirst).highlight();
                                        }
                                        if (!spOptionSecond.getChildren().get(1).isVisible()) {
                                            lineFifth.get(optionSecond).highlight();
                                        }
                                        if (!spOptionThird.getChildren().get(1).isVisible()) {
                                            lineSeventh.get(optionFirst).highlight();
                                        }
                                        if (!spOptionFourth.getChildren().get(1).isVisible()) {
                                            lineSeventh.get(optionSecond).highlight();
                                        }
                                    } else {
                                        optionFirst = square.getCol() - 1;
                                        square.setColor(Color.PINK);
                                        StackPane spOptionFirst = (StackPane) lineFifth.get(optionFirst).getParent();
                                        StackPane spOptionSecond = (StackPane) lineSeventh.get(optionFirst).getParent();
                                        if (!spOptionFirst.getChildren().get(1).isVisible()) {
                                            lineFifth.get(optionFirst).highlight();
                                        }
                                        if (!spOptionSecond.getChildren().get(1).isVisible()) {
                                            lineSeventh.get(optionFirst).highlight();
                                        }
                                    }
                                    ifHoundsWon();
                                    break;

                                case 7:
                                    if (col > 0) {
                                        optionFirst = square.getCol() - 1;
                                        optionSecond = square.getCol() + 1;
                                        square.setColor(Color.PINK);
                                        StackPane spOptionFirst = (StackPane) lineSixth.get(optionFirst).getParent();
                                        StackPane spOptionSecond = (StackPane) lineSixth.get(optionSecond).getParent();
                                        if (!spOptionFirst.getChildren().get(1).isVisible()) {
                                            lineSixth.get(optionFirst).highlight();
                                        }
                                        if (!spOptionSecond.getChildren().get(1).isVisible()) {
                                            lineSixth.get(optionSecond).highlight();
                                        }
                                    } else {
                                        optionFirst = square.getCol() + 1;
                                        square.setColor(Color.PINK);
                                        StackPane spOptionFirst = (StackPane) lineSixth.get(optionFirst).getParent();
                                        if (!spOptionFirst.getChildren().get(1).isVisible()) {
                                            lineSixth.get(optionFirst).highlight();
                                        }
                                    }
                                    ifHoundsWon();
                                    break;
                            }
                        }
                    } else if (square.getColor() == Color.PINK){
                        System.out.println("Tuuuu");
                        blackAll();
                    } else if (square.getColor() == Color.YELLOW) {
                    }
                } else {
                    if (square.isHighlighted()) {
                        System.out.println("TAK!!");
                        BoardSquare lastMovement = movements.get(movements.size() - 1);
                        MyCircle lastCircle = previousCircle.get(previousCircle.size() - 1);
                        System.out.println(lastCircle.getFill());
                        if (lastCircle.getFill() == Color.GREEN) {
                            System.out.println("TAK");
                            MyCircle lastCircle1 = (MyCircle) square.getParent().getChildrenUnmodifiable().get(1);
                            lastCircle1.setVisible(true);
                            lastCircle1.setFill(Color.GREEN);
                            movements.get(movements.size() - 1).getParent().getChildrenUnmodifiable().get(1).setVisible(false);
                            blackAll();
                        } else if (lastCircle.getFill() == Color.BLUE) {
                            System.out.println("Niebieski");
                            MyCircle lastCircle1 = (MyCircle) square.getParent().getChildrenUnmodifiable().get(1);
                            lastCircle1.setVisible(true);
                            lastCircle1.setFill(Color.BLUE);
                            movements.get(movements.size() - 1).getParent().getChildrenUnmodifiable().get(1).setVisible(false);
                            blackAll();
                            if (lastCircle1.getRow() == 0) {
                                win("Fox");
                            }
                        }
                    }

                }

                System.out.println(circle.getRow() + " " + circle.getCol());
            }
        });

        return stackPane;
    }

    public void win(String winner) {

        System.out.println(winner + " won!");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Gratulacje!");
        alert.setHeaderText("Gratulacje..");
        alert.setContentText(winner + " won!");
        alert.showAndWait();
        restart();
    }

    public void blackAll() {

        for (BoardSquare ss : allBoarSquares) {
            ss.blacken();
        }
    }

    public void ifHoundsWon() {

        int highlighted = 0;

        for (BoardSquare bs : allBoarSquares) {
            if (bs.isHighlighted()) {
                highlighted++;
            }
        }

        if (highlighted == 0) {
            blackAll();
            win("Hounds");
        }
    }


    public void saveFile(String path) {

        //TODO saveFile usage
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(this);
            oos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Niepoprawny plik!");
            alert.setContentText("Wybrano niepoprawny plik.");
            alert.showAndWait();
        }

    }

    public void readFile(String path) {

        //TODO readFile usage
    }
}
