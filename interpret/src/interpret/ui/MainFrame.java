package interpret.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import interpret.setting.Consts;

/**
 * ・classの入力
 * @author Sato Kodai
 *
 */
public class MainFrame extends JFrame {
	JLabel label;
	JTextField text;
	protected JList list;
	protected DefaultListModel model = new DefaultListModel<String>();

	/**
	 *
	 */
	public MainFrame() {
		super(Consts.MAIN_FRAME_TITLE);
		setBounds(Consts.MAIN_FRAME_SIZE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		text = new JTextField(Consts.INPUT_CLASS_NAME_SIZE);
		JButton button = new JButton("show constractor");
		button.addActionListener(SHOW_CONSTRACTOR);

		JPanel p = new JPanel();
		p.add(text);
		p.add(button);

		Container contentPane = getContentPane();
		contentPane.add(p, BorderLayout.NORTH);

		list = new JList(model);
		JScrollPane sp = new JScrollPane();
		sp.getViewport().setView(list);
		sp.setPreferredSize(new Dimension(200, 100));

		JPanel p1 = new JPanel();
		p1.add(sp);

		getContentPane().add(p1, BorderLayout.CENTER);

	}

	public final ActionListener SHOW_CONSTRACTOR = (e) -> {
		try {
			System.out.println("class name ** " + text.getText());
			Constructor<?>[] constructors = Class.forName("java.lang." + text.getText()).getConstructors();
			for (int i = 0; i < constructors.length - 1; i++) {
				model.addElement(constructors[i].toGenericString());
			}

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	};

}
