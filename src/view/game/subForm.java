package game;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class subForm extends JFrame implements ActionListener{

	private JButton close;
	public subForm(){
		super("Sub Form");
		//tạo button
		close = new JButton("Close");
		// add sự kiện vào
		close.addActionListener(this);
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		add(new JLabel("Đây là Form phụ của bạn."));
		add(close);
		
		setSize(400, 400);
	}
	// sự kiện cho nha
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==close){
			this.setVisible(false);
		}
	}

}
