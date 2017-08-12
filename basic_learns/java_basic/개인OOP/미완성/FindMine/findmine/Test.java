package findmine;

import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Test extends JFrame implements MouseListener{
	private static int cnt = 1;
	private JButton btn1 = new JButton("BTN");
	private JButton btn2 = new JButton("BTN2");
	private int[] select = new int[2];
	private int idx = 0;
	
	public Test(){
		this.setBounds(120, 120, 100, 100);
		this.setLayout(new FlowLayout());
		this.add(btn1);
		this.add(btn2);
		btn1.addMouseListener(this);
		btn2.addMouseListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void print(){
		System.out.println(select[0]+","+select[1]);
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		JButton btn = (JButton)e.getSource();
		if(btn == btn1){
			btn2.doClick();
		}else
			btn1.doClick();
	
		
	}
	public void display(){
		System.out.println("select[0] = "+select[0]+", select[1] = "+select[1]);
	}
	private int prev ;
	public void mouseHandler(int sel){		
		select[idx] = sel;
		idx = (idx+1)%2;
		if(idx != 0){ //첫번째 호출			
			try{
				Thread.sleep(1000); // 기다림
			}catch(InterruptedException e2){}
			display();
			if(select[1]==0){
				if(select[0]==1)
					System.out.println("왼쪽");				
				else if(select[0]==3)
					System.out.println("오른쪽");
				select[0] = 0;				
			}else{
				if(select[0]==1 && select[1]==1)
					System.out.println("왼쪽");
				else if(select[0]==3&&select[1]==3)
					System.out.println("오른쪽");
				else
					System.out.println("양쪽");
				select[0]=0;
				select[1]=0;
			}
		}		
	}	

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("clicked : "+e.getButton());
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				new Test();
			}					
		});
			
	}
}