public class Contact {
    private Department department;
    private String email;
    public static final String[] departValid = {"EE","CS","BAIT","MATH","ITI"};
    public Contact(Department department, String email){
        this.email = email;
        this.department=department;
    }
    private boolean validEmail(){
        return this.email.endsWith("@rutgers.edu") && this.email!=null ;
    }
    private boolean validDepart(){
        for(String validDepartment:departValid){
            if (department.toString().equals(validDepartment))
                return true;
        }
        return false;
    }
    public String toString(){
        return this.department + " " + this.email;
    }
    public boolean isValid(){
        return validDepart() && validEmail();
    }

    public static void main(String[] args){
    }

}