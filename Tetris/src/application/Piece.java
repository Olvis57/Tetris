package application;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Piece {
	
	public int shape;
	public int nextshape;
	public Color color; 
	public Color nextcolor; 
	private int position;
	private Rectangle square;
	private Rectangle square1;
	private Rectangle square2;
	private Rectangle square3;
	private int crazyNess = 7;
	
	public Piece(Rectangle square, Rectangle square1, Rectangle square2,Rectangle square3){
		Random rand = new Random();
		nextshape = rand.nextInt(6);
		
		double r = rand.nextDouble();
		double g = rand.nextDouble();
		double b = rand.nextDouble();
		nextcolor = new Color(r, g, b, 1);
		
		this.square = square;
		this.square1 = square1;
		this.square2 = square2;
		this.square3 = square3;
	}
	
	public void Down() {
		square.setLayoutY(square.getLayoutY()+45);
		square1.setLayoutY(square1.getLayoutY()+45);
		square2.setLayoutY(square2.getLayoutY()+45);
		square3.setLayoutY(square3.getLayoutY()+45);
	}
	
	public void Right() {
		square.setLayoutX(square.getLayoutX()+45);
		square1.setLayoutX(square1.getLayoutX()+45);
		square2.setLayoutX(square2.getLayoutX()+45);
		square3.setLayoutX(square3.getLayoutX()+45);
	}
	
	public void Left() {
		square.setLayoutX(square.getLayoutX()-45);
		square1.setLayoutX(square1.getLayoutX()-45);
		square2.setLayoutX(square2.getLayoutX()-45);
		square3.setLayoutX(square3.getLayoutX()-45);
	}
	
	public void xDown(int x) {
		square.setLayoutY(square.getLayoutY()+(45*x));
		square1.setLayoutY(square1.getLayoutY()+(45*x));
		square2.setLayoutY(square2.getLayoutY()+(45*x));
		square3.setLayoutY(square3.getLayoutY()+(45*x));
	}
	
	public void xLeft(int x) {
		square.setLayoutX(square.getLayoutX()-(45*x));
		square1.setLayoutX(square1.getLayoutX()-(45*x));
		square2.setLayoutX(square2.getLayoutX()-(45*x));
		square3.setLayoutX(square3.getLayoutX()-(45*x));
	}
	
	public void xRight(int x) {
		square.setLayoutX(square.getLayoutX()+(45*x));
		square1.setLayoutX(square1.getLayoutX()+(45*x));
		square2.setLayoutX(square2.getLayoutX()+(45*x));
		square3.setLayoutX(square3.getLayoutX()+(45*x));
	}
	
	public void Turn(int row, int column, int[][] occupancy) {
		int i = 1;
		int down = 0;
		int left = 0;
		int itr = 0;
		int checkBox;
		
		switch(shape) {
		case 0:
			switch(position) {
			case 1:
					if(row<1 || row>20 || column>9 || column<0 || occupancy[row-1][column+2]==1 || occupancy[row][column+2]==1 ||occupancy[row+1][column+2]==1 || occupancy[row+2][column+2]==1 ){
						
						while(i==1) {
							checkBox = checkBox(4,column-left,row-1+down,occupancy);
							if(checkBox==0) {
								break;
							}else if(itr>crazyNess) {
								return;
							}else if(itr>2) {
								down--;
							}else if(checkBox==1) {
								left++;
							}else if(checkBox==2) {
								down--;
							}else if(checkBox==4) {
								left--;
							}else if(checkBox==3) {
								down++;
							}
							itr ++;
							
						}
							xLeft(left);
							xDown(down);
					}
					
					square1.setLayoutX(square2.getLayoutX());
					square.setLayoutY(square1.getLayoutY()-45);
					square.setLayoutX(square2.getLayoutX());
					square3.setLayoutY(square1.getLayoutY()+90);
					square3.setLayoutX(square2.getLayoutX());
					square2.setLayoutY(square1.getLayoutY()+45);
					
					position = 2;
				break;
			case 2:
					if(column>8 || column-2<0 || row>22 || occupancy[row+2][column+1]==1 || occupancy[row+2][column]==1 || occupancy[row+2][column-1]==1 || occupancy[row+2][column-2]==1){
						
						while(i==1) {
							
							checkBox = checkBox(4,column-2-left,row+down,occupancy);
							
							if(checkBox==0) {
								break;
							}else if(itr>crazyNess) {
								return;
							}else if(itr>2) {
								down--;
							}else if(checkBox==1) {
								left++;
							}else if(checkBox==2) {
								down--;
							}else if(checkBox==4) {
								left--;
							}else if(checkBox==3) {
								down++;
							}
							itr ++;
							
						}
							xLeft(left);
							xDown(down);
					}
					square.setLayoutY(square2.getLayoutY());
					square.setLayoutX(square2.getLayoutX()-90);
					square1.setLayoutY(square2.getLayoutY());
					square1.setLayoutX(square2.getLayoutX()-45);
					square3.setLayoutY(square2.getLayoutY());
					square3.setLayoutX(square2.getLayoutX()+45);
					position = 3;
				break;
			case 3:
					if(row<2 || row>21 || column>9 || column<0 || occupancy[row-1][column+1]==1 || occupancy[row-2][column+1]==1 || occupancy[row+1][column+1]==1 || occupancy[row][column+1]==1){
						
						while(i==1) {
							
							checkBox = checkBox(4,column-left,row-2+down,occupancy);
							
							if(checkBox==0) {
								break;
							}else if(itr>crazyNess) {
								return;
							}else if(itr>2) {
								down--;
							}else if(checkBox==1) {
								left++;
							}else if(checkBox==2) {
								down--;
							}else if(checkBox==4) {
								left--;
							}else if(checkBox==3) {
								down++;
							}
							itr ++;
							
						}
							xLeft(left);
							xDown(down);
					}
					
					square.setLayoutY(square1.getLayoutY()-90);
					square.setLayoutX(square1.getLayoutX());
					square2.setLayoutY(square1.getLayoutY()-45);
					square2.setLayoutX(square1.getLayoutX());
					square3.setLayoutY(square1.getLayoutY()+45);
					square3.setLayoutX(square1.getLayoutX());
					position = 4;
				break;
			case 4:
					if(column>7 || column-1<0 || row>22 || occupancy[row+1][column+1]==1 || occupancy[row+1][column+2]==1 || occupancy[row+1][column-1]==1 || occupancy[row+1][column]==1){
						
						while(i==1) {
							
							checkBox = checkBox(4,column-1-left,row-2+down,occupancy);
							
							if(checkBox==0) {
								break;
							}else if(itr>crazyNess) {
								return;
							}else if(itr>2) {
								down--;
							}else if(checkBox==1) {
								left++;
							}else if(checkBox==2) {
								down--;
							}else if(checkBox==4) {
								left--;
							}else if(checkBox==3) {
								down++;
							}
							itr ++;
							
						}
							xLeft(left);
							xDown(down);
					}
				square.setLayoutY(square1.getLayoutY()-45);
				square.setLayoutX(square1.getLayoutX()-45);
				square2.setLayoutY(square1.getLayoutY()-45);
				square2.setLayoutX(square1.getLayoutX()+45);
				square3.setLayoutY(square1.getLayoutY()-45);
				square3.setLayoutX(square1.getLayoutX()+90);
				square1.setLayoutY(square1.getLayoutY()-45);
				position = 1;
				break;
			}
			break;
		case 1:
			switch(position) {
			case 1:
				
					if(column==0 || row>22 || column<0 || column>9 || occupancy[row-1][column-1]==1 || occupancy[row-1][column]==1 || occupancy[row][column+1]==1 || occupancy[row-1][column+1]==1) {
						while(i==1) {
							
							checkBox = checkBox(3,column-1-left,row-2+down,occupancy);
							
							if(checkBox==0) {
								break;
							}else if(itr>crazyNess) {
								return;
							}else if(itr>2) {
								down--;
							}else if(checkBox==1) {
								left++;
							}else if(checkBox==2) {
								down--;
							}else if(checkBox==4) {
								left--;
							}else if(checkBox==3) {
								down++;
							}
							itr ++;
							
						}
							xLeft(left);
							xDown(down);
					}
				
				square.setLayoutX(square1.getLayoutX()-45);
				square.setLayoutY(square1.getLayoutY());
				square2.setLayoutX(square1.getLayoutX()+45);
				square2.setLayoutY(square1.getLayoutY());
				square3.setLayoutY(square1.getLayoutY()+45);
				position = 2;
				break;
			case 2:
				if(row>22 || column<0 || column>9 || occupancy[row-1][column+1]==1 || occupancy[row][column+1]==1 || occupancy[row+1][column+1]==1 || occupancy[row+1][column]==1) {
					while(i==1) {
						
						checkBox = checkBox(3,column-left,row-1+down,occupancy);
						
						if(checkBox==0) {
							break;
						}else if(itr>crazyNess) {
							return;
						}else if(itr>2) {
							down--;
						}else if(checkBox==1) {
							left++;
						}else if(checkBox==2) {
							down--;
						}else if(checkBox==4) {
							left--;
						}else if(checkBox==3) {
							down++;
						}
						itr ++;
						
					}
						xLeft(left);
						xDown(down);
				}
				square.setLayoutX(square1.getLayoutX());
				square.setLayoutY(square1.getLayoutY()-45);
				square2.setLayoutX(square1.getLayoutX());
				square2.setLayoutY(square1.getLayoutY()+45);
				square3.setLayoutX(square1.getLayoutX()-45);
				position = 3;
				break;
			case 3:				
					if(column>8 || row>22 || column<0 || occupancy[row+1][column+1]==1 || occupancy[row+1][column]==1 || occupancy[row][column-1]==1 || occupancy[row+1][column-1]==1){
						while(i==1) {
							
							checkBox = checkBox(3,column-1-left,row+down,occupancy);
							
							if(checkBox==0) {
								break;
							}else if(itr>crazyNess) {
								return;
							}else if(itr>2) {
								down--;
							}else if(checkBox==1) {
								left++;
							}else if(checkBox==2) {
								down--;
							}else if(checkBox==4) {
								left--;
							}else if(checkBox==3) {
								down++;
							}
							itr ++;
							
						}
							xLeft(left);
							xDown(down);
					}
					
				square.setLayoutX(square1.getLayoutX()+45);
				square.setLayoutY(square1.getLayoutY());
				square2.setLayoutX(square1.getLayoutX()-45);
				square2.setLayoutY(square1.getLayoutY());
				square3.setLayoutY(square1.getLayoutY()-45);
				position = 4;
				break;
			case 4:
					if(row>21 || column<0 || column>9 || occupancy[row+1][column-1]==1 || occupancy[row][column-1]==1 || occupancy[row-1][column-1]==1 || occupancy[row-1][column]==1){
						while(i==1) {
							
							checkBox = checkBox(3,column-2-left,row-1+down,occupancy);
							
							if(checkBox==0) {
								break;
							}else if(itr>crazyNess) {
								return;
							}else if(itr>2) {
								down--;
							}else if(checkBox==1) {
								left++;
							}else if(checkBox==2) {
								down--;
							}else if(checkBox==4) {
								left--;
							}else if(checkBox==3) {
								down++;
							}
							itr ++;
							
						}
							xLeft(left);
							xDown(down);
					}

				square.setLayoutX(square1.getLayoutX());
				square.setLayoutY(square1.getLayoutY()+45);
				square2.setLayoutX(square1.getLayoutX());
				square2.setLayoutY(square1.getLayoutY()-45);
				square3.setLayoutX(square1.getLayoutX()+45);
				position = 1;
				break;
			}
			break;
		case 2:
			switch(position) {
			case 1:
					if(column>8 || row>22 || column<0 || occupancy[row-1][column-1]==1 || occupancy[row-1][column]==1 || occupancy[row-1][column+1]==1 || occupancy[row-2][column+1]==1 ){
						while(i==1) {
							
							checkBox = checkBox(3,column-1-left,row-2+down,occupancy);
							
							if(checkBox==0) {
								break;
							}else if(itr>crazyNess) {
								return;
							}else if(itr>2) {
								down--;
							}else if(checkBox==1) {
								left++;
							}else if(checkBox==2) {
								down--;
							}else if(checkBox==4) {
								left--;
							}else if(checkBox==3) {
								down++;
							}
							itr ++;
							
						}
							xLeft(left);
							xDown(down);
					}
					
				square.setLayoutX(square1.getLayoutX()-45);
				square.setLayoutY(square1.getLayoutY());
				square2.setLayoutX(square1.getLayoutX()+45);
				square2.setLayoutY(square1.getLayoutY());
				square3.setLayoutX(square1.getLayoutX()+45);
				position = 2;
				break;
			case 2:
					
					if(row>21 || column<0 || column>9 || occupancy[row+1][column+1]==1 || occupancy[row+1][column+2]==1 || occupancy[row-1][column+1]==1 || occupancy[row][column+1]==1){
						while(i==1) {
							
							checkBox = checkBox(3,column-left,row-1+down,occupancy);
							
							if(checkBox==0) {
								break;
							}else if(itr>crazyNess) {
								return;
							}else if(itr>2) {
								down--;
							}else if(checkBox==1) {
								left++;
							}else if(checkBox==2) {
								down--;
							}else if(checkBox==4) {
								left--;
							}else if(checkBox==3) {
								down++;
							}
							itr ++;
						}
							xLeft(left);
							xDown(down);
					}
					
				square.setLayoutX(square1.getLayoutX());
				square.setLayoutY(square1.getLayoutY()-45);
				square2.setLayoutX(square1.getLayoutX());
				square2.setLayoutY(square1.getLayoutY()+45);
				square3.setLayoutY(square1.getLayoutY()+45);
				position = 3;
				break;
			case 3:
					if(column==0 || row>22 || column<0 || column>9 || occupancy[row+1][column-1]==1 || occupancy[row+2][column-1]==1 || occupancy[row+1][column+1]==1 || occupancy[row+1][column]==1){
						while(i==1) {
							
							checkBox = checkBox(3,column-1-left,row+down,occupancy);
							
							if(checkBox==0) {
								break;
							}else if(itr>crazyNess) {
								return;
							}else if(itr>2) {
								down--;
							}else if(checkBox==1) {
								left++;
							}else if(checkBox==2) {
								down--;
							}else if(checkBox==4) {
								left--;
							}else if(checkBox==3) {
								down++;
							}
							itr ++;
						}
							xLeft(left);
							xDown(down);
					}
					
				square.setLayoutX(square1.getLayoutX()+45);
				square.setLayoutY(square1.getLayoutY());
				square2.setLayoutX(square1.getLayoutX()-45);
				square2.setLayoutY(square1.getLayoutY());
				square3.setLayoutX(square1.getLayoutX()-45);
				position = 4;
				break;
			case 4:
				
					if(row>22 || column<0 || column>9 || occupancy[row-1][column-1]==1 || occupancy[row-1][column-2]==1 || occupancy[row+1][column-1]==1 || occupancy[row][column-1]==1) {
						while(i==1) {
							
							checkBox = checkBox(3,column-2-left,row-1+down,occupancy);
							
							if(checkBox==0) {
								break;
							}else if(itr>crazyNess) {
								return;
							}else if(itr>2) {
								down--;
							}else if(checkBox==1) {
								left++;
							}else if(checkBox==2) {
								down--;
							}else if(checkBox==4) {
								left--;
							}else if(checkBox==3) {
								down++;
							}
							itr ++;
						}
							xLeft(left);
							xDown(down);
					}
					
				square.setLayoutX(square1.getLayoutX());
				square.setLayoutY(square1.getLayoutY()+45);
				square2.setLayoutX(square1.getLayoutX());
				square2.setLayoutY(square1.getLayoutY()-45);
				square3.setLayoutY(square1.getLayoutY()-45);
				position = 1;
				break;
			}
			break;
		case 3:
			break;
			
		case 4:
			switch(position) {
			case 1:
				if(column>8 || occupancy[row-1][column+1]==1) {
					while(i==1) {
						
						checkBox = checkBox(3,column-1-left,row-2+down,occupancy);
						
						if(checkBox==0) {
							break;
						}else if(itr>crazyNess) {
							return;
						}else if(itr>2) {
							down--;
						}else if(checkBox==1) {
							left++;
						}else if(checkBox==2) {
							down--;
						}else if(checkBox==4) {
							left--;
						}else if(checkBox==3) {
							down++;
						}
						itr ++;
					}
						xLeft(left);
						xDown(down);
				}
				
				square.setLayoutX(square1.getLayoutX()+45);
				square.setLayoutY(square1.getLayoutY());
				position = 2;
				break;
			case 2:
				
				if(row>21 || occupancy[row+1][column-1]==1) {
					while(i==1) {
						
						checkBox = checkBox(3,column-2-left,row-1+down,occupancy);
						
						if(checkBox==0) {
							break;
						}else if(itr>crazyNess) {
							return;
						}else if(itr>2) {
							down--;
						}else if(checkBox==1) {
							left++;
						}else if(checkBox==2) {
							down--;
						}else if(checkBox==4) {
							left--;
						}else if(checkBox==3) {
							down++;
						}
						itr ++;
					}
						xLeft(left);
						xDown(down);
				}
				square3.setLayoutX(square1.getLayoutX());
				square3.setLayoutY(square1.getLayoutY()+45);
				position = 3;
				break;
			case 3:
				if(column<2 || occupancy[row][column-2]==1) {
					while(i==1) {
						
						checkBox = checkBox(3,column-2-left,row-1+down,occupancy);
						
						if(checkBox==0) {
							break;
						}else if(itr>crazyNess) {
							return;
						}else if(itr>2) {
							down--;
						}else if(checkBox==1) {
							left++;
						}else if(checkBox==2) {
							down--;
						}else if(checkBox==4) {
							left--;
						}else if(checkBox==3) {
							down++;
						}
						itr ++;
					}
						xLeft(left);
						xDown(down);
				}
				square2.setLayoutY(square1.getLayoutY());
				square2.setLayoutX(square2.getLayoutX()-45);
				position = 4;
				break;
			case 4:
				if(row==0 || occupancy[row-1][column-1]==1) {
					while(i==1) {
						
						checkBox = checkBox(3,column-2-left,row-1+down,occupancy);
						
						if(checkBox==0) {
							break;
						}else if(itr>crazyNess) {
							return;
						}else if(itr>2) {
							down--;
						}else if(checkBox==1) {
							left++;
						}else if(checkBox==2) {
							down--;
						}else if(checkBox==4) {
							left--;
						}else if(checkBox==3) {
							down++;
						}
						itr ++;
					}
						xLeft(left);
						xDown(down);
				}
				square.setLayoutX(square1.getLayoutX());
				square.setLayoutY(square1.getLayoutY()+45);
				square2.setLayoutX(square1.getLayoutX());
				square2.setLayoutY(square1.getLayoutY()-45);
				square3.setLayoutX(square1.getLayoutX()-45);
				square3.setLayoutY(square1.getLayoutY());
				position = 1;
				break;
			}
			break;
		case 5:
			switch(position) {
			case 1:
				if(column<2 || occupancy[row][column-1]==1 || occupancy[row][column-2]==1) {
					while(i==1) {
						
						checkBox = checkBox(3,column-2-left,row-2+down,occupancy);
						
						if(checkBox==0) {
							break;
						}else if(itr>crazyNess) {
							return;
						}else if(itr>2) {
							down--;
						}else if(checkBox==1) {
							left++;
						}else if(checkBox==2) {
							down--;
						}else if(checkBox==4) {
							left--;
						}else if(checkBox==3) {
							down++;
						}
						itr ++;
					}
						xLeft(left);
						xDown(down);
				}
				
				square2.setLayoutY(square.getLayoutY());
				square.setLayoutX(square3.getLayoutX()-45);
				position = 2;
				break;
			case 2:
				if(row<2 || occupancy[row-1][column]==1 || occupancy[row-2][column]==1) {
					while(i==1) {
						
						checkBox = checkBox(3,column-left,row-2+down,occupancy);
						
						if(checkBox==0) {
							break;
						}else if(itr>crazyNess) {
							return;
						}else if(itr>2) {
							down--;
						}else if(checkBox==1) {
							left++;
						}else if(checkBox==2) {
							down--;
						}else if(checkBox==4) {
							left--;
						}else if(checkBox==3) {
							down++;
						}
						itr ++;
					}
						xLeft(left);
						xDown(down);
				}
				square1.setLayoutX(square.getLayoutX());
				square.setLayoutY(square3.getLayoutY()-45);
				position = 3;
				break;
			case 3:
				if(column>7 || occupancy[row][column+1]==1 || occupancy[row][column+2]==1) {
					while(i==1) {
						
						checkBox = checkBox(3,column-left,row+down,occupancy);
						
						if(checkBox==0) {
							break;
						}else if(itr>crazyNess) {
							return;
						}else if(itr>2) {
							down--;
						}else if(checkBox==1) {
							left++;
						}else if(checkBox==2) {
							down--;
						}else if(checkBox==4) {
							left--;
						}else if(checkBox==3) {
							down++;
						}
						itr ++;
					}
						xLeft(left);
						xDown(down);
				}
				square2.setLayoutY(square.getLayoutY());
				square.setLayoutX(square3.getLayoutX()+45);
				position = 4;
				break;
			case 4:
				if(row>20 || occupancy[row+1][column]==1 || occupancy[row+2][column]==1) {
					while(i==1) {
						
						checkBox = checkBox(3,column-2-left,row+down,occupancy);
						
						if(checkBox==0) {
							break;
						}else if(itr>crazyNess) {
							return;
						}else if(itr>2) {
							down--;
						}else if(checkBox==1) {
							left++;
						}else if(checkBox==2) {
							down--;
						}else if(checkBox==4) {
							left--;
						}else if(checkBox==3) {
							down++;
						}
						itr ++;
					}
						xLeft(left);
						xDown(down);
				}
				square1.setLayoutX(square.getLayoutX());
				square.setLayoutY(square3.getLayoutY()+45);
				position = 1;
				break;
			}
			break;
		case 6:
			switch(position) {
			case 1:
				if(column<2 || occupancy[row+2][column]==1 || occupancy[row+1][column-2]==1) {
					while(i==1) {
						
						checkBox = checkBox(3,column-2-left,row+down,occupancy);
						
						if(checkBox==0) {
							break;
						}else if(itr>crazyNess) {
							return;
						}else if(itr>2) {
							down--;
						}else if(checkBox==1) {
							left++;
						}else if(checkBox==2) {
							down--;
						}else if(checkBox==4) {
							left--;
						}else if(checkBox==3) {
							down++;
						}
						itr ++;
					}
						xLeft(left);
						xDown(down);
				}
				square.setLayoutY(square2.getLayoutY());
				square1.setLayoutX(square3.getLayoutX()-45);
				position = 2;
				break;
			case 2:
				if(row<2 || occupancy[row][column-2]==1 || occupancy[row-2][column-1]==1) {
					while(i==1) {
						
						checkBox = checkBox(3,column-2-left,row-2+down,occupancy);
						
						if(checkBox==0) {
							break;
						}else if(itr>crazyNess) {
							return;
						}else if(itr>2) {
							down--;
						}else if(checkBox==1) {
							left++;
						}else if(checkBox==2) {
							down--;
						}else if(checkBox==4) {
							left--;
						}else if(checkBox==3) {
							down++;
						}
						itr ++;
					}
						xLeft(left);
						xDown(down);
				}
				square2.setLayoutY(square3.getLayoutY()-45);
				square.setLayoutX(square1.getLayoutX());
				position = 3;
				break;
			case 3:
				if(column>7 || occupancy[row-2][column]==1 || occupancy[row-1][column+2]==1) {
					while(i==1) {
						
						checkBox = checkBox(3,column-left,row-2+down,occupancy);
						
						if(checkBox==0) {
							break;
						}else if(itr>crazyNess) {
							return;
						}else if(itr>2) {
							down--;
						}else if(checkBox==1) {
							left++;
						}else if(checkBox==2) {
							down--;
						}else if(checkBox==4) {
							left--;
						}else if(checkBox==3) {
							down++;
						}
						itr ++;
					}
						xLeft(left);
						xDown(down);
				}
				square1.setLayoutX(square3.getLayoutX()+45);
				square.setLayoutY(square2.getLayoutY());
				position = 4;
				break;
			case 4:
				if(row>20 || occupancy[row][column+2]==1 || occupancy[row+2][column+1]==1) {
					while(i==1) {
						
						checkBox = checkBox(3,column-left,row+down,occupancy);
						
						if(checkBox==0) {
							break;
						}else if(itr>crazyNess) {
							return;
						}else if(itr>2) {
							down--;
						}else if(checkBox==1) {
							left++;
						}else if(checkBox==2) {
							down--;
						}else if(checkBox==4) {
							left--;
						}else if(checkBox==3) {
							down++;
						}
						itr ++;
					}
						xLeft(left);
						xDown(down);
				}
				square2.setLayoutY(square3.getLayoutY()+45);
				square.setLayoutX(square1.getLayoutX());
				position = 1;
				break;
			}
			break;
		}
	}
	
	public void newShape() {
		Random rand = new Random();
		shape = nextshape;
		nextshape = rand.nextInt(6);
		position = 1;
		
		color=nextcolor;
		
		double r = rand.nextDouble();
		double g = rand.nextDouble();
		double b = rand.nextDouble();
		
		nextcolor = new Color(r, g, b, 1);
		
		square.setFill(color);
		square1.setFill(color);
		square2.setFill(color);
		square3.setFill(color);
		
		//shape = 1;
		switch(shape) {
		case 0:
			//Stick
			square.setLayoutX(145);
			square.setLayoutY(2.5);
			square1.setLayoutX(190);
			square1.setLayoutY(2.5);
			square2.setLayoutX(235);
			square2.setLayoutY(2.5);
			square3.setLayoutX(280);
			square3.setLayoutY(2.5);
			break;
		case 1:
			//L1
			square.setLayoutX(190);
			square.setLayoutY(2.5);
			square1.setLayoutX(190);
			square1.setLayoutY(-42.5);
			square2.setLayoutX(190);
			square2.setLayoutY(-87.5);
			square3.setLayoutX(235);
			square3.setLayoutY(-87.5);
			break;
		case 2:
			//L2
			square.setLayoutX(235);
			square.setLayoutY(2.5);
			square1.setLayoutX(235);
			square1.setLayoutY(-42.5);
			square2.setLayoutX(235);
			square2.setLayoutY(-87.5);
			square3.setLayoutX(190);
			square3.setLayoutY(-87.5);
			break;
		case 3:
			//Cube
			square.setLayoutX(235);
			square.setLayoutY(2.5);
			square1.setLayoutX(235);
			square1.setLayoutY(-42.5);
			square2.setLayoutX(190);
			square2.setLayoutY(2.5);
			square3.setLayoutX(190);
			square3.setLayoutY(-42.5);
			break;
		case 4:
			//T
			square.setLayoutX(235);
			square.setLayoutY(2.5);
			square1.setLayoutX(235);
			square1.setLayoutY(-42.5);
			square2.setLayoutX(235);
			square2.setLayoutY(-87.5);
			square3.setLayoutX(190);
			square3.setLayoutY(-42.5);
			break;
		case 5:
			//S1
			square.setLayoutX(235);
			square.setLayoutY(2.5);
			square1.setLayoutX(235);
			square1.setLayoutY(-42.5);
			square2.setLayoutX(190);
			square2.setLayoutY(-87.5);
			square3.setLayoutX(190);
			square3.setLayoutY(-42.5);
			break;
		case 6:
			//S2
			square.setLayoutX(235);
			square.setLayoutY(-87.5);
			square1.setLayoutX(235);
			square1.setLayoutY(-42.5);
			square2.setLayoutX(190);
			square2.setLayoutY(2.5);
			square3.setLayoutX(190);
			square3.setLayoutY(-42.5);
			break;
			
		}
		
	}

	public void newShape(int shape, Color color) {
		this.color=color;
		this.shape=shape;
			
		square.setFill(color);
		square1.setFill(color);
		square2.setFill(color);
		square3.setFill(color);
		
		//shape = 1;
		switch(shape) {
		case 0:
			//Stick
			square.setLayoutX(145);
			square.setLayoutY(2.5);
			square1.setLayoutX(190);
			square1.setLayoutY(2.5);
			square2.setLayoutX(235);
			square2.setLayoutY(2.5);
			square3.setLayoutX(280);
			square3.setLayoutY(2.5);
			break;
		case 1:
			//L1
			square.setLayoutX(190);
			square.setLayoutY(2.5);
			square1.setLayoutX(190);
			square1.setLayoutY(-42.5);
			square2.setLayoutX(190);
			square2.setLayoutY(-87.5);
			square3.setLayoutX(235);
			square3.setLayoutY(-87.5);
			break;
		case 2:
			//L2
			square.setLayoutX(235);
			square.setLayoutY(2.5);
			square1.setLayoutX(235);
			square1.setLayoutY(-42.5);
			square2.setLayoutX(235);
			square2.setLayoutY(-87.5);
			square3.setLayoutX(190);
			square3.setLayoutY(-87.5);
			break;
		case 3:
			//Cube
			square.setLayoutX(235);
			square.setLayoutY(2.5);
			square1.setLayoutX(235);
			square1.setLayoutY(-42.5);
			square2.setLayoutX(190);
			square2.setLayoutY(2.5);
			square3.setLayoutX(190);
			square3.setLayoutY(-42.5);
			break;
		case 4:
			//T
			square.setLayoutX(235);
			square.setLayoutY(2.5);
			square1.setLayoutX(235);
			square1.setLayoutY(-42.5);
			square2.setLayoutX(235);
			square2.setLayoutY(-87.5);
			square3.setLayoutX(190);
			square3.setLayoutY(-42.5);
			break;
		case 5:
			//S1
			square.setLayoutX(235);
			square.setLayoutY(2.5);
			square1.setLayoutX(235);
			square1.setLayoutY(-42.5);
			square2.setLayoutX(190);
			square2.setLayoutY(-87.5);
			square3.setLayoutX(190);
			square3.setLayoutY(-42.5);
			break;
		case 6:
			//S2
			square.setLayoutX(235);
			square.setLayoutY(-87.5);
			square1.setLayoutX(235);
			square1.setLayoutY(-42.5);
			square2.setLayoutX(190);
			square2.setLayoutY(2.5);
			square3.setLayoutX(190);
			square3.setLayoutY(-42.5);
			break;
			
		}
		
	}
	
	private int checkBox(int size, int column, int row, int [][] occupancy){	
		if(column+size>10) {
			return 1;
		}
		if(column<0) {
			return 4;
		}
		if(row<0) {
			return 3;
		}
		if(row+size>23) {
			return 2;
		}
		
		int a = 0;
		int s = 0;
		int d = 0;

		
		if(size == 4) {
			for(int i = 0; i<size;i++) {
				for(int j=0;j<size;j++) {
					if(occupancy[row+i][column+j]==1) {
						if(j<2) {
							a++;
							
						}else {
							d++;
						}
						if(i>1) {
							s++;
						}
					}
				}
			}
		}else {
			for(int i = 0; i<size;i++) {
				for(int j=0;j<size;j++) {			
					if(occupancy[row+i][column+j]==1) {
						if(j==0) {
							a++;
						}else if(j==2){
							d++;
						}
						if(i>1) {
							s++;
						}
					}
				}
			}
		}
		if(a==0 && d==0 && s==0) {
			return 0;
		}
		int val = 4;
		if(d>=a) {
			val = 1;
		}
		if(s>=Math.max(a,d)) {
			val=2;
		}
		return val;
	}
	
	
}
