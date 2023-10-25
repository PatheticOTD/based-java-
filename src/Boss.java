import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Boss extends Person {
     private List<Organization> companies = new ArrayList<Organization>(); 

    // Конструктор класса Boss
    public Boss(String name, int age) {
        super(name, age);
    }

    // Метод для собеседования кандидата
    public void test(Employee _emp) {
        for (int i = 0; i<companies.size(); i++){   //Пробегаемся по компаниям

            var vac = companies.get(i).getVacancies(); // модные сокращения
            var req_exp = companies.get(i).getRequiredExperience();
            var salary = companies.get(i).getOfferedSalary();

            for (int j = 0; j < vac.size(); j++){ // пробегаемся по вакансиям
                if (Objects.equals(_emp.getDream(), vac.get(j)) & (_emp.getstatus() == false) & (req_exp.get(j) <= _emp.getExp()) & (_emp.GetDesiredSalary() <= salary.get(j))){
                    System.out.println("Employee "+ _emp.getName() + " ready for " + vac.get(j));
                    _emp.hired(companies.get(i)); // Нанимаем чела. Теперь на другие вакансии его не нанять
                    companies.get(i).addSlave(_emp);
                    companies.get(i).changeVacStatus(vac.get(j)); // итай компании джей вакансия становится неактивной
                    break;
                }
            }
                
        }    
    }
    public String test(Employee e, Organization o){

        var vac = o.getVacancies(); // модные сокращения
        var req_exp = o.getRequiredExperience();
        var salary = o.getOfferedSalary();

        for (int i = 0; i < vac.size(); i++){ // пробегаемся по вакансиям
            System.out.println(e.getDream()+" == "+ vac.get(i) + " - "+ (e.getDream() == vac.get(i)) +"\n");
            System.out.println(e.getExp()+" == "+ req_exp.get(i) + " - "+ (req_exp.get(i) <= e.getExp()) +"\n");
            System.out.println(e.GetDesiredSalary()+" == "+ salary.get(i) + " - "+ (e.GetDesiredSalary() <= salary.get(i)) +"\n");

            if (Objects.equals(e.getDream(), vac.get(i)) & (e.getstatus() == false) & (req_exp.get(i) <= e.getExp()) & (e.GetDesiredSalary() <= salary.get(i))){
                var vname = vac;
                e.hired(o); // Нанимаем чела. Теперь на другие вакансии его не нанять
                companies.get(i).addSlave(e);
                o.changeVacStatus(vac.get(i)); // итай компании джей вакансия становится неактивной
                return ("Employee "+ e.getName() + " ready for " + vname);
            }
        }
        return "Похоже, что никто не подошел";
    }
    public void killSlave(Employee emp){ // Тот, кого мы ищем, чтобы уволить
        for(int i = 0; i< companies.size(); i++){ // проходимся по компаниям
            for(int j = 0 ; j< companies.get(i).getSlaves().size();j++){ // проходимся по работникам
                if (companies.get(i).getSlaves().get(j) == emp){ //Если это Тот, кого мы ищем, то увольняем его
                    companies.get(i).getSlaves().remove(j); // больше он в нашей перспективной компании не работает
                    emp.fired();    //теперь он безработный 
                    companies.get(i).changeVacStatus(emp.getDream(), "close-to-open"); //Набор открыт!
                }
            }
        }
    } 

    public void addComp(Organization comp){ // Добавление компаний
        this.companies.add(comp);

        
        

    }
    public void createComp(String comp_name){// Создание своей компании
        Organization new_org = new Organization();
        new_org.setName(comp_name);
        addComp(new_org);
    }
    public void sellComp(Organization comp){ // Удаление компаний
        for (int i = 0; i < companies.size(); i++){
            if (comp == companies.get(i)){
                companies.remove(i);
                break;
            }
        }
    }
    public List<Organization> getComps(){
        return this.companies;
    }
    
    public void info(){
        
        System.out.println("name: " + getName());
        System.out.println("age: " + getAge());
        System.out.println("---- COMPANIES ----");
        for(int i = 0; i < this.companies.size(); i++){
            System.out.println(this.companies.get(i).getName());
        }
        System.out.println("-----------------------------------------");
    }  
}