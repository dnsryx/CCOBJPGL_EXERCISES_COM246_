public class App {
    public static void main (String[]args)throws Exception{

Datascientist datascientist1 = new Datascientist("John", null, null);

        
        System.out.println("My Name is "+ datascientist1.name);
       System.out.println("His Work is " + datascientist1.Getwork());
     System.out.println("And He earns " + datascientist1.Getsalary());

Researcher reseacher1 = new Researcher("Jane", null, null);
        
        System.out.println("My Name is "+ reseacher1.name);
        System.out.println("Her Work is " + reseacher1.Getwork());
        System.out.println("And she earns " + reseacher1.Getsalary());

}

}
