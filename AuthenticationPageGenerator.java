package com.company;

class AuthenticationPageGenerator {
    private static String startForm = "<form method='POST'>";
    private static String inputName = "<p>Введите своё имя: </p> <input name='name' type='text'/>";
    private static String endForm = "<p><input type='submit'></p></form>";

    private static String getInputWithSessionId(Integer sessionId) {
        return "<input name='session_id' value='" + sessionId + "' type='text'/ hidden>";
    }

    static String getPage() {
        return startForm + inputName + endForm;
    }

    static String getPageWithSessionId(Integer sessionId) {
        return startForm + inputName + getInputWithSessionId(sessionId) + endForm;
    }
}
