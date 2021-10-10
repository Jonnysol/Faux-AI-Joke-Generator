 package com.company;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class main {


    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    // This method allows to retrieve JSON from URL and parses it.
    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static void main(String[] args) throws IOException, JSONException, InterruptedException {
        Scanner sc = new Scanner(System.in);

        String[] genre = {"Any", "Misc", "Dark", "Pun", "Spooky", "Christmas"};

        System.out.println("Hey, Whats Your name, Buccko?");
        String name = sc.nextLine();
        System.out.println("Well, " + name + " Welcome To The Virtual Comedy Club?");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("What rocks your boat " + name + " ? Enter the number associated to the genre are you into ? ");
        TimeUnit.SECONDS.sleep(4);
        System.out.println("""
                1-Any\r
                2-Misc\r
                3-Dark\r
                4-Pun\r
                5-Spooky\r
                6-Christmas""");
        System.out.print("Get jokes at number:");
        int value = Integer.parseInt(sc.nextLine()) - 1;
        if (value < 1) {
            System.out.println("So you think anything is funny Huh? F%$K you " + name + ".  >:{");
        } else {
            System.out.println("Ah, " + genre[value] + " jokes, They are my favorite. My dad used to tell me " + genre[value] + " Jokes in bed before he beat me :)");
        }
        String uniURL = "https://v2.jokeapi.dev/joke/" + genre[value] + "?type=single";
        JSONObject json = readJsonFromUrl(uniURL);

        System.out.println("Anyway " + name + ", press enter whenever Your ready to start.");
        String next = sc.nextLine();

        if (!next.equals("")) {
            System.exit(0);
        }

        while (next.equals("")) {
            System.out.println("One " + json.get("category") + " Joke coming up....");

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(json.get("joke") + "\n" + " 'Press Enter.' ");
            next = sc.nextLine();
            json = readJsonFromUrl(uniURL);

        }
        System.exit(0);
    }


    }


