package interpret.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

class InvokeUIFrame extends JFrame {

	private static final long serialVersionUID = -5188095995143316568L;
	private final JPanel contentPane;

	private int objectPoolIndex;//オブジェクトが指し示す

	/**
	 * Create the frame.
	 */
	InvokeUIFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 0, 0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane methodScrollPane = new JScrollPane();
		panel.add(methodScrollPane);

		JList methodJList = new JList();
		methodScrollPane.setViewportView(methodJList);

		JPanel methodView = new JPanel();
		panel.add(methodView);
		methodView.setLayout(new BorderLayout(0, 0));

		JButton btnInvoke = new JButton("invoke");
		methodView.add(btnInvoke, BorderLayout.SOUTH);

		JPanel filedPanel = new JPanel();
		contentPane.add(filedPanel);
		filedPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblFields = new JLabel("Fields");
		filedPanel.add(lblFields, BorderLayout.NORTH);

		JPanel filedComp = new JPanel();
		filedPanel.add(filedComp, BorderLayout.CENTER);
	}

	/**
	 * この画面に来るときは必ずこのメソッドを呼ばないといけない。。
	 *
	 * @param index
	 */
	public void setObjectPoolIndex(int index) {
		objectPoolIndex = index;
	}

}
