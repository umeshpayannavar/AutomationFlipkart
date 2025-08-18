package org.example;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class downloadapp {

    public static void installApk(String deviceId, String apkPath) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder("adb", "-s", deviceId, "install", "-r", apkPath);
        Process process = builder.start();
        int resultCode = process.waitFor();

        if (resultCode == 0) {
            System.out.println("✅ APK installed successfully on device: " + deviceId);
        } else {
            System.err.println("❌ Failed to install APK.");
        }
    }

    public static String downloadApk(String apkUrl, String savePath) throws IOException {
        System.out.println("⬇️ Downloading APK from: " + apkUrl);
        URL url = new URL(apkUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        InputStream in = connection.getInputStream();
        FileOutputStream out = new FileOutputStream(savePath);

        byte[] buffer = new byte[4096];
        int bytesRead;

        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }

        out.close();
        in.close();

        System.out.println("✅ APK downloaded to: " + savePath);
        return savePath;
    }

    public static void main(String[] args) throws Exception {
        String apkUrl = "https://flipkart.apk"; // 🔁 Replace with real APK URL
        String savePath = "C:\\temp\\flipkart.apk";         // ✅ Make sure this path is writable
        String deviceId = "emulator-5554";                  // ✅ Use `adb devices` to get your ID

        // Step 1: Download
        String downloadedApkPath = downloadApk(apkUrl, savePath);

        // Step 2: Install
        installApk(deviceId, downloadedApkPath);
    }
}
