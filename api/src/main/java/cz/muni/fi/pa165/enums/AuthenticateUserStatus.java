package cz.muni.fi.pa165.enums;





public enum AuthenticateUserStatus {

    OK("Mail and password is OK"),
    UNKNOWN_USER("User not found"),
    BAD_PASSWORD("Bad password");


      private String definition;

    AuthenticateUserStatus(String definition) {
        this.definition=definition;
    }

}
