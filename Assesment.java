package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

public class Assesment {

	private JFrame frame;
	private JTextField ID;
	private JTextField FIRSTNAME;
	private JTextField LASTNAME;
	private JTextField EMAIL;
	private JTextField MOBILE;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Assesment window = new Assesment();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection cn=null;

	
	public Assesment() {
		initialize();
		connect();
	}
	
	public void connect()
	{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		
			cn=DriverManager.getConnection("jdbc:mysql://localhost:3307/assessment","root","");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void loddata()
	{
		
		try {
			PreparedStatement ps=cn.prepareStatement("select * from crud");
			
			ResultSet rs=ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 472, 567);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registration Form");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(125, 23, 209, 32);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel i1 = new JLabel("ID");
		i1.setFont(new Font("Tahoma", Font.BOLD, 14));
		i1.setBounds(113, 125, 90, 26);
		frame.getContentPane().add(i1);
		
		JLabel fn1 = new JLabel("FIRST NAME");
		fn1.setFont(new Font("Tahoma", Font.BOLD, 14));
		fn1.setBounds(113, 169, 90, 26);
		frame.getContentPane().add(fn1);
		
		JLabel ln1 = new JLabel("LAST NAME");
		ln1.setFont(new Font("Tahoma", Font.BOLD, 14));
		ln1.setBounds(113, 222, 90, 26);
		frame.getContentPane().add(ln1);
		
		JLabel e1 = new JLabel("EMAIL");
		e1.setFont(new Font("Tahoma", Font.BOLD, 14));
		e1.setBounds(113, 269, 90, 26);
		frame.getContentPane().add(e1);
		
		JLabel m1 = new JLabel("MOBILE");
		m1.setFont(new Font("Tahoma", Font.BOLD, 14));
		m1.setBounds(113, 316, 90, 26);
		frame.getContentPane().add(m1);
		
		ID = new JTextField();
		ID.setBounds(231, 131, 151, 19);
		frame.getContentPane().add(ID);
		ID.setColumns(10);
		
		FIRSTNAME = new JTextField();
		FIRSTNAME.setBounds(231, 175, 151, 19);
		frame.getContentPane().add(FIRSTNAME);
		FIRSTNAME.setColumns(10);
		
		LASTNAME = new JTextField();
		LASTNAME.setBounds(231, 228, 151, 19);
		frame.getContentPane().add(LASTNAME);
		LASTNAME.setColumns(10);
		
		EMAIL = new JTextField();
		EMAIL.setBounds(231, 276, 151, 19);
		frame.getContentPane().add(EMAIL);
		EMAIL.setColumns(10);
		
		MOBILE = new JTextField();
		MOBILE.setBounds(231, 322, 151, 19);
		frame.getContentPane().add(MOBILE);
		MOBILE.setColumns(10);
		
		JButton btnNewButton = new JButton("INSERT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String i1=ID.getText();
				
				String fn1=FIRSTNAME.getText();
				String ln1=LASTNAME.getText();
				String e1=EMAIL.getText();
				String m1=MOBILE.getText();
				
				try {
					if(i1.equals(null)|| i1.equals(""))
					{
					PreparedStatement ps=cn.prepareStatement("insert into crud values(?,?,?,?,?)");
					ps.setInt(1, 0);
					ps.setString(2, fn1);
					ps.setString(3, ln1);
					ps.setString(4, e1);
					ps.setString(5, m1);
					
					int i=ps.executeUpdate();
					if(i>0)
					{
						JOptionPane.showMessageDialog(frame,"Data Inserted...");
						FIRSTNAME.setText("");
						LASTNAME.setText("");
						EMAIL.setText("");
						MOBILE.setText("");
					}
					}
					else
					{
						
						int ID=Integer.parseInt(i1);
						PreparedStatement ps=cn.prepareStatement("update crud set FIRSTNAME=?,LASTNAME=?,EMAIL=?,MOBILE=? where ID=?");
						ps.setInt(5, ID);
						ps.setString(1,fn1);
						ps.setString(2, ln1);
						ps.setString(3, e1);
						ps.setString(4, m1);
						
						int i =ps.executeUpdate();
						if(i>0)
						{
							JOptionPane.showMessageDialog(frame, "DATA UPDATED....");
							FIRSTNAME.setText("");
							LASTNAME.setText("");
							EMAIL.setText("");
							MOBILE.setText("");
							Assesment.this.ID.setText("");
							
						}
						
						
					}
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
					
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(98, 390, 105, 32);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("SEARCH");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
				String i1=ID.getText();
				
				int ID=Integer.parseInt(i1);
				
				try {
					PreparedStatement ps=cn.prepareStatement("select * from crud where ID=?");
					ps.setInt(1, ID);
					
					ResultSet rs=ps.executeQuery();
					
					if(rs.next())
					{
						
						FIRSTNAME.setText(rs.getString(2));
						LASTNAME.setText(rs.getString(3));
						EMAIL.setText(rs.getString(4));
						MOBILE.setText(rs.getString(5));
						
					}
					
					else
					{
						
						JOptionPane.showMessageDialog(frame, "ID NOT FOUND!!!");
						
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(279, 390, 103, 32);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("UPDATE");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String i1=ID.getText();
				
				int ID=Integer.parseInt(i1);
				
				try {
					PreparedStatement ps=cn.prepareStatement("select * from crud where ID=?");
					ps.setInt(1, ID);
					
					ResultSet rs=ps.executeQuery();
					
					if(rs.next())
					{
						
						FIRSTNAME.setText(rs.getString(2));
						LASTNAME.setText(rs.getString(3));
						EMAIL.setText(rs.getString(4));
						MOBILE.setText(rs.getString(5));
						
					}
					else
					{

						JOptionPane.showMessageDialog(frame, "ID NOT FOUND!!!");
						
					}	
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				
				
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBounds(98, 449, 105, 32);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("DELETE");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				int i1=Integer.parseInt(ID.getText());
				
				try {
					PreparedStatement ps=cn.prepareStatement("delete from crud where ID=?");
					ps.setInt(1, i1);
					
					int i=ps.executeUpdate();
					if(i>0)
					{
						JOptionPane.showMessageDialog(frame, "DATA DELETED....");
						ID.setText("");
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "ID NOT FOUND!!!!");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_3.setBounds(279, 449, 103, 32);
		frame.getContentPane().add(btnNewButton_3);
	}
}
