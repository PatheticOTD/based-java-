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
            _orgs.get(i).addBoss(b);
        }
        boss_list.add(b);
    }
    public static void addorg(String _name, ArrayList<Boss> _bosses){
        Organization o = new Organization();
        o.setName(_name);
        for(int i = 0; i< _bosses.size(); i++){
            o.addBoss(_bosses.get(i));
            _bosses.get(i).addComp(o);
        }
        orgs_list.add(o);

    }

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
        JButton bosses = new JButton("Боссы");
        JButton slaves = new JButton("Работники");

        // Добавляем кнопки на панель
        buttonPanel.add(employeesButton);
        buttonPanel.add(organizationsButton);
        buttonPanel.add(bossesButton);
        buttonPanel.add(addEmp);
        buttonPanel.add(addOrg);
        buttonPanel.add(addBoss);
        buttonPanel.add(bosses);
        buttonPanel.add(slaves);

        // Добавляем панель на главное окно
        mainFrame.add(buttonPanel, BorderLayout.CENTER);

//--------------------------------------------------------------------------------
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
                        infoLabel.append("dream job - "+slave_list.get(i).getDream()+"\n");
                        infoLabel.append("desired salary - "+slave_list.get(i).GetDesiredSalary()+"\n");
                        infoLabel.append("hierd - "+ slave_list.get(i).getstatus()+"\n");
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
                        infoLabel.append("age - "+boss_list.get(i).getAge()+"\n");
                        for(int j = 0; j< boss_list.get(i).getComps().size();j++){
                            infoLabel.append(boss_list.get(i).getComps().get(j).getName()+"\n");
                        }
                        infoLabel.append("--------------\n");
                    }
                    //System.out.println(ans);
                    infoLabel.setEditable(false);
                }
                if (e.getSource() == organizationsButton) {
                    for(int i =0; i< orgs_list.size(); i++){
                        infoLabel.append("--------------\n");
                        infoLabel.append("Name - "+orgs_list.get(i).getName()+"\n");
                        for(int j = 0; j< orgs_list.get(i).getBosses().size();j++){
                            infoLabel.append(orgs_list.get(i).getBosses().get(j).getName()+"\n");
                        }
                        infoLabel.append("вакансии:\n");
                        for(int j = 0; j<orgs_list.get(i).getVacancies().size(); j++){
                            infoLabel.append(orgs_list.get(i).getVacancies().get(j)+"\n");
                        }
                        infoLabel.append("Работники: \n");
                        for(int j = 0; j<orgs_list.get(i).getSlaves().size(); j++){
                            infoLabel.append(orgs_list.get(i).getSlaves().get(j).getName()+"\n");
                        }
                        infoLabel.append("--------------\n");
                    }
                    //System.out.println(ans);
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
//--------------------------------------------------------------------------------
        employeesButton.addActionListener(buttonActionListener);
        organizationsButton.addActionListener(buttonActionListener);
        bossesButton.addActionListener(buttonActionListener);
//--------------------------------------------------------------------------------
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
                        try{
                            addslave(nameField.getText(), Integer.parseInt(ageField.getText()), Integer.parseInt(experienceField.getText()),  Integer.parseInt(salaryField.getText()), dreamJobField.getText());
                            addEmployeeFrame.dispose();
                            mainFrame.setVisible(true);
                        }
                        catch(Exception ex){
                            JFrame woops = new JFrame();
                            woops.setSize(200, 200);
                            woops.add(new JTextArea("Неправильно набран номер"));
                            woops.setVisible(true);
                        }

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

//--------------------------------------------------------------------------------
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
                        try {
                            addboss(nameField.getText(), Integer.parseInt(ageField.getText()), selected);
                            addbossframe.dispose(); // Закрываем текущее окно
                            mainFrame.setVisible(true);
                        }catch(Exception ex){
                            JFrame woops = new JFrame();
                            woops.setSize(200, 200);
                            woops.add(new JTextArea("Неправильно набран номер"));
                            woops.setVisible(true);
                        }
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
//--------------------------------------------------------------------------------
        ActionListener newOrg = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addOrgFrame = new JFrame("Организационная мастерская");
                addOrgFrame.setSize(400, 200);

                JTextField nameField = new JTextField(10);

                JPanel addOrgPanel = new JPanel();
                addOrgPanel.setLayout(new GridLayout(slave_list.size() + 1, 1));

                addOrgPanel.add(new JLabel("Имя:"));
                addOrgPanel.add(nameField);
                addOrgPanel.add(new JLabel("боссы:"));



                addOrgFrame.add(addOrgPanel);

                ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
                ArrayList<Boss> selected = new ArrayList<Boss>();

                for (int i = 0; i < boss_list.size(); i++) {
                    JCheckBox c = new JCheckBox(boss_list.get(i).getName());
                    c.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JCheckBox cb = (JCheckBox) e.getSource();
                            if (cb.isSelected()) {
                                for (int i = 0; i < boss_list.size(); i++) {
                                    if (boss_list.get(i).getName() == cb.getText()) {
                                        selected.add(boss_list.get(i));
                                    }
                                }

                            } else {
                                //for(int i = 0; i< orgs_list.size();i++){
                                //if (orgs_list.get(i).getName() == cb.getText()){
                                //selected.remove();
                                //}
                                //}
                            }

                        }
                    });

                    checkBoxes.add(c);
                    addOrgPanel.add(c);
                }

                JButton backButton = new JButton("Назад");
                JButton enter = new JButton("Добавить");

                // Обработчик события для кнопки "Назад"
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addOrgFrame.dispose(); // Закрываем текущее окно
                        mainFrame.setVisible(true);
                    }
                });
                enter.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addorg(nameField.getText(), selected);

                        addOrgFrame.dispose(); // Закрываем текущее окно
                        mainFrame.setVisible(true);
                    }
                });

                addOrgFrame.add(backButton, BorderLayout.SOUTH);
                addOrgFrame.add(enter, BorderLayout.EAST);
                mainFrame.setVisible(false);
                addOrgFrame.setVisible(true);
            }
        };
        addOrg.addActionListener(newOrg);

//--------------------------------------------------------------------------------

        ActionListener bossbutton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f = new JFrame("Боссовская");
                f.setSize(500,500);

                ArrayList <JButton> orgbut = new ArrayList<JButton>();

                JPanel butpanel = new JPanel();
                butpanel.setLayout(new GridLayout(boss_list.size() + 1, 1));

                for(int i=0; i < boss_list.size(); i++){

                    JButton _b = new JButton(boss_list.get(i).getName());
                    Boss sellected_boss = boss_list.get(i);
                    _b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JFrame subf = new JFrame("(Игро)Оргии");
                            subf.setSize(500,500);

                            JPanel subbutpanel = new JPanel();
                            subbutpanel.setLayout(new GridLayout (sellected_boss.getComps().size() + 1, 1));

                            //System.out.println(sellected_boss.getComps().size());

                            for(int j = 0; j< sellected_boss.getComps().size(); j++){
                                Organization sellected_comp = sellected_boss.getComps().get(j);
                                //System.out.println(j);
                                JButton _subb = new JButton(sellected_comp.getName());
                                subbutpanel.add(_subb);
                                _subb.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                      JFrame subsubf = new JFrame("Пожалуйста, не кликай дальше");
                                      subsubf.setSize(500, 500);

                                      JPanel subsubpanel = new JPanel();
                                      subsubpanel.setLayout(new GridLayout(4,1));

                                      JButton addvac = new JButton("Добавить вакансию");
                                      JButton testslave = new JButton("Нанять раба");
                                      JButton sellOrg = new JButton("Продать бизнесс");
                                      JButton killslave = new JButton("Уволить роботягу");

                                      addvac.addActionListener(new ActionListener() {
                                          @Override
                                          public void actionPerformed(ActionEvent e) {
                                              JFrame vacf = new JFrame("Перестань пользоваться этими кнопками");
                                              vacf.setSize(500, 500);

                                              JTextField vacname = new JTextField();
                                              JTextField reqexp = new JTextField();
                                              JTextField salary = new JTextField();

                                              JPanel vacp = new JPanel();
                                              vacp.setLayout(new GridLayout(4, 2));

                                              vacp.add(new JLabel("Название вакансии: "));
                                              vacp.add(vacname);
                                              vacp.add(new JLabel("Требуемый опыт: "));
                                              vacp.add(reqexp);
                                              vacp.add(new JLabel("Зарплата: "));
                                              vacp.add(salary);

                                              JButton enter = new JButton("добавить");
                                              //добавление вакансии
                                              enter.addActionListener(new ActionListener() {
                                                  @Override
                                                  public void actionPerformed(ActionEvent e) {
                                                      sellected_comp.addVacancy(vacname.getText(), Integer.parseInt(reqexp.getText()), Integer.parseInt(salary.getText()));
                                                      vacf.dispose();
                                                  }
                                              });
                                              vacp.add(enter);

                                              vacf.add(vacp);
                                              vacf.setVisible(true);

                                          }
                                      });
                                      testslave.addActionListener(new ActionListener() {
                                          @Override
                                          public void actionPerformed(ActionEvent e) {
                                              JFrame results = new JFrame();
                                              results.setSize(500, 500);

                                              JTextArea ans = new JTextArea();

                                              for(int k = 0; k< slave_list.size(); k++){

                                                  ans.append(sellected_boss.test(slave_list.get(k), sellected_comp));
                                              }
                                              results.add(ans);
                                              results.setVisible(true);
                                          }
                                      });
                                      sellOrg.addActionListener(new ActionListener() {
                                          @Override
                                          public void actionPerformed(ActionEvent e) {

                                              sellected_boss.sellComp(sellected_comp);
                                              sellected_comp.dropBoss(sellected_boss);
                                          }
                                      });
                                      killslave.addActionListener(new ActionListener() {
                                          @Override
                                          public void actionPerformed(ActionEvent e) {
                                              JFrame hit_list_f = new JFrame();
                                              hit_list_f.setSize(500, 500);

                                              ArrayList <Employee> hit_list = new ArrayList<Employee>();
                                              JPanel p = new JPanel();
                                              p.setLayout(new GridLayout(sellected_comp.getSlaves().size()+1, 1));

                                              for(int i = 0; i< sellected_comp.getSlaves().size(); i++){
                                                  Employee selected_emp = sellected_comp.getSlaves().get(i);
                                                  JCheckBox j = new JCheckBox(selected_emp.getName());
                                                  p.add(j);
                                                  j.addActionListener(new ActionListener() {
                                                      @Override
                                                      public void actionPerformed(ActionEvent e) {
                                                          hit_list.add(selected_emp);
                                                      }
                                                  });
                                              }

                                              JButton enter =  new JButton("Удалить выбранных");
                                              enter.addActionListener(new ActionListener() {
                                                  @Override
                                                  public void actionPerformed(ActionEvent e) {
                                                      for(int i = 0; i< hit_list.size(); i++){
                                                          sellected_boss.killSlave(hit_list.get(i));
                                                      }
                                                      hit_list_f.setVisible(false);
                                                  }
                                              });
                                              p.add(enter);

                                              hit_list_f.add(p);
                                              hit_list_f.setVisible(true);
                                          }
                                      });

                                      subsubpanel.add(addvac);
                                      subsubpanel.add(testslave);
                                      subsubpanel.add(sellOrg);
                                      subsubpanel.add(killslave);

                                      subsubf.add(subsubpanel);
                                      subsubf.setVisible(true);
                                      subf.setVisible(false);
                                    }
                                });
                                System.out.println("i was here");

                            }
                            System.out.println("im here");
                            subf.add(subbutpanel);
                            subf.setVisible(true);
                        }
                    });
                    butpanel.add(_b);
                }

                //butpanel.add(new JButton("test"));
                f.add(butpanel);
                f.setVisible(true);
                //mainFrame.setVisible(false);
            }
        };
        bosses.addActionListener(bossbutton);

        ActionListener slavebutton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f = new JFrame("Работяжная");
                f.setSize(500,500);

                JPanel p = new JPanel();
                p.setLayout(new GridLayout(slave_list.size()+1, 1));

                for(int i = 0; i< slave_list.size(); i++){
                    Employee selected_slave = slave_list.get(i);
                    JButton b = new JButton(selected_slave.getName());

                    b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JFrame subf = new JFrame("Работяжная");
                            subf.setSize(500,500);

                            JPanel subp = new JPanel();
                            subp.setLayout(new GridLayout(1, 1));

                            JButton free_at_last = new JButton("Стать свободным");
                            free_at_last.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    selected_slave.imleavin();
                                    subf.setVisible(false);
                                }
                            });
                            subp.add(free_at_last);
                            subf.add(subp);
                            subf.setVisible(true);
                        }
                    });
                    p.add(b);
                }
                f.add(p);
                f.setVisible(true);
            }
        };

        slaves.addActionListener(slavebutton);

            // Отображаем главное окно
        mainFrame.setVisible(true);


    }

}
