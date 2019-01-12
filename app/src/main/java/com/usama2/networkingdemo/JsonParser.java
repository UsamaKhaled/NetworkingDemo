package com.usama2.networkingdemo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class JsonParser {

    public String getAllData(){

        StringBuilder dataStringBuilder = new StringBuilder();
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts");
            HttpURLConnection connection= (HttpURLConnection)url.openConnection();
            if (connection.getResponseCode() == 200){
                Scanner sc = new Scanner(connection.getInputStream());
                while(sc.hasNextLine()){
                    dataStringBuilder.append(sc.nextLine());
                }
            };
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataStringBuilder.toString();
    }

    public ArrayList<Posts> convertDataToList (String data){

        ArrayList<Posts>allPosts = new ArrayList<>();
        try {
            JSONArray dataArray = new JSONArray(data);
            JSONObject item;
            Posts posts;
            for(int i = 0; i<dataArray.length();i++){
                item =(JSONObject) dataArray.get(i);
          int userId = item.getInt("userId");
          int id = item.getInt("id");
          String title = item.getString("title");
          String body = item.getString("body");
          posts = new Posts(userId, id, title, body);
          allPosts.add(posts);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return allPosts;
    }
}
