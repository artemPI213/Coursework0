package mainpack;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;

public class SecondForm extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTable table;
	
	int max=0;
	String path,str;
	String data[][];
	String columnnames[];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SecondForm frame = new SecondForm();
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
	public SecondForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
		JPanel panelDownload = new JPanel();
		panelDownload.setLayout(null);
		panelDownload.setBounds(10, 11, 414, 239);
		contentPane.add(panelDownload);
		
		JPanel panelWork = new JPanel();
		panelWork.setBounds(10, 11, 414, 239);
		contentPane.add(panelWork);
		panelWork.setLayout(null);
		panelWork.setVisible(false);
		
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
					
					columnnames = new String[12];
					columnnames[0] = "";
					columnnames[1] = ""; 
					columnnames[2] = ""; 
					columnnames[3] = ""; 
					columnnames[4] = "";
					columnnames[5] = "Зарплата";
					columnnames[6] = "НДФЛ";
					columnnames[7] = "ПФР";
					columnnames[8] = "ФФОМС";
					columnnames[9] = "ФСС";
					columnnames[10] = "ФСС (Несчастные случаи)";
					columnnames[11] = "К выплате";
					int i=0,j=0,k=0;
					while (j<5)
					{
						columnnames[j] = columnnames[j] + str.charAt(i); 
						i++;
						if (str.charAt(i)=='\r') i++;
						if (str.charAt(i)==',' || str.charAt(i)=='\n') {i++; j++;}
					}
					for (k=0;k<str.length();k++) {if (str.charAt(k)=='\n') {max++;}}				
					max--;
					data = new String[max][12];
					for (k=0;k<max;k++) {
						j=0;
						data[k][0]=""; data[k][1]=""; data[k][2]=""; data[k][3]=""; data[k][4]="";
						while (j<5)
					    {
					    	data[k][j] = data[k][j] + str.charAt(i); 
					    	i++;
					    	if (str.charAt(i)=='\r') i++;
					    	if (str.charAt(i)==',' || str.charAt(i)=='\n' || str.charAt(i)==-1) {i++; j++;}
					    }
					}
					for (i=0;i<max;i++) { 
						data[i][5] = String.valueOf((int)(Float.valueOf(data[i][2])*(Float.valueOf(data[i][4])/Float.valueOf(data[i][3]))));
						data[i][6] = String.valueOf((int)(Float.valueOf(data[i][5])*0.13));
						data[i][7] = String.valueOf((int)(Float.valueOf(data[i][5])*0.22));
						data[i][8] = String.valueOf((int)(Float.valueOf(data[i][5])*0.051));
						data[i][9] = String.valueOf((int)(Float.valueOf(data[i][5])*0.029));
						data[i][10] = String.valueOf((int)(Float.valueOf(data[i][5])*0.002));
						data[i][11] = String.valueOf((int)(Float.valueOf(data[i][5])-Float.valueOf(data[i][6])));
					}
					table.setModel(new DefaultTableModel(data,columnnames));}
			}
		});
		buttonDownload.setBounds(68, 103, 260, 23);
		panelDownload.add(buttonDownload);
		
		JButton buttonExit = new JButton("\u0412\u044B\u0445\u043E\u0434");
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonExit.setBounds(315, 205, 89, 23);
		panelDownload.add(buttonExit);
		
		JButton buttonClose = new JButton("\u0417\u0430\u043A\u0440\u044B\u0442\u044C");
		buttonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelWork.setVisible(false);
				panelDownload.setVisible(true);
				max = 0;
				str = ""; path = null;
			}
		});
		buttonClose.setBounds(10, 205, 89, 23);
		panelWork.add(buttonClose);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(10, 11, 394, 183);
		panelWork.add(scrollPane);
		
		JButton buttonSave = new JButton("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i,j;
				str = "ФИО,Должность,Оклад,Всего рабочих дней,Отработано дней,Зарплата,НДФЛ,ПФР,ФФОМС,ФСС,ФСС (Несчастные случаи),К выплате\r\n";
				for (i=0;i<max;i++) {
					for (j=0;j<12;j++) {
						if (j!=11) { str = str + String.valueOf(table.getValueAt(i, j)) + ","; }
						else { str = str + String.valueOf(table.getValueAt(i, j)) + "\r\n"; }
					}
				}
				FileWorker.write(path, str);
				JOptionPane.showMessageDialog(null, "Успешно сохранено", "Сохранение", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
	
		table = new JTable(){
			private static final long serialVersionUID = 1L;

			@Override
		       public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
	           Component component = super.prepareRenderer(renderer, row, column);
	           int rendererWidth = component.getPreferredSize().width;
	           TableColumn tableColumn = getColumnModel().getColumn(column);
	           tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
	           return component;
	        }
	    };
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		
		buttonSave.setBounds(109, 205, 115, 23);
		panelWork.add(buttonSave);
		
		JButton buttonExit_1 = new JButton("\u0412\u044B\u0445\u043E\u0434");
		buttonExit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonExit_1.setBounds(315, 205, 89, 23);
		panelWork.add(buttonExit_1);
	}
}