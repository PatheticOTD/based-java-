import java.util.ArrayList;
import java.util.List;

class Organization {
    private String name = new String();                         // Имя
    private List<String> owners = new ArrayList<String>();      // Владельцы
    private List<Employee> slaves = new ArrayList<Employee>();  // Смузихлебы

    private List<String> vacancies = new ArrayList<String>();           // Списки с нужной инфой для открытых вакансий
    private List<Integer> offeredSalary = new ArrayList<Integer>();
    private List<Integer> requiredExperience = new ArrayList<Integer>();
    
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
    public void addBoss(String boss_name){
        owners.add(boss_name);
    }
    public List<String> getBosses(){
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
        return vacancies;
    }
    public List<Integer> getRequiredExperience() {
        return requiredExperience;
    }
    public List<Integer> getOfferedSalary() {
        return offeredSalary;
    }


    // добавление вакансий
    public void addVacancy(String vacancy, int requiredExperience, int offeredSalary) {
        vacancies.add(vacancy);
        this.requiredExperience.add(requiredExperience);
        this.offeredSalary.add(offeredSalary);
    }
    // изменение статуса вакансии
    public void changeVacStatus(String vacancy, String what_to_do){ 
        if (what_to_do == "open-to-close"){     // Если я хочу переместить вакансию с открытой в закрытую (иногда такое бывает)
            for(int i = 0; i< this.vacancies.size(); i++){
                if (this.vacancies.get(i) == vacancy){

                    this._vacancies.add(this.vacancies.get(i));
                    this._requiredExperience.add(this.requiredExperience.get(i)); // добавляем в закрытые
                    this._offeredSalary.add(this.offeredSalary.get(i));

                    this.vacancies.remove(i);
                    this.requiredExperience.remove(i); // удаляем из открытых
                    this.offeredSalary.remove(i);
                }
            }
        }
        else if (what_to_do == "close-to-open"){
            for(int i = 0; i< this._vacancies.size(); i++){
                if (this._vacancies.get(i) == vacancy){

                    this.vacancies.add(this._vacancies.get(i));
                    this.requiredExperience.add(this._requiredExperience.get(i)); // добавляем в открытые
                    this.offeredSalary.add(this._offeredSalary.get(i));

                    this._vacancies.remove(i);
                    this._requiredExperience.remove(i); // удаляем из закрытых
                    this._offeredSalary.remove(i);
                }
            }
        }
        else System.out.println("There's only to options: \"open-to-close\" and \"close-to-open\"");
    }
    // добавление перегрузки. я решил, что чаще буду вызывать функцию выше для найма сотрудников
    public void changeVacStatus(String vacancy){
        changeVacStatus(vacancy, "open-to-close");
    }

    public void info(){
        System.out.println("----"+this.name+"----");
        System.out.println("OWNERS:");
        for ( int i = 0; i< owners.size(); i++){
            System.out.println(owners.get(i));
        }
        System.out.println("EMPLOYEES:");
        for ( int i = 0; i< this.slaves.size(); i++){
            System.out.println(this.slaves.get(i).getName());
        }
        System.out.println("VACANCIES:");
        for ( int i = 0; i< vacancies.size(); i++){
            System.out.println(vacancies.get(i));
        }
        System.out.println("Closed VACANCIES:");
        for ( int i = 0; i< _vacancies.size(); i++){
            System.out.println(_vacancies.get(i));
        }
        System.out.println("-----------------------------------------");

    }
    
}