import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.net.URLConnection;
import java.net.URL;

//class Dude extends Frame{
//    Dude(){
//        setVisible(true);
//        setSize(400,400);
//        setLayout(null);
//        Button b = new Button("Download");
//        b.addActionListener(e -> {
//            try {
//                new File(TempDude.movie_name).mkdirs();
//                String url="https://isongsmp3.com/telugu01/hello-2017.html";
//                Document doc = Jsoup.connect(url).get();
//                Elements links = doc.select("a[href]");
//                for (Element link:links
//                        ) {
//                    String temp = link.attr("href");
//                    String match = "[iSongs.info]";
//                    if(temp.toLowerCase().contains(match.toLowerCase())) {
//                        TempDude.Download(temp,link.text());
//                        System.out.println(link.text());
//                    }
//                }
//                String s = doc.title();
//                System.out.println(s);
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//        });
//        addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                System.exit(0);
//            }
//        });
//        b.setBounds(20,20,100,20);
//        add(b);
//    }
//    public static void main(String[] args){
//        new Dude();
//    }
//}
//

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
                String match = "[iSongs.info]";
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
        //new TempDude("Bahubali");
        String movieLink = getLink(movie_name);
        try {
            new File(movie_name1).mkdirs();
            String url=movieLink;
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select("a[href]");
            for (Element link:links
                 ) {
                String temp = link.attr("href");
                String match = "[iSongs.info]";
                if(temp.toLowerCase().contains(match.toLowerCase())) {
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

//class GTest{
//    static String movieLink = null;
//    static Document doc = null;
//    public static String getLink(String name){
//        String url="https://www.google.co.in/search?q=naasongs.com+"+name;
//        try {
//            doc = Jsoup.connect(url).get();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Elements links = doc.select("a[href]");
//        for (Element link:links
//                ) {
//            String temp = link.attr("href");
//            if(temp.contains("https://naasongs.com")){
//                movieLink=temp;
//                break;
//            }
//        }
//        return movieLink;
//
//    }
//    public static  void main (String args[]) throws IOException {
//        String movieLink = getLink("hello");
//
//
//    }
//}
