package interpret.ui;

import interpret.data.ObjectPool;
import interpret.setting.Consts;
import interpret.util.LexicalAnalyzer;
import interpret.util.ReflectUtil;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO Packagenameの入力もほしい
 */
public class CreateInstanceUIFrame extends JFrame {
    private static final long serialVersionUID = -2817250238322914062L;
    private JTextField classNameInputFiled;
    private JTextField arraySizeInputFiled;
    private JList constractorList;
    private JButton showConstrutorButton;
    private JPanel dynamicParamPanel;
    private GridLayout layout;
    private final List<JTextField> inputParams = new ArrayList<>();
    private Constructor<?>[] constructors;
    private int selectedIndex;
    private List<String> paramList;
    private JList instanceList;

    private final DefaultListModel model = new DefaultListModel<String>();
    private final DefaultListModel instanceModel = new DefaultListModel<String>();


    public CreateInstanceUIFrame() {
        super(Consts.MAIN_FRAME_TITLE);
        setBounds(Consts.MAIN_FRAME_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout();
        addListeners();


    }

    private void setLayout() {
        getContentPane().setLayout(new GridLayout(2, 0, 0, 0));

        JPanel inputPanel = new JPanel();
        getContentPane().add(inputPanel);
        inputPanel.setLayout(new BorderLayout(0, 0));

        JPanel headerPanel = new JPanel();
        inputPanel.add(headerPanel, BorderLayout.NORTH);
        headerPanel.setLayout(new GridLayout(1, 0, 0, 0));

        classNameInputFiled = new JTextField();
        headerPanel.add(classNameInputFiled);
        classNameInputFiled.setColumns(10);

        showConstrutorButton = new JButton(Consts.SHOW_CONSTRUCTOR);
        headerPanel.add(showConstrutorButton);

        arraySizeInputFiled = new JTextField();
        headerPanel.add(arraySizeInputFiled);
        arraySizeInputFiled.setColumns(10);

        JButton decideArraySizeButton = new JButton(Consts.DECIDE_ARRAY_SIZE);
        headerPanel.add(decideArraySizeButton);

        JPanel panel = new JPanel();
        inputPanel.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));

        JPanel construtorsPanel = new JPanel();
        panel.add(construtorsPanel, BorderLayout.CENTER);
        construtorsPanel.setLayout(new GridLayout(1, 0, 0, 0));

        JScrollPane scrollPane = new JScrollPane();
        construtorsPanel.add(scrollPane);

        constractorList = new JList(model);
        scrollPane.setViewportView(constractorList);

        dynamicParamPanel = new JPanel();
        layout = new GridLayout();
        layout.setColumns(2);
        dynamicParamPanel.setLayout(layout);
        construtorsPanel.add(dynamicParamPanel);

        JPanel footerPanel = new JPanel();
        panel.add(footerPanel, BorderLayout.SOUTH);
        footerPanel.setLayout(new GridLayout(1, 0, 0, 0));

        JLabel lblInstanceObjs = new JLabel("instance object(s)");
        footerPanel.add(lblInstanceObjs);

        JButton btnCreateInstance = new JButton("create instance");
        btnCreateInstance.addActionListener(CREATE_INSTANCE);
        footerPanel.add(btnCreateInstance);

        JPanel instanceListPanel = new JPanel();
        getContentPane().add(instanceListPanel);
        instanceListPanel.setLayout(new BorderLayout(0, 0));

        instanceList = new JList(instanceModel);
        instanceListPanel.add(instanceList);

        JButton btnShowObject = new JButton("show object");
        btnShowObject.addActionListener(SHOW_OBJECT);
        instanceListPanel.add(btnShowObject, BorderLayout.SOUTH);

    }

    private void addListeners() {
        showConstrutorButton.addActionListener(SHOW_CONSTRACTOR);
        constractorList.addListSelectionListener(GET_CONSTRUCTOR_PARAM);
    }

    /**
     * 　コンストラクタのリストをパネルに表示する
     */
    private final ActionListener SHOW_CONSTRACTOR = (e) -> {
        try {
            constructors = Class.forName(classNameInputFiled.getText()).getConstructors();
            model.clear();
            for (int i = 0; i < constructors.length; i++) {
                model.addElement(constructors[i].toString());
            }

        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    };

    /**
     * コンストラクタのパラメタを取得
     */
    private final ListSelectionListener GET_CONSTRUCTOR_PARAM = e -> {
        if (e.getValueIsAdjusting()) {
            return;
        }
        selectedIndex = constractorList.getSelectedIndex();
        if(selectedIndex == -1){return;}
        Constructor<?> con = constructors[selectedIndex];
        //paramList = LexicalAnalyzer.findParams(con.toString());
        paramList = LexicalAnalyzer.findParams(con.toString());
        deleteComponentFromPanel(dynamicParamPanel);
        setCompToParamPanel(dynamicParamPanel, paramList);
    };

    /**
     * パラメータリストを消す.
     * すでにパネルに何もコンポーネントがない場合何も起こらない。
     *
     * @param jPanel
     */
    private void deleteComponentFromPanel(JPanel jPanel) {
        inputParams.clear();
        Component[] comps = jPanel.getComponents();
        for (Component comp : comps) {
            jPanel.remove(comp);
        }
        jPanel.revalidate();
        jPanel.repaint();
    }

    /**
     * paramパネルにコンポーネントを追加します。
     *
     * @param jPanel
     * @param classes
     * @return
     */
    private void setCompToParamPanel(JPanel jPanel, List<String> classes) {
        inputParams.clear();
        System.out.println(classes);
        if (classes == null) {
            return;
        }
        if (classes.isEmpty()) {
            return;
        }
        layout.setRows(classes.size());
        for (int i = 0; i < classes.size(); i++) {
            jPanel.add(new JLabel(classes.get(i)));
            inputParams.add(new JTextField());
            jPanel.add(inputParams.get(i));
        }

    }

    private final ActionListener CREATE_INSTANCE = e -> {
        Constructor<?> con = constructors[selectedIndex];
        try {
            //Object instance = ReflectUtil.construct(con, LexicalAnalyzer.parse(paramList, inputParams));
            Object instance = ReflectUtil.construct(con, LexicalAnalyzer.parse(paramList, inputParams));
            System.out.println("CREATE INSTANCE :: " + instance.getClass().getSimpleName() + "#" + instance.hashCode());
            ObjectPool.getInstance().add(instance);
            instanceModel.addElement(instance.getClass().getSimpleName() + "#" + instance.hashCode());

        } catch (NumberFormatException ignore) {
            JOptionPane.showMessageDialog(this, "Incorrect input");
        } catch (Throwable throwable) {
            JOptionPane.showMessageDialog(this, "Invalid");
        }

    };

    private final ActionListener SHOW_OBJECT = e -> {
        InvokeUIFrame invokeUIFrame = new InvokeUIFrame(instanceList.getSelectedIndex());
        invokeUIFrame.setVisible(true);

    };


}
