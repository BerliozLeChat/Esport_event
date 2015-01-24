package com.event.esport.personnal.esport_event;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @Author François Hallereau
 * @Date 24/01/2015
 */
public class Match{

    private String team1; //name of team 1
    private String team2; //name of team 2
    private String event; //name of the event
    private String date; //date of the event
    private String percent1; //bet estimation of team 1
    private String percent2; //bet estimation of team 2
    private boolean live; //match is live


    public Match() {}




    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        setLive(date);
        if(isLive()){
            this.date = date.substring(0,date.length()-6);
        }
        else{
            this.date = date;
        }
    }

    public boolean isLive(){
        return live;
    }

    public String getPercent1() {
        return percent1;
    }

    public void setPercent1(String percent1) {
        this.percent1 = percent1;
    }

    public String getPercent2() {
        return percent2;
    }

    public void setPercent2(String percent2) {
        this.percent2 = percent2;
    }

    private void setLive(String strdate){
        live=strdate.contains("LIVE");
    }

    public String toString(){
        return "Match in "+date+",at "+event+", "+team1+" vs "+team2+", bet: "+percent1+" | "+percent2;
    }

    public static ArrayList<Match> getListofMatch(int game){
        ArrayList<Match> matchs = new ArrayList<>();
        Document doc=null;
        DownloadTask task = new DownloadTask();
        if(game==1) {
            task.execute("http://dota2lounge.com/");
            try {
                doc = task.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        else if(game==2) {
            task.execute("http://csgolounge.com/");
            try {
                doc = task.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        else
            return null;

        if(doc==null){
            return null;
        }
        Elements elements = doc.getElementsByClass("matchmain"); //get all matchs

        for(Element e : elements){
            Match match = new Match();
            String strdate = e.getElementsByClass("whenm").first().text();
            strdate = strdate.substring(0,strdate.length()-2);
            match.setDate(strdate);
            match.setEvent(e.getElementsByClass("eventm").first().text());

            Elements team =e.getElementsByClass("teamtext");
            String teamtext = team.first().text();
            String teamname = teamtext.substring(0, teamtext.length() - 4);
            String teampercent = teamtext.substring(teamtext.length()-3);
            match.setTeam1(teamname);
            match.setPercent1(teampercent);


            teamtext = team.last().text();
            teamname = teamtext.substring(0, teamtext.length() - 4);
            teampercent = teamtext.substring(teamtext.length()-3);
            match.setTeam2(teamname);
            match.setPercent2(teampercent);

            matchs.add(match);

        }
        return matchs;
    }
}
