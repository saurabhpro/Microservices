package org.saurabh.users;

import java.util.Map;

public record ErrorResponse(String title, int status, String type, Map<String, String> details) {

}
