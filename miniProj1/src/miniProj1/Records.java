package miniProj1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Records extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table_rec;

	static Records dialog;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
		    dialog = new Records();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Records() {
		setTitle("Records");
		setBounds(100, 100, 794, 381);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 118, 749, 203);
		contentPanel.add(scrollPane);
		
		table_rec = new JTable();
		table_rec.setFont(new Font("Tahoma", Font.BOLD, 13));
		table_rec.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Address", "Items", "Quantity", "Sub Total", "VAT", "Total"
			}
		));
		scrollPane.setViewportView(table_rec);
		
		JLabel lblStored = new JLabel("Stored Records In Database");
		lblStored.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStored.setHorizontalAlignment(SwingConstants.CENTER);
		lblStored.setBounds(12, 30, 749, 35);
		contentPanel.add(lblStored);
		loadRec();
		
	}

	public void loadRec() {
		
		
		try {
			
			DefaultTableModel model = (DefaultTableModel) table_rec.getModel();
			Connection con = null;
			Statement stmt;
			
			
			Class.forName("com.mysql.jdbc.Driver");  
			 con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/imran","root","root123");
			 stmt=con.createStatement();
			 ResultSet rs=stmt.executeQuery("select * from bill"); 
				while(rs.next())  {
					
					String name=rs.getString(1);
					String address=rs.getString(2);
					String items=rs.getString(3);
					String quantity=rs.getString(4);
					String subtotal=rs.getString(5);
					String vat=rs.getString(6);
					String total=rs.getString(7);
					model.addRow(new Object[]{name,address,items,quantity,subtotal,vat,total});
					
				}	
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
