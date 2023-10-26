import java.util.ArrayList;

public class Storage {
    private ArrayList<Employee> slave_list = new ArrayList<Employee>();
    private ArrayList <Organization> orgs_list = new ArrayList<Organization>();
    private ArrayList<Boss> boss_list = new ArrayList<Boss>();

    public void add(Employee e){
        slave_list.add(e);
    }
    public void add(Organization o){
        orgs_list.add(o);
    }
    public void add(Boss b){
        boss_list.add(b);
    }

    public ArrayList<Employee> get_slaves(){
        return slave_list;
    }
    public ArrayList<Organization> get_orgs(){
        return orgs_list;
    }

    //здесь неначто смотреть
}
