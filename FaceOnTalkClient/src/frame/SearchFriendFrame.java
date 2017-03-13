package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import connector.WebConnector;
import controller.ClientHandler;
import dto.MemberVO;
import request.Request;
import util.RegularExpression;

public class SearchFriendFrame extends JFrame implements ActionListener, FocusListener {
   private int admin;
   private java.util.List<MemberVO> searchList;   

   public SearchFriendFrame(int admin) {
		this.admin = admin;		
		initFrame();
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getActionCommand() == "검색") {
         searchHandler();
      } else if (e.getActionCommand() == "추가") {
         addHandler();
      }else if (e.getActionCommand() == "취소"){
         dispose();
      }
   }

   private void searchHandler() {
		btnAdd.setEnabled(true);
		Thread thread = new Thread() {
			public void run() {
				String value = searchField.getText();
				Request request = new Request("searchfriend");
				request.setAttribute("from", String.valueOf(admin));
				
				if (RegularExpression.isEmailExpression((value))) {
					System.out.println("email형식");
					request.setParameter("searchtype", "email");
				} else if (RegularExpression.isSearchPhoneExpression(value)) {
					System.out.println("phone형식");
					request.setParameter("searchtype", "phone");
					value = value.replaceAll("-", "");
				} else {
					System.out.println("name형식");
					request.setParameter("searchtype", "name");
				}

				request.setParameter("value", value);
				Request response = WebConnector.connect(request);
				Map<String, Boolean> errors = (Map<String, Boolean>) response.getAttribute("errors");
				displayList.removeAll();
				if (errors.isEmpty()) {
					searchList = (java.util.List<MemberVO>) response.getAttribute("searchresult");
					System.out.println("errors.isEmpty," + searchList.size());
					for (int i = 0; i < searchList.size(); i++) {
						MemberVO searchMember = searchList.get(i);
						displayList.add(searchMember.getName() + "(" + searchMember.getEmail() + ")");
					}
					invalidate();
					repaint();
				} else {					
					if (errors.get("cantfind")==Boolean.TRUE) {
						displayList.add(value + "가 없습니다.");
						btnAdd.setEnabled(false);
					} else if (errors.get("SQLException") == Boolean.TRUE) {
						JOptionPane.showMessageDialog(null, "서버와의 연결이 좋지 않습니다.");
					}
				}
			}
		};
		thread.start();
   }

   private void addHandler() {
       Request request = new Request("addfriend");
       int selectedIdx = displayList.getSelectedIndex();
       int to = searchList.get(selectedIdx).getId();      
       request.setParameter("from",String.valueOf(admin));
       request.setParameter("to",String.valueOf(to));
       ClientHandler.getInstance().send(request);
   }

   ////////////////////////////
   // GUI
   private final int WIDTH = 330, HEIGHT = 400;
   private final String DEFAULT_EXPALIN = " 이메일 or 핸드폰 or 이름";
   private JPanel contentPane = new JPanel();
   private JPanel panel = new JPanel();
   // 메인라벨
   private JLabel mainLabel = new JLabel("친 구 추 가");
   // 친구검색 txt,btn
   private JTextField searchField = new JTextField(DEFAULT_EXPALIN);
   private JButton btnSearch = makeButton("검색");
   // center
   private List displayList = new List();
   private JScrollPane scrollPane = new JScrollPane(displayList);
   // 하단버튼
   private JButton btnAdd = makeButton("추가");
   private JButton btnCancel = makeButton("취소");   

   private void initFrame() {
      setSize(WIDTH, HEIGHT);
      Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
      int xpos = (int) (screen.getWidth() / 2 - this.getWidth() / 2);
      int ypos = (int) (screen.getHeight() / 2 - this.getHeight() / 2);
      setLocation(xpos, ypos);
      setResizable(false);
      setVisible(true);

      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);
      panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
      panel.setBounds(0, 0, WIDTH, HEIGHT);
      contentPane.add(panel);
      panel.setLayout(null);
      panel.setBackground(new Color(255, 255, 255));
      // =====================
      // =====메인라벨
      panel.add(mainLabel);
      mainLabel.setFont(new Font("굴림체", Font.BOLD, 18));
      mainLabel.setBounds(105, 21, 116, 31);
      // =====================
      // =====친구정보 입력txt, btn
      panel.add(searchField); // txt
      searchField.addFocusListener(this);
      searchField.setBounds(20, 67, 210, 23);
      searchField.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
      searchField.setForeground(Color.GRAY);
      searchField.addFocusListener(this);
      panel.add(btnSearch); // btn
      btnSearch.setBounds(240, 67, 65, 23);
      // =====================
      // =====친구 리스트
      panel.add(scrollPane);
      scrollPane.setBounds(20, 100, 285, 180);
      // =====================
      // ===== 추가,취소btn
      panel.add(btnAdd); // 추가
      btnAdd.setBounds(70, 305, 65, 23);
      panel.add(btnCancel); // 취소
      btnCancel.setBounds(190, 305, 65, 23);
     
      searchField.addActionListener(event -> {
         searchHandler();
      });

      addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent e) {
            dispose();
         }
      });
      addWindowFocusListener(new WindowAdapter() {
    	  public void windowGainedFocus(WindowEvent e) {
        	  btnSearch.requestFocusInWindow();
          }    	  
      });
      setVisible(true);
   }

   private JButton makeButton(String value) { // btn
      JButton btn = new JButton(value);
      btn.addActionListener(this);
      btn.setBackground(new Color(255, 255, 255));
      btn.setForeground(Color.BLACK);
      btn.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
      return btn;
   }

   /////////////
   /////포커스리스너
   @Override
   public void focusGained(FocusEvent e) {
      // TODO Auto-generated method stub
      if(e.getSource() == searchField){
         if (searchField.getText().equals(DEFAULT_EXPALIN)) {
            searchField.setText("");
            searchField.setForeground(Color.BLACK);
         }
      }
   }
   @Override
   public void focusLost(FocusEvent e) {
      // TODO Auto-generated method stub
      if (searchField.getText().isEmpty()) {
         searchField.setForeground(Color.GRAY);         
         searchField.setText(DEFAULT_EXPALIN);
      }
   }
}