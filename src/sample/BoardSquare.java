package sample;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class BoardSquare extends Region {

    private Color color;
    private int row, col;
    private boolean isHighlighted;

    public BoardSquare(int row, int col) {

        this.row = row;
        this.col = col;

        if ((col + row) % 2 == 0) {
            color = Color.WHITE;
        } else {
            color = Color.BLACK;
        }

        setColor(color);
        setPrefSize(200, 200);

    }


    public void highlight() {
        setColor(Color.YELLOW);
        isHighlighted = true;
    }


    public void blacken() {
        setColor(color);
        isHighlighted = false;
    }


    public void setColor(Color color) {
        BackgroundFill bgFill = new BackgroundFill(color, CornerRadii.EMPTY, new Insets(2));
        Background bg = new Background(bgFill);
        setBackground(bg);
    }

    public Color getColor() {
        return color;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isHighlighted() {
        return isHighlighted;
    }

    public void setHighlighted(boolean highlighted) {
        isHighlighted = highlighted;
    }
}
