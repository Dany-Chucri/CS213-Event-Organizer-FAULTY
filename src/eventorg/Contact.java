package src.eventorg;

/**
 An event's contact information, including their department and email.
 Contains method to check a contact's validity.
 @author Dany Chucri, Madhur Nutulapati
 */
public class Contact {
    private final Department department;
    private final String email;
    public static final String[] departValid = {"EE","CS","BAIT","MATH","ITI"};

    /**
     Creates an instance of Timeslot with a given hour and number.
     @param department A choice from the Department enum.
     @param email The Contact's email.
     */
    public Contact(Department department, String email){
        this.email = email;
        this.department = department;
    }

    /**
     Basic getter for a Contact's department.
     @return A Department enum representing the Contact's department.
     */
    public Department getDepartment() {
        return department;
    }

    /**
     Basic getter for a Contact's email.
     @return A String representing the Contact's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     Checks validity of an email.
     An email address must belong to @rutgers.edu.
     @return 1 for a valid email, 0 otherwise.
     */
    private boolean validEmail(){
        return this.email.endsWith("@rutgers.edu") && this.email != null ;
    }

    /**
     Checks validity of a Department.
     Valid departments are BAIT, CS, EE, ITI, and MATH.
     @return 1 for a valid Department, 0 otherwise.
     */
    private boolean validDepart(){
        for(String validDepartment:departValid){
            if (department.toString().equals(validDepartment))
                return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * Represents Contact as "Department, Email".
     * @return A string in the above form.
     */
    @Override
    public String toString(){
        return this.department.getFullMajorName() + ", " + this.email;
    }

    /**
     Checks validity of a Contact instance.
     Combines validity of Department and email variables.
     @return 1 for a valid email, 0 otherwise.
     */
    public boolean isValid(){
        return validDepart() && validEmail();
    }

    public static void main(String[] args){
    }

}