import javax.swing.*; //swing ������Ʈ Ŭ�������� ����� ���� ���
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.FileDialog;
import java.awt.Font;
import java.awt.event.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

class act_li_new implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		notepad note2 =  new notepad();
	}
	
}

class act_li_open implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		FileDialog dialog = new FileDialog(make_notepad.note, "�ҷ�����", FileDialog.LOAD);
		dialog.setVisible(true);
	
		String path = dialog.getDirectory() + dialog.getFile();
		
		String context = "";
		
		try {
			FileReader file_read = new FileReader(path);
			
			int i;
			
			while(true) {
				i = file_read.read();
				
				if(i == -1) {
					break;
				}
				context = context + (char)i;
			}
			
			make_notepad.note.textBox.setText(context);
			
			file_read.close();
			
		} catch (Exception e1) {
			System.out.println("�ҷ����⿡ �����߽��ϴ�." + e1);
		}
	}
	
}

class act_li_save implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		FileDialog dialog = new FileDialog(make_notepad.note, "����", FileDialog.SAVE);
		dialog.setVisible(true);
		
		String path = dialog.getDirectory() + dialog.getFile();
		
		try {
			FileWriter notepad_file = new FileWriter(path);
			notepad_file.write(make_notepad.note.textBox.getText());
			notepad_file.close();
		} catch (Exception e2) {
			System.out.println("���忡 �����߽��ϴ�." + e2);
		}
	}
	
}

class act_li_exit implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		make_notepad.note.dispatchEvent(new WindowEvent(make_notepad.note, WindowEvent.WINDOW_CLOSING));
	}
	
}

class act_li_font implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		fontChange fontWindow = new fontChange();
	}
	
}

class act_li_info implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		infoWindow info = new infoWindow();
	}
	
}

class fontChange extends JFrame{
	String[] data = {"�ü�ü", "����", "����"};
	JList fontList = new JList(data);
	
	public fontChange() {
		setTitle("�۲� ����");
		setSize(300, 300);
		
		
		this.add(fontList);
		
		fontList.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				Font selectedFont = new Font((String) fontList.getSelectedValue(), Font.PLAIN, 10);
				make_notepad.note.textBox.setFont(selectedFont);
			}
			
		});
		
		this.setVisible(true);
	}
}

class infoWindow extends JFrame{
	
	public infoWindow() {
		
		JLabel name = new JLabel("�������� ���� �޸���");
		
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setVerticalAlignment(SwingConstants.CENTER);
		
		setTitle("�޸��� ����");
		setSize(300, 300);
		
		this.add(name);
		this.setVisible(true);
	}
}


class notepad extends JFrame{
	public JMenuBar sideBar = new JMenuBar();
	JTextArea textBox = new JTextArea();
	
	public notepad(){//������
		setTitle("������ �޸���");
		setSize(700, 500);
		
		this.add(sideBar);
		this.add(textBox);
		

		
		this.setJMenuBar(sideBar);
		
		JMenu bar_file = new JMenu("����(F)");
		sideBar.add(bar_file);
		JMenu bar_font = new JMenu("����(O)");
		sideBar.add(bar_font);
		JMenu bar_help = new JMenu("����(H)");
		sideBar.add(bar_help);
		
		//����
		JMenuItem li_new = new JMenuItem("���� �����(N)");
		bar_file.add(li_new);
		JMenuItem li_open = new JMenuItem("����(O)...");
		bar_file.add(li_open);
		JMenuItem li_save = new JMenuItem("����(S)");
		bar_file.add(li_save);
		JMenuItem li_exit = new JMenuItem("������(X)");
		bar_file.add(li_exit);
		
		//����
		JMenuItem li_font = new JMenuItem("�۲�(F)...");
		bar_font.add(li_font);
		
		//����
		JMenuItem li_info = new JMenuItem("�޸��� ����(A)");
		bar_help.add(li_info);
		
		li_new.addActionListener(new act_li_new());
		li_open.addActionListener(new act_li_open());
		li_save.addActionListener(new act_li_save());
		li_exit.addActionListener(new act_li_exit());
		li_font.addActionListener(new act_li_font());
		li_info.addActionListener(new act_li_info());
		
		this.setVisible(true);
	}
}

public class make_notepad {
	static notepad note; // static�� ����ؼ� �ٸ� Ŭ�������� �ٷ� �ּ� �����ؼ� �����ϱ�.
	public static void main(String [] args) {
		note =  new notepad();
	}
}

