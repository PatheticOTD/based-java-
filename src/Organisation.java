import java.util.ArrayList;
import java.util.List;

class Organization {
    private String name = new String();                         // Имя
    private List<Boss> owners = new ArrayList<Boss>();      // Владельцы
    private List<Employee> slaves = new ArrayList<Employee>();  // Смузихлебы

    Vacancy vacs = new Vacancy();
    
    private List<String> _vacancies = new ArrayList<String>();           // Списки с нужной инфой для закрытых вакансий
    private List<Integer> _offeredSalary = new ArrayList<Integer>();    // При увольнении сотрудника вакансии из этой ...
    private List<Integer> _requiredExperience = new ArrayList<Integer>(); // ... группы списков станут вновь открытыми
    
    //работа c именем компании
    public void setName(String n){
        this.name = n;
    }
    public String getName(){
        return this.name;
    }
    //работа с владельцами
    public void addBoss(Boss b){
        owners.add(b);
    }
    public void dropBoss(Boss b){owners.remove(b);}
    public List<Boss> getBosses(){
        return owners;
    }
    // Работа с рабочими
    public void addSlave(Employee empl_name){
        slaves.add(empl_name);
    }
    public List<Employee> getSlaves(){
        return slaves;
    }
    // получение инфы о вакансиях
    public List<String> getVacancies() {
        return vacs.getVacancies();
    }
    public List<Integer> getRequiredExperience() {
        return vacs.getRequiredExperience();
    }
    public List<Integer> getOfferedSalary() {
        return vacs.getOfferedSalary();
    }


    // добавление вакансий
    public void addVacancy(String vacancy, int requiredExperience, int offeredSalary) {
        vacs.addvac(vacancy, requiredExperience, offeredSalary);
    }
    // изменение статуса вакансии
    public void changeVacStatus(String vacancy, String what_to_do){ 
        if (what_to_do == "open-to-close"){     // Если я хочу переместить вакансию с открытой в закрытую (иногда такое бывает)
            for(int i = 0; i< this.vacs.getVacancies().size(); i++){
                if (this.vacs.getVacancies().get(i) == vacancy){

                    /*this._vacancies.add(this.vacancies.get(i));
                    this._requiredExperience.add(this.requiredExperience.get(i)); // добавляем в закрытые
                    this._offeredSalary.add(this.offeredSalary.get(i));*/
                    this.vacs.remvac(vacancy);
                }
            }
        }
        else if (what_to_do == "close-to-open"){
            for(int i = 0; i< this._vacancies.size(); i++){
                if (this._vacancies.get(i) == vacancy){

                    //но мне больше не нужно

                    /*this._vacancies.remove(i);
                    this._requiredExperience.remove(i); // удаляем из закрытых
                    this._offeredSalary.remove(i);*/
                }
            }
        }
        else System.out.println("There's only to options: \"open-to-close\" and \"close-to-open\"");
    }
    // добавление перегрузки. я решил, что чаще буду вызывать функцию выше для найма сотрудников
    public void changeVacStatus(String vacancy){
        changeVacStatus(vacancy, "open-to-close");
    }

    public String info(){
        return vacs.show_vacs();

    }
    
}