package com.glc.iacs__springboot.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    // public boolean sendMail(
    // Long fundingId,
    // String supervisorEmail,
    // String name
    // ){
    // SimpleMailMessage message = new SimpleMailMessage();
    // if (supervisorEmail == null || supervisorEmail.trim().isEmpty()) {
    // return false;
    // }
    // message.setFrom("iacs.corporation@gmail.com");
    // message.setTo(supervisorEmail.trim());
    // message.setSubject("IACS | Project Funding Request");
    // message.setText("name "+name+"localhost/project/"+fundingId+"</h1>");
    // mailSender.send(message);
    // return true;

    // }
    public boolean sendMail(
            String fundingId,
            String supervisorEmail,
            String supervisorName,
            String studentName,
            String studentEmail,
            String projectTitle) {
        if (supervisorEmail == null || supervisorEmail.trim().isEmpty()) {
            return false;
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("iacs.corporation@gmail.com");
        message.setTo(supervisorEmail.trim()); // Remove leading/trailing whitespaces.
        message.setSubject("IACS\t-\tProject Funding Approval");

        String link = "http://localhost/funding-approval?funding=" + fundingId;

        String emailBody = String.format(
                "Respected %s,\n\n" +
                        "I hope this email finds you well. We are willing to inform you that your student, %s, has applied for project funding through our Platform 'IACS' Industrial Academia Coordination System.\n\n"
                        +
                        "Student Details:\n" +
                        "Name: %s\n" +
                        "Email: %s\n" +
                        "Project Title: %s\n\n" +
                        "%s is excited about the project and is looking forward to receiving approval for the funding. The project's potential impact and educational value are significant, and we believe it aligns well with our institution's goals.\n\n"
                        +
                        "We kindly request your support and approval for the funding of this project. Your mentorship and guidance will play a crucial role in ensuring the success of %s's endeavor.\n\n"
                        +
                        "Please use the following link to approve or reject the funding request:\n" +
                        "%s\n\n" +
                        "Please let us know if you have any questions or if any additional information is required. We appreciate your attention to this matter and thank you for your continued support in nurturing our students' academic pursuits.\n\n"
                        +
                        "IACS\n",
                supervisorName, studentName, studentName, studentEmail, projectTitle, studentName, studentName, link);

        message.setText(emailBody);
        mailSender.send(message);
        return true;
    }

    public boolean sendStudentNotificationMail(
            String studentEmail,
            String studentName,
            String projectTitle,
            String status) {
        if (studentEmail == null || studentEmail.trim().isEmpty()) {
            return false;
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("iacs.corporation@gmail.com");
        message.setTo(studentEmail.trim()); // Remove leading/trailing whitespaces.
        message.setSubject("IACS | Funding Request Status");


        String emailBody = String.format(
                "Dear %s,\n\n" +
                        "I hope this email finds you well. We would like to inform you about the status of your funding request for the project '%s'.\n\n"
                        +
                        "Project Title: %s\n" +
                        "Status: %s\n\n" +
                        "Thank you for your interest and effort in pursuing this project. If you have any questions or need further information, please don't hesitate to contact us.\n\n"
                        +
                        "IACS\n",
                studentName, projectTitle, projectTitle, status);

        message.setText(emailBody);
        mailSender.send(message);
        return true;
    }

}
