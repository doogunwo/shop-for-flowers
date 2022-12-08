package create_customer;

import java.awt.EventQueue;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.awt.event.ActionEvent;
import java.util.*;
import javax.swing.JTextField;
import javax.swing.JLabel;
public class create_customer extends Thread {
	
	
	
	public static void sounds() {
		try {
			File file = new File("C:\\Users\\danpe\\git\\shop-for-flowers\\src\\create_customer\\sounds.wav");
			
			 Clip clip = AudioSystem.getClip();
             clip.open(AudioSystem.getAudioInputStream(file));             
             clip.start();
		}
		catch (Exception e) {
			System.err.println("Put the music.wav file!");
		}
		
		
	}
	public static void sql1() {//상품고유번호를 랜덤으로 출력하기 위한 함수
		DB_Connector db = new DB_Connector();
		String query = "select 상품고유번호 from 상품";
		ArrayList<String> list = new ArrayList<>();
		
		String tf1="";	//상품고유번호
		String tf2="";	//상품명
		String tf3="";	//카테고리
		String tf4="";	//주문개수
		String tf5=""; //주문날짜
		
		try {
			db.DB_Connect();
			java.sql.Statement stmt = db.con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				System.out.println(rs.getString("상품고유번호"));
				list.add(rs.getString("상품고유번호"));
			}
		}
		catch(SQLException e) {e.printStackTrace();}
		
		Random random =new Random();
		int randomIndex = random.nextInt(list.size());
		String text=list.get(randomIndex);
		System.out.print(text);
		String query2 = "select 상품고유번호,상품명,카테고리 from 상품 where 상품고유번호="+"'"+text+"'";
		
		try {
			db.DB_Connect();
			java.sql.Statement stmt = db.con.createStatement();
			ResultSet rs = stmt.executeQuery(query2);
			
			while(rs.next()) {
				tf1= rs.getString("상품고유번호"); // TF1
				tf2= rs.getString("상품명");//TF2
				tf3= rs.getString("카테고리");//TF3
			}
			
		}
		catch(SQLException e) {e.printStackTrace();}
		int cnt =  random.nextInt(1,4);
		tf4=  Integer.toString(cnt);
		LocalDate now = LocalDate.now(); //TF5
		tf5= now.toString();
		
		TF1.setText(tf1);
		TF2.setText(tf2);
		TF3.setText(tf3);
		TF4.setText(tf4);
		TF5.setText(tf5);
	}
	
	public static void sql2() {
		ArrayList<String> list1 = new ArrayList<>(Arrays.asList("김","인","호","준","영","민","대","진","현","현","흠","재","영","도","건","황","우","이","건","유","손","보","묵","진") ); //이름
		String list2 = "010"; //전화번호
		String list3 = "";
		String name = ""; // 집코드
		String ho = ""; //호수
		String lat = "";
		Random random =new Random();
		
		for (int i=0; i<3; i++) {//이름생성
			int randomIndex = random.nextInt(list1.size());
			String text=list1.get(randomIndex);
		
			name = name + text;
		}
		
		
		
		for (int i=0; i<8; i++) {
			int cnt =  random.nextInt(1,9);
			list2 = list2 + Integer.toString(cnt);
		}
		
		// 44567 302호
		for (int i=0; i<5; i++) {
			int cnt =  random.nextInt(4,9);
			list3 = list3 + Integer.toString(cnt);
		}
		
		
		for (int i=0; i<3; i++) {
			int cnt =  random.nextInt(1,5);
			ho = ho + Integer.toString(cnt);
		}
		lat = list3+" "+ ho+"호";
		
		System.out.println(name);
		System.out.println(list2);
		System.out.println(lat);
		
		TF6.setText(name);
		TF7.setText(list2);
		TF8.setText(lat);
		
		
		
	}
	
	
	
	private JFrame frame;
	public static JTextField TF1;
	public static JTextField TF4;
	public static JTextField TF8;
	public static JTextField TF5;
	public static JTextField TF6;
	public static JTextField TF7;
	public static JTextField TF2;
	public static JTextField TF3;
	
	
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
					create_customer window = new create_customer();
					window.frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public create_customer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 555, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("주문받기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNewButton) {
					System.out.print("주문생성");
					sounds();// 소리 출력
					sql1(); //상품관련
					sql2();//주문하는사람관련
				}
				
			}
		});
		
		btnNewButton.setBounds(12, 10, 84, 59);
		frame.getContentPane().add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setForeground(new Color(255, 255, 255));
		panel.setBounds(108, 10, 419, 466);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		TF1 = new JTextField();
		TF1.setBounds(101, 5, 196, 21);
		panel.add(TF1);
		TF1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("상품고유번호");
		lblNewLabel.setBounds(12, 8, 81, 15);
		panel.add(lblNewLabel);
		
		TF4 = new JTextField();
		TF4.setColumns(10);
		TF4.setBounds(101, 138, 196, 21);
		panel.add(TF4);
		
		TF8 = new JTextField();
		TF8.setColumns(10);
		TF8.setBounds(101, 318, 196, 21);
		panel.add(TF8);
		
		JLabel lblNewLabel_1 = new JLabel("배송지");
		lblNewLabel_1.setBounds(12, 321, 81, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("주문개수");
		lblNewLabel_1_1.setBounds(12, 141, 81, 15);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("주문날짜");
		lblNewLabel_1_1_1.setBounds(12, 203, 81, 15);
		panel.add(lblNewLabel_1_1_1);
		
		TF5 = new JTextField();
		TF5.setColumns(10);
		TF5.setBounds(101, 200, 196, 21);
		panel.add(TF5);
		
		JLabel lblNewLabel_1_2 = new JLabel("이름");
		lblNewLabel_1_2.setBounds(12, 251, 81, 15);
		panel.add(lblNewLabel_1_2);
		
		TF6 = new JTextField();
		TF6.setColumns(10);
		TF6.setBounds(101, 248, 196, 21);
		panel.add(TF6);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("전화번호");
		lblNewLabel_1_2_1.setBounds(12, 288, 81, 15);
		panel.add(lblNewLabel_1_2_1);
		
		TF7 = new JTextField();
		TF7.setColumns(10);
		TF7.setBounds(101, 285, 196, 21);
		panel.add(TF7);
		
		JLabel lblNewLabel_2 = new JLabel("상품명");
		lblNewLabel_2.setBounds(12, 43, 81, 15);
		panel.add(lblNewLabel_2);
		
		TF2 = new JTextField();
		TF2.setColumns(10);
		TF2.setBounds(101, 40, 196, 21);
		panel.add(TF2);
		
		JLabel lblNewLabel_2_1 = new JLabel("카테고리");
		lblNewLabel_2_1.setBounds(12, 79, 81, 15);
		panel.add(lblNewLabel_2_1);
		
		TF3 = new JTextField();
		TF3.setColumns(10);
		TF3.setBounds(101, 71, 196, 21);
		panel.add(TF3);
		
		JButton btn2 = new JButton("주문등록");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//주문넣기 
				//중복고객 확인부터하자.
				String text = TF7.getText();
				DB_Connector db = new DB_Connector();
				ArrayList<String> allName = new ArrayList<>();
				String name = "";
				
				
				try {
					String query = "select 전화번호 from 주문고객";
					db.DB_Connect();
					java.sql.Statement stmt = db.con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					while(rs.next()) {					
						allName.add(rs.getString("전화번호"));				
						System.out.println(rs.getString("전화번호"));
					}
				}
				catch (Exception d) {d.printStackTrace();
				System.out.print("1");
				}
				
				
				try {
					String query2 = "select 이름 from 주문고객 where 전화번호="+"'"+text+"'";
					db.DB_Connect();
					java.sql.Statement stmt = db.con.createStatement();
					ResultSet rs = stmt.executeQuery(query2);
					while(rs.next()) {					
						name=rs.getString("이름")	;
						System.out.println(rs.getString("이름"));
					}
				}
				catch (Exception d) {d.printStackTrace();
				System.out.print("2");
				}
				
				
				
				
				// 기존에 존재하는 고객님이다.
				//전화번호는 다른데 이름같은경우 = 존재함
				//전화번호같은데 다른이름 = 존재불가능
				//전화번호 다르고 이름도 다른경우 존재가능
				String get_tf1 ="";		//상품고유번호
				String get_tf2 ="";		//상품명
				String get_tf3 ="";		//꽃바구니
				
				String get_tf5 ="";		//주문날짜
				String get_tf6 ="";		//이름
				String get_tf7 ="";		//전화번호
				String get_tf8 ="";		//배송지
				
				
				
				//주문 파싱
				get_tf5 = TF5.getText();
				get_tf5 = get_tf5.replace("-", "");
				
				
				try {// insert into 주문 values(상품고유번호,상품명,카테고리,가격,재고,이미지);
					String query3 = "insert into 주문고객(전화번호,이름,고객등급) values (?,?,?)";
					db.DB_Connect();
					
					PreparedStatement ptmt = db.con.prepareStatement(query3);
					
					
					ptmt.setString	(1,       TF7.getText()                	);
					ptmt.setString	(2,       TF6.getText()                	);
					ptmt.setString		(3,     "노말"   				);
					int res = ptmt.executeUpdate();
					if(res>0) System.out.println("고객저장성공");
					else  System.out.println("고객저장실패");
				}
				catch (Exception d) {d.printStackTrace();
				System.out.print("4");
				}
				
				try {// insert into 주문 values(상품고유번호,상품명,카테고리,가격,재고,이미지);
					String query4 = "insert into 주문(주문번호,주소,주문개수,주문날짜,상품고유번호,전화번호) values (?,?,?,?,?,?)";
					db.DB_Connect();
					
					PreparedStatement ptmt = db.con.prepareStatement(query4);
					
					
					ptmt.setString	(1,       "00000000"                	);
					ptmt.setString	(2,       TF8.getText()                	);
					ptmt.setInt		(3,     		Integer.parseInt(TF4.getText())   				);
					ptmt.setInt	(4,       Integer.parseInt(get_tf5  )              		);
					ptmt.setString	(5,       TF1.getText()                	);
					ptmt.setString	(6,       TF7.getText()                	);
					
					int res = ptmt.executeUpdate();
					if(res>0) System.out.println("주문저장성공");
					else  System.out.println("주문저장실패");
					
				}
				catch (Exception d) {d.printStackTrace();
				System.out.print("3");
				}
			}

		
		});
		btn2.setBounds(12, 89, 84, 59);
		frame.getContentPane().add(btn2);
	}
}
