import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.net.URLConnection;
import java.net.URL;

class Dude extends Frame{
    Button b;
    TextField t;
    Label l;
    Dude(){
        setVisible(true);
        setSize(400,400);
        setLayout(null);
        b = new Button("Download");
        t=new TextField();
        l= new Label("Movie Name");
        b.addActionListener(e -> {
            new TempDude(t.getText());
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        b.setBounds(30,150,100,20);
        t.setBounds(150,30,100,20);
        l.setBounds(30,30,100,20);
        add(l);
        add(t);
        add(b);
    }
    public static void main(String[] args){
        new Dude();
    }
}


class TempDude{
    static Document doc = null;
    static String movie_name1="Bahubali";
    static String movie_name=movie_name1.replace(' ','+');
    static String movieLink = getLink(movie_name);
    TempDude(String movie_name1){
        this.movie_name1 = movie_name1;
        movie_name=movie_name1.replace(' ','+');
        movieLink = getLink(movie_name);
        try {
            new File(movie_name1).mkdirs();
            String url=movieLink;
            try {
                doc = Jsoup.connect(url).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements links = doc.select("a[href]");
            for (Element link:links
                 ) {
                String temp = link.attr("href");
                String match = "iSongs.info";
                if(temp.toLowerCase().contains(match.toLowerCase())) {
                    System.out.println("in");
                    Download(temp,link.text());
                    System.out.println(link.text());
                }
            }
            String s = doc.title();
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static String getLink(String name){
        String url="https://www.google.co.in/search?q=naasongs.com+"+name;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements links = doc.select("a[href]");
        for (Element link:links
                ) {
            String temp = link.attr("href");
            if(temp.contains("https://naasongs.com")){
                movieLink=temp;
                break;
            }
        }
        return movieLink;

    }
    public static void main(String[] args){
        new TempDude("Bahubali");

    }
    public static void Download(String temp,String name) throws IOException {
        URLConnection conn = null;
        try {
            conn = new URL(temp).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream is = conn.getInputStream();
        OutputStream os = new FileOutputStream(new File(movie_name1+"/"+name+".mp3"));
        byte[] buffer = new byte[4096];
        int len;
        while ((len = is.read(buffer)) > 0) {
            os.write(buffer, 0, len);
        }
    }
}

//Google test download

class GTest {
    static String movieLink = null;
    static Document doc = null;

    public static String getLink(String name) {
        String url = "https://www.google.co.in/search?q=naasongs.com+" + name;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements links = doc.select("a[href]");
        for (Element link : links
                ) {
            String temp = link.attr("href");
            if (temp.contains("https://naasongs.com")) {
                movieLink = temp;
                break;
            }
        }
        return movieLink;

    }

    public static String getSong(String snmae) {
        String song = " ";
        String url = "https://www.google.co.in/search?q=+" + snmae.replace(' ', '+') + "+" + "Telugu+Lyrics";
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements links = doc.select("a[href]");
        for (Element link : links
                ) {
            String temp = link.attr("href");
            //System.out.println(temp);
//            if((temp.contains("checklyrics") || temp.contains("telugu.surli")) && !(temp.contains("/movie/"))){
//                song=temp;
//                break;
//            }
            if (temp.contains("lyrics") || temp.contains("telugu")) {
                song = temp;
                break;
            }
        }
        return song;
    }

    public static String getSongString(String url) {
        String song = " ";
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements links = doc.select("p");
        for (Element link : links
                ) {
            song = song+(link.text() + '\n');
            //System.out.println(link.text());
        }
        return song;
    }

    public static void writeSong(String fname,String song) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fname+".txt",true));
        try {
            bw.write(song);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bw.close();
    }

    public static void main(String args[]) throws IOException {
        String url = getSong("I Wanna Fly");
        writeSong("test",getSongString(url));
    }
}

