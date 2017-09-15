package frame;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.tree.DefaultMutableTreeNode;

import dto.MemberVO;

public class FriendsNode extends DefaultMutableTreeNode{
	private int id;
	private String name;
	private String email;
	private JMenu menu = new JMenu("kk");
	private Icon imageIcon = new ImageIcon("image/feedicon.png");
	public FriendsNode(MemberVO friend) {
		super(friend.getName()+"("+friend.getEmail()+")");
		id = friend.getId();
		name = friend.getName();
		email = friend.getEmail();	
	}	
	//getter
	public int getId() {
		return id;
	}	
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public JMenu getMenu() {
		return menu;
	}
}
