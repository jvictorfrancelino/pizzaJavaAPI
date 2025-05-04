package br.com.pizzaAPI.exception;

public enum MessageSystem {

    BAD_REQUEST("400", "Invalid or incorrect request, check values entered");

    private String code;
    private String error;

    MessageSystem(String code, String error) {
        this.code = code;
        this.error = error;
    }

    public String getErro() {
        return this.error;
    }

}
