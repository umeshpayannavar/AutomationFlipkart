package org.example;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import org.jsoup.Jsoup;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OTPFetcher {

    public static String OTPF() {
        String username = "rpa2024testing@gmail.com"; // Replace with your email
        String password = "ptwitzhvoglerwxu"; // Replace with your password or App Password
        String OTP = "";

        // Set up properties for the mail session
        Properties properties = new Properties();
        properties.put("mail.imap.ssl.enable", "true");
        properties.put("mail.imap.host", "imap.gmail.com");
        properties.put("mail.imap.port", "993");

        // Create a session and connect to the email server
        Session session = Session.getInstance(properties);
        try {
            Store store = session.getStore("imap");
            store.connect(username, password);
            System.out.println("Connected to email account.");

            // Open the inbox folder
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            // Get the latest message
            Message[] messages = inbox.getMessages();
            if (messages.length > 0) {
                Message latestMessage = messages[messages.length - 1];
                System.out.println("Subject: " + latestMessage.getSubject());

                // Fetch the content of the email as plain text
                String content = getTextFromMessage(latestMessage);
                System.out.println("Email Content: " + content); // Print the content for debugging

                // Use regex to extract OTP from the email content
                String otp = extractOTP(content);
                if (otp != null) {
                    System.out.println("Extracted OTP: " + otp);
                    return otp; // Return the OTP if found
                }
            } else {
                System.out.println("No messages found in inbox.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return OTP;
    }

    // Method to extract OTP using regex
    private static String extractOTP(String content) {
        // Example OTP pattern: "Your OTP is: 123456" or simply "123456"
        String otpPattern = "\\b\\d{6}\\b"; // Matches exactly 6 digits surrounded by word boundaries (non-digit characters)
        Pattern pattern = Pattern.compile(otpPattern);
        Matcher matcher = pattern.matcher(content);

        if (matcher.find()) {
            return matcher.group(); // Return the first matched OTP
        }
        return null; // No OTP found
    }

    // Method to get the plain text content from a message
    private static String getTextFromMessage(Message message) throws Exception {
        // Check if the message is plain text
        if (message.isMimeType("text/plain")) {
            return message.getContent().toString(); // Return plain text if it's in plain text
        } else if (message.isMimeType("text/html")) {
            // If the email content is in HTML, parse and extract plain text from it using Jsoup
            return Jsoup.parse(message.getContent().toString()).text();
        } else if (message.isMimeType("multipart/*")) {
            // If the email is multipart, handle each part
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            return getTextFromMultipart(mimeMultipart);
        }
        return "";
    }

    // Method to extract plain text from multipart content
    private static String getTextFromMultipart(MimeMultipart mimeMultipart) throws Exception {
        StringBuilder textContent = new StringBuilder();
        for (int i = 0; i < mimeMultipart.getCount(); i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                textContent.append(bodyPart.getContent().toString());
            } else if (bodyPart.isMimeType("text/html")) {
                textContent.append(Jsoup.parse(bodyPart.getContent().toString()).text());
            }
        }
        return textContent.toString();
    }

    public static void main(String[] args) {
        OTPF();  // Call the method to fetch and print the OTP
    }
}
