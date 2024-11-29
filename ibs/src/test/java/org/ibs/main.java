package org.ibs;

import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {

        Runtime.getRuntime().exec("cmd /c start server.bat");
        Runtime.getRuntime().exec("taskkill /F /IM cmd.exe -server.bat");
        }
    }


