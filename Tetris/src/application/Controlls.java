package application;


import java.io.IOException;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controlls {
	
// Att göra:
	// Slid:a när man precis kommit ner
	// start skärm?
	// Automatiskt ner timing (återställ en-sekunds-timern när annat trycks på?) XXX Typ fixat XXX
	
//buggar:
	// Bitar fastnar vid toppen ibland om "c" trycks för tidigt
	// Bitar förvandlas till kuber när "c" trycks igen ibland
	// Ibland upptäcks att enskilda rutor svävar, vilket inte borde vara möjligt, undersök
	
	
	@FXML
	private AnchorPane root;
	@FXML
	private Rectangle square;
	@FXML
	private Rectangle square1;
	@FXML
	private Rectangle square2;
	@FXML
	private Rectangle square3;
	@FXML
	private Rectangle mini;
	@FXML
	private Rectangle mini1;
	@FXML
	private Rectangle mini2;
	@FXML
	private Rectangle mini3;
	@FXML
	private Rectangle hold;
	@FXML
	private Rectangle hold1;
	@FXML
	private Rectangle hold2;
	@FXML
	private Rectangle hold3;
	@FXML
	private Text points;
	@FXML
	private Text score;
	
	private Stage stage;
	private Scene scene;
	
	@FXML
	private Button buttonleft;
	@FXML
	private Button buttonright;
	@FXML
	private Button buttonstart;
	
	
	private int[][] occupancy;
	private Rectangle[][] oldSquares;
	private Timeline timeline;
	private int row;
	private int column;
	private int row1;
	private int column1;
	private int row2;
	private int column2;
	private int row3;
	private int column3;
	private Piece p;
	
	private int holdPiece;
	private Color holdColor;
	private boolean holded;
	
	private TranslateTransition translate;
	private Group animationGroup;
	private double duration = 0.2;
	
	private int Points;
	
	private Random r;
	
	
	public void initialize(){	
		r = new Random();
		
		translate = new TranslateTransition(Duration.seconds(duration));
		
		
		oldSquares = new Rectangle [23][10];
		occupancy= new int[23][10];
		
		holdPiece = 7;
		holded = false;
		
		Points = 0;
		
		hold.setLayoutX(-100);
		hold1.setLayoutX(-100);
		hold2.setLayoutX(-100);
		hold3.setLayoutX(-100);

		p = new Piece(square, square1, square2, square3);
		p.newShape();
		printNext();
		updateIndices();
		
		buttonleft.setFocusTraversable(false);
		buttonright.setFocusTraversable(false);
		buttonstart.setFocusTraversable(false);
		
		timeline = new Timeline(
			    new KeyFrame(Duration.seconds(1), event -> {
					try {
						down();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				})
			);

			timeline.setCycleCount(Timeline.INDEFINITE); // repeat forever
			timeline.play();
			
	}

	public void left(ActionEvent e) {
		leftB();
	}
	
	public void right(ActionEvent e) {
		//System.out.println("höger");
		rightB();
	}
	
	public void down() throws IOException{
		if(row<22 && row1<22 && row2<22 && row3<22 && occupancy[row+1][column]==0 && occupancy[row1+1][column1]==0 && occupancy[row2+1][column2]==0 && occupancy[row3+1][column3]==0) {
			p.Down();
			updateIndices();
		}else{
		
			occupancy[row][column] = 1;
			occupancy[row1][column1] = 1;
			occupancy[row2][column2] = 1;
			occupancy[row3][column3] = 1;
						
			copyShape();
			if(row<4 || row1<4 || row2<4 || row3<4) {
				timeline.setRate(0);
				gameOver();
			}
			
			lineCheck();
			p.newShape();
			printNext();
			updateIndices();
			points.setText(String.valueOf(Points));
			
			holded = false;
			
			int pos = r.nextInt(4);
			for(int p = 0; p<pos; p++) {
				turn();
			}

			for (int i = 0; i < occupancy.length; i++) {
			    for (int j = 0; j < occupancy[i].length; j++) {
			        System.out.print(occupancy[i][j] + " ");
			    }
			    System.out.println();
			}
		}
		
	}
	
	public void leftB() {
		//System.out.println("vänster");
		if((int) square.getLayoutX()/45 < 1 || (int) square1.getLayoutX()/45 < 1 || (int) square2.getLayoutX()/45 < 1 || (int) square3.getLayoutX()/45 < 1) {
			return;
		} else if(square.getLayoutY()<0 || square1.getLayoutY()<0 || square2.getLayoutY()<0 || square3.getLayoutY()<0) {
			p.Left();
		} else if(occupancy[row][column-1]==1 || occupancy[row1][column1-1]==1 || occupancy[row2][column2-1]==1 || occupancy[row3][column3-1]==1){
			return;
		}else {
			p.Left();
			updateIndices();
		}
	}
	
	public void rightB() {
		//System.out.println("höger");
		if (column>=9 || column1>=9 || column2>=9 || column3>=9) {
			return;
		} else if(occupancy[row][column+1]==1 || occupancy[row1][column1+1]==1 || occupancy[row2][column2+1]==1 || occupancy[row3][column3+1]==1){
			return;
		}else {
			p.Right();
			updateIndices();
		}
	}
	
	public void downB() {
		//System.out.println("vänster");
		if(row<22 && row1<22 && row2<22 && row3<22 && occupancy[row+1][column]==0 && occupancy[row1+1][column1]==0 && occupancy[row2+1][column2]==0 && occupancy[row3+1][column3]==0) {
			p.Down();
			updateIndices();
			timeline.playFromStart();
		} else {
			return;
		}
	}
	
	public void space() {
		int fall = 0;
		int combinedrow = row;
		int combinedrow1 = row1;
		int combinedrow2 = row2;
		int combinedrow3= row3;
		for(int i =0; i<22; i++) {
			if(combinedrow>22||combinedrow1>22||combinedrow2>22||combinedrow3>22) {
				break;
			}else if(occupancy[combinedrow][column]==1 || occupancy[combinedrow1][column1]==1 || occupancy[combinedrow2][column2]==1 || occupancy[combinedrow3][column3]==1) {
				break;
			}else {
				combinedrow++;
				combinedrow1++;
				combinedrow2++;
				combinedrow3++;
				fall++;
			}
		}
		p.xDown(fall-1);
		updateIndices();
		timeline.jumpTo(Duration.seconds(0.999));
	}
	
	public void hold() {
		if(holded == true) {
			return;
		}
		if(holdPiece==7) {
			passThrough();
		}else {
			switchEm();
		}
		int pos = r.nextInt(4);
		for(int p = 0; p<pos; p++) {
			turn();
		}
		holded = true;
	}

	private void passThrough() {
		holdColor = p.color;
		
		hold.setFill(holdColor);
		hold1.setFill(holdColor);
		hold2.setFill(holdColor);
		hold3.setFill(holdColor);
		
		switch(p.shape) {
		case 0:
			//Stick
			hold.setLayoutX(500);
			hold.setLayoutY(425);
			hold1.setLayoutX(500);
			hold1.setLayoutY(450);
			hold2.setLayoutX(500);
			hold2.setLayoutY(475);
			hold3.setLayoutX(500);
			hold3.setLayoutY(500);
			holdPiece=0;
			System.out.println("Stick");
			
			break;
		case 1:
			//L1
			hold.setLayoutX(500);
			hold.setLayoutY(425);
			hold1.setLayoutX(500);
			hold1.setLayoutY(450);
			hold2.setLayoutX(500);
			hold2.setLayoutY(475);
			hold3.setLayoutX(525);
			hold3.setLayoutY(425);
			holdPiece=1;
			System.out.println("L1");
			break;
		case 2:
			//L2
			hold.setLayoutX(525);
			hold.setLayoutY(425);
			hold1.setLayoutX(525);
			hold1.setLayoutY(450);
			hold2.setLayoutX(525);
			hold2.setLayoutY(475);
			hold3.setLayoutX(500);
			hold3.setLayoutY(425);
			holdPiece=2;
			System.out.println("L2");
			break;
		case 3:
			//Cube
			hold.setLayoutX(500);
			hold.setLayoutY(425);
			hold1.setLayoutX(500);
			hold1.setLayoutY(450);
			hold2.setLayoutX(525);
			hold2.setLayoutY(425);
			hold3.setLayoutX(525);
			hold3.setLayoutY(450);
			holdPiece=3;
			System.out.println("Cube");
			break;
		case 4:
			//T
			hold.setLayoutX(500);
			hold.setLayoutY(425);
			hold1.setLayoutX(500);
			hold1.setLayoutY(450);
			hold2.setLayoutX(500);
			hold2.setLayoutY(475);
			hold3.setLayoutX(525);
			hold3.setLayoutY(450);
			holdPiece=4;
			System.out.println("T");
			break;
		case 5:
			//S1
			hold.setLayoutX(500);
			hold.setLayoutY(425);
			hold1.setLayoutX(500);
			hold1.setLayoutY(450);
			hold2.setLayoutX(525);
			hold2.setLayoutY(450);
			hold3.setLayoutX(525);
			hold3.setLayoutY(475);
			holdPiece=5;
			System.out.println("S1");
			break;
		case 6:
			//S2
			hold.setLayoutX(525);
			hold.setLayoutY(425);
			hold1.setLayoutX(525);
			hold1.setLayoutY(450);
			hold2.setLayoutX(500);
			hold2.setLayoutY(450);
			hold3.setLayoutX(500);
			hold3.setLayoutY(475);
			holdPiece=6;
			System.out.println("S2");
			break;
		}
			p.newShape();
			printNext();
	}
	
	private void switchEm() {
		Color switchColor = holdColor;
		int switchShape = holdPiece;
		holdPiece = p.shape;
		holdColor = p.color;

		hold.setFill(holdColor);
		hold1.setFill(holdColor);
		hold2.setFill(holdColor);
		hold3.setFill(holdColor);
		
		switch(holdPiece) {
		case 0:
			//Stick
			hold.setLayoutX(500);
			hold.setLayoutY(425);
			hold1.setLayoutX(500);
			hold1.setLayoutY(450);
			hold2.setLayoutX(500);
			hold2.setLayoutY(475);
			hold3.setLayoutX(500);
			hold3.setLayoutY(500);
			holdPiece=0;
			System.out.println("Stick");
			
			break;
		case 1:
			//L1
			hold.setLayoutX(500);
			hold.setLayoutY(425);
			hold1.setLayoutX(500);
			hold1.setLayoutY(450);
			hold2.setLayoutX(500);
			hold2.setLayoutY(475);
			hold3.setLayoutX(525);
			hold3.setLayoutY(425);
			holdPiece=1;
			System.out.println("L1");
			break;
		case 2:
			//L2
			hold.setLayoutX(525);
			hold.setLayoutY(425);
			hold1.setLayoutX(525);
			hold1.setLayoutY(450);
			hold2.setLayoutX(525);
			hold2.setLayoutY(475);
			hold3.setLayoutX(500);
			hold3.setLayoutY(425);
			holdPiece=2;
			System.out.println("L2");
			break;
		case 3:
			//Cube
			hold.setLayoutX(500);
			hold.setLayoutY(425);
			hold1.setLayoutX(500);
			hold1.setLayoutY(450);
			hold2.setLayoutX(525);
			hold2.setLayoutY(425);
			hold3.setLayoutX(525);
			hold3.setLayoutY(450);
			holdPiece=3;
			System.out.println("Cube");
			break;
		case 4:
			//T
			hold.setLayoutX(500);
			hold.setLayoutY(425);
			hold1.setLayoutX(500);
			hold1.setLayoutY(450);
			hold2.setLayoutX(500);
			hold2.setLayoutY(475);
			hold3.setLayoutX(525);
			hold3.setLayoutY(450);
			holdPiece=4;
			System.out.println("T");
			break;
		case 5:
			//S1
			hold.setLayoutX(500);
			hold.setLayoutY(425);
			hold1.setLayoutX(500);
			hold1.setLayoutY(450);
			hold2.setLayoutX(525);
			hold2.setLayoutY(450);
			hold3.setLayoutX(525);
			hold3.setLayoutY(475);
			holdPiece=5;
			System.out.println("S1");
			break;
		case 6:
			//S2
			hold.setLayoutX(525);
			hold.setLayoutY(425);
			hold1.setLayoutX(525);
			hold1.setLayoutY(450);
			hold2.setLayoutX(500);
			hold2.setLayoutY(450);
			hold3.setLayoutX(500);
			hold3.setLayoutY(475);
			holdPiece=6;
			System.out.println("S2");
			break;
		}
		
		p.newShape(switchShape, switchColor);
		int pos = r.nextInt(4);
		for(int p = 0; p<pos; p++) {
			turn();
		}
		
	}
	
	private void copyShape() {
		Rectangle copy = new Rectangle(45,45);
		shapeSettings(copy);
		copy.setLayoutX(square.getLayoutX());
		copy.setLayoutY(square.getLayoutY());
		copy.setFill(square.getFill());
		Rectangle copy1 = new Rectangle(45,45);
		shapeSettings(copy1);
		copy1.setLayoutX(square1.getLayoutX());
		copy1.setLayoutY(square1.getLayoutY());
		copy1.setFill(square.getFill());
		Rectangle copy2 = new Rectangle(45,45);
		shapeSettings(copy2);
		copy2.setLayoutX(square2.getLayoutX());
		copy2.setLayoutY(square2.getLayoutY());
		copy2.setFill(square.getFill());
		Rectangle copy3 = new Rectangle(45,45);
		shapeSettings(copy3);
		copy3.setLayoutX(square3.getLayoutX());
		copy3.setLayoutY(square3.getLayoutY());
		copy3.setFill(square.getFill());
		
		root.getChildren().add(copy);
		root.getChildren().add(copy1);
		root.getChildren().add(copy2);
		root.getChildren().add(copy3);
		oldSquares[row][column]=copy;
		oldSquares[row1][column1]=copy1;
		oldSquares[row2][column2]=copy2;
		oldSquares[row3][column3]=copy3;
		
	}
	
	private void shapeSettings(Rectangle r) {
		r.setArcHeight(5);
		r.setArcWidth(5);
		r.setStrokeWidth(1);
		r.setStroke(Color.BLACK);
	}
	
	public void turn() {
		p.Turn(row,column,occupancy);	
		updateIndices();
	}

	//+1 ty hela skärmen får inte plats, måste fixa detta senare. En rad längst ner syns inte, och används inte.
	//Det synliga oldSquares:et är bara 19x10.
	
	//kan potentiellt bara uppdatera med + och - minus i varje förflyttningsfunktion
	
	public void updateIndices() {
		row = (int) ((square.getLayoutY()+135)/45)+1;
		column = (int) (square.getLayoutX()/45);
		row1 = (int) ((square1.getLayoutY()+135)/45)+1;
		column1 = (int) (square1.getLayoutX()/45);
		row2 = (int) ((square2.getLayoutY()+135)/45)+1;
		column2 = (int) square2.getLayoutX()/45;
		row3 = (int) ((square3.getLayoutY()+135)/45)+1;
		column3 = (int) (square3.getLayoutX()/45);
	}

	private void lineCheck() {
		int nbrRows = 0;
		
		for(int i = 0; i<23; i++) {
			for(int j = 0; j<10; j++) {
				if(occupancy[i][j]==0) {
					break;
				}else if(j==9) {
					deleteRow(i);
					nbrRows++;
					break;
				}
			}
		}
		
		if(nbrRows != 0) {
			awardPoints(nbrRows);
			timeline.setRate(timeline.getCurrentRate()+(nbrRows*0.05));
		}
	}
	
	private void deleteRow(int row) {
				animation(row);
				translate.setOnFinished(e -> {
					root.getChildren().remove(animationGroup);
					for (int i = row; i > 0; i--) {
					    for (int j = 0; j < 10; j++) {

					        oldSquares[i][j] = oldSquares[i - 1][j];
					        occupancy[i][j] = occupancy[i - 1][j];

					        if (oldSquares[i][j] != null) {
					            oldSquares[i][j].setLayoutY(oldSquares[i][j].getLayoutY() + 45);
					        }
					    }
					}
					for (int j = 0; j < 10; j++) {
					    oldSquares[0][j] = null;
					    occupancy[0][j] = 0;
					}
					System.out.println("finished");
				});
		
	}
	
	private void animation(int row) {
		animationGroup = new Group();
		translate = new TranslateTransition(Duration.seconds(duration), animationGroup);
		for (Rectangle r : oldSquares[row]) {
		    animationGroup.getChildren().add(r);
		}
		root.getChildren().add(animationGroup);
		translate.setNode(animationGroup);
		translate.setByX(600);
	
		System.out.println("animation started");
		translate.play();
		
	}

	private void awardPoints(int nbrRows) {
		if(nbrRows==1) {
			Points += 1000;
		}
		if(nbrRows==2) {
			Points += 2500;
		}
		if(nbrRows==3) {
			Points += 5000;
		}
		if(nbrRows==4) {
			Points += 10000;
		}
		
	}
	
	private void printNext() {
		
		int nextshape = p.nextshape;
		
		mini.setFill(p.nextcolor);
		mini1.setFill(p.nextcolor);
		mini2.setFill(p.nextcolor);
		mini3.setFill(p.nextcolor);
		
		switch(nextshape) {
		case 0:
			//Stick
			mini.setLayoutX(500);
			mini.setLayoutY(250);
			mini1.setLayoutX(500);
			mini1.setLayoutY(275);
			mini2.setLayoutX(500);
			mini2.setLayoutY(300);
			mini3.setLayoutX(500);
			mini3.setLayoutY(325);
			System.out.println("Stick");
			break;
		case 1:
			//L1
			mini.setLayoutX(500);
			mini.setLayoutY(250);
			mini1.setLayoutX(500);
			mini1.setLayoutY(275);
			mini2.setLayoutX(500);
			mini2.setLayoutY(300);
			mini3.setLayoutX(525);
			mini3.setLayoutY(250);
			System.out.println("L1");
			break;
		case 2:
			//L2
			mini.setLayoutX(525);
			mini.setLayoutY(250);
			mini1.setLayoutX(525);
			mini1.setLayoutY(275);
			mini2.setLayoutX(525);
			mini2.setLayoutY(300);
			mini3.setLayoutX(500);
			mini3.setLayoutY(250);
			System.out.println("L2");
			break;
		case 3:
			//Cube
			mini.setLayoutX(500);
			mini.setLayoutY(250);
			mini1.setLayoutX(500);
			mini1.setLayoutY(275);
			mini2.setLayoutX(525);
			mini2.setLayoutY(250);
			mini3.setLayoutX(525);
			mini3.setLayoutY(275);
			System.out.println("Cube");
			break;
		case 4:
			//T
			mini.setLayoutX(500);
			mini.setLayoutY(250);
			mini1.setLayoutX(500);
			mini1.setLayoutY(275);
			mini2.setLayoutX(500);
			mini2.setLayoutY(300);
			mini3.setLayoutX(525);
			mini3.setLayoutY(275);
			System.out.println("T");
			break;
		case 5:
			//S1
			mini.setLayoutX(500);
			mini.setLayoutY(250);
			mini1.setLayoutX(500);
			mini1.setLayoutY(275);
			mini2.setLayoutX(525);
			mini2.setLayoutY(275);
			mini3.setLayoutX(525);
			mini3.setLayoutY(300);
			System.out.println("S1");
			break;
		case 6:
			//S2
			mini.setLayoutX(525);
			mini.setLayoutY(250);
			mini1.setLayoutX(525);
			mini1.setLayoutY(275);
			mini2.setLayoutX(500);
			mini2.setLayoutY(275);
			mini3.setLayoutX(500);
			mini3.setLayoutY(300);
			System.out.println("S2");
			break;
		}
	}

	private void gameOver() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("GameOver.fxml"));
		Parent root = loader.load();
		
		GameOverControlls controller = loader.getController();
		controller.setScore(Points);
		
		int highscore = Highscore.loadHighscore();
		if(Points>highscore) {
			Highscore.saveHighscore(Points);
			controller.setHighscore(Points);
		}else {
			controller.setHighscore(highscore);
		}
		
		stage = (Stage) this.root.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
}





