package com.adinfi.seven.presentation.views.util;

import com.adinfi.seven.business.domain.TblActividad;
import com.adinfi.seven.business.domain.TblCampanaActividades;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.activation.MailcapCommandMap;
import javax.activation.MimetypesFileTypeMap;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {
    private static final String SMTP_HOST_NAME = Messages.getString("SMTP_HOST_NAME");
    private static final String SMTP_AUTH_USER = Messages.getString("SMTP_AUTH_USER");
    private static final String SMTP_PORT = Messages.getString("SMTP_PORT");
    private static final String SMTP_AUTH_USER_PERSONAL = Messages.getString("SMTP_AUTH_USER_PERSONAL");
    private static final String SMTP_AUTH_PWD = Messages.getString("SMTP_AUTH_PWD");
    private static final String EMAIL_RECOVERY_PASS_TITLE = Messages.getString("EMAIL_RECOVERY_PASS_TITLE");
    private static final String EMAIL_RECOVERY_PASS_DETAIL = "EMAIL_RECOVERY_PASS_DETAIL";
    private static final String SMTP_FROM_EMAIL = Messages.getString("SMTP_FROM_EMAIL");
    private static Session session;

    public static void init() {
        if (session == null) {
            Properties properties = System.getProperties();
            properties.put("mail.transport.protocol", "smtp");
            properties.put("mail.smtp.host", SMTP_HOST_NAME);
            properties.put("mail.smtp.auth", "true");
            //properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.port", SMTP_PORT);

            session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(SMTP_AUTH_USER, SMTP_AUTH_PWD);
                }
            });
        }
    }

    public static void sendPassword(String mail, String password) {
        init();
        String to = mail;
        String from = SMTP_FROM_EMAIL;
        String fromPersonal = SMTP_AUTH_USER_PERSONAL;
        MimeMessage message;
        try {
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from,fromPersonal));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(EMAIL_RECOVERY_PASS_TITLE);
            message.setContent(Messages.getString(EMAIL_RECOVERY_PASS_DETAIL, password),"text/html");
            Transport.send(message);
        } catch (MessagingException | UnsupportedEncodingException mex) {
                Util.logger(SendMail.class).error(mex);
        }
    }

    public static void sendGenericEmail(String toMails, String subject,String bodyPropertie, Object... params) {
            sendGenericEmail(new String[] { toMails }, subject, bodyPropertie,params);
    }

    public static void sendGenericEmail(String[] toMails, String subject,String bodyPropertie, Object... params) {
        try {
            init();
            String from = SMTP_FROM_EMAIL;
            String fromPersonal = SMTP_AUTH_USER_PERSONAL;
            MimeMessage message;
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from,fromPersonal));
            for (String mail : toMails) {
                    message.addRecipient(Message.RecipientType.TO,new InternetAddress(mail));
            }
            message.setSubject(Messages.getString(subject));
            message.setContent(Messages.getString(bodyPropertie, params),"text/html;charset=\"ISO-8859-1\"");
            Transport.send(message);
        } catch (MessagingException | UnsupportedEncodingException mex) {
                Util.logger(SendMail.class).error(mex);
        }
    }
	
    public static void sendGenericEmail(String[] toMails, String subject, String body) {
        try {
            init();
            String from = SMTP_FROM_EMAIL;
            String fromPersonal = SMTP_AUTH_USER_PERSONAL;
            MimeMessage message;
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from,fromPersonal));
            for (String mail : toMails) {
                    message.addRecipient(Message.RecipientType.TO,new InternetAddress(mail));
            }
            message.setSubject(subject);
            message.setContent(body,"text/html;charset=\"ISO-8859-1\"");
            //message.setText(body, "ISO-8859-1");
            Transport.send(message);
        } catch (MessagingException | UnsupportedEncodingException mex) {
                Util.logger(SendMail.class).error(mex);
        }
    }

    public static void sendRechazoDisenoMail(String [] toMails, TblActividad actividad, String mecanicaName, int userRole) throws Exception {
        init();
        String roleString = "";

        if (userRole == 3){
            roleString = "Precios";
        }else if (userRole == 1){
            roleString = "Category";
        }
        String msj = "El diseño de la mecanica " + mecanicaName + " fue rechazado por " + roleString + "<br /><br />La actividad ha sido reabierta";
        MimetypesFileTypeMap mimetypes = (MimetypesFileTypeMap)MimetypesFileTypeMap.getDefaultFileTypeMap();
        mimetypes.addMimeTypes("text/calendar ics ICS");

        MailcapCommandMap mailcap = (MailcapCommandMap) MailcapCommandMap.getDefaultCommandMap();
        mailcap.addMailcap("text/calendar;; x-java-content-handler=com.sun.mail.handlers.text_plain");

        Object[] params = new Object[] {"Rechazo de "+ roleString +" Mecanica " + mecanicaName, new Date()};

        MimeMessage message;
        message = new MimeMessage(session);
        message.setFrom(new InternetAddress(Messages.getString("SMTP_FROM_EMAIL")));
        message.setSubject("Rechazo de "+ roleString +" en Mecanica " + mecanicaName);
        for (String mail: toMails){
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
        }

        Multipart multipart = new MimeMultipart("alternative");

        BodyPart messageBodyPart = buildHtmlTextPart(msj, params);
        multipart.addBodyPart(messageBodyPart);

        BodyPart calendarPart = buildCalendarPart(Utileria.dateToCalendar(actividad.getVigenciaInicio()),
                Utileria.dateToCalendar(actividad.getFechaFin()),"Actividad: " + actividad.getDescripcion(), toMails[0], "CONFIRMED", actividad );
        multipart.addBodyPart(calendarPart);

        message.setContent(multipart);

        Transport transport = session.getTransport("smtp");
        transport.connect();
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
    
    public static void sendActivity(String[] toMails, TblActividad activity, boolean edit) throws MessagingException, Exception{
        init();
        MimetypesFileTypeMap mimetypes = (MimetypesFileTypeMap)MimetypesFileTypeMap.getDefaultFileTypeMap();
        mimetypes.addMimeTypes("text/calendar ics ICS");

        MailcapCommandMap mailcap = (MailcapCommandMap) MailcapCommandMap.getDefaultCommandMap();
        mailcap.addMailcap("text/calendar;; x-java-content-handler=com.sun.mail.handlers.text_plain");

        Object[] params = new Object[] {edit ? "Actualizacion Actividad" : "Nueva Actividad",new Date()};
        MimeMessage message;
        message = new MimeMessage(session);
        message.setFrom(new InternetAddress(Messages.getString("SMTP_FROM_EMAIL")));
        message.setSubject(edit ? "Actualizacion Actividad AdMaster - " : "Nueva Actividad AdMaster - " + activity.getDescripcion());
        for (String mail: toMails){
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
        }

        Multipart multipart = new MimeMultipart("alternative");

        BodyPart messageBodyPart = buildHtmlTextPart(Constants.EMAIL_NUEVA_ACTIVIDAD_DETAIL, params);
        multipart.addBodyPart(messageBodyPart);

        BodyPart calendarPart = buildCalendarPart(Utileria.dateToCalendar(activity.getVigenciaInicio()),
                Utileria.dateToCalendar(activity.getFechaFin()),"Actividad: " + activity.getDescripcion(), toMails[0], "CONFIRMED", activity );
        multipart.addBodyPart(calendarPart);

        message.setContent(multipart);

        Transport transport = session.getTransport("smtp");
        transport.connect();
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
	
    public static void sendGenericMeeting(Calendar calStart, Calendar calEnd, String from, String[] toMails, String subject, String bodyPropertie, Object... params) throws Exception{

        init();
        MimetypesFileTypeMap mimetypes = (MimetypesFileTypeMap)MimetypesFileTypeMap.getDefaultFileTypeMap();
        mimetypes.addMimeTypes("text/calendar ics ICS");

        MailcapCommandMap mailcap = (MailcapCommandMap) MailcapCommandMap.getDefaultCommandMap();
        mailcap.addMailcap("text/calendar;; x-java-content-handler=com.sun.mail.handlers.text_plain");

        MimeMessage message;
        message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setSubject(Messages.getString(subject));
        for (String mail: toMails){
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
        }

        Multipart multipart = new MimeMultipart("alternative");

        BodyPart messageBodyPart = buildHtmlTextPart(bodyPropertie, params);
        multipart.addBodyPart(messageBodyPart);

        BodyPart calendarPart = buildCalendarPart(calStart, calEnd,"General Meeting","organizer@yahoo.com","CONFIRMED", null);
        multipart.addBodyPart(calendarPart);

        message.setContent(multipart);

        Transport transport = session.getTransport("smtp");
        transport.connect();
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();

    }
	
	
    private static BodyPart buildHtmlTextPart(String bodyPropertie, Object... params) throws MessagingException {
        
        MimeBodyPart descriptionPart = new MimeBodyPart();
        String content = Messages.getString(bodyPropertie, params);
        descriptionPart.setContent(content, "text/html; charset=utf-8");
 
        return descriptionPart;
    }
 
    private static BodyPart buildCalendarPart(Calendar calStart, Calendar calEnd, String description, String mailTo, String status, TblActividad activity) throws Exception {
    	SimpleDateFormat iCalendarDateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmm'00'");
        BodyPart calendarPart = new MimeBodyPart();
        Date start = calStart.getTime();
        Date end = calEnd.getTime();
 
        StringBuilder calendarContent = new StringBuilder();
        calendarContent.append("BEGIN:VCALENDAR\n");
        calendarContent.append("METHOD:REQUEST\n");
        calendarContent.append("PRODID: BCP - Meeting\n");
        calendarContent.append("VERSION:2.0\n");
        calendarContent.append("BEGIN:VEVENT\n");
        calendarContent.append("DTSTAMP:"); 
        calendarContent.append(iCalendarDateFormat.format(start));
        calendarContent.append("\n");
        calendarContent.append("DTSTART:"); 
        calendarContent.append(iCalendarDateFormat.format(start));
        calendarContent.append("\n");
        calendarContent.append("DTEND:").append(iCalendarDateFormat.format(end)).append("\n");
        calendarContent.append("SUMMARY: Respuesta\n");
        calendarContent.append("UID:324\n");
        calendarContent.append("ATTENDEE;ROLE=REQ-PARTICIPANT;PARTSTAT=NEEDS-ACTION;RSVP=TRUE:MAILTO:").append(mailTo).append("\n");
        calendarContent.append("ORGANIZER:MAILTO:").append(mailTo).append("\n");
        calendarContent.append("LOCATION:NA 3\n");
        calendarContent.append("DESCRIPTION:").append(description).append("\n");
        
        if(activity != null){
            calendarContent.append("AVANCE: ").append(activity.getAvance()).append(" %\n");
        }
        
        
        calendarContent.append("SEQUENCE:0\n");
        calendarContent.append("PRIORITY:5\n");
        calendarContent.append("CLASS:PUBLIC\n");
        calendarContent.append("STATUS:").append(status).append("\n");
        calendarContent.append("TRANSP:OPAQUE\n");
        calendarContent.append("BEGIN:VALARM\n");
        calendarContent.append("ACTION:DISPLAY\n");
        calendarContent.append("DESCRIPTION:REMINDER\n");
        calendarContent.append("TRIGGER;RELATED=START:-PT00H15M00S\n");
        calendarContent.append("END:VALARM\n");
        calendarContent.append("END:VEVENT\n");
        calendarContent.append("END:VCALENDAR");
 
        calendarPart.addHeader("Content-Class", "urn:content-classes:calendarmessage");
        calendarPart.setContent(calendarContent.toString(), "text/calendar;method=CANCEL");
 
        return calendarPart;
    }
	
    @Deprecated
    private static class SMTPAuthenticator extends javax.mail.Authenticator {
        @Override
        public PasswordAuthentication getPasswordAuthentication() {
                String username = SMTP_AUTH_USER;
                String password = SMTP_AUTH_PWD;
                return new PasswordAuthentication(username, password);
        }
    }
}
