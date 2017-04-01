package mainpack;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class FirstForm extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	int max=0;
	String path,str;
	String data[][];
	String columnnames[];
	
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstForm frame = new FirstForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FirstForm() {		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 295);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
		JPanel panelWork = new JPanel();
		panelWork.setBounds(10, 11, 414, 239);
		contentPane.add(panelWork);
		panelWork.setLayout(null);
		panelWork.setVisible(false);
		
		JPanel panelDownload = new JPanel();
		panelDownload.setBounds(10, 11, 414, 239);
		contentPane.add(panelDownload);
		panelDownload.setLayout(null);
		
		JButton buttonClose = new JButton("\u0417\u0430\u043A\u0440\u044B\u0442\u044C");
		buttonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelWork.setVisible(false);
				panelDownload.setVisible(true);
				max = 0;
				str = ""; path = null;
			}
		});
		buttonClose.setBounds(10, 205, 89, 23);
		panelWork.add(buttonClose);
		
		table = new JTable();
		table.setBounds(10, 191, 394, -179);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 394, 183);
		panelWork.add(scrollPane);
		
		JButton buttonSave = new JButton("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i,j;
				str = "ФИО,Должность,Оклад,Всего рабочих дней,Отработано дней\r\n";
				for (i=0;i<max;i++) {
					for (j=0;j<5;j++) {
						if (j!=4) { str = str + String.valueOf(table.getValueAt(i, j)) + ","; }
						else { str = str + String.valueOf(table.getValueAt(i, j)) + "\r\n"; }
					}
				}
				FileWorker.write(path, str);
				JOptionPane.showMessageDialog(null, "Успешно сохранено", "Сохранение", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		buttonSave.setBounds(109, 205, 115, 23);
		panelWork.add(buttonSave);
		
		JButton buttonP2Exit = new JButton("\u0412\u044B\u0445\u043E\u0434");
		buttonP2Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonP2Exit.setBounds(315, 205, 89, 23);
		panelWork.add(buttonP2Exit);
		
		JButton buttonDownload = new JButton("\u0417\u0430\u0433\u0440\u0443\u0437\u0438\u0442\u044C \u0444\u0430\u0439\u043B");
		buttonDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileopen = new JFileChooser();				
                int ret = fileopen.showDialog(null, "Открыть файл");                
                if (ret == JFileChooser.APPROVE_OPTION) {
					File file = fileopen.getSelectedFile();
					path = file.getPath();
					panelDownload.setVisible(false);
					panelWork.setVisible(true);
					try {
						str = FileWorker.read(path);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					
					columnnames = new String[5];
					columnnames[0] = "";
					columnnames[1] = ""; 
					columnnames[2] = "Оклад"; 
					columnnames[3] = "Всего рабочих дней"; 
					columnnames[4] = "Отработано дней";
					int i=0,j=0,k=0;
					while (j<2)
					{
						columnnames[j] = columnnames[j] + str.charAt(i); 
						i++;
						if (str.charAt(i)=='\r') i++;
						if (str.charAt(i)==',' || str.charAt(i)=='\n') {i++; j++;}
					}
					for (k=0;k<str.length();k++) {if (str.charAt(k)=='\n') {max++;}}
					max--;
					data = new String[max][5];
					for (k=0;k<max;k++) {
						j=0;
						data[k][0]=""; data[k][1]=""; 
						while (j<2)
					    {
					    	data[k][j] = data[k][j] + str.charAt(i); 
					    	i++;
					    	if (str.charAt(i)=='\r') i++;
					    	if (str.charAt(i)==',' || str.charAt(i)=='\n' || str.charAt(i)==-1) {i++; j++;}
					    }
					}
					table.setModel(new DefaultTableModel(data,columnnames));
                }
			}
		});
		buttonDownload.setBounds(68, 103, 260, 23);
		panelDownload.add(buttonDownload);
		
		JButton buttonExit = new JButton("\u0412\u044B\u0445\u043E\u0434");
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		buttonExit.setBounds(315, 205, 89, 23);
		panelDownload.add(buttonExit);
			
	}
}
