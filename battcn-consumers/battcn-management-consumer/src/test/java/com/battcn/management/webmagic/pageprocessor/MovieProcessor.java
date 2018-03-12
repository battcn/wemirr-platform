package com.battcn.management.webmagic.pageprocessor;

import com.battcn.management.webmagic.entity.Actor;
import com.battcn.management.webmagic.entity.Director;
import com.battcn.management.webmagic.entity.Movie;
import com.battcn.management.webmagic.utils.UserAgentUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.util.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovieProcessor implements PageProcessor {

    // 列表正则表达式
    public static final String URL_LIST_INDEX = "^http://www.80s.tw/$";
    // 列表正则表达式
    public static final String URL_LIST = "https?://www\\.80s\\.tw/(movie|ju|dm)/list/?.*";
    // 电影详情正则表达式
    public static final String URL_DETAIL = "https?://www\\.80s\\.tw/(movie|ju|dm)/\\d+";
    // 演员正则表达式
    public static final String URL_ACTOR = "https?://www\\.80s\\.tw/actor/\\d+";
    // 导演正则表达式
    public static final String URL_DIRECTOR = "https?://www\\.80s\\.tw/director/\\d+";

    private static final String ORDER_NUM = "ZF201710169692T66jkr";//讯代理订单号
    private static final String SECRET = "3b23ace31a2447baa44d624e9c5fd0f5";//讯代理密码
    // 抓取网站的相关配置，包括编码、抓取间隔、重试次数、代理、UserAgent等
    //setCycleRetryTimes 重试次数  如果连接超时  读取超时  设置之后可以重试cycleRetryTimes次访问
    //retryTimes  大概是重连次数
    private Site site = Site.me()
            //.addHeader("Proxy-Authorization",ProxyGeneratedUtil.authHeader(ORDER_NUM, SECRET, (int) (new Date().getTime()/1000)))//设置代理 相当于账号密码
            .setDisableCookieManagement(true).setCharset("UTF-8").setTimeOut(30000).setRetryTimes(6).setCycleRetryTimes(6)
            .setSleepTime(new Random().nextInt(20) * 100)
            //.setSleepTime(new Random().nextInt(5) * 700)
            .setUserAgent(UserAgentUtil.getRandomUserAgent());

    @Override
    public void process(Page page) {
        // 如果是打开列表页的链接则新开页面
        if (page.getUrl().regex(URL_LIST_INDEX).match()) {
            List<String> listUrls = page.getHtml().xpath("//div[@id='nav']").links().all();
            // 把列表的URL加入抓取队列
            page.addTargetRequests(listUrls);// 把列表的URL加入抓取队列
        }
        if (page.getUrl().regex(URL_LIST).match()) {
            crawlList(page);
        }
        if (page.getUrl().regex(URL_DETAIL).match()) {
            crawlDetail(page);
        }
        // 演员url验证
        if (page.getUrl().regex(URL_ACTOR).match()) {
            crawlActor(page);
        }
        // 导演url验证
        if (page.getUrl().regex(URL_DIRECTOR).match()) {
            crawldirector(page);
        }
    }

    /**
     * 演员页信息
     *
     * @param page 当前页面对象
     */
    private void crawlActor(Page page) {// 抓取演员页
        //TODO
        String chineseName = "中文名：";
        String sex = "性别：";
        String homePlace = "出生地：";
        Actor actorObj = new Actor();
        // 截取数字
        String actorId = getMatcher("\\d+", page.getUrl().toString());
        if (!StringUtils.isEmpty(actorId)) {
            actorObj.setId(Integer.parseInt(actorId));
        }

        //截取演员姓名
        String actor = page.getHtml().xpath("//div[@class='noborder block1']/div[@class='f_block1']/h1//tidyText()").toString();
        String regEx = "(.+)电影全集";
        String actorName = getMatcher(regEx, actor, 1);
        if (!StringUtils.isEmpty(actorName)) {
            actorObj.setActorName(actorName);
        }
        //捕捉正确的演员信息
        String actorInfo = page.getHtml().xpath("//div[@id='block3']/div[@class='noborder block1']/div/tidyText()").regex(".*中文名.*").toString();
        //捕捉演员拍摄的电影
        List<String> movieidList = page.getHtml().xpath("//div[@id='block3']/div[@class='clearfix noborder block1']/ul").links().regex("https?://www.80s.tw/\\w+/(\\d+)", 1).all();
        Map<String, String> movieidMap = new HashMap<>();
        if (movieidList != null && movieidList.size() > 0) {
            //如果把它用于多线程中将会涉及异步访问的安全性，这是可以用StringBuffer类来进行操作
            StringBuilder movieIds = new StringBuilder();
            for (String movieId : movieidList) {
                if (movieidMap.get(movieId) == null) {
                    //用map防止重复录入id
                    movieidMap.put(movieId, movieId);
                    movieIds.append(movieId).append(",");
                }
            }
            //删除最后一个逗号
            movieIds.deleteCharAt(movieIds.length() - 1);
            actorObj.setMovieIds(movieIds.toString());
        }

        if (actorInfo != null) {
            String[] actors = actorInfo.split("\n");
            for (String str : actors) {
                if (!org.springframework.util.StringUtils.isEmpty(str)) {
                    if (str.length() > 6 && str.substring(0, 6).trim().equals(chineseName)) {
                        str = str.replace(chineseName, "").trim();
                        actorObj.setChineseName(str);
                    }
                    if (str.contains(sex)) {
                        str = str.replace(sex, "").trim();
                        actorObj.setSex(str);
                    }
                    if (str.contains(homePlace)) {
                        str = str.replace(homePlace, "").trim();
                        actorObj.setHomePlace(str);
                    }
                }

            }
        }
        page.putField("actorObj", actorObj);
    }

    /**
     * 导演页信息
     *
     * @param page 当前页面对象
     */
    private void crawldirector(Page page) {// 抓取导演页
        String director = page.getHtml().xpath("//div[@class='f_block1']/h1/tidyText()").toString().trim();
        String regEx = "\"(.+)\"导演作品专辑";
        Director directorObj = new Director();
        // 截取数字
        String directorId = getMatcher("\\d+", page.getUrl().toString());
        if (!StringUtils.isEmpty(directorId)) {
            directorObj.setId(Integer.parseInt(directorId));
        }
        directorObj.setDirectorname(getMatcher(regEx, director, 1));
        List<String> movieList = page.getHtml().xpath("//ul[@class='me1 clearfix']").links().all();
        if (movieList != null && movieList.size() > 0) {
            Map<String, String> movieIdMap = new HashMap<>();
            //如果把它用于多线程中将会涉及异步访问的安全性，这是可以用StringBuffer类来进行操作
            StringBuffer movieids = new StringBuffer();
            for (String movie : movieList) {
                String movieid = getMatcher("\\d+", movie);
                if (movieIdMap.get(movieid) == null) {
                    movieIdMap.put(movieid, movieid);
                    movieids.append(movieid + ",");
                }
            }
            //删除最后一个逗号
            movieids.deleteCharAt(movieids.length() - 1);
            directorObj.setMovieids(movieids.toString());
        }
        page.putField("directorObj", directorObj);
    }

    /**
     * 列表页信息
     *
     * @param page 当前页面对象
     */
    private void crawlList(Page page) {// 抓取论坛列表页
        List<String> listUrls = page.getHtml().xpath("//div[@class='clearfix noborder block1']/div[@class='pager']")
                .links().all();
        // List<String> listUrls =
        // page.getHtml().links().regex(URL_DETAIL).all();
        page.addTargetRequests(listUrls);// 把列表的URL加入抓取队列
        // 将详情url
        List<String> detailUrls = page.getHtml().xpath("//ul[@class='me1 clearfix']").links().all();
        page.addTargetRequests(detailUrls);// 把列表的URL加入抓取队列

    }

    /**
     * 详情页信息
     *
     * @param page 当前页面对象
     */
    private void crawlDetail(Page page) {// 抓取详情页
        Movie movieObj = new Movie();
        // 电影详情页
        String movieDetail = page.getHtml().xpath("//div[@id='body']/div").toString();
        // 截取数字
        String movieId = getMatcher("\\d+", page.getUrl().toString());
        if (!StringUtils.isEmpty(movieId)) {
            movieObj.setId(Integer.parseInt(movieId));
        }
        //类型  电视剧  动漫  综艺
        movieObj.setType(getMatcher("https?://www.80s.tw/(.+)/\\d+", page.getUrl().toString(), 1));
        if (!StringUtils.isEmpty(movieDetail) && new Html(movieDetail).xpath("//div[@class='info']/h1[@class='font14w']/tidyText()") != null) {
            Map<String, String> downLoadMap = new HashMap<>();
            String movieInfo = new Html(movieDetail).xpath("//div[@class='info']").toString();
            movieObj.setTitle(new Html(movieInfo).xpath("//h1[@class='font14w']/tidyText()").toString());
            movieObj.setContent(new Html(movieDetail).xpath("//div[@id='movie_content']/tidyText()").toString());
            //电影上映时间
            String releaseDate = new Html(movieInfo).xpath("//div[@class='clearfix']/span/tidyText()").regex("上映日期：(.+)", 1).toString();
            if (!StringUtils.isEmpty(releaseDate)) {
                try {
                    movieObj.setReleasedate(DateUtils.parseDate(releaseDate, "yyyy-MM-dd"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            // 解析分集和下载地址
            List<String> downLoads = new Html(movieDetail)
                    .xpath("//ul[@class='dllist1']//li[@class='clearfix dlurlelement' OR @class='clearfix dlurlelement backcolor1']//span[@class='dlname nm']//a")
                    .all();
            for (String download : downLoads) {
                String title = new Html(download).xpath("//a/text()").toString();
                String downloadurl = new Html(download).xpath("//a/@href").toString();
                downLoadMap.put(title, downloadurl);
            }
            page.putField("downUrls", downLoadMap);
            //获取评分
            // 先将span_block元素全部匹配下来 再进行正则匹配 选择正确的一条
            String gradeHtml = new Html(movieInfo).xpath("//div[@class='clearfix']//span[@class='span_block']")
                    .regex(".*score.*").toString();

            if (gradeHtml != null) {
                // 这里获取出来的是豆瓣评分：8.3
                String grade = new Html(gradeHtml).xpath("tidyText()").toString();
                // 截取数字
                String regEx = "[^0-9.]";
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(grade);
                String gradeStr = m.replaceAll("").trim();
                if (!StringUtils.isEmpty(gradeStr)) {
                    movieObj.setGrade(Float.valueOf(gradeStr));
                }
            }
            page.putField("movieObj", movieObj);
            // 演员
            List<String> actors = new Html(movieInfo).links().regex("/actor/\\d+").all();
            page.addTargetRequests(actors);
            // 导演
            List<String> directors = new Html(movieInfo).links().regex("/director/\\d+").all();
            page.addTargetRequests(directors);
        }
        /*
         * List<String> listUrls =
		 * page.getHtml().links().regex(URL_DETAIL).all();
		 * page.addTargetRequests(listUrls);//把列表的URL加入抓取队列
		 */
    }

    @Override
    public Site getSite() {
        return site;
    }

    //获取第几个group   比如
    //String regEx = "\"(.+)\"导演作品专辑";page.putField("directorName", getMatcher(regEx, director,1));  表示第一个（）的内容
    public String getMatcher(String regex, String source, Integer goroup) {
        String result = "";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            if (goroup != null) {
                result = matcher.group(goroup);
            } else {
                result = matcher.group();
            }
        }
        return result;
    }

    //直接截取匹配regex的值
    public String getMatcher(String regex, String source) {
        return getMatcher(regex, source, null);
    }
}
