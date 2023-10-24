
class Employee extends Person {

    private int exp;
    private Organization work;
    private int desiredSalary;
    private String dreamjob; 
    private boolean ishired = false;

    public Employee(String name, int age, int experience, int desiredSalary, String dreamjob) {
        super(name, age);
        this.exp = experience;
        this.desiredSalary = desiredSalary;
        this.dreamjob = dreamjob;
    }
    
    public int getExp(){
        return exp;
    }
    public int GetDesiredSalary(){
        return desiredSalary;
    }
    public boolean getstatus(){
        return this.ishired;
    }
    public String getWorkName(){
        return work.getName();
    }
    public String getDream(){
        return dreamjob;
    }
    
    //Нанят или уволен
    public void hired(Organization org){
        ishired = true;
        work = org;
    }
    public void fired(){
        ishired = false;
        work = new Organization();
    }
    //А может он сам уволиться хочет?
    public void imleavin(){
        for(int i = 0 ; i< work.getSlaves().size();i++){ // Я тут смотрю работников компании по имени.
                if (work.getSlaves().get(i).getName() == getName()){ // если оно такое же как и у желающего уволиться, ...
                    work.getSlaves().remove(i);  // то он увольняется
                    work.changeVacStatus(dreamjob, "close-to-open"); // Вакансия, которую он занимал, вновь открывается. Проверка идет по работе мечты, так как dreamjob идентична названию вакансии
                    fired(); // очистка work от компании. задание статуса безработного в ishired
                }
            }

    }
    
    public void info(){
        System.out.println("-----------------------------------------");
        System.out.println("name:       " + getName());
        System.out.println("age:        " + getAge());
        System.out.println("expirience: " + this.exp);
        System.out.println("work:      " + work.getName());
        System.out.println("-----------------------------------------");
    }
}