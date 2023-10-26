import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vacancy {
    private List<String> vacancies = new ArrayList<String>();           // Списки с нужной инфой для открытых вакансий
    private List<Integer> offeredSalary = new ArrayList<Integer>();
    private List<Integer> requiredExperience = new ArrayList<Integer>();

    public void addvac(String name, int salary, int exp){
        vacancies.add(name);offeredSalary.add(salary);requiredExperience.add(exp);
    }
    public void remvac(String name){
        for(int i=0; i< vacancies.size(); i++){
            if(name == vacancies.get(i)){
                vacancies.remove(vacancies.get(i));
                offeredSalary.remove(offeredSalary.get(i));
                requiredExperience.remove(requiredExperience.get(i));
            }
        }
    }

    public List<String> getVacancies() {
        return vacancies;
    }
    public List<Integer> getRequiredExperience() {
        return requiredExperience;
    }
    public List<Integer> getOfferedSalary() {
        return offeredSalary;
    }
    public String show_vacs(){

        String ans = new String();
        for (int i =0; i<vacancies.size(); i++){
            ans.concat("name- "+vacancies.get(i)+"\n");
            ans.concat("salary- "+offeredSalary.get(i)+"\n");
            ans.concat("exp- "+ requiredExperience.get(i)+"\n");
        }
        return ans;
    }

}
