package cz.muni.fi.pa165.enums;





public enum AuthenticateUserStatus {

    OK("Mail and password is OK"),
    UNKNOWN("no find User by email"),
    BAD_PASSWORD("Bad password");


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    AuthenticateUserStatus(String description) {
        this.description=description;
    }

    public String description() {
        return description;
    }
}
