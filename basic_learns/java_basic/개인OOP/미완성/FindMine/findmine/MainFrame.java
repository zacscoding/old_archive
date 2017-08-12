package findmine;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame implements MouseListener,ActionListener {
	//상수
	private final int[] DY = new int[] { -1, -1, -1, 0, 0, 1, 1, 1 };
	private final int[] DX = new int[] { -1, 0, 1, -1, 1, -1, 0, 1 };
	private final int SIZE = 9;
	private final int NUM_OF_MINE = 10;

	//variables
	private int[][] board;	
	private boolean isGamming;
	private int flagCount;

	//UI코드
	private JButton btnStartStop;
	private JButton btnReset;
	private JButton btnExit;
	private MineButton[][] mineBtn;
	private TimeThread timeThread;
	private JLabel timeLabel;
	private JLabel timeDisplayLabel;
	private int timeHistory;
	private JPanel centerPanel;

	//constructor
	public MainFrame() {
		super("Zac`s Fine Mine Game");
		init();
	}
	
	private void init() {
		///////////////////////////////////////////
		// 초기설정 JFrame 설정
//		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
//		int xpos = (int) (screen.getWidth() / 2.0 - this.getWidth());
//		int ypos = (int) (screen.getHeight() / 2.0 - this.getHeight());
		setBounds(400, 200, 600, 600);
		setResizable(false);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		///////////////////////////////////////////

		makeCenterPanel();
		btnStartStop = makeButton("Start");
		btnReset = makeButton("Reset");
		btnExit = makeButton("Exit");		
		timeLabel = makeLabel("경과 시간 : ",JLabel.CENTER);
		timeDisplayLabel = makeLabel("",JLabel.CENTER);		
		
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(1, 5));
		northPanel.add(timeLabel);

		northPanel.add(timeDisplayLabel);
		northPanel.add(btnStartStop);
		northPanel.add(btnReset);
		northPanel.add(btnExit);

		add(centerPanel, BorderLayout.CENTER);
		add(northPanel, BorderLayout.NORTH);
		setVisible(true);
	}

	private JButton makeButton(String caption) {
		JButton btn = new JButton(caption);
		btn.setFont(new Font("", Font.BOLD, 15));
		btn.addActionListener(this);
		return btn;
	}
	
	private JLabel makeLabel(String caption,int position){
		JLabel label = new JLabel(caption,position);
		label.setFont(new Font("",Font.BOLD,15));
		return label;
	}

	private void makeCenterPanel() {
		flagCount = NUM_OF_MINE;
		makeMine();
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(SIZE, SIZE));
		mineBtn = new MineButton[SIZE][SIZE];
		for (int i = 0; i < mineBtn.length; i++) {
			for (int j = 0; j < mineBtn[i].length; j++) {
				mineBtn[i][j] = new MineButton(board[i][j], i, j);
				mineBtn[i][j].addMouseListener(this);
				centerPanel.add(mineBtn[i][j]);
			}
		}
	}		
	@Override
	public void actionPerformed(ActionEvent event) {
		String comm = event.getActionCommand();		
		if(comm.equals("Start")){
			btnStartStop.setText("Pause");
			isGamming = true;
			timeThread = new TimeThread(timeDisplayLabel);
			timeThread.start();			
		}else if(comm.equals("Pause")){
			isGamming = false;
			btnStartStop.setText("Start");
			timeThread.setPause();			
		}else if(comm.equals("Reset")){
			setVisible(false);
			remove(centerPanel);
			makeCenterPanel();
			add(centerPanel, BorderLayout.CENTER);
			btnStartStop.setText("Start");
			timeHistory = 1;
			timeDisplayLabel.setText("0");
			repaint();
			setVisible(true);			
		}else if(comm.equals("Exit")){
			System.exit(0);
		}	
	}

	// 테스트 코드(보드판 출력하기)
	public void showBoard() {
		System.out.println("-------------------------------------");
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + "  ");
			}
			System.out.println();
		}
		System.out.println("-------------------------------------");
	}
	
	
	/*
	 * 지뢰 생성 
	 */
	private void makeMine() {
		board = new int[SIZE][SIZE]; // 2차원 배열에 지뢰 정보 등록
		int limit = NUM_OF_MINE;
		while (limit > 0) {
			int row = (int) (Math.random() * board.length);
			int col = (int) (Math.random() * board.length);
			// int isMine = (int) (Math.random() * (SIZE * SIZE / NUM_OF_MINE));
			if (board[row][col] != 9) {
				board[row][col] = 9;
				increaseNumber(row, col);
				limit--;
			}
		}
		showBoard();
	}
	// 지뢰 주변 숫자 증가
	private void increaseNumber(int y, int x) {
		for (int dir = 0; dir < DX.length; dir++) {
			int row = y + DY[dir], col = x + DX[dir];
			if (isRange(row, col)) { // 인덱스 넘어가지 않으면
				if (board[row][col] != 9)
					board[row][col]++;
			}
		}
	}	
	// 배열의 인덱스안에 있는지 확인
		private boolean isRange(int y, int x) {
			if (x < 0 || x >= board.length || y < 0 || y >= board.length)
				return false;
			return true;
		}

	/*
	 * 게임 핸들링	
	 */		
	// MouseEvent Handler
	public void mouseReleased(MouseEvent event) {
		// //양쪽버튼 핸들링 해보기
		// select[idx] = event.getButton();
		// idx = (idx+1)%2;
		if (isGamming) {
			MineButton selectedBtn = (MineButton) event.getSource();
			int x = selectedBtn.getCol();
			int y = selectedBtn.getRow();
			int select = event.getButton();

			if (selectedBtn.isClicked()) { //버튼이 클릭되었던 상태이면 button2만 핸들링
				if (select == MouseEvent.BUTTON2) 
					confirmAroundHandler(y, x);
				return;
			}  			
			
			if (select == MouseEvent.BUTTON1) { // 왼쪽 버튼
				if (board[y][x] == 9) { // 폭탄이면
					selectedBtn.showMineImage(); // 마인 이미지로 바꾸기
					gameOver(); //게임종료
				} else { //숫자이면
					if (board[y][x] == 0) //0이면
						checkAroundWithZero(y, x);
					else //1~8이면
						selectedBtn.showNumberImage();
				}
			} else if (select == MouseEvent.BUTTON3) { // 오른쪽 버튼
				//폭탄인데 체크하면 -1 // 폭탄인데 체크 풀면  +1
				//숫자인데 체크하면 +1 // 숫자인데 체크 풀면 -1
				flagCount += (selectedBtn.changeFlagImage(board[y][x]==9));
				//=>정확히 지뢰에 깃발을 체크해야만 flagCount == 0이됨
			}
			
			//게임 성공이면
			if (flagCount == 0) {
				timeThread.setPause();
				isGamming = false;
				JOptionPane.showMessageDialog(this, "Clear Stage!!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	// 0주변 체크 해줌
	private void checkAroundWithZero(int y, int x) {
		//기저사례
		if (board[y][x] == 9) //폭탄이면 리턴
			return;
		if (mineBtn[y][x].isClicked() || mineBtn[y][x].isChecked()) //클릭됬거나 깃발로 체크했으면 리턴
			return;
		
		//0인 버튼의 이미지를 바꿔줌
		mineBtn[y][x].showNumberImage();
		//0인 버튼  주변 검사
		for (int k = 0; k < DX.length; k++) {
			int row = y + DY[k], col = x + DX[k];
			if (isRange(row, col)) { // 인덱스 넘어가지 않으면
				if (board[row][col] != 9) { //지뢰가 아니면
					if (board[row][col] == 0) //0이면 재귀
						checkAroundWithZero(row, col);
					else //숫자면 숫자 보여줌
						mineBtn[row][col].showNumberImage();
				}
			}
		}
	}
	
	//2) 양쪽 클릭시 확인해주는 핸들러
	private void confirmAroundHandler(int row, int col) {
		HashMap<String, List<Coordinate>> hMap = getAroundMineState(row, col);

		List<Coordinate> nMineList = hMap.get("notCheckedMine");
		List<Coordinate> cNumberList = hMap.get("checkedNumber");
		List<Coordinate> nNumberList = hMap.get("notCheckedNumber");
		
		if (nMineList == null) { // 주변에 체크되지 않은 지뢰가 없으면			
			if (nNumberList != null) {//주변 숫자들 이미지 바꿔주기
				for (int i = 0; i < nNumberList.size(); i++) {
					Coordinate coord = nNumberList.get(i);					
					mineBtn[coord.y][coord.x].showNumberImage();
				}
			} 
		} else { // 주변에 체크되지 않은 지뢰가 존재
			if (cNumberList != null) { // 숫자에 깃발로 체크한 경우 게임 오버
				for (int i = 0; i < nMineList.size(); i++) {
					Coordinate coord = nMineList.get(i);					
					mineBtn[coord.y][coord.x].showMineImage();
				}
				for(int i=0;i<cNumberList.size();i++){
					Coordinate coord = cNumberList.get(i);					
					mineBtn[coord.y][coord.x].showWrongCheck();
				}
				gameOver();
				return;
			}			
			//주변 지뢰버튼들 doClick()효과 주기
			for (int i = 0; i < nMineList.size(); i++) {
				Coordinate coord = nMineList.get(i);				
				mineBtn[coord.y][coord.x].doClick();
			}
			if (nNumberList != null) {
				for (int i = 0; i < nNumberList.size(); i++) {
					Coordinate coord = nNumberList.get(i);					
					mineBtn[coord.y][coord.x].doClick();
				}
			}
		}
	}
	//board의 y,x를 담는 클래스
	class Coordinate {
		int x, y;
		public Coordinate(int y, int x) {
			this.y = y;
			this.x = x;
		}
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
	}	
	//양쪽 버튼 클릭 시  주변의 상태를 HashMap에 담음
	private HashMap<String, List<Coordinate>> getAroundMineState(int row, int col) {
		HashMap<String, List<Coordinate>> hMap = new HashMap<>();
		List<Coordinate> notCheckedMineList = new ArrayList<>(8); // 체크안된 폭탄
		List<Coordinate> checkedNumberList = new ArrayList<>(8); // 체크된 숫자
		List<Coordinate> notCheckedNumberList = new ArrayList<>(8); // 체크 안된 숫자
		
		for (int dir = 0; dir < DX.length; dir++) {
			int nextRow = row + DY[dir], nextCol = col + DX[dir];
			if (isRange(nextRow, nextCol)) { // 인덱스 넘어가지 않으면
				if (board[nextRow][nextCol] == 9) {
					if (!mineBtn[nextRow][nextCol].isChecked())					
						notCheckedMineList.add(new Coordinate(nextRow, nextCol));
				} else {
					if (!mineBtn[nextRow][nextCol].isClicked()) {
						if (mineBtn[nextRow][nextCol].isChecked())
							checkedNumberList.add(new Coordinate(nextRow, nextCol));
						else
							notCheckedNumberList.add(new Coordinate(nextRow, nextCol));
					}
				}
			}
		}
		if (!notCheckedMineList.isEmpty())
			hMap.put("notCheckedMine", notCheckedMineList);
		if (!checkedNumberList.isEmpty())
			hMap.put("checkedNumber", checkedNumberList);
		if (!notCheckedNumberList.isEmpty())
			hMap.put("notCheckedNumber", notCheckedNumberList);
		return hMap;
	}
	
	private void gameOver() {
		timeThread.setPause(); // 시간 종료
		isGamming = false;
		btnStartStop.setText("Game Over");
		JOptionPane.showMessageDialog(this, "Game Over!!", "END", JOptionPane.WARNING_MESSAGE);
	}

	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	// TimeThread
	class TimeThread extends Thread {
		private JLabel timeLabel;
		private boolean isGamming = true;

		public TimeThread(JLabel timeLabel) {
			this.timeLabel = timeLabel;
		}

		public void setPause() {
			isGamming = false;
		}

		public void run() {
			while (isGamming) {
				try {
					timeLabel.setText(String.valueOf(timeHistory));
					timeHistory++;
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	// Main method
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainFrame();
			}
		});
	}	
}
