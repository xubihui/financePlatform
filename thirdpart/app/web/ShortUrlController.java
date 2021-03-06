package web;


import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.ShortUrlService;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * <p>Project: thirdpartyservice</p>
 * <p>Title: ShortUrlController.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Transactional
public class ShortUrlController extends Controller {


    public Result getShortURL() {
        String path = null;
        Http.RequestBody body = request().body();

        if (body.asJson() != null) {
            path = body.asJson().get("path").asText();
        } else {
            Map<String, String> params = Form.form().bindFromRequest().data();
            path = params.get("path");
        }
        Logger.debug("getShortURL params：" + path);

        String shortUrl = ShortUrlService.getShortURL(path);

        Logger.info("getShortURL return：" + shortUrl);

        return ok(Json.toJson(shortUrl));
    }

    public static String getURLContent(String url, String encoding) {
        StringBuffer content = new StringBuffer();
        try {
            // 新建URL对象
            URL u = new URL(url);
            InputStream in = new BufferedInputStream(u.openStream());
            InputStreamReader theHTML = new InputStreamReader(in, encoding);
            int c;
            while ((c = theHTML.read()) != -1) {
                content.append((char) c);
            }
        }
        // 处理异常
        catch (MalformedURLException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
        return content.toString();
    }

}
