package org.saurabh.schedule;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.util.FileUtil;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

import java.util.HashMap;
import java.util.Map;

import static j2html.TagCreator.br;
import static j2html.TagCreator.button;
import static j2html.TagCreator.form;
import static j2html.TagCreator.input;
import static j2html.TagCreator.textarea;

public class JavalinHtmlFormsExampleApp {

    private static final Map<String, String> reservations = new HashMap<>();

    static {
        reservations.put("saturday", "No reservation");
        reservations.put("sunday", "No reservation");
    }

    public static void main(String[] args) {

        final var app = Javalin
            .create(config -> config.staticFiles.add("/public", Location.CLASSPATH))
            .start(7070);

        app.post("/make-reservation", ctx -> {
            reservations.put(ctx.formParam("day"), ctx.formParam("time"));
            ctx.html("Your reservation has been saved");
        });

        app.get("/check-reservation", ctx ->
            ctx.html(reservations.get(ctx.queryParam("day")))
        );

        app.post("/upload-example", ctx -> {
            ctx.uploadedFiles("files").forEach(file -> {
                FileUtil.streamToFile(file.content(), "upload/" + file.filename());
            });
            ctx.html("Upload successful");
        });

        app.get("/help", ctx -> ctx.html(
            form()
                .withAction("/contact-us")
                .withMethod("post")
                .with(
                    input().withName("subject").withPlaceholder("Subject"),
                    br(),
                    textarea().withName("message").withPlaceholder("Your message ..."),
                    br(),
                    button("Submit")
                ).render()
        ));

        app.post("/contact-us", ctx -> {
            final var email = new SimpleEmail();
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("<email>", "<password>"));
            email.setSSLOnConnect(true);
            email.setFrom("FROM");
            email.setSubject(ctx.formParam("subject"));
            email.setMsg(ctx.formParam("message"));
            email.addTo("TO");
            email.send(); // will throw email-exception if something is wrong
            ctx.redirect("/contact-us/success");
        });

        app.get("/contact-us/success", ctx -> ctx.html("Your message was sent"));

    }
}