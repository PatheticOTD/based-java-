import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class dz2 {
    static ArrayList <Employee> slave_list = new ArrayList<Employee>();
    static ArrayList <Organization> orgs_list = new ArrayList<Organization>();
    static ArrayList<Boss> boss_list = new ArrayList<Boss>();
    public static void addslave(String name, int age, int exp, int dsal, String dj){
        Employee emp = new Employee(name, age, exp, dsal, dj);
        slave_list.add(emp);
    }
    public static void addboss(String _name, int _age, ArrayList<Organization> _orgs){
        Boss b = new Boss(_name, _age);
        for( int i = 0; i< _orgs.size(); i++){
            b.addComp(_orgs.get(i));
        }
        boss_list.add(b);cd

    }
    public static void addOrg(String name){}

    public static void main(String[] args) {

            // Создаем главное окно
        JFrame mainFrame = new JFrame("Информация о персонале");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 200);
        // Создаем панель для размещения кнопок
        JPanel buttonPanel = new JPanel();
        // Создаем кнопки
        JButton employeesButton = new JButton("Информация о работниках");
        JButton organizationsButton = new JButton("Информация об организациях");
        JButton bossesButton = new JButton("Информация о боссах");
        JButton addEmp = new JButton("Добавить ра... работника");
        JButton addOrg = new JButton("Добавить организацию");
        JButton addBoss = new JButton("Добавить босса");
        // Добавляем кнопки на панель
        buttonPanel.add(employeesButton);
        buttonPanel.add(organizationsButton);
        buttonPanel.add(bossesButton);
        buttonPanel.add(addEmp);
        buttonPanel.add(addOrg);
        buttonPanel.add(addBoss);
        // Добавляем панель на главное окно
        mainFrame.add(buttonPanel, BorderLayout.CENTER);
        ActionListener buttonActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Создаем новое окно
                JFrame infoFrame = new JFrame("Информация");
                infoFrame.setSize(400, 200);
                // Создаем текстовую метку для отображения текста
                JTextArea infoLabel = new JTextArea("");
                // Создаем кнопку для возврата к главному окну
                JButton backButton = new JButton("Назад");
                // Обработчик события для кнопки "Назад"
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        infoFrame.dispose(); // Закрываем текущее окно
                        mainFrame.setVisible(true);
                    }
                });
                // Устанавливаем текст для метки в зависимости от нажатой кнопки
                if (e.getSource() == employeesButton) {
                    //String ans = new String();
                    for(int i =0; i< slave_list.size(); i++){
                        infoLabel.append("--------------\n");
                        infoLabel.append("Name- "+slave_list.get(i).getName()+"\n");
                        infoLabel.append("experience - "+slave_list.get(i).getExp()+"\n");
                        infoLabel.append("--------------\n");
                    }
                    //System.out.println(ans);
                    infoLabel.setEditable(false);
                }
                if (e.getSource() == bossesButton) {
                    //String ans = new String();
                    for(int i =0; i< boss_list.size(); i++){
                        infoLabel.append("--------------\n");
                        infoLabel.append("Name - "+boss_list.get(i).getName()+"\n");
                        infoLabel.append("experience - "+boss_list.get(i).getAge()+"\n");
                        for(int j = 0; j< boss_list.get(i).getComps().size();j++){
                            infoLabel.append(boss_list.get(i).getComps().get(j)+"\n");
                        }
                        infoLabel.append("--------------\n");
                    }
                    //System.out.println(ans);
                    infoLabel.setEditable(false);
                }
                if (e.getSource() == organizationsButton) {
                    infoLabel.append("босс");
                    infoLabel.setEditable(false);
                }
                // Добавляем метку и кнопку на окно
                infoFrame.add(infoLabel, BorderLayout.CENTER);
                infoFrame.add(backButton, BorderLayout.SOUTH);
                // Закрываем главное окно
                mainFrame.setVisible(false);
                // Отображаем новое окно
                infoFrame.setVisible(true);
            }
        };
        employeesButton.addActionListener(buttonActionListener);
        organizationsButton.addActionListener(buttonActionListener);
        bossesButton.addActionListener(buttonActionListener);

        ActionListener newEmp = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addEmployeeFrame = new JFrame("Добавить работника");
                addEmployeeFrame.setSize(300, 200);

                // Создаем текстовые поля для ввода информации
                JTextField nameField = new JTextField(20);
                JTextField ageField = new JTextField(20);
                JTextField experienceField = new JTextField(20);
                JTextField salaryField = new JTextField(20);
                JTextField dreamJobField = new JTextField(20);

                // Создаем кнопку "Добавить"
                JButton addButton = new JButton("Добавить");

                // Создаем кнопку для возврата к главному окну
                JButton backButton = new JButton("Назад");

                // Обработчик события для кнопки "Назад"
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addEmployeeFrame.dispose(); // Закрываем текущее окно
                        mainFrame.setVisible(true);
                    }
                });

                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addslave(nameField.getText(), Integer.parseInt(ageField.getText()), Integer.parseInt(experienceField.getText()),  Integer.parseInt(salaryField.getText()), dreamJobField.getText());
                        addEmployeeFrame.dispose();
                        mainFrame.setVisible(true);
                    }
                });
                JPanel addEmployeePanel = new JPanel();
                addEmployeePanel.setLayout(new GridLayout(6, 2));
                addEmployeePanel.add(new JLabel("Имя:"));
                addEmployeePanel.add(nameField);
                addEmployeePanel.add(new JLabel("Возраст:"));
                addEmployeePanel.add(ageField);
                addEmployeePanel.add(new JLabel("Опыт работы:"));
                addEmployeePanel.add(experienceField);
                addEmployeePanel.add(new JLabel("Желаемая зарплата:"));
                addEmployeePanel.add(salaryField);
                addEmployeePanel.add(new JLabel("Работа мечты:"));
                addEmployeePanel.add(dreamJobField);
                addEmployeePanel.add(addButton);

                addEmployeeFrame.add(addEmployeePanel);

                addEmployeeFrame.setVisible(true);
                addEmployeeFrame.add(backButton, BorderLayout.SOUTH);
                // Закрываем главное окно
                mainFrame.setVisible(false);

                // Отображаем новое окно
                addEmployeeFrame.setVisible(true);
            }
        };
        addEmp.addActionListener(newEmp);

        ActionListener newBoss = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addbossframe = new JFrame("создаем новых инфоцыган");
                addbossframe.setSize(500, 500);

                JTextField nameField = new JTextField(20);
                JTextField ageField = new JTextField(20);
                JButton backButton = new JButton("Назад");
                JButton enter = new JButton("Сохранить");

                // Обработчик события для кнопки "Назад"
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addbossframe.dispose(); // Закрываем текущее окно
                        mainFrame.setVisible(true);
                    }
                });
                //панель
                JPanel bosspanel = new JPanel();
                bosspanel.setLayout(new GridLayout(orgs_list.size()+4, 2));
                bosspanel.add(new JLabel("Имя:"));
                bosspanel.add(nameField);
                bosspanel.add(new JLabel("Возраст:"));
                bosspanel.add(ageField);
                bosspanel.add(new JLabel("доступные компании:"));
                ArrayList <JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
                ArrayList <Organization> selected = new ArrayList<Organization>();
                // чекбоксы
                for (int i=0; i < orgs_list.size(); i++) {
                    JCheckBox c = new JCheckBox(orgs_list.get(i).getName());
                    c.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JCheckBox cb = (JCheckBox) e.getSource();
                            if (cb.isSelected()){
                                for(int i = 0; i< orgs_list.size();i++){
                                    if (orgs_list.get(i).getName() == cb.getText()){
                                        selected.add(orgs_list.get(i));
                                    }
                                }

                            }
                            else{
                                //for(int i = 0; i< orgs_list.size();i++){
                                    //if (orgs_list.get(i).getName() == cb.getText()){
                                        //selected.remove();
                                    //}
                                //}
                            }
                        }
                    });
                    checkBoxes.add(c);
                    bosspanel.add(checkBoxes.get(i));
                }
                JCheckBox noOrgs = new JCheckBox("Без компании");
                bosspanel.add(noOrgs);


                enter.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addboss(nameField.getText(), Integer.parseInt(ageField.getText()), selected);
                        addbossframe.dispose(); // Закрываем текущее окно
                        mainFrame.setVisible(true);
                    }
                });

                bosspanel.add(backButton, BorderLayout.SOUTH);
                bosspanel.add(enter, BorderLayout.EAST);
                addbossframe.add(bosspanel);

                mainFrame.setVisible(false);
                addbossframe.setVisible(true);


            }
        };
        addBoss.addActionListener(newBoss);

        ActionListener newOrg = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addOrgFrame = new JFrame("Организационная мастерская");
                addOrgFrame.setSize(400, 200);

                JTextField nameField = new JTextField(10);

                JPanel addOrgPanel = new JPanel();
                addOrgPanel.setLayout(new GridLayout(slave_list.size()+1, 1));

                addOrgPanel.add(new JLabel("Имя:"));
                addOrgPanel.add(nameField);
                addOrgPanel.add(new JLabel("боссы:"));


                addOrgFrame.add(addOrgPanel);

                ArrayList <JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
                for (int i=0; i < slave_list.size(); i++){
                    checkBoxes.add(new JCheckBox(slave_list.get(i).getName()));
                    addOrgPanel.add(checkBoxes.get(i));
                }



                JButton backButton = new JButton("Назад");

                // Обработчик события для кнопки "Назад"
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addOrgFrame.dispose(); // Закрываем текущее окно
                        mainFrame.setVisible(true);
                    }
                });

                addOrgFrame.add(backButton, BorderLayout.SOUTH);
                mainFrame.setVisible(false);
                addOrgFrame.setVisible(true);
            }
        };
        addOrg.addActionListener(newOrg);

            // Отображаем главное окно
        mainFrame.setVisible(true);


    }

}
