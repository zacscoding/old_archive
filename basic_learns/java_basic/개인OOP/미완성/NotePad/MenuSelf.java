
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class MyFrame08 extends Frame {
	// 상단 기본
	private MenuBar menuBar = new MenuBar();
	private Menu fileMenu = new Menu("파일");
	private Menu helpMenu = new Menu("도움말");

	// 상단 하위
	private MenuItem openMenuItem = new MenuItem("열기");
	private MenuItem saveMenuItem = new MenuItem("저장");
	private MenuItem diffNameSaveMenuItem = new MenuItem("다른 이름으로 저장");
	private MenuItem exitMenuItem = new MenuItem("종료");

	private MenuItem showHelpMenuItem = new MenuItem("버전보기");

	private Dialog helpDialog;
	private FileDialog fdlOpen;
	private FileDialog fdlSave;

	private TextArea txtDisplay = new TextArea();
	private Label labelVersion = new Label("버전 1.0 입니다.", Label.CENTER);
	private FileHandler fileHandler;
	
	
	private String fileName;
	private String path;

	public MyFrame08(String title) {
		super(title);
		this.init();
	}

	private void init() {
		///////////////////////////////////////////
		// 초기 설정
		this.setSize(600, 800);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = (int) (screen.getWidth() / 2.0 - this.getWidth() / 2.0);
		int ypos = (int) (screen.getHeight() / 2.0 - this.getHeight() / 2.0);
		this.setLocation(xpos, ypos);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		///////////////////////////////////////////
		this.setLayout(new BorderLayout());
		this.add(txtDisplay, BorderLayout.CENTER);
		txtDisplay.setFont(new Font("", Font.BOLD, 20));
		// UI 등록
		// 기본 상단
		this.setMenuBar(menuBar);
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);

		// 하위
		fileMenu.add(openMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.add(diffNameSaveMenuItem);
		fileMenu.add(exitMenuItem);

		helpMenu.add(showHelpMenuItem);

		// 버전보기 이벤트 핸들링
		showHelpMenuItem.addActionListener(event -> {
			showVersion();
		});

		// 파일 열기 이벤트 핸들링
		openMenuItem.addActionListener(event -> fileOpenHandler());
		// 파일 저장 이벤트 핸들링
		saveMenuItem.addActionListener(event -> fileSaveHandler());
		// 다른 이름 파일 저장 이벤트 핸들링
		diffNameSaveMenuItem.addActionListener(event -> newNameSaveHandler() );
		// 종료 이벤트 핸들링
		exitMenuItem.addActionListener(event -> {
			System.exit(0);
		});
	}
	
	//파일 열기 이벤트 핸들링
	private void fileOpenHandler(){
		fdlOpen = new FileDialog(this, "열기", FileDialog.LOAD);
		fdlOpen.setVisible(true);
		fileName = fdlOpen.getFile();
		path = fdlOpen.getDirectory();
		if (fileName != null) {
			if (fileHandler == null)
				fileHandler = new FileHandler(fileName, path, txtDisplay);
			else
				fileHandler.setFilePath(fileName, path);
			fileHandler.readFile();
		}
	}
	
	//기존 이름으로 저장 이벤트 핸들링
	private void fileSaveHandler(){
		if(fileName == null){
			newNameSaveHandler();				
		}else{
			if (fileHandler == null)
				fileHandler = new FileHandler(fileName, path, txtDisplay);
			else
				fileHandler.setFilePath(fileName, path);
			fileHandler.saveFile();
		}
	}
	
	//새로운 이름으로 저장
	private void newNameSaveHandler(){
		fdlSave = new FileDialog(this, "저장", FileDialog.SAVE);
		fdlSave.setVisible(true);
		String file = fdlSave.getFile();
		String path = fdlSave.getDirectory();
		if (file != null) {
			if (fileHandler == null)
				fileHandler = new FileHandler(file, path, txtDisplay);
			else
				fileHandler.setFilePath(file, path);

			fileHandler.saveFile();
		}
	}

	// 버전 보여줌
	private void showVersion() {
		helpDialog = new Dialog(this, "Show Version", true);
		helpDialog.setBounds(120, 120, 200, 200);
		helpDialog.setLayout(new BorderLayout());

		labelVersion.setFont(new Font("", Font.BOLD, 15));

		Button btnExit = new Button("종료");

		btnExit.addActionListener(event -> {
			helpDialog.setVisible(false);
			helpDialog = null;
		});

		Panel bottomPanel = new Panel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		bottomPanel.add(btnExit);

		helpDialog.add(labelVersion, BorderLayout.CENTER);
		helpDialog.add(bottomPanel, BorderLayout.SOUTH);

		helpDialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		helpDialog.setVisible(true);
	}
	
	public void start() {
		this.setVisible(true);
	}
	
	
	//////////////////////////////////////
	//파일 관련 클래스	
	class FileHandler {
		private String fileName;
		private String path;
		private TextArea txtDisplay;

		public FileHandler(String fileName, String path, TextArea txtDisplay) {
			this.fileName = fileName;
			this.path = path;
			this.txtDisplay = txtDisplay;
		}

		public void readFile() {
			txtDisplay.setText("");
			BufferedReader buffIn = null;
			try {
				buffIn = new BufferedReader(new FileReader(path + fileName));
				while (true) {
					String data = buffIn.readLine();
					if (data == null)
						break;
					displayText(data);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {buffIn.close();} catch (IOException e2) {}
			}
		}

		public void saveFile() {
			String saveData = txtDisplay.getText();
			BufferedWriter buffOut = null;
			try {
				buffOut = new BufferedWriter(new FileWriter(path + fileName));
				buffOut.write(saveData);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					buffOut.close();
				} catch (IOException e2) {
				}
			}
		}

		public void setFilePath(String fileName, String path) {
			this.fileName = fileName;
			this.path = path;
		}

		private void displayText(String data) {
			txtDisplay.append(data + "\n");
		}
	}
}

public class MenuSelf {
	public static void main(String[] args) {
		MyFrame08 frm = new MyFrame08("Test");
		frm.start();

	}
}
