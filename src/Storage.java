import java.util.ArrayList;

interface StorageConnector{
    public void addslave(String name, int age, int exp, int dsal, String dj);
    public void addorg(String _name, ArrayList<Boss> _bosses);
    public void addboss(String _name, int _age, ArrayList<Organization> _orgs);
    public ArrayList<Employee> get_slaves();
    public ArrayList<Organization> get_orgs();
    public ArrayList<Boss> get_bosses();
}
public class Storage implements StorageConnector{
    private ArrayList<Employee> slave_list = new ArrayList<Employee>();
    private ArrayList <Organization> orgs_list = new ArrayList<Organization>();
    private ArrayList<Boss> boss_list = new ArrayList<Boss>();

    public void addslave(String name, int age, int exp, int dsal, String dj){
        Employee emp = new Employee(name, age, exp, dsal, dj);
        slave_list.add(emp);
    }
    public void addorg(String _name, ArrayList<Boss> _bosses){
        Organization o = new Organization();
        o.setName(_name);
        for(int i = 0; i< _bosses.size(); i++){
            o.addBoss(_bosses.get(i));
            _bosses.get(i).addComp(o);
        }
        orgs_list.add(o);

    }
    public void addboss(String _name, int _age, ArrayList<Organization> _orgs){
        Boss b = new Boss(_name, _age);
        for( int i = 0; i< _orgs.size(); i++){
            b.addComp(_orgs.get(i));
            _orgs.get(i).addBoss(b);
        }
        boss_list.add(b);
    }


    public ArrayList<Employee> get_slaves(){
        return slave_list;
    }
    public ArrayList<Organization> get_orgs(){return orgs_list;}

    public ArrayList<Boss> get_bosses(){return boss_list; }



    //здесь неначто смотреть
}
