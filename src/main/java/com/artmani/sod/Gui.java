package com.artmani.sod;

import com.artmani.sod.items.Group;
import com.artmani.sod.items.Student;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame {
    private JPanel mainPanel;
    private JComboBox selectStudent;
    private JComboBox selectGroup;
    private JLabel groupNumberInfo;
    private JTextField groupNumberText;
    private JPanel groupInfoPanel;
    private JTextField groupStudentsCountText;
    private JLabel groupStudentsCount;
    private JComboBox groupSelectSubject;
    private JTextField groupAvrgMarkText;
    private JTextField groupAvrgMarksCountPerStudentText;
    private JTextField studentNameText;
    private JTextField studentLastNameText;
    private JTextField studentGroupIdText;
    private JTextField studentCouseText;
    private JTextArea studentSubjectMarks;
    private JComboBox studentSubjectSelect;
    private JTextField studentArvgMarkText;

    Gui() {
        super("Лучкив Артем | Статистическая обработка данных");

        $$$setupUI$$$();
        setVisible(true);
        setContentPane(mainPanel);

        importGroupsToGui();
        importStudentsToGui();

        registerEvents();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        setSize(720, 480);
        setResizable(false);

    }

    public void registerEvents() {

        /**
         * ActionListener для selectGroup
         **/
        selectGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int groupID = (int) selectGroup.getSelectedItem();
                Group group = Group.getGroupByID(groupID);

                DefaultComboBoxModel comboBox = new DefaultComboBoxModel();
                for (Student s : group.getStudents()) {
                    comboBox.addElement(s.getFullName());
                }
                selectStudent.setModel(comboBox);

                groupNumberText.setText("" + groupID);
                groupStudentsCountText.setText("" + group.getStudents().size());


                DefaultComboBoxModel comboBox1 = new DefaultComboBoxModel();
                for (String s : group.getSubjectList()) {
                    comboBox1.addElement(s);
                }
                groupSelectSubject.setModel(comboBox1);

                groupAvrgMarkText.setText("" + group.getAvrgMark((String) groupSelectSubject.getSelectedItem()));
                groupAvrgMarksCountPerStudentText.setText("" + group.getAvrgMarksCount((String) groupSelectSubject.getSelectedItem()));

                setDefaultStudentInfo();

            }
        });

        /**
         * ActionListener для selectStudent
         **/
        selectStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int groupID = (int) selectGroup.getSelectedItem();
                Group group = Group.getGroupByID(groupID);

                Student student = group.getStudentByName((String) selectStudent.getSelectedItem());

                studentNameText.setText(student.getFirstname());
                studentLastNameText.setText(student.getLastname());
                studentGroupIdText.setText(student.getGroupNumber() + "");
                studentCouseText.setText(student.getCourse() + "");

                DefaultComboBoxModel comboBox = new DefaultComboBoxModel();
                for (String subject : student.getSubjectList()) {
                    comboBox.addElement(subject);
                }
                studentSubjectSelect.setModel(comboBox);

                studentSubjectMarks.setText(student.getMarks().get((String) studentSubjectSelect.getSelectedItem()).toString());

            }
        });

        /**
         * ActionListener для groupSelectSubject
         */
        groupSelectSubject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int groupID = (int) selectGroup.getSelectedItem();
                Group group = Group.getGroupByID(groupID);

                System.out.println(group.getAvrgMark((String) groupSelectSubject.getSelectedItem()) + " " + group.getAvrgMarksCount((String) groupSelectSubject.getSelectedItem()));
                groupAvrgMarkText.setText("" + group.getAvrgMark((String) groupSelectSubject.getSelectedItem()));
                groupAvrgMarksCountPerStudentText.setText("" + group.getAvrgMarksCount((String) groupSelectSubject.getSelectedItem()));

            }
        });


        /**
         * ActionListener для studentSubjectSelect
         */
        studentSubjectSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int groupID = (int) selectGroup.getSelectedItem();
                Group group = Group.getGroupByID(groupID);

                Student student = group.getStudentByName((String) selectStudent.getSelectedItem());
                studentSubjectMarks.setText(student.getMarks().get((String) studentSubjectSelect.getSelectedItem()).toString());

                studentArvgMarkText.setText(student.getAvrgMark((String) studentSubjectSelect.getSelectedItem()) + "");

            }
        });
    }

    public void setDefaultStudentInfo() {
        studentCouseText.setText("Выбери студента");
        studentArvgMarkText.setText("Выбери студента");
        studentNameText.setText("Выбери студента");
        studentGroupIdText.setText("Выбери студента");
        studentLastNameText.setText("Выбери студента");
        studentSubjectMarks.setText("None");
        // todo set default fields
    }

    public void importGroupsToGui() {
        DefaultComboBoxModel comboBox = new DefaultComboBoxModel();
        System.out.println(Group.groups.toString());
        for (Group g : Group.groups) {
            comboBox.addElement(g.getId());
        }
        selectGroup.setModel(comboBox);
    }


    public void importStudentsToGui() {

        DefaultComboBoxModel comboBox = new DefaultComboBoxModel();

        for (Student s : Group.getGroupByID((int) selectGroup.getSelectedItem()).getStudents()) {
            comboBox.addElement(s.getFullName());
        }
        selectStudent.setModel(comboBox);
    }


    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        groupInfoPanel = new JPanel();
        groupInfoPanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(groupInfoPanel, new GridConstraints(1, 0, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        groupInfoPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, -1), null, 0, false));
        groupNumberInfo = new JLabel();
        groupNumberInfo.setHorizontalTextPosition(2);
        groupNumberInfo.setText("Номер группы:");
        panel1.add(groupNumberInfo, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        groupNumberText = new JTextField();
        groupNumberText.setEditable(false);
        groupNumberText.setText("Выбери группу");
        panel1.add(groupNumberText, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        groupStudentsCount = new JLabel();
        groupStudentsCount.setText("Кол-во студентов:");
        panel1.add(groupStudentsCount, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        groupStudentsCountText = new JTextField();
        groupStudentsCountText.setEditable(false);
        groupStudentsCountText.setHorizontalAlignment(10);
        groupStudentsCountText.setText("Выбери группу");
        panel1.add(groupStudentsCountText, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(7, 1, new Insets(0, 0, 0, 0), -1, -1));
        groupInfoPanel.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        groupSelectSubject = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Выбери группу ->");
        groupSelectSubject.setModel(defaultComboBoxModel1);
        panel2.add(groupSelectSubject, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel2.add(spacer2, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Предмет");
        panel2.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Средняя оценка по группе:");
        panel2.add(label2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        groupAvrgMarkText = new JTextField();
        groupAvrgMarkText.setEditable(false);
        groupAvrgMarkText.setText("None");
        panel2.add(groupAvrgMarkText, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Среднее кол-во оценок на человека:");
        panel2.add(label3, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        groupAvrgMarksCountPerStudentText = new JTextField();
        groupAvrgMarksCountPerStudentText.setEditable(false);
        groupAvrgMarksCountPerStudentText.setText("None");
        panel2.add(groupAvrgMarksCountPerStudentText, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel3, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(-1, 100), 0, false));
        final Spacer spacer3 = new Spacer();
        panel3.add(spacer3, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        selectStudent = new JComboBox();
        selectStudent.setEditable(false);
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        selectStudent.setModel(defaultComboBoxModel2);
        panel3.add(selectStudent, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(120, -1), null, 0, false));
        selectGroup = new JComboBox();
        selectGroup.setEditable(false);
        final DefaultComboBoxModel defaultComboBoxModel3 = new DefaultComboBoxModel();
        selectGroup.setModel(defaultComboBoxModel3);
        panel3.add(selectGroup, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(14, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel4, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Имя:");
        panel4.add(label4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel4.add(spacer4, new GridConstraints(13, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        studentNameText = new JTextField();
        studentNameText.setEditable(false);
        studentNameText.setText("Выбери студента");
        panel4.add(studentNameText, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Фамилия:");
        panel4.add(label5, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        studentLastNameText = new JTextField();
        studentLastNameText.setEditable(false);
        studentLastNameText.setText("Выбери студента");
        panel4.add(studentLastNameText, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Группа:");
        panel4.add(label6, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Курс:");
        panel4.add(label7, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        studentGroupIdText = new JTextField();
        studentGroupIdText.setEditable(false);
        studentGroupIdText.setText("Выбери студента");
        panel4.add(studentGroupIdText, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        studentCouseText = new JTextField();
        studentCouseText.setEditable(false);
        studentCouseText.setText("Выбери студента");
        panel4.add(studentCouseText, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Оценки по предмету:");
        panel4.add(label8, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        studentSubjectMarks = new JTextArea();
        studentSubjectMarks.setEditable(false);
        studentSubjectMarks.setText("None");
        panel4.add(studentSubjectMarks, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(-1, 50), new Dimension(160, 50), 0, false));
        studentSubjectSelect = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel4 = new DefaultComboBoxModel();
        defaultComboBoxModel4.addElement("Выбери студента");
        studentSubjectSelect.setModel(defaultComboBoxModel4);
        panel4.add(studentSubjectSelect, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("Средняя оценка по предмету:");
        panel4.add(label9, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        studentArvgMarkText = new JTextField();
        studentArvgMarkText.setEditable(false);
        studentArvgMarkText.setText("Выбери студента");
        panel4.add(studentArvgMarkText, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer5 = new Spacer();
        mainPanel.add(spacer5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 5), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
